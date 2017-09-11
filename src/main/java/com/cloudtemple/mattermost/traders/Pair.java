package com.cloudtemple.mattermost.traders;

import java.util.Optional;

public class Pair<CONTEXT, A, B>
{
	private final Optional<CONTEXT> _context;
	private final A _a;
	private final B _b;

	public CONTEXT getContext()
	{
		return _context.orElse(null);
	}

	public A getA()
	{
		return _a;
	}

	public B getB()
	{
		return _b;
	}

	public Pair(final CONTEXT context, final A a, final B b)
	{
		_context = Optional.ofNullable(context);
		_a = a;
		_b = b;
	}

	public Pair(final A a, final B b)
	{
		_context = Optional.empty();
		_a = a;
		_b = b;
	}

	@Override
	public String toString()
	{
		return _context.map(x -> x.toString()).orElse("") + ":{a:" + _a + ", b:" + _b + "}";
	}
}
