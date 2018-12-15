package com.cloudtemple.mattermost.traders.channel;

import com.cloudtemple.mattermost.WsSocketListener;
import com.cloudtemple.mattermost.traders.ApiV4Exception;
import com.cloudtemple.mattermost.traders.MatterMostClientObject;
import com.cloudtemple.mattermost.traders.Pair;
import com.cloudtemple.mattermost.traders.post.Post;
import com.cloudtemple.mattermost.traders.post.PostList;
import com.cloudtemple.mattermost.traders.post.PostRequest;
import com.cloudtemple.mattermost.traders.post.Status;
import com.cloudtemple.mattermost.traders.user.ChannelUser;
import com.cloudtemple.mattermost.traders.user.User;
import com.cloudtemple.mattermost.traders.user.UserId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * { "id": "string", "create_at": 0, "update_at": 0, "delete_at": 0, "team_id": "string", "type": "string", "display_name": "string", "name": "string",
 * "header": "string", "purpose": "string", "last_post_at": 0, "total_msg_count": 0, "extra_update_at": 0, "creator_id": "string" }
 */
@SuppressWarnings("hiding")
@XmlRootElement
public class Channel extends MatterMostClientObject
{
	public String id;
	public long create_at;
	public long update_at;
	public long delete_at;
	public String team_id;
	public String type;
	public String display_name;
	public String name;
	public String header;
	public String purpose;
	public long last_post_at;
	public long total_msg_count;
	public long extra_update_at;
	public String creator_id;

	public ChannelId channelId()
	{
		return new ChannelId(id);
	}

	public String getId()
	{
		return id;
	}

	public void setId(final String id)
	{
		this.id = id;
	}

	public long getCreate_at()
	{
		return create_at;
	}

	public void setCreate_at(final long create_at)
	{
		this.create_at = create_at;
	}

	public long getUpdate_at()
	{
		return update_at;
	}

	public void setUpdate_at(final long update_at)
	{
		this.update_at = update_at;
	}

	public long getDelete_at()
	{
		return delete_at;
	}

	public void setDelete_at(final long delete_at)
	{
		this.delete_at = delete_at;
	}

	public String getTeam_id()
	{
		return team_id;
	}

	public void setTeam_id(final String team_id)
	{
		this.team_id = team_id;
	}

	public String getType()
	{
		return type;
	}

	public void setType(final String type)
	{
		this.type = type;
	}

	public String getDisplay_name()
	{
		return display_name;
	}

	public void setDisplay_name(final String display_name)
	{
		this.display_name = display_name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public String getHeader()
	{
		return header;
	}

	public void setHeader(final String header)
	{
		this.header = header;
	}

	public String getPurpose()
	{
		return purpose;
	}

	public void setPurpose(final String purpose)
	{
		this.purpose = purpose;
	}

	public long getLast_post_at()
	{
		return last_post_at;
	}

	public void setLast_post_at(final long last_post_at)
	{
		this.last_post_at = last_post_at;
	}

	public long getTotal_msg_count()
	{
		return total_msg_count;
	}

	public void setTotal_msg_count(final long total_msg_count)
	{
		this.total_msg_count = total_msg_count;
	}

	public long getExtra_update_at()
	{
		return extra_update_at;
	}

	public void setExtra_update_at(final long extra_update_at)
	{
		this.extra_update_at = extra_update_at;
	}

	public String getCreator_id()
	{
		return creator_id;
	}

	public void setCreator_id(final String creator_id)
	{
		this.creator_id = creator_id;
	}

	public Optional<Pair<Channel, UserId, UserId>> asDirect()
	{
		if ("D".equals(type))
		{
			final String[] splited = name.split("__");
			if (2 != splited.length)
				throw new ApiV4Exception("Unexpected name for a type 'D' channel. ");

			return Optional.of(new Pair<>(this, new UserId(splited[0]), new UserId(splited[1])));
		}
		return Optional.empty();
	}

	public Post sendPost(final String textMessage)
	{
		return getClient().post("/posts", new PostRequest(channelId(), textMessage, null, null), Post.class);
	}

	public PostList getPostsForChannel()
	{
		return getClient().get("/channels/" + id + "/posts", PostList.class);
	}

	public ChannelUser join(final User u)
	{
		final Map<String, String> body = new HashMap<>();
		body.put("user_id", u.getId());
		return getClient().post("/channels/" + getId() + "/members", body, ChannelUser.class);
	}

	public ChannelUser[] getUsersInChannel()
	{
		return getClient().get("/channels/" + getId() + "/members", ChannelUser[].class);
	}

	public Status kick(final User user)
	{
		return getClient().delete("/channels/" + getId() + "/members/" + user.getId(), Status.class);
	}

	public Channel changeHeader(final String header)
	{
		final Map<String, String> body = new HashMap<>();
		body.put("header", header);

		return getClient().put("/channels/" + getId() + "/patch", body, Channel.class);
	}

	public void addEventListener(final WsSocketListener listener)
	{
		getClient().addChannelListener(getId(), listener);
	}

	public void removeEventListener(final WsSocketListener listener)
	{
		getClient().removeChannelListener(getId(), listener);
	}
}
