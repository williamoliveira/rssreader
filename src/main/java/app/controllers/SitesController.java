package app.controllers;

import app.views.MainView;
import app.views.tableModels.SitesTableModel;
import shared.models.site.Site;
import shared.models.site.SiteRepository;

import java.util.List;

public class SitesController {

    private SiteRepository siteRepository;
    private MainView mainView;

    public SitesController(SiteRepository siteRepository, MainView mainView) {
        this.siteRepository = siteRepository;
        this.mainView = mainView;

        setupComponents();
        refreshSitesTable();
    }

    private void setupComponents() {
        mainView.sitesTable.setModel(new SitesTableModel());

        mainView.sitesSaveButton.addActionListener((actionEvent) -> saveSiteHandler());
        mainView.sitesDeleteButton.addActionListener((actionEvent) -> deleteSiteHandler());
    }

    private void refreshSitesTable() {
        List<Site> sites = siteRepository.fetchMany();
        ((SitesTableModel)mainView.sitesTable.getModel()).setResources(sites);
    }

    private void saveSiteHandler() {
        String url = mainView.sitesUrlField.getText();

        if (!Helpers.isUrlValid(url)) {
            Helpers.displayErrorMessage("URL inválida.");
            return;
        }

        Site site = new Site(url);

        mainView.sitesUrlField.setText(null);

        siteRepository.transaction(() -> siteRepository.save(site));

        refreshSitesTable();
    }

    private void deleteSiteHandler() {
        Site site = getSelectedSite();

        siteRepository.transaction(() -> siteRepository.delete(site));
        refreshSitesTable();
    }

    private Site getSelectedSite() {
        int index = mainView.sitesTable.getSelectedRow();

        return ((SitesTableModel)mainView.sitesTable.getModel())
                .getResources()
                .get(index);
    }
}
