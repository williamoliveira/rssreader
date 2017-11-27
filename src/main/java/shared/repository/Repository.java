package shared.repository;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface Repository<T> {
    public List<T> fetchMany();
    public List<T> fetchMany(CriteriaQuery<T> select);
    public T fetchOneById(long id);
    public T save(T entity);
    public void delete(T entity);
    public T refresh(T entity);
    public void beginTransaction();
    public void commitTransaction();
    public void rollbackTransaction();
    public void transaction(Transaction transaction);

    interface Transaction{
        abstract public void run();
    }
}
