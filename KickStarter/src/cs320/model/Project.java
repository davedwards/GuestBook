package cs320.model;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Comparator;
import java.util.List;

public class Project {// implements Comparable<Project>{
	Integer id;
	String creator;
	String title;
	String description;
	Integer fundTarget;
	Integer amountPledged;
	Integer pledgeAmount = 0;
//	boolean pledgeAmount;
	Integer totalPledgeAmount = 0;
	double percentFunded = 0.0;
	String startDateString;
	Calendar startDate;
	int duration;
	Date date;
	Date endDate;
	Date startDateObject;
	int datesDifference;

	private List<Pledge> pledges = new ArrayList<Pledge>();
	
	Project() {
	}
	
//	 format MM/dd/yyyy, e.g. 01/16/2013
	public Project(Integer id, String creator, String title,
			String description, Integer fundTarget,
			String startDateString, int duration) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.fundTarget = fundTarget;
		this.creator = creator;
		this.startDateString = startDateString;
		this.duration = duration;
	}
	
	public Date getEndDate() {
//		Date dt = new Date();
		Date dt = stringToDate(startDateString
				);
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, duration);
		dt = c.getTime();
		return dt;
	}

//	format MMM dd, yyyy, e.g. Dec 16, 2012
	public String getStartDateString() {
		String strDate = this.startDateString;

		try {
			SimpleDateFormat sdfSource = new SimpleDateFormat("MM/dd/yyyy");
			Date date = sdfSource.parse(strDate);
			SimpleDateFormat sdfDestination = new SimpleDateFormat(
					"MMM dd, yyyy");
			strDate = sdfDestination.format(date);
		} catch (ParseException pe) {
			System.out.println("Parse Exception : " + pe);
		}
		return strDate;
	}
	
	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDateString) throws ParseException {
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
	    cal.setTime(sdf.parse(startDateString));// all done
		this.startDate = startDate;
	}

	public int getDaysToGo() {
		
		int daysDiff = 0;
		try {
			daysDiff = (int) (getDateDiff() / (1000*60*60*24));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return (duration - daysDiff);
	}

	// Current Date - Start Date
	public long getDateDiff() throws ParseException {
		String currentDateString = now();
		Date date_current = stringToDate(currentDateString);
		Date date_start = stringToDate(startDateString);
		long currentDateMilSec = date_current.getTime();
		long startDateMilSec = date_start.getTime();
		return (currentDateMilSec - startDateMilSec);
	}

	public Date stringToDate(String dateInStringFormat){
		try {
//			String str_date = "11-June-07";
			String str_date = dateInStringFormat;
	 DateFormat formatter ;
	  formatter = new SimpleDateFormat("MM/dd/yyyy");
	  date = (Date)formatter.parse(str_date);  
	} catch (ParseException e)
	  {
		System.out.println("Exception :"+e);  
	  }
	return date;
	}

	public static String now() {
	String DATE_FORMAT_NOW = "MM/dd/yyyy";
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	return sdf.format(cal.getTime());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreator() {
		return creator;
	}

	public void setUsername(String creator) {
		this.creator = creator;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFundTarget() {
		
		return fundTarget;
	}

	public void setPercentageFunded(Integer percentFunded) {
		
		this.percentFunded = percentFunded;
	}

	public double getPercentFunded() {
		percentFunded = ( (double)getTotalPledgeAmount() / 
				(double)getFundTarget() ) * 100;
		return percentFunded; 
	}

	public void setAmountPledged(Integer amountPledged) {
		this.amountPledged = amountPledged;
	}

	public void setTotalPledgeAmount()
	{
		this.totalPledgeAmount=0;
		for(Pledge pledge : getPledges())
			totalPledgeAmount += pledge.pledgeAmount;
		//this.totalPledgeAmount = totalPledgeAmount;
	}
	
	public Integer getTotalPledgeAmount() {
		
//		for(Pledge pledge : this.pledges)
		return this.totalPledgeAmount;
	}
		
	public void setFundTarget(Integer fundTarget) {
		this.fundTarget = fundTarget;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int compareTo(Project compareProject) {
		 
//		Date compareEndDate = ((Project) compareProject).getEndDate(); 
		long thisProjectEndDate = this.getEndDate().getTime();
		long compareProjectEndDate = compareProject.getEndDate().getTime();
		//ascending order
		long dateDiff = thisProjectEndDate - compareProjectEndDate;
		return ((int) dateDiff / (1000*60*60*24));
 
	}
 
	public List<Pledge> getPledges() {
		return pledges;
	}

	public void setPledges(List<Pledge> pledges) {
		this.pledges = pledges;
	}

	public static Comparator<Project> EndDateComparator = new Comparator<Project>() {
 
	    public int compare(Project project1, Project project2) {
 
	      Date projectEndDate1 = project1.getEndDate();
	      Date projectEndDate2 = project2.getEndDate();
 
	      //ascending order
	      return projectEndDate1.compareTo(projectEndDate2);
 
	      //descending order
	      //return projectEndDate2.compareTo(projectEndDate1);
	    }
 
	};

	
}