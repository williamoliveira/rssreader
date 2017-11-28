package shared.services.rss;

import shared.entities.post.Post;
import shared.entities.post.PostRepository;
import shared.entities.site.Site;
import shared.entities.site.SiteRepository;
import shared.entities.term.Term;
import shared.entities.term.TermRepository;

import java.util.List;

public class FetchAndSave {
    private PostRepository postRepository;
    private SiteRepository siteRepository;
    private TermRepository termRepository;

    public FetchAndSave(PostRepository postRepository, SiteRepository siteRepository, TermRepository termRepository) {
        this.postRepository = postRepository;
        this.siteRepository = siteRepository;
        this.termRepository = termRepository;
    }

    public void fetchAndSave() {
        List<Site> sites = siteRepository.fetchMany();
        List<Term> terms = termRepository.fetchMany();
        List<Post> posts = Fetcher.fetch(sites, terms);

        posts.stream()
                .filter(post -> !postRepository.existsByUrl(post.getUrl()))
                .forEach(post -> postRepository.transaction(() -> postRepository.save(post)));
    }
}
