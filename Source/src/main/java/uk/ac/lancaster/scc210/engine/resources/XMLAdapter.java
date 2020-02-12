package uk.ac.lancaster.scc210.engine.resources;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Adapts the loading of an XML document into a generic Resource.
 */
public class XMLAdapter extends Resource<Document> {
    /**
     * Instantiates a new Xml adapter.
     */
    public XMLAdapter() {
        super(null);
    }

    @Override
    public void loadFromFile(InputStream stream) throws IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            resource = builder.parse(stream);

            resource.getDocumentElement().normalize();

        } catch (ParserConfigurationException | SAXException e) {
            throw new IOException(e);
        }
    }
}
