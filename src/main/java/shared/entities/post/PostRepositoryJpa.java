package shared.entities.post;

import shared.repository.jpa.RepositoryJpa;

import javax.persistence.EntityManager;

public class PostRepositoryJpa extends RepositoryJpa<Post> implements PostRepository {

    public PostRepositoryJpa(EntityManager manager) {
        super(manager, Post.class);
    }
}
