package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Projectile.GreenProjectile;
import me.jack.LD35.Projectile.LaserProjectile;
import me.jack.LD35.Shape.*;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;

/**
 * Created by Jack on 16/04/2016.
 */
public class EntityPlayer extends Entity {

    Image image;
    public int currentShape = 0;
    public Shape currentShapeObject = new SquareShape();
    ArrayList<Image> spriteArray = new ArrayList<Image>();

    private float exp;
    public float level;
    private float nextLevel = 20;

    protected float chargeLevel = 0f;

    int switchCountdown = 0;
    public EntityPlayer(float x, float y) {
        super(x, y, 16, 16);

        for(int i = 0;i!= 4;i++){
            spriteArray.add(sprites.getSprite(i,0));
        }
        health = 100;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(spriteArray.get(currentShape), getX(), getY());
    }

    @Override
    public void update(Level level) {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            if(level.canMove(getX(),getY()-(currentShapeObject.getMoveSpeed()),getW(),getH()))
            addY(-(currentShapeObject.getMoveSpeed()));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            if(level.canMove(getX(),getY()+(currentShapeObject.getMoveSpeed()),getW(),getH()))
            addY((currentShapeObject.getMoveSpeed()));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            if(level.canMove(getX()-(currentShapeObject.getMoveSpeed()),getY(),getW(),getH()))
            addX(-(currentShapeObject.getMoveSpeed()));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            if(level.canMove(getX()+(currentShapeObject.getMoveSpeed()),getY(),getW(),getH()))
            addX((currentShapeObject.getMoveSpeed()));
        }

        if(health <=0){
           // System.out.println("gg");
        }

        switchCountdown--;
        if(switchCountdown <= 0){
            if(currentShape != 0){
                shift(1);
            }
        }

        Rectangle me = new Rectangle(getX(),getY(),getW(),getH());
        for(Entity e : level.entities){
            if(e instanceof EntityProjectile){
                Rectangle rr = new Rectangle(e.getX(),e.getY(),e.getW(),e.getH());
               if(((EntityProjectile)e).projectile instanceof GreenProjectile && me.intersects(rr)){

                   health-=5;
                   level.entities.remove(e);
               }
            }
        }

    }

    public void mouseClick(int x,int y, int button,Level level){
        if(button == 1 && currentShape != 0){
            Circle effectArea = new Circle(getX(),getY(),currentShapeObject.getAoeRadius());
            ArrayList<Entity> hit = new ArrayList<>();
            for(Entity e : level.entities){
                if(e instanceof  EntityRobot){
                    Rectangle r = new Rectangle(e.getX(),e.getY(),e.getW(),e.getH());
                    if(r.intersects(effectArea)){
                        hit.add(e);
                    }
                }
            }
            currentShapeObject.dealAOEDamage(hit,level);
            shift(1);
        }
    }

    public void shift(int i) {
        System.out.println("Shifting: " + i);
        i-=1;
        if(i > level)
            return;
        if(currentShape == i)
            return;


        Shape newShape = null;
        if(i == 0) {
            newShape = new SquareShape();
        }
        else if(i == 1) {
            newShape = new CircleShape();
            if(chargeLevel < newShape.getChargeNeeded())
                return;
            chargeLevel = 0;
            switchCountdown = 500;
        }else if(i == 2){
            newShape = new OctagonShape();
            if(chargeLevel < newShape.getChargeNeeded())
                return;
            chargeLevel = 0;
            switchCountdown = 500;
        }else if(i == 3){
            newShape = new DiamondShape();
            if(chargeLevel < newShape.getChargeNeeded())
                return;
            chargeLevel = 0;
            switchCountdown = 300;
        }
        if(newShape == null)return;
        currentShapeObject = newShape;
        currentShape = i;
    }

    public void addExp(float exp) {
        this.exp+=exp;
        if(this.exp>(level*level) + 20){
            level++;
            this.exp = 0;
            nextLevel = level*level + 20;
        }

    }

    public float getExp() {
        return exp;
    }

    public float getExpToNextLevel() {
        return nextLevel;
    }

    public float getLevel() {
        return level;
    }

    public float getCharge() {
        return chargeLevel;
    }

    public void mouseDown(int x, int y, int button, Level level) {
        currentShapeObject.attack(x,y,level);
    }
}
