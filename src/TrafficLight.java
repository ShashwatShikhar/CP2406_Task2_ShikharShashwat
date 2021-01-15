public class TrafficLight {
    private double rateOfChange = 0.5D;
    private String colorNow = "Green";
    private int redTime = 0;
    private int greenTime = 0;

    public TrafficLight() {
    }

    public String getColorNow() {
        return this.colorNow;
    }

    public void operate() {
        if (this.colorNow.equals("Red")) {
            this.rateOfChange = 1.0D;
            ++this.redTime;
        } else {
            this.rateOfChange = 0.0D;
            ++this.greenTime;
        }

        if (this.redTime == 12 || this.greenTime == 8) {
            this.redTime = 0;
            this.greenTime = 0;
            this.rateOfChange = 0.3D;
        }

        double num = Math.random();
        if (num < this.rateOfChange) {
            this.colorNow = "Red";
        } else {
            this.colorNow = "Green";
        }

    }
}

