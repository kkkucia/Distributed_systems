// Original file: restaurant.proto

import type { Client as _restaurant_Client, Client__Output as _restaurant_Client__Output } from '../restaurant/Client';
import type { StatusType as _restaurant_StatusType, StatusType__Output as _restaurant_StatusType__Output } from '../restaurant/StatusType';

export interface OrderRequest {
  'client'?: (_restaurant_Client | null);
  'dishName'?: (string);
  'status'?: (_restaurant_StatusType);
  'extras'?: (string);
  'quantity'?: (number);
}

export interface OrderRequest__Output {
  'client'?: (_restaurant_Client__Output);
  'dishName'?: (string);
  'status'?: (_restaurant_StatusType__Output);
  'extras'?: (string);
  'quantity'?: (number);
}
