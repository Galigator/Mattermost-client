package com.cloudtemple.mattermost.traders.user;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("hiding")
@XmlRootElement
public class User
{
	public String id;
	public long create_at;
	public long update_at;
	public long delete_at;
	public String username;
	public String auth_data;
	public String auth_service;
	public String email;
	public String nickname;
	public String first_name;
	public String last_name;
	public String position;
	public String roles;
	public boolean allow_marketing;
        public boolean email_verified;
	public NotifyProps notify_props;
	public long last_password_update;
	public String locale;
	public Timezone timezone;

	public UserId userId()
	{
		return new UserId(id);
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

	public String getUsername()
	{
		return username;
	}

	public void setUsername(final String username)
	{
		this.username = username;
	}

	public String getAuth_data()
	{
		return auth_data;
	}

	public void setAuth_data(final String auth_data)
	{
		this.auth_data = auth_data;
	}

	public String getAuth_service()
	{
		return auth_service;
	}

	public void setAuth_service(final String auth_service)
	{
		this.auth_service = auth_service;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(final String nickname)
	{
		this.nickname = nickname;
	}

	public String getFirst_name()
	{
		return first_name;
	}

	public void setFirst_name(final String first_name)
	{
		this.first_name = first_name;
	}

	public String getLast_name()
	{
		return last_name;
	}

	public void setLast_name(final String last_name)
	{
		this.last_name = last_name;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(final String position)
	{
		this.position = position;
	}

	public String getRoles()
	{
		return roles;
	}

	public void setRoles(final String roles)
	{
		this.roles = roles;
	}

	public boolean isAllow_marketing()
	{
		return allow_marketing;
	}

	public void setAllow_marketing(final boolean allow_marketing)
	{
		this.allow_marketing = allow_marketing;
	}

        public boolean isEmail_verified()
        {
                return email_verified;
        }

        public void setEmail_verified(final boolean email_verified)
        {
                this.email_verified = email_verified;
        }

	public NotifyProps getNotify_props()
	{
		return notify_props;
	}

	public void setNotify_props(final NotifyProps notify_props)
	{
		this.notify_props = notify_props;
	}

	public long getLast_password_update()
	{
		return last_password_update;
	}

	public void setLast_password_update(final long last_password_update)
	{
		this.last_password_update = last_password_update;
	}

	public String getLocale()
	{
		return locale;
	}

	public void setLocale(final String locale)
	{
		this.locale = locale;
	}

	public Timezone getTimezone() {
		return timezone;
	}

	public void setTimezone(Timezone timezone) {
		this.timezone = timezone;
	}
}
