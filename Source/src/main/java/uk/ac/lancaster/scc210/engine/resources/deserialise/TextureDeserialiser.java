package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.ac.lancaster.scc210.engine.content.TextureAtlas;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

/**
 * Deserialises a texture from a XML document
 */
class TextureDeserialiser extends Deserialiser<SerialisedTexture> {
    private TextureAtlas textureAtlas;

    /**
     * Instantiates a new Texture deserialiser.
     *
     * @param nodes        a series of Nodes to be used for deserialisation
     * @param textureAtlas the texture atlas to draw Textures from
     * @throws ResourceNotFoundException the Texture could not be found or created
     */
    TextureDeserialiser(NodeList nodes, TextureAtlas textureAtlas) throws ResourceNotFoundException {
        super(null, "texture");

        this.nodes = nodes;
        this.textureAtlas = textureAtlas;

        deserialise();
    }

    @Override
    protected void deserialise() {
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

