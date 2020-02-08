package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

public class SoundDeserialiser extends Deserialiser<SerialisedSound> {
    public SoundDeserialiser(Document document) throws ResourceNotFoundException {
        super(document, document.getElementsByTagName("sounds"), "sound");
    }

    @Override
    protected void deserialise() throws ResourceNotFoundException {
        nodes = document.getElementsByTagName("sound");

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundNode(node)) {
                Element elem = (Element) node;

                String name = elem.getAttribute("name");

                String src = elem.getAttribute("src");

                serialised.add(new SerialisedSound(name, src));
            }
        }
    }
}
