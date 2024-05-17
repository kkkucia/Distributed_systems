import * as grpcWeb from 'grpc-web';

import * as restaurant_pb from './restaurant_pb';


export class RestaurantServiceClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: any; });

  addOrder(
    request: restaurant_pb.OrderRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.RpcError,
               response: restaurant_pb.Order) => void
  ): grpcWeb.ClientReadableStream<restaurant_pb.Order>;

  getOrders(
    request: restaurant_pb.Empty,
    metadata?: grpcWeb.Metadata
  ): grpcWeb.ClientReadableStream<restaurant_pb.Orders>;

  updateOrderStatus(
    request: restaurant_pb.UpdateOrderStatusRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.RpcError,
               response: restaurant_pb.Order) => void
  ): grpcWeb.ClientReadableStream<restaurant_pb.Order>;

  monitorOrderStatus(
    request: restaurant_pb.MonitorOrderStatusRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.RpcError,
               response: restaurant_pb.OrderStatusUpdate) => void
  ): grpcWeb.ClientReadableStream<restaurant_pb.OrderStatusUpdate>;

  getMenu(
    request: restaurant_pb.Empty,
    metadata?: grpcWeb.Metadata
  ): grpcWeb.ClientReadableStream<restaurant_pb.MenuResponse>;

}

export class RestaurantServicePromiseClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: any; });

  addOrder(
    request: restaurant_pb.OrderRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<restaurant_pb.Order>;

  getOrders(
    request: restaurant_pb.Empty,
    metadata?: grpcWeb.Metadata
  ): grpcWeb.ClientReadableStream<restaurant_pb.Orders>;

  updateOrderStatus(
    request: restaurant_pb.UpdateOrderStatusRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<restaurant_pb.Order>;

  monitorOrderStatus(
    request: restaurant_pb.MonitorOrderStatusRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<restaurant_pb.OrderStatusUpdate>;

  getMenu(
    request: restaurant_pb.Empty,
    metadata?: grpcWeb.Metadata
  ): grpcWeb.ClientReadableStream<restaurant_pb.MenuResponse>;

}

