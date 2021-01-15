import java.awt.Graphics;
import java.util.ArrayList;

public class Car {
    private Road road;
    protected int yPosition;
    protected int xPosition;
    protected int height;
    protected int width;

    public void paintMeHorizontal(Graphics g) {
    }

    public void paintMeVertical(Graphics g) {
    }

    Car(Road road) {
        this.road = road;
        this.yPosition = this.getRoadCarIsOn().roadYPosition;
        this.xPosition = this.getRoadCarIsOn().roadXPosition;
    }

    public Road getRoadCarIsOn() {
        return this.road;
    }

    public int getCarXPosition() {
        return this.xPosition;
    }

    public void setCarXPosition(int x) {
        this.xPosition = x;
    }

    public int getCarYPosition() {
        return this.yPosition;
    }

    public void setCarYPosition(int y) {
        this.yPosition = y;
    }

    public int getCarWidth() {
        return this.width;
    }

    private void setRecentRoad(Road road) {
        this.road = road;
    }

    private boolean checkIfAtEndOfRoad() {
        if (!this.getRoadCarIsOn().getTrafficDirection().equals("east") && !this.getRoadCarIsOn().getTrafficDirection().equals("south")) {
            if (!this.getRoadCarIsOn().getTrafficDirection().equals("west") && !this.getRoadCarIsOn().getTrafficDirection().equals("north")) {
                return true;
            } else {
                return this.xPosition <= this.road.getRoadXPosition();
            }
        } else {
            return this.xPosition + this.width >= this.getRoadCarIsOn().getEndRoadXPosition();
        }
    }

    public boolean collision(int x, Car car) {
        String direction = this.getRoadCarIsOn().getTrafficDirection();

        for(int i = 0; i < Map.cars.size(); ++i) {
            Car c = (Car)Map.cars.get(i);
            if (c.getRoadCarIsOn() == this.getRoadCarIsOn() && car.getCarYPosition() == c.getCarYPosition()) {
                int otherCarXPosition = c.getCarXPosition();
                int otherCarWidth = c.getCarWidth();
                if (!car.equals(c)) {
                    if (x >= otherCarXPosition + otherCarWidth || x + otherCarWidth <= otherCarXPosition || !direction.equals("east") && !direction.equals("south")) {
                        if (x >= otherCarXPosition + otherCarWidth * 2 - 15 || x + car.getCarWidth() <= otherCarXPosition || !direction.equals("west") && !direction.equals("north")) {
                            continue;
                        }

                        return true;
                    }

                    return true;
                }
            }
        }

        return false;
    }

    private boolean canMoveForward() {
        String direction = this.getRoadCarIsOn().getTrafficDirection();
        if (this.xPosition + this.width >= this.getRoadCarIsOn().getRoadLength() * 25 - 25 + this.getRoadCarIsOn().getRoadXPosition() && (direction.equals("east") || direction.equals("south")) || this.xPosition <= this.getRoadCarIsOn().getRoadXPosition() + 25 && (direction.equals("west") || direction.equals("north"))) {
            if (this.getRoadCarIsOn().getTrafficLight() == null) {
                return true;
            } else {
                TrafficLight light = this.getRoadCarIsOn().getTrafficLight();
                return light.getColorNow().equals("Green");
            }
        } else {
            return true;
        }
    }

    private int getIndexOfRoadNow() {
        return Map.roads.indexOf(this.road);
    }

