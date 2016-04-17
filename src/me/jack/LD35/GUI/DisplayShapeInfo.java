package me.jack.LD35.GUI;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Projectile.FireballProjectile;
import me.jack.LD35.Projectile.LaserProjectile;
import me.jack.LD35.Projectile.Projectile;
import me.jack.LD35.Shape.CircleShape;
import me.jack.LD35.Shape.OctagonShape;
import me.jack.LD35.Shape.Shape;
import me.jack.LD35.Shape.SquareShape;
import me.jack.LD35.States.InGameState;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

/**
 * Created by Jack on 17/04/2016.
 */
public class DisplayShapeInfo {

    public static boolean displaying = false;
    public static Image background = null;

    static ArrayList<Shape> shapes = new ArrayList<Shape>();
    static ArrayList<Projectile> projectiles = new ArrayList<>();

    static {
        shapes.add(new SquareShape());
        shapes.add(new CircleShape());
        shapes.add(new OctagonShape());

        projectiles.add(new LaserProjectile(100,0));
        projectiles.add(new FireballProjectile());
        projectiles.add(new FireballProjectile());
    }
    public static void displayInfo(int shape, Graphics g){
        if(!displaying){
            displaying = true;
        }
        if(background == null){
            try {
                background = new Image("res/shapeInfoTemplate.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        g.drawImage(background,300,90);

        Shape s = shapes.get(shape);
        String name = s.getClass().getSimpleName();
        g.drawString(name,320,105);

        Projectile t = projectiles.get(shape);
        g.drawImage(t.getIcon(),158+300,51+90);
        g.drawString(t.getName(),300+10,51+90);
        g.drawString("Damage:" + t.getDamage(),310,51+90+15);

        g.drawString("Speed:" + s.getMoveSpeed(),310,182);
        g.drawString("Level:" + (shape+1),310,197);
        g.drawString("Charge needed:" + s.getChargeNeeded(),310,212);

        g.drawString(s.getDescription(),310,240);

    }

    public static void mouseClick(int x,int y){
        if(!displaying)return;
        Rectangle closebutton = new Rectangle(452,100,38,31);
        if(closebutton.contains(x,y)){
            InGameState.showing = -1;
            displaying = false;
        }

    }

}
