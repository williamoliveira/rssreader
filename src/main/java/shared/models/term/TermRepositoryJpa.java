package shared.models.term;

import shared.repository.jpa.RepositoryJpa;

import javax.persistence.EntityManager;

public class TermRepositoryJpa extends RepositoryJpa<Term> implements TermRepository {

    public TermRepositoryJpa(EntityManager manager) {
        super(manager, Term.class);
    }
}
