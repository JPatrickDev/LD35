package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Projectile.LaserProjectile;
import me.jack.LD35.Shape.CircleShape;
import me.jack.LD35.Shape.Shape;
import me.jack.LD35.Shape.SquareShape;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

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
    public EntityPlayer(float x, float y) {
        super(x, y, 16, 16);

        for(int i = 0;i!= 4;i++){
            spriteArray.add(sprites.getSprite(i,0));
        }
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
            //gg
        }
    }

    public void mouseClick(int x,int y, int button,Level level){
        currentShapeObject.attack(x,y,level);
    }

    public void shift(int i) {
        i-=1;
        if(i > level)
            return;
        if(currentShape == i)
            return;

        currentShape = i;
        if(i == 0)
            currentShapeObject = new SquareShape();
        else if(i == 1)
            currentShapeObject = new CircleShape();

        this.health = currentShapeObject.getHealth();

    }

    public void addExp(int exp) {
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
}
