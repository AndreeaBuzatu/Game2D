package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import uk.ac.lancaster.scc210.engine.content.TextureAtlasManager;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

/**
 * Given appropriate XML, attempts to deserialise into a SerialisedAnimation
 */
public class AnimationDeserialiser extends Deserialiser<SerialisedAnimation> {
    private TextureAtlasManager atlasManager;

    /**
     * Instantiates a new Animation deserialiser.
     *
     * @param atlasManager the atlas manager. Used to get frames for the Animation
     * @param document     the XML document
     * @throws ResourceNotFoundException if the animation can't be found or constructed
     */
    public AnimationDeserialiser(TextureAtlasManager atlasManager, Document document) throws ResourceNotFoundException {
        super(document, "animation");

        this.atlasManager = atlasManager;

        deserialise();
    }

    @Override
    protected void deserialise() {
        if (atlasManager == null) {
            return;
        }

        nodes = document.getElementsByTagName("animation");

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundNode(node)) {
                Element elem = (Element) node;

                String name = elem.getAttribute("name");

                String atlas = elem.getAttribute("atlas");

                int row = Integer.parseInt(elem.getAttribute("row"));

                int column = Integer.parseInt(elem.getAttribute("column"));

                int numFrames = Integer.parseInt(elem.getAttribute("num_frames"));

                serialised.add(new SerialisedAnimation(atlasManager.get(atlas), name, row, column, numFrames));
            }
        }
    }
}
