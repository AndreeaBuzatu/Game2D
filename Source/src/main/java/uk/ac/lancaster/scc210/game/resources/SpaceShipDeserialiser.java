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

                String texture  = elem.getAttribute("texture");

                String[] items = elem.getAttribute("items").split("\\s*,\\s*");

                String bullet = elem.getAttribute("bullet");

                String pattern = elem.getAttribute("pattern");

                int speed = Integer.parseInt(elem.getAttribute("speed"));

                int score = Integer.parseInt(elem.getAttribute("score"));

                int lives = Integer.parseInt(elem.getAttribute("lives"));

                String firingSound = elem.getAttribute("firing-sound");

                String hitSound = elem.getAttribute("hit-sound");

                if (animation != null) {
                    serialised.add(new SerialisedSpaceShip(name, animation, items, bullet, pattern, speed, score, lives, firingSound, hitSound, texture));
                }
            }
        }
    }
}
