package app.views.tableModels;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public abstract class ResourceTableModel<T> extends AbstractTableModel {

    private List<T> resources = new ArrayList<>();

    public List<T> getResources() {
        return resources;
    }

    public void setResources(List<T> resources) {
        this.resources = resources;
        fireTableDataChanged();
    }

    public abstract String[] getHeaders();

    @Override
    public int getRowCount() {
        return resources.size();
    }

    @Override
    public int getColumnCount() {
        return getHeaders().length;
    }

    @Override
    public String getColumnName(int column){
        return getHeaders()[column];
    }
}
