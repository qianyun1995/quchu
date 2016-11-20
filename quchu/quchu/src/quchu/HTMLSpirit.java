package quchu;

import java.util.regex.Matcher; 
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements; 

/*public class HTMLSpirit{ 
    public static String delHTMLTag(String htmlStr){ 
    	Document doc = Jsoup.parse(htmlStr);
    	  String text = doc.text();
    	  // remove extra white space
    	  StringBuilder builder = new StringBuilder(text);
    	  int index = 0;
    	  while(builder.length()>index){
    	    char tmp = builder.charAt(index);
    	    if(Character.isSpaceChar(tmp) || Character.isWhitespace(tmp)){
    	      builder.setCharAt(index, ' ');
    	    }
    	    index++;
    	  }
    	  text = builder.toString().replaceAll(" +", " ").trim();
    	  return text;
    } 
 */
public class HTMLSpirit{ 
	public  static String jkey(String htmlStr) {
		Document doc = Jsoup.parse(htmlStr);
		Elements metas = doc.head().select("meta"); 
		String result="";
		for (Element meta : metas) { 
			String content=content =meta.attr("content");
			if ("keywords".equalsIgnoreCase(meta.attr("name"))||"keyword".equalsIgnoreCase(meta.attr("name"))) {
					result+="abKEYWORDSba"+content;
			}
			/*if ("description".equalsIgnoreCase(meta.attr("name"))) {
					result+="\r\nab网站内容描述ba"+"\r\n"+content;
				
			}*/
		} 
		//Elements keywords = doc.getElementsByTag("meta");
		return result;

	}
	public static String delHTMLTag(String htmlStr){ 
        String luanma="[\u4e00-\u9fa5]{4,}";
		//取出script、css
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // script
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // style
		// url
		String regEx_link="^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
		//html tag
		String regEx_html = "<[^>]+>";
		// other characters
		String regEx_space = "\\s+|\t|\r|\n";
		String isformat="<!DOCTYPE html>";
		Pattern p_luanma = Pattern.compile(luanma,Pattern.CASE_INSENSITIVE);
		Matcher m_luanma = p_luanma.matcher(htmlStr);
		Pattern p_format = Pattern.compile(isformat,Pattern.CASE_INSENSITIVE);
		Matcher m_format = p_format.matcher(htmlStr);
		if(!m_format.find()||!m_luanma.find())
		{
			htmlStr="123456";
		}else{

			Pattern p_link = Pattern.compile(regEx_link,
					Pattern.CASE_INSENSITIVE);
			Matcher m_link =  p_link.matcher(htmlStr);
			htmlStr = m_link.replaceAll("");
			Pattern p_script = Pattern.compile(regEx_script,
					Pattern.CASE_INSENSITIVE);
			Matcher m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");
			Pattern p_style = Pattern
					.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			Matcher m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");
			Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");
			Pattern p_space = Pattern
					.compile(regEx_space, Pattern.CASE_INSENSITIVE);
			Matcher m_space = p_space.matcher(htmlStr);
			htmlStr = m_space.replaceAll(" ");
		}
		return htmlStr;
	} 
	public static String stripHtml(String content) { 
		//special tag
		content = content.replaceAll("\\&[a-zA-Z]{1,10};", " ");
		// <p>段落替换为换行 
		content = content.replaceAll("<p .*?>|p>|<p", " "); 
		// <br><br/>替换为换行 
		content = content.replaceAll("<br\\s*/?>", ""); 
		// 去掉其它的<>之间的东西 
		content = content.replaceAll("\\<.*?>", ""); 
		content = content.replaceAll("null", ""); 
		content = content.replaceAll("br", ""); 
		content = content.replaceAll("http", ""); 
		content = content.replaceAll("img", ""); 
		content = content.replaceAll("li", ""); 
		content = content.replaceAll("class", ""); 
		content = content.replaceAll("amp", ""); 
		content = content.replaceAll("target", ""); 
		content = content.replaceAll("blank", ""); 
		content = content.replaceAll("<script src|<script.*/>", ""); 
		content = content.replaceAll("\n\r", " "); 
		content = content.replaceAll("\r\n", " "); 
		content = content.replaceAll("\n", " "); 
		content = content.replaceAll("\r", " "); 
		content = content.replaceAll("nbsp", " "); 
		content = content.replaceAll("\\s", ""); 
		content = content.replaceAll("/a>|<a|<a href|<a>|</a>", " "); 
		content = content.replaceAll("/img>|<img|<img>", " "); 
		content = content.replaceAll("/span>", " ");
		content = content.replaceAll("div", " ");
		content = content.replaceAll("href", " ");
		//content = content.replaceAll("quot", " ");
		//content = content.replaceAll("label", " ");
		//content = content.replaceAll("li", " ");
		//content = content.replaceAll("ul", " ");
		content = content.replaceAll("<em>|em>", " ");
		//content = content.replaceAll("img", " ");
		//content = content.replaceAll("src", " ");
		content = content.replaceAll("/div>", " "); 
		content = content.replaceAll("/title>", " "); 
		content = content.replaceAll("&lt", " "); 
		content = content.replaceAll("&gt", " "); 
		content = content.replaceAll("xmlversion", " ");
		content = content.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", " "); 
		// 还原HTML 
		// content = HTMLDecoder.decode(content); 

		return content; 
	}
	
	public static String jkeystripHtml(String content) { 
		
		content = content.replaceAll("null", ""); 
		content = content.replaceAll("nbsp", " "); 
		content = content.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", " "); 

		return content; 
	}
} 