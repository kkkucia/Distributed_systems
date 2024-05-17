// Original file: restaurant.proto

import type { StatusType as _restaurant_StatusType, StatusType__Output as _restaurant_StatusType__Output } from '../restaurant/StatusType';

export interface UpdateOrderStatusRequest {
  'orderId'?: (string);
  'newStatus'?: (_restaurant_StatusType);
}

export interface UpdateOrderStatusRequest__Output {
  'orderId'?: (string);
  'newStatus'?: (_restaurant_StatusType__Output);
}
