package org.web.crawler.graph.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {
	private Queue<String> queue;
	private List<String> listOfDiscoveredWebSite;
	public WebCrawler() {
		this.queue = new LinkedList<String>();
		this.listOfDiscoveredWebSite = new ArrayList<String>();
	}
	
	public void discoverWeb(String root) {
		this.queue.add(root);
		this.listOfDiscoveredWebSite.add(root);
		
		while(!queue.isEmpty()) {
			String v = this.queue.remove();
			String rawHtml = readURL(v);
			
			String regexp = "https://(\\w+\\.)*(\\w+)";
			Pattern pattern = Pattern.compile(regexp);
			Matcher matcher = pattern.matcher(rawHtml);
			while(matcher.find()) {
				String actualUrl = matcher.group();
				if(!listOfDiscoveredWebSite.contains(actualUrl)) {
					listOfDiscoveredWebSite.add(actualUrl);
					System.out.println("Web site linked: "+actualUrl);
					this.queue.add(actualUrl);
				}
			}
		}
	}

	private String readURL(String v) {
		String rawHtml = "";
		try {
			URL url = new URL(v);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine = "";
			while((inputLine=in.readLine())!=null) {
				rawHtml = rawHtml+inputLine;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawHtml;
	}
}
