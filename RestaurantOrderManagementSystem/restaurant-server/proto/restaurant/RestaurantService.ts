// Original file: restaurant.proto

import type * as grpc from '@grpc/grpc-js'
import type { MethodDefinition } from '@grpc/proto-loader'
import type { Empty as _restaurant_Empty, Empty__Output as _restaurant_Empty__Output } from '../restaurant/Empty';
import type { MenuResponse as _restaurant_MenuResponse, MenuResponse__Output as _restaurant_MenuResponse__Output } from '../restaurant/MenuResponse';
import type { MonitorOrderStatusRequest as _restaurant_MonitorOrderStatusRequest, MonitorOrderStatusRequest__Output as _restaurant_MonitorOrderStatusRequest__Output } from '../restaurant/MonitorOrderStatusRequest';
import type { Order as _restaurant_Order, Order__Output as _restaurant_Order__Output } from '../restaurant/Order';
import type { OrderRequest as _restaurant_OrderRequest, OrderRequest__Output as _restaurant_OrderRequest__Output } from '../restaurant/OrderRequest';
import type { OrderStatusUpdate as _restaurant_OrderStatusUpdate, OrderStatusUpdate__Output as _restaurant_OrderStatusUpdate__Output } from '../restaurant/OrderStatusUpdate';
import type { Orders as _restaurant_Orders, Orders__Output as _restaurant_Orders__Output } from '../restaurant/Orders';
import type { UpdateOrderStatusRequest as _restaurant_UpdateOrderStatusRequest, UpdateOrderStatusRequest__Output as _restaurant_UpdateOrderStatusRequest__Output } from '../restaurant/UpdateOrderStatusRequest';

