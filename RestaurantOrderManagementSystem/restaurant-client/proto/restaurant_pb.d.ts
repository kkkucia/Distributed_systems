import * as jspb from 'google-protobuf'



export class Client extends jspb.Message {
  getName(): string;
  setName(value: string): Client;

  getEmail(): string;
  setEmail(value: string): Client;

  getAddress(): string;
  setAddress(value: string): Client;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Client.AsObject;
  static toObject(includeInstance: boolean, msg: Client): Client.AsObject;
  static serializeBinaryToWriter(message: Client, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Client;
  static deserializeBinaryFromReader(message: Client, reader: jspb.BinaryReader): Client;
}

export namespace Client {
  export type AsObject = {
    name: string,
    email: string,
    address: string,
  }
}

export class OrderRequest extends jspb.Message {
  getClient(): Client | undefined;
  setClient(value?: Client): OrderRequest;
  hasClient(): boolean;
  clearClient(): OrderRequest;

  getDishname(): string;
  setDishname(value: string): OrderRequest;

  getStatus(): StatusType;
  setStatus(value: StatusType): OrderRequest;

  getExtras(): string;
  setExtras(value: string): OrderRequest;

  getQuantity(): number;
  setQuantity(value: number): OrderRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): OrderRequest.AsObject;
  static toObject(includeInstance: boolean, msg: OrderRequest): OrderRequest.AsObject;
  static serializeBinaryToWriter(message: OrderRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): OrderRequest;
  static deserializeBinaryFromReader(message: OrderRequest, reader: jspb.BinaryReader): OrderRequest;
}

export namespace OrderRequest {
  export type AsObject = {
    client?: Client.AsObject,
    dishname: string,
    status: StatusType,
    extras: string,
    quantity: number,
  }
}

export class Order extends jspb.Message {
  getOrderid(): string;
  setOrderid(value: string): Order;

  getDishname(): string;
  setDishname(value: string): Order;

  getStatus(): StatusType;
  setStatus(value: StatusType): Order;

  getExtras(): string;
  setExtras(value: string): Order;

  getQuantity(): number;
  setQuantity(value: number): Order;

  getClient(): Client | undefined;
  setClient(value?: Client): Order;
  hasClient(): boolean;
  clearClient(): Order;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Order.AsObject;
  static toObject(includeInstance: boolean, msg: Order): Order.AsObject;
  static serializeBinaryToWriter(message: Order, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Order;
  static deserializeBinaryFromReader(message: Order, reader: jspb.BinaryReader): Order;
}

export namespace Order {
  export type AsObject = {
    orderid: string,
    dishname: string,
    status: StatusType,
    extras: string,
    quantity: number,
    client?: Client.AsObject,
  }
}

export class Orders extends jspb.Message {
  getOrder(): Order | undefined;
  setOrder(value?: Order): Orders;
  hasOrder(): boolean;
  clearOrder(): Orders;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Orders.AsObject;
  static toObject(includeInstance: boolean, msg: Orders): Orders.AsObject;
  static serializeBinaryToWriter(message: Orders, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Orders;
  static deserializeBinaryFromReader(message: Orders, reader: jspb.BinaryReader): Orders;
}

export namespace Orders {
  export type AsObject = {
    order?: Order.AsObject,
  }
}

export class UpdateOrderStatusRequest extends jspb.Message {
  getOrderid(): string;
  setOrderid(value: string): UpdateOrderStatusRequest;

  getNewstatus(): StatusType;
  setNewstatus(value: StatusType): UpdateOrderStatusRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): UpdateOrderStatusRequest.AsObject;
  static toObject(includeInstance: boolean, msg: UpdateOrderStatusRequest): UpdateOrderStatusRequest.AsObject;
  static serializeBinaryToWriter(message: UpdateOrderStatusRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): UpdateOrderStatusRequest;
  static deserializeBinaryFromReader(message: UpdateOrderStatusRequest, reader: jspb.BinaryReader): UpdateOrderStatusRequest;
}

export namespace UpdateOrderStatusRequest {
  export type AsObject = {
    orderid: string,
    newstatus: StatusType,
  }
}

export class MonitorOrderStatusRequest extends jspb.Message {
  getOrderid(): string;
  setOrderid(value: string): MonitorOrderStatusRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): MonitorOrderStatusRequest.AsObject;
  static toObject(includeInstance: boolean, msg: MonitorOrderStatusRequest): MonitorOrderStatusRequest.AsObject;
  static serializeBinaryToWriter(message: MonitorOrderStatusRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): MonitorOrderStatusRequest;
  static deserializeBinaryFromReader(message: MonitorOrderStatusRequest, reader: jspb.BinaryReader): MonitorOrderStatusRequest;
}

export namespace MonitorOrderStatusRequest {
  export type AsObject = {
    orderid: string,
  }
}

export class OrderStatusUpdate extends jspb.Message {
  getOrderid(): string;
  setOrderid(value: string): OrderStatusUpdate;

  getStatus(): StatusType;
  setStatus(value: StatusType): OrderStatusUpdate;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): OrderStatusUpdate.AsObject;
  static toObject(includeInstance: boolean, msg: OrderStatusUpdate): OrderStatusUpdate.AsObject;
  static serializeBinaryToWriter(message: OrderStatusUpdate, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): OrderStatusUpdate;
  static deserializeBinaryFromReader(message: OrderStatusUpdate, reader: jspb.BinaryReader): OrderStatusUpdate;
}

export namespace OrderStatusUpdate {
  export type AsObject = {
    orderid: string,
    status: StatusType,
  }
}

export class Empty extends jspb.Message {
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Empty.AsObject;
  static toObject(includeInstance: boolean, msg: Empty): Empty.AsObject;
  static serializeBinaryToWriter(message: Empty, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Empty;
  static deserializeBinaryFromReader(message: Empty, reader: jspb.BinaryReader): Empty;
}

export namespace Empty {
  export type AsObject = {
  }
}

export class MenuResponse extends jspb.Message {
  getDishname(): string;
  setDishname(value: string): MenuResponse;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): MenuResponse.AsObject;
  static toObject(includeInstance: boolean, msg: MenuResponse): MenuResponse.AsObject;
  static serializeBinaryToWriter(message: MenuResponse, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): MenuResponse;
  static deserializeBinaryFromReader(message: MenuResponse, reader: jspb.BinaryReader): MenuResponse;
}

export namespace MenuResponse {
  export type AsObject = {
    dishname: string,
  }
}

export enum StatusType { 
  ORDERED = 0,
  IN_PROGRESS = 1,
  READY = 2,
  COMPLETED = 3,
}
