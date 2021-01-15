import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;

public class Sports extends Car {
    Image image1;
    Image image2;
    Image image3;
    Image image4;

    Sports(Road road) {
        super(road);
        this.width = 95;
        this.height = 45;

        try {
            this.image1 = ImageIO.read(new File("resources/car1.png"));
            this.image2 = ImageIO.read(new File("resources/car2.png"));
            this.image3 = ImageIO.read(new File("resources/car2.png"));
            this.image4 = ImageIO.read(new File("resources/car2.png"));
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void paintMeHorizontal(Graphics g) {
        if (this.getRoadCarIsOn().getTrafficDirection().equals("east")) {
            g.drawImage(this.image1, this.xPosition, this.yPosition, (ImageObserver)null);
        } else if (this.getRoadCarIsOn().getTrafficDirection().equals("west")) {
            g.drawImage(this.image2, this.xPosition, this.yPosition, (ImageObserver)null);
        }

    }

    public void paintMeVertical(Graphics g) {
        if (this.getRoadCarIsOn().getTrafficDirection().equals("north")) {
            g.drawImage(this.image3, this.xPosition, this.yPosition, (ImageObserver)null);
        } else if (this.getRoadCarIsOn().getTrafficDirection().equals("south")) {
            g.drawImage(this.image4, this.xPosition, this.yPosition, (ImageObserver)null);
        }

    }
}

