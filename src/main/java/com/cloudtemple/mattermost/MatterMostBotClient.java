package com.cloudtemple.mattermost;

import com.cloudtemple.mattermost.traders.ApiV4Exception;
import com.cloudtemple.mattermost.traders.Event;
import com.cloudtemple.mattermost.traders.MatterMostClientObject;
import com.cloudtemple.mattermost.traders.channel.Channel;
import com.cloudtemple.mattermost.traders.channel.ChannelId;
import com.cloudtemple.mattermost.traders.file.File;
import com.cloudtemple.mattermost.traders.post.Post;
import com.cloudtemple.mattermost.traders.post.PostId;
import com.cloudtemple.mattermost.traders.post.PostList;
import com.cloudtemple.mattermost.traders.post.PostRequest;
import com.cloudtemple.mattermost.traders.post.SearchQuery;
import com.cloudtemple.mattermost.traders.post.Status;
import com.cloudtemple.mattermost.traders.team.Team;
import com.cloudtemple.mattermost.traders.team.TeamId;
import com.cloudtemple.mattermost.traders.user.User;
import com.cloudtemple.mattermost.traders.user.UserId;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.client.WebClient;

public class MatterMostBotClient
{
	public static final Logger _logger = Logger.getLogger(MatterMostBotClient.class.getName());

	static
	{
		_logger.setLevel(Level.INFO);
	}

	public static final String apiV4 = "/api/v4";
	private final ObjectMapper _json = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
	private final WebClient _client;
	private final WebSocketClient _asyncClient;

	private final Set<WsSocketListener> eventListeners = new HashSet<>();
	private final Map<String, Set<WsSocketListener>> channelListeners = new HashMap<>();

	public MatterMostBotClient(final String host, final String personalToken, final Optional<WsSocketListener> listener, final boolean secure)
	{
		String protocol = "http://";
		String wsProtocol = "ws://";
		if (secure)
		{
			protocol = "https://";
			wsProtocol = "wss://";
		}

		final WebClient client = WebClient.create(protocol + host + apiV4);
		final String bearer = "Bearer " + personalToken;
		client.header("Authorization", bearer);
		_client = client;
		if (listener.isPresent())
			eventListeners.add(listener.get());
		_asyncClient = new WebSocketClient(wsProtocol + host + apiV4 + "/websocket", bearer, Optional.of(new MatterMostEventDispatcher()));
	}

	public MatterMostBotClient(final String host, final String personalToken, final Optional<WsSocketListener> listener)
	{
		this(host, personalToken, listener, false);
	}

	public MatterMostBotClient(final String host, final String personalToken)
	{
		this(host, personalToken, Optional.empty(), false);
	}

	public MatterMostBotClient(final String host, final String personalToken, final WsSocketListener listener)
	{
		this(host, personalToken, Optional.ofNullable(listener));
	}

	protected WebSocketClient asyncClient()
	{
		return _asyncClient;
	}

	public void connect(final WsSocketListener listener)
	{
		try
		{
			_asyncClient.connect(listener);
		}
		catch (final Exception exception)
		{
			throw new ApiV4Exception(exception);
		}
	}

	public void connect()
	{
		try
		{
			_asyncClient.connect();
		}
		catch (final Exception exception)
		{
			throw new ApiV4Exception(exception);
		}
	}

	public void disconnect()
	{
		_asyncClient.close();
	}

	public void await(final int seconds)
	{
		try
		{
			Thread.yield();
			Thread.sleep(seconds * 1000);
		}
		catch (final Exception exception)
		{
			throw new ApiV4Exception(exception);
		}
	}

	protected static String toString(final Response r)
	{
		try
		{
			try (final InputStream is = (InputStream) r.getEntity())
			{
				final byte[] b = new byte[1024];
				int read = 0;
				final StringBuffer buff = new StringBuffer();
				while ((read = is.read(b)) > 0)
					buff.append(new String(b, 0, read));
				return buff.toString();
			}
		}
		catch (final IOException e)
		{
			throw new ApiV4Exception(e);
		}
	}

