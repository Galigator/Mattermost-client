package com.cloudtemple.mattermost;

import com.cloudtemple.mattermost.traders.Event;
import com.cloudtemple.mattermost.traders.channel.ChannelId;
import com.cloudtemple.mattermost.traders.post.Post;
import com.cloudtemple.mattermost.traders.post.PostRequest;
import com.cloudtemple.mattermost.traders.user.User;
import com.cloudtemple.mattermost.traders.user.UserId;

import java.util.function.Predicate;

public class MyFirstBot {

    public static final String personalAccessToken = "5wfggktfdirpiphejxjcxjboco"; // You need to activate the 'Personal Access Token' and create one.
    public static final String host = "localhost:8065";
    public static final String teamId = "jeciqwjze3b8jm3b3d4khneehy";

    public static void main(String[] args) {
        MatterMostBotClient syncClient = new MatterMostBotClient(host, personalAccessToken);
        final User user = syncClient.getUsersMe();
        final UserId userId = user.userId();

        Predicate<Event> isPosted = event -> "posted".equals(event.event);
        Predicate<Event> hasMentions = event -> event.getData().containsKey("mentions");
        Predicate<Event> isMentionForMe = event -> {
            String mentions = (String) event.getData().get("mentions");
            boolean result = mentions.contains(userId.getId());
            return result;
        };
        Predicate<Event> isExplicitMentionForMe = event -> {
            String message = event.getPost().get().getMessage();
            String mentionByName = "@" + user.username;
            boolean result = message.startsWith(mentionByName);
            return result;
        };
        Predicate<Event> onExpectedTeam = event -> {
            Object currentTeamId = event.getData().get("team_id");
            boolean result = teamId.equals(currentTeamId);
            if (!result) {
                System.out.println("Unexpected teamId: " + currentTeamId);
            }
            return result;
        };

        Predicate<Event> compliant = isPosted
                .and(hasMentions)
                .and(isMentionForMe)
                .and(isExplicitMentionForMe)
                .and(onExpectedTeam);

        MatterMostBotClient watchClient = new MatterMostBotClient(host, personalAccessToken, event -> {
            if (compliant.test(event)) {
                Post post = event.getPost().get();
                System.out.println("ALERTE ! On me pause une question !!!! " + post.getMessage());
                ChannelId channelId = new ChannelId(post.getChannel_id());
                syncClient.sendPost(channelId, "Oh toi ta gueule !");
//                syncClient.sendPost(new PostRequest(channelId, "Oh toi ta gueule !", post.getId(), null));
            } else if (isPosted.test(event)) {
                Post post = event.getPost().get();
                System.out.println("Message mais pas pour moi: " + post.getMessage());
            }
        });
        watchClient.connect();
        watchClient.await(600);
        watchClient.disconnect();

    }
}
