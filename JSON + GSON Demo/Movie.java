import java.util.Arrays;

public class Movie {
	
	private String title;
	private int year;
	private String [] cast;
	private String [] genres;
	
	public Movie ( String title, int year, String [] cast, String [] genres) {
		this.title = title;
		this.year = year;
		this.cast = cast;
		this.genres = genres;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String[] getCast() {
		return cast;
	}
	public void setCast(String[] cast) {
		this.cast = cast;
	}
	public String[] getGenres() {
		return genres;
	}
	public void setGenres(String[] genres) {
		this.genres = genres;
	}
	
	public String toString() {
		return title + " (" + year + ")\nGenres: " + Arrays.toString( genres ).substring( 1 , Arrays.toString( genres ).length() - 1 ) 
				+ "\nCast: " +Arrays.toString( cast ).substring( 1 , Arrays.toString( cast ).length() - 1 ) + "\n";
	}
	

}
