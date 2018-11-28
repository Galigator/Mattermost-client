package com.cloudtemple.mattermost;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cloudtemple.mattermost.traders.Event;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.ws.WebSocket;
import com.ning.http.client.ws.WebSocketUpgradeHandler;

/**
 * Websocket client.
 */
class WebSocketClient {

    protected static final Logger _logger = Logger.getLogger(WebSocketClient.class.getName());

    static {
        _logger.setLevel(Level.INFO);
    }

    private final static String _authorization = "Authorization";
    private final AsyncHttpClient _client;
    private final String _url;
    private final String _bearer;
    private volatile Optional<WsSocketListener> _listener = Optional.empty();
    private volatile WebSocket _websocket;
    private boolean _reconnectionEnable = true;
    private final WsSocketListener _reconnectListener = new WsSocketListener() {
        @Override
        public void onClose(final WebSocket ws) {
            _logger.info("[ws] reconnect");
            if (_reconnectionEnable) {
                try {
                    connect();
                } catch (final Exception e) {
                    _logger.log(Level.SEVERE, "Can't reconnect", e);
                }
            }
        }

        @Override
        public void onEvent(final Event event) {
            // Trully don't care.
        }
    };

    private WebSocketClient(final String url_, final String bearer, final AsyncHttpClient client_) {
        _client = client_;
        _url = url_;
        _bearer = bearer;
    }

    protected WebSocketClient(final String url_, final String bearer, final Optional<WsSocketListener> listener) {
        this(url_, bearer, new AsyncHttpClient());
        _listener = listener;
    }

    protected WebSocketClient(final String url_, final String bearer) {
        this(url_, bearer, new AsyncHttpClient());
    }

    public void connect() throws InterruptedException, ExecutionException {
        if (_listener.isPresent()) {
            _websocket = _client.prepareGet(_url)//
                    .setHeader(_authorization, _bearer)//
                    .execute(new WebSocketUpgradeHandler.Builder()//
                            .addWebSocketListener(_listener.get())//
                            .addWebSocketListener(_reconnectListener)//
                            .build()//
                    ).get();
        }
    }

    public void connect(final WsSocketListener listener) throws InterruptedException, ExecutionException {
        _listener = Optional.ofNullable(listener);
        connect();
    }

    public void sendMessage(final String message) {
        _websocket.sendMessage(message);
    }

    public void sendMessage(final byte[] message) {
        _logger.info("[ws] received --> " + message);
        _websocket.sendMessage(message);
    }

    public void close() {
        _reconnectionEnable = false;
        _logger.info("close()");
        _websocket.close();
        _client.close();
    }
}
