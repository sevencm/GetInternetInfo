package com.sevencm.test;


import java.util.Set;


import com.sevencm.method.GetInfoMethod;
import com.sevencm.method.IGetInfoMethod;

public class Test {
	
//http://t.qq.com/
	public static void main(String[] args) {
		
		IGetInfoMethod getInfoMethod = new GetInfoMethod();
		String str = getInfoMethod
				.GetResponseDataByID("http://sou.zhaopin.com/jobs/searchresult.ashx?in=170500%3B120800%3B129900%3B121200&jl=763&sm=0&p=1");
		
		
		Set<String> urlSet = getInfoMethod.getURL(str);
		
		for(Object url :urlSet) {
			  String urlHtml =  (String) url;
//			  System.out.println(urlHtml);
//			  IGetInfoMethod getInfoMethod_ = new GetInfoMethod();
			  String strGetI = getInfoMethod.GetResponseDataByID(urlHtml);
			  
//			  System.out.println(strGetI);
			  System.out.println(getInfoMethod.getCompanyInfo(strGetI));
			}
		
//		  String strGetI = getInfoMethod.GetResponseDataByID("http://jobs.zhaopin.com/416378228250008.htm");
//		  System.out.println(strGetI);
////		  System.out.println(strGetI);
//		  System.out.println(getInfoMethod.getCompanyInfo(strGetI));
		
//		String strGetI = getInfoMethod.GetResponseDataByID("http://jobs.zhaopin.com/guangzhou/%e5%a4%a7%e5%ae%a2%e6%88%b7%e9%83%a8%e5%95%86%e5%8a%a1%e7%bb%8f%e7%90%86_493183727250091.htm");
//		System.out.println(strGetI);
//		================================================================================================		
//		String companyName = str.substring(str.indexOf("company-logo"), str.indexOf("basic-condition"));
//		companyName = companyName.substring(companyName.indexOf("公司名称"), companyName.indexOf("clearfix"));		
//		String companyCon = companyName.substring(companyName.indexOf("<a"), companyName.indexOf("\">")+2);
//		companyName = companyName.replace(companyCon, "");
//		companyName = companyName.replace("<dd class=\"", "");
//		companyName = companyName.replace(" ", "");
//		companyName = companyName.replace("</dt>", "");
//		companyName = companyName.replace("</a>", "");
//		companyName = companyName.replace("<dd>", "");	
//		companyName = companyName.replace("<dt>", "");
//		companyName = companyName.replace("&nbsp;", "").trim();	
//		companyName = companyName.replace("\n", "");
//		companyName = companyName.replace("</dd>", "\n");
//		companyName = companyName.substring(0,companyName.length()-1);
////		System.out.println(companyName);
//		
////      ================================================================================================		
//		
////		System.out.println(companyCon);
////      ================================================================================================
//		//联系信息
//		String conneInfosou = str.substring(str.indexOf("<!--联系方式-->"), str.indexOf("PositionForm"));
//		String conneInfo = conneInfosou.toLowerCase();
////		System.out.println(conneInfo);
//		conneInfo = conneInfo.substring(conneInfo.indexOf("<div>"), conneInfo.indexOf("clearfix"));
//		conneInfo = conneInfo.replace("<div>", "");
//		conneInfo = conneInfo.replace("&nbsp;", "");
//		conneInfo = conneInfo.replace("&nbsp;", "");
//		conneInfo = conneInfo.replace("<div class=\"", "");
//		conneInfo = conneInfo.replace("<br />", "");
//		conneInfo = conneInfo.replace("\n", "");
//		conneInfo = conneInfo.replace("</dd>", "");
//		conneInfo = conneInfo.replace("</div>", "\n");
//		//检测是否有传真
//		int faxStart = conneInfo.indexOf("<img");
//		int faxEnd = conneInfo.indexOf(">");
//		if(faxStart!=-1 && faxEnd !=-1){
//		String fax = conneInfo.substring(faxStart,faxEnd+1);
//		conneInfo = conneInfo.replace(fax, "");
//		//System.out.println(fax);
//		}
//		String compInfo = companyName + conneInfo;
//		System.out.println(compInfo);
		
//==================================================================================		
//		String getUrl = str.substring(str.indexOf("tabhead"), str.indexOf("next-page")); //获取job的连接
//		int start = getUrl.indexOf("http://jobs.zhaopin.com/");
//		int end = getUrl.indexOf(".htm");
//		
////		System.out.println(getUrl);
//		String url = null;
//		url = getUrl.substring(start, end+4);
//		System.out.println(url);
//		int i = 0;
//		while (start != -1 && end != -1) {
//
//			getUrl = getUrl.substring(end + 4, getUrl.length());
//
//			start = getUrl.indexOf("http://jobs.zhaopin.com/");
//
//			if (start != -1 && end != -1) {
//				getUrl = getUrl.substring(start, getUrl.length());
//			} else {
//				break;
//			}
//			start = getUrl.indexOf("http://jobs.zhaopin.com/");
//
//			end = getUrl.indexOf(".htm");
//			if (start != -1 && end != -1) {
//				url = getUrl.substring(start, end + 4);
//			} else {
//				break;
//			}
//			i++;
//			System.out.println(url);
//		}
//		System.out.println(i);
//============================智联招聘网页======================================================
//		System.out.println();
//		System.out.println(getUrl); //智联招聘
//		int start = str.indexOf("sr_bt");
//		int end = str.indexOf("每页显示");
//		
////		System.out.println(start);
////		System.out.println(end);
//		
//		String togetinfo = str.substring(start, end);
//		togetinfo = togetinfo.replace("&nbsp;", "");
//		togetinfo = togetinfo.replace("&gt;", "");
//		
//		
//		int startName = togetinfo.indexOf(">");
//		int endName = togetinfo.indexOf("<");
//		String Name = togetinfo.substring(startName+1,endName);
//		System.out.println(Name);
//		int hangye = togetinfo.indexOf("公司行业");
//		int ren = togetinfo.indexOf("人");
//		String detail = togetinfo.substring(hangye,ren+1);
//		detail = detail.replace("</strong>", "");
//		detail = detail.replace("<strong>", "\n");
//		detail = detail.replace("<br>", "");
//		System.out.println(detail);
//		
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
