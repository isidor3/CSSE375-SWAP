package scheduleGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Used to store predicted days and generate new days.
 * 
 * @author schneimd. Created Oct 18, 2012.
 */
public class Schedule extends Thread implements Serializable {

	private ArrayList<Worker> workers;
	private ArrayList<Day> days;
	private TreeMap<String, TreeMap<String, Worker>> schedule;
	private GregorianCalendar cal;
	private HashMap<Integer, ArrayList<Worker>> workerIndices;
	private boolean workerForEveryJob = true;

	/**
	 * Used to construct an initial schedule, used if one does not exist.
	 * 
	 * @param daySlots
	 * @param wrks
	 */
	public Schedule(ArrayList<Day> daySlots, ArrayList<Worker> wrks) {
		this.workers = wrks;
		this.days = daySlots;
		this.workerIndices = new HashMap<Integer, ArrayList<Worker>>();
		for (int i = 1; i <= 7; i++) {
			this.workerIndices.put(i, new ArrayList<Worker>());
		}
		this.generateIndices();

		// Key is year/month/day format and item is a hashmap with key nameOfJob
		// and item Worker
		this.schedule = new TreeMap<String, TreeMap<String, Worker>>();

		this.cal = new GregorianCalendar();

		this.calculateNextMonth();
	}

	@Override
	public void run() {
		this.calculateNextMonth();
	}

	/**
	 * returns workers in schedule.
	 * 
	 * @return workers
	 */
	public ArrayList<Worker> getWorkers() {
		return this.workers;
	}

	// SWAP 1 TEAM 7 QUALITY CHANGES
	// This is one of the methods extracted from calculateNextMonth
	private void generateIndices() {
		for (int i = 0; i < this.workers.size(); i++) {
			for (Day day : this.workers.get(i).getDays()) {
				int numDay = this.numForName(day.getNameOfDay());
				this.workerIndices.get(numDay).add(this.workers.get(i));
			}
		}
	}

