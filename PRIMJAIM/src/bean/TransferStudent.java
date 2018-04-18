/**
 * 
 */
package bean;

import java.sql.Date;

/**
 * @author TiTAS
 *
 */
public class TransferStudent {
	
	private Student student;
	private Date transferDate;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	

}
