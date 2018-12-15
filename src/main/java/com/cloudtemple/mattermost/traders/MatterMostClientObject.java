package com.cloudtemple.mattermost.traders;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

import com.cloudtemple.mattermost.MatterMostBotClient;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

@SuppressWarnings("hiding")
@XmlRootElement
public class MatterMostClientObject
{
  private Map<String, Object> data = new HashMap<String, Object>();
  private MatterMostBotClient client;

  public MatterMostBotClient getClient()
  {
    return client;
  }

  public void setClient(MatterMostBotClient client)
  {
    this.client = client;
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
