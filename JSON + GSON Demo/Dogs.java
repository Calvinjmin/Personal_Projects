
public class Dogs {
	
	private String name;
	private String popularity;
	
	public Dogs( String name, String split ) {
		this.name = name;
		this.popularity = split;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPopularity() {
		return popularity;
	}
	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}
	
	public String toString() {
		return "Name: " + name + " \t" + "Popularity: " + popularity;
	}

}
