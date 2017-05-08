package paczka.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	Shop shop;
	
	public static float HEALTH=100;
	public int bounds =0;	
	private float greenValue=255;
	private int score = 1;
	private int level = 1;
	
	public void tick(){
		
		HEALTH=Game.clamp(HEALTH, 0, 100+bounds/2);
		greenValue =HEALTH*2;
		greenValue=Game.clamp(greenValue, 0, 255);
		
		
		score++;
		
	}
	
	public void render (Graphics g){
		g.setColor(Color.black);
		g.fillRect(15, 15, 200+bounds, 32);		
		g.setColor(new Color(75,(int)greenValue,0));
		g.fillRect((int)15, (int)15, (int)(HEALTH*2), 32);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200+bounds, 32);
		
		
		g.drawString("SCORE: "+score, 15, 11);
		g.drawString("LEVEL: "+level, 560, 11);
		g.drawString("Space to Shop", 300, 11);
		if(score>=1000){	
			if(shop.avelible){
				g.setColor(Color.green);
				g.drawString("Space to Shop", 300, 11);
			}
		}
	}
	public void setScore (int score){
		this.score=score;
	}
	public int getScore(){
		return score;
	}
	public int getLevel(){
		return level;
	}
	public void setLevel(int level){
		this.level=level;
	}
}
