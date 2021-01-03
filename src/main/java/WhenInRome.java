import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
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

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        String url = "https://stackoverflow.com/feeds/tag?tagnames=rome";

        /*
            Rome
         */
        String jsonFeedData = "";
        String feedText = "";
        //SyndFeed feed = null;
        try {

            SyndFeedInput in = new SyndFeedInput();
            //in.setPreserveWireFeed(true);
            SyndFeed feed = in.build(new XmlReader(new URL(url)));
            //feed.setPreserveWireFeed(true);
            // wireFeed = feed.originalWireFeed();
            // This will "marshall" the feed back to Atom XML
            //LOG.info(new SyndFeedOutput().outputString(feed));
            feedText = new SyndFeedOutput().outputString(feed);
//LOG.info(feedText);
            /* convert to JSON using Jackson */
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode jsonNode = xmlMapper.readTree(new SyndFeedOutput().outputString(feed));
            ObjectMapper objectMapper = new ObjectMapper();
           jsonFeedData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

          //LOG.info("JSON:"+jsonFeedData);
        } catch (FeedException | IOException e) {
            LOG.error(String.format("Caught an exception: %s", e.getMessage()), e);
        }


        /*
            HTTP POST Using the Java 9 HttpClient - does get a 200 but will only deal with json right now! if I can only figure out how to send XML!
         */
        try {
            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/api/kafka"))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Content-Type", "text/plain")
                    .POST(HttpRequest.BodyPublishers.ofString(feedText))///HttpRequest.BodyProcessor.fromString(feedText))
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
        } catch (URISyntaxException | InterruptedException | IOException e) {
            LOG.error(String.format("Caught an exception: %s", e.getMessage()), e);
        }

        /*
           Rome: create a custom feed
         */
        SyndFeed feed1 = new SyndFeedImpl();
        //feed1.setFeedType("rss_2.0");
        feed1.setTitle("test-title");
        feed1.setDescription("test-description");
        feed1.setLink("https://example.org");
        feed1.setFeedType("atom_1.0");
        //feed.setType("text/html");
        try {
            LOG.info(new SyndFeedOutput().outputString(feed1));
        } catch (FeedException e) {
            LOG.error(String.format("Caught an exception: %s", e.getMessage()), e);
        }
    }
}
