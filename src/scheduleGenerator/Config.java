/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleGenerator;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author schneimd
 */
public class Config extends javax.swing.JFrame {
	
	//SMELL - SWAP 1 TEAM 04 - Duplicate Code - Almost duplicate code is given for each day of the week
	//Removing the duplicate code would let us change the behavior of the checkboxes more easily. We could make them add multiple days
	//at once if we wanted to for some reason, etc.
    // SWAP 2, TEAM 5
    // REFACTORING FOR ENHANCEMENT FROM DUPLICATE CODE SMELL
    // Pulled the repeated portion of the if/else blocks in the for loop of the constructor (line 48)
	// into a new function (line 69). Additionally, refactored the duplicated portions of the initDyn
	// method (line 102) into a new class, weekdayGUIObject, to clarify and shorten the code.
	// These refactors were accomplished using Fowler's Extract Method and Replace Method with Method
	// Object, and will allow for changes to be made to these functions more easily in the future. For
	// instance, you could add a default job listing that should always be listed for every day using
	// the refactored setupJobs method (line 48).
	//SMELL - SWAP 1 TEAM 04 - Shotgun Surgery - If a change in structure or function was to be made to the actions in this class you would need
	//to change it in multiple places
	//Features this allows are centralized changes to the functionality of the configuration window. Right now if you wanted to change
	//the functionality of the text boxes you would need to change the code in almost every method.

    boolean firstSelection = true;
    int numSelected = 0;
    @SuppressWarnings("rawtypes") DefaultListModel[] models;
    
    
    /**
     * Used to edit days.
     *
     * @param days
     */
    public Config(ArrayList<Day> days) {
    	this.models = new DefaultListModel[7];
        initDyn();
        initComponents();
        
    	for(Day day: days) {
    		if(day.getNameOfDay().equals("Sunday")) {
    			this.setupJobs(this.sundayCheck, this.sundayGUIObject, day, 0);
    		} else if(day.getNameOfDay().equals("Monday")) {
    			this.setupJobs(this.mondayCheck, this.mondayGUIObject, day, 1);
    		} else if(day.getNameOfDay().equals("Tuesday")) {
    			this.setupJobs(this.tuesdayCheck, this.tuesdayGUIObject, day, 2);
    		} else if(day.getNameOfDay().equals("Wednesday")) {
    			this.setupJobs(this.wednesdayCheck, this.wednesdayGUIObject, day, 3);
    		} else if(day.getNameOfDay().equals("Thursday")) {
    			this.setupJobs(this.thursdayCheck, this.thursdayGUIObject, day, 4);
    		} else if(day.getNameOfDay().equals("Friday")) {
    			this.setupJobs(this.fridayCheck, this.fridayGUIObject, day, 5);
    		} else if(day.getNameOfDay().equals("Saturday")) {
    			this.setupJobs(this.saturdayCheck, this.saturdayGUIObject, day, 6);
    		}
    	}
    }
    
    @SuppressWarnings("unchecked")
	private void setupJobs(JCheckBox dayCheckBox, weekdayGUIObject dayGUI, Day day, int dayNum) {
		dayCheckBox.doClick();
		ArrayList<String> jobs = day.getJobs();
		for(String job: jobs) {
			this.models[dayNum].addElement(job);
			dayGUI.getJobList().setModel(this.models[dayNum]);
		}
	}

	/**
     * Creates new form.
     */
    public Config() {
        this.models = new DefaultListModel[7];
        initDyn();
        
        initComponents();
    }
    
