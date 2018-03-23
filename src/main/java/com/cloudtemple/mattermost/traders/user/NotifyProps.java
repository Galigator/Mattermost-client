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
        public String from_webhook;
        public String webhook_display_name;
        public String addedUsername;
        public String comments;
        public long desktop_duration;
        public String push_status;
        public String user_id;

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

        public String getFrom_webhook()
        {
                return from_webhook;
        }

        public void setFrom_webhook(final String from_webhook)
        {
                this.from_webhook = from_webhook;
        }

        public String getWebhook_display_name()
        {
                return webhook_display_name;
        }

        public void setWebhook_display_name(final String webhook_display_name)
        {
                this.webhook_display_name = webhook_display_name;
        }

        public String getAddedUsername()
        {
                return addedUsername;
        }

        public void setAddedUsername(final String addedUsername)
        {
                this.addedUsername = addedUsername;
        }

        public String getComments()
        {
                return comments;
        }

        public void setComments(final String comments)
        {
                this.comments = comments;
        }

        public long getDesktop_duration()
        {
                return desktop_duration;
        }

        public void setDesktop_duration(final long desktop_duration)
        {
                this.desktop_duration = desktop_duration;
        }

        public String getPush_status()
        {
                return push_status;
        }

        public void setPush_status(final String push_status)
        {
                this.push_status = push_status;
        }

        public String getUser_id()
        {
                return user_id;
        }

        public void setUser_id(final String user_id)
        {
                this.user_id = user_id;
        }

}
