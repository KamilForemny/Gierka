
package paczka.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {

	private Handler handler;
	private Random r = new Random();	
	private Color col;
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler=handler;
		velX=3;
		velY=8;
		
		col = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
		
		if(y<=0||y>=Game.HEIGHT-32) velY*=-1;
		if(x<=0||x>=Game.WIDTH-32) velX*=-1;
		
		handler.addObjcet(new Trial(x, y,ID.Trial, col, 16, 16, 0.09f, handler));
	}

	
	public void render(Graphics g) {
		
		
		g.setColor(col);
		g.fillRect((int)x, (int)y, 16,16);
		
	}
	

}
