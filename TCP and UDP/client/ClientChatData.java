package client;

import server.ServerTcpClientHandler;

import java.util.UUID;

public class ClientChatData {
    private final UUID clientId;
    private final int port;
    private final ServerTcpClientHandler serverTcpHandler;

    private ClientChatData(UUID clientId, int port, ServerTcpClientHandler serverTcpHandler) {
        this.clientId = clientId;
        this.port = port;
        this.serverTcpHandler = serverTcpHandler;
    }

    public static ClientChatDataBuilder builder() {
        return new ClientChatDataBuilder();
    }

    public UUID getClientId() {
        return clientId;
    }

    public int getPort() {
        return port;
    }

    public ServerTcpClientHandler getServerTcpHandler() {
        return serverTcpHandler;
    }

    public static class ClientChatDataBuilder {
        private UUID clientId;
        private int port;
        private ServerTcpClientHandler serverTcpHandler;

        private ClientChatDataBuilder() {
        }

        public ClientChatDataBuilder clientId(UUID clientId) {
            this.clientId = clientId;
            return this;
        }

        public ClientChatDataBuilder port(int port) {
            this.port = port;
            return this;
        }

        public ClientChatDataBuilder serverTcpHandler(ServerTcpClientHandler serverTcpHandler) {
            this.serverTcpHandler = serverTcpHandler;
            return this;
        }

        public ClientChatData build() {
            return new ClientChatData(clientId, port, serverTcpHandler);
        }
    }
}
