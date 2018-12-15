package com.cloudtemple.mattermost;

import com.cloudtemple.mattermost.traders.Event;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.ning.http.client.ws.WebSocket;
import com.ning.http.client.ws.WebSocketByteListener;
import com.ning.http.client.ws.WebSocketTextListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface WsSocketListener extends WebSocketTextListener, WebSocketByteListener
{
	public static final Logger _logger = WebSocketClient._logger;

	@Override
	default public void onOpen(final WebSocket ws)
	{
		_logger.info("[ws] opened");
	}

	@Override
	default public void onClose(final WebSocket ws)
	{
		_logger.info("[ws] closed");
	}

	@Override
	default public void onError(final Throwable t)
	{
		t.printStackTrace();
		_logger.info("[ws] error: " + t);
	}

	@Override
	default public void onMessage(final byte[] message)
	{
		onMessage(new String(message));
	}

	@Override
	default public void onMessage(final String message)
	{
		try
		{
			onEvent(new MappingJsonFactory().createParser(message).readValueAs(Event.class));
		}
		catch (final Exception e)
		{
			_logger.log(Level.SEVERE, "Can't convert json to Map<String,Object>" + message, e);
			onEvent(new Event());
		}
	}

	public void onEvent(final Event event);
}
