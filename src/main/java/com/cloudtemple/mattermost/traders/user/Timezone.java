package com.cloudtemple.mattermost.traders.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("hiding")
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Timezone
{

	public String automaticTimezone;
	public String manualTimezone;
	public boolean useAutomaticTimezone;

	public String getManualTimezone()
	{
		return manualTimezone;
	}

	public void setManualTimezone(final String manualTimezone)
	{
		this.manualTimezone = manualTimezone;
	}

	public boolean isUseAutomaticTimezone()
	{
		return useAutomaticTimezone;
	}

	public void setUseAutomaticTimezone(final boolean useAutomaticTimezone)
	{
		this.useAutomaticTimezone = useAutomaticTimezone;
	}

	public String getAutomaticTimezone()
	{
		return automaticTimezone;
	}

	public void setAutomaticTimezone(final String automaticTimezone)
	{
		this.automaticTimezone = automaticTimezone;
	}
}
