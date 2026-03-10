package Game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Personnage.Ennemy;

public class GameLoop implements Runnable{
	private Boolean running = false;
	public GameComponent gameComponent;
	public GameComponent[] Niveaux;
	public Niveau1 lvl1 = new Niveau1();
	
	public GameLoop(GameComponent gameComponent) {
        this.gameComponent = gameComponent;
        this.running = true;
    }
	public void start() {
		running = true;
		new Thread(this).start();
	}
	public void stop() {
		running = false;
	}
	@Override
    public void run() {
                   while (running) {
            try {
            	update();
            	gameComponent.repaint();
            	//SwingUtilities.invokeLater(() -> gameComponent.repaint());
                //gameComponent.mario.marcher("mario", 3);
                Thread.sleep(16);     // ~60 FPS.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	public static void main(String[] args) {
		//SwingUtilities.invokeLater(() -> new GameLoop());
		JFrame frame = new JFrame("Mon Jeu");
        GameComponent canvas = new GameComponent();
        frame.add(canvas);
        frame.setSize(1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Lancer le GameLoop.
        GameLoop loop = new GameLoop(canvas);
        Thread gameThread = new Thread(loop);
        gameThread.start();
        
        frame.setVisible(true);

	}
	public void update() {
		gameComponent.checkCollisions(gameComponent.mario);
		//gameComponent.bougerMonde();
	}
	

}
