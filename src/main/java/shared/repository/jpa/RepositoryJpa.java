package shared.repository.jpa;

import shared.repository.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class RepositoryJpa<T> implements Repository<T> {

    private EntityManager manager;

    private Class<T> entityClass;

    public RepositoryJpa(EntityManager manager, Class<T> entityClass) {
        this.manager = manager;
        this.entityClass = entityClass;
    }

    @Override
    public List<T> fetchMany() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> from = query.from(entityClass);
        CriteriaQuery<T> select = query.select(from);

        TypedQuery<T> typedQuery = manager.createQuery(select);

        return typedQuery.getResultList();
    }

    public List<T> fetchMany(CriteriaQuery<T> select) {
        TypedQuery<T> typedQuery = manager.createQuery(select);

        return typedQuery.getResultList();
    }

    @Override
    public T fetchOneById(long id) {
        return manager.find(entityClass, id);
    }

    @Override
    public T save(T entity) {
        if (entity instanceof Timestamped) {
            Timestamped timestamped = (Timestamped) entity;
            Date now = new Date();

            if (timestamped.getCreatedAt() == null) {
                timestamped.setCreatedAt(now);
            }

            timestamped.setUpdatedAt(now);
        }

        manager.persist(entity);

        return entity;
    }

    @Override
    public void delete(T entity) {
        manager.remove(entity);
    }

    @Override
    public T refresh(T entity) {
        manager.refresh(entity);

        return entity;
    }

    @Override
    public void beginTransaction() {
        manager.getTransaction().begin();
    }

    @Override
    public void commitTransaction() {
        manager.getTransaction().commit();
    }

    @Override
    public void rollbackTransaction() {
        manager.getTransaction().rollback();
    }

    @Override
    public void transaction(Transaction transaction) {
        beginTransaction();
        try {
            transaction.run();
            commitTransaction();
        }
        catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }
}