    private Road nextRoad() {
        Road roadNow = (Road)Map.roads.get(this.getIndexOfRoadNow());
        Road nextRoad = (Road)Map.roads.get(0);
        ArrayList<Integer> xPositions = new ArrayList();
        ArrayList<Integer> yPositions = new ArrayList();
        int roadXPositionNow;
        int roadYPositionNow;
        int roadEndXPositionNow;
        int roadEndYPositionNow;
        if (roadNow.getOrientation().equals("Vertical")) {
            roadXPositionNow = roadNow.getRoadYPosition();
            roadYPositionNow = roadNow.getRoadXPosition();
            roadEndXPositionNow = roadNow.getEndRoadYPosition();
            roadEndYPositionNow = roadNow.getEndRoadXPosition();
        } else {
            roadXPositionNow = roadNow.getRoadXPosition();
            roadYPositionNow = roadNow.getRoadYPosition();
            roadEndXPositionNow = roadNow.getEndRoadXPosition();
            roadEndYPositionNow = roadNow.getEndRoadYPosition();
        }

        int number;
        for(number = 0; number < Map.roads.size(); ++number) {
            Road r = (Road)Map.roads.get(number);
            if (r != roadNow) {
                int otherRoadXPosition;
                int otherRoadYPosition;
                int otherRoadEndXPosition;
                int otherRoadEndYPosition;
                if (r.getOrientation().equals("Horizontal")) {
                    otherRoadXPosition = r.getRoadXPosition();
                    otherRoadYPosition = r.getRoadYPosition();
                    otherRoadEndXPosition = r.getEndRoadXPosition();
                    otherRoadEndYPosition = r.getEndRoadYPosition();
                } else {
                    otherRoadXPosition = r.getRoadYPosition();
                    otherRoadYPosition = r.getRoadXPosition();
                    otherRoadEndXPosition = r.getEndRoadYPosition();
                    otherRoadEndYPosition = r.getEndRoadXPosition();
                }

                if (roadNow.getTrafficDirection().equals("east") && otherRoadXPosition > roadEndXPositionNow) {
                    xPositions.add(otherRoadXPosition);
                } else if (roadNow.getTrafficDirection().equals("west") && otherRoadEndXPosition < roadXPositionNow) {
                    xPositions.add(otherRoadEndXPosition);
                } else if (roadNow.getTrafficDirection().equals("north") && otherRoadEndYPosition < roadYPositionNow) {
                    yPositions.add(otherRoadEndYPosition);
                } else if (roadNow.getTrafficDirection().equals("south") && otherRoadYPosition > roadEndYPositionNow) {
                    yPositions.add(otherRoadYPosition);
                }
            }
        }

        number = this.getCarXPosition();
        int number2 = this.getCarYPosition();
        int index = 0;
        int index2 = 0;
        int difference_1 = 10000;
        int difference_2 = 10000;
        int closestXPosition;
        int closestYPosition;
        if (!roadNow.getTrafficDirection().equals("east") && !roadNow.getTrafficDirection().equals("west")) {
            if (roadNow.getTrafficDirection().equals("south") || roadNow.getTrafficDirection().equals("north")) {
                for(closestXPosition = 0; closestXPosition < xPositions.size(); ++closestXPosition) {
                    closestYPosition = Math.abs((Integer)yPositions.get(closestXPosition) - number2);
                    if (closestYPosition < difference_2) {
                        index2 = closestXPosition;
                        difference_2 = closestYPosition;
                    }
                }
            }
        } else {
            for(closestXPosition = 0; closestXPosition < xPositions.size(); ++closestXPosition) {
                closestYPosition = Math.abs((Integer)xPositions.get(closestXPosition) - number);
                if (closestYPosition < difference_1) {
                    index = closestXPosition;
                    difference_1 = closestYPosition;
                }
            }
        }

        closestXPosition = 0;
        closestYPosition = 0;
        if (!roadNow.getTrafficDirection().equals("east") && !roadNow.getTrafficDirection().equals("west")) {
            closestYPosition = (Integer)yPositions.get(index2);
        } else {
            closestXPosition = (Integer)xPositions.get(index);
        }

        System.out.println(closestXPosition);

        for(int z = 0; z < Map.roads.size(); ++z) {
            Road r = (Road)Map.roads.get(z);
            if ((r.getRoadXPosition() == closestXPosition || r.getEndRoadXPosition() == closestXPosition) && r.getOrientation().equals("Horizontal")) {
                nextRoad = r;
            } else if ((r.getRoadYPosition() == closestXPosition || r.getEndRoadYPosition() == closestXPosition) && r.getOrientation().equals("Vertical")) {
                nextRoad = r;
            }

            if ((r.getRoadYPosition() == closestYPosition || r.getEndRoadXPosition() == closestYPosition) && r.getOrientation().equals("Horizontal")) {
                nextRoad = r;
            } else if ((r.getRoadXPosition() == closestYPosition || r.getEndRoadXPosition() == closestYPosition) && r.getOrientation().equals("Vertical")) {
                nextRoad = r;
            }
        }

        xPositions.clear();
        yPositions.clear();
        return nextRoad;
    }

    public void move() {
        if (this.canMoveForward()) {
            if (!this.road.getTrafficDirection().equals("east") && !this.road.getTrafficDirection().equals("south")) {
                if (this.road.getTrafficDirection().equals("west") || this.road.getTrafficDirection().equals("north")) {
                    this.xPosition -= 25;
                }
            } else {
                this.xPosition += 25;
            }

            if (this.checkIfAtEndOfRoad()) {
                try {
                    Road r = this.nextRoad();
                    this.setRecentRoad(r);
                    int x;
                    if (r.getOrientation().equals("Horizontal") && r.getTrafficDirection().equals("east") || r.getOrientation().equals("Vertical") && r.getTrafficDirection().equals("south")) {
                        for(x = r.getRoadXPosition(); x + this.getCarWidth() < r.getRoadLength() * 25 + r.getEndRoadXPosition(); x += 30) {
                            this.setCarXPosition(x);
                            this.setCarYPosition(this.getRoadCarIsOn().getRoadYPosition() + 5);
                            if (!this.collision(x, this)) {
                                return;
                            }
                        }
                    } else if (r.getOrientation().equals("Horizontal") && r.getTrafficDirection().equals("west") || r.getOrientation().equals("Vertical") && r.getTrafficDirection().equals("north")) {
                        for(x = r.getRoadXPosition() + r.getRoadLength() * 25 - this.getCarWidth(); x > r.getRoadXPosition(); x -= 30) {
                            this.setCarXPosition(x);
                            this.setCarYPosition(this.getRoadCarIsOn().getRoadYPosition() + 5);
                            if (!this.collision(x, this)) {
                                return;
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException var3) {
                    this.setRecentRoad(this.road);
                    this.xPosition = this.road.getRoadXPosition();
                    this.yPosition = this.road.getRoadYPosition() + 5;
                }
            }
        }

    }
}

