package uk.ac.lancaster.scc210.engine.resources;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * The type Xml adapter.
 */
public class XMLAdapter extends Resource<Document> {
    /**
     * Instantiates a new Xml adapter.
     */
    public XMLAdapter() {
        super(null);
    }

    @Override
    public void loadFromFile(Path path) throws IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            resource = builder.parse(new File(path.toString()));

            resource.getDocumentElement().normalize();

        } catch (ParserConfigurationException | SAXException e) {
            throw new IOException(e);
        }
    }
}
