package uk.ac.lancaster.scc210.resources.deserialise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.ac.lancaster.scc210.content.TextureAtlas;
import uk.ac.lancaster.scc210.resources.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TextureAtlasDeserialiser {
    private List<SerialisedTextureAtlas> serialisedTextureAtlases;

    private Document document;

    public TextureAtlasDeserialiser(Document document) throws ResourceNotFoundException {
        this.document = document;

        serialisedTextureAtlases = new ArrayList<>();

        deserialise();
    }

    private void deserialise() throws ResourceNotFoundException {
        NodeList nodes = document.getElementsByTagName("atlas");

        if (nodes == null) {
            throw new ResourceNotFoundException("Could not find texture_atlas");
        }

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String src = elem.getAttribute("src");

                int textureWidth = Integer.parseInt(elem.getAttribute("texture_width"));

                int textureHeight = Integer.parseInt(elem.getAttribute("texture_height"));

                TextureAtlas textureAtlas = new TextureAtlas(src, textureWidth, textureHeight);

                serialisedTextureAtlases.add(
                        new SerialisedTextureAtlas(
                                textureAtlas,
                                TextureDeserialiser.deserialise(textureAtlas, elem.getChildNodes())));
            }
        }
    }

    public List<SerialisedTextureAtlas> getSerialisedTextureAtlases() {
        return serialisedTextureAtlases;
    }
}

