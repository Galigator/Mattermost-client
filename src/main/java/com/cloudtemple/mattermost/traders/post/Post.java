package com.cloudtemple.mattermost.traders.post;

import com.cloudtemple.mattermost.traders.ApiV4Exception;
import com.cloudtemple.mattermost.traders.channel.ChannelId;
import com.cloudtemple.mattermost.traders.user.NotifyProps;
import com.cloudtemple.mattermost.traders.user.UserId;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * { "id": "string", "create_at": 0, "update_at": 0, "delete_at": 0, "user_id": "string", "channel_id": "string", "root_id": "string", "parent_id": "string",
 * "original_id": "string", "message": "string", "type": "string", "props": { }, "hashtag": "string", "filenames": [ "string" ], "file_ids": [ "string" ],
 * "pending_post_id": "string" }
 */
@SuppressWarnings("hiding")
@XmlRootElement
public class Post
{
	public String id;
	public long create_at;
	public long update_at;
	public long delete_at;
	public long edit_at; // <- not in doc
	public String user_id;
	public String channel_id;
	public String root_id;
	public String parent_id;
	public String original_id;
	public String message;
	public String type;
	public NotifyProps props;
	public List<String> filenames;
	public List<String> file_ids;
	public boolean is_pinned; // <- not in doc
	public boolean has_reactions;
	public String hashtags; // <- not in doc
	public String pending_post_id;

	public Post()
	{
		// I am useful ! don't delete me.
	}

	public Post(final Map<String, Object> map)
	{
		if (null != map)
			map.forEach((k, v) ->
			{
				try
				{
					Post.class.getField(k).set(Post.this, v);
				}
				catch (final Exception exception)
				{
					throw new ApiV4Exception(exception);
				}
			});
	}

	public PostId postId()
	{
		return new PostId(id);
	}

	public PostId pendingPostId()
	{
		return new PostId(pending_post_id);
	}

	public ChannelId channelId()
	{
		return new ChannelId(channel_id);
	}

	public PostId rootPostId()
	{
		return new PostId(root_id);
	}

	public PostId parentPostId()
	{
		return new PostId(parent_id);
	}

	public PostId originalPostId()
	{
		return new PostId(original_id);
	}

	public UserId userId()
	{
		return new UserId(user_id);
	}

	public String getPending_post_id()
	{
		return pending_post_id;
	}

	public void setPending_post_id(final String pending_post_id)
	{
		this.pending_post_id = pending_post_id;
	}

	public String getHashtags()
	{
		return hashtags;
	}

	public void setHashtags(final String hashtags)
	{
		this.hashtags = hashtags;
	}

	public boolean isIs_pinned()
	{
		return is_pinned;
	}

	public void setIs_pinned(final boolean is_pinned)
	{
		this.is_pinned = is_pinned;
	}

	public boolean isHas_reactions()
	{
		return has_reactions;
	}

	public void setHas_Reactions(final boolean has_reactions)
	{
		this.has_reactions = has_reactions;
	}

	public long getEdit_at()
	{
		return edit_at;
	}

	public void setEdit_at(final long edit_at)
	{
		this.edit_at = edit_at;
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

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(final String user_id)
	{
		this.user_id = user_id;
	}

	public String getChannel_id()
	{
		return channel_id;
	}

	public void setChannel_id(final String channel_id)
	{
		this.channel_id = channel_id;
	}

	public String getRoot_id()
	{
		return root_id;
	}

	public void setRoot_id(final String root_id)
	{
		this.root_id = root_id;
	}

	public String getParent_id()
	{
		return parent_id;
	}

	public void setParent_id(final String parent_id)
	{
		this.parent_id = parent_id;
	}

	public String getOriginal_id()
	{
		return original_id;
	}

	public void setOriginal_id(final String original_id)
	{
		this.original_id = original_id;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public String getType()
	{
		return type;
	}

	public void setType(final String type)
	{
		this.type = type;
	}

	public NotifyProps getProps()
	{
		return props;
	}

	public void setProps(final NotifyProps props)
	{
		this.props = props;
	}

	public List<String> getFilenames()
	{
		return filenames;
	}

	public void setFilenames(final List<String> filenames)
	{
		this.filenames = filenames;
	}

	public List<String> getFile_ids()
	{
		return file_ids;
	}

	public void setFile_ids(final List<String> file_ids)
	{
		this.file_ids = file_ids;
	}

}
