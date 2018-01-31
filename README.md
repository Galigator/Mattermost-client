# Mattermost-client
A simple client for the api V4 of Mattermost compatible with Mattermost 4.1
The client aim at writing Bot for Mattermost.

### Pre-requist
 - Mattermost 4.1 with {*websockets*, *personal-access-token*} enables
 - Java 8.131+
 - Maven 3.3.9+


### The connexion is done with :
```java
	final MatterMostBotClient mm = new MatterMostBotClient(host, personalAccessToken, message -> //
	{
		System.out.println("========== " + message.event + " =============");
		message.data.forEach((k, v) -> System.out.println(k + "\t" + v));
		message.getPost().ifPresent(post -> System.out.println("Message : " + post.getMessage()));
	});
	mm.connect();
```

The enable you to send command using the MatterMostBotClient object, and process incoming messages send via the websocket protocol.
You need to **connect()** to receive message from Mattermost.

### Simple listing of users.
```java
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
```

### Simple Bot that answer to an user.
```java
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
```

### Play around with API then send an 'Coincoin' to 'town-square'
```java
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

	final PostResponse post = mm.sendPost(channelId, "Coincoin !");
	System.out.println(post.id + " " + post.message + " " + post.type);
```

