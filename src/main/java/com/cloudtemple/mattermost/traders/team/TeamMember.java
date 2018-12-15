package com.cloudtemple.mattermost.traders.team;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("hiding")
@XmlRootElement
public class TeamMember
{
	private String teamId;
	private String userId;
	private String roles;
	private long deleteAt;
	private final Map<String, Object> data = new ConcurrentHashMap<>();

	public String getTeamId()
	{
		return teamId;
	}

	public void setTeamId(final String teamId)
	{
		this.teamId = teamId;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(final String userId)
	{
		this.userId = userId;
	}

	public String getRoles()
	{
		return roles;
	}

	public void setRoles(final String roles)
	{
		this.roles = roles;
	}

	public long getDeleteAt()
	{
		return deleteAt;
	}

	public void setDeleteAt(final long deleteAt)
	{
		this.deleteAt = deleteAt;
	}

	@JsonAnyGetter
	public Map<String, Object> getData()
	{
		return data;
	}

	@JsonAnySetter
	public void setData(final String name, final Object value)
	{
		data.put(name, value);
	}

}
