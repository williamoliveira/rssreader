package shared.models.post;

import shared.repository.jpa.RepositoryJpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PostRepositoryJpa extends RepositoryJpa<Post> implements PostRepository {

    public PostRepositoryJpa(EntityManager manager) {
        super(manager, Post.class);
    }

    @Override
    public boolean existsByUrl(String url) {
        String jpql = "SELECT count(p) FROM Post p WHERE p.url = :url";
        Query q = manager.createQuery(jpql, Long.class);
        q.setParameter("url", url);
        Long count = (Long) q.getSingleResult();

        return count > 0;
    }
}
