package com.cloudtemple.mattermost.traders.channel;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("hiding")
@XmlRootElement
public class Props
{
	//	"delete_at", "display_name", "update_at", "name", "header", "creator_id", "scheme_id", "create_at", "type", "id", "team_id", "purpose", "last_post_at", "total_msg_count", "extra_update_at"

	public long delete_at;

	public String display_name;
	public long update_at;
	public String name;
	public String header;
	public String creator_id;
	public String scheme_id;
	public long create_at;
	public String type;
	public String id;
	public String team_id;
	public String purpose;
	public long last_post_at;
	public long total_msg_count;
	public long extra_update_at;

	public long getDelete_at()
	{
		return delete_at;
	}

	public void setDelete_at(final long delete_at)
	{
		this.delete_at = delete_at;
	}

	public String getDisplay_name()
	{
		return display_name;
	}

	public void setDisplay_name(final String display_name)
	{
		this.display_name = display_name;
	}

	public long getUpdate_at()
	{
		return update_at;
	}

	public void setUpdate_at(final long update_at)
	{
		this.update_at = update_at;
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

	public String getCreator_id()
	{
		return creator_id;
	}

	public void setCreator_id(final String creator_id)
	{
		this.creator_id = creator_id;
	}

	public String getScheme_id()
	{
		return scheme_id;
	}

	public void setScheme_id(final String scheme_id)
	{
		this.scheme_id = scheme_id;
	}

	public long getCreate_at()
	{
		return create_at;
	}

	public void setCreate_at(final long create_at)
	{
		this.create_at = create_at;
	}

	public String getType()
	{
		return type;
	}

	public void setType(final String type)
	{
		this.type = type;
	}

	public String getId()
	{
		return id;
	}

	public void setId(final String id)
	{
		this.id = id;
	}

	public String getTeam_id()
	{
		return team_id;
	}

	public void setTeam_id(final String team_id)
	{
		this.team_id = team_id;
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

}
