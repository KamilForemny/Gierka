package paczka.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {
	Handler handler;
	HUD hud;
	
	private int cost1 = 1000; private int cost2 = 1000; private int cost3 = 1000;
	public static boolean avelible=false;
	
	public Shop(Handler handler,HUD hud){
		this.handler=handler;
		this.hud=hud;
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.setFont(new Font("arial", 0, 48));
		g.drawString("SHOP", 240, 100);
		//b1
		g.setFont(new Font("arial", 0, 12));
		g.setColor(Color.white);
		g.drawString("Upgrade Health", 110, 230);
		g.drawString("Cost: "+cost1, 110, 250);
		g.drawRect(100, 200, 100, 80);
		if(hud.getScore()>=cost1){
			avelible=true;
			g.setColor(Color.green);
			g.drawRect(100, 200, 100, 80);
			g.setColor(Color.white);
			g.drawString("Upgrade Health", 110, 230);
			g.drawString("Cost: "+cost1, 110, 250);
		}else avelible=false;
		//b2
		g.setColor(Color.white);
		g.drawString("Upgrade Speed", 250, 230);
		g.drawString("Cost: "+cost2, 250, 250);
		g.drawRect(240, 200, 100, 80);
		if(hud.getScore()>=cost2){
			avelible=true;
			g.setColor(Color.green);
			g.drawRect(240, 200, 100, 80);
			g.setColor(Color.white);
			g.drawString("Upgrade Speed", 250, 230);
			g.drawString("Cost: "+cost2, 250, 250);
		}else avelible=false;
		
		//b3
		g.setColor(Color.white);
		g.drawString("Refil Health", 390, 230);
		g.drawString("Cost: "+cost3, 390, 250);
		g.drawRect(380, 200, 100, 80);
		if(hud.getScore()>=cost3){
			avelible=true;
			g.setColor(Color.green);
			g.drawRect(380, 200, 100, 80);
			g.setColor(Color.white);
			g.drawString("Upgrade Speed", 250, 230);			
		}else avelible=false;
		
		g.setColor(Color.white);
		g.drawString("Score"+hud.getScore(), Game.WIDTH/2-50, 300);
		g.drawString("Space to Back", Game.WIDTH/2-50, 330);
	}
	
	public void mousePressed(MouseEvent e){

		int mx = e.getX();
		int my = e.getY();
		
		//b1
		if(mx >=100 && mx <= 200){
			if(my>=200 && my <=280){				
				if(hud.getScore()>=cost1){
					hud.setScore(hud.getScore()-cost1);				
					cost1+=1000;
					hud.bounds +=20;
					hud.HEALTH= 100+(hud.bounds/2);					
					}
			}
		}

		//b2
		if(mx >=240 && mx <= 340){
			if(my>=200 && my <=280){
				if(hud.getScore()>=cost2){
					
					hud.setScore(hud.getScore()-cost2);
					cost2+=1000;
					handler.spd++;					
				}
			}
		}

		//b3
		if(mx >=380 && mx <= 480){
			if(my>=200 && my <=280){
				if(hud.getScore()>=cost3){
					hud.setScore(hud.getScore()-cost3);
					cost3+=1000;
					hud.HEALTH= 30+(hud.bounds/2);					
				} 
			}
		}
	}
	
	public int getCost1(){
		return cost1;
	}
	
	public int getCost2(){
		return cost2;
	}
	
	public int getCost3(){
		return cost3;
	}
}










