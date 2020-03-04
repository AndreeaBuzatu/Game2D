package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

/**
 * The type Music deserialiser.
 */
public class MusicDeserialiser extends Deserialiser<SerialisedMusic> {
    /**
     * Instantiates a new Music deserialiser.
     *
     * @param document the document
     * @throws ResourceNotFoundException the resource not found exception
     */
    public MusicDeserialiser(Document document) throws ResourceNotFoundException {
        super(document, "music", "piece");
    }

    @Override
    protected void deserialise() throws ResourceNotFoundException {
        nodes = document.getElementsByTagName("piece");

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundNode(node)) {
                Element elem = (Element) node;

                String name = elem.getAttribute("name");

                String src = elem.getAttribute("src");

                serialised.add(new SerialisedMusic(name, src));
            }
        }
    }
}
