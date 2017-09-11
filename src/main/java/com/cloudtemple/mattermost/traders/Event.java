package com.cloudtemple.mattermost.traders;

import com.cloudtemple.mattermost.MatterMostBotClient;
import com.cloudtemple.mattermost.traders.post.Post;
import java.util.Map;
import java.util.Optional;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * https://api.mattermost.com/#tag/WebSocket { "event": "hello", "data": { "server_version": "3.6.0.1451.1c38da627ebb4e3635677db6939e9195" }, "broadcast":{
 * "omit_users": null, "user_id": "ay5sq51sebfh58ktrce5ijtcwy", "channel_id": "", "team_id": "" } }
 */
@SuppressWarnings("hiding")
@XmlRootElement
public class Event
{
	public String event; //	typing	posted	post_edited	post_deleted	channel_created	channel_deleted	channel_updated	channel_viewed	direct_added	group_added	added_to_team	new_user	leave_team	update_team	user_added	user_updated	user_removed	preference_changed	preferences_changed	preferences_deleted	ephemeral_message	status_change	hello	webrtc	authentication_challenge	reaction_added	reaction_removed	response	emoji_added
	public Map<String, Object> data;
	public Map<String, Object> broadcast;
	public long seq = -1;

	public String action;
	public String status;
	public String seq_reply;

	public String getAction()
	{
		return action;
	}

	public void setAction(final String action)
	{
		this.action = action;
	}

	public String getEvent()
	{
		return event;
	}

	public void setEvent(final String event)
	{
		this.event = event;
	}

	public Map<String, Object> getData()
	{
		return data;
	}

	public void setData(final Map<String, Object> data)
	{
		this.data = data;
	}

	public Map<String, Object> getBroadcast()
	{
		return broadcast;
	}

	public void setBroadcast(final Map<String, Object> broadcast)
	{
		this.broadcast = broadcast;
	}

	public long getSeq()
	{
		return seq;
	}

	public void setSeq(final long seq)
	{
		this.seq = seq;
	}

	@SuppressWarnings("unchecked")
	public Optional<Post> getPost()
	{
		if (null == data)
			return Optional.empty();

		final Object post = data.get("post");
		if (null == post)
			return Optional.empty();

		if (post instanceof String)
			return Optional.of(MatterMostBotClient.decode(200, (String) post, Post.class));
		if (post instanceof Map)
			return Optional.of(new Post((Map<String, Object>) post));

		return Optional.empty();
	}

	@Override
	public String toString()
	{
		final StringBuffer buff = new StringBuffer();
		buff.append("Event{").append("\n");
		{
			if (null != event)
				buff.append("event:").append(event).append("\n");
			if (null != action)
				buff.append("action:").append(action).append("\n");
			if (null != status)
				buff.append("status:").append(status).append("\n");
			if (null != seq_reply)
				buff.append("seq_reply:").append(seq_reply).append("\n");
			if (null != data)
				buff.append("data:").append(data.toString()).append("\n");
			if (null != broadcast)
				buff.append("broadcast:").append(broadcast.toString()).append("\n");
		}
		buff.append("}").append("\n");

		return buff.toString();
	}
}
