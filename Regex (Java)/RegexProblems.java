import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexProblems {

	public static void main( String [] args ) {
		System.out.println( isHex( "0213ADEF4") );
		System.out.println( isHex( "091232380x498023" ) );
		System.out.println( isHex( "0xABCD1234") );
		System.out.println( logMess("Log 890: Hello this is Calvin" ));
		List<String> vars = new ArrayList<String>();
		for ( int i = 0; i < 5; i++ ) {
			vars.add( "e" + i );
		}
		vars.add( "Afjlksd" );
		vars.add( "_fjalskdfj" );
		vars.add( "9009" );
		vars.add( "9Afjlksd" );
		vars.add( "_fjalskdfj" );
		vars.add( "jhkh9009" );
		remover( vars );
		System.out.println( vars );
		System.out.println( isAddress( "0.0.0.0" ) );
		System.out.println( isAddress( "255.115.255.90" ) );
		System.out.println( isAddress( "2.21.21.21" ) );
		
	}
	
	public static boolean isHex( String num ) {
		Pattern p = Pattern.compile("[A-Z0-9]+|0x[A-Z0-9]+");
		Matcher m = p.matcher( num );
		return m.matches();
		
	}
	
	public static String logMess( String message ) {
		String [] mess = message.split("Log [0-9]+: ");
		return mess[1];
	}
	
	public static void remover( List<String> words ) {
		if ( words == null ) {
			return;
		}
		Pattern p = Pattern.compile( "[a-z_][a-zA-Z0-9_]+" );
		Matcher m = p.matcher("");
		for ( int i = 0; i < words.size(); i++ ) {
			m = p.matcher(words.get(i));
			if( !m.matches() ) {
				words.remove( i );
				i--;
			}
		}
	}
	
	public static boolean isAddress( String address ) {
		Pattern p = Pattern.compile( "(0.|1[0-9][0-9].|2[0-4][0-9].|25[0-5].|[0-9]{1,}.|2[0-9].){3}(0|1[0-9][0-9]|2[0-4][0-9]|25[0-5]|[0-9]{1,})" );
		Matcher m = p.matcher( address );
		return m.matches();
	}
}


//[.*AP.*[0-9]{4}] |[.*[0-9]{4}.*AP]

