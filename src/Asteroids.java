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
        appFrame = new JFrame ("Asteroids");
        XOFFSET = 0;
        YOFFSET = 40;
        WINWIDTH = 500;
        WINHEIGHT = 500;
        pi = 3.14159265358979;
        twoPi = 2.0 * 3.14159265358979;
        endgame = false;
        p1width = 18.5;
        p1height = 25.0;
        p1originalX = (double)XOFFSET + ((double)WINWIDTH / 2.0) - (p1width / 2.0);
        p1originalY = (double)YOFFSET + ((double)WINHEIGHT / 2.0) - (p1height / 2.0);
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

        try{
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
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static class Animate implements Runnable {
        public void run(){
            while(endgame == false){

            }
        }
    }

}

