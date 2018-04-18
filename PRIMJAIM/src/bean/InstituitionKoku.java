package bean;

public class InstituitionKoku {
	private String instituitionKoku;
	private Instituition instituition;
	private Koku koku;
	private int quota;
	
	public int getQuota() {
		return quota;
	}
	public void setQuota(int quota) {
		this.quota = quota;
	}
	public String getInstituitionKokuId() {
		return instituitionKoku;
	}
	public void setInstituitionKokuId(String instituitionKokuId) {
		this.instituitionKoku = instituitionKokuId;
	}
	public Instituition getInstituitionId() {
		return instituition;
	}
	public void setInstituitionId(Instituition instituitionId) {
		this.instituition = instituitionId;
	}
	public Koku getKokuId() {
		return koku;
	}
	public void setKokuId(Koku kokuId) {
		this.koku = kokuId;
	}
	
	
}
