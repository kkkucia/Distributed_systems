// Original file: restaurant.proto

import type { StatusType as _restaurant_StatusType, StatusType__Output as _restaurant_StatusType__Output } from '../restaurant/StatusType';

export interface OrderStatusUpdate {
  'orderId'?: (string);
  'status'?: (_restaurant_StatusType);
}

export interface OrderStatusUpdate__Output {
  'orderId'?: (string);
  'status'?: (_restaurant_StatusType__Output);
}
