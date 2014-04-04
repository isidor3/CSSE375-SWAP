package scheduleGenerator;

import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author schneimd
 */
public class CalendarGUI extends javax.swing.JFrame {
	//SMELL - SWAP 1 TEAM 04 - Divergent Change - Any major change to the functionality of the system will impact this class.
	//Features that could be added if this fix are not really limited. We could add a system which displays more than one month at a time
	//or we could display different panels for each job.
	//SMELL - SWAP 1 TEAM 04 - Large Class - This class contains a lot of the logic for the gui in addition to GUI components. Seems like this could be extracted
	//This is the same type of feature change as a above. The code would become overall a lot cleaner if this was fixed.
	//SWAP 1 Team 4 Change 1
	//The extremely duplicated code in the switch statements is pulled into a static constant list of names. This way it doesn't need to use the case statement anymore.
	//and removes the switch statements.
	//These duplicated code changes allow us to create an internationalizer that changes the names for the days of the week and months of the year.
	//SMELL - SWAP 1 TEAM 04 - Switch Statements - switch statements were used to convert integers to strings. These have been fixed already. They let us change
	//the names of things without changing multiple sections of code.
	public static String[] months = {"January ","February ","March ","April ","May ","June ","July ","August ","September ","October ","November ","December "};
	public static String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	//END Change 1
	private Schedule schedule;
	private GregorianCalendar cal;
	private TreeMap<String, TreeMap<String, Worker>> scheduleMap;
	private int currentMonth;
	private String monthName;
	//SWAP 1 Team Change 2
	//remove an unused variable.
	private int earliestYear, earliestMonth;
	//END Change 2
	private int monthsAhead = 0;
	private int yearsAhead = 0;

	/**
	 * Creates new form Calendar
	 * 
	 * @param schd
	 */
	public CalendarGUI(Schedule schd) {
		this.schedule = schd;
		this.scheduleMap = this.schedule.getSchedule();
		String[] earliest = this.scheduleMap.firstKey().split("/");
		this.earliestYear = Integer.parseInt(earliest[0]);
		this.earliestMonth = Integer.parseInt(earliest[1]);
		//SWAP 1 Team 4 Change 3
		//End Change 3
		this.cal = new GregorianCalendar();
		initComponents();
		this.fillTableForThisMonth();
	}

	private void setTitleMonth(int n, int year) {
		//Swap 1 Team 4 Change 2
		try {
			// SWAP 2, TEAM 5
			// FURTHER ELABORATION ON ADDITIONAL FEATURE
			// As well as displaying the month at a uniform length, there is
			// now also a checkbox to show the number of workers in the title.
			// This was relatively simple to implement now that the important
			// locations in the code that needed changing have been cleaned up.
			// All it took was creating the checkbox, a listener to update the
			// title, and the check in this function.
			String text = months[n-1]+year;
			if (this.showWorkersBox.isSelected()) {
				text = text+", "+Main.getWorkers().size()+" Workers";
			}
			this.monthTitle.setText(text);
			
			
			//SWAP 1 TEAM 4 ADDITIONAL FEATURE. FIXED LENGTH ON THE TEXT
			for(int x = this.monthTitle.getText().length(); x < 25; x++)
			{
				this.monthTitle.setText(this.monthTitle.getText() + " ");
			}
		
			this.monthName = months[n-1] + year;
		}
		catch (Exception e) {
			//this should never happen, but as we didn't write the code, keep it from crashing on out of bounds error.
			e.printStackTrace();
		}
		// Removed switch statement
		// End Change 2
	}

	/**
	 * Displays the calendar for the current month based on the computers month.
	 * 
	 */
	public void fillTableForThisMonth() {
		int currentYear = new GregorianCalendar().get(Calendar.YEAR);
		this.currentMonth = new GregorianCalendar().get(Calendar.MONTH) + 1;
		this.setTitleMonth(this.currentMonth, currentYear);
		this.monthsAhead = 0;
		this.yearsAhead = 0;

		//SWAP 1 TEAM 4 CHANGE: Removes Code into a Method
		this.GenerateCalendar(currentYear, this.currentMonth);
	}

