/**
 * @fileoverview gRPC-Web generated client stub for restaurant
 * @enhanceable
 * @public
 */

// Code generated by protoc-gen-grpc-web. DO NOT EDIT.
// versions:
// 	protoc-gen-grpc-web v1.4.2
// 	protoc              v3.15.6
// source: restaurant.proto


/* eslint-disable */
// @ts-nocheck



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.restaurant = require('./restaurant_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.restaurant.RestaurantServiceClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname.replace(/\/+$/, '');

};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.restaurant.RestaurantServicePromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname.replace(/\/+$/, '');

};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.restaurant.OrderRequest,
 *   !proto.restaurant.Order>}
 */
const methodDescriptor_RestaurantService_AddOrder = new grpc.web.MethodDescriptor(
  '/restaurant.RestaurantService/AddOrder',
  grpc.web.MethodType.UNARY,
  proto.restaurant.OrderRequest,
  proto.restaurant.Order,
  /**
   * @param {!proto.restaurant.OrderRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.restaurant.Order.deserializeBinary
);


/**
 * @param {!proto.restaurant.OrderRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.restaurant.Order)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.restaurant.Order>|undefined}
 *     The XHR Node Readable Stream
 */
proto.restaurant.RestaurantServiceClient.prototype.addOrder =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/restaurant.RestaurantService/AddOrder',
      request,
      metadata || {},
      methodDescriptor_RestaurantService_AddOrder,
      callback);
};


/**
 * @param {!proto.restaurant.OrderRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.restaurant.Order>}
 *     Promise that resolves to the response
 */
proto.restaurant.RestaurantServicePromiseClient.prototype.addOrder =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/restaurant.RestaurantService/AddOrder',
      request,
      metadata || {},
      methodDescriptor_RestaurantService_AddOrder);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.restaurant.Empty,
 *   !proto.restaurant.Orders>}
 */
const methodDescriptor_RestaurantService_GetOrders = new grpc.web.MethodDescriptor(
  '/restaurant.RestaurantService/GetOrders',
  grpc.web.MethodType.SERVER_STREAMING,
  proto.restaurant.Empty,
  proto.restaurant.Orders,
  /**
   * @param {!proto.restaurant.Empty} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.restaurant.Orders.deserializeBinary
);


/**
 * @param {!proto.restaurant.Empty} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.restaurant.Orders>}
 *     The XHR Node Readable Stream
 */
proto.restaurant.RestaurantServiceClient.prototype.getOrders =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/restaurant.RestaurantService/GetOrders',
      request,
      metadata || {},
      methodDescriptor_RestaurantService_GetOrders);
};


/**
 * @param {!proto.restaurant.Empty} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.restaurant.Orders>}
 *     The XHR Node Readable Stream
 */
proto.restaurant.RestaurantServicePromiseClient.prototype.getOrders =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/restaurant.RestaurantService/GetOrders',
      request,
      metadata || {},
      methodDescriptor_RestaurantService_GetOrders);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.restaurant.UpdateOrderStatusRequest,
 *   !proto.restaurant.Order>}
 */
const methodDescriptor_RestaurantService_UpdateOrderStatus = new grpc.web.MethodDescriptor(
  '/restaurant.RestaurantService/UpdateOrderStatus',
  grpc.web.MethodType.UNARY,
  proto.restaurant.UpdateOrderStatusRequest,
  proto.restaurant.Order,
  /**
   * @param {!proto.restaurant.UpdateOrderStatusRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.restaurant.Order.deserializeBinary
);


/**
 * @param {!proto.restaurant.UpdateOrderStatusRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.restaurant.Order)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.restaurant.Order>|undefined}
 *     The XHR Node Readable Stream
 */
proto.restaurant.RestaurantServiceClient.prototype.updateOrderStatus =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/restaurant.RestaurantService/UpdateOrderStatus',
      request,
      metadata || {},
      methodDescriptor_RestaurantService_UpdateOrderStatus,
      callback);
};


/**
 * @param {!proto.restaurant.UpdateOrderStatusRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.restaurant.Order>}
 *     Promise that resolves to the response
 */
proto.restaurant.RestaurantServicePromiseClient.prototype.updateOrderStatus =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/restaurant.RestaurantService/UpdateOrderStatus',
      request,
      metadata || {},
      methodDescriptor_RestaurantService_UpdateOrderStatus);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.restaurant.MonitorOrderStatusRequest,
 *   !proto.restaurant.OrderStatusUpdate>}
 */
const methodDescriptor_RestaurantService_MonitorOrderStatus = new grpc.web.MethodDescriptor(
  '/restaurant.RestaurantService/MonitorOrderStatus',
  grpc.web.MethodType.UNARY,
  proto.restaurant.MonitorOrderStatusRequest,
  proto.restaurant.OrderStatusUpdate,
  /**
   * @param {!proto.restaurant.MonitorOrderStatusRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.restaurant.OrderStatusUpdate.deserializeBinary
);


/**
 * @param {!proto.restaurant.MonitorOrderStatusRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.restaurant.OrderStatusUpdate)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.restaurant.OrderStatusUpdate>|undefined}
 *     The XHR Node Readable Stream
 */
proto.restaurant.RestaurantServiceClient.prototype.monitorOrderStatus =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/restaurant.RestaurantService/MonitorOrderStatus',
      request,
      metadata || {},
      methodDescriptor_RestaurantService_MonitorOrderStatus,
      callback);
};


/**
 * @param {!proto.restaurant.MonitorOrderStatusRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.restaurant.OrderStatusUpdate>}
 *     Promise that resolves to the response
 */
proto.restaurant.RestaurantServicePromiseClient.prototype.monitorOrderStatus =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/restaurant.RestaurantService/MonitorOrderStatus',
      request,
      metadata || {},
      methodDescriptor_RestaurantService_MonitorOrderStatus);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.restaurant.Empty,
 *   !proto.restaurant.MenuResponse>}
 */
const methodDescriptor_RestaurantService_GetMenu = new grpc.web.MethodDescriptor(
  '/restaurant.RestaurantService/GetMenu',
  grpc.web.MethodType.SERVER_STREAMING,
  proto.restaurant.Empty,
  proto.restaurant.MenuResponse,
  /**
   * @param {!proto.restaurant.Empty} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.restaurant.MenuResponse.deserializeBinary
);


/**
 * @param {!proto.restaurant.Empty} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.restaurant.MenuResponse>}
 *     The XHR Node Readable Stream
 */
proto.restaurant.RestaurantServiceClient.prototype.getMenu =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/restaurant.RestaurantService/GetMenu',
      request,
      metadata || {},
      methodDescriptor_RestaurantService_GetMenu);
};


/**
 * @param {!proto.restaurant.Empty} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.restaurant.MenuResponse>}
 *     The XHR Node Readable Stream
 */
proto.restaurant.RestaurantServicePromiseClient.prototype.getMenu =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/restaurant.RestaurantService/GetMenu',
      request,
      metadata || {},
      methodDescriptor_RestaurantService_GetMenu);
};


module.exports = proto.restaurant;

