import java.util.Scanner;

public class Distance {

	//Distance Formula: a = sin²(Δφ/2) + cos φ1 ⋅ cos φ2 ⋅ sin²(Δλ/2)
					  //c = 2 ⋅ atan2( √a, √(1−a) )
					  //d = R ⋅ c
					  //where	φ is latitude, λ is longitude, R is earth’s radius (mean radius = 6,371km);
	
	//Web: https://www.latlong.net/degrees-minutes-seconds-to-decimal-degrees -- Converts coordinates to degrees
	
	public static void main (String [] args ) {
		/*
		Scanner prompt = new Scanner(  System.in );
		System.out.println( "Input an address below.." );
		String address1 = prompt.nextLine();
		System.out.println( address1 );
		*/
		
		double lat1 = toRad( 50.06638889 );
		double long1 = toRad( 5.71472222 );
		double lat2 = toRad( 58.64388889 );
		double long2 = toRad( 3.07000000 );
		
		System.out.println( "The distance between the two points is " + distance( lat1, long1, lat2, long2) + "km" );
		
	}
	
	public static double toRad( double deg ) {
		return deg * (Math.PI/180);
	}
	
	public static double distance( double lat1, double long1, double lat2, double long2 ) {
		double earthRadius = 6371;
		double a = Math.pow( Math.sin( (lat2 - lat1) / 2 ) , 2 ) + Math.cos( lat1 )*Math.cos( lat2 )*Math.pow( Math.sin( (long2 - long1)/2 ), 2);
		double c = 2*Math.atan2( Math.sqrt(a) , Math.sqrt( 1 - a ) );
		return Math.round( earthRadius * c );	
	}
	
}
