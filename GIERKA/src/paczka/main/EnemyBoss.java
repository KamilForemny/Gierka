package paczka.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject {

	private Handler handler;
	private int timer =120;
	private int timer2 = 90;
	private int timer3 = 300;
	Random r = new Random();
	
	
	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler=handler;
		velX=0;
		velY=2;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,96,96);
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
		
		timer--;
		
		if(timer <= 0) velY=0;
		else timer --;
		
		if(timer <=0) timer2 --;
		if(timer2 <= 0){
			timer3 --;
			if(velX==0) velX=2;
			
			if (timer3 <= 150){
				if(velX>=0 )velX+=0.1f;
				if(velX<=0) velX-=0.1f;
				if(timer3==0) {
					timer3=300;
					if(velX>=0 )velX=2;
					if(velX<=0) velX=-2;
					
				}
			}
			if(timer3==0) timer3=30;
			
			int spawn = r.nextInt(8);
			if(spawn ==0) handler.addObjcet(new EnemyBossBullet((int)x+48,(int)y, ID.BasicEnemy, handler)); 
			
			
		}
		//if(y<=0||y>=Game.HEIGHT-32) velY*=-1;
		if(x<=0||x>=Game.WIDTH-96) velX*=-1;
		
		handler.addObjcet(new Trial(x, y,ID.Trial, Color.red, 96, 96, 0.09f, handler));
	}

	
	public void render(Graphics g) {
		
		
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 96,96);
		
	}
	

}
