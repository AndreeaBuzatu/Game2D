package uk.ac.lancaster.scc210.resources.deserialise;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.ac.lancaster.scc210.content.TextureAtlas;
import uk.ac.lancaster.scc210.resources.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

class TextureDeserialiser {
    static List<SerialisedTexture> deserialise(TextureAtlas textureAtlas, NodeList textureNodes) throws ResourceNotFoundException {
        List<SerialisedTexture> textures = new ArrayList<>();

        for (int i = 0; i < textureNodes.getLength(); i++) {
            Node node = textureNodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String name = elem.getAttribute("name");

                int row = Integer.parseInt(elem.getAttribute("row"));

                int column = Integer.parseInt(elem.getAttribute("column"));

                textures.add(new SerialisedTexture(name, textureAtlas.get(row, column)));
            }
        }

        return textures;
    }
}

