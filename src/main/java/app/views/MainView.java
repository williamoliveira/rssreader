package app.views;

import app.views.tableModels.PostsTableModel;
import app.views.tableModels.SitesTableModel;
import shared.entities.post.Post;

import javax.swing.*;
import java.util.List;

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

    public MainView() {
        super();
        setContentPane(contentPane);
        pack();
    }

    public void createUIComponents() {
    }


}
