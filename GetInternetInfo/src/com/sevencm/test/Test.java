package com.sevencm.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sevencm.method.GetInfoMethod;
import com.sevencm.method.IGetInfoMethod;

public class Test {
	public String GetResponseDataByID(String url) {
		String content = null;
		try {
			URL dataUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) dataUrl
					.openConnection();
			// System.out.println(con.getResponseCode());
			// System.out.println(con.getContentLength());
			// byte d[] = new byte[4];
			con.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			char[] c = { 'u' };
			IGetInfoMethod getInfoMethod = new GetInfoMethod();
			System.out.println(getInfoMethod.getCharSetbyHeader(con));
			
			//
//			int size = con.getHeaderFields().size();
//			String str = null;
//			StringBuffer strBuffer = new StringBuffer();
//			for (int i = 0; i < size; i++) {
//				strBuffer.append(con.getHeaderFieldKey(i) + "\t");
//				strBuffer.append(con.getHeaderField(i));
//				strBuffer.append("\n");
//				if (con.getHeaderFieldKey(i) != null) {
//					if (con.getHeaderFieldKey(i).equals("Content-Type")) {
//						str = con.getHeaderField(i);
//						break;
//					}
//				}
//			}
//			System.out.println(str);
//			System.out.println(strBuffer.toString());
//			System.out.println(strBuffer.toString().indexOf("charset"));

			String line;
			InputStream is = con.getInputStream();
			StringBuffer stringBuffer = new StringBuffer();
			Reader reader = new InputStreamReader(is);
			BufferedReader bufferedReader = new BufferedReader(reader);
			// while ((line = bufferedReader.readLine())!=null){
			// int k = line.indexOf("charset");
			// stringBuffer.append(line+"\n");
			// if(k != -1){
			// c = line.substring(k+8, k+9).toCharArray();
			// System.out.println(c[0]);
			// break;
			// }
			// }
			// System.out.println(c[0]);
			// if(c[0] == 'u'){
			// reader = new InputStreamReader(is,"UTF-8");
			// } else {
			// reader = new InputStreamReader(is,"GBK");
			// }
			// bufferedReader = new BufferedReader(reader);
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line + "\n");
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			content = stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
//http://t.qq.com/
	public static void main(String[] args) {
		Test getInfor = new Test();
		System.out
				.println(getInfor
						.GetResponseDataByID("http://blog.csdn.net"));
	}
}