	public static <T> T decode(final int status, final String str, final Class<T> t)
	{
		try
		{
			if (200 == status || 201 == status)
				return new MappingJsonFactory().setCodec(new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)).createParser(str).readValueAs(t);
			else
				throw new ApiV4Exception(new MappingJsonFactory().createParser(str).readValueAs(com.cloudtemple.mattermost.traders.Error.class));
		}
		catch (final IOException e)
		{
			_logger.log(Level.SEVERE, "", e);
			throw new ApiV4Exception(e);
		}
	}

	protected static <T> T decode(final Response r, final Class<T> t)
	{
		final String str = toString(r);
		_logger.fine(() -> r.getStatus() + "\tResponse : " + str);
		return decode(r.getStatus(), str, t);
	}

	protected <T> T decodeClientObject(final Response r, final Class<T> t)
	{
		final T returnValue = decode(r, t);
		if (returnValue instanceof MatterMostClientObject)
			((MatterMostClientObject) returnValue).setClient(this);
		_logger.fine(() -> "decode, returning " + returnValue);
		return returnValue;
	}

	public <T> T get(final String path, final Class<T> t)
	{
		_logger.fine(() -> "GET Path : " + path);
		final Response r;
		synchronized (_client) // If you don't want synchronization, look at the webSocket client.
		{
			r = _client.replacePath(path).accept("application/json").get();
		}
		return decodeClientObject(r, t);
	}

	public <T> T post(final String path, final Object jsonPostBody, final Class<T> t)
	{
		try
		{
			final String str = (jsonPostBody instanceof String) ? (String) jsonPostBody : _json.writeValueAsString(jsonPostBody);
			_logger.fine(() -> "POST Path : " + path + "\t" + str);
			final Response r;
			synchronized (_client)
			{
				r = _client.replacePath(path).accept("application/json").post(str);
			}
			return decodeClientObject(r, t);
		}
		catch (final Exception e)
		{
			throw new ApiV4Exception(e);
		}
	}

	public <T> T put(final String path, final Object jsonPostBody, final Class<T> t)
	{
		try
		{
			final String str = (jsonPostBody instanceof String) ? (String) jsonPostBody : _json.writeValueAsString(jsonPostBody);
			_logger.fine(() -> "POST Path : " + path + "\t" + str);
			final Response r;
			synchronized (_client)
			{
				r = _client.replacePath(path).accept("application/json").put(str);
			}
			return decodeClientObject(r, t);
		}
		catch (final Exception e)
		{
			throw new ApiV4Exception(e);
		}
	}

	public <T> T delete(final String path, final Class<T> t)
	{
		try
		{
			final Response r;
			synchronized (_client)
			{
				r = _client.replacePath(path).accept("application/json").delete();
			}
			return decodeClientObject(r, t);
		}
		catch (final Exception e)
		{
			throw new ApiV4Exception(e);
		}
	}

	public void addEventListener(final WsSocketListener listener)
	{
		eventListeners.add(listener);
	}

	public void removeEventListener(final WsSocketListener listener)
	{
		eventListeners.remove(listener);
	}

	public void addChannelListener(final String channelId, final WsSocketListener listener)
	{
		Set<WsSocketListener> listeners = channelListeners.get(channelId);
		if (listeners == null)
		{
			listeners = new HashSet<>();
			channelListeners.put(channelId, listeners);
		}
		listeners.add(listener);
	}

	public void removeChannelListener(final String channelId, final WsSocketListener listener)
	{
		final Set<WsSocketListener> listeners = channelListeners.get(channelId);
		if (listeners != null)
		{
			listeners.remove(listener);
			if (listeners.isEmpty())
				channelListeners.remove(channelId);
		}
	}

	public User getUsersMe()
	{
		return get("/users/me", User.class);
	}

	public User getUser(final UserId userId)
	{
		return get("/users/" + userId, User.class);
	}

	public User[] getUsers()
	{
		return get("/users", User[].class);
	}

	public User getUserByUsername(final String username)
	{
		return get("/users/username/" + username, User.class);
	}

	public Team[] getTeams()
	{
		return get("/teams", Team[].class);
	}

	public Team getTeam(final String teamName)
	{
		return get("/teams/name/" + teamName, Team.class);
	}

	public Team[] getMyTeams()
	{
		return get("/users/me/teams", Team[].class);
	}

	public Channel getTeamChannelByName(final TeamId teamId, final String channelName)
	{
		return get("/teams/" + teamId + "/channels/name/" + channelName, Channel.class);
	}

	public Post sendPost(final PostRequest postRequest)
	{
		return post("/posts", postRequest, Post.class);
	}

	public Post sendPost(final ChannelId channelId, final String textMessage)
	{
		return post("/posts", new PostRequest(channelId, textMessage, null, null), Post.class);
	}

	public PostList getPostsForChannel(final ChannelId channelId)
	{
		return get("/channels/" + channelId + "/posts", PostList.class);
	}

	public Post getPost(final PostId postId)
	{
		return get("/posts/" + postId, Post.class);
	}

	public PostList getThread(final PostId postId)
	{
		return get("/posts/" + postId + "/thread" + postId, PostList.class);
	}

	public PostList getFlaggedPosts(final UserId userId)
	{
		return get("/users/" + userId + "/posts/flagged", PostList.class);
	}

	public File[] getFilesInfo(final PostId postId)
	{
		return get("/posts/" + postId + "/files/info", File[].class);
	}

	public PostList searchTeamPost(final TeamId teamId, final SearchQuery query)
	{
		return post("/team/" + teamId + "/posts/search", query, PostList.class);
	}

	public PostList searchTeamPost(final TeamId teamId, final String terms, final boolean is_or_search)
	{
		return searchTeamPost(teamId, new SearchQuery(terms, is_or_search));
	}

	public Status performPostAction(final PostId postId, final String actionId, final Object actionDescription)
	{
		return post("/posts/" + postId + "/actions/" + actionId, actionDescription, Status.class);
	}

	public Channel createDirectChannel(final UserId alpha, final UserId beta)
	{
		final String[] message = { alpha.getId(), beta.getId() };
		return post("/channels/direct", message, Channel.class);
	}

	public Channel getChannel(final ChannelId channelId)
	{
		return get("/channels/" + channelId, Channel.class);
	}

	public Channel[] getChannelsForUser(final UserId userId, final TeamId teamId)
	{
		return get("/users/" + userId + "/teams/" + teamId + "/channels", Channel[].class);
	}

	public Team createTeam(final String teamId, final String displayName, final boolean open)
	{
		final Map<String, String> body = new HashMap<>();
		body.put("name", teamId);
		body.put("display_name", displayName);
		body.put("type", open ? "O" : "I");
		return post("/teams", body, Team.class);
	}

	public class MatterMostEventDispatcher implements WsSocketListener
	{
		@Override
		public void onEvent(final Event event)
		{
			_logger.fine("MM:  onEvent()");
			for (final WsSocketListener listener : eventListeners)
				// dispatch to the general listener list
				try
				{
					listener.onEvent(event);
				}
				catch (@SuppressWarnings("unused") final Exception e)
				{
					// ignore; listeners should really handle their own exceptions
				}
			Object channelId = event.data.get("channel_id");
			if (channelId == null)
				channelId = event.broadcast.get("channel_id");
			if (channelId != null && channelId instanceof String)
			{
				final Set<WsSocketListener> cListeners = channelListeners.get(channelId);
				if (cListeners != null && !cListeners.isEmpty())
					for (final WsSocketListener listener : cListeners)
						try
						{
							listener.onEvent(event);
						}
						catch (@SuppressWarnings("unused") final Exception e)
						{
							// ignore; listeners should really handle their own exceptions
						}
			}
		}

	}
}
