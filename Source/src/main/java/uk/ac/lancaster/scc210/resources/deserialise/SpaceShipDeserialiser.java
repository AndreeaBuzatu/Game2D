package uk.ac.lancaster.scc210.resources.deserialise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.ac.lancaster.scc210.resources.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class SpaceShipDeserialiser {
    private List<SerialisedSpaceShip> serialisedSpaceShips;

    private Document document;

    public SpaceShipDeserialiser(Document document) throws ResourceNotFoundException {
        this.document = document;

        serialisedSpaceShips = new ArrayList<>();

        deserialise();
    }

    private void deserialise() throws ResourceNotFoundException {
        NodeList nodes = document.getDocumentElement().getChildNodes();

        if (nodes == null) {
            throw new ResourceNotFoundException("Could not find spaceships");
        }

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("spaceship")) {
                Element elem = (Element) node;

                String name = elem.getAttribute("name");

                String texture = elem.getAttribute("texture");

                int speed = Integer.parseInt(elem.getAttribute("speed"));

                serialisedSpaceShips.add(new SerialisedSpaceShip(name, texture, speed));
            }
        }
    }

    public List<SerialisedSpaceShip> getSerialisedSpaceShips() {
        return serialisedSpaceShips;
    }
}
