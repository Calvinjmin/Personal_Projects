import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ScannerMap {
	public static void main( String [] args ) {
		Map<String, Integer> count = new TreeMap<String, Integer>();
		Map<Integer, TreeSet<String> > voter = new TreeMap< Integer, TreeSet<String>>();
		Scanner scan;
		try {
			scan = new Scanner( new File( "./src/votes.txt" ));
			String vote = scan.nextLine();
			while ( scan.hasNext() ) {
				if ( count.containsKey( vote ) ) {
					count.put( vote, count.get( vote ) + 1 );
				}
				else {
					count.put( vote, 1 );
				}
				vote = scan.nextLine();
			}
			
			for ( String cand : count.keySet()) {
				int voted = count.get( cand );
				if ( voter.containsKey( voted ) ) {
					TreeSet<String> temp = voter.get( voted );
					temp.add( cand );
				}
				else {
					TreeSet<String> temp = new TreeSet<String>();
					temp.add( cand );
					voter.put( voted , temp );
				}
			}
		
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			System.out.println( voter );
		}
	}
}
