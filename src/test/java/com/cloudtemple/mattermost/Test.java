package com.cloudtemple.mattermost;

import com.cloudtemple.mattermost.traders.Pair;
import com.cloudtemple.mattermost.traders.channel.Channel;
import com.cloudtemple.mattermost.traders.channel.ChannelId;
import com.cloudtemple.mattermost.traders.post.Post;
import com.cloudtemple.mattermost.traders.post.PostId;
import com.cloudtemple.mattermost.traders.post.PostList;
import com.cloudtemple.mattermost.traders.team.Team;
import com.cloudtemple.mattermost.traders.team.TeamId;
import com.cloudtemple.mattermost.traders.user.User;
import com.cloudtemple.mattermost.traders.user.UserId;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Connection test to an Docker Mattermost container.
 * 
 * @since 0.5.0
 */
public class Test
{
	public static final Logger _logger = Logger.getLogger(Test.class.getName());
	public static final String personalAccessToken = "omm5rqtcb78h7qp3h49dr3mruc"; // You need to activate the 'Personal Access Token' and create one.
	public static final String host = "localhost:8065";

	public static final Handler _systemOutHandler = new Handler()
	{
		@Override
		public void publish(final LogRecord record)
		{
			final StringBuffer buff = new StringBuffer();
			buff.append(record.getLevel()).append(' ');
			buff.append(record.getThreadID()).append(' ');
			buff.append(record.getLoggerName()).append(' ');
			buff.append(record.getMessage()).append(' ');
			System.out.println(buff.toString());
		}

		@Override
		public void flush()
		{
			//
		}

		@Override
		public void close() throws SecurityException
		{
			//
		}
	};

	static
	{
		final String property = System.getProperty("java.util.logging.SimpleFormatter.format");
		if (null == property)
			System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
		_logger.setLevel(Level.FINE);
		_logger.addHandler(_systemOutHandler);
	}

	public static void test(final MatterMostBotClient mm)
	{
		final User user = mm.getUsersMe();
		final UserId userId = user.userId();
		System.out.println("userId : " + userId + "\t userMail : " + user.getEmail() + "\tpropsChannel : " + user.getNotify_props().getChannel());

		final Team team = mm.getTeam("cloud-temple");
		final TeamId teamId = team.teamId();
		System.out.println("teamId : " + teamId + "\tname : " + team.getName());

		final Channel channel = mm.getTeamChannelByName(teamId, "town-square");
		final ChannelId channelId = channel.channelId();
		System.out.println("channelId : " + channelId + "\tchannelName : " + channel.getName());

		final PostList x = mm.getPostsForChannel(channelId);
		for (final String pid : x.getOrder())
		{
			final Post post = x.getPosts().get(pid);
			System.out.println("PostId:" + pid + "\tpost:" + post.getChannel_id() + "\t" + post.getMessage());
		}

		System.out.println(mm.getPost(new PostId(x.getOrder()[0])).getMessage());

		//		final PostResponse post = mm.sendPost(channelId, "Coincoin !");
		//		System.out.println(post.id + " " + post.message + " " + post.type);
	}

	public static void test2(final MatterMostBotClient mm)
	{
		mm.connect();
		mm.await(60); // You have 1 minute to enter some commands.
		mm.disconnect();
	}

	public static void test3(final MatterMostBotClient mm)
	{
		mm.connect();
		final User me = mm.getUsersMe();
		final UserId myId = me.userId();

		final Team team = mm.getTeam("cloud-temple");
		final TeamId teamId = team.teamId();
		System.out.println("teamId : " + teamId + "\tname : " + team.getName());

		final Channel[] channels = mm.getChannelsForUser(myId, teamId);

		for (final Channel channel : channels)
			System.out.println(channel.id + " " + channel.type + " " + channel.name);

		final Stream<Pair<Object, Channel, UserId>> directStream = Stream.of(channels)//
				.map(Channel::asDirect)//
				.filter(Optional::isPresent)//
				.map(Optional::get)//
				.map(p -> new Pair<>(p.getContext(), p.getA().equals(myId) ? p.getB() : p.getA())); // get only the 'not me' part.

		directStream.forEach(p ->
		{
			final Channel channel = p.getA();
			final UserId target = p.getB();
			mm.sendPost(channel.channelId(), "Hey man, I know your ID. It is " + target.getId() + " . Here a random number for you : " + (new Random()).nextInt());
		});

		mm.await(60); // You have 1 minute to enter some commands.
		mm.disconnect();
	}

	public static void test4(final MatterMostBotClient mm)
	{
		Stream.of(mm.getUsers()).forEach(user ->
		{
			System.out.println("-------- USER ----------");
			System.out.println("id:" + user.id);
			System.out.println("email:" + user.email);
			System.out.println("username:" + user.username);
			System.out.println("first name:" + user.first_name);
			System.out.println("last name:" + user.last_name);
			System.out.println("roles:" + user.roles);
			System.out.println("position:" + user.position);
			System.out.println("nickname:" + user.nickname);
		});
	}

	public static void main(final String[] args)
	{
		final MatterMostBotClient mm = new MatterMostBotClient(host, personalAccessToken, message -> //
		{
			System.out.println("========== " + message.event + " =============");
			message.data.forEach((k, v) -> System.out.println(k + "\t" + v));
			message.getPost().ifPresent(post -> System.out.println("Message : " + post.getMessage()));
		});
		test4(mm);
	}
}
