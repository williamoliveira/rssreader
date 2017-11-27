package shared.entities.site;

import shared.repository.jpa.RepositoryJpa;

import javax.persistence.EntityManager;

public class SiteRepositoryJpa extends RepositoryJpa<Site> implements SiteRepository {

    public SiteRepositoryJpa(EntityManager manager) {
        super(manager, Site.class);
    }
}
