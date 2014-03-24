/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleGenerator;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author schneimd
 */
public class Config extends javax.swing.JFrame {

    private boolean firstSelection = true;
    private int numSelected = 0;
	private DefaultListModel[] models;
    
    
    /**
     * Used to edit days.
     *
     * @param days
     */
	public Config(ArrayList<Day> days) {
    	this.models = new DefaultListModel[7];
        initDyn();
        initComponents();
        
        //SWAP 1, TEAM 7 - QUALITY CHANGES - Removed duplication
        //By using the arrays, we can avoid having to have separate
        //code for each item.
        for(Day day: days) {
        	String weekdayName = day.getNameOfDay(); 
        	int dayIndex = WEEK_INDICIES.valueOf(weekdayName).ordinal();
	        this.weekCheck[dayIndex].doClick();
	        List<String> jobs = day.getJobs();
	        for(String job: jobs) {
	        	this.models[dayIndex].addElement(job);
	        	this.weekJobList[dayIndex].setModel(this.models[dayIndex]);
	        }
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
    
    //SWAP 1, TEAM 7 - QUALITY CHANGES - Removed duplication
    //By using the arrays, rather than individual indexes, we
    //can  avoid having a ton of duplicated code
	private void initDyn() {
		for(int i = 0; i < this.weekTab.length; i++) {
			 this.weekScrollPane[i] = new JScrollPane();
		     this.weekScrollPane[i].setPreferredSize(new Dimension(185,150));
		     this.weekJobList[i] = new JList();
		     this.weekJobName[i] = new JTextField();
		     this.weekLabel[i] = new JLabel();
		     this.weekAddJob[i] = new JButton();
		     this.weekDeleteJob[i] = new JButton();
		     this.weekTab[i] = new javax.swing.JPanel();
		}
    }

	
    private void initComponents() {

    	this.jPanel1 = new javax.swing.JPanel();
        this.weekCheck[0] = new javax.swing.JCheckBox();
        this.weekCheck[3] = new javax.swing.JCheckBox();
        this.weekCheck[1] = new javax.swing.JCheckBox();
        this.weekCheck[2] = new javax.swing.JCheckBox();
        this.jLabel1 = new javax.swing.JLabel();
        this.weekCheck[4] = new javax.swing.JCheckBox();
        this.weekCheck[5] = new javax.swing.JCheckBox();
        this.weekCheck[6] = new javax.swing.JCheckBox();
        this.nextButton = new javax.swing.JButton();
        this.dayTabs = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configuration");
        setPreferredSize(new java.awt.Dimension(801, 87));
        setResizable(false);

        this.weekCheck[0].setText("Sunday");
        this.weekCheck[0].setName("weekCheck[0]"); // NOI18N
        this.weekCheck[0].addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                sundayActionPerformed(evt);
            }
        });

