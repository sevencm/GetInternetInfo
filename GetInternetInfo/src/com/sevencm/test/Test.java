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
//			char[] c = { 'u' };
			IGetInfoMethod getInfoMethod = new GetInfoMethod();
			String st = getInfoMethod.getCharSetbyHeader(con);
			if(st == null){
				st = getInfoMethod.getCharSetbyMeta(url);
			}
//			System.out.println(st);
			
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
			Reader reader = new InputStreamReader(is,st);
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
		String str = getInfor
				.GetResponseDataByID("http://search.51job.com/job/55100959,c.html");
		
		int start = str.indexOf("sr_bt");
		int end = str.indexOf("每页显示");
		
//		System.out.println(start);
//		System.out.println(end);
		
		String togetinfo = str.substring(start, end);
		togetinfo = togetinfo.replace("&nbsp;", "");
		togetinfo = togetinfo.replace("&gt;", "");
		
		
		int startName = togetinfo.indexOf(">");
		int endName = togetinfo.indexOf("<");
		String Name = togetinfo.substring(startName+1,endName);
		System.out.println(Name);
		int hangye = togetinfo.indexOf("公司行业");
		int ren = togetinfo.indexOf("人");
		String detail = togetinfo.substring(hangye,ren+1);
		detail = detail.replace("</strong>", "");
		detail = detail.replace("<strong>", "\n");
		detail = detail.replace("<br>", "");
		System.out.println(detail);
		
//		togetinfo = togetinfo.substring(togetinfo.indexOf("txt_font"),togetinfo.length());
//		
//		
//		int deatilmes = togetinfo.indexOf("txt_font");
//		int detailmes_ = togetinfo.indexOf("</div>");
//		String deam = togetinfo.substring(deatilmes,detailmes_);
//		
//		int deatil1 = deam.indexOf("br");
//		int deatil2 = deam.indexOf("/p");
//		String detailn = deam.substring(deatil1, deatil2);
//		System.out.println(detailn);
//		
//		int detail3 = deam.indexOf("txt_font1");
//		String oth = deam.substring(detail3, deam.length());
//		
//		System.out.println(oth);
		
		
//		syso
//		Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
//		Matcher matcher = pattern.matcher(str);
//		String string = matcher.replaceAll("");
//		System.out.println(string);
//		
//		System.out
//				.println(togetinfo);
	}
}