	//SWAP 1 TEAM 7 QUALITY CHANGES
	//Modify the existing schedule when one already exists
	//Created using extract method
	private synchronized void ammendSchedule() {
		String lastDateMade = this.schedule.lastKey();
		String[] parts = lastDateMade.split("/");
		int year = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]) - 1;
		int day = Integer.parseInt(parts[2]);
		this.cal = new GregorianCalendar(year, month, day);
		int tempNum = this.cal.get(Calendar.MONTH);
		while (tempNum == this.cal.get(Calendar.MONTH)) {
			this.cal.add(Calendar.DATE, 1);
		}
	}

	// SWAP 1, TEAM 07
	// QUALITY CHANGES

	// This method was extracted from calculateNextMonth serving the purpose of
	// finding all available workers for a particular job.
	private synchronized ArrayList<Worker> getWorkersForJob(String nameOfDay,
			ArrayList<String> workersWorking, String job) {
		ArrayList<Worker> workersForJob = new ArrayList<Worker>();

		for (Worker worker : this.workerIndices.get(this.numForName(nameOfDay))) {
			Day workerDay = worker.getDayWithName(nameOfDay);
			if (workerDay.getJobs().contains(job)
					&& !workersWorking.contains(worker.getName())) {
				workersForJob.add(worker);
			}
		}
		return workersForJob;
	}

	// SWAP 1, TEAM 07
	// QUALITY CHANGES

	// This method was extracted from calculateNextMonth serving the purpose of
	// selecting a worker to perform a particular job.
	private synchronized Worker pickWorkerForJob(
			ArrayList<Worker> workersAvailable, String job) {
		Worker workerForJob = workersAvailable.get(new Random()
				.nextInt(workersAvailable.size()));
		for (Worker w : workersAvailable) {
			if (w.numWorkedForJob(job) < workerForJob.numWorkedForJob(job)) {
				workerForJob = w;
			}
		}
		return workerForJob;
	}

	private synchronized ArrayList<Integer> generateDays() {
		
		int currentMonth = this.cal.get(Calendar.MONTH);
		ArrayList<Integer> numOfJobs = new ArrayList<Integer>();

		while (currentMonth == this.cal.get(Calendar.MONTH)) {
			for (Day day : this.days) {
				if (this.cal.get(Calendar.DAY_OF_WEEK) == this.numForName(day
						.getNameOfDay())) {
					TreeMap<String, Worker> jobsWithWorker = new TreeMap<String, Worker>();
					ArrayList<String> workersAlreadyWorking = new ArrayList<String>();
					ArrayList<String> jobsInOrder = day.getJobs();

					numOfJobs.add(jobsInOrder.size());

					for (String job : jobsInOrder) {
						ArrayList<Worker> workersForJob = getWorkersForJob(
								day.getNameOfDay(), workersAlreadyWorking, job);
						if (workersForJob.size() > 0) {
							Worker workerForJob = pickWorkerForJob(
									workersForJob, job);
							jobsWithWorker.put(job, workerForJob);
							workersAlreadyWorking.add(workerForJob.getName());
							workerForJob.addWorkedJob(job);
						} else {
							jobsWithWorker.put(job, new Worker("Empty",
									new ArrayList<Day>()));
							JOptionPane
									.showMessageDialog(
											new JFrame(),
											"No workers are able to work as a(n) "
													+ job + " on "
													+ day.getNameOfDay());
							this.workerForEveryJob = false;
							break;
						}
					}
					String date = genDate();
					this.schedule.put(date, jobsWithWorker);
					break; // Breaks so it doesn't check the other days
				}
			}
			this.cal.add(Calendar.DATE, 1);
		}
		return numOfJobs;
	}

	/**
	 * Calculates another month of schedule based on workers availability.
	 * 
	 */
	private synchronized void calculateNextMonth() {

		int initialSize = this.schedule.size();

		// If the schedule has already been generated
		if (this.schedule.size() > 0)
			ammendSchedule();

		ArrayList<Integer> numOfJobs = generateDays();
		this.cal.add(Calendar.DATE, -1);
		int daysInMonth = this.cal.get(Calendar.DATE);
		
		HTMLGenerator.makeTable(daysInMonth, numOfJobs);
		// Calls itself if there aren't many days generated
		// For instance if the date it was created is the last day of the
		// month it would only makes one day of schedule.
		if (this.schedule.size() - initialSize < 2 && !this.workerForEveryJob) {
			this.calculateNextMonth();
		}

		Main.dumpConfigFile();
	}

	private synchronized String genDate() {
		return this.cal.get(Calendar.YEAR) + "/"
				+ String.format("%02d", (this.cal.get(Calendar.MONTH) + 1))
				+ "/"
				+ String.format("%02d", this.cal.get(Calendar.DAY_OF_MONTH));
	}

	private int numForName(String nameOfDay) {
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
	}

	// /**
	// * Returns the month/day/year of next date with the name of day.
	// *
	// * @param nameOfDay
	// * @return string of year/month/day format
	// */
	// private String getNextDate(String nameOfDay) {
	// int dayNum = numForName(nameOfDay);
	// GregorianCalendar tempCal = (GregorianCalendar) this.cal.clone();
	//
	// tempCal.add(Calendar.DATE, 1);
	// while (tempCal.get(Calendar.DAY_OF_WEEK) != dayNum) {
	// tempCal.add(Calendar.DATE, 1);
	// }
	// return String.valueOf(tempCal.get(Calendar.YEAR)) + "/" +
	// String.valueOf(tempCal.get(Calendar.MONTH)) + "/"
	// + String.valueOf(tempCal.get(Calendar.DAY_OF_MONTH));
	// }

	/**
	 * Returns the schedule.
	 * 
	 * @return HashMap schedule
	 */
	public TreeMap<String, TreeMap<String, Worker>> getSchedule() {
		return this.schedule;
	}

}
