const {
  StatusType,
  OrderRequest,
  MonitorOrderStatusRequest,
  Empty,
  Client,
} = require("./proto/restaurant_pb.js");
const {
  RestaurantServiceClient,
} = require("./proto/restaurant_grpc_web_pb.js");

const showOrdersButton = document.getElementById("show-orders-button");
const showOrderStatusButton = document.getElementById("show-status-button");
const showMenuButton = document.getElementById("show-menu-button");
const addOrderButton = document.getElementById("add-order-button");
const stopMenuButton = document.getElementById("menu-stop");

const orderIdInput = document.getElementById("orderId");

const clientNameInput = document.getElementById("client-name");
const clientEmailInput = document.getElementById("client-email");
const clientAddressInput = document.getElementById("client-address");
const dishNameInput = document.getElementById("dish-name");
const extrasSelect = document.getElementById("extras");
const quantityInput = document.getElementById("quantity");

var client = new RestaurantServiceClient("http://localhost:8080");

addMenuResponse = (r) => {
  const ele = `
<div class="alert alert-primary" role="alert">
  ${r.getDishname()}
</div>
`;
  const div = document.createElement("div");
  div.innerHTML = ele;

  document.getElementById("menu-list").prepend(div);
  setTimeout(() => {
    div.remove();
  }, 9000);
};

const statusText = {
  0: "ORDERED",
  1: "IN_PROGRESS",
  2: "READY",
  3: "COMPLETED",
};

addOrderStatusResponse = (r) => {
  const ele = `
<div class="alert alert-primary" role="alert">
  OrderId: ${r.getOrderid()}; Status: ${statusText[r.getStatus()]},
</div>
`;
  const div = document.createElement("div");
  div.innerHTML = ele;

  document.getElementById("status-list").prepend(div);
  setTimeout(() => {
    div.remove();
  }, 9000);
};

addOrderResponse = (r) => {
  console.log(r.getOrder());
  const order = r.getOrder();
  console.log(r);

  const ele = `
      <div class="alert alert-primary" role="alert">
        OrderId: ${order.getOrderid()}, 
        Dish: ${order.getDishname()},
        Status: ${statusText[order.getStatus()]},
        Extras: ${order.getExtras()},
        Quantity: ${order.getQuantity()},
        Client: ${order.getClient().getName()}, ${order
    .getClient()
    .getEmail()}, ${order.getClient().getAddress()}
      </div>
    `;
  const div = document.createElement("div");
  div.innerHTML = ele;

  document.getElementById("orders-list").prepend(div);
  setTimeout(() => {
    div.remove();
  }, 9000);
};

const addOrder = () => {
  const order = new OrderRequest();

  const clientInfo = new Client();
  clientInfo.setName(clientNameInput.value);
  clientInfo.setEmail(clientEmailInput.value);
  clientInfo.setAddress(clientAddressInput.value);

  order.setClient(clientInfo);
  order.setDishname(dishNameInput.value);
  order.setStatus(StatusType.ORDERED);
  order.setExtras(extrasSelect.value);
  order.setQuantity(quantityInput.value);

  const request = order;

  client.addOrder(request, {}, (err, response) => {
    err ? console.log(err) : console.log("Added order:", response);
  });
};

addOrderButton.addEventListener("click", () => {
  console.log("Add order request clicked");
  addOrder();
});

const getMenu = () => {
  var input = new Empty();
  var stream = client.getMenu(input);

  stopMenuButton.addEventListener("click", () => {
    console.log("stream menu cancel clicked");
    stream.cancel();
  });

  stream.on("data", (data) => {
    console.log("Received data:", data);
    addMenuResponse(data);
  });
  stream.on("status", (status) => {
    console.log("status " + status.code);
  });
  stream.on("end", (end) => {});
};

showMenuButton.addEventListener("click", () => {
  console.log("Show menu request clicked");
  getMenu();
});

const monitorOrder = () => {
  var input = new MonitorOrderStatusRequest();
  input.setOrderid(orderIdInput.value);
  console.log("MONITOR")

  client.monitorOrderStatus(input, {}, (err, r) => {
    err ? console.log(err) : addOrderStatusResponse(r);
  });
};

showOrderStatusButton.addEventListener("click", () => {
  console.log("Show order status request clicked");
  monitorOrder();
});

const getOrders = () => {
  var input = new Empty();
  var stream = client.getOrders(input);

  stream.on("data", (data) => {
    console.log("Received data:", data.getOrder());
    addOrderResponse(data);
  });
  stream.on("status", (status) => {
    console.log("status " + status.code);
  });
  stream.on("end", (end) => {
    console.log("End of stream");
  });
};

showOrdersButton.addEventListener("click", () => {
  console.log("Show orders request clicked");
  getOrders();
});
