package com.cloudtemple.mattermost.traders.post;

import com.cloudtemple.mattermost.traders.channel.ChannelId;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * { "channel_id": "string", "message": "string", "root_id": "string", "file_ids": [ "string" ] }
 */
@SuppressWarnings("hiding")
@XmlRootElement
public class PostRequest
{
	private String channel_id;
	private String message;
	private String root_id;
	private List<String> file_ids;

	public ChannelId channelId()
	{
		return new ChannelId(channel_id);
	}

	public PostId rootPostId()
	{
		return new PostId(root_id);
	}

	public String getChannel_id()
	{
		return channel_id;
	}

	public void setChannel_id(final String channel_id)
	{
		this.channel_id = channel_id;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public String getRoot_id()
	{
		return root_id;
	}

	public void setRoot_id(final String root_id)
	{
		this.root_id = root_id;
	}

	public List<String> getFile_ids()
	{
		return file_ids;
	}

	public void setFile_ids(final List<String> file_ids)
	{
		this.file_ids = file_ids;
	}

	public PostRequest()
	{
		// Plz plz don't delete me ;-(
	}

	public PostRequest(final ChannelId channel_id, final String message, final String root_id, final List<String> file_ids)
	{
		this.channel_id = channel_id.getId();
		this.message = message;
		this.root_id = root_id;
		this.file_ids = file_ids;
	}

}
