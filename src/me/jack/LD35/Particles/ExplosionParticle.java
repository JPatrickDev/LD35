package me.jack.LD35.Particles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import uk.co.jdpatrick.JEngine.Particle.Particle;
import uk.co.jdpatrick.JEngine.Particle.ParticleSystem;

import java.util.Random;

/**
 * Created by Jack on 17/04/2016.
 */
public class ExplosionParticle extends Particle{


    Random r = new Random();
    Color color = new Color(0 + r.nextInt(100),0 + r.nextInt(100),r.nextInt(50));
    public ExplosionParticle(int x, int y) {
        super(x, y);
        xx = x;
        yy = y;

        xa = random.nextGaussian() * 5;
        ya = random.nextGaussian() * 5;

        particle = new Rectangle((float) xx, (float) yy, 1, 1);
    }

    @Override
    public void render(Graphics g, int offsetX, int offsetY) {
        g.setColor(color);
        g.fillRect(x,y,1,1);
        g.setColor(Color.white);
    }

    @Override
    public void update(ParticleSystem system) {
        time++;
        if (time > 5 || remove) {
            system.removeParticle(this);
            xa /= 20;
            ya/=20;
        }
        xx += xa;
        yy += ya;

        x = (int) xx;
        y = (int) yy;

        if(xa > 0){
            xa-=0.5;
        }else{
            xa+=0.5;
        }

        if(ya > 0){
            ya-=0.5;
        }else{
            ya+=0.5;
        }

        particle.setX(x);
        particle.setY(y);
    }
}
