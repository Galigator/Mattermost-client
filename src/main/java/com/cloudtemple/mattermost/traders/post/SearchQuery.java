package com.cloudtemple.mattermost.traders.post;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * { "terms": "string", "is_or_search": true }
 */
@SuppressWarnings("hiding")
@XmlRootElement
public class SearchQuery
{
	public String terms;
	public boolean is_or_search = true;

	public String getTerms()
	{
		return terms;
	}

	public void setTerms(final String terms)
	{
		this.terms = terms;
	}

	public boolean isIs_or_search()
	{
		return is_or_search;
	}

	public void setIs_or_search(final boolean is_or_search)
	{
		this.is_or_search = is_or_search;
	}

	public SearchQuery()
	{
		// If you delete, I will not exist any more ;-(
	}

	public SearchQuery(final String terms, final boolean is_or_search)
	{
		this.terms = terms;
		this.is_or_search = is_or_search;
	}
}
