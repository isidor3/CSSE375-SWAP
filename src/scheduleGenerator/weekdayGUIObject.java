package scheduleGenerator;

import java.awt.Dimension;

import javax.swing.JCheckBox;


// SWAP 2, TEAM 5
// REFACTORING FOR ENHANCEMENT FROM BAD SMELL
// This entire class was made to refactor the Data Clumps in Config's initDyn method.
public class weekdayGUIObject {
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton addJob;
    private javax.swing.JButton deleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList jobList;
    private javax.swing.JTextField jobName;
    private javax.swing.JLabel label;
    private DayPanel tab;
    
    public weekdayGUIObject() {
        this.scrollPane = new javax.swing.JScrollPane();
        this.scrollPane.setPreferredSize(new Dimension(185,150));
        this.jobList = new javax.swing.JList();
        this.jobName = new javax.swing.JTextField();
        this.label = new javax.swing.JLabel();
        this.addJob = new javax.swing.JButton();
        this.deleteJob = new javax.swing.JButton();
    }
    
	public javax.swing.JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(javax.swing.JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	public javax.swing.JButton getAddJob() {
		return addJob;
	}
	public void setAddJob(javax.swing.JButton addJob) {
		this.addJob = addJob;
	}
	public javax.swing.JButton getDeleteJob() {
		return deleteJob;
	}
	public void setDeleteJob(javax.swing.JButton deleteJob) {
		this.deleteJob = deleteJob;
	}
	public javax.swing.JList getJobList() {
		return jobList;
	}
	public void setJobList(javax.swing.JList jobList) {
		this.jobList = jobList;
	}
	public javax.swing.JTextField getJobName() {
		return jobName;
	}
	public void setJobName(javax.swing.JTextField jobName) {
		this.jobName = jobName;
	}
	public javax.swing.JLabel getLabel() {
		return label;
	}
	public void setLabel(javax.swing.JLabel label) {
		this.label = label;
	}
	public DayPanel getTab() {
		return tab;
	}
	public void setTab(DayPanel tab) {
		this.tab = tab;
	}
	public void createTab(String day, int numDay, JCheckBox check, Config configSet) {
		this.tab = new DayPanel(day, configSet, numDay, this.jobList, check, this.scrollPane, this.jobName, this.addJob, this.deleteJob, this.label);
	}
	
}