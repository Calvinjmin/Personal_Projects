import java.util.Arrays;

public class Flag {

	private String country;
	private String [] colors;
	private boolean retired;
	
	public String toString() {
		String print = "Country Name: " + country + "\nColors: " + Arrays.toString( colors ) + "\nRetired: " + retired;
		return print;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String[] getColors() {
		return colors;
	}
	public void setColors(String[] colors) {
		this.colors = colors;
	}
	public boolean isRetired() {
		return retired;
	}
	public void setRetired(boolean retired) {
		this.retired = retired;
	}
	
}
