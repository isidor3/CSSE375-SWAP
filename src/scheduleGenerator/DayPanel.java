package scheduleGenerator;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class DayPanel extends JPanel{
	
	private Config config;
	private JCheckBox dayCheck;
	private int dayInt;
	private JList dayJobList;
	private JScrollPane dayScrollPane;
	private JTextField dayJobName;
	private JButton dayAddJob;
	private JButton dayDeleteJob;
	private JLabel dayLabel;
	private String day;
	
	// SWAP 3, TEAM 7
	// ENHANCEMENT FROM REFACTORING
	// Added a button to each DayPanel to allow for changing the background color.
	// The refactorings done for this enhancement did allow for easy editing of the
	// colors within the Config panels.  However, the refactorings were either not
	// complete enough, or did not require changing the data structure used for
	// generating the schedule itself, so there is no easy way of changing the color
	// of each day to match the color selected in Config.  As for adding value to
	// the system, if this enhancement could be fully implemented, it could make for
	// easier reading of the schedule, through highlighting of specific days. 
	private JButton changeColor;
	
	public DayPanel(String daySet, Config configSet, int intSet, JList listSet, JCheckBox dayCheckSet, JScrollPane scrollSet,
			JTextField textSet, JButton addSet, JButton deleteSet, JLabel labelSet)
	{
		this.config = configSet;
		this.day = daySet;
		this.dayInt = intSet;
		this.dayJobList = listSet;
		this.dayCheck = new JCheckBox();
		this.dayScrollPane = scrollSet;
		this.dayJobName = textSet;
		this.dayAddJob = addSet;
		this.dayDeleteJob = deleteSet;
		this.dayLabel = labelSet;
		
		// SWAP 3, TEAM 7
		// ENHANCEMENT FROM REFACTORING
		// Added this button to allow for changing background colors.
		this.changeColor = new JButton();
	}
	
	
	
	public void displayPanel()
	{
		this.dayCheck.setSelected(!this.dayCheck.isSelected());
		if(this.dayCheck.isSelected()) {
            config.numSelected++;
            if(config.firstSelection) {
                config.stretch();
            }
            config.models[dayInt] = new DefaultListModel<Object>();
            this.dayJobList.setModel(config.models[this.dayInt]);
            this.dayScrollPane.setViewportView(this.dayJobList);

            this.dayJobName.setColumns(20);

            this.dayLabel.setText("Job Name:");

            this.dayAddJob.setText("Add Job");
            this.dayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!DayPanel.this.dayJobName.getText().isEmpty()) {
                        DayPanel.this.config.models[DayPanel.this.dayInt].addElement(DayPanel.this.dayJobName.getText());
                        DayPanel.this.dayJobList.setModel(DayPanel.this.config.models[DayPanel.this.dayInt]);
                        DayPanel.this.dayJobName.setText("");
                    }
                }
            });

            this.dayDeleteJob.setText("Delete Job");
            this.dayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!DayPanel.this.dayJobList.isSelectionEmpty()) {
                        int n = DayPanel.this.dayJobList.getSelectedIndex();
                        DayPanel.this.config.models[DayPanel.this.dayInt].remove(n);
                    }
                    
                }
            });
            
            // SWAP 3, TEAM 7
            // ENHANCEMENT FROM REFACTORING
            // Added the following button to allow for changing the display color for each day of the week.
            this.changeColor.setText("Change Color");
            this.changeColor.addActionListener(new java.awt.event.ActionListener() {
            	@Override
            	public void actionPerformed(java.awt.event.ActionEvent evt) {
            		DayPanel.this.setBackground(JColorChooser.showDialog(getParent(), "Choose Background Color", DayPanel.this.getBackground()));
            		DayPanel.this.repaint();
            	}
            });

            javax.swing.GroupLayout dayTabLayout = new javax.swing.GroupLayout(this);
            this.setLayout(dayTabLayout);
            dayTabLayout.setHorizontalGroup(
                dayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(this.dayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(dayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dayTabLayout.createSequentialGroup()
                            .addComponent(this.dayLabel)
                            .addGroup(dayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(dayTabLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(this.dayAddJob))
                                    
                                // SWAP 3, TEAM 7
                                // ENHANCEMENT FROM REFACTORING
                                // Added the button to the GroupLayout.
                                .addGroup(dayTabLayout.createSequentialGroup()
                                		.addGap(14, 14, 14)
                                		.addComponent(this.changeColor))
                                
                                .addGroup(dayTabLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(this.dayJobName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(this.dayDeleteJob))
                    .addContainerGap(431, Short.MAX_VALUE))
            );
            dayTabLayout.setVerticalGroup(
                dayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(dayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(dayTabLayout.createSequentialGroup()
                            .addGroup(dayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(this.dayJobName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.dayLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(this.dayAddJob)
                            
                            // SWAP 3, TEAM 7
                            // ENHANCEMENT FROM REFACTORING
                            // Added the button to the GroupLayout.
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(this.changeColor)
                            
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(this.dayDeleteJob))
                        .addComponent(this.dayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(25, Short.MAX_VALUE))
            );
            this.config.dayTabs.addTab(day, this);
        } else {
            this.config.numSelected--;
            this.config.stretch();
            this.config.dayTabs.remove(this);
        }

		
	}

}
