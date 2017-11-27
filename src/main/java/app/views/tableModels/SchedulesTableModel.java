package app.views.tableModels;

import shared.entities.schedule.Schedule;

public class SchedulesTableModel extends ResourceTableModel<Schedule>{

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Schedule schedule = getResources().get(rowIndex);

        switch (columnIndex) {
            case 0: return schedule.getTime();
            default: return null;
        }
    }

    @Override
    public String[] getHeaders() {
        return new String[]{"Horário"};
    }
}
