package app.controllers;

import app.views.MainView;
import app.views.tableModels.SchedulesTableModel;
import shared.entities.schedule.Schedule;
import shared.entities.schedule.ScheduleRepository;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.List;

public class SchedulesController {

    private ScheduleRepository scheduleRepository;
    private MainView mainView;

    public SchedulesController(ScheduleRepository scheduleRepository, MainView mainView) {
        this.scheduleRepository = scheduleRepository;
        this.mainView = mainView;

        setupComponents();
        refreshSchedulesTable();
    }

    private void setupComponents() {
        mainView.schedulesTable.setModel(new SchedulesTableModel());

        mainView.schedulesTimeInput.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {
            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                try {
                    return new MaskFormatter("##:##");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return null;
            }
        });

        mainView.schedulesSaveButton.addActionListener((actionEvent) -> saveScheduleHandler());
        mainView.schedulesDeleteButton.addActionListener((actionEvent) -> deleteScheduleHandler());
    }

    private void refreshSchedulesTable() {
        List<Schedule> schedules = scheduleRepository.fetchMany();
        ((SchedulesTableModel)mainView.schedulesTable.getModel()).setResources(schedules);
    }

    private void saveScheduleHandler() {
        String time = mainView.schedulesTimeInput.getText();

        if (!Helpers.isTimeValid(time)) {
            Helpers.displayErrorMessage("Horário inválido.");
            return;
        }

        Schedule schedule = new Schedule(time);

        mainView.schedulesTimeInput.setText(null);

        scheduleRepository.transaction(() -> scheduleRepository.save(schedule));

        refreshSchedulesTable();
    }

    private void deleteScheduleHandler() {
        Schedule schedule = getSelectedSchedule();

        scheduleRepository.transaction(() -> scheduleRepository.delete(schedule));
        refreshSchedulesTable();
    }

    private Schedule getSelectedSchedule() {
        int index = mainView.schedulesTable.getSelectedRow();

        return ((SchedulesTableModel)mainView.schedulesTable.getModel())
                .getResources()
                .get(index);
    }
}
