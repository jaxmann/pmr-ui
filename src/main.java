import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class main {

	public static final boolean DEBUG = true;
	public static String currentUser = null;
	public static ArrayList<Player> players = new ArrayList<Player>();

	public static void main(String[] args) {
		//debug flag for print statements

		//set up favorite teams/players - players and teams you want to track

		//parse play - figure out what happened - who scored, what team, what the score is, the date?, what sport, set time as NOW

		//identify a search query based on the play/identify keywords used to search, make sure post is recent (PMR)

		//perform search/crawl on websites, save url, verify its a smallish video file

		//send a link (create an alert at first)


		//////////////////////////////////////////////////////////////////
		//after picking teams, etc
		//do big db call to populate teams/players arraylist
		//play alert is received from api

		//possible to approximate file size before download
		//HttpURLConnection content = (HttpURLConnection) new URL("https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html").openConnection();
		//		System.out.println(content.getContentLength());


		players.add(new Player("Marco Reus", "Borussia Dortmund", "Bundesliga"));
		players.add(new Player("Christiano Ronaldo", "Real Madrid", "La Liga"));
		players.add(new Player("Emre Mor", "Borussia Dortmund", "Bundesliga"));
		players.add(new Player("Emre Can", "Liverpool", "EPL"));
		players.add(new Player("Marek Hamsik", "Napoli", "Serie A"));
		players.add(new Player("Edinson Cavani", "Paris Saint Germain", "Ligue 1"));
		players.add(new Player("Robert Huth", "Leicester City", "EPL"));
		players.add(new Player("Antoine Griezmann", "Atletico Madrid", "La Liga"));
		players.add(new Player("Paul Pogba", "Manchester United", "EPL"));


		Login.main(args);

		//determine if play is worth examining -- does it contain "goal"?
		//if yes, parse play for keywords/query, set time, etc
		//create a new play
		//create a thread for each play, wait 10, then run each minute until 40 ----- crawlSites + thread
		//each thread validates and checks where to send url, and sends it
		///////////////////////////////////////////////////////////////////



	}

}
