package com.area51.utils;

public class ConstantsApp {

	public static final String TAG_APP = "TwitterApp";

	public static final String URL_ROOT_TWITTER_API_VERSION = "1.1/";
	public static final String URL_ROOT_TWITTER_API = "https://api.twitter.com/";

	public static final String URL_SEARCH = URL_ROOT_TWITTER_API
			+ URL_ROOT_TWITTER_API_VERSION + "search/tweets.json?q=";
	// https://api.twitter.com/1.1/search/tweets.json?q=desvelopers

	public static final String URL_USER = URL_ROOT_TWITTER_API
			+ URL_ROOT_TWITTER_API_VERSION
			+ "statuses/user_timeline.json?screen_name=";
	// https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=segundo_acosta

	public static final String URL_AUTHENTICATION = URL_ROOT_TWITTER_API
			+ "oauth2/token";
	public static final String CONSUMER_KEY = "ZSNCWTVGcmkj4YY1ca0p9FXUl";
	public static final String CONSUMER_SECRET = "07BqufcW6PX9rDheLa9oNw5UdFHCIIwIejVOCf1rZHhIHONC1t";

	// AMOUNT OF TWEETS
	public static final String TWITTER_COUNT = "10000";

	// FOR TIMELINEFRAGMENT OF USER
	public static final String TWITTER_USER = "segundo_acosta";

	// FOR HASHTAGFRAGMENT (TERM FOR SEARCH BY DEFAULT)
	public static final String TWITTER_TERM = "티아라";

	// FOR SEARCHFRAGMENT
	public static final String TWITTER_SEARCH = "";

}
