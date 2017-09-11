package com.cloudtemple.mattermost.traders;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("hiding")
@XmlRootElement
public class Error
{
	private String status_code;
	private String id;
	private String message;
	private String request_id;
	private String detailed_error;

	public String getRequest_id()
	{
		return request_id;
	}

	public void setRequest_id(final String request_id)
	{
		this.request_id = request_id;
	}

	public String getId()
	{
		return id;
	}

	public void setId(final String id)
	{
		this.id = id;
	}

	public String getStatus_code()
	{
		return status_code;
	}

	public void setStatus_code(final String status_code)
	{
		this.status_code = status_code;
	}

	public String getDetailed_error()
	{
		return detailed_error;
	}

	public void setDetailed_error(final String detailed_error)
	{
		this.detailed_error = detailed_error;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}
}
