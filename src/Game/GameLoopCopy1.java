package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameLoopCopy1 extends JFrame implements Runnable{
	private Boolean running = false;
	public GameComponent gameComponent;
	
	public GameLoopCopy1(GameComponent gameComponent) {
		//   this.frameInit();
		this.gameComponent = gameComponent;
		add(gameComponent);
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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
		
		final double TARGET_FPS = 60.0; // Frame Per Second ~ Images par secondes
        final double OPTIMAL_TIME = 1_000_000_000/TARGET_FPS; // Temps optimal par frame en nanosecondes
            
        long lastTime = System.nanoTime();
        double delta = 0;
            
        while(running) {
        	long now = System.nanoTime();
        	long elapsedTime = now - lastTime;
        	lastTime = now;
        	
        	delta += elapsedTime/OPTIMAL_TIME;
        	while(delta>=1) {
        		update();
        		delta --;
        	}
        	render();
        	try {
        		Thread.sleep(16);
        	}catch(InterruptedException e) {}
        }
    }
	public static void main(String[] args) {
        // Lancer le GameLoop.
        GameLoopCopy1 loop = new GameLoopCopy1(new Niveau2());
        Thread gameThread = new Thread(loop);
        gameThread.start();
	 }
	public void update() {
		gameComponent.checkCollisions(gameComponent.mario);
		MovementManagement();
		ItemManagement();
		if(gameComponent.mario.life <= 0) {
			gameOver();
		}
		if(gameComponent.mario.collisionADroite(gameComponent.flag) || gameComponent.mario.collisionAGauche(gameComponent.flag) || gameComponent.mario.collisionEnHaut(gameComponent.flag)) {
			//Win(gameComponent.mario.getY());
		}
	}
	public void render() {
		gameComponent.repaint();
	}
	public void MovementManagement() {
		if(gameComponent.mario.staticBlocCollision[3] || gameComponent.mario.yellowBoxCollision[3] || 
		   gameComponent.mario.tuyauCollision[3] || gameComponent.mario.castleCollision[3]){
			if(!gameComponent.mario.isDroite && gameComponent.mario.isMarche) {
				gameComponent.mario.setMarcheAutorisation(false);
			}
		}
		if(gameComponent.mario.staticBlocCollision[1] || gameComponent.mario.yellowBoxCollision[1] ||
		   gameComponent.mario.tuyauCollision[1] || gameComponent.mario.castleCollision[1]) {
					
			if(gameComponent.mario.isDroite && gameComponent.mario.isMarche) {
				gameComponent.mario.setMarcheAutorisation(false);
			}
		}
		if(gameComponent.keyTab[0] || gameComponent.keyTab[1]) {
			gameComponent.mario.marcher("mario", 8);
		}
	}
	public void ItemManagement() {
		if(gameComponent.usingItem) {
			gameComponent.usingItem = false;
			if(gameComponent.mario.itemStock.size() == 0) {Audio.playSound("/Sound/hitbox1.wav");}
			
			else {
				if(gameComponent.mario.itemStock.get(0).getNom().equals("champignon")) {
					Audio.playSound("/Sound/mushroom.wav");
					
					gameComponent.mario.mushroomTimer.start();
					
					if(gameComponent.mario.life < 3) {
						gameComponent.mario.life +=1;
				    }
				 }
				if(gameComponent.mario.itemStock.get(0).getNom().equals("etoile")) {
					gameComponent.mainTheme.stop();
					Audio starAudio = new Audio("/Sound/staritem.wav");
					starAudio.setLoop(true);
					gameComponent.mario.isInvincible = true;
					gameComponent.mario.canLooseLife = false;
					Timer timer = new Timer(10000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							gameComponent.mainTheme.setLoop(true);
							gameComponent.mario.isInvincible = false;
							gameComponent.mario.canLooseLife = true;
							starAudio.stop();
						}
					});timer.start();
				}
			}
			gameComponent.mario.updateVies();
			gameComponent.mario.itemStock.remove(gameComponent.mario.itemStock.get(0));
		}
	}
	public void gameOver() {
		JLabel gameOver = new JLabel("Game Over") {
			@Override 
			public void paintComponent(Graphics g) {
				String texte = this.getText();
				Graphics2D g2d = (Graphics2D)g;
				for(int i = 0; i<texte.length(); i++) {
					g2d.drawImage(stringToImage(texte.charAt(i)), i*50, 0, 50, 50, this);
				}
			}
		};
		gameOver.setBounds(400, 200, 500, 50);
		gameOver.setPreferredSize(new Dimension(400, 50));
		gameOver.setBackground(Color.red);
		gameComponent.add(gameOver);
		
		JButton restart = new JButton("restart") {
			@Override 
			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				Rectangle2D.Double rect = new Rectangle2D.Double(0,  0,  getWidth(),  getHeight());
				g2d.setColor(new Color(51, 139, 27));
				g2d.fill(rect);
				g2d.clip(rect);
				String texte = this.getText();
				for(int i = 0; i<texte.length(); i++) {
					int y = (int)(getHeight()/2 - 15);
					g2d.drawImage(stringToImage(texte.charAt(i)),i*30 + 20, y, 30, 30, this);
				}
			}
		};
		restart.setBounds(525, 300, 250, 70);
		restart.setPreferredSize(new Dimension(250, 70));
		restart.setBorder(BorderFactory.createLineBorder(new Color(255, 200, 80), 10, true));
		restart.setBorderPainted(false);
		restart.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gameComponent = null;
				running = true;
				GameLoopCopy1 loop = new GameLoopCopy1(new GameComponent());
				Thread gameThread = new Thread(loop);
		        gameThread.start();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				restart.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				restart.setBorderPainted(false);
			}
		});
		gameComponent.add(restart);
		running = false;
	
	}
	public Image stringToImage(char lettre) {
		if(lettre == ' ') {return (new ImageIcon(getClass().getResource("/image/vide.png"))).getImage();}
		return (new ImageIcon(getClass().getResource("/image/"+lettre+".png"))).getImage();
	}
	public void Win(int limit) {
		final int MAX_FLAG = limit;
		Timer drapeauAnimationTimer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(gameComponent.flag.getTriangleY()[0]<MAX_FLAG) {
					gameComponent.flag.setTriangleY(gameComponent.flag.getTriangleY()[0] - 2, gameComponent.flag.getTriangleY()[1] - 2, gameComponent.flag.getTriangleY()[0] - 2);
					//gameComponent.flag.repaint();
				}
			}
		});drapeauAnimationTimer.start();
	}
}