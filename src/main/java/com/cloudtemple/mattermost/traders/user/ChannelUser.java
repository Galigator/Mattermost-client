package com.cloudtemple.mattermost.traders.user;

/**
 * Represents a User within the context of a Channel.
 */
public class ChannelUser extends User
{
  public String channelId;
  public String userId;
  public long msgCount;
  public long mentionCount;
  public long lastUpdateAt;
  public long lastViewedAt;

  public String getChannelId()
  {
    return channelId;
  }
  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  public String getUserId()
  {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }

  public long getMsgCount()
  {
    return msgCount;
  }
  public void setMsgCount(long msgCount) {
    this.msgCount = msgCount;
  }

  public long getMentionCount()
  {
    return mentionCount;
  }
  public void setMentionCount(long mentionCount) {
    this.mentionCount = mentionCount;
  }

  public long getLastUpdateAt()
  {
    return lastUpdateAt;
  }
  public void setLastUpdateAt(long lastUpdateAt) {
    this.lastUpdateAt = lastUpdateAt;
  }

  public long getLastViewedAt()
  {
    return lastViewedAt;
  }
  public void setLastViewedAt(long lastViewedAt) {
    this.lastViewedAt = lastViewedAt;
  }
}

