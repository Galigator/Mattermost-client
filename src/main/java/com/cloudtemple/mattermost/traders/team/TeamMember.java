package com.cloudtemple.mattermost.traders.team;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class TeamMember
{
  private String teamId;
  private String userId;
  private String roles;
  private long deleteAt;
  private Map<String, Object> data = new HashMap<String, Object>();

  public String getTeamId()
  {
    return teamId;
  }
  public void setTeamId(String teamId)
  {
    this.teamId = teamId;
  }

  public String getUserId()
  {
    return userId;
  }
  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public String getRoles()
  {
    return roles;
  }
  public void setRoles(String roles)
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
  public Map<String, Object> getData() {
    return data;
  }

  @JsonAnySetter
  public void setData(String name, Object value) {
    this.data.put(name, value);
  }

}
