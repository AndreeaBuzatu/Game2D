package uk.ac.lancaster.scc210.game.resources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.deserialise.Deserialiser;

/**
 * The type Bullet deserialiser.
 */
public class BulletDeserialiser extends Deserialiser<SerialisedBullet> {
    /**
     * Instantiates a new Deserialiser.
     *
     * @param document the xml document
     * @throws ResourceNotFoundException if the resource cannot be created or found
     */
    public BulletDeserialiser(Document document) throws ResourceNotFoundException {
        super(document, "bullet");
    }

    @Override
    protected void deserialise() throws ResourceNotFoundException {
        if (document == null) {
            throw new ResourceNotFoundException("Could not find bullets");
        }

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundNode(node)) {
                Element elem = (Element) node;

                String name = elem.getAttribute("name");

                String texture = elem.getAttribute("texture");

                int speed = Integer.parseInt(elem.getAttribute("speed"));

                serialised.add(new SerialisedBullet(name, texture, speed));
            }
        }
    }
}
