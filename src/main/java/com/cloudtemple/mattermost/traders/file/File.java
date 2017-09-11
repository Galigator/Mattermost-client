package com.cloudtemple.mattermost.traders.file;

import com.cloudtemple.mattermost.traders.post.PostId;
import com.cloudtemple.mattermost.traders.user.UserId;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * { "id": "string", "user_id": "string", "post_id": "string", "create_at": 0, "update_at": 0, "delete_at": 0, "name": "string", "extension": "string", "size":
 * 0, "mime_type": "string", "width": 0, "height": 0, "has_preview_image": true }
 */
@SuppressWarnings("hiding")
@XmlRootElement
public class File
{
	public String id;
	public String user_id;
	public String post_id;
	public long create_at;
	public long update_at;
	public long delete_at;
	public String name;
	public String extension;
	public long size;
	public String mime_type;
	public long width;
	public long height;
	public boolean has_preview_image;

	public FileId fileId()
	{
		return new FileId(id);
	}

	public UserId userId()
	{
		return new UserId(user_id);
	}

	public PostId postId()
	{
		return new PostId(post_id);
	}

	public String getId()
	{
		return id;
	}

	public void setId(final String id)
	{
		this.id = id;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(final String user_id)
	{
		this.user_id = user_id;
	}

	public String getPost_id()
	{
		return post_id;
	}

	public void setPost_id(final String post_id)
	{
		this.post_id = post_id;
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

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public String getExtension()
	{
		return extension;
	}

	public void setExtension(final String extension)
	{
		this.extension = extension;
	}

	public long getSize()
	{
		return size;
	}

	public void setSize(final long size)
	{
		this.size = size;
	}

	public String getMime_type()
	{
		return mime_type;
	}

	public void setMime_type(final String mime_type)
	{
		this.mime_type = mime_type;
	}

	public long getWidth()
	{
		return width;
	}

	public void setWidth(final long width)
	{
		this.width = width;
	}

	public long getHeight()
	{
		return height;
	}

	public void setHeight(final long height)
	{
		this.height = height;
	}

	public boolean isHas_preview_image()
	{
		return has_preview_image;
	}

	public void setHas_preview_image(final boolean has_preview_image)
	{
		this.has_preview_image = has_preview_image;
	}

}
