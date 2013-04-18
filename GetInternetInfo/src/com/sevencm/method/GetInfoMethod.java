package com.sevencm.method;

import java.net.HttpURLConnection;

public class GetInfoMethod implements IGetInfoMethod {

	@Override
	public String getCharSetbyHeader(HttpURLConnection conn) {
		int size = conn.getHeaderFields().size();
		StringBuffer strBuffer = new StringBuffer();
		String str = null; // 获取Content-Type 的值 最后返回字符集
		for (int i = 0; i < size; i++) {
			strBuffer.append(conn.getHeaderFieldKey(i) + "\t");
			strBuffer.append(conn.getHeaderField(i));
			strBuffer.append("\n");
			if (conn.getHeaderFieldKey(i) != null) {
				if (conn.getHeaderFieldKey(i).equals("Content-Type")) {
					str = conn.getHeaderField(i);
					break;
				}
			}
		}

		System.out
				.println("****************************************************");
		System.out.println(strBuffer.toString());
		System.out
				.println("****************************************************");

		if (str == null) {
			return str;
		} else if (str.indexOf("charset") == -1) {
			return null;
		} else {
			return str = str
					.substring(str.indexOf("charset") + 8, str.length());
		}
	}

}
