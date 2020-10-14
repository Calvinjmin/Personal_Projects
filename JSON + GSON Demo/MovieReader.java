import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MovieReader {

	private static String filename = "/Users/calvinjmin/Downloads/movies.json";
	private static String menu = "Type in a letter that correlates to the option you wish to choose\n"
			+ "A) Look up and Actor/Actress\nB) Look up a Movie Title\nC) Look up a Year\nD) Add a new movie\nE) Degrees to Kevin Bacon";
	private static String file;
	private static Scanner sc = new Scanner(System.in);
	private static Gson theOG = new Gson();

	public static void main(String[] args) throws IOException {
		menu();
	}

	private static String getFileContents(String filename) throws IOException {
		return new String(Files.readAllBytes(Paths.get(filename)));
	}
	
	public static void menu() throws IOException {
		file = getFileContents(filename);
		
		System.out.println(menu);
		String answer1 = sc.nextLine();
		
		if (answer1.toLowerCase().equals("a")) {
			System.out.println("Type in an actor's name below");
			String actorName = sc.nextLine();
			optionA( actorName );
			menu();
		}

		if (answer1.toLowerCase().equals("b")) {
			System.out.println("Type in a movie below");
			String movieName = sc.nextLine();
			optionB( movieName );
		}

		if (answer1.toLowerCase().equals("c")) {
			System.out.println("Type in a year below");
			optionC( Integer.parseInt(sc.nextLine()) );
			menu();
			
		}

		if (answer1.toLowerCase().equals("d")) {
			System.out.println("Type in the name of the movie below");
			String name = sc.nextLine();
			System.out.println("Type in the year the movie was made below");
			int year = Integer.parseInt(sc.nextLine());
			System.out.println("Type the cast of the movie and seperate each actor by commas and a space below");
			String[] cast = sc.nextLine().split(", ");
			System.out.println("Type the genres of the movie below. If there's more than one, seperate by commas");
			String[] genres = sc.nextLine().split(",");
			Movie temp = new Movie(name, year, cast, genres);
			String jsonMovie = theOG.toJson(temp);
			String updatedFile = file.substring(0, file.length() - 1) + "," + jsonMovie + "]";
			writeFile( updatedFile, "/Users/calvinjmin/Downloads/movies.json");
			System.out.println(jsonMovie + "\nJson File was updated with this movie data above.");
			menu();
		}
		
		if (answer1.toLowerCase().equals("e" )) {
			System.out.println("Type in an actor/actress that could connect to Kevin Bacon");
		}
	}
	
	public static void optionA ( String actor ) {
		TypeToken<List<Movie>> token = new TypeToken<List<Movie>>() {};
		ArrayList<Movie> movies = theOG.fromJson(file, token.getType());
		
		int numOfMovies = 0;
		for (Movie tempMovie : movies) {
			String[] castMembers = tempMovie.getCast();
			for ( int i = 0; i < castMembers.length; i++ ) {
				if ( castMembers[i].equals(actor) ) {
					numOfMovies++;
					System.out.println(tempMovie);
				}
			}
		}
		if ( numOfMovies == 0 ) {
			System.out.println("The name you typed in could not be found. Try another name!");
			String actorNameAgain = sc.nextLine();
			optionA ( actorNameAgain );
		}
	}
	
	public static void optionB (String movieName) {
		TypeToken<List<Movie>> token = new TypeToken<List<Movie>>() {};
		ArrayList<Movie> movies = theOG.fromJson(file, token.getType());
		int numOfMoviesFound = 0;
		ArrayList<Movie> moviesFound = new ArrayList<Movie>();
		
		try {
			for (Movie tempMovie : movies) {
				if ( tempMovie.getTitle().contains( movieName ) ) {
					numOfMoviesFound += 1;
					moviesFound.add( tempMovie );
					if ( tempMovie.getCast().length == 0 ) {
						System.out.println(numOfMoviesFound + ") " + tempMovie.getTitle() + " (" + tempMovie.getYear() + ")" );
					}
					else {
					System.out.println(numOfMoviesFound + ") " + tempMovie.getTitle() + " (" + tempMovie.getYear() + ", " + tempMovie.getCast()[0] + ")" );
					}
				}
			}
			if ( numOfMoviesFound >= 1 ) {
				System.out.println("Type in a number to learn more information about that specific movie");
				System.out.println( moviesFound.get( Integer.parseInt( sc.nextLine() ) - 1 ) );
				menu();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		if ( numOfMoviesFound == 0 ) {
			System.out.println( "Movie could not be found. Try again.");
			optionB( sc.nextLine() );
		}
	}
	
	public static void optionC ( Integer yearAsked ) {
		TypeToken<List<Movie>> token = new TypeToken<List<Movie>>() {};
		ArrayList<Movie> movies = theOG.fromJson(file, token.getType());
		
		int numOfMovies = 0;
		for (Movie tempMovie : movies) {
			if (tempMovie.getYear() == yearAsked) {
				numOfMovies++;
			}
		}
		System.out.println("The number of movies produced in the year " + yearAsked + " is " + numOfMovies);
	}

	public static void writeFile(String text, String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write(text);
		writer.close();
	}

}
