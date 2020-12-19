import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.w3._2005.atom.FeedType;

import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.net.URL;

public class Demo {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance("org.w3._2005.atom");

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        //URL url = new URL("http://bdoughan.blogspot.com/atom.xml");
        URL url = new URL("http://blog.msbbc.co.uk/atom.xml");
        InputStream xml = url.openStream();
        JAXBElement<FeedType> feed = unmarshaller.unmarshal(new StreamSource(xml), FeedType.class);
        xml.close();

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(feed, System.out);
    }

}