package uk.ac.lancaster.scc210.resources.deserialise;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.ac.lancaster.scc210.content.TextureAtlas;
import uk.ac.lancaster.scc210.resources.ResourceNotFoundException;

class TextureDeserialiser extends Deserialiser<SerialisedTexture> {
    private TextureAtlas textureAtlas;

    TextureDeserialiser(NodeList nodes, TextureAtlas textureAtlas) throws ResourceNotFoundException {
        super(null, "texture");

        this.nodes = nodes;
        this.textureAtlas = textureAtlas;

        deserialise();
    }

    @Override
    protected void deserialise() throws ResourceNotFoundException {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundNode(node)) {
                Element elem = (Element) node;

                String name = elem.getAttribute("name");

                int row = Integer.parseInt(elem.getAttribute("row"));

                int column = Integer.parseInt(elem.getAttribute("column"));

                serialised.add(new SerialisedTexture(name, textureAtlas.get(row, column)));
            }
        }

    }
}

