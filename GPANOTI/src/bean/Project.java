package bean;

public class Project {
	
	private int id;
	private String name;
	private String authKey;
	
	//Constructor
	public Project(){
	}
	
	public Project(String name, String authKey){
		this.name = name;
		this.authKey = authKey;
	}
	
	public Project(int id, String name, String authKey){
		this.id = id;
		this.name = name;
		this.authKey = authKey;
	}

	//Setter and getter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

}
