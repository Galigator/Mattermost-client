package com.cloudtemple.mattermost.traders.post;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * { "order": [ "post_id1", "post_id12" ], "posts": { "property1": {}, "property2": {} } }
 */
@SuppressWarnings("hiding")
@XmlRootElement
public class PostList
{
	public String[] order;
	public Map<String, Post> posts = new HashMap<>();

	/**
	 * @return the postId in reversed temporal order
	 * @since 0.0.1
	 */
	public Stream<PostId> order()
	{
		return Stream.of(order).map(PostId::new);
	}

	/**
	 * @return the postId in reversed temporal order
	 * @since 0.0.1
	 */
	public String[] getOrder()
	{
		return order;
	}

	public void setOrder(final String[] order)
	{
		this.order = order;
	}

	public Map<String, Post> getPosts()
	{
		return posts;
	}

	public void setPosts(final Map<String, Post> posts)
	{
		this.posts = posts;
	}

}
