package app.controllers;

import app.views.MainView;
import app.views.tableModels.TermsTableModel;
import shared.entities.term.Term;
import shared.entities.term.TermRepository;

import java.util.List;

public class TermsController {
    
    private TermRepository termRepository;
    private MainView mainView;

    public TermsController(TermRepository termRepository, MainView mainView) {
        this.termRepository = termRepository;
        this.mainView = mainView;
        
        setupComponents();
        refreshTermsTable();
    }

    private void setupComponents() {
        mainView.termsTable.setModel(new TermsTableModel());

        mainView.termsSaveButton.addActionListener((actionEvent) -> saveTermHandler());
        mainView.termsDeleteButton.addActionListener((actionEvent) -> deleteTermHandler());
    }
    
    private void refreshTermsTable() {
        List<Term> terms = termRepository.fetchMany();
        ((TermsTableModel)mainView.termsTable.getModel()).setResources(terms);
    }

    private void saveTermHandler() {
        String termString = mainView.termsTermField.getText();

        Term term = new Term(termString);

        mainView.termsTermField.setText(null);

        termRepository.transaction(() -> termRepository.save(term));

        refreshTermsTable();
    }

    private void deleteTermHandler() {
        Term term = getSelectedTerm();

        termRepository.transaction(() -> termRepository.delete(term));
        refreshTermsTable();
    }

    private Term getSelectedTerm() {
        int index = mainView.termsTable.getSelectedRow();

        return ((TermsTableModel)mainView.termsTable.getModel())
                .getResources()
                .get(index);
    }
}
