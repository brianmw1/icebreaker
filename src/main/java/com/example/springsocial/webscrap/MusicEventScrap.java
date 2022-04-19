package com.example.springsocial.webscrap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MusicEventScrap {
	public MusicEventScrap() {
		
	}
	
	private String scrapPage() throws IOException {
		 Document doc = Jsoup.connect("https://www.songkick.com/metro-areas/27396-canada-toronto").get();
		 Elements repositories = doc.getElementsByClass("event-listings-element");
		 File csvConcert = new File("concert.csv");
		 PrintWriter out = new PrintWriter(csvConcert);
		 StringBuilder sb = new StringBuilder();
		 sb.append("Performer");
		 sb.append(",");
		 sb.append("Location");
		 sb.append(",");
		 sb.append("City Province Country");
		 sb.append("\r\n");
		 int i=0;
		 for (Element repository: repositories) {
			
			 
			 i++;
			 
			 Elements artistLocationWrapper = repository.getElementsByClass("artists-venue-location-wrapper");
			 for (Element artistLocation: artistLocationWrapper) {
				 // .toString will include <strong>
				 String artist= artistLocation.getElementsByTag("strong").text();
				 String location = artistLocation.getElementsByClass("venue-link").text();
				 String city = artistLocation.getElementsByClass("city-name").text();
				 //System.out.println(artist+", "+location+", " +city);
				 artist=artist.replaceAll(",", "");
				 location=location.replaceAll(",", " ");
				 city = city.replaceAll(",", "");
				 sb.append(artist);
				 sb.append(",");
				 sb.append(location);
				 sb.append(",");
				 sb.append(city);
				 sb.append("\r\n");
			 }
			 
		 }
		 out.write(sb.toString());
		 out.close();
		 
		 System.out.printf("numbers of container is %d", i);
		 
		 return "success";
	}
	
	public String getMusicEventScrap(int indexOfMusicEvent) throws IOException {
		this.scrapPage();
		File file = new File("concert.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		String delimiter = ",";
		int linesOfInfo = 0;
		while (linesOfInfo <indexOfMusicEvent+1) {
			line = br.readLine();
			linesOfInfo++;
		}
		
		return line;
		
	}
   

}
