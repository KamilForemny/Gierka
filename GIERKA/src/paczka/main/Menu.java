package paczka.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import paczka.main.Game.STATE;

public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game=game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState==STATE.Menu){
				//play
			if(mouseOver(mx, my, 210, 150, 200, 64)){			
				game.gameState=STATE.Select;
				//game.gameState=STATE.Game;
				//handler.clearEnemys();
				//handler.addObjcet(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));		
				//handler.addObjcet(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
				//AudioPlayer.getSound("back").loop();
				//AudioPlayer.getMusic("music").stop();
				return;
			}				
			//help button
			if(mouseOver(mx, my, 210, 250, 200, 64)){
				game.gameState = STATE.Help;
				//AudioPlayer.getMusic("music").play();
			}
			// exit
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				System.exit(1);
			}
			
			
		
		
	}
		
		if(game.gameState==STATE.End){
			AudioPlayer.getSound("back").stop();
			AudioPlayer.getMusic("music").play();
			//again
			if(mouseOver(mx, my,170, 350, 330, 64)){
				game.gameState=STATE.Menu;
				handler.clearEnemys();
				//handler.addObjcet(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));		
				//handler.addObjcet(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
				hud.setLevel(1);
				hud.setScore(0);
				handler.spd=5;
				hud.HEALTH=100;
				AudioPlayer.getMusic("music").stop();
				AudioPlayer.getMusic("music").loop();
				for(int i=0;i<20;i++){
					handler.addObjcet(new MenuParticle(r.nextInt(game.WIDTH),r.nextInt(game.HEIGHT),ID.MenuParticle,handler));
				}
			}	
		}
	
		if (game.gameState==STATE.Select){
			//play easy
		if(mouseOver(mx, my, 210, 150, 200, 64)){			
			game.gameState=STATE.Game;
			handler.clearEnemys();
			handler.addObjcet(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));		
			handler.addObjcet(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
			game.diff=0;
			AudioPlayer.getSound("back").loop();
			AudioPlayer.getMusic("music").stop();
		}				
		// play hard
		if(mouseOver(mx, my, 210, 250, 200, 64)){
			game.gameState=STATE.Game;
			handler.clearEnemys();
			handler.addObjcet(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));		
			handler.addObjcet(new HardEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
			game.diff=1;
			AudioPlayer.getSound("back").loop();
			AudioPlayer.getMusic("music").stop();
		}
		// back
		if(mouseOver(mx, my, 210, 350, 200, 64)){
			game.gameState= STATE.Menu;
			return;
		}
	}
		
		
		if(game.gameState == STATE.Help){
			//back button help
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState=STATE.Menu;
				//AudioPlayer.getMusic("music").play();
				return;
			}
		}	
		
	}
	
	public void mouseReleased( MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if (my > y && my < y + height){
				return true;
			}else return false;
			
		}else return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		
		if(game.gameState == STATE.Menu){
			
			g.setColor(Color.white);
			Font fff = new Font("Arial",3,65);
			g.setFont(fff);
			g.drawString("Wladekk", 180, 70);
			Font f = new Font("Arial",1,50);
			g.setFont(f);			
			g.setColor(Color.white);
			
			//g.drawString("Wladekk", 210, 70);
			
			g.setColor(new Color(30,30,30));
			g.drawRect(210, 150, 200, 64);
			g.fillRect(210, 150, 200, 64);
			g.setColor(Color.lightGray);
			g.drawString("PLAY", 242, 200);
			
			g.setColor(new Color(30,30,30));
			g.fillRect(210, 250, 200, 64);
			g.drawRect(210, 250, 200, 64);
			g.setColor(Color.lightGray);
			g.drawString("HELP", 241, 300);			
			
			g.setColor(new Color(30,30,30));
			g.drawRect(210, 350, 200, 64);
			g.fillRect(210, 350, 200, 64);
			g.setColor(Color.lightGray);
			g.drawString("EXIT", 253, 400);
		}
		else if(game.gameState == STATE.Help){
			
			Font f = new Font("Arial",1,50);
			Font ff = new Font("Arial",1,30);
			g.setFont(f);
			g.setColor(Color.lightGray);
			g.drawString("HELP", 240, 70);
			g.setColor(new Color(30,30,30));
			g.fillRect(210, 350, 200, 64);
			g.drawRect(210, 350, 200, 64);
			g.setColor(Color.lightGray);
			g.drawString("BACK", 240, 400);
			g.setFont(ff);
			g.drawString("Use WSDA by sterowac", 150, 220);
		}
		else if(game.gameState == STATE.End){
			
			Font f = new Font("Arial",1,50);
			Font ff = new Font("Arial",1,30);
			g.setFont(f);
			g.setColor(Color.red);
			g.drawString("GAME OVER", 180, 100);
			g.setColor(new Color(30,30,30));
			g.fillRect(170, 350, 330, 64);
			g.drawRect(170, 350, 330, 64);
			g.setColor(Color.green);
			g.drawString("Jeszcze Raz?", 175, 400);
			g.setFont(ff);
			g.setColor(Color.lightGray);
			g.drawString("Przegrana \n wynik: "+hud.getScore(), 175, 220);
		}
		else if(game.gameState == STATE.Select){
			
			g.setColor(Color.white);
			Font fff = new Font("Arial",3,65);
			g.setFont(fff);
			g.drawString("Trudnosc", 170, 70);
			Font f = new Font("Arial",1,50);
			g.setFont(f);			
			g.setColor(Color.white);
			
			//g.drawString("Wladekk", 210, 70);
			
			g.setColor(new Color(30,30,30));
			g.drawRect(210, 150, 200, 64);
			g.fillRect(210, 150, 200, 64);
			g.setColor(Color.lightGray);
			g.drawString("EASY", 245, 200);
			
			g.setColor(new Color(30,30,30));
			g.fillRect(210, 250, 200, 64);
			g.drawRect(210, 250, 200, 64);
			g.setColor(Color.lightGray);
			g.drawString("HARD", 245, 300);			
			
			g.setColor(new Color(30,30,30));
			g.drawRect(210, 350, 200, 64);
			g.fillRect(210, 350, 200, 64);
			g.setColor(Color.lightGray);
			g.drawString("Back", 253, 400);
		
		}
	}
	
	
}








