// Original file: restaurant.proto

import type { Long } from '@grpc/proto-loader';

export interface Output {
  'result'?: (number | string | Long);
}

export interface Output__Output {
  'result'?: (Long);
}
