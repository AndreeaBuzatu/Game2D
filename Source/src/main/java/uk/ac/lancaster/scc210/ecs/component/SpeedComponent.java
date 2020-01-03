package uk.ac.lancaster.scc210.ecs.component;

public class SpeedComponent implements Component {
    private int speed;

    public SpeedComponent(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
