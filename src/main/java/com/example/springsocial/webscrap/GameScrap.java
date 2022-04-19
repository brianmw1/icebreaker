package com.example.springsocial.webscrap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GameScrap {
	private int numberOfItem=0;
	String line = "";
	public GameScrap() {
		super();
	}
	
	public void gameScrap() throws IOException {
		
		Document doc =  Jsoup.connect("https://www.gamespot.com/news/").get();
		File file = new File("game.csv");
		PrintWriter out = new PrintWriter(file);
		StringBuilder sb = new StringBuilder();
		
		Elements elements = doc.getElementsByClass("promo--metadata");
		for (Element element: elements) {
			
			String articleTitle = element.getElementsByTag("h2").text();
			sb.append(articleTitle);
			sb.append("\r\n");
			numberOfItem=numberOfItem+1;
			
		}
		elements = doc.select("div.promo-strip__item");
		for (Element element: elements) {
			String articleTitle = element.getElementsByTag("h3").text();
			if(articleTitle!=null || articleTitle!="") {
				sb.append(articleTitle);
				System.out.println(articleTitle);
				sb.append("\r\n");
				numberOfItem=numberOfItem+1;
			}

		}
		
		out.write(sb.toString());
		out.close();
	}
	
	public int getNumberOfLines() throws IOException {
		File file = new File("game.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line="";
		int numOfLine=0;
		while((line=br.readLine())!=null) {
			numOfLine++;
		}
		return numOfLine;
	}
	public String getGameScrap() throws IOException {
		this.gameScrap();
		File file = new File("game.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		int randomMax=this.getNumberOfLines();
		
		int randomNum = ThreadLocalRandom.current().nextInt(1,randomMax);
		int i=0;
		while(i<randomNum) {
			line = br.readLine();
			i++;
			System.out.println("randome line: "+i);
			
		}
		System.out.println(line);
		br.close();
	
		return line;
	}

}
