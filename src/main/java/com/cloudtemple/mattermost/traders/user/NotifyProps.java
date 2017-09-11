package com.cloudtemple.mattermost.traders.user;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("hiding")
@XmlRootElement
public class NotifyProps
{ // Nothing documented for this class :-(
	public String channel;
	public String desktop;
	public String desktop_sound;
	public String email;
	public String first_name;
	public String mention_keys;
	public String push;
	public String username;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(final String username)
	{
		this.username = username;
	}

	public String getChannel()
	{
		return channel;
	}

	public void setChannel(final String channel)
	{
		this.channel = channel;
	}

	public String getDesktop()
	{
		return desktop;
	}

	public void setDesktop(final String desktop)
	{
		this.desktop = desktop;
	}

	public String getDesktop_sound()
	{
		return desktop_sound;
	}

	public void setDesktop_sound(final String desktop_sound)
	{
		this.desktop_sound = desktop_sound;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getFirst_name()
	{
		return first_name;
	}

	public void setFirst_name(final String first_name)
	{
		this.first_name = first_name;
	}

	public String getMention_keys()
	{
		return mention_keys;
	}

	public void setMention_keys(final String mention_keys)
	{
		this.mention_keys = mention_keys;
	}

	public String getPush()
	{
		return push;
	}

	public void setPush(final String push)
	{
		this.push = push;
	}
}
