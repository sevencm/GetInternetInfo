package com.sevencm.method;

import java.net.HttpURLConnection;
import java.util.Set;

public interface IGetInfoMethod {
	public String getCharSetbyHeader(HttpURLConnection conn);
	public String getCharSetbyMeta(String url);
	public Set<String> getURL(String htmlString);
}