	/**
	 * Displays the next month from current month.
	 * 
	 */
	public void fillTableMonthAhead() {
		int currentYear = new GregorianCalendar().get(Calendar.YEAR);
		this.monthsAhead++;
		int showMonth = new GregorianCalendar().get(Calendar.MONTH)
				+ this.monthsAhead + 1;
		this.yearsAhead = 0;
		while (showMonth > 12) {
			currentYear++;
			showMonth -= 12;
			this.yearsAhead++;
		}
		this.setTitleMonth(showMonth, currentYear);
		//SWAP 1 TEAM 4 CHANGE: Removes code into a method.
		this.GenerateCalendar(currentYear, showMonth);
	}

	/**
	 * Displays the last months from current month.
	 * 
	 */
	public void fillTableMonthBack() {
		int tempMonths = this.monthsAhead;
		if ((new GregorianCalendar().get(Calendar.MONTH) + tempMonths) % 12 == 0) {
			this.yearsAhead--;
		}
		int currentYear = new GregorianCalendar().get(Calendar.YEAR)
				+ this.yearsAhead;
		this.monthsAhead--;
		int monthsToAdd = this.monthsAhead;
		while (monthsToAdd < -11) {
			monthsToAdd += 12;
			currentYear--;
			this.yearsAhead--;
		}
		int showMonth = new GregorianCalendar().get(Calendar.MONTH)
				+ monthsToAdd + 1;

		while (showMonth > 12) {
			showMonth -= 12;
		}

		if (currentYear < this.earliestYear
				|| (currentYear == this.earliestYear && showMonth < this.earliestMonth)) {
			this.monthsAhead++;

		} else {
			this.setTitleMonth(showMonth, currentYear);

			//SWAP1 TEAM 4: Removes Code into a Method
			this.GenerateCalendar(currentYear, showMonth);
		}

	}
	
	//SWAP 1 TEAM 4 CHANGE 4
	//Refactors a longer statement into it's own method.
	//SMELL - SWAP 1 TEAM 04 - Long Method - A lot of smaller statments in this code can be extracted into their own methods.
	//Features this would allow are more clearly separated responsibilties, we could change the formatting of the strings without
	//digging through the code to find every one.
	// SWAP 2, TEAM 5
	// REFACTORING FOR ENHANCEMENT FROM BAD SMELL
	// Pulled out a large chunk of the function that all had to do with populating
	// the table using Extract Method and made a call to a new function,
	// populateScheduleTable (line 204).
	// With this functionality more clearly separated it is easier to find in order
	// to add features. For instance, a new parameter could be added to the function
	// so that it takes in the name of a worker and only displays the days that particular
	// worker is on the job.
	// SWAP 3, TEAM 7
	// ENHANCEMENT FROM REFACTORING
	// Here, we have code that will only show the jobs for the currently selected
	// worker. It is exactly as useful as team 5 suggested, and just as easy to 
	// implement. The refactoring was very helpful to enabling this feature.
	// The refactoring adds value to the product by making complex schedules
	// easy to view without permanently changing the schedule.
	public void GenerateCalendar(int currentYear, int showMonth)
	{
		String keyStart = currentYear + "/" + String.format("%02d", showMonth);
		String currentKey = "";
		// Generates calendar for current month if none exists
		while (currentKey.equals("")) {
			Set<String> keys = this.scheduleMap.keySet();
			for (String key : keys) {
				if (key.startsWith(keyStart)) {
					currentKey = key;
					break;
				}
			}
			if (currentKey.equals("")) {
				Thread t = new Thread(this.schedule);
				t.start();
				//this.schedule.calculateNextMonth();
			}
		}
		
		DefaultTableModel table = new DefaultTableModel(new Object[0][0],
				new String[0][0]);
		this.cal = new GregorianCalendar(currentYear, showMonth - 1, 1);

		this.populateScheduleTable(showMonth, table);
		
		HTMLGenerator.addMonth(this.monthName, table);
		this.scheduleTable.setModel(table);
	}
	
