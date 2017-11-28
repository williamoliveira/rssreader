package shared.services.rss;

import shared.models.post.Post;
import shared.models.post.PostRepository;
import shared.models.site.Site;
import shared.models.site.SiteRepository;
import shared.models.term.Term;
import shared.models.term.TermRepository;
import shared.services.email.MailSender;

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
        newPosts.forEach(post -> MailSender.send(
                "test@mail.com",
                post.getTitle(),
                post.getUrl())
        );
    }
}
