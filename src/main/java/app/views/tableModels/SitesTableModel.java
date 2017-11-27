package app.views.tableModels;

import shared.entities.site.Site;

public class SitesTableModel extends ResourceTableModel<Site>{

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Site site = getResources().get(rowIndex);

        switch (columnIndex) {
            case 0: return site.getUrl();
            default: return null;
        }
    }

    @Override
    public String[] getHeaders() {
        return new String[]{"URL"};
    }
}
