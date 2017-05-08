package paczka.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Player extends GameObject {

	Random r=new Random();
	Handler handler;
	BufferedImage img;
	
	public Player(int x, int y, ID id,Handler handler) {
		super(x, y, id);	
		this.handler=handler;
		try {
			img = ImageIO.read(new File("rec/rasta.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	public void tick() {
		
			x+=velX;
			y+=velY;
			
			x= Game.clamp(x, 0, Game.WIDTH-39);
			y= Game.clamp(y, 0, Game.HEIGHT-60);
			
			handler.addObjcet(new Trial(x, y,ID.Trial, Color.white, 32, 32, 0.13f, handler));
			
			collision();
			
	}
	public void collision(){
		
		for(int i=0; i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.id==ID.BasicEnemy || tempObject.getId()==ID.FastEnemy || tempObject.getId()==ID.SmartEnemy || tempObject.getId()==ID.EnemyBoss){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -=2;
					AudioPlayer.getMusic("collision").play();
				}
			}	
		}
		
	}

	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		//g.setColor(Color.white);
		//g2.draw(getBounds());
		
		//g.setColor(Color.white);	
		//g.setColor(Color.blue);
		//g2.setColor(Color.BLUE);;
		//g.fillRect((int)x, (int)y, 32,32);
		g.drawImage(img, (int)x,(int) y, null);
		
		
	}

	
	
}
