import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Road extends JPanel {
    private TrafficLight light;
    private int numberOfSegments;
    private final int roadWidth = 120;
    final int roadYPosition;
    final int endRoadYPosition;
    final int roadXPosition;
    final int endRoadXPosition;
    private Color lightColor;
    private String orientation;
    String trafficDirection;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 1200, 1200);

        for(int z = 0; z < Map.roads.size(); ++z) {
            Road r = (Road)Map.roads.get(z);
            r.paintRoad(g);
            int c;
            Car car;
            if (r.getOrientation().equals("Vertical")) {
                for(c = 0; c < Map.cars.size(); ++c) {
                    car = (Car)Map.cars.get(c);
                    if (car.getRoadCarIsOn().equals(r)) {
                        car.paintMeVertical(g);
                    }
                }

                if (r.getTrafficLight() != null) {
                    r.paintLight(g);
                }
            } else {
                for(c = 0; c < Map.cars.size(); ++c) {
                    car = (Car)Map.cars.get(c);
                    if (car.getRoadCarIsOn().equals(r)) {
                        car.paintMeHorizontal(g);
                    }
                }

                if (r.getTrafficLight() != null) {
                    r.paintLight(g);
                }
            }
        }

    }

    public void paintLight(Graphics g) {
        g.setColor(this.lightColor);
        if (this.getOrientation().equals("Horizontal")) {
            if (this.getTrafficDirection().equals("east")) {
                g.fillRect(this.roadXPosition + this.numberOfSegments * 25 - 10, this.roadYPosition - 20, 10, 20);
                g.setColor(Color.black);
                g.drawRect(this.roadXPosition + this.numberOfSegments * 25 - 10, this.roadYPosition - 20, 10, 20);
            } else {
                g.fillRect(this.roadXPosition, this.roadYPosition - 20, 10, 20);
                g.setColor(Color.black);
                g.drawRect(this.roadXPosition, this.roadYPosition - 20, 10, 20);
            }
        } else if (this.getTrafficDirection().equals("south")) {
            g.fillRect(this.roadYPosition - 20, this.roadXPosition + this.numberOfSegments * 25 - 10, 20, 10);
            g.setColor(Color.black);
            g.drawRect(this.roadYPosition - 20, this.roadXPosition + this.numberOfSegments * 25 - 10, 20, 10);
        } else {
            g.fillRect(this.roadYPosition - 20, this.roadXPosition, 20, 10);
            g.setColor(Color.black);
            g.drawRect(this.roadYPosition - 20, this.roadXPosition, 20, 10);
        }

    }

    public void paintRoad(Graphics g) {
        int j;
        if (this.orientation.equals("Horizontal")) {
            g.setColor(Color.black);
            g.fillRect(this.roadXPosition, this.roadYPosition, this.numberOfSegments * 25, 120);
            g.setColor(Color.WHITE);

            for(j = 0; j < this.numberOfSegments * 25; j += 50) {
                g.fillRect(this.roadXPosition + j, this.roadYPosition + 60, 30, 2);
            }
        } else {
            g.setColor(Color.black);
            g.fillRect(this.roadYPosition, this.roadXPosition, 120, this.numberOfSegments * 25);
            g.setColor(Color.WHITE);

            for(j = 0; j < this.numberOfSegments * 25; j += 50) {
                g.fillRect(this.roadYPosition + 60, this.roadXPosition + j, 2, 30);
            }
        }

    }

    Road(int numberOfSegments, String orientation, int xPosition, int yPosition, String direction) {
        this.lightColor = Color.green;
        this.numberOfSegments = numberOfSegments * 2;
        this.orientation = orientation;
        this.roadXPosition = xPosition;
        this.roadYPosition = yPosition;
        this.endRoadXPosition = xPosition + numberOfSegments * 50;
        this.endRoadYPosition = yPosition + numberOfSegments * 50;
        this.trafficDirection = direction;
    }

    Road(int numOfSegments, String orientation, int xPosition, int yPosition, String direction, TrafficLight light) {
        this.lightColor = Color.green;
        this.numberOfSegments = numOfSegments * 2;
        this.orientation = orientation;
        this.light = light;
        this.roadXPosition = xPosition;
        this.roadYPosition = yPosition;
        this.endRoadXPosition = xPosition + numOfSegments * 50;
        this.endRoadYPosition = yPosition + numOfSegments * 50;
        this.trafficDirection = direction;
    }

    public String getOrientation() {
        return this.orientation;
    }

    public TrafficLight getTrafficLight() {
        return this.light;
    }

    public int getRoadLength() {
        return this.numberOfSegments;
    }

    public int getRoadYPosition() {
        return this.roadYPosition;
    }

    public int getRoadXPosition() {
        return this.roadXPosition;
    }

    public int getEndRoadYPosition() {
        return this.endRoadYPosition;
    }

    public int getEndRoadXPosition() {
        return this.endRoadXPosition;
    }

    public String getTrafficDirection() {
        return this.trafficDirection;
    }

    public void setLightColor(Color c) {
        this.lightColor = c;
    }
}

