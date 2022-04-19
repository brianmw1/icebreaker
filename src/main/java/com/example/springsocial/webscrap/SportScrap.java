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

public class SportScrap {
	private int numberOfItems = 0;
	public SportScrap() { super(); }
	String line="";
	public void sportScrap() throws IOException {
		Document doc = Jsoup.connect("https://www.sportsnet.ca/").get();
		File file = new File("sport.csv");
		PrintWriter out = new PrintWriter(file);
		StringBuilder sb = new StringBuilder();
		
		Elements elements = doc.select("div.content-wrap");
		
		for (Element element: elements) {
			
			String newsTitle = element.select("h3").text();
			if(newsTitle==null || newsTitle==" "|| newsTitle=="") {
	
			}else {
				sb.append(newsTitle);
				sb.append("\n");
				numberOfItems++;
			}


		}
		out.write(sb.toString());
		out.close();
		
	}
	 public int getNumberOfItem() throws IOException { 
		 File file = new File("sport.csv"); 
		 FileReader fr = new FileReader(file); 
		 BufferedReader br =new BufferedReader(fr); 
		 int numOfLines=0;
		 String nextline="";
		 while((nextline=br.readLine())!=null) {
			 numOfLines++;
			 
		 }
		 return numOfLines;
		 } 
	
	 public String getSportScrap() throws IOException{ 
		 this.sportScrap();
		 File file = new File("sport.csv"); 
		 FileReader fr = new FileReader(file); 
		 BufferedReader br =new BufferedReader(fr); 
		 int randomMax=this.getNumberOfItem(); 
		 
		 int randomNum = ThreadLocalRandom.current().nextInt(1,randomMax); 
		 System.out.println("randome number is "+randomNum);
		 int i=0;
		 String firstLine = br.readLine();
		 line = firstLine;
		 while(i<randomNum) { 
			 String x = br.readLine();
;			 if(x==null || x=="" || x.isEmpty()) {
				
			 }else {
				 line = x; 
				 System.out.println("if br.readline is not null: "+line);
			 }
			 i++; 
			 System.out.println("i: "+i);
			 } 
	
		 br.close();
		 return line; 
	 }

	

}

