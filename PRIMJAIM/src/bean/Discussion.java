package bean;

public class Discussion {
	private int discussionId;
	private String teacherId;
	private String teacherName;
	private String text;
	private String postDate;
	/**
	 * @return the discussionId
	 */
	public int getDiscussionId() {
		return discussionId;
	}
	/**
	 * @param discussionId the discussionId to set
	 */
	public void setDiscussionId(int discussionId) {
		this.discussionId = discussionId;
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
	
}
