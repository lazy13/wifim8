package wifi.wifim8;

public class Spot {
	private long id;
	private String spot;
	
	public long getId(){		
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getSpot(){
		return spot;
	}
	
	public void setSpot(String spot){
		this.spot=spot;
	}
	
	public String toString(){
		return spot;
	}
}