export interface RestaurantServiceClient extends grpc.Client {
  AddOrder(argument: _restaurant_OrderRequest, metadata: grpc.Metadata, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  AddOrder(argument: _restaurant_OrderRequest, metadata: grpc.Metadata, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  AddOrder(argument: _restaurant_OrderRequest, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  AddOrder(argument: _restaurant_OrderRequest, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  addOrder(argument: _restaurant_OrderRequest, metadata: grpc.Metadata, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  addOrder(argument: _restaurant_OrderRequest, metadata: grpc.Metadata, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  addOrder(argument: _restaurant_OrderRequest, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  addOrder(argument: _restaurant_OrderRequest, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  
  GetMenu(argument: _restaurant_Empty, metadata: grpc.Metadata, options?: grpc.CallOptions): grpc.ClientReadableStream<_restaurant_MenuResponse__Output>;
  GetMenu(argument: _restaurant_Empty, options?: grpc.CallOptions): grpc.ClientReadableStream<_restaurant_MenuResponse__Output>;
  getMenu(argument: _restaurant_Empty, metadata: grpc.Metadata, options?: grpc.CallOptions): grpc.ClientReadableStream<_restaurant_MenuResponse__Output>;
  getMenu(argument: _restaurant_Empty, options?: grpc.CallOptions): grpc.ClientReadableStream<_restaurant_MenuResponse__Output>;
  
  GetOrders(argument: _restaurant_Empty, metadata: grpc.Metadata, options?: grpc.CallOptions): grpc.ClientReadableStream<_restaurant_Orders__Output>;
  GetOrders(argument: _restaurant_Empty, options?: grpc.CallOptions): grpc.ClientReadableStream<_restaurant_Orders__Output>;
  getOrders(argument: _restaurant_Empty, metadata: grpc.Metadata, options?: grpc.CallOptions): grpc.ClientReadableStream<_restaurant_Orders__Output>;
  getOrders(argument: _restaurant_Empty, options?: grpc.CallOptions): grpc.ClientReadableStream<_restaurant_Orders__Output>;
  
  MonitorOrderStatus(argument: _restaurant_MonitorOrderStatusRequest, metadata: grpc.Metadata, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_OrderStatusUpdate__Output>): grpc.ClientUnaryCall;
  MonitorOrderStatus(argument: _restaurant_MonitorOrderStatusRequest, metadata: grpc.Metadata, callback: grpc.requestCallback<_restaurant_OrderStatusUpdate__Output>): grpc.ClientUnaryCall;
  MonitorOrderStatus(argument: _restaurant_MonitorOrderStatusRequest, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_OrderStatusUpdate__Output>): grpc.ClientUnaryCall;
  MonitorOrderStatus(argument: _restaurant_MonitorOrderStatusRequest, callback: grpc.requestCallback<_restaurant_OrderStatusUpdate__Output>): grpc.ClientUnaryCall;
  monitorOrderStatus(argument: _restaurant_MonitorOrderStatusRequest, metadata: grpc.Metadata, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_OrderStatusUpdate__Output>): grpc.ClientUnaryCall;
  monitorOrderStatus(argument: _restaurant_MonitorOrderStatusRequest, metadata: grpc.Metadata, callback: grpc.requestCallback<_restaurant_OrderStatusUpdate__Output>): grpc.ClientUnaryCall;
  monitorOrderStatus(argument: _restaurant_MonitorOrderStatusRequest, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_OrderStatusUpdate__Output>): grpc.ClientUnaryCall;
  monitorOrderStatus(argument: _restaurant_MonitorOrderStatusRequest, callback: grpc.requestCallback<_restaurant_OrderStatusUpdate__Output>): grpc.ClientUnaryCall;
  
  UpdateOrderStatus(argument: _restaurant_UpdateOrderStatusRequest, metadata: grpc.Metadata, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  UpdateOrderStatus(argument: _restaurant_UpdateOrderStatusRequest, metadata: grpc.Metadata, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  UpdateOrderStatus(argument: _restaurant_UpdateOrderStatusRequest, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  UpdateOrderStatus(argument: _restaurant_UpdateOrderStatusRequest, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  updateOrderStatus(argument: _restaurant_UpdateOrderStatusRequest, metadata: grpc.Metadata, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  updateOrderStatus(argument: _restaurant_UpdateOrderStatusRequest, metadata: grpc.Metadata, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  updateOrderStatus(argument: _restaurant_UpdateOrderStatusRequest, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  updateOrderStatus(argument: _restaurant_UpdateOrderStatusRequest, callback: grpc.requestCallback<_restaurant_Order__Output>): grpc.ClientUnaryCall;
  
}

export interface RestaurantServiceHandlers extends grpc.UntypedServiceImplementation {
  AddOrder: grpc.handleUnaryCall<_restaurant_OrderRequest__Output, _restaurant_Order>;
  
  GetMenu: grpc.handleServerStreamingCall<_restaurant_Empty__Output, _restaurant_MenuResponse>;
  
  GetOrders: grpc.handleServerStreamingCall<_restaurant_Empty__Output, _restaurant_Orders>;
  
  MonitorOrderStatus: grpc.handleUnaryCall<_restaurant_MonitorOrderStatusRequest__Output, _restaurant_OrderStatusUpdate>;
  
  UpdateOrderStatus: grpc.handleUnaryCall<_restaurant_UpdateOrderStatusRequest__Output, _restaurant_Order>;
  
}

export interface RestaurantServiceDefinition extends grpc.ServiceDefinition {
  AddOrder: MethodDefinition<_restaurant_OrderRequest, _restaurant_Order, _restaurant_OrderRequest__Output, _restaurant_Order__Output>
  GetMenu: MethodDefinition<_restaurant_Empty, _restaurant_MenuResponse, _restaurant_Empty__Output, _restaurant_MenuResponse__Output>
  GetOrders: MethodDefinition<_restaurant_Empty, _restaurant_Orders, _restaurant_Empty__Output, _restaurant_Orders__Output>
  MonitorOrderStatus: MethodDefinition<_restaurant_MonitorOrderStatusRequest, _restaurant_OrderStatusUpdate, _restaurant_MonitorOrderStatusRequest__Output, _restaurant_OrderStatusUpdate__Output>
  UpdateOrderStatus: MethodDefinition<_restaurant_UpdateOrderStatusRequest, _restaurant_Order, _restaurant_UpdateOrderStatusRequest__Output, _restaurant_Order__Output>
}
