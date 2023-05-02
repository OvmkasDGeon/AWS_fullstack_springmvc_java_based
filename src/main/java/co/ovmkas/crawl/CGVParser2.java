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

public class CGVParser2 {
	public static void main(String[] args) throws MalformedURLException, IOException {
		String 슬램덩크 = "http://www.cgv.co.kr/movies/detail-view/?midx=86720";
		String 매트릭스 = "http://www.cgv.co.kr/movies/detail-view/?midx=85541";
		Document doc = Jsoup.parse(new URL(매트릭스), 2000);//2000은 시간? 상세페이지
		
		String info = doc.selectFirst(".sect-story-movie").text();
		String engtitle = doc.selectFirst(".sect-base-movie .title p").text();
		
		Element ele = doc.selectFirst("#ctl00_PlaceHolderContent_Section_Still_Cut");
		Elements els = doc.select(".sect-base-movie .spec dt");
		
		for(Element e : els){
			Elements es = e.getElementsContainingText("감독").next().select("a");
			for(Element e2 : es){
				String director = e2.text();
				String directorHref =e2.attr("href");
				String pidx = directorHref.substring(directorHref.lastIndexOf("=")+1);
			}
			System.out.println("================================");
			Elements es2 = e.getElementsContainingText("배우").next().select("a");
			for(Element e2 : es2){
				String actor = e2.text();
				String actorHref = e2.attr("href");
				String pidx = actorHref.substring(actorHref.lastIndexOf("=")+1);
			}
			System.out.println("================================");
			Elements es3 = e.getElementsContainingText("장르");
			for(Element e2 : es3){
				String genre = e2.text();
			}
			System.out.println("================================");
			Elements es4 = e.getElementsContainingText("기본").next();//기본 다음에 나오는걸 가져온다
			for(Element e2 : es4){
				String runningTime = e2.text().split(", ")[1]; 
				String nation = e2.text().split(", ")[2]; 
			}
		}
		
		
		Elements lis = ele.select("img");
		for(Element e : lis){
//			System.out.println(e);
			System.out.println(e.attr("data-src"));
		}
	}
}
