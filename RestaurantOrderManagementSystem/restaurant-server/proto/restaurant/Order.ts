// Original file: restaurant.proto

import type { StatusType as _restaurant_StatusType, StatusType__Output as _restaurant_StatusType__Output } from '../restaurant/StatusType';
import type { Client as _restaurant_Client, Client__Output as _restaurant_Client__Output } from '../restaurant/Client';

export interface Order {
  'orderId'?: (string);
  'dishName'?: (string);
  'status'?: (_restaurant_StatusType);
  'extras'?: (string);
  'quantity'?: (number);
  'client'?: (_restaurant_Client | null);
}

export interface Order__Output {
  'orderId'?: (string);
  'dishName'?: (string);
  'status'?: (_restaurant_StatusType__Output);
  'extras'?: (string);
  'quantity'?: (number);
  'client'?: (_restaurant_Client__Output);
}
