package com.sevencm.method;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

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

	@Override
	public Set<String> getURL(String htmlString) {
		// TODO Auto-generated method stub
		Set<String> urlSet = new HashSet<String>();
		String str = htmlString;
		String getUrl = str.substring(str.indexOf("tabhead"), str.indexOf("next-page")); //获取job的连接
		int start = getUrl.indexOf("http://jobs.zhaopin.com/");
		int end = getUrl.indexOf(".htm");
		
//		System.out.println(getUrl);
		String url = null;
		url = getUrl.substring(start, end+4);
		urlSet.add(url);
		int i = 0;
		while (start != -1 && end != -1) {

			getUrl = getUrl.substring(end + 4, getUrl.length());

			start = getUrl.indexOf("http://jobs.zhaopin.com/");

			if (start != -1 && end != -1) {
				getUrl = getUrl.substring(start, getUrl.length());
			} else {
				break;
			}
			start = getUrl.indexOf("http://jobs.zhaopin.com/");

			end = getUrl.indexOf(".htm");
			if (start != -1 && end != -1) {
				url = getUrl.substring(start, end + 4);
			} else {
				break;
			}
			i++;
			urlSet.add(url);
		}
		System.out.println(i);
		return urlSet;
	}

	/**
	 * <p>Discription:[方法功能中文描述]</p>
	 * @param htmlString
	 * @return
	 * @author:[刘昌模]
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	
	@Override
	public String getCompanyInfo(String htmlString) {
		String str = htmlString;
		String compInfo = null;
//		================================================================================================	
		try{
		String companyName = str.substring(str.indexOf("company-logo"), str.indexOf("basic-condition"));
		companyName = companyName.substring(companyName.indexOf("公司名称"), companyName.indexOf("clearfix"));		
		String companyCon = companyName.substring(companyName.indexOf("<a"), companyName.indexOf("\">")+2);
		companyName = companyName.replace(companyCon, "");
		companyName = companyName.replace("<dd class=\"", "");
		companyName = companyName.replace(" ", "");
		companyName = companyName.replace("</dt>", "");
		companyName = companyName.replace("</a>", "");
		companyName = companyName.replace("<dd>", "");	
		companyName = companyName.replace("<dt>", "");
		companyName = companyName.replace("&nbsp;", "").trim();	
		companyName = companyName.replace("\n", "");
		companyName = companyName.replace("</dd>", "\n");
		companyName = companyName.substring(0,companyName.length()-1);
//		System.out.println(companyName);
		
//      ================================================================================================		
		
//		System.out.println(companyCon);
//      ================================================================================================
		//联系信息
		String conneInfosou = str.substring(str.indexOf("<!--联系方式-->"), str.indexOf("PositionForm"));
		String conneInfo = conneInfosou.toLowerCase();
//		System.out.println(conneInfo);
		conneInfo = conneInfo.substring(conneInfo.indexOf("<div>"), conneInfo.indexOf("clearfix"));
		conneInfo = conneInfo.replace("<div>", "");
		conneInfo = conneInfo.replace("&nbsp;", "");
		conneInfo = conneInfo.replace("&nbsp;", "");
		conneInfo = conneInfo.replace("<div class=\"", "");
		conneInfo = conneInfo.replace("<br />", "");
		conneInfo = conneInfo.replace("\n", "");
		conneInfo = conneInfo.replace("</dd>", "");
		conneInfo = conneInfo.replace("</div>", "\n");
		//检测是否有传真
		int faxStart = conneInfo.indexOf("<img");
		int faxEnd = conneInfo.indexOf(">");
		if(faxStart!=-1 && faxEnd !=-1){
		String fax = conneInfo.substring(faxStart,faxEnd+1);
		conneInfo = conneInfo.replace(fax, "");
		//System.out.println(fax);
		}
		compInfo = companyName + conneInfo;
		} catch(Exception e){
			System.out.println("信息措辞！");
		}
		return compInfo;
		}
		
		
	

	/**
	 * <p>Discription:[方法功能中文描述]</p>
	 * @param url
	 * @return
	 * @author:[刘昌模]
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	
	@Override
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
//				char[] c = { 'u' };
				IGetInfoMethod getInfoMethod = new GetInfoMethod();
				String st = getInfoMethod.getCharSetbyHeader(con);
				if(st == null){
					st = getInfoMethod.getCharSetbyMeta(url);
				}
//				System.out.println(st);
				
				//
//				int size = con.getHeaderFields().size();
//				String str = null;
//				StringBuffer strBuffer = new StringBuffer();
//				for (int i = 0; i < size; i++) {
//					strBuffer.append(con.getHeaderFieldKey(i) + "\t");
//					strBuffer.append(con.getHeaderField(i));
//					strBuffer.append("\n");
//					if (con.getHeaderFieldKey(i) != null) {
//						if (con.getHeaderFieldKey(i).equals("Content-Type")) {
//							str = con.getHeaderField(i);
//							break;
//						}
//					}
//				}
//				System.out.println(str);
//				System.out.println(strBuffer.toString());
//				System.out.println(strBuffer.toString().indexOf("charset"));

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
				bufferedReader.close();
				content = stringBuffer.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return content;
		}
	
	
	
	

}
