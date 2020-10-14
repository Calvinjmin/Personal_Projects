import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPractice {

	public RegexPractice() {
		Scanner scan = new Scanner( "./src/names.txt" );
		String line = scan.nextLine();
		Pattern p = Pattern.compile("");
		int matches = 0;
		while ( !line.equals( "" ) ) {
			Matcher m = p.matcher( line );
			
			
		}
		
	}
	public static void main ( String [] args ) {
		new RegexPractice();
	}
}
