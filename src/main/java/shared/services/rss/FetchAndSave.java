package shared.services.rss;

import shared.entities.post.Post;
import shared.entities.post.PostRepository;
import shared.entities.site.Site;
import shared.entities.site.SiteRepository;
import shared.entities.term.Term;
import shared.entities.term.TermRepository;
import shared.services.email.EmailSender;

import java.util.List;
import java.util.stream.Collectors;

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

        List<Post> newPosts = posts.stream()
                .filter(post -> !postRepository.existsByUrl(post.getUrl()))
                .collect(Collectors.toList());

        // salva no banco
        newPosts.forEach(post -> postRepository.transaction(
                () -> postRepository.save(post)
        ));

        // envia emails
        newPosts.forEach(post -> EmailSender.send(
                "test@mail.com",
                post.getTitle(),
                post.getUrl())
        );
    }
}
