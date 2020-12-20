import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WhenInRome {

    private static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        String url = "https://stackoverflow.com/feeds/tag?tagnames=rome";
        SyndFeed feed = null;
        WireFeed wireFeed = null;
        try {
          //  SyndFeedInput in = new SyndFeedInput();

            //SyndFeed syndFeed = in.build(..);
            SyndFeedInput in = new SyndFeedInput();
            in.setPreserveWireFeed(true);

            feed = in.build(new XmlReader(new URL(url)));
            //feed.setPreserveWireFeed(true);
            wireFeed = feed.originalWireFeed();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(feed.getTitle());
        LOG.info("here"+feed.getTitle());
        LOG.info("here"+ wireFeed.toString());
        LOG.info(wireFeed.getFeedType());
        //LOG.info("here"+feed.);
        //feedFetcher.setPreserveWireFeed(true);

        //System.out.println(feed.originalWireFeed().toString());

        try {
            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/api/kafka"))
                    //.version(HttpClient.Version.HTTP_2)
                    .header("key1", "value1")
                    .header("key2", "value2")
                    .GET()
                    .build();
           // HttpResponse<String> response = HttpClient.newHttpClient()
             //       .send(request2);

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request2, HttpResponse.BodyHandlers.ofString());
            //.send(request, HttpResponse.BodyHandler.asString());

            LOG.info(response.toString());


/*
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://postman-echo.com/post"))
                    .headers("Content-Type", "text/plain;charset=UTF-8")
                    .POST(HttpRequest.BodyProcessor.fromString("Sample request body"))
                    .build(); */
            //LOG.info("her2e"+ request2.);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        feed.setTitle("test-title");
        feed.setDescription("test-description");
        feed.setLink("https://example.org");
        System.out.println(new SyndFeedOutput().outputString(feed));
        */

    }
}
