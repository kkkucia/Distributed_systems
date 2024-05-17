# Restaurant order system

A simple project shows how gRPC-Web works.

## Requirements

1. [Node](https://nodejs.org/en/download)
2. [Docker (version 25.0.3 or higher)](https://www.docker.com/)
3. [Protoc compiler](https://github.com/protocolbuffers/protobuf/releases)

### Compile .proto files

From restaurant-service directory:

```
yarn add grpc @grpc/proto-loader protobufjs
```

```
yarn proto-loader-gen-types --grpcLib=@grpc/grpc-js --outDir=proto/  restaurant.proto
```

```
protoc --plugin=protoc-gen-grpc-web=path\to\protoc-gen-grpc-web.exe --js_out=import_style=commonjs:../restaurant-client/proto/ --grpc-web_out=import_style=commonjs+dts,mode=grpcwebtext:../restaurant-client/proto/ --proto_path=./ restaurant.proto
```

### Run Proxy Server

Open Docker Desktop, Go into main directory:

```
docker compose up -d
```

### Run Server

Go to restaurant-server directory and run the following command in this folder to run the server:

```
yarn install
```

then

```
yarn start
```

### Run Client

Go to client-server directory and run the following command in this folder:

```
npx webpack --config webpack.config.js
```

Open index.html in your browser
