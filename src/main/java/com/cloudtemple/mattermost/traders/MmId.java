package com.cloudtemple.mattermost.traders;

public abstract class MmId
{
	protected final String _id;

	public String getId()
	{
		return _id;
	}

	public MmId(final String id)
	{
		_id = id;
	}

	@Override
	public String toString()
	{
		return _id;
	}

	public boolean equals(final MmId that)
	{
		return _id.equals(that._id);
	}
}
