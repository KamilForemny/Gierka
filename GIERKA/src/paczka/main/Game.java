package paczka.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 3827465590709072482L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	private Thread thread;
	private boolean running = false;

	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;

	public int diff = 0; // 0-easy 1-hard
	public static boolean paused = false;

	public enum STATE {
		Menu, Help, End, Shop, Select, Game
	};

	public STATE gameState = STATE.Menu;

	public Game() {
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);

		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();

		new Window(WIDTH, HEIGHT, "Lets start", this);

		ImageLoader loader = new ImageLoader();
		loader.loadImage("/rasta.png");

		spawner = new Spawn(handler, hud, this);
		r = new Random();

		if (gameState == STATE.Game) {
			handler.addObjcet(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
			handler.addObjcet(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
		} else {
			for (int i = 0; i < 20; i++) {
				handler.addObjcet(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTricks = 60.0;
		double ns = 1000000000 / amountOfTricks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
				if (running)
					render();
				frames++;
				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					// System.out.println("FPS: "+frames);
					frames = 0;
				}
			}

		}
		stop();
	}

	private void tick() {

		if (gameState == STATE.Game) {
			if (!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();

				if (hud.HEALTH <= 0) {
					hud.HEALTH = 100;
					handler.clearEnemys();
					gameState = STATE.End;
				}
			}

		} else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select
				|| gameState == STATE.Help) {
			menu.tick();
			handler.tick();
		}

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// handler.render(g);

		if (paused) {
			g.drawString("PAUZA", 150, 210);
		}

		if (gameState == STATE.Game) {
			handler.render(g);
			hud.render(g);
		} else if (gameState == STATE.Shop) {
			shop.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End
				|| gameState == STATE.Select) {
			handler.render(g);
			menu.render(g);
		}

		g.dispose();
		bs.show();
	}

	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public static void main(String agrg[]) {
		new Game();
	}
}
