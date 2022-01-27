import java.util.Vector;
import java.util.Random;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;

import javax.imageio.ImageIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;


//
public class Asteroids {
    public Asteroids() {
        setup();
    }

    public static void setup() {
        appFrame = new JFrame("Asteroids");
        XOFFSET = 0;
        YOFFSET = 40;
        WINWIDTH = 500;
        WINHEIGHT = 500;
        pi = 3.14159265358979;
        twoPi = 2.0 * 3.14159265358979;
        endgame = false;
        p1width = 18.5;
        p1height = 25.0;
        p1originalX = (double) XOFFSET + ((double) WINWIDTH / 2.0) - (p1width / 2.0);
        p1originalY = (double) YOFFSET + ((double) WINHEIGHT / 2.0) - (p1height / 2.0);
        playerBullets = new Vector<ImageObject>();
        playerBulletsTimes = new Vector<Long>();
        bulletWidth = 5;
        playerbulletlifetime = new Long(1600);
        enemybulletlifetime = new Long(1600);
        explosionlifetime = new Long(800);
        playerbulletgap = 1;
        flamecount = 1;
        flamewidth = 12.0;
        expcount = 1;
        level = 3;
        asteroids = new Vector<ImageObject>();
        asteroidsTypes = new Vector<Integer>();
        ast1width = 32;
        ast2width = 21;
        ast3width = 26;

        try {
            background = ImageIO.read(new File(""));
            player = ImageIO.read(new File(""));
            flame1 = ImageIO.read(new File(""));
            flame2 = ImageIO.read(new File(""));
            flame3 = ImageIO.read(new File(""));
            flame4 = ImageIO.read(new File(""));
            flame5 = ImageIO.read(new File(""));
            flame6 = ImageIO.read(new File(""));
            ast1 = ImageIO.read(new File(""));
            ast2 = ImageIO.read(new File(""));
            ast3 = ImageIO.read(new File(""));
            playerBullet = ImageIO.read(new File(""));
            enemyShip = ImageIO.read(new File(""));
            enemyBullet = ImageIO.read(new File(""));
            exp1 = ImageIO.read(new File(""));
            exp2 = ImageIO.read(new File(""));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static class Animate implements Runnable {
        public void run() {
            while (endgame == false) {

            }
        }
    }

    private static void insertPlayerBullet() {

    }

    private static void insertEnemyBullet() {

    }

    private static class PlayerMover implements Runnable {

    }

    private static class FlameMover implements Runnable {

    }

    private static class AsteroidsMover implements Runnable {

    }

    private static class PlayerBulletsMover implements Runnable {

    }

    private static class EnemyShipMover implements Runnable {

    }

    private static class EnemyBulletsMover implements Runnable {

    }

    private static class CollisionChecker implements Runnable {

    }

    private static class WinChecker implements Runnable {

    }

    private static void generateAsteroids() {

    }

    private static void generateEnemy() {

    }

    private static void lockrotateObjAroundObjbottom(ImageObject objOuter, ImageObject objInner, double dist) {

    }

    private static void lockrotateObjAroundObjtop(ImageObject objOuter, ImageObjct objInner, double dist) {

    }

    private static AffineTransformOp rotateImageObject(ImageObject obj) {

    }

    private static AffineTransformOp spinImageObject(ImageObject obj) {

    }


    /**
     * zeek stopped here, I am working from back to front. page 101
     **/
//    public double getWidth() {
//        re
//    }
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

    }

    public setInternalAngle(double internalangleinput) {

    }

    public Vector<Double> getCoords() {
        return coords;
    }

    public void setCoords(Vector<Double> coordsiput) {

    }

    public void generateTriangles() {

    }

    public void printTriangles() {

    }

    public double getComX() {
        return 0;
    }

    public double getComY() {
        return 0;
    }

    public void move(double xinput, double yinput) {

    }

    public void moveto(double xinput, double yinput) {

    }

    public void screenWrap(double leftEdge, double rightEdge, double topEdge, double bottomEdge) {

    }

    public void rotate(double angleinput) {

    }

    public void spin(double internalangleinput) {

    }

    public static void bindKey(JPanel myPanel, String input) {

    }

    public static void main(String[] args) {
        setup();
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setSize(501, 585);

        JPanel myPanel = new JPanel();

        String[] levels = {"One" + "Two" + "Three" +
                "Four" + "Five" + "Six" + "Seven" + "Eight" + "Nine" + "Ten"};
        JComboBox<String> levelMenu = new JComboBox<String>(levels);
        levelMenu.setSelectedIndex(2);
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
        bindKey(myPanel, "F");

        appFrame.getContentPane().add(myPanel, "South");
        appFrame.setVisible(true);
    }

    private static Boolean endgame;
    private static Boolean enemyAlive;
    private static BufferedImage background;
    private static BufferedImage player;

    private static Boolean upPressed;
    private static Boolean downPressed;
    private static Boolean leftPressed;
    private static Boolean rightPressed;
    private static Boolean firePressed;

    private static ImageObject p1;
    private static double p1width;
    private static double p1height;
    private static double p1originalX;
    private static double p1originalY;
    private static double p1velocity;

    private static ImageObject enemy;
    private static BufferedImage enemyShip;
    private static BufferedImage enemyBullet;
    private static Vector<ImageObject> enemyBullets;
    private static Vector<Long> enemyBulletsTimes;
    private static Long enemybulletlifetime;

    private static Vector<ImageObject> playerBullets;
    private static Vector<Long> playerBulletsTimes;
    private static double bulletWidth;
    private static BufferedImage playerBullet;
    private static Long playerbulletlifetime;
    private static double playerbulletgap;

    private static ImageObject flames;
    private static BufferedImage flame1;
    private static BufferedImage flame2;
    private static BufferedImage flame3;
    private static BufferedImage flame4;
    private static BufferedImage flame5;
    private static BufferedImage flame6;
    private static int flamecount;
    private static double flamewidth;
    private static int level;

    private static Vector<ImageObject> asteroids;
    private static Vector<Integer> asteroidsTypes;
    private static BufferedImage ast1;
    private static BufferedImage ast2;
    private static BufferedImage ast3;
    private static double ast1width;
    private static double ast2width;
    private static double ast3width;

    private static Vector<ImageObject> explosions;
    private static Vector<Long> explosionsTimes;
    private static Long explosionlifetime;
    private static BufferedImage exp1;
    private static BufferedImage exp2;
    private static int expcount;

    private static int XOFFSET;
    private static int YOFFSET;
    private static int WINWIDTH;
    private static int WINHEIGHT;

    private static double pi;
    private static double twoPi;

    private static JFrame appFrame;

    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    //stay inside this last bracket NOTE TO SELF - zeek
}