    @SuppressWarnings("rawtypes")
    //SMELL - SWAP 1 TEAM 04 - Data Clumps - These elements are always used together.
    //The features that could be added if we clumped this into an object would be localized additional buttons and panels.
    //For instance, we could add a time duration for each job that we could show and put that in here.
	// SWAP 2, TEAM 5
    // REFACTORING FOR ENHANCEMENT FROM BAD SMELL
    // To do this refactoring, I extracted the elements of the data clump into their own class.
    // Additionally, I extracted the creation of the dayPanel into a method in this class,
    // as that piece of code was repeated quite often.
    // Because these GUI Objects handle how the panels are displayed,
    // this refactor could enable such additional features as options for
    // how the config is displayed, or add panels for special days, like
    // Christmas or Halloween panels.
    private void initDyn() {
        this.sundayGUIObject = new weekdayGUIObject();
        
        this.mondayGUIObject = new weekdayGUIObject();
        
        this.tuesdayGUIObject = new weekdayGUIObject();
        
        this.wednesdayGUIObject = new weekdayGUIObject();
        
        this.thursdayGUIObject = new weekdayGUIObject();
        
        this.fridayGUIObject = new weekdayGUIObject();
        
        this.saturdayGUIObject = new weekdayGUIObject();
        
        this.mondayGUIObject.createTab("Monday", 1, this.mondayCheck, this);
        this.tuesdayGUIObject.createTab("Tuesday", 2, this.tuesdayCheck, this);
        this.wednesdayGUIObject.createTab("Wednesday", 3, this.wednesdayCheck, this);
        this.thursdayGUIObject.createTab("Thursday", 4, this.thursdayCheck, this);
        this.fridayGUIObject.createTab("Friday", 5, this.fridayCheck, this);
        this.saturdayGUIObject.createTab("Saturday", 6, this.saturdayCheck, this);
        this.sundayGUIObject.createTab("Sunday", 0, this.sundayCheck, this);
        
    }