	private void populateScheduleTable(int showMonth, DefaultTableModel table) {
		while (this.cal.get(Calendar.MONTH) + 1 == showMonth) {
			String tempKey = this.cal.get(Calendar.YEAR)
					+ "/"
					+ String.format("%02d", (this.cal.get(Calendar.MONTH) + 1))
					+ "/"
					+ String.format("%02d", this.cal.get(Calendar.DAY_OF_MONTH));
			if (this.scheduleMap.containsKey(tempKey)) {
				int numOfJobs = this.scheduleMap.get(tempKey).size();
				String[] colData = new String[numOfJobs];
				int i = 0;
				for (String key : this.scheduleMap.get(tempKey).keySet()) {
					if(this.selectedWorker == this.scheduleMap.get(tempKey).get(key) || this.selectedWorker == null){
						colData[i] = key + ": "
								+ this.scheduleMap.get(tempKey).get(key).getName();
					}
					i++;
				}
				String numDate = String.format("%02d",
						(this.cal.get(Calendar.MONTH) + 1))
						+ "/"
						+ String.format("%02d",
								this.cal.get(Calendar.DAY_OF_MONTH))
						+ "/"
						+ this.cal.get(Calendar.YEAR);
				String colTitle = this.getNameforNum(this.cal
						.get(Calendar.DAY_OF_WEEK)) + " (" + numDate + ")";
				table.addColumn(colTitle, colData);
			}
			this.cal.add(Calendar.DATE, 1);
		}
	}

	//END CHANGE 4
	private String getNameforNum(int n) {
		//SWAP 1 Team 4 Change 3
		//This allows for custom names for the days of the week. Since it is loaded as a constant we could internationalize
		//by creating an internationalizer that would load different names for the day of the week.
		try {
			return days[n-1];
		}
		catch(Exception e) {
			return null;
		}
		//Removed switch statement
		//End Change 3
	}

