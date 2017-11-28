package app.views;

import javax.swing.*;

public class MainView extends JDialog {
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JPanel postsTab;
    private JPanel sitesTab;
    private JPanel termsTab;
    private JPanel schedulesTab;
    public JTable postsTable;
    public JTable sitesTable;
    public JTextField sitesUrlField;
    public JButton sitesSaveButton;
    public JButton sitesDeleteButton;
    public JTable termsTable;
    public JButton termsDeleteButton;
    public JTextField termsTermField;
    public JButton termsSaveButton;
    public JButton schedulesSaveButton;
    public JButton schedulesDeleteButton;
    public JTable schedulesTable;
    public JFormattedTextField schedulesTimeInput;
    public JButton verifyNowButton;
    public JButton openButton;

    public MainView() {
        super();
        setContentPane(contentPane);
        pack();
    }

    public void createUIComponents() {
    }


}
