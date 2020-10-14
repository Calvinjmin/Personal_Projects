import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ReadFile {
	
	private static String filename;
	
	public static void main (String [] args ) throws IOException {
		
		//Reads from a file, parses the String into an array based of the DATA section from the JSON File
		filename = "/Users/calvinjmin/Downloads/dogNames.json";
		String jsonData = getFileContents( filename );
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject)jsonParser.parse( jsonData );
		JsonArray jsonArr = jo.getAsJsonArray( "data" );
		Dogs[] top100 = new Dogs[100];
		int bCount = 0;
		
		//Creates and assigns each slot in the top100 Array a dog
		for ( int i = 0; i < jsonArr.size(); i++ ) {
			String iteration = jsonArr.get(i).toString();
			String[] split = iteration.split(",");
			String name = split[ split.length - 2].substring( 1 , split[ split.length - 2].length() - 1);
			String popularity = split[split.length-1].substring(1, split[split.length-1].length() - 2);
			
			Dogs temp = new Dogs( name , popularity  );
			if ( i < 100 ) {
				top100[i] = temp;
			}
			else {
				break;
			}
		}
		
		//Loops through each dog and calculates different statistics
		System.out.println( "Top 100 most popular names from Anchorage Animal Care and Control\n");
		int pop = 1;
		int totalPop = 0;
		for ( Dogs temp: top100 ) {
			System.out.println( pop + "\t" + temp );
			pop++;
			
			totalPop += Integer.parseInt( temp.getPopularity() );
			
			if ( temp.getName().charAt(0) == 'B' ) {
				bCount++;
			}
		}
		
		System.out.println("\n" + bCount + " dogs have a name that start with B in the Top 100" + "\nThe combined popularity in the top 100 is " + totalPop);	
	}
	
	 private static String getFileContents( String filename ) throws IOException {
	        return new String(Files.readAllBytes(Paths.get(filename)));
	 }
	 
}
