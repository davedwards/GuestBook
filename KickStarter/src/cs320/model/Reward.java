package cs320.model;

public class Reward {
	
	Integer pid;
	Integer rid;
	Integer amount;
	String description;
	
	public Reward(){
	}
	
	public Reward( Integer rid, Integer pid, 
				   Integer amount, String description )
	{
		this.rid = rid;
		this.pid = pid;
		this.amount = amount;
		this.description = description;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}