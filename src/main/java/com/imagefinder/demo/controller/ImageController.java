package com.imagefinder.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import com.imagefinder.demo.Information;
import com.imagefinder.demo.urlCrawler;


@RestController
public class ImageController {
	
	
	public static final String[] testImages = {"https://images.pexels.com/photos/545063/pexels-photo-545063.jpeg?auto=compress&format=tiny","https://images.pexels.com/photos/464664/pexels-photo-464664.jpeg?auto=compress&format=tiny", "https://images.pexels.com/photos/406014/pexels-photo-406014.jpeg?auto=compress&format=tiny", "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?auto=compress&format=tiny"};
	protected static final Gson GSON = new GsonBuilder().create();
	public static ArrayList<String> processedUrls = new ArrayList<String>();
	public static ArrayList<String> toProcessUrls = new ArrayList<String>();
	public static Information currentSiteImages = new Information();
	
	
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public ResponseEntity getImages(@RequestBody String url) {
		urlCrawler crawl = new urlCrawler(url);
		Thread currentSite = new Thread(crawl, "thread1");
		currentSite.start();
		try {
		currentSite.join();
		for (int i = 0; i< toProcessUrls.size(); i++) {
			System.out.println("\njfnsdjnf" + toProcessUrls.get(i));
			Thread temp = new Thread(new urlCrawler(toProcessUrls.get(i)), "Thread#" + i);
			temp.start();
			
		}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(GSON.toJson(currentSiteImages));
	}
	
	
	
	
	
	
}