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

          // LOG.info("JSON:"+jsonFeedData);

            HttpRequest request1 = null;
            try {
                request1 = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/api/kafka/json"))
                        .version(HttpClient.Version.HTTP_2)
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonFeedData))///HttpRequest.BodyProcessor.fromString(feedText))
                        .build();
                HttpClient client1 = HttpClient.newHttpClient();
                HttpResponse<String> response1 = client1.send(request1, HttpResponse.BodyHandlers.ofString());
                LOG.info("POSTED JSON:" + response1.toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // HttpResponse<String> response = HttpClient.newHttpClient()
            //       .send(request2);


            //.send(request, HttpResponse.BodyHandler.asString());


        } catch (FeedException | IOException e) {
            LOG.error(String.format("Caught an exception: %s", e.getMessage()), e);
        }


        /*
            HTTP POST Using the Java 9 HttpClient - send the XML (as text/plain) so it can be pushed into MarkLogic Server
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

            LOG.info("POSTED XML:" + response.toString());

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
