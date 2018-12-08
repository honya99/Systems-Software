  //Honya Elfayoumy

public class labelObject extends HashableObject{
	private String label;
	private int location;
	
	public labelObject(String label, int location){
		this.label = label;
		this.location = location;
		setHashableString(label);
	}
	
	public String getLabel(){
		return label;
	}

	public int getLocation(){
		return location;
	}
}