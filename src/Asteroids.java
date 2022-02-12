import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Vector;
import java.util.Random;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;

import javax.imageio.ImageIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

/**
 * TAG SOME GIT COMMANDS HERE OR LINKS TO HELPFUL SITES/VIDEOS
 */

/**
 * zeek asteroid notes
 * check line 80
 */

public class Asteroids {
    public Asteroids() {
        setup();
    }

    public static void setup() {
        timer = Instant.now();
        appFrame = new JFrame("Asteroids");
        XOFFSET = 0;
        YOFFSET = 0;
        WINWIDTH = 700;
        WINHEIGHT = 750;
        pi = 3.14159265358979;
        twoPi = 2.0 * 3.14159265358979;
        endgame = false;

        // TODO: Make sure that entities' width and height match the png width and height
        p2width = 15.0;
        p2height = 20.0;
        p2originalX = (double) XOFFSET + ((double) WINWIDTH / 2.0) - (p1width / 2.0);
        p2originalY = (double) YOFFSET + ((double) WINHEIGHT / 2.0) - (p1height / 2.0);

        p1width = 15.0;
        p1height = 20.0;
        p1originalX = (double) XOFFSET + ((double) WINWIDTH / 2.0) - (p1width / 2.0);
        p1originalY = (double) YOFFSET + ((double) WINHEIGHT / 2.0) - (p1height / 2.0);


        flamewidth = 52;

        level = 3;

        walls.add(new ImageObject(32,195,270,5, 0.0));
        walls.add(new ImageObject(35,189,5,485,0.0));
        walls.add(new ImageObject(83,235,170,5,0.0));
        walls.add(new ImageObject(83,239,5,377,0.0));
        walls.add(new ImageObject(83,630,497,5,0.0));
        walls.add(new ImageObject(85,700,497,5,0.0));
        walls.add(new ImageObject(595,160,5,465,0.0));
        walls.add(new ImageObject(660,163,5,465,0.0));
        walls.add(new ImageObject(516,157,66,5,0.0));
        walls.add(new ImageObject(516,110,66,5,0.0));
        walls.add(new ImageObject(462,157,5,388,0.0));
        walls.add(new ImageObject(513,157,5,388,0.0));
        walls.add(new ImageObject(300,541,165,5,0.0));
        walls.add(new ImageObject(300,590,165,5,0.0));
        walls.add(new ImageObject(300,237,5,310,0.0)); // check here
        walls.add(new ImageObject(250,237,5,310,0.0));

        try {
            background = ImageIO.read(new File("src/pictures/Background.png"));
            player = ImageIO.read(new File("src/pictures/Player1Car.png"));
            player2 = ImageIO.read(new File("src/pictures/Player2Car.png"));
            frontLight = ImageIO.read(new File("src/pictures/carLights.png"));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static class Animate implements Runnable {
        public void run() {
            while (endgame == false) {
                backgroundDraw();
                explosionsDraw(timer);
                player2Draw();
                playerDraw();
                flameDraw();
                flameDrawP2();
                try {
                    Thread.sleep(32);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class PlayerMover implements Runnable {
        public PlayerMover() {
            velocitystep = 0.01;
            rotatestep = 0.01;
        }

        public void run() {
            while (endgame == false) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                }

                if (upPressed == true && p1velocity <= 1) { //This allows more or less control over max velocity
                    p1velocity = p1velocity + velocitystep;
                }else{
                    p1velocity = p1velocity;
                }

                if (downPressed == true && p1velocity >= -1) {
                    p1velocity = p1velocity - velocitystep;
                }else{
                    p1velocity = p1velocity;
                }

                if (leftPressed == true) {
                    if (p1velocity < 0) {
                        p1.rotate(-rotatestep);
                    } else {
                        p1.rotate(rotatestep);
                    }
                }

                if (rightPressed == true) {
                    if (p1velocity < 0) {
                        p1.rotate(rotatestep);
                    } else {
                        p1.rotate(-rotatestep);
                    }
                }

                p1.updateBounce();
                p1.move(-p1velocity * Math.cos(p1.getAngle() - pi / 2.0), p1velocity * Math.sin(p1.getAngle() - pi / 2.0));
                p1.screenWrap(XOFFSET, XOFFSET + WINWIDTH, YOFFSET, YOFFSET + WINHEIGHT);
            }
        }

        private double velocitystep;
        private double rotatestep;
    }

    private static class Player2Mover implements Runnable {
        public Player2Mover() {
            velocitystep = 0.01;
            rotatestep = 0.01;
        }

        public void run() {
            while (endgame == false) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                }

                if (upPressedP2 == true && p2velocity <= 1) { //This allows more or less control over max velocity
                    p2velocity = p2velocity + velocitystep;
                }else{
                    p2velocity = p2velocity;
                }

                if (downPressedP2 == true && p2velocity >= -1) {
                    p2velocity = p2velocity - velocitystep;
                }else{
                    p2velocity = p2velocity;
                }

                if (leftPressedP2 == true) {
                    if (p2velocity < 0) {
                        p2.rotate(-rotatestep);
                    } else {
                        p2.rotate(rotatestep);
                    }
                }
                if (rightPressedP2 == true) {
                    if (p2velocity < 0) {
                        p2.rotate(rotatestep);
                    } else {
                        p2.rotate(-rotatestep);
                    }
                }
                p2.updateBounce();
                p2.move(-p2velocity * Math.cos(p2.getAngle() - pi / 2.0), p2velocity * Math.sin(p2.getAngle() - pi / 2.0));
                p2.screenWrap(XOFFSET, XOFFSET + WINWIDTH, YOFFSET, YOFFSET + WINHEIGHT);
            }
        }

        private double velocitystep;
        private double rotatestep;
    }

    private static class FlameMover implements Runnable {
        public FlameMover() {
            gap = 0.0;
        }

        public void run() {
            while (endgame == false) {
                lockrotateObjAroundObjtop(lightP1, p1, gap);
            }
        }

        private double gap;
    }

    private static class FlameMoverP2 implements Runnable {
        public FlameMoverP2() {
            gap = 0.0;
        }

        public void run() {
            while (endgame == false) {
                lockrotateObjAroundObjtop(lightP2, p2, gap);
            }
        }

        private double gap;
    }

    private static class CollisionChecker implements Runnable {
        public void run() {
            while(endgame == false){
                for(int i = 0; i < walls.size(); i++){
                    if(collisionOccurs(p1,walls.elementAt(i))){
                        p1.setBounce(true);
                    }
                }
                if(collisionOccurs(p1,checkPoint)){
                    p1HitCheck = true;
                }
                if (collisionOccurs(p1,finalPoint) && p1HitCheck){
                    p1lapsTaken++;
                    p1HitCheck = false;
                }
            }
        }
    }

    private static class CollisionCheckerP2 implements Runnable {
        public void run() {
            while(endgame == false){
                for(int i = 0; i < walls.size(); i++){
                    if(collisionOccurs(p2,walls.elementAt(i))){
                        p2.setBounce(true);
                    }
                }
                if(collisionOccurs(p2,checkPoint)){
                    p2HitCheck = true;
                }
                if (collisionOccurs(p2,finalPoint) && p2HitCheck){
                    p2lapsTaken++;
                    p2HitCheck = false;
                }
            }
        }
    }

    private static class WinChecker implements Runnable {
        public void run() {
            while(endgame == false) {
                if (p1lapsTaken == 2) {
                    System.out.println("Player One Wins!");
                    timer.equals(0);
                    endgame = true;
                }
                if (p2lapsTaken == 2) {
                    System.out.println("Player Two Wins!");
                    timer.equals(0);
                    endgame = true;
                }
            }
        }
    }


    private static void lockrotateObjAroundObjbottom(ImageObject objOuter, ImageObject objInner, double dist) {
        objOuter.moveto(objInner.getX() + (dist + objInner.getWidth() / 2.00) * Math.cos(-objInner.getAngle() + pi / 2.0) + objOuter.getWidth() / 2.0, objInner.getY() + (dist + objInner.getHeight() / 2.0) * Math.sin(-objInner.getAngle() + pi / 2.0) + objOuter.getHeight() / 2.0);
        objOuter.setAngle(objInner.getAngle());
    }

    private static void lockrotateObjAroundObjtop(ImageObject objOuter, ImageObject objInner, double dist) {
        objOuter.moveto(objInner.getX() + objOuter.getWidth() + (objInner.getWidth() / 2.0) + (dist + objInner.getWidth() / 2.0) * Math.cos((objInner.getAngle() + pi / 2.0)) / 2.0, objInner.getY() - objOuter.getHeight() + (dist + objInner.getHeight() / 2.0) * Math.sin(objInner.getAngle() / 2.0));
        objOuter.setAngle(objInner.getAngle());
    }

    private static AffineTransformOp rotateImageObject(ImageObject obj) {
        AffineTransform at = AffineTransform.getRotateInstance(-obj.getAngle(), obj.getWidth() / 2.0, obj.getHeight() / 2.0);
        AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return atop;
    }

    private static AffineTransformOp spinImageObject(ImageObject obj) {
        AffineTransform at = AffineTransform.getRotateInstance(-obj.getInternalAngle(), obj.getWidth() / 2.0, obj.getHeight() / 2.0);
        AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return atop;
    }

    private static void backgroundDraw() {
        Graphics g = appFrame.getGraphics();
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, null);
    }

    private static void player2Draw() {
        Graphics g = appFrame.getGraphics();
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(rotateImageObject(p2).filter(player2, null), (int) (p2.getX() + 0.5), (int) (p2.getY() + 0.5), null);
    }

    private static void playerDraw() {
        Graphics g = appFrame.getGraphics();
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(rotateImageObject(p1).filter(player, null), (int) (p1.getX() + 0.5), (int) (p1.getY() + 0.5), null);
    }

    private static void flameDraw() {
        if (p1velocity > 0) {
            Graphics g = appFrame.getGraphics();
            Graphics2D g2D = (Graphics2D) g;
            g2D.drawImage(rotateImageObject(lightP1).filter(frontLight, null), (int) (lightP1.getX() + 0.5), (int) (lightP1.getY() + 0.5), null);
        }

    }

    private static void flameDrawP2() {
        if (p2velocity > 0) {
            Graphics g = appFrame.getGraphics();
            Graphics2D g2D = (Graphics2D) g;
            g2D.drawImage(rotateImageObject(lightP2).filter(frontLight, null), (int) (lightP2.getX() + 0.5), (int) (lightP2.getY() + 0.5), null);
        }

    }
    private static void explosionsDraw(Instant t) {
        // tied to line 39 jtext
        // line 4 has a library for time

        Graphics g = appFrame.getGraphics();
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.white);
        g2D.scale(2,2);
        Instant start = Instant.now();
        Duration diff = Duration.between(t, start);
        String time = diff.toString().substring(2, 6);
        g2D.drawString(time, 5, 30);
        // time passes

    }


    private static class KeyPressed extends AbstractAction {
        public KeyPressed() {
            action = "";
        }

        public KeyPressed(String input) {
            action = input;
        }

        public void actionPerformed(ActionEvent e) {
            if (action.equals("UP")) {
                upPressed = true;
            }
            if (action.equals("DOWN")) {
                downPressed = true;
            }
            if (action.equals("LEFT")) {
                leftPressed = true;
            }
            if (action.equals("RIGHT")) {
                rightPressed = true;
            }
            if (action.equals("W")) {
                upPressedP2 = true;
            }
            if (action.equals("S")) {
                downPressedP2 = true;
            }
            if (action.equals("A")) {
                leftPressedP2 = true;
            }
            if (action.equals("D")) {
                rightPressedP2 = true;
            }
        }

        private String action;
    }

    public static class KeyReleased extends AbstractAction {
        public KeyReleased() {
            action = "";
        }

        public KeyReleased(String input) {
            action = input;
        }

        public void actionPerformed(ActionEvent e) {
            if (action.equals("UP")) {
                upPressed = false;
            }
            if (action.equals("DOWN")) {
                downPressed = false;
            }
            if (action.equals("LEFT")) {
                leftPressed = false;
            }
            if (action.equals("RIGHT")) {
                rightPressed = false;
            }
            if (action.equals("W")) {
                upPressedP2 = false;
            }
            if (action.equals("S")) {
                downPressedP2 = false;
            }
            if (action.equals("A")) {
                leftPressedP2 = false;
            }
            if (action.equals("D")) {
                rightPressedP2 = false;
            }
        }

