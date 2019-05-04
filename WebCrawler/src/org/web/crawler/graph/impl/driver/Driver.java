package org.web.crawler.graph.impl.driver;

import org.web.crawler.graph.impl.WebCrawler;

public class Driver {

	public static void main(String[] args) {
		WebCrawler crawler = new WebCrawler();
		String url = "https://www.tutorialspoint.com";
		crawler.discoverWeb(url);
	}

}
