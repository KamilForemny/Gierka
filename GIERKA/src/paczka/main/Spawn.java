package paczka.main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Game game;
	private Random r=new Random();
	
	
	private int scoreKeep=0;
	
	public Spawn(Handler handler,HUD hud, Game game){
		this.handler=handler;
		this.hud=hud;
		this.game=game;
	}
	
	public void tick(){
		scoreKeep  ++;
		
		if(scoreKeep>=250){
			scoreKeep=0;
			hud.setLevel(hud.getLevel()+1);
			
			if(game.diff==0){
				if(hud.getLevel()==2){
				handler.addObjcet(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel()==3){
					handler.addObjcet(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.BasicEnemy, handler));		
				}
				else if(hud.getLevel()==4){
					handler.addObjcet(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.FastEnemy, handler));				
				}
				else if(hud.getLevel()==5){
					handler.addObjcet(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.SmartEnemy, handler));							
				}
				else if(hud.getLevel()==6){
					handler.addObjcet(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.FastEnemy, handler));				
				}
				else if(hud.getLevel()==7){
					handler.addObjcet(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.FastEnemy, handler));				
				}
				else if(hud.getLevel()==8){
					handler.addObjcet(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.SmartEnemy, handler));				
				}
				else if(hud.getLevel()==10){
					handler.clearEnemys();
					handler.addObjcet(new Player(Game.WIDTH/2-32,Game.HEIGHT/2,ID.Player,handler));
					handler.addObjcet(new EnemyBoss(Game.WIDTH/2-48, -120, ID.EnemyBoss, handler));
				}									
			}
			
			if(game.diff==1){
				if(hud.getLevel()==2){
				handler.addObjcet(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel()==3){
					handler.addObjcet(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.BasicEnemy, handler));		
				}
				else if(hud.getLevel()==4){
					handler.addObjcet(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.FastEnemy, handler));				
				}
				else if(hud.getLevel()==5){
					handler.addObjcet(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.SmartEnemy, handler));							
				}
				else if(hud.getLevel()==6){
					handler.addObjcet(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.FastEnemy, handler));				
				}
				else if(hud.getLevel()==7){
					handler.addObjcet(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.FastEnemy, handler));				
				}
				else if(hud.getLevel()==8){
					handler.addObjcet(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.SmartEnemy, handler));				
				}
				else if(hud.getLevel()==10){
					handler.clearEnemys();
					handler.addObjcet(new Player(Game.WIDTH/2-32,Game.HEIGHT/2,ID.Player,handler));
					handler.addObjcet(new EnemyBoss(Game.WIDTH/2-48, -120, ID.EnemyBoss, handler));
				}									
			}
		}
	}
}














