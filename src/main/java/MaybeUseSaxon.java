import com.rometools.rome.io.SyndFeedOutput;
import com.saxonica.xqj.SaxonXQDataSource;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xquery.*;
import java.io.*;

public class MaybeUseSaxon {
    public static void main(String[] args) {
        try {
            execute();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XQException e) {
            e.printStackTrace();
        }


        /**
         * XSLT magic !!
         */

        // Create a transform factory instance.
        TransformerFactory tfactory = TransformerFactory.newInstance();

        // Create a transformer for the stylesheet.
        Transformer transformer = null;
        try {
            transformer = tfactory.newTransformer(new StreamSource(new File("src/main/resources/j3.xsl")));
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

        //
    }

    private static void execute() throws FileNotFoundException, XQException {
        InputStream inputStream = new FileInputStream(new File("xquery.xqy"));
        XQDataSource ds = new SaxonXQDataSource();
        XQConnection conn = ds.getConnection();
        XQPreparedExpression exp = conn.prepareExpression(inputStream);
        XQResultSequence result = exp.executeQuery();
        while (result.next()) {
            System.out.println(result.getItemAsString(null));
        }
    }
}