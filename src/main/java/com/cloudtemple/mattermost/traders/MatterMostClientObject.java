package com.cloudtemple.mattermost.traders;

import com.cloudtemple.mattermost.MatterMostBotClient;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("hiding")
@XmlRootElement
public class MatterMostClientObject
{
	private final Map<String, Object> data = new ConcurrentHashMap<>();
	private MatterMostBotClient client;

	public MatterMostBotClient getClient()
	{
		return client;
	}

	public void setClient(final MatterMostBotClient client)
	{
		this.client = client;
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
