package shared.services.rss;

import com.github.kevinsawicki.http.HttpRequest;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import shared.models.post.Post;
import shared.models.site.Site;
import shared.models.term.Term;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Fetcher {

    public static List<Post> fetch(List<Site> sites, List<Term> terms) {
        return sites.parallelStream()
                .map(site -> fetch(site, terms))
                .reduce((posts1, posts2) -> {
                    posts1.addAll(posts2);

                    return posts1;
                })
                .get();
    }

    public static List<Post> fetch(Site site, List<Term> terms) {
        String response = HttpRequest.get(site.getUrl()).body();

        Rss rss = responseToRss(response);

        List<Rss.Channel.Item> items = filterRssItemList(rss.channel.itemList, terms);

        return rssItemListToPostList(items);
    }

    private static List<Post> rssItemListToPostList(List<Rss.Channel.Item> items) {
        return items.parallelStream()
                .map(Fetcher::rssItemToPost).collect(Collectors.toList());
    }

    private static Post rssItemToPost(Rss.Channel.Item item) {

        return new Post(
                item.link,
                item.title,
                item.description,
                rssDateStringToDate(item.pubDate)
        );
    }

    private static List<Rss.Channel.Item> filterRssItemList(List<Rss.Channel.Item> items, List<Term> terms) {
        return items.parallelStream()
                .filter((item) -> terms.stream()
                        .anyMatch((term -> item.title.contains(term.getTerm()))))
                .collect(Collectors.toList());
    }

    private static Rss responseToRss(String source) {
        Serializer serializer = new Persister();

        String fixedSource = source.trim().replaceFirst("^([\\W]+)<","<");
        try {
            return serializer.read(Rss.class, fixedSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Date rssDateStringToDate(String dateString){
        try {
            return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz")
                    .parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