        private String action;
    }

    private static class QuitGame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            endgame = true;
        }
    }

    private static class StartGame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            endgame = true;

            upPressed = false;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;

            upPressedP2 = false;
            downPressedP2 = false;
            leftPressedP2 = false;
            rightPressedP2 = false;

            p1 = new ImageObject(340, 650, p1width, p1height, pi/2);
            p2 = new ImageObject(340, 675, p2width, p2height, pi/2);

            lightP1 = new ImageObject(p1width / 2.0, p1height, flamewidth, flamewidth, 0.0);
            lightP2 = new ImageObject(p2width / 2.0, p2height, flamewidth, flamewidth, 0.0);

            p1velocity = 0.0;
            p2velocity = 0.0;

            p1.setLastposx(p1originalX);
            p1.setLastposy(p1originalY);

            p2.setLastposx(p2originalX);
            p2.setLastposy(p1originalY);

            p1HitCheck = false;
            p2HitCheck = false;

            p1lapsTaken = 0;
            p2lapsTaken = 0;


            checkPoint = new ImageObject(145,145,.5,.5,0);
            finalPoint = new ImageObject(145,145,.5,.5,0);


            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            endgame = false;

            Thread t1 = new Thread(new Animate());
            Thread t2 = new Thread(new PlayerMover());
            Thread t3 = new Thread(new FlameMover());
            Thread t4 = new Thread(new Player2Mover());
            Thread t5 = new Thread(new CollisionChecker());
            Thread t6 = new Thread(new WinChecker());
            Thread t7 = new Thread(new FlameMoverP2());
            Thread t8 = new Thread(new CollisionCheckerP2());

            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t7.start();
            t8.start();
        }
    }

    private static class GameLevel implements ActionListener {
        public int decodeLevel(String input) {
            int ret = 1; // default behavior is to start at lv 3
            if (input.equals("One")) {
                ret = 1;
            } else if (input.equals("Two")) {
                ret = 2;
            } else if (input.equals("Three")) {
                ret = 3;
            } else if (input.equals("Four")) {
                ret = 4;
            } else if (input.equals("Five")) {
                ret = 5;
            } else if (input.equals("Six")) {
                ret = 6;
            } else if (input.equals("Seven")) {
                ret = 7;
            } else if (input.equals("Eight")) {
                ret = 8;
            } else if (input.equals("Nine")) {
                ret = 9;
            } else if (input.equals("Ten")) {
                ret = 10;
            }
            return ret;
        }

        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String textLevel = (String) cb.getSelectedItem();
            level = decodeLevel(textLevel);
        }
    }

    private static Boolean isInside(double p1x, double p1y, double p2x1, double p2y1, double p2x2, double p2y2) {
        Boolean ret = false;
        if (p1x > p2x1 && p1x < p2x2) {
            if (p1y > p2y1 && p1y < p2y2) {
                ret = true;
            }
            if (p1y > p2y2 && p1y < p2y1) {
                ret = true;
            }
        }
        if (p1x > p2x2 && p1x < p2x1) {
            if (p1y > p2y1 && p1y < p2y2) {
                ret = true;
            }
            if (p1y > p2y2 && p1y < p2y1) {
                ret = true;
            }
        }
        return ret;
    }

    private static Boolean collisionOccursCoordinates(double p1x1, double p1y1, double p1x2, double p1y2, double p2x1, double p2y1, double p2x2, double p2y2) {
        Boolean ret = false;
        if (isInside(p1x1, p1y1, p2x1, p2y1, p2x2, p2y2) == true) {
            ret = true;
        }
        if (isInside(p1x1, p1y2, p2x1, p2y1, p2x2, p2y2) == true) {
            ret = true;
        }
        if (isInside(p1x2, p1y1, p2x1, p2y1, p2x2, p2y2) == true) {
            ret = true;
        }
        if (isInside(p1x2, p1y2, p2x1, p2y1, p2x2, p2y2) == true) {
            ret = true;
        }
        if (isInside(p2x1, p2y1, p1x1, p1y1, p1x2, p1y2) == true) {
            ret = true;
        }
        if (isInside(p2x1, p2y2, p1x1, p1y1, p1x2, p1y2) == true) {
            ret = true;
        }
        if (isInside(p2x2, p2y1, p1x1, p1y1, p1x2, p1y2) == true) {
            ret = true;
        }
        if (isInside(p2x2, p2y2, p1x1, p1y1, p1x2, p1y2) == true) {
            ret = true;
        }
        return ret;
    }

    private static Boolean collisionOccurs(ImageObject obj1, ImageObject obj2) {
        Boolean ret = false;
        if (collisionOccursCoordinates(obj1.getX(), obj1.getY(), obj1.getX() + obj1.getWidth(), obj1.getY() + obj1.getHeight(), obj2.getX(), obj2.getY(), obj2.getX() + obj2.getWidth(), obj2.getY() + obj2.getHeight()) == true) {
            ret = true;
        }
        return ret;
    }

    private static class ImageObject {
        public ImageObject() {
            bounce = false;
        }

        public ImageObject(double xinput, double yinput, double xwidthinput, double yheightinput, double angleinput) {
            x = xinput;
            y = yinput;

            xwidth = xwidthinput;
            yheight = yheightinput;
            angle = angleinput;
            internalangle = 0.0;
            coords = new Vector<Double>();
            lastposx = x;
            lastposy = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getWidth() {
            return xwidth;
        }

        public double getHeight() {
            return yheight;
        }

        public double getAngle() {
            return angle;
        }

        public double getInternalAngle() {
            return internalangle;
        }

        public void setAngle(double angleinput) {
            angle = angleinput;
        }

        public void setInternalAngle(double internalangleinput) {
            internalangle = internalangleinput;
        }

        public Vector<Double> getCoords() {
            return coords;
        }

        public void setCoords(Vector<Double> coordsinput) {
            coords = coordsinput;
            generateTriangles();
            //printTriangles();
        }

        public void generateTriangles() {
            triangles = new Vector<Double>();
            // format : (0 , 1) , (2 , 3) , (4 , 5) i s the ( x , y ) coords of a triangle.
            // get center point of all coordinates .
            comX = getComX();
            comY = getComY();
            for (int i = 0; i < coords.size(); i = i + 2) {
                triangles.addElement(coords.elementAt(i));
                triangles.addElement(coords.elementAt(i + 1));
                triangles.addElement(coords.elementAt((i + 2) % coords.size()));
                triangles.addElement(coords.elementAt((i + 3) % coords.size()));
                triangles.addElement(comX);
                triangles.addElement(comY);
            }
        }

        public void printTriangles() {
            for (int i = 0; i < triangles.size(); i = i + 6) {
                System.out.println("p0x: " + triangles.elementAt(i) + ", p0y: " +
                        triangles.elementAt(i + 1));
                System.out.println(" p1x: " + triangles.elementAt(i + 2) + ", p1y: " +
                        triangles.elementAt(i + 3));
                System.out.println(" p2x: " + triangles.elementAt(i + 4) + ", p2y: " +
                        triangles.elementAt(i + 5));
            }
        }

        public double getComX() {
            double ret = 0;
            if (coords.size() > 0) {
                for (int i = 0; i < coords.size(); i = i + 2) {
                    ret = ret + coords.elementAt(i);
                }
                ret = ret / (coords.size() / 2.0);
            }
            return ret;
        }

        public double getComY() {
            double ret = 0;
            if (coords.size() > 0) {
                for (int i = 1; i < coords.size(); i = i + 2) {
                    ret = ret + coords.elementAt(i);
                }
                ret = ret / (coords.size() / 2.0);
            }
            return ret;
        }

        public void setLastposx(double lastposx) {
            this.lastposx = lastposx;
        }

        public void setLastposy(double lastposy) {
            this.lastposy = lastposy;
        }

        public double getLastposx() {
            return lastposx;
        }

        public double getLastposy() {
            return lastposy;
        }

        public boolean getBounce() {
            return bounce;
        }

        public void setBounce(boolean bounce) {
            this.bounce = bounce;
        }

        public void updateBounce() {
            if (getBounce()) {
                moveto(getLastposx(), getLastposy());
            } else {
                setLastposx(getX());
                setLastposy(getY());
            }
            setBounce(false);
        }

        public void move(double xinput, double yinput) {
            x = x + xinput;
            y = y + yinput;
        }

        public void moveto(double xinput, double yinput) {
            x = xinput;
            y = yinput;
        }

        public void screenWrap(double leftEdge, double rightEdge, double topEdge, double bottomEdge) {
            if (x > rightEdge) {
                moveto(leftEdge, getY());
            }
            if (x < leftEdge) {
                moveto(rightEdge, getY());
            }
            if (y > bottomEdge) {
                moveto(getX(), topEdge);
            }
            if (y < topEdge) {
                moveto(getX(), bottomEdge);
            }
        }

        public void rotate(double angleinput) {
            angle = angle + angleinput;
            while (angle > twoPi) {
                angle = angle - twoPi;
            }

            while (angle < 0) {
                angle = angle + twoPi;
            }
        }

        public void spin(double internalangleinput) {
            internalangle = internalangle + internalangleinput;
            while (internalangle > twoPi) {
                internalangle = internalangle - twoPi;
            }

            while (internalangle < 0) {
                internalangle = internalangle + twoPi;
            }
        }


        private double x;
        private double y;
        private double xwidth;
        private double yheight;
        private double angle; // in Radians
        private double internalangle; // in Radians
        private Vector<Double> coords;
        private Vector<Double> triangles;
        private double comX;
        private double comY;
        private double lastposx;
        private double lastposy;
        private boolean bounce;
    }


    public static void bindKey(JPanel myPanel, String input) {
        myPanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed " + input), input + " pressed");
        myPanel.getActionMap().put(input + " pressed", new KeyPressed(input));

        myPanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("released " + input), input + " released");
        myPanel.getActionMap().put(input + " released", new KeyReleased(input));
    }

    public static void main(String[] args) {
        setup();
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setSize(700, 750);

        JPanel myPanel = new JPanel();

        String[] levels = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
        //check parameters for levelMenu method and the inputs
        JComboBox<String> levelMenu = new JComboBox<String>(levels);
        levelMenu.setSelectedIndex(1);
        levelMenu.addActionListener(new GameLevel());
        myPanel.add(levelMenu);

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new StartGame());
        myPanel.add(newGameButton);

        JButton quitButton = new JButton("Quit Game");
        quitButton.addActionListener(new QuitGame());
        myPanel.add(quitButton);

        bindKey(myPanel, "UP");
        bindKey(myPanel, "DOWN");
        bindKey(myPanel, "LEFT");
        bindKey(myPanel, "RIGHT");

        bindKey(myPanel, "W");
        bindKey(myPanel, "S");
        bindKey(myPanel, "A");
        bindKey(myPanel, "D");

        appFrame.getContentPane().add(myPanel, "South");
        appFrame.setVisible(true);
    }

    private static Vector<ImageObject> walls = new Vector<>();

    private static Boolean p1HitCheck;
    private static Boolean p2HitCheck;

    private static int p1lapsTaken;
    private static int p2lapsTaken;

    private static Boolean endgame;
    private static BufferedImage background;
    private static BufferedImage player;
    private static BufferedImage player2;

    private static Boolean upPressed;
    private static Boolean downPressed;
    private static Boolean leftPressed;
    private static Boolean rightPressed;

    private static Boolean upPressedP2;
    private static Boolean downPressedP2;
    private static Boolean leftPressedP2;
    private static Boolean rightPressedP2;


    private static ImageObject p1;
    private static double p1width;
    private static double p1height;
    private static double p1originalX;
    private static double p1originalY;
    private static double p1velocity;

    private static ImageObject p2;
    private static double p2width;
    private static double p2height;
    private static double p2originalX;
    private static double p2originalY;
    private static double p2velocity;

    private static ImageObject checkPoint;
    private static ImageObject finalPoint;
    private static ImageObject lightP1;
    private static ImageObject lightP2;
    private static BufferedImage frontLight;
    private static double flamewidth;
    private static int level;

    private static int XOFFSET;
    private static int YOFFSET;
    private static int WINWIDTH;
    private static int WINHEIGHT;

    private static double pi;
    private static double twoPi;
    private static Instant timer;
    private static JFrame appFrame;

    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    //stay inside this last bracket NOTE TO SELF - zeek
}
