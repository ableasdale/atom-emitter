import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.DocumentManager;

public class PlainWriteToMarkLogic {
    public static void main(String[] args) {

        final DatabaseClient marklogic = DatabaseClientFactory
                .newClient("localhost", 8000,
                        new DatabaseClientFactory.DigestAuthContext("admin", "admin")
                );

        DocumentManager xmlDocMgr = marklogic.newXMLDocumentManager();

        xmlDocMgr.writeAs("/test123789.xml", "<test-123/>");
    }
}

