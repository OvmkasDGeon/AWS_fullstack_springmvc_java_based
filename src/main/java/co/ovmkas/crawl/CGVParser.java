package co.ovmkas.crawl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CGVParser {
	public static void main(String[] args) throws MalformedURLException, IOException {
		Document doc = Jsoup.parse(new URL("http://www.cgv.co.kr/movies/"), 2000);//2000은 시간? 무비 목록
//		System.out.println(doc);
		
//		URL url = new URL("http://www.cgv.co.kr/movies/");
//		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
//		String str="";
//		while((str = br.readLine()) !=null){
//			System.out.println(br.readLine());
//		}
		Element ele = doc.selectFirst(".sect-movie-chart");
//		System.out.println(ele);
		
		Elements lis = ele.select(".box-contents");
		System.out.println(lis);
		for(Element e : lis){

//			System.out.println(e);
			String href = e.selectFirst("a").attr("href");
			String pidx = href.substring(href.lastIndexOf("=")+1);
			String date = e.selectFirst(".txt-info").text().replaceAll("개봉", "").trim();
			String imgSrc = e.selectFirst(".thumb-image img").attr("src");
			String imgAlt = e.selectFirst(".thumb-image img").attr("alt");
			String age = e.selectFirst("i.cgvIcon").text();
			String title = e.selectFirst(".box-contents strong.title").text();
//			
//			
//			System.out.println("idx :: " +href);
//			System.out.println("개봉일 :: " +date);
//			System.out.println("imgSrc :: "+ imgSrc);
//			System.out.println("imgAlt :: " +imgAlt);
//			System.out.println("age :: " +age);
//			System.out.println("title 3:: "+title);
			
//			doc = Jsoup.parse(new URL("http://www.cgv.co.kr" + e.selectFirst("a").attr("href")), 2000);
//			System.out.println(doc);
//			break;
		}
		
//		Elements els = doc.select(".sect-movie-chart");//선택자를 가져온다
//		System.out.println(els.size());
//		
//		for(int i =0; i<els.size(); i++){
//			Element e =  els.get(i);
//			System.out.println(e);
//		}
//		
	}
}
