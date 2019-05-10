import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

public final class XMLUtil {

  public static void write(Document doc, OutputStream out) throws IOException {
    try {
      Transformer t = TransformerFactory.newInstance().newTransformer();
      DocumentType dt = doc.getDoctype();
      if (dt != null) {
        String pub = dt.getPublicId();
        if (pub != null) {
          t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, pub);
        }
        t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dt.getSystemId());
      }
      t.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // NOI18N
      t.setOutputProperty(OutputKeys.INDENT, "yes"); // NOI18N
      t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // NOI18N
      Source source = new DOMSource(doc);
      Result result = new StreamResult(out);
      t.transform(source, result);
    } catch (Exception e) {
      throw (IOException) new IOException(e.toString()).initCause(e);
    } catch (TransformerFactoryConfigurationError e) {
      throw (IOException) new IOException(e.toString()).initCause(e);
    }
  }

}