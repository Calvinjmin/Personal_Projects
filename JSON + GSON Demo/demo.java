import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class demo {

	public static void main ( String [] args ) {
		//Building Two Flags
		Flag southKorea = new Flag();
		Flag america = new Flag();
		
		southKorea.setCountry( "South Korea");
		southKorea.setColors( new String[] { "Red", "White", "Blue", "Black" } );
		southKorea.setRetired( false );
		
		america.setCountry( "America" );
		america.setColors( new String[] {"Red","White","Blue"} );
		america.setRetired( false );
		
		//GSON Build
		Gson theOG = new GsonBuilder().setPrettyPrinting().create();
		String jsonKorea = theOG.toJson( southKorea );
		String jsonAmerica = theOG.toJson( america );
		
		//Print the JSON Strings
		System.out.println( jsonKorea + "\n" + jsonAmerica);
		
		Flag[] flagArray = new Flag [] {southKorea , america};
		String jsonFlagArray = theOG.toJson(flagArray);
		System.out.println( jsonFlagArray );
		
		ArrayList<Flag> flagList = new ArrayList<Flag>();
		flagList.add( southKorea );
		flagList.add( america );
		System.out.println( theOG.toJson(flagList) );
		
		HashMap<String, Flag> flagMap = new HashMap<String, Flag>();
		flagMap.put( southKorea.getCountry() , southKorea );
		flagMap.put( america.getCountry(), america );
		System.out.println( theOG.toJson(flagMap) );
		
		//Converting from JSON
		Flag koreaConvert = theOG.fromJson(jsonKorea , Flag.class );
		System.out.println( koreaConvert + "\n" );
		
		Flag[] flagArrayConvert = theOG.fromJson( jsonFlagArray, Flag[].class);
		System.out.println( Arrays.toString(flagArrayConvert) + "\n" );
		
		ArrayList<Flag> flagListConvert = theOG.fromJson( jsonFlagArray, ArrayList.class );
		System.out.println( flagListConvert );
	}
}
