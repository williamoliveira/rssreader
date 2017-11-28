package app.views.tableModels;

import shared.models.term.Term;

public class TermsTableModel extends ResourceTableModel<Term>{

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Term term = getResources().get(rowIndex);

        switch (columnIndex) {
            case 0: return term.getTerm();
            default: return null;
        }
    }

    @Override
    public String[] getHeaders() {
        return new String[]{"Termo"};
    }
}
