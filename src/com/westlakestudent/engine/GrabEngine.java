package com.westlakestudent.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.westlakestudent.io.IOUtils;

/**
 *
 * GrabEngine
 * @author chendong
 * 2014年9月12日 下午4:51:15
 * 
 * @version 2.0.0
 *
 */
public class GrabEngine {

	private String mainpath = "https://github.com";
	private String path = "https://github.com/android/platform_frameworks_base/tree/master/core/java/android";
	
	private static GrabEngine instance = null;
	private List<String> urls = new ArrayList<String>();
	private GrabEngine(){}
	
	private IOUtils IO = null;
	public static GrabEngine getIntance(){
		if(instance == null)
			instance = new GrabEngine();
		return instance;
	}
	
	public void start(){
		IO = IOUtils.getInstance();
		fetch(path);
	}
	
	
	private void fetch(String path){
		try {
			Document doc = Jsoup.connect(path).timeout(100 * 1000).get();
			Element node = doc.body();
			Elements packages = node.getElementsByClass("content");
			for(Element pack : packages){
				Element a = pack.getElementsByTag("a").first();
				String href =  a.attr("href");
				String url = mainpath + a.attr("href");
				if(href.contains(".")){
					System.out.println("grabbed the right url");
					urls.add(url);
					String content = getcontent(url);
					IO.saveFile(url, content);
				}else{
					System.out.println("fetching...");
					IO.mkdirchild(url);
					fetch(url);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
	}
	
	private String getcontent(String path){
		StringBuffer content = new StringBuffer();
		try {
			Document doc = Jsoup.connect(path).timeout(100 * 1000).get();
			Element node = doc.body();
			Element body = node.getElementsByTag("tbody").first();
			Elements trs = body.getElementsByTag("tr");
			for(Element tr : trs){
				content.append(tr.text() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return content.toString();
	}
}
