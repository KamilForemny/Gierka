package paczka.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object=new LinkedList<GameObject>();
	
	public int spd = 5;	
	
	public void tick(){
		for(int i=0; i<object.size(); i++){
			GameObject tempObject = object.get(i);			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i=0; i<object.size(); i++){
			GameObject tempObject = object.get(i);			
			tempObject.render(g);
		}
	}
	
	public void clearEnemys(){
		object.clear();
		
	}
	
	public void addObjcet(GameObject object){
		this.object.add(object);
	}
	
	public void removeObjcet(GameObject object){
		this.object.remove(object);
		
	}
}
