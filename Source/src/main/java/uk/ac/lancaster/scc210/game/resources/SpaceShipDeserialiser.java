package uk.ac.lancaster.scc210.game.resources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.deserialise.Deserialiser;

/**
 * Given appropriate XML, attempts to deserialise into a SerialisedSpaceShip
 */
public class SpaceShipDeserialiser extends Deserialiser<SerialisedSpaceShip> {
    /**
     * Instantiates a new Space ship deserialiser.
     *
     * @param document the XML document
     * @throws ResourceNotFoundException the resource could not be found or created
     */
    public SpaceShipDeserialiser(Document document) throws ResourceNotFoundException {
        super(document, "spaceship");
    }

    @Override
    public void deserialise() throws ResourceNotFoundException {
        if (document == null) {
            throw new ResourceNotFoundException("Could not find spaceships");
        }

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundNode(node)) {
                Element elem = (Element) node;

                String name = elem.getAttribute("name");

                String animation = elem.getAttribute("animation");

                int speed = Integer.parseInt(elem.getAttribute("speed"));

                String bullet = elem.getAttribute("bullet");

                if (animation != null) {
                    serialised.add(new SerialisedSpaceShip(name, animation, bullet, speed));
                }
            }
        }
    }
}
