import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
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
//        LOG.info("here"+feed.getTitle());
//        LOG.info("here"+ wireFeed.toString());
//        LOG.info(wireFeed.getFeedType());
        //LOG.info("here"+feed.);
        //feedFetcher.setPreserveWireFeed(true);

        //System.out.println(feed.originalWireFeed().toString());

        try {
            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/api/kafka"))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Content-Type", "application/json")
                    //.header("key2", "value2")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"firstname\" : \"alex\",\"surname\" : \"bleasdale\"}"))///HttpRequest.BodyProcessor.fromString("Sample request body"))
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
            e.printStackTrace();
        }

        /**
         * XSLT magic
         */

            // Create a transform factory instance.
            TransformerFactory tfactory = TransformerFactory.newInstance();

            // Create a transformer for the stylesheet.
        Transformer transformer = null;
        try {
            transformer = tfactory.newTransformer(new StreamSource(new File("src/main/resources/j2.xsl")));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        SyndFeedOutput output = new SyndFeedOutput();
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       // Writer foo = new SyndFeedOutput().output(feed1, new OutputStreamWriter(baos));
       // Writer writer = new OutputStreamWriter();
                // Transform the source XML to System.out.

        try {
           // transformer.transform(new SyndFeedOutput().output(feed1, new OutputStreamWriter(baos)),
             //       new StreamResult(System.out));
            transformer.transform(new StreamSource(new File("src/main/resources/feed.xml")),

            new StreamResult(System.out));
        } catch (TransformerException e) {
            e.printStackTrace();
        }




    }
}
