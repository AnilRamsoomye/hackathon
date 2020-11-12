package com.imagefinder.demo;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class urlCrawler implements Runnable {
	
	private String url;
	private ArrayList<String> imageUrls;
	private ArrayList<String> iconUrls;
	private ArrayList<String> subsiteUrls;
	
	
	
	public urlCrawler(String toCrawl) {
		url = toCrawl;
		imageUrls = new ArrayList<String>();
		iconUrls = new ArrayList<String>();
		subsiteUrls = new ArrayList<String>();
	}
	
	public void run() {
		System.out.print("Thread Starting");
		if (!(com.imagefinder.demo.controller.ImageController.processedUrls.contains(url))) {
			com.imagefinder.demo.controller.ImageController.processedUrls.add(url);
			loadImageUrls();
			loadSubsites();
			com.imagefinder.demo.controller.ImageController.currentSiteImages.setImages(imageUrls);
			com.imagefinder.demo.controller.ImageController.currentSiteImages.setIcons(iconUrls);
			
		}
		System.out.println(subsiteUrls);
		System.out.print("Thread ending");
		
	}
	public void loadSubsites() {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href]");
			for (Element link: links) {
				subsiteUrls.add(link.absUrl("href"));
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loadImageUrls() {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements icons = doc.select("link[href ~=.*\\.(ico|png)]");
			for (Element icon: icons) {
				iconUrls.add(icon.absUrl("href"));
			}
			//images not found in iconUrls
			Elements images = doc.getElementsByTag("img");
			for (Element link: images) {
				imageUrls.add(link.absUrl("src"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> getImageUrls() {
		return imageUrls;
	}
	public ArrayList<String> getIconUrls() {
		return iconUrls;
	}
	
	public static void main(String[] args) {
		urlCrawler crawl = new urlCrawler("https://en.wikipedia.org/wiki/Main_Page");
		System.out.print(crawl.iconUrls);
	}
	
}
	
	