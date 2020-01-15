package uk.ac.lancaster.scc210.resources.deserialise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import uk.ac.lancaster.scc210.resources.ResourceNotFoundException;

public class SpaceShipDeserialiser extends Deserialiser<SerialisedSpaceShip> {
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

                String texture = elem.getAttribute("texture");

                int speed = Integer.parseInt(elem.getAttribute("speed"));

                serialised.add(new SerialisedSpaceShip(name, texture, speed));
            }
        }

    }
}
