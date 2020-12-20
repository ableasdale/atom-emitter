import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;

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
