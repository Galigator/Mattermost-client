package com.cloudtemple.mattermost.traders.team;

import com.cloudtemple.mattermost.traders.MatterMostClientObject;
import com.cloudtemple.mattermost.traders.channel.Channel;
import com.cloudtemple.mattermost.traders.post.PostList;
import com.cloudtemple.mattermost.traders.post.SearchQuery;
import com.cloudtemple.mattermost.traders.user.UserId;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * [ { "id": "string", "create_at": 0, "update_at": 0, "delete_at": 0, "display_name": "string", "name": "string", "description": "string", "email": "string",
 * "type": "string", "allowed_domains": "string", "invite_id": "string", "allow_open_invite": true } ]
 */
@SuppressWarnings("hiding")
@XmlRootElement
public class Team extends MatterMostClientObject
{
	public String id;
	public long create_at;
	public long update_at;
	public long delete_at;
	public long last_team_icon_update;
	public String company_name; // <-- not in documentation !
	public String display_name;
	public String name;
	public String description;
	public String email;
	public String type;
	public String allowed_domains;
	public String invite_id; // What is this ?
	public boolean allow_open_invite;
	public String scheme_id;

	public TeamId teamId()
	{
		return new TeamId(id);
	}

	public String getCompany_name()
	{
		return company_name;
	}

	public void setCompany_name(final String company_name)
	{
		this.company_name = company_name;
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

	public long getLast_team_icon_update()
	{
		return last_team_icon_update;
	}

	public void setLast_team_icon_update(final long last_team_icon_update)
	{
		this.last_team_icon_update = last_team_icon_update;
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getType()
	{
		return type;
	}

	public void setType(final String type)
	{
		this.type = type;
	}

	public String getAllowed_domains()
	{
		return allowed_domains;
	}

	public void setAllowed_domains(final String allowed_domains)
	{
		this.allowed_domains = allowed_domains;
	}

	public String getInvite_id()
	{
		return invite_id;
	}

	public void setInvite_id(final String invite_id)
	{
		this.invite_id = invite_id;
	}

	public boolean isAllow_open_invite()
	{
		return allow_open_invite;
	}

	public void setAllow_open_invite(final boolean allow_open_invite)
	{
		this.allow_open_invite = allow_open_invite;
	}

	public String getScheme_id()
	{
		return scheme_id;
	}

	public void setScheme_id(final String scheme_id)
	{
		this.scheme_id = scheme_id;
	}

	public Channel getChannelByName(final String channelName)
	{
		return getClient().get("/teams/" + id + "/channels/name/" + channelName, Channel.class);
	}

	public PostList searchTeamPost(final SearchQuery query)
	{
		return getClient().post("/team/" + id + "/posts/search", query, PostList.class);
	}

	public PostList searchTeamPost(final String terms, final boolean is_or_search)
	{
		return searchTeamPost(new SearchQuery(terms, is_or_search));
	}

	public Channel createPublicChannel(final String channel_name, final String display_name)
	{
		final Map<String, String> body = new HashMap<>();
		body.put("team_id", getId());
		body.put("name", channel_name);
		body.put("display_name", display_name);
		body.put("type", "O");
		return getClient().post("/channels", body, Channel.class);
	}

	public TeamMember getTeamMember(final UserId userId)
	{
		return getClient().get("/teams/" + getId() + "/members/" + userId, TeamMember.class);
	}

	public TeamMember addTeamMember(final UserId userId, final boolean admin)
	{
		final Map<String, String> body = new HashMap<>();
		body.put("team_id", getId());
		body.put("user_id", userId.toString());
		body.put("roles", admin ? "system_admin" : "system_user");
		return getClient().post("/teams/" + getId() + "/members", body, TeamMember.class);
	}
}
