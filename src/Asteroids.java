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

public class Asteroids {
    public Asteroids(){
        setup();
    }
    public static  void setup(){
        JFrame appFrame = new JFrame ("Asteroids");
        final int XOFFSET = 0;
        final int YOFFSET = 40;
        final int WINWIDTH = 500;
        final int WINHEIGHT = 500;
        final double pi = 3.14159265358979;
        final double twoPi = 2.0 * 3.14159265358979;
        endgame = false;
        double p1width = 18.5;
        double p1height = 25.0;
        double p1originalX = (double)XOFFSET + ((double)WINWIDTH / 2.0) - (p1width / 2.0);
        double p1originalY = (double)XOFFSET + ((double)WINHEIGHT / 2.0) - (p1height / 2.0);
        Vector <ImageObject> playerBullets = new Vector<ImageObject>();
        Vector <Long> playerBulletsTimes = new Vector<Long>();
        int bulletWidth = 5;
        long playerbulletlifetime = new Long(1600);
        long enemybulletlifetime = new Long(1600);
        long explosionlifetime = new Long(800);
        int playerbulletgap = 1;
        int flamecount = 1;
        double flamewidth = 12.0;
        int expcount = 1;
        int level = 3;
        Vector <ImageObject> asteroids = new Vector<ImageObject>();
        Vector <Integer> asteroidsTypes = new Vector<Integer>();
        int ast1width = 32;
        int ast2width = 21;
        int ast3width = 26;

        try{
            BufferedImage background = ImageIO.read(new File(""));
            BufferedImage player = ImageIO.read(new File(""));
            BufferedImage flame1 = ImageIO.read(new File(""));
            BufferedImage flame2 = ImageIO.read(new File(""));
            BufferedImage flame3 = ImageIO.read(new File(""));
            BufferedImage flame4 = ImageIO.read(new File(""));
            BufferedImage flame5 = ImageIO.read(new File(""));
            BufferedImage flame6 = ImageIO.read(new File(""));
            BufferedImage ast1 = ImageIO.read(new File(""));
            BufferedImage ast2 = ImageIO.read(new File(""));
            BufferedImage ast3 = ImageIO.read(new File(""));
            BufferedImage playerBullet = ImageIO.read(new File(""));
            BufferedImage enemyShip = ImageIO.read(new File(""));
            BufferedImage enemyBullet = ImageIO.read(new File(""));
            BufferedImage exp1 = ImageIO.read(new File(""));
            BufferedImage exp2 = ImageIO.read(new File(""));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
