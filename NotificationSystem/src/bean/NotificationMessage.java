
package bean;

public class NotificationMessage {
	
	//private final String title = "PRIM Messaging Services 2017";
	private String title;
	private String message;
	private Project project;
	
	//Constructor
	public NotificationMessage(String title, String message){
		this.message = message;
		this.title = title;
	}
	
	public NotificationMessage(String title, String message, Project project){
		this.message = message;
		this.title = title;
		this.project = project;
	}
	
	//Setter and Getter
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}