        this.weekCheck[3].setText("Wednesday");
        this.weekCheck[3].setName("weekCheck[3]"); // NOI18N
        this.weekCheck[3].addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                wednesdayActionPerformed(evt);
            }
        });

        this.weekCheck[1].setText("Monday");
        this.weekCheck[1].setName("weekCheck[1]"); // NOI18N
        this.weekCheck[1].addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                mondayActionPerformed(evt);
            }
        });

        this.weekCheck[2].setText("Tuesday");
        this.weekCheck[2].setName("weekCheck[2]"); // NOI18N
        this.weekCheck[2].addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                teusdayActionPerformed(evt);
            }
        });

        this.jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        this.jLabel1.setText("Days:");

        this.weekCheck[4].setText("Thursday");
        this.weekCheck[4].setName("weekCheck[4]"); // NOI18N
        this.weekCheck[4].addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                thursdayActionPerformed(evt);
            }
        });

        this.weekCheck[5].setText("Friday");
        this.weekCheck[5].setName("weekCheck[5]"); // NOI18N
        this.weekCheck[5].addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
               fridayActionPerformed(evt);
            }
        });

        this.weekCheck[6].setText("Saturday");
        this.weekCheck[6].setName("weekCheck[6]"); // NOI18N
        this.weekCheck[6].addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                saturdayActionPerformed(evt);
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
                .addComponent(this.weekCheck[0])
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.weekCheck[1], javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.weekCheck[2])
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.weekCheck[3], javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.weekCheck[4])
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.weekCheck[5], javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.weekCheck[6], javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(this.nextButton)
                .addGap(78, 78, 78))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(this.weekCheck[0], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(this.weekCheck[5], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(this.weekCheck[6], javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(this.nextButton))
                    .addComponent(this.weekCheck[3], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.weekCheck[2], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(this.jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(this.weekCheck[4], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.weekCheck[1], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    
    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void sundayActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if(this.weekCheck[0].isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[0] = new DefaultListModel<Object>();
            this.weekJobList[0].setModel(this.models[0]);
            this.weekScrollPane[0].setViewportView(this.weekJobList[0]);

            this.weekJobName[0].setColumns(20);

            this.weekLabel[0].setText("Job Name:");

            this.weekAddJob[0].setText("Add Job");
            this.weekAddJob[0].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.weekJobName[0].getText().isEmpty()) {
                        Config.this.models[0].addElement(Config.this.weekJobName[0].getText());
                        Config.this.weekJobList[0].setModel(Config.this.models[0]);
                        Config.this.weekJobName[0].setText("");
                    }
                }
            });

            this.weekDeleteJob[0].setText("Delete Job");
            this.weekDeleteJob[0].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.weekJobList[0].isSelectionEmpty()) {
                        int n = Config.this.weekJobList[0].getSelectedIndex();
                        Config.this.models[0].remove(n);
                    }
                    
                }
            });

            javax.swing.GroupLayout weekTab0Layout = new javax.swing.GroupLayout(this.weekTab[0]);
            this.weekTab[0].setLayout(weekTab0Layout);
            weekTab0Layout.setHorizontalGroup(
                weekTab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(weekTab0Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(this.weekScrollPane[0], javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(weekTab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(weekTab0Layout.createSequentialGroup()
                            .addComponent(this.weekLabel[0])
                            .addGroup(weekTab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(weekTab0Layout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(this.weekAddJob[0]))
                                .addGroup(weekTab0Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(this.weekJobName[0], javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(this.weekDeleteJob[0]))
                    .addContainerGap(431, Short.MAX_VALUE))
            );
            weekTab0Layout.setVerticalGroup(
                weekTab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(weekTab0Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(weekTab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(weekTab0Layout.createSequentialGroup()
                            .addGroup(weekTab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(this.weekJobName[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.weekLabel[0]))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(this.weekAddJob[0])
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(this.weekDeleteJob[0]))
                        .addComponent(this.weekScrollPane[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(25, Short.MAX_VALUE))
            );
            this.dayTabs.addTab("Sunday", this.weekTab[0]);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.weekTab[0]);
        }
        
    }                                           

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void mondayActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if(this.weekCheck[1].isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[1] = new DefaultListModel<Object>();
            this.weekJobList[1].setModel(this.models[1]);
            this.weekScrollPane[1].setViewportView(this.weekJobList[1]);

            this.weekJobName[1].setColumns(20);

            this.weekLabel[1].setText("Job Name:");

            this.weekAddJob[1].setText("Add Job");
            this.weekAddJob[1].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.weekJobName[1].getText().isEmpty()) {
                        Config.this.models[1].addElement(Config.this.weekJobName[1].getText());
                        Config.this.weekJobList[1].setModel(Config.this.models[1]);
                        Config.this.weekJobName[1].setText("");
                    }
                }
            });

            this.weekDeleteJob[1].setText("Delete Job");
            this.weekDeleteJob[1].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.weekJobList[1].isSelectionEmpty()) {
                        int n = Config.this.weekJobList[1].getSelectedIndex();
                        Config.this.models[1].remove(n);
                    }
                    
                }
            });

            javax.swing.GroupLayout mondayTabLayout = new javax.swing.GroupLayout(this.weekTab[1]);
            this.weekTab[1].setLayout(mondayTabLayout);
            mondayTabLayout.setHorizontalGroup(
                mondayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mondayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(this.weekScrollPane[1], javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(mondayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mondayTabLayout.createSequentialGroup()
                            .addComponent(this.weekLabel[1])
                            .addGroup(mondayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(mondayTabLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(this.weekAddJob[1]))
                                .addGroup(mondayTabLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(this.weekJobName[1], javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(this.weekDeleteJob[1]))
                    .addContainerGap(431, Short.MAX_VALUE))
            );
            mondayTabLayout.setVerticalGroup(
                mondayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mondayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(mondayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(mondayTabLayout.createSequentialGroup()
                            .addGroup(mondayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(this.weekJobName[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.weekLabel[1]))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(this.weekAddJob[1])
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(this.weekDeleteJob[1]))
                        .addComponent(this.weekScrollPane[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(25, Short.MAX_VALUE))
            );
            this.dayTabs.addTab("Monday", this.weekTab[1]);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.weekTab[1]);
        }
                
    }                                           

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void teusdayActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if(this.weekCheck[2].isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[2] = new DefaultListModel<Object>();
            this.weekJobList[2].setModel(this.models[2]);
            this.weekScrollPane[2].setViewportView(this.weekJobList[2]);

            this.weekJobName[2].setColumns(20);

            this.weekLabel[2].setText("Job Name:");

            this.weekAddJob[2].setText("Add Job");
            this.weekAddJob[2].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.weekJobName[2].getText().isEmpty()) {
                        Config.this.models[2].addElement(Config.this.weekJobName[2].getText());
                        Config.this.weekJobList[2].setModel(Config.this.models[2]);
                        Config.this.weekJobName[2].setText("");
                    }
                }
            });

            this.weekDeleteJob[2].setText("Delete Job");
            this.weekDeleteJob[2].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.weekJobList[2].isSelectionEmpty()) {
                        int n = Config.this.weekJobList[2].getSelectedIndex();
                        Config.this.models[2].remove(n);
                    }
                    
                }
            });

            javax.swing.GroupLayout tuesdayTabLayout = new javax.swing.GroupLayout(this.weekTab[2]);
            this.weekTab[2].setLayout(tuesdayTabLayout);
            tuesdayTabLayout.setHorizontalGroup(
                tuesdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tuesdayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(this.weekScrollPane[2], javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(tuesdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tuesdayTabLayout.createSequentialGroup()
                            .addComponent(this.weekLabel[2])
                            .addGroup(tuesdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(tuesdayTabLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(this.weekAddJob[2]))
                                .addGroup(tuesdayTabLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(this.weekJobName[2], javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(this.weekDeleteJob[2]))
                    .addContainerGap(431, Short.MAX_VALUE))
            );
            tuesdayTabLayout.setVerticalGroup(
                tuesdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tuesdayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(tuesdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(tuesdayTabLayout.createSequentialGroup()
                            .addGroup(tuesdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(this.weekJobName[2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.weekLabel[2]))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(this.weekAddJob[2])
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(this.weekDeleteJob[2]))
                        .addComponent(this.weekScrollPane[2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(25, Short.MAX_VALUE))
            );
            this.dayTabs.addTab("Tuesday", this.weekTab[2]);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.weekTab[2]);
        }
    }                                            

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void wednesdayActionPerformed(java.awt.event.ActionEvent evt) {                                               
        if(this.weekCheck[3].isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[3] = new DefaultListModel<Object>();
            this.weekJobList[3].setModel(this.models[3]);
            this.weekScrollPane[3].setViewportView(this.weekJobList[3]);

            this.weekJobName[3].setColumns(20);

            this.weekLabel[3].setText("Job Name:");

            this.weekAddJob[3].setText("Add Job");
            this.weekAddJob[3].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.weekJobName[3].getText().isEmpty()) {
                        Config.this.models[3].addElement(Config.this.weekJobName[3].getText());
                        Config.this.weekJobList[3].setModel(Config.this.models[3]);
                        Config.this.weekJobName[3].setText("");
                    }
                }
            });

            this.weekDeleteJob[3].setText("Delete Job");
            this.weekDeleteJob[3].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.weekJobList[3].isSelectionEmpty()) {
                        int n = Config.this.weekJobList[3].getSelectedIndex();
                        Config.this.models[3].remove(n);
                    }
                    
                }
            });

            javax.swing.GroupLayout wednesdayTabLayout = new javax.swing.GroupLayout(this.weekTab[3]);
            this.weekTab[3].setLayout(wednesdayTabLayout);
            wednesdayTabLayout.setHorizontalGroup(
                wednesdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(wednesdayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(this.weekScrollPane[3], javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(wednesdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(wednesdayTabLayout.createSequentialGroup()
                            .addComponent(this.weekLabel[3])
                            .addGroup(wednesdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(wednesdayTabLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(this.weekAddJob[3]))
                                .addGroup(wednesdayTabLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(this.weekJobName[3], javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(this.weekDeleteJob[3]))
                    .addContainerGap(431, Short.MAX_VALUE))
            );
            wednesdayTabLayout.setVerticalGroup(
                wednesdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(wednesdayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(wednesdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(wednesdayTabLayout.createSequentialGroup()
                            .addGroup(wednesdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(this.weekJobName[3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.weekLabel[3]))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(this.weekAddJob[3])
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(this.weekDeleteJob[3]))
                        .addComponent(this.weekScrollPane[3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(25, Short.MAX_VALUE))
            );
            this.dayTabs.addTab("Wednesday", this.weekTab[3]);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.weekTab[3]);
        }
        
    }                                              

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void thursdayActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if(this.weekCheck[4].isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[4] = new DefaultListModel<Object>();
            this.weekJobList[4].setModel(this.models[4]);
            this.weekScrollPane[4].setViewportView(this.weekJobList[4]);

            this.weekJobName[4].setColumns(20);

            this.weekLabel[4].setText("Job Name:");

            this.weekAddJob[4].setText("Add Job");
            this.weekAddJob[4].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.weekJobName[4].getText().isEmpty()) {
                        Config.this.models[4].addElement(Config.this.weekJobName[4].getText());
                        Config.this.weekJobList[4].setModel(Config.this.models[4]);
                        Config.this.weekJobName[4].setText("");
                    }
                }
            });

            this.weekDeleteJob[4].setText("Delete Job");
            this.weekDeleteJob[4].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.weekJobList[4].isSelectionEmpty()) {
                        int n = Config.this.weekJobList[4].getSelectedIndex();
                        Config.this.models[4].remove(n);
                    }
                    
                }
            });

            javax.swing.GroupLayout thursdayTabLayout = new javax.swing.GroupLayout(this.weekTab[4]);
            this.weekTab[4].setLayout(thursdayTabLayout);
            thursdayTabLayout.setHorizontalGroup(
                thursdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(thursdayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(this.weekScrollPane[4], javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(thursdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(thursdayTabLayout.createSequentialGroup()
                            .addComponent(this.weekLabel[4])
                            .addGroup(thursdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(thursdayTabLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(this.weekAddJob[4]))
                                .addGroup(thursdayTabLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(this.weekJobName[4], javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(this.weekDeleteJob[4]))
                    .addContainerGap(431, Short.MAX_VALUE))
            );
            thursdayTabLayout.setVerticalGroup(
                thursdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(thursdayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(thursdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(thursdayTabLayout.createSequentialGroup()
                            .addGroup(thursdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(this.weekJobName[4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.weekLabel[4]))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(this.weekAddJob[4])
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(this.weekDeleteJob[4]))
                        .addComponent(this.weekScrollPane[4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(25, Short.MAX_VALUE))
            );
            this.dayTabs.addTab("Thursday", this.weekTab[4]);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.weekTab[4]);
        }
        
    }                                             

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void fridayActionPerformed(java.awt.event.ActionEvent evt) {                                            
       if(this.weekCheck[5].isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[5] = new DefaultListModel<Object>();
            this.weekJobList[5].setModel(this.models[5]);
            this.weekScrollPane[5].setViewportView(this.weekJobList[5]);

            this.weekJobName[5].setColumns(20);

            this.weekLabel[5].setText("Job Name:");

            this.weekAddJob[5].setText("Add Job");
            this.weekAddJob[5].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.weekJobName[5].getText().isEmpty()) {
                        Config.this.models[5].addElement(Config.this.weekJobName[5].getText());
                        Config.this.weekJobList[5].setModel(Config.this.models[5]);
                        Config.this.weekJobName[5].setText("");
                    }
                }
            });

            this.weekDeleteJob[5].setText("Delete Job");
            this.weekDeleteJob[5].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.weekJobList[5].isSelectionEmpty()) {
                        int n = Config.this.weekJobList[5].getSelectedIndex();
                        Config.this.models[5].remove(n);
                    }
                    
                }
            });

            javax.swing.GroupLayout fridayTabLayout = new javax.swing.GroupLayout(this.weekTab[5]);
            this.weekTab[5].setLayout(fridayTabLayout);
            fridayTabLayout.setHorizontalGroup(
                fridayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(fridayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(this.weekScrollPane[5], javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(fridayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(fridayTabLayout.createSequentialGroup()
                            .addComponent(this.weekLabel[5])
                            .addGroup(fridayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(fridayTabLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(this.weekAddJob[5]))
                                .addGroup(fridayTabLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(this.weekJobName[5], javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(this.weekDeleteJob[5]))
                    .addContainerGap(431, Short.MAX_VALUE))
            );
            fridayTabLayout.setVerticalGroup(
                fridayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(fridayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(fridayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(fridayTabLayout.createSequentialGroup()
                            .addGroup(fridayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(this.weekJobName[5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.weekLabel[5]))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(this.weekAddJob[5])
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(this.weekDeleteJob[5]))
                        .addComponent(this.weekScrollPane[5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(25, Short.MAX_VALUE))
            );
            this.dayTabs.addTab("Friday", this.weekTab[5]);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.weekTab[5]);
        }
        
    }                                           

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void saturdayActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if(this.weekCheck[6].isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[6] = new DefaultListModel<Object>();
            this.weekJobList[6].setModel(this.models[6]);
            this.weekScrollPane[6].setViewportView(this.weekJobList[6]);

            this.weekJobName[6].setColumns(20);

            this.weekLabel[6].setText("Job Name:");

            this.weekAddJob[6].setText("Add Job");
            this.weekAddJob[6].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.weekJobName[6].getText().isEmpty()) {
                        Config.this.models[6].addElement(Config.this.weekJobName[6].getText());
                        Config.this.weekJobList[6].setModel(Config.this.models[6]);
                        Config.this.weekJobName[6].setText("");
                    }
                }
            });

            this.weekDeleteJob[6].setText("Delete Job");
            this.weekDeleteJob[6].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.weekJobList[6].isSelectionEmpty()) {
                        int n = Config.this.weekJobList[6].getSelectedIndex();
                        Config.this.models[6].remove(n);
                    }
                    
                }
            });

            javax.swing.GroupLayout saturdayTabLayout = new javax.swing.GroupLayout(this.weekTab[6]);
            this.weekTab[6].setLayout(saturdayTabLayout);
            saturdayTabLayout.setHorizontalGroup(
                saturdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(saturdayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(this.weekScrollPane[6], javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(saturdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(saturdayTabLayout.createSequentialGroup()
                            .addComponent(this.weekLabel[6])
                            .addGroup(saturdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(saturdayTabLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(this.weekAddJob[6]))
                                .addGroup(saturdayTabLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(this.weekJobName[6], javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(this.weekDeleteJob[6]))
                    .addContainerGap(431, Short.MAX_VALUE))
            );
            saturdayTabLayout.setVerticalGroup(
                saturdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(saturdayTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(saturdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(saturdayTabLayout.createSequentialGroup()
                            .addGroup(saturdayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(this.weekJobName[6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.weekLabel[6]))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(this.weekAddJob[6])
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(this.weekDeleteJob[6]))
                        .addComponent(this.weekScrollPane[6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(25, Short.MAX_VALUE))
            );
            this.dayTabs.addTab("Saturday", this.weekTab[6]);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.weekTab[6]);
        }
    }                                             

    /**
	 * @param evt  
	 */
    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	ArrayList<Day> days = new ArrayList<Day>();
    	if(this.weekCheck[0].isSelected())
        {
    		ArrayList<Object> sun = new ArrayList<Object>();
    		List<Object> jobs = Arrays.asList(this.models[0].toArray());
    		sun.addAll(jobs);
        	days.add(new Day("Sunday",sun));
        }
    	if(this.weekCheck[1].isSelected())
        {
    		ArrayList<Object> mon = new ArrayList<Object>();
    		List<Object> jobs = Arrays.asList(this.models[1].toArray());
    		mon.addAll(jobs);
        	days.add(new Day("Monday",mon));
        }
    	if(this.weekCheck[2].isSelected())
        {
    		ArrayList<Object> tue = new ArrayList<Object>();
    		List<Object> jobs = Arrays.asList(this.models[2].toArray());
    		tue.addAll(jobs);
        	days.add(new Day("Tuesday",tue));
        }
    	if(this.weekCheck[3].isSelected())
        {
    		ArrayList<Object> wed = new ArrayList<Object>();
    		List<Object> jobs = Arrays.asList(this.models[3].toArray());
    		wed.addAll(jobs);
        	days.add(new Day("Wednesday",wed));
        }
    	if(this.weekCheck[4].isSelected())
        {
    		ArrayList<Object> thu = new ArrayList<Object>();
    		List<Object> jobs = Arrays.asList(this.models[4].toArray());
    		thu.addAll(jobs);
        	days.add(new Day("Thursday",thu));
        }
    	if(this.weekCheck[5].isSelected())
        {
    		ArrayList<Object> fri = new ArrayList<Object>();
    		List<Object> jobs = Arrays.asList(this.models[5].toArray());
    		fri.addAll(jobs);
        	days.add(new Day("Friday",fri));
        }
    	if(this.weekCheck[6].isSelected())
        {
    		ArrayList<Object> sat = new ArrayList<Object>();
    		List<Object> jobs = Arrays.asList(this.models[6].toArray());
    		sat.addAll(jobs);
        	days.add(new Day("Saturday",sat));
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
    
    
    private void stretch() {
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
    
    //SWAP 1, TEAM 7 - QUALITY CHANGES
    //Each of these arrays used to be 7 seperate variables, we removed all of the
    //duplicated code and can now use arrays to store the variables instead
    private JScrollPane[] weekScrollPane = new JScrollPane[7];
    private JButton[] weekAddJob = new JButton[7];
    private JButton[] weekDeleteJob = new JButton[7];
	private JList[] weekJobList = new JList[7];
    private JTextField[] weekJobName = new JTextField[7];
    private JLabel[] weekLabel = new JLabel[7];
    private JPanel[] weekTab = new JPanel[7];
    private JCheckBox[] weekCheck = new JCheckBox[7];
    
    //Static Mapping of weekday names to the integers of the arrays
    //This is needed as we still need be able to figure out what day
    // goes with what index of the array
    private static enum WEEK_INDICIES {
    	Sunday,
		Monday,
		Tuesday,
		Wednesday,
		Thursday,
		Friday,
		Satuday
    };
    
    private JTabbedPane dayTabs;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JButton nextButton;

}
