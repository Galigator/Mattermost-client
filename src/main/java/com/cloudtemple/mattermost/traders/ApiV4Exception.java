package com.cloudtemple.mattermost.traders;

public class ApiV4Exception extends RuntimeException
{
	private static final long serialVersionUID = 5143139504702192634L;

	public Error _error;

	public Error getError()
	{
		return _error;
	}

	public void setError(final Error error)
	{
		_error = error;
	}

	public ApiV4Exception()
	{
		super();
	}

	public ApiV4Exception(final com.cloudtemple.mattermost.traders.Error error)
	{
		super(error.getStatus_code() + " " + ((error.getDetailed_error() != null && (!"".equals(error.getDetailed_error()))) ? error.getDetailed_error() : error.getMessage()));
		_error = error;
	}

	public ApiV4Exception(final String message)
	{
		super(message);
	}

	public ApiV4Exception(final Throwable cause)
	{
		super(cause);
	}

	public ApiV4Exception(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
