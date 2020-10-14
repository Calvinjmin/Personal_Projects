
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Compression {

	private static HashMap<String, Integer> dict = new HashMap<>();
	private static int bitSize = 0;
	private static int smallestSize = 0;
	private static int smallestBitSize = 0;
	private static int startingBitSize = 20;
	private static String [] compresses;

	public static void main(String[] args) throws IOException {
		String content = getFileContents( "./src/toCompress.txt");
		compress(content);
	}

	public static void buildDict() {
		for (int i = 0; i < 128; i++) {
			dict.put((char) i + "", i);
			System.out.println((char) i + "  " + dict.get((char) i + ""));
		}
	}
	
	public static void resetDict() {
		dict = new HashMap<String,Integer>();
		buildDict();
	}

	public static void compress(String text) throws IOException {
		int skip = 0;
		int index = 128;
		
		compresses = new String [startingBitSize + 1 ];

		String finalText = "";
		String compressEfficient = "";
		String test = "";

		buildDict();
		
		for (int j = startingBitSize; j > 6; j--) {
			
			index = 128;
			skip = 0;
			resetDict();
			finalText = "";
			test = "";
			bitSize = j;
			
			for (int i = 0; i < text.length(); i = (i + skip - 1)) {
				skip = 1;

				while (i < text.length() - 1 && dict.containsKey(text.substring(i, i + skip))) {
					if ( i + skip + 1 > text.length() ) {
						skip++;
						break;
					}
					skip++;
				}

				if (i + skip > text.length()) {
					test = Integer.toBinaryString(dict.get(text.substring(i, i + skip - 1)));
					while (test.length() < bitSize) {
						test = "0" + test;
					}
					finalText += test;
					break;
				}

				if (dict.get(text.substring(i, i + skip - 1)) == null) {
					test = Integer.toBinaryString(dict.get(text.charAt(text.length() - 1) + ""));
					while (test.length() < bitSize) {
						test = "0" + test;
					}
					finalText += test;
					break;

				}
				if (dict.size() < Math.pow( bitSize, 2 ) ) {
					dict.put(text.substring(i, i + skip), index);
					index++;
					// System.out.println("Into dictionary " + text.substring(i, i + skip) + "
					// Index: " + index);
				}

				test = Integer.toBinaryString(dict.get(text.substring(i, i + skip - 1)));
				while (test.length() < bitSize) {
					test = "0" + test;
				}
				finalText += test;

				// inner loop

			}
			compresses[j] = finalText;

			
			// Calculating the best
			if (j == startingBitSize) {
				smallestSize = finalText.length();
				smallestBitSize = startingBitSize;
			}

			if (finalText.length() < smallestSize) {
				smallestBitSize = j;
				compressEfficient = finalText;

			}
			
			
		}
		System.out.println("The best compression: " + smallestBitSize + " " + compressEfficient);
		writeFileContents( "./src/compressed.txt" , Integer.toBinaryString( smallestBitSize ) + "\n" + compressEfficient  );
		
		for ( int i = startingBitSize ; i > 0; i-- ) {
			System.out.println( i + " " + compresses[i] );
		}
	}
	
	private static void writeFileContents( String filename, String data ) throws IOException {
		FileWriter fw = new FileWriter( new File( filename ) );
		fw.write( data );
		fw.close();
	}
	

	private static String getFileContents(String filename) throws IOException {
		return new String(Files.readAllBytes(Paths.get(filename)));
	}

}
