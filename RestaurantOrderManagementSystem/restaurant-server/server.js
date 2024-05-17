const grpc = require("@grpc/grpc-js");
const protoLoader = require("@grpc/proto-loader");
const { StatusType } = require("../restaurant-client/proto/restaurant_pb");

var orders = [];

const packageDefinition = protoLoader.loadSync("restaurant.proto", {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true,
});

const proto = grpc.loadPackageDefinition(packageDefinition).restaurant;

const server = new grpc.Server();

server.addService(proto.RestaurantService.service, {
  AddOrder: (call, callback) => {
    console.log("Received order request");
    const order = call.request;
    console.log("Received order:", order);

    orders.push({
      orderId: orders.length,
      dishName: order.dishName,
      status: StatusType.IN_PROGRESS,
      extras: order.extras,
      quantity: order.quantity,
      client: order.client,
    });

    callback(null, order);
    call.on("end", () => {
      console.log("End order request");
    });
  },

  MonitorOrderStatus: (call, callback) => {
    console.log("Received order status request");
    const inputId = call.request.orderId;
    console.log("Received order id:", inputId);

    var orderToMonitor = null;

    orders.forEach((order) => {
      if (order.orderId == inputId) {
        orderToMonitor = order;
        const orderStatusUpdate = {
          orderId: order.orderId,
          status: order.status,
        };
        call.write(orderStatusUpdate);
      }
    });
    callback(null, orderToMonitor);
    console.log("Received order status request ended");
    call.end();
  },

  GetOrders: (call) => {
    console.log("Received orders request");

    orders.forEach((element) => {
      var response = { order: element };
      console.log(response);
      call.write(response);
    });

    call.end();
    console.log("End orders request");
  },

  GetMenu: (call) => {
    console.log("Received menu request");

    var i = 1;
    const intervalId = setInterval(() => {
      const mockDish = "Infinity soup " + i;
      call.write({ dishName: mockDish });
      i += 1;
    }, 1000);

    call.on("cancelled", () => {
      console.log("Cancelled menu request");
      clearInterval(intervalId);
    });
  },
});

const PORT = 9090;
server.bindAsync(
  `0.0.0.0:${PORT}`,
  grpc.ServerCredentials.createInsecure(),
  () => {
    console.log(`Server running at http://0.0.0.0:${PORT}`);
  }
);