    private void initComponents() {

    	this.jPanel1 = new javax.swing.JPanel();
        this.sundayCheck = new javax.swing.JCheckBox();
        this.wednesdayCheck = new javax.swing.JCheckBox();
        this.mondayCheck = new javax.swing.JCheckBox();
        this.tuesdayCheck = new javax.swing.JCheckBox();
        this.jLabel1 = new javax.swing.JLabel();
        this.thursdayCheck = new javax.swing.JCheckBox();
        this.fridayCheck = new javax.swing.JCheckBox();
        this.saturdayCheck = new javax.swing.JCheckBox();
        this.nextButton = new javax.swing.JButton();
        this.dayTabs = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configuration");
        setPreferredSize(new java.awt.Dimension(801, 87));
        setResizable(false);
        
        //SWAP 1 - TEAM 04 - Remove Duplication 
        //We create a list of the checkboxes so that we can remove a lot of the duplication,
        //we also relate the text to the CalendarGUI since we have st the names for the days there.
        this.checkBoxes = new ArrayList<JCheckBox>();
        checkBoxes.add(this.sundayCheck);
        checkBoxes.add(this.mondayCheck);
        checkBoxes.add(this.tuesdayCheck);
        checkBoxes.add(this.wednesdayCheck);
        checkBoxes.add(this.thursdayCheck);
        checkBoxes.add(this.fridayCheck);
        checkBoxes.add(this.saturdayCheck);
        
        for (int i=0; i<checkBoxes.size(); i++) {
        	checkBoxes.get(i).setText(CalendarGUI.days[i]);
        }
        
        this.sundayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                sundayCheckActionPerformed(evt);
            }
        });

        this.wednesdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                wednesdayCheckActionPerformed(evt);
            }
        });
        
        this.mondayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                mondayCheckActionPerformed(evt);
            }
        });

        this.tuesdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                tuesdayCheckActionPerformed(evt);
            }
        });

        this.jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        this.jLabel1.setText("Days:");

        this.thursdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                thursdayCheckActionPerformed(evt);
            }
        });

        this.fridayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                fridayCheckActionPerformed(evt);
            }
        });

        this.saturdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                saturdayCheckActionPerformed(evt);
            }
        });

        this.nextButton.setText("Next");
        this.nextButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.jLabel1)
                .addGap(18, 18, 18)
                .addComponent(this.sundayCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.mondayCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.tuesdayCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.wednesdayCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.thursdayCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.fridayCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.saturdayCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(this.nextButton)
                .addGap(78, 78, 78))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(this.sundayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(this.fridayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(this.saturdayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(this.nextButton))
                    .addComponent(this.wednesdayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.tuesdayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(this.jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(this.thursdayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.mondayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
            .addComponent(this.dayTabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(this.dayTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
        );

        this.dayTabs.getAccessibleContext().setAccessibleName("Days Tab");

        pack();
    }// </editor-fold>

    //TEAM 4 SWAP 1 CHANGES. PULLED OUT TO ADDITIONAL CLASS/METHOD
    //By pulling all the references into a new class, we can extend the functionality of all of the checkboxes.
    //Instead of changing all of the individual functions, we can only modify the one class instead.
    // SWAP 2, TEAM 5
    // REFACTORING FOR ENHANCEMENT FROM BAD SMELL
    // Each of these action perform methods had to be changed for the new object.
    /**
	 * @param evt  
	 */
	private void sundayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                            
        this.sundayGUIObject.getTab().displayPanel();
        
    }                                           

    /**
	 * @param evt  
	 */
	private void mondayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                            
        this.mondayGUIObject.getTab().displayPanel();
    }                                           

    /**
	 * @param evt  
	 */
	private void tuesdayCheckActionPerformed(java.awt.event.ActionEvent evt) {
        this.tuesdayGUIObject.getTab().displayPanel();
        }                                            

    /**
	 * @param evt  
	 */
	private void wednesdayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                               
        this.wednesdayGUIObject.getTab().displayPanel();
    }                                              

    /**
	 * @param evt  
	 */
    private void thursdayCheckActionPerformed(java.awt.event.ActionEvent evt) {
        this.thursdayGUIObject.getTab().displayPanel();
    }                                             

    /**
	 * @param evt  
	 */
	private void fridayCheckActionPerformed(java.awt.event.ActionEvent evt) {
        this.fridayGUIObject.getTab().displayPanel();
          }                                           

    /**
	 * @param evt  
	 */
	private void saturdayCheckActionPerformed(java.awt.event.ActionEvent evt) {
        this.saturdayGUIObject.getTab().displayPanel();
          }                                             

    /**
	 * @param evt  
	 */
    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	ArrayList<Day> days = new ArrayList<Day>();
    	//SWAP 1 - TEAM 04 - Removed duplication here by the list we created earlier. Now we can iterate through it and achieve the
    	//same functionality
    	int modelNumber = 0;
    	
    	// SWAP 2, TEAM 05
    	// Major Bugfix
    	// The models weren't being used correctly in this method, so trying to
    	// press the next button caused a null pointer exception.
    	// This was obviously a huge problem, but it's been fixed, so the system can run now.
    	for (JCheckBox box : this.checkBoxes) {
    		if (box.isSelected()) {
    			ArrayList<String> l = new ArrayList<String>();
    			for (Object s : this.models[modelNumber].toArray()) {
    				l.add((String) s);
    			}
            	days.add(new Day(box.getText(),l));
    		}
    		modelNumber++;
    	}
    	if(days.size() > 0) {
    		boolean hasJobs = true;
    		int i = 0;
    		while(hasJobs && i<days.size()) {
    			if(days.get(i).getJobs().size() == 0) {
    				hasJobs = false;
    			}
    			i++;
    		}
    		if(hasJobs) {
		    	Main.setDays(days);
		    	Main.wSet = new WorkerSetup();
		    	Main.toggleWorkerSetup();
		    	Main.config = this;
		    	Main.toggleConfig();
    		} else {
    			JOptionPane.showMessageDialog(this, "You must have at least one job each day.");
    		}
    	} else {
    		JOptionPane.showMessageDialog(this, "You have not added any days.");
    	}
    }
    
    
    void stretch() {
        if(this.numSelected > 0) {
            this.setSize(801, 290);
            this.firstSelection = false;
        } else {
            this.setSize(801, 87);
            this.firstSelection = true;
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
			public void run() {
                new Config().setVisible(true);
            }
        });
    }
    
    private ArrayList<JCheckBox> checkBoxes;
    
    // SWAP 2, TEAM 5
    // REFACTORING FOR ENHANCEMENT FROM BAD SMELL
    // Needed to delete several of the fields in this method,
    // and replace them with the new Object.
    private weekdayGUIObject sundayGUIObject;
    private weekdayGUIObject mondayGUIObject;
    private weekdayGUIObject tuesdayGUIObject;
    private weekdayGUIObject wednesdayGUIObject;
    private weekdayGUIObject thursdayGUIObject;
    private weekdayGUIObject fridayGUIObject;
    private weekdayGUIObject saturdayGUIObject;
    
    javax.swing.JTabbedPane dayTabs;
    private javax.swing.JCheckBox fridayCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox mondayCheck;
    private javax.swing.JButton nextButton;
    private javax.swing.JCheckBox saturdayCheck;
    private javax.swing.JCheckBox sundayCheck;
    private javax.swing.JCheckBox thursdayCheck;
    private javax.swing.JCheckBox tuesdayCheck;
    private javax.swing.JCheckBox wednesdayCheck;
}
