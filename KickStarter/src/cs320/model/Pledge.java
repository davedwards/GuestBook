package cs320.model;

import java.util.ArrayList;
import java.util.List;

public class Pledge {

	String username;
	Integer pledgeID;
	Integer projectID;
	String sponsorID;
	Integer pledgeAmount;
	Integer rewardID;
	Integer totalPledgeAmount;
	
	List<Project> projects = new ArrayList<Project>();
	
	public Pledge(){
	}

	public Pledge( Integer projectID, String sponsorID, 
					Integer pledgeAmount, Integer rewardID )
	{
		this.projectID = projectID;
		this.sponsorID = sponsorID;
		this.pledgeAmount = pledgeAmount;
		this.rewardID = rewardID;
	}
	
	
	
	public Integer getPledgeID() {
		return pledgeID;
	}

	public void setPledgeID(Integer pledgeID) {
		this.pledgeID = pledgeID;
	}

//	public Integer getTotalPledgeAmount(Integer projectID)
//	{
//		for(Project proj : projects)
//		{
//			if(proj.projectID == projectID)
//				totalPledgeAmount += proj.getAmountPledged();
//		}
//		return amountPledged;
//		return totalPledgeAmount;	
//	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getProjectID() {
		return projectID;
	}

	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}

	public Integer getPledgeAmount() {
		return pledgeAmount;
	}

	public void setPledgeAmount(Integer pledgeAmount) {
		this.pledgeAmount = pledgeAmount;
	}

//	public Integer getRewardID() {
//		return rewardID;
//	}
//
//	public void setRewardID(Integer rewardID) {
//		this.rewardID = rewardID;
//	}

	public Integer getTotalPledgeAmount() {
		return totalPledgeAmount;
	}

	public void setTotalPledgeAmount(Integer totalPledgeAmount) {
		this.totalPledgeAmount = totalPledgeAmount;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	
}
