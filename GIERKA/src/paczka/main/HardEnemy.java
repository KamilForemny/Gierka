package paczka.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class HardEnemy extends GameObject {

	private Handler handler;
	private Random r = new Random();
	
	
	public HardEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler=handler;
		velX=4;
		velY=4;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
		
		if(y<=0||y>=Game.HEIGHT-32) { if(velY<0) velY=-(r.nextInt(10)+1)*-1; else velY=(r.nextInt(10)+1)*-1;}
		if(x<=0||x>=Game.WIDTH-32) velX*=-1;
		
		handler.addObjcet(new Trial(x, y,ID.Trial, Color.yellow, 16, 16, 0.09f, handler));
	}

	
	public void render(Graphics g) {
		
		
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 16,16);
		
	}
	

}
