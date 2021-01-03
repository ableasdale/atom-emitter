import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3._2005.atom.FeedType;

import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.net.URL;

public class Demo {

    private Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance("org.w3._2005.atom");

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        unmarshaller.setEventHandler(new jakarta.xml.bind.helpers.DefaultValidationEventHandler());

        //URL url = new URL("http://bdoughan.blogspot.com/atom.xml");
        URL url = new URL("http://blog.msbbc.co.uk/atom.xml");
        InputStream xml = url.openStream();
        JAXBElement<FeedType> feed = unmarshaller.unmarshal(new StreamSource(xml), FeedType.class);

        FeedType f = feed.getValue();
      //  JAXBElement<ArrayOfLeadRecord> r = l.getLeadRecordList() ;
       // List<LeadRecordList> leadRecordList = r.getValue().getLeadRecordList();
        xml.close();

        /*
        URL url2 = new URL("http://blog.msbbc.co.uk/atom.xml");
        InputStream xml2 = url2.openStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(FeedType.class);
        //Jaxb2
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        //jaxbUnmarshaller.setProperty();
        FeedType que = (FeedType) jaxbUnmarshaller.unmarshal(new StreamSource(xml2));

        JAXBContext jc = JAXBContext.newInstance(FeedType.class);

        FeedType rootElement = new FeedType();

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://exyus.com/xcs/tasklist/source/\\?f=put_atom.xsd");
        marshaller.marshal(rootElement, System.out);
*/


        // for (FeedType f : feed)

         //   feed.

        /*... and back to XML
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(feed, System.out);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://exyus.com/xcs/tasklist/source/\\?f=put_atom.xsd");
        marshaller.marshal(rootElement, System.out);

*/



       // Unmarshaller jaxbUnmarshaller = jc.createUnmarshaller();
        //FeedType ft = (FeedType) jaxbUnmarshaller.unmarshal( url );

        //JAXBElement<FeedType> po =  jaxbUnmarshaller.unmarshal(new StreamSource(xml), FeedType.class);
        System.out.println("jhere");
    }

}