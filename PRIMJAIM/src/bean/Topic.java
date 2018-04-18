package bean;

public class Topic {
	
	private int topicId;
	private String teacherId;
	private String teacherName; //not calling object teacher for performance purposes
	private String title;
	private String text;
	private String postDate;
	private int totalDiscussion;
	/**
	 * @return the topicId
	 */
	public int getTopicId() {
		return topicId;
	}
	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	/**
	 * @return the teacherId
	 */
	public String getTeacherId() {
		return teacherId;
	}
	/**
	 * @param teacherId the teacherId to set
	 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	/**
	 * @return the teacherName
	 */
	public String getTeacherName() {
		return teacherName;
	}
	/**
	 * @param teacherName the teacherName to set
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the postDate
	 */
	public String getPostDate() {
		return postDate;
	}
	/**
	 * @param postDate the postDate to set
	 */
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	/**
	 * @return the totalDiscussion
	 */
	public int getTotalDiscussion() {
		return totalDiscussion;
	}
	/**
	 * @param totalDiscussion the totalDiscussion to set
	 */
	public void setTotalDiscussion(int totalDiscussion) {
		this.totalDiscussion = totalDiscussion;
	}
	
	
	

}
