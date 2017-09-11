package com.cloudtemple.mattermost.traders.post;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * { "status": "string" }
 */
@SuppressWarnings("hiding")
@XmlRootElement
public class Status
{
	public String status;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(final String status)
	{
		this.status = status;
	}
}
