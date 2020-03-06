package uk.ac.lancaster.scc210.engine.gui;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;

import java.util.ArrayList;
import java.util.List;

public class TextContainer implements Drawable {
    private final List<Text> text;

    public TextContainer() {
        this.text = new ArrayList<>();
    }

    public void add(Text item) {
        text.add(item);
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        for (Text item : text) {
            renderTarget.draw(item);
        }
    }
}
