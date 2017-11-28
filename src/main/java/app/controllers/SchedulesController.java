package app.controllers;

import app.views.MainView;
import app.views.tableModels.SchedulesTableModel;
import shared.models.schedule.Schedule;
import shared.models.schedule.ScheduleRepository;
import shared.rmi.DaemonRemoteInterface;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

import static javax.swing.JFormattedTextField.*;

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

        mainView.schedulesTimeInput.setFormatterFactory(new AbstractFormatterFactory() {
            @Override
            public AbstractFormatter getFormatter(JFormattedTextField tf) {
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

    private void refreshDaemonSchedulesIfRunning() {
        refreshDaemonSchedulesIfRunning(9192);
    }

    private void refreshDaemonSchedulesIfRunning(int port) {
        try {
            DaemonRemoteInterface daemonRemoteInterface =
                    (DaemonRemoteInterface) Naming.lookup("rmi://127.0.0.1:" + port + "/DaemonRemoteInterface");

            daemonRemoteInterface.refreshSchedules();
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void refreshSchedules() {
        refreshSchedulesTable();
        refreshDaemonSchedulesIfRunning();
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

        refreshSchedules();
    }

    private void deleteScheduleHandler() {
        Schedule schedule = getSelectedSchedule();

        scheduleRepository.transaction(() -> scheduleRepository.delete(schedule));
        refreshSchedules();
    }

    private Schedule getSelectedSchedule() {
        int index = mainView.schedulesTable.getSelectedRow();

        return ((SchedulesTableModel)mainView.schedulesTable.getModel())
                .getResources()
                .get(index);
    }
}
