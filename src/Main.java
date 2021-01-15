import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main implements ActionListener, Runnable, MouseListener {
    private int x;
    private int y;
    private boolean running = false;
    private JFrame frame = new JFrame("Traffic Simulator");
    private TrafficLight light = new TrafficLight();
    Road roadStart;
    private JLabel info;
    private JLabel labelXPositionField;
    private JTextField xPositionField;
    private JLabel labelYPositionField;
    private JTextField yPositionField;
    private Container north;
    private JButton startSimulator;
    private JButton exitSimulator;
    private JButton removeRoad;
    private Container south;
    private Container west;
    private JButton addSedan;
    private JButton addBus;
    private JButton addSUV;
    private JButton addTruck;
    private JButton addSports;
    private JButton addSuperCar;
    private JButton addCoupe;
    private JButton addRoad;
    private ButtonGroup selections;
    private JRadioButton horizontal;
    private JRadioButton vertical;
    private ButtonGroup selections2;
    private JRadioButton hasLight;
    private JRadioButton noLight;
    private JLabel label;
    private JTextField length;
    private ButtonGroup selections3;
    private JRadioButton northDirection;
    private JRadioButton southDirection;
    private JRadioButton westDirection;
    private JRadioButton eastDirection;

    private int getX() {
        return this.x;
    }

    private int getY() {
        return this.y;
    }

    private Main() {
        this.roadStart = new Road(10, "Horizontal", 0, 250, "east", this.light);
        this.info = new JLabel("Click On Screen To Select x,y Position");
        this.labelXPositionField = new JLabel("Road x Position");
        this.xPositionField = new JTextField("0");
        this.labelYPositionField = new JLabel("Road y Position");
        this.yPositionField = new JTextField("0");
        this.north = new Container();
        this.startSimulator = new JButton("Start");
        this.exitSimulator = new JButton("Exit");
        this.removeRoad = new JButton("Remove Last Road");
        this.south = new Container();
        this.west = new Container();
        this.addSedan = new JButton("Add Sedan");
        this.addBus = new JButton("Add Bus");
        this.addSUV = new JButton("Add SUV");
        this.addTruck = new JButton("Add Truck");
        this.addSports = new JButton("Add Sports");
        this.addSuperCar = new JButton("Add SuperCar");
        this.addCoupe = new JButton("Add Coupe");
        this.addRoad = new JButton("Add Road");
        this.selections = new ButtonGroup();
        this.horizontal = new JRadioButton("Horizontal");
        this.vertical = new JRadioButton("Vertical");
        this.selections2 = new ButtonGroup();
        this.hasLight = new JRadioButton("Traffic Light");
        this.noLight = new JRadioButton("No Traffic Light");
        this.label = new JLabel("Enter Road Length");
        this.length = new JTextField("");
        this.selections3 = new ButtonGroup();
        this.northDirection = new JRadioButton("Upwards");
        this.southDirection = new JRadioButton("Downwards");
        this.westDirection = new JRadioButton("Leftwards");
        this.eastDirection = new JRadioButton("Rightwards");
        Map.roads.add(this.roadStart);
        this.frame.setSize(1270, 670);
        this.frame.setLayout(new BorderLayout());
        this.frame.add(this.roadStart, "Center");
        this.roadStart.addMouseListener(this);
        this.north.setLayout(new GridLayout(1, 5));
        this.north.add(this.info);
        this.north.add(this.labelXPositionField);
        this.north.add(this.xPositionField);
        this.north.add(this.labelYPositionField);
        this.north.add(this.yPositionField);
        this.frame.add(this.north, "North");
        this.south.setLayout(new GridLayout(1, 3));
        this.south.add(this.startSimulator);
        this.startSimulator.addActionListener(this);
        this.south.add(this.exitSimulator);
        this.exitSimulator.addActionListener(this);
        this.south.add(this.removeRoad);
        this.removeRoad.addActionListener(this);
        this.frame.add(this.south, "South");
        this.west.setLayout(new GridLayout(13, 1));
        this.west.add(this.addSedan);
        this.addSedan.addActionListener(this);
        this.west.add(this.addBus);
        this.addBus.addActionListener(this);
        this.west.add(this.addSUV);
        this.addSUV.addActionListener(this);
        this.west.add(this.addTruck);
        this.addTruck.addActionListener(this);
        this.west.add(this.addSports);
        this.addSports.addActionListener(this);
        this.west.add(this.addSuperCar);
        this.addSuperCar.addActionListener(this);
        this.west.add(this.addCoupe);
        this.addCoupe.addActionListener(this);
        this.west.add(this.addRoad);
        this.addRoad.addActionListener(this);
        this.west.add(this.label);
        this.west.add(this.length);
        this.length.addActionListener(this);
        this.selections.add(this.vertical);
        this.selections.add(this.horizontal);
        this.west.add(this.vertical);
        this.vertical.addActionListener(this);
        this.horizontal.setSelected(true);
        this.west.add(this.horizontal);
        this.horizontal.addActionListener(this);
        this.selections2.add(this.hasLight);
        this.selections2.add(this.noLight);
        this.west.add(this.hasLight);
        this.hasLight.addActionListener(this);
        this.west.add(this.noLight);
        this.noLight.addActionListener(this);
        this.noLight.setSelected(true);
        this.selections3.add(this.northDirection);
        this.selections3.add(this.southDirection);
        this.selections3.add(this.eastDirection);
        this.selections3.add(this.westDirection);
        this.west.add(this.northDirection);
        this.northDirection.addActionListener(this);
        this.northDirection.setEnabled(false);
        this.west.add(this.southDirection);
        this.southDirection.addActionListener(this);
        this.southDirection.setEnabled(false);
        this.west.add(this.eastDirection);
        this.eastDirection.addActionListener(this);
        this.eastDirection.setSelected(true);
        this.west.add(this.westDirection);
        this.westDirection.addActionListener(this);
        this.frame.add(this.west, "West");
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(3);
        Map.trafficLights.add(this.light);
        this.frame.repaint();
    }

    public static void main(String[] args) {
        new Main();
        new Map();
        new SoundManager();
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (this.horizontal.isSelected()) {
            this.northDirection.setEnabled(false);
            this.southDirection.setEnabled(false);
            this.eastDirection.setEnabled(true);
            this.westDirection.setEnabled(true);
        } else if (this.vertical.isSelected()) {
            this.eastDirection.setEnabled(false);
            this.westDirection.setEnabled(false);
            this.northDirection.setEnabled(true);
            this.southDirection.setEnabled(true);
        }

        if (source == this.startSimulator && !this.running) {
            this.running = true;
            Thread t = new Thread(this);
            t.start();
        }

        if (source == this.removeRoad && Map.roads.size() > 1) {
            Map.roads.remove(Map.roads.size() - 1);
            this.frame.repaint();
        }

        int x;
        if (source == this.addBus) {
            Bus bus = new Bus(this.roadStart);
            Map.cars.add(bus);

            for(x = this.roadStart.roadXPosition; x < bus.getRoadCarIsOn().getRoadLength() * 50; x += 30) {
                bus.setCarXPosition(x);
                bus.setCarYPosition(bus.getRoadCarIsOn().getRoadYPosition() + 5);
                if (!bus.collision(x, bus)) {
                    this.frame.repaint();
                    return;
                }
            }
        }

        if (source == this.addSedan) {
            Sedan sedan = new Sedan(this.roadStart);
            Map.cars.add(sedan);
            sedan.setCarYPosition(sedan.getRoadCarIsOn().getRoadYPosition() + 5);

            for(x = this.roadStart.roadXPosition; x < sedan.getRoadCarIsOn().getRoadLength() * 50; x += 30) {
                sedan.setCarXPosition(x);
                if (!sedan.collision(x, sedan)) {
                    this.frame.repaint();
                    return;
                }
            }
        }

        if (source == this.addSUV) {
            SUV suv = new SUV(this.roadStart);
            Map.cars.add(suv);

            for(x = this.roadStart.roadXPosition; x < suv.getRoadCarIsOn().getRoadLength() * 50; x += 30) {
                suv.setCarXPosition(x);
                suv.setCarYPosition(suv.getRoadCarIsOn().getRoadYPosition() + 5);
                if (!suv.collision(x, suv)) {
                    this.frame.repaint();
                    return;
                }
            }
        }

        if (source == this.addTruck) {
            Truck truck = new Truck(this.roadStart);
            Map.cars.add(truck);

            for(x = this.roadStart.roadXPosition; x < truck.getRoadCarIsOn().getRoadLength() * 50; x += 30) {
                truck.setCarXPosition(x);
                truck.setCarYPosition(truck.getRoadCarIsOn().getRoadYPosition() + 5);
                if (!truck.collision(x, truck)) {
                    this.frame.repaint();
                    return;
                }
            }
        }

        if (source == this.addSports) {
            Sports sports = new Sports(this.roadStart);
            Map.cars.add(sports);

            for(x = this.roadStart.roadXPosition; x < sports.getRoadCarIsOn().getRoadLength() * 50; x += 30) {
                sports.setCarXPosition(x);
                sports.setCarYPosition(sports.getRoadCarIsOn().getRoadYPosition() + 5);
                if (!sports.collision(x, sports)) {
                    this.frame.repaint();
                    return;
                }
            }
        }

        if (source == this.addSuperCar) {
            SuperCar supercar = new SuperCar(this.roadStart);
            Map.cars.add(supercar);

            for(x = this.roadStart.roadXPosition; x < supercar.getRoadCarIsOn().getRoadLength() * 50; x += 30) {
                supercar.setCarXPosition(x);
                supercar.setCarYPosition(supercar.getRoadCarIsOn().getRoadYPosition() + 5);
                if (!supercar.collision(x, supercar)) {
                    this.frame.repaint();
                    return;
                }
            }
        }

        if (source == this.addCoupe) {
            Coupe coupe = new Coupe(this.roadStart);
            Map.cars.add(coupe);

            for(x = this.roadStart.roadXPosition; x < coupe.getRoadCarIsOn().getRoadLength() * 50; x += 30) {
                coupe.setCarXPosition(x);
                coupe.setCarYPosition(coupe.getRoadCarIsOn().getRoadYPosition() + 5);
                if (!coupe.collision(x, coupe)) {
                    this.frame.repaint();
                    return;
                }
            }
        }

        if (source == this.addRoad) {
            int roadLength = 5;
            String orientation = "Horizontal";
            String direction = "east";
            int xPosition = 0;
            int yPosition = 0;
            Boolean lightOnRoad = false;
            if (this.vertical.isSelected()) {
                orientation = "Vertical";
            } else if (this.horizontal.isSelected()) {
                orientation = "Horizontal";
            }

            if (this.hasLight.isSelected()) {
                lightOnRoad = true;
            } else if (this.noLight.isSelected()) {
                lightOnRoad = false;
            }

            if (this.eastDirection.isSelected()) {
                direction = "east";
            } else if (this.westDirection.isSelected()) {
                direction = "west";
            } else if (this.northDirection.isSelected()) {
                direction = "north";
            } else if (this.southDirection.isSelected()) {
                direction = "south";
            }

            if (orientation.equals("Horizontal")) {
                yPosition = Integer.parseInt(this.yPositionField.getText());
                xPosition = Integer.parseInt(this.xPositionField.getText());
            } else if (orientation.equals("Vertical")) {
                xPosition = Integer.parseInt(this.yPositionField.getText());
                yPosition = Integer.parseInt(this.xPositionField.getText());
            }

            try {
                roadLength = Integer.parseInt(this.length.getText());
            } catch (Exception var10) {
                JOptionPane.showMessageDialog((Component)null, "Road Length Needs An Integer");
                this.length.setText("5");
            }

            Road road;
            if (lightOnRoad) {
                road = new Road(roadLength, orientation, xPosition, yPosition, direction, new TrafficLight());
                Map.roads.add(road);
            } else {
                road = new Road(roadLength, orientation, xPosition, yPosition, direction);
                Map.roads.add(road);
            }

            this.frame.repaint();
        }

        if (source == this.exitSimulator) {
            System.exit(0);
        }

    }

    public void mouseClicked(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        this.xPositionField.setText(Integer.toString(this.getX()));
        this.yPositionField.setText(Integer.toString(this.getY()));
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent mouseEvent) {
    }

    public void run() {
        boolean carCollision = false;
        ArrayList trueCases = new ArrayList();

        while(this.running) {
            try {
                Thread.sleep(300L);
            } catch (Exception var8) {
            }

            int i;
            for(i = 0; i < Map.roads.size(); ++i) {
                Road r = (Road)Map.roads.get(i);
                TrafficLight l = r.getTrafficLight();
                if (l != null) {
                    l.operate();
                    if (l.getColorNow().equals("Red")) {
                        r.setLightColor(Color.red);
                    } else {
                        r.setLightColor(Color.GREEN);
                    }
                }
            }

            for(i = 0; i < Map.cars.size(); ++i) {
                Car recentCar = (Car)Map.cars.get(i);
                String direction = recentCar.getRoadCarIsOn().getTrafficDirection();
                if ((recentCar.collision(recentCar.getCarXPosition() + 30, recentCar) || !direction.equals("east") && !direction.equals("south")) && (recentCar.collision(recentCar.getCarXPosition(), recentCar) || !direction.equals("west") && !direction.equals("north"))) {
                    int m;
                    for(m = 0; m < Map.cars.size(); ++m) {
                        Car otherCar = (Car)Map.cars.get(m);
                        if (otherCar.getCarYPosition() != recentCar.getCarYPosition()) {
                            if (recentCar.getCarXPosition() + recentCar.getCarWidth() < otherCar.getCarXPosition()) {
                                trueCases.add(true);
                            } else {
                                trueCases.add(false);
                            }
                        }
                    }

                    for(m = 0; m < trueCases.size(); ++m) {
                        if (!(Boolean)trueCases.get(m)) {
                            carCollision = true;
                            break;
                        }
                    }

                    if (!carCollision) {
                        recentCar.setCarYPosition(recentCar.getRoadCarIsOn().getRoadYPosition() + 60);
                    }

                    for(m = 0; m < trueCases.size(); ++m) {
                        trueCases.remove(m);
                    }

                    carCollision = false;
                } else {
                    recentCar.move();
                }
            }

            this.frame.repaint();
        }

    }
}

