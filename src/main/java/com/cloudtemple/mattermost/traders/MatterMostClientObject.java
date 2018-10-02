package com.cloudtemple.mattermost.traders;

import com.cloudtemple.mattermost.MatterMostBotClient;

public class MatterMostClientObject
{
  private MatterMostBotClient client;

  public MatterMostBotClient getClient()
  {
    return client;
  }

  public void setClient(MatterMostBotClient client)
  {
    this.client = client;
  }

}
