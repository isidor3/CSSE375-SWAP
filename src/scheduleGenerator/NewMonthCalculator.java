package scheduleGenerator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * QUALITY CHANGES SWAP 1, TEAM 6
 * 
 * This class was created to help refactor the long method in the Schedule
 * class. This class helps by using fields instead of long parameters lists in
 * the smaller methods.
 * 
 */

/**
 * BONUS FEATURE Swap 1, Team 6
 * 
 * We added the bonus feature that ensures every worker will work at least
 * once before any worker is repeated. Once every worker has been assigned
 * at least once, workers are allowed to repeat.
 * 
 */

/**
 * Additional FEATURE Swap 1, Team 6
 * 
 * We added an additional feature of arbitrarily assigning a worker to any job
 * that is not selected by any worker. This ensures that every job will have a
 * worker.
 * 
 */

public class NewMonthCalculator {

	private GregorianCalendar cal;
	//private ArrayList<Day> days;
	private HashMap<Integer, ArrayList<String>> daysMap;
	private ArrayList<Worker> workers;
	private HashMap<Integer, ArrayList<Worker>> workerIndices;
	private boolean workerForEveryJob = true;
	private TreeMap<String, TreeMap<String, Worker>> schedule;
	private int daysInMonth = 0;
	private ArrayList<Integer> numOfJobs = new ArrayList<Integer>();
	private TreeMap<String, Worker> jobsWithWorker;
	private ArrayList<String> workersWorking;
	private ArrayList<Worker> workersFree;
	private ArrayList<Worker> workersWorked;
	private ArrayList<String> jobsInOrder;

	/*public NewMonthCalculator(GregorianCalendar cal, ArrayList<Day> days,
			ArrayList<Worker> workers,
			HashMap<Integer, ArrayList<Worker>> workerIndices,
			TreeMap<String, TreeMap<String, Worker>> schedule) {
		this.cal = cal;
		this.days = days;
		this.workers = workers;
		this.workerIndices = workerIndices;
		this.schedule = schedule;
	}*/
	
	public NewMonthCalculator(GregorianCalendar cal, HashMap<Integer, ArrayList<String>> days,
			ArrayList<Worker> workers,
			HashMap<Integer, ArrayList<Worker>> workerIndices,
			TreeMap<String, TreeMap<String, Worker>> schedule) {
		this.cal = cal;
		this.daysMap = days;
		this.workers = workers;
		this.workerIndices = workerIndices;
		this.schedule = schedule;
	}

	public boolean isWorkerForEveryJob() {
		return this.workerForEveryJob;
	}

	public int getDaysInMonth() {
		return this.daysInMonth;
	}

	public ArrayList<Integer> getNumOfJobs() {
		return this.numOfJobs;
	}

	public void calculate(int currentMonth) {

		while (currentMonth == this.cal.get(Calendar.MONTH)) {

			//for (Day day : this.days) {
			for (Integer day : this.daysMap.keySet()) {

				/*if (this.cal.get(Calendar.DAY_OF_WEEK) == this.numForName(day
						.getNameOfDay())) {*/
				if (this.cal.get(Calendar.DAY_OF_WEEK) == day) { 
					createDaySchedule(day, this.daysMap.get(day));
					break;
				}

			}
			this.cal.add(Calendar.DATE, 1);
		}
	}

	//private void createDaySchedule(Day day) {
	private void createDaySchedule(Integer day, ArrayList<String> jobs) {

		this.jobsWithWorker = new TreeMap<String, Worker>();
		this.workersWorking = new ArrayList<String>();
		this.workersFree = new ArrayList<Worker>();
		this.workersWorked = new ArrayList<Worker>();
		this.jobsInOrder = this.daysMap.get(day);

		this.daysInMonth++;
		this.numOfJobs.add(this.jobsInOrder.size());
		
		this.cal.set(Calendar.DAY_OF_WEEK, day);

		for (Worker w : this.workers) {
			this.workersFree.add(w);
		}

		for (String job : this.jobsInOrder) {

			ArrayList<Worker> workersForJob = new ArrayList<Worker>();

			sortWorkers(day, job, workersForJob);

			if (workersForJob.size() == 0) {
				assignFreeWorkers(job);
			} else if (workersForJob.size() > 0) {
				assignLeftoverJobs(job, workersForJob);
			} else {
				createEmptyJobMessage(job, this.cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
				break;
			}

		}
		String date = getDateString();
		this.schedule.put(date, this.jobsWithWorker);
	}

	private void sortWorkers(Integer day, String job,
			ArrayList<Worker> workersForJob) {
		for (Worker worker : this.workerIndices.get(day)) {
			//Day workerDay = worker.getDayWithName(day.getNameOfDay());
			if (worker.getJobsOnDay(worker.getDayWithName(this.cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()))).contains(job)
					&& !this.workersWorking.contains(worker.getName())) {
				if (this.workersFree.size() == 0) {
					this.workersFree = this.workersWorked;
					this.workersWorked = new ArrayList<Worker>();
				}

				if (this.workersFree.contains(worker)) {
					workersForJob.add(worker);
					this.workersFree.remove(worker);
					this.workersWorked.add(worker);
				} else {
					workersForJob.add(this.workersFree.get(0));
					this.workersWorked.add(this.workersFree.get(0));
					this.workersFree.remove(0);
				}
			}
		}
	}

	private void assignFreeWorkers(String job) {
		if (this.workersFree.size() == 0) {
			this.workersFree = this.workersWorked;
			this.workersWorked = new ArrayList<Worker>();
		}
		this.jobsWithWorker.put(job, this.workersFree.get(0));
		this.workersWorking.add(this.workersFree.get(0).getName());
		this.workersFree.get(0).addWorkedJob(job);
		this.workersWorked.add(this.workersFree.get(0));
		this.workersFree.remove(0);
	}

	private void assignLeftoverJobs(String job, ArrayList<Worker> workersForJob) {
		Worker workerForJob = workersForJob.get(new Random()
				.nextInt(workersForJob.size()));
		for (Worker w : workersForJob) {
			if (w.numWorkedForJob(job) < workerForJob.numWorkedForJob(job)) {
				workerForJob = w;
			}
		}
		this.jobsWithWorker.put(job, workerForJob);
		this.workersWorking.add(workerForJob.getName());
		workerForJob.addWorkedJob(job);
	}

	private void createEmptyJobMessage(String job, String day) {
		this.jobsWithWorker.put(job, new Worker("Empty", new HashMap<Integer, ArrayList<String>>()));
		JOptionPane.showMessageDialog(
				new JFrame(),
				"No workers are able to work as a(n) " + job + " on "
						+ day);
		this.workerForEveryJob = false;
	}

	private String getDateString() {
		return this.cal.get(Calendar.YEAR) + "/"
				+ String.format("%02d", (this.cal.get(Calendar.MONTH) + 1))
				+ "/"
				+ String.format("%02d", this.cal.get(Calendar.DAY_OF_MONTH));
	}

	/*private int numForName(String nameOfDay) {
		int dayNum = 0;
		if (nameOfDay.equals("Sunday")) {
			dayNum = 1;
		} else if (nameOfDay.equals("Monday")) {
			dayNum = 2;
		} else if (nameOfDay.equals("Tuesday")) {
			dayNum = 3;
		} else if (nameOfDay.equals("Wednesday")) {
			dayNum = 4;
		} else if (nameOfDay.equals("Thursday")) {
			dayNum = 5;
		} else if (nameOfDay.equals("Friday")) {
			dayNum = 6;
		} else if (nameOfDay.equals("Saturday")) {
			dayNum = 7;
		}
		return dayNum;
	}*/

}
