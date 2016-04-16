package me.jack.LD35.Level;

import me.jack.LD35.Entity.EnemyCircle;
import me.jack.LD35.Entity.Mob;
import me.jack.LD35.Entity.Player;
import me.jack.LD35.Entity.TestMob;
import me.jack.LD35.Level.Tile.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Jack on 16/04/2016.
 */
public class Level{

    private int[] tiles;
    private int width,height;

    Player mob = new Player(50,15*Tile.TILE_SIZE);
    ArrayList<Rectangle> hitboxes = new ArrayList<Rectangle>();
    public CopyOnWriteArrayList<Mob> mobs = new CopyOnWriteArrayList<Mob>();

    public Level(int width,int height){
        this.width = width;
        this.height = height;
        tiles = new int[width*height];

        for(int x = 0;x!= width;x++){
            setTileAt(x,0,1);
            setTileAt(x,height-1,1);
        }

        for(int y = 0;y!= height;y++){
            setTileAt(0,y,1);
            setTileAt(width-1,y,1);
        }


        mobs.add(new EnemyCircle(50,50));
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getTileAt(int x,int y){
        if(x < 0 || x > this.width)return -1;
        if(y< 0 || y > this.height) return -1;
        return tiles[x+y*this.width];
    }

    public void setTileAt(int x,int y, int i){
        System.out.println("Setting tile " + x + ":" + y + ":" + width + ":" + height);
        if(x < 0 || x > width)return;
        if(y< 0 || y > height) return;
        hitboxes.add(new Rectangle(x * Tile.TILE_SIZE,y*Tile.TILE_SIZE,Tile.TILE_SIZE,Tile.TILE_SIZE));
        tiles[x+y*width] = i;
    }

    public void render(Graphics g){
        for(int x = 0;x!= width;x++){
            for(int y = 0;y!= height;y++){
                int i = getTileAt(x,y);
                if(i != -1){
                    Tile.render(g,x,y,i);
                }
            }
        }
        for(Mob m : mobs)
            m.render(g);
        mob.render(g);

        g.setColor(Color.orange);
        for(Rectangle r : hitboxes){
            g.fill(r);
        }
        g.setColor(Color.white);
    }

    public void update(){
        for(Mob m :mobs)
        m.update(this);
        mob.update(this);
    }

    public Rectangle canMove(float x, float y, int width,int height) {
        Rectangle r = new Rectangle(x,y,width,height);
        for(Rectangle hit : hitboxes){
            if(r.intersects(hit))return hit;
        }
        return null;
    }

    public void keyPressed(int key, char c) {

    }

    public void mousePressed(int button, int x, int y) {
        mob.attack(this);
    }

    public Player getPlayer() {
        return mob;
    }
}
