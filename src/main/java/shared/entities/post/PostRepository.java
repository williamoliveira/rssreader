package shared.entities.post;

import shared.repository.Repository;

public interface PostRepository extends Repository<Post> {
    public boolean existsByUrl(String url);
}
