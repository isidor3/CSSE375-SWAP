package scheduleGenerator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Day is used to store jobs for a given day.
 * 
 * SWAP 1, TEAM 6
 * 
 * SMELL: Data class - This class is nothing but getters and setters. There is
 * zero actual functionality in this class. Refactoring this would allow
 * computations that care about this data to actually be in the relevant class.
 * 
 * SMELL: Primitive Obsession - the only fields in this class is a string and an
 * array list of strings. Refactoring this would allow a more helpful class that
 * can actually do calculations based on more relevant fields.
 * 
 * @author schneimd. Created Oct 15, 2012.
 */
public class Day implements Serializable {

	private String dayOfWeek;
	private ArrayList<String> jobs = new ArrayList<String>();

	/**
	 * Construct a day with a name and jobs.
	 * 
	 * @param name
	 * 
	 * @param jobs
	 */
	public Day(String name, ArrayList<Object> jobs) {
		this.dayOfWeek = name;
		for (Object i : jobs) {
			this.jobs.add((String) i);
		}
	}

	/**
	 * Add one jobName.
	 * 
	 * @param jobName
	 */
	public void addJob(String jobName) {
		this.jobs.add(jobName);
	}

	/**
	 * Set jobs to new jobs.
	 * 
	 * @param jobNames
	 */
	public void setJobs(ArrayList<String> jobNames) {
		this.jobs = jobNames;
	}

	/**
	 * return current jobs.
	 * 
	 * @return jobs
	 */
	public ArrayList<String> getJobs() {
		return this.jobs;
	}

	/**
	 * Gives the name of this day.
	 * 
	 * @return day of week
	 */
	public String getNameOfDay() {
		return this.dayOfWeek;
	}
}
