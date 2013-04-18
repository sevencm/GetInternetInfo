package com.sevencm.method;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

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

//		System.out
//				.println("****************************************************");
//		System.out.println(strBuffer.toString());
//		System.out
//				.println("****************************************************");

		if (str == null) {
			return str;
		} else if (str.indexOf("charset") == -1) {
			return null;
		} else {
			return str = str
					.substring(str.indexOf("charset") + 8, str.length());
		}
	}

	@Override
	public String getCharSetbyMeta(String url) {
		String strCharset = null;
		
		try {
			URL dataUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) dataUrl
					.openConnection();
			con.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			InputStream is = con.getInputStream();
			Reader reader = new InputStreamReader(is);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = null;
			
			while ((line = bufferedReader.readLine()) != null) {
						
				line = line.replace('"', ' ');
				line = line.replace('/', ' ');
				line = line.replace('>', ' ');
				line = line.replace('\'', ' ');
				line = line.trim();
				int intCharSetStart = line.indexOf("charset=");		
				if (intCharSetStart != -1) {
					strCharset = line.substring(intCharSetStart + 8, line.length()).trim();			 //indexOf("\"/>")	
					break;
				}
			}
			if(bufferedReader!=null){
				bufferedReader.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return strCharset;
	}
	
	
	

}
