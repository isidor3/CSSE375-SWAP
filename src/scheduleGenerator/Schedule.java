package scheduleGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Used to store predicted days and generate new days.
 * 
 * @author schneimd. Created Oct 18, 2012.
 */
public class Schedule extends Thread implements Serializable {

	private ArrayList<Worker> workers;

	// SWAP 2, TEAM 7
	// REFACTORING FOR ENHANCEMENT FROM BAD SMELL
	/* The field below was removed as part of the removal of the Day class. */
	// private ArrayList<Day> days;

	private HashMap<Integer, ArrayList<String>> daysMap;
	private TreeMap<String, TreeMap<String, Worker>> schedule;
	private GregorianCalendar cal;
	private HashMap<Integer, ArrayList<Worker>> workerIndices;

	// SWAP 2, TEAM7
	// REFACTORING FOR ENHANCEMENT FROM BAD SMELL
	/*
	 * The constructor below was replaced by the constructor below it as part of
	 * the removal of the Day class.
	 */
	/**
	 * Used to construct an initial schedule, used if one does not exist.
	 * 
	 * @param daySlots
	 * @param wrks
	 */
	/*
	 * public Schedule(ArrayList<Day> daySlots, ArrayList<Worker> wrks) {
	 * this.workers = wrks; this.days = daySlots; this.workerIndices = new
	 * HashMap<Integer, ArrayList<Worker>>(); for (int i = 1; i <= 7; i++) {
	 * this.workerIndices.put(i, new ArrayList<Worker>()); }
	 * this.generateIndices();
	 * 
	 * // Key is year/month/day format and item is a hashmap with key nameOfJob
	 * // and item Worker this.schedule = new TreeMap<String, TreeMap<String,
	 * Worker>>();
	 * 
	 * this.cal = new GregorianCalendar();
	 * 
	 * this.calculateNextMonth(); }
	 */

	/**
	 * Used to construct an initial schedule, used if one does not exist.
	 * 
	 * @param daySlots
	 * @param wrks
	 */
	public Schedule(HashMap<Integer, ArrayList<String>> daySlots,
			ArrayList<Worker> wrks) {
		this.workers = wrks;
		this.daysMap = daySlots;
		this.workerIndices = new HashMap<Integer, ArrayList<Worker>>();
		/*for (int i = 1; i <= 7; i++) {
			this.workerIndices.put(i, new ArrayList<Worker>());
		}*/
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

	private void generateIndices() {
		for (int i = 0; i < this.workers.size(); i++) {
			for (Integer day : this.workers.get(i).getDays()) {
				// int numDay = this.numForName(day.getNameOfDay());
				if (!this.workerIndices.containsKey(day)) {
					this.workerIndices.put(day, new ArrayList<Worker>());
				}
				this.workerIndices.get(day).add(this.workers.get(i));
			}
		}
	}

	/*
	 * BONUS FEATURE Swap 1, Team 6
	 * 
	 * We added the bonus feature that ensures every worker will work at least
	 * once before any worker is repeated. Once every worker has been assigned
	 * at least once, workers are allowed to repeat.
	 */

	/*
	 * Additional FEATURE Swap 1, Team 6
	 * 
	 * We added an additional feature of arbitrarily assigning a worker to any
	 * job that is not selected by any worker. This ensures that every job will
	 * have a worker.
	 */

	/*
	 * QUALITY CHANGES SWAP 1, TEAM 6
	 * 
	 * Broke up the long method calculateNextMonth() with the following new
	 * method resetMonthOfPreviousSchedule() and with the new helper class
	 * NewMonthCalculator. The new class uses fields instead of many temporary
	 * variables. With fields, it is much easier to create smaller methods that
	 * don't have long parameter lists.
	 * 
	 * By breaking this method into smaller pieces, then other methods will be
	 * able to use it the smaller pieces to implement new functionality.
	 */

	/**
	 * Calculates another month of schedule based on workers availability.
	 * 
	 */
	private synchronized void calculateNextMonth() {

		int initialSize = this.schedule.size();

		resetMonthOfPreviousSchedule();

		NewMonthCalculator calculator = new NewMonthCalculator(this.cal,
				this.daysMap, this.workers, this.workerIndices, this.schedule);

		int currentMonth = this.cal.get(Calendar.MONTH);
		calculator.calculate(currentMonth);

		HTMLGenerator.makeTable(calculator.getDaysInMonth(),
				calculator.getNumOfJobs());

		// Calls itself if there aren't many days generated
		// For instance if the date it was created is the last day of the
		// month it would only makes one day of schedule.
		if (this.schedule.size() - initialSize < 2
				&& !calculator.isWorkerForEveryJob()) {
			this.calculateNextMonth();
		}

		Main.dumpConfigFile();
	}

	/**
	 * QUALITY CHANGES SWAP 1, TEAM 6
	 */
	private void resetMonthOfPreviousSchedule() {
		if (!this.schedule.isEmpty()) {
			this.cal.set(Calendar.DAY_OF_MONTH, 1);
		}
	}

	/*
	 * SWAP 1, TEAM 6
	 * 
	 * SMELL: Switch Statements - an assortment of if cases shows that the
	 * abstraction was not thoroughly thought out. Refactoring this would allow
	 * a cleaner use of this method and less of a need to for other switch
	 * statements.
	 */
	/*
	 * private int numForName(String nameOfDay) { int dayNum = 0; if
	 * (nameOfDay.equals("Sunday")) { dayNum = 1; } else if
	 * (nameOfDay.equals("Monday")) { dayNum = 2; } else if
	 * (nameOfDay.equals("Tuesday")) { dayNum = 3; } else if
	 * (nameOfDay.equals("Wednesday")) { dayNum = 4; } else if
	 * (nameOfDay.equals("Thursday")) { dayNum = 5; } else if
	 * (nameOfDay.equals("Friday")) { dayNum = 6; } else if
	 * (nameOfDay.equals("Saturday")) { dayNum = 7; } return dayNum; }
	 */

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