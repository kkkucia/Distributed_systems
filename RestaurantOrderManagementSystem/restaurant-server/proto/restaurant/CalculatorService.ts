// Original file: restaurant.proto

import type * as grpc from '@grpc/grpc-js'
import type { MethodDefinition } from '@grpc/proto-loader'
import type { Input as _restaurant_Input, Input__Output as _restaurant_Input__Output } from '../restaurant/Input';
import type { Output as _restaurant_Output, Output__Output as _restaurant_Output__Output } from '../restaurant/Output';

export interface CalculatorServiceClient extends grpc.Client {
  findFactors(argument: _restaurant_Input, metadata: grpc.Metadata, options?: grpc.CallOptions): grpc.ClientReadableStream<_restaurant_Output__Output>;
  findFactors(argument: _restaurant_Input, options?: grpc.CallOptions): grpc.ClientReadableStream<_restaurant_Output__Output>;
  findFactors(argument: _restaurant_Input, metadata: grpc.Metadata, options?: grpc.CallOptions): grpc.ClientReadableStream<_restaurant_Output__Output>;
  findFactors(argument: _restaurant_Input, options?: grpc.CallOptions): grpc.ClientReadableStream<_restaurant_Output__Output>;
  
  findSquare(argument: _restaurant_Input, metadata: grpc.Metadata, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_Output__Output>): grpc.ClientUnaryCall;
  findSquare(argument: _restaurant_Input, metadata: grpc.Metadata, callback: grpc.requestCallback<_restaurant_Output__Output>): grpc.ClientUnaryCall;
  findSquare(argument: _restaurant_Input, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_Output__Output>): grpc.ClientUnaryCall;
  findSquare(argument: _restaurant_Input, callback: grpc.requestCallback<_restaurant_Output__Output>): grpc.ClientUnaryCall;
  findSquare(argument: _restaurant_Input, metadata: grpc.Metadata, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_Output__Output>): grpc.ClientUnaryCall;
  findSquare(argument: _restaurant_Input, metadata: grpc.Metadata, callback: grpc.requestCallback<_restaurant_Output__Output>): grpc.ClientUnaryCall;
  findSquare(argument: _restaurant_Input, options: grpc.CallOptions, callback: grpc.requestCallback<_restaurant_Output__Output>): grpc.ClientUnaryCall;
  findSquare(argument: _restaurant_Input, callback: grpc.requestCallback<_restaurant_Output__Output>): grpc.ClientUnaryCall;
  
}

export interface CalculatorServiceHandlers extends grpc.UntypedServiceImplementation {
  findFactors: grpc.handleServerStreamingCall<_restaurant_Input__Output, _restaurant_Output>;
  
  findSquare: grpc.handleUnaryCall<_restaurant_Input__Output, _restaurant_Output>;
  
}

export interface CalculatorServiceDefinition extends grpc.ServiceDefinition {
  findFactors: MethodDefinition<_restaurant_Input, _restaurant_Output, _restaurant_Input__Output, _restaurant_Output__Output>
  findSquare: MethodDefinition<_restaurant_Input, _restaurant_Output, _restaurant_Input__Output, _restaurant_Output__Output>
}
