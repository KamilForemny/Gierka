package paczka.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BasicEnemy extends GameObject {

	private Handler handler;
	public BufferedImage img;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		try {
			img = ImageIO.read(new File("rec/police.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		if(y<=0||y>=Game.HEIGHT-32) velY*=-1;
		if(x<=0||x>=Game.WIDTH-32) velX*=-1;
		
		handler.addObjcet(new Trial(x, y,ID.Trial, Color.red, 16, 16, 0.09f, handler));
	}

	
	public void render(Graphics g) {
		
		//g.drawImage(img, (int)x, (int)y, null);
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 16,16);
		
	}
	

}