	private void initComponents() {

		this.monthTitle = new javax.swing.JLabel();
		this.previousMonthButton = new javax.swing.JButton();
		this.nextMonthButton = new javax.swing.JButton();
		this.jScrollPane1 = new javax.swing.JScrollPane();
		this.scheduleTable = new javax.swing.JTable();
		this.popup = new javax.swing.JPopupMenu();
		this.menuBar = new javax.swing.JMenuBar();
		this.fileMenu = new javax.swing.JMenu();
		this.saveChanges = new javax.swing.JMenuItem();
		this.undoChanges = new javax.swing.JMenuItem();
		this.editMenu = new javax.swing.JMenu();
		this.editWorkers = new javax.swing.JMenuItem();
		this.editDays = new javax.swing.JMenuItem();
		this.generateMenu = new javax.swing.JMenu();
		this.genHtml = new javax.swing.JMenuItem();
		this.generateText = new javax.swing.JMenuItem();
		this.showWorkersBox = new javax.swing.JCheckBox();
		this.workersMenu = new javax.swing.JMenu();
		this.selectedWorker = null;
		this.workers = new ArrayList<javax.swing.JMenuItem>();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Calendar");

		this.monthTitle.setFont(new java.awt.Font("Monospaced", 1, 24));
		this.monthTitle.setText("Month Name Here");
		this.monthTitle.setPreferredSize(new Dimension(100, 25));

		this.previousMonthButton.setText("<");
		this.previousMonthButton
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						previousMonthActionPerformed(evt);
					}
				});

		this.nextMonthButton.setText(">");
		this.nextMonthButton
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						nextMonthActionPerformed(evt);
					}
				});
		
		this.showWorkersBox.setText("Show number of workers");
		this.showWorkersBox
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						showWorkersActionPerformed(evt);
					}
				});

		this.scheduleTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null } },
				new String[] { "Monday (10/22/2012)", "Wednesday (10/24/12)",
						"Thursday (10/26/12)" }));
		this.scheduleTable.setColumnSelectionAllowed(true);
		this.scheduleTable.getTableHeader().setReorderingAllowed(false);
		
		for(Worker i:this.schedule.getWorkers())
		{
			final Worker input = i;
			this.popup.add(new JMenuItem(input.getName())).addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					editCell(input);
				}
			});
			this.workers.add(new JMenuItem(input.getName()));
			this.workers.get(this.workers.size()-1).addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					selectWorker(input);
				}
			});
		}
		
		for(JMenuItem i: this.workers) {
			this.workersMenu.add(i);
		}
		
		this.scheduleTable.setComponentPopupMenu(this.popup);
		
		this.jScrollPane1.setViewportView(this.scheduleTable);

		this.fileMenu.setText("File");
		
		this.workersMenu.setText("Workers");

		this.saveChanges.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S,
				java.awt.event.InputEvent.CTRL_MASK));
		this.saveChanges.setText("Save Changes");
		this.saveChanges.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveChangesActionPerformed(evt);
			}
		});
		this.fileMenu.add(this.saveChanges);

		this.undoChanges.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Z,
				java.awt.event.InputEvent.CTRL_MASK));
		this.undoChanges.setText("Undo Changes");
		this.undoChanges.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				undoChangesActionPerformed(evt);
			}
		});
		//this.fileMenu.add(this.undoChanges);

		this.menuBar.add(this.fileMenu);

		this.editMenu.setText("Edit");

		this.editWorkers.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_W,
				java.awt.event.InputEvent.CTRL_MASK));
		this.editWorkers.setText("Edit Workers");
		this.editWorkers.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editWorkersActionPerformed(evt);
			}
		});
		this.editMenu.add(this.editWorkers);

		this.editDays.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_D,
				java.awt.event.InputEvent.CTRL_MASK));
		this.editDays.setText("Edit Days");
		this.editDays.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editDaysActionPerformed(evt);
			}
		});
		this.editMenu.add(this.editDays);

		this.menuBar.add(this.editMenu);

		this.generateMenu.setText("Generate");

		this.genHtml.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_H,
				java.awt.event.InputEvent.CTRL_MASK));
		this.genHtml.setText("Generate Web Page");
		this.genHtml.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				genHtmlActionPerformed(evt);
			}
		});
		this.generateMenu.add(this.genHtml);

		this.generateText.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_T,
				java.awt.event.InputEvent.CTRL_MASK));
		this.generateText.setText("Generate Text");
		this.generateText
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						generateTextActionPerformed(evt);
					}
				});
		this.generateMenu.add(this.generateText);

		this.menuBar.add(this.generateMenu);
		this.menuBar.add(this.workersMenu);

		setJMenuBar(this.menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(this.jScrollPane1,
						javax.swing.GroupLayout.DEFAULT_SIZE, 1002,
						Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(this.previousMonthButton)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.monthTitle)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.nextMonthButton)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.showWorkersBox)
								.addGap(0, 0, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap(18, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														this.monthTitle,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														this.previousMonthButton,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														this.nextMonthButton,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														29,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														this.showWorkersBox,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(this.jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										265,
										javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}
	
	private void selectWorker(Worker input) {
		if(this.selectedWorker == input) this.selectedWorker = null;
		else this.selectedWorker = input;
		this.fillTableForThisMonth();
	}

	/**
	 * @param evt
	 */
	private void editWorkersActionPerformed(java.awt.event.ActionEvent evt) {
		Main.wSet = new WorkerSetup(this.schedule.getWorkers());
		Main.toggleWorkerSetup();
		Main.toggleCalendar();
	}

	/**
	 * @param evt
	 */
	private void editDaysActionPerformed(java.awt.event.ActionEvent evt) {
		Main.config = new Config(Main.getDays());
		Main.toggleConfig();
		Main.toggleCalendar();
	}

	/**
	 * @param evt
	 */
	private void previousMonthActionPerformed(java.awt.event.ActionEvent evt) {
		this.fillTableMonthBack();
	}

	/**
	 * @param evt
	 */
	private void nextMonthActionPerformed(java.awt.event.ActionEvent evt) {
		this.fillTableMonthAhead();
	}

	/**
	 * @param evt
	 */
	private void showWorkersActionPerformed(java.awt.event.ActionEvent evt) {
		this.fillTableMonthAhead();
		this.fillTableMonthBack();
	}

	/**
	 * @param evt
	 */
	private void genHtmlActionPerformed(java.awt.event.ActionEvent evt) {
		HTMLGenerator.writeHtml();
	}

	/**
	 * @param evt
	 */
	private void generateTextActionPerformed(java.awt.event.ActionEvent evt) {
		NavigableSet<String> keySet = this.scheduleMap.navigableKeySet();
		String textOutput = new String();
		File readout = new File("Calendar.txt");
		ArrayList<String> dutyRows = new ArrayList<String>();

		int column = 1;
		for (String i : keySet) {
			textOutput += String.format("%-30s", "|" + i);
			NavigableSet<String> valueSet = this.scheduleMap.get(i)
					.navigableKeySet();
			int row = 0;
			for (String j : valueSet) {
				if (dutyRows.size() <= row)
					dutyRows.add("");
				String newCol = dutyRows.get(row) + "|" + j + ": "
						+ this.scheduleMap.get(i).get(j).getName();

				dutyRows.set(row,
						String.format("%-" + 30 * column + "s", newCol));
				row += 1;
			}
			column += 1;
		}

		for (String i : dutyRows) {
			textOutput += "\n" + i;
		}
		
		char[] letterOutput = textOutput.toCharArray();

		try {
			readout.createNewFile();

			FileWriter outFile = new FileWriter(readout);
			for(char i:letterOutput)
				outFile.write(i);
			outFile.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * @param evt
	 */
	private void saveChangesActionPerformed(java.awt.event.ActionEvent evt) {
		Main.dumpConfigFile();
	}

	/**
	 * @param evt
	 */
	private void undoChangesActionPerformed(java.awt.event.ActionEvent evt) {
		// removed
	}
	
	private void editCell(Worker input)
	{
		int i = this.scheduleTable.getSelectedRow();
		int j = this.scheduleTable.getSelectedColumn();
		if(this.scheduleTable.getValueAt(i,j) != null)
		{
			System.out.println(this.scheduleTable.getColumnName(j));
			String job = this.scheduleTable.getValueAt(i,j).toString().split(":")[0];
			String date = this.scheduleTable.getColumnName(j).split(" ")[1];
			date = date.substring(1,date.length()-1);
			String[] dateNums = date.split("/");
			date = dateNums[2] + "/" + dateNums[0] + "/" + dateNums[1];
			System.out.println(date);
			this.scheduleMap.get(date).put(job,input);
			this.scheduleTable.setValueAt(job + ": " + input.getName(),i,j);
		}
	}

	private javax.swing.JMenuItem editDays;
	private javax.swing.JMenu editMenu;
	private javax.swing.JMenuItem editWorkers;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenuItem genHtml;
	private javax.swing.JMenu generateMenu;
	private javax.swing.JMenuItem generateText;
	private javax.swing.JMenu workersMenu;
	private ArrayList<javax.swing.JMenuItem> workers;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JLabel monthTitle;
	private javax.swing.JButton nextMonthButton;
	private javax.swing.JPopupMenu popup;
	private javax.swing.JButton previousMonthButton;
	private javax.swing.JMenuItem saveChanges;
	private javax.swing.JTable scheduleTable;
	private javax.swing.JMenuItem undoChanges;
	private javax.swing.JCheckBox showWorkersBox;
	private Worker selectedWorker;
}
