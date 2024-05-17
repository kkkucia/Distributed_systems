import type * as grpc from '@grpc/grpc-js';
import type { EnumTypeDefinition, MessageTypeDefinition } from '@grpc/proto-loader';

import type { RestaurantServiceClient as _restaurant_RestaurantServiceClient, RestaurantServiceDefinition as _restaurant_RestaurantServiceDefinition } from './restaurant/RestaurantService';

type SubtypeConstructor<Constructor extends new (...args: any) => any, Subtype> = {
  new(...args: ConstructorParameters<Constructor>): Subtype;
};

export interface ProtoGrpcType {
  restaurant: {
    Client: MessageTypeDefinition
    Empty: MessageTypeDefinition
    MenuResponse: MessageTypeDefinition
    MonitorOrderStatusRequest: MessageTypeDefinition
    Order: MessageTypeDefinition
    OrderRequest: MessageTypeDefinition
    OrderStatusUpdate: MessageTypeDefinition
    Orders: MessageTypeDefinition
    RestaurantService: SubtypeConstructor<typeof grpc.Client, _restaurant_RestaurantServiceClient> & { service: _restaurant_RestaurantServiceDefinition }
    StatusType: EnumTypeDefinition
    UpdateOrderStatusRequest: MessageTypeDefinition
  }
}

