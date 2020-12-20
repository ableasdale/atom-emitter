import com.saxonica.xqj.SaxonXQDataSource;

import javax.xml.xquery.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MaybeUseSaxon {
    public static void main(String[] args) {
        try {
            execute();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XQException e) {
            e.printStackTrace();
        }
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