
package com.example.springsocial.webscrap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MovieReleaseScrap {
	public MovieReleaseScrap() {
		
	}
	
	private String scrapPage() throws IOException {
		File csvMovies = new File("movies.csv");
		  PrintWriter out = new PrintWriter(csvMovies);
		  StringBuilder sb = new StringBuilder();
		  sb.append("Movie Title");
		  sb.append(",");
		  sb.append("Release Date");
		  sb.append("\r\n");
		  try {
		      /**
		       * Here we create a document object,
		       * The we use JSoup to fetch the website.
		       */
		      Document doc = Jsoup.connect("https://www.metacritic.com/browse/movies/release-date/theaters/date").get();
		      
		      /**
		       * With the document fetched,
		       * we use JSoup???s title() method to fetch the title
		       */ 
		      System.out.printf("\nWebsite Title: %s\n\n", doc.title());


		      // Get the list of repositories
		      
		      Elements repositories = doc.getElementsByClass("clamp-list");
		      
		    		  /**
		       * For each repository, extract the following information:
		       * 1. Title
		       * 2. Number of issues
		       * 3. Description
		       * 4. Full name on github
		       */
		      for (Element repository : repositories) {
		        // Extract the title
		        Elements repositorySummaries = repository.getElementsByClass("clamp-summary-wrap");
		        for (Element repositorySummary: repositorySummaries) {
		        	String movieTitle = repositorySummary.getElementsByClass("title").select("a").text();
		        	String releaseDate = repositorySummary.getElementsByClass("clamp-details").text();
		        
		        	releaseDate = releaseDate.replaceAll("[,|]", "");
		        	
		        	System.out.println("Title: "+movieTitle);
		        	System.out.println("Release Date: "+releaseDate+"\n");
		        	sb.append(movieTitle);
		        	sb.append(",");
		        	sb.append(releaseDate);
		        	sb.append("\r\n");
		        	String repositoryTitle = repositorySummary.getElementsByClass("title").text();
		        	
		        }
	
		      }

		    /**
		     * Incase of any IO errors, we want the messages 
		     * written to the console
		     */
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    out.write(sb.toString());
		    out.close();
		    

		  
		    return "success";
	}
	
	public String getMovieReleaseScrap(int indexOfMovieRelease) throws IOException {
		this.scrapPage();
		File file = new File("movies.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		String delimiter = ",";
		int linesOfInfo = 0;
		while (linesOfInfo <indexOfMovieRelease+1) {
			line = br.readLine();
			linesOfInfo++;
		}
		
		return line;
		
	}
   

}