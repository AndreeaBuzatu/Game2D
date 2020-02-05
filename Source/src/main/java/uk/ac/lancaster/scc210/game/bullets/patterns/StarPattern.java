package uk.ac.lancaster.scc210.game.bullets.patterns;

import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class StarPattern extends Pattern{

    private Vector2f[] coordinates;


    public StarPattern(World world, Entity spaceShip) {
        super(world, spaceShip, new Entity[8]);

        float startPoint =  - bulletSprite.getSprite().getLocalBounds().width/2;
        float width = spaceShipSprite.getSprite().getLocalBounds().width;
        float height = spaceShipSprite.getSprite().getLocalBounds().height;
        float equalDistance = (float) ( Math.sqrt(Math.pow(height,2) + Math.pow(width,2))/Math.sqrt(2));
        float difference = (float) (equalDistance*(Math.sqrt(2) - 1)/2);

        coordinates = new Vector2f[8];

        coordinates[0] = new Vector2f(startPoint , startPoint);
        coordinates[1] = new Vector2f( startPoint +equalDistance/2 ,  - difference);
        coordinates[2] = new Vector2f(startPoint/2 + equalDistance , startPoint);
        coordinates[3] = new Vector2f(startPoint + equalDistance + difference  , startPoint + equalDistance/2);
        coordinates[4] = new Vector2f( equalDistance,  equalDistance);
        coordinates[5] = new Vector2f(-startPoint + equalDistance/2 , equalDistance + difference );
        coordinates[6] = new Vector2f( -startPoint, width - startPoint);
        coordinates[7] = new Vector2f(-startPoint - difference , -startPoint + equalDistance/2);
    }

    @Override
    public void fire() {
        for(int i = 0; i < 8; i++)
        {
            SpriteComponent bulletSprite = (SpriteComponent) bullets[i].findComponent(SpriteComponent.class);

           Vector2f bulletPos = spaceShipSprite.getSprite().getTransform().transformPoint(coordinates[i]);
            bulletSprite.getSprite().setPosition(bulletPos);
            bulletSprite.getSprite().setRotation(spaceShipSprite.getSprite().getRotation() + (i -1)*45);
            world.addEntity(bullets[i]);


        }


    }
}
