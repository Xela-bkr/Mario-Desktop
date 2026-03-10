package Game;
import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Objet.Brick;
import Objet.Castle;
import Objet.Drapeau;
import Objet.Item;
import Objet.Objet;
import Objet.Piece;
import Objet.Platform;
import Objet.StaticBloc;
import Objet.Tuyau;
import Objet.YellowBox;
import Personnage.Ennemy;
import Personnage.Mario;
import Personnage.Personnage;

@SuppressWarnings("serial")
public class GameComponent extends JPanel implements KeyListener{
	
	//****VARIABLES DE CLASSE****//
	public Castle startCastle, endCastle;
	public Drapeau flag;
	public ArrayList<StaticBloc> bricks = new ArrayList<StaticBloc>();
	public ArrayList<StaticBloc> blocs = new ArrayList<StaticBloc>();
	public ArrayList<Castle> castles = new ArrayList<Castle>();
	public ArrayList<YellowBox> yellowBoxes = new ArrayList<YellowBox>();
	public ArrayList<Tuyau> tuyaux = new ArrayList<Tuyau>();
	public ArrayList<Piece> pieces = new ArrayList<Piece>();
	public ArrayList<Personnage> personnages = new ArrayList<Personnage>();
	public ArrayList<Ennemy> ennemies = new ArrayList<Ennemy>();
	public ArrayList<Objet> objets = new ArrayList<Objet>();
	public ArrayList<Item> items = new ArrayList<Item>();
	public ArrayList<Platform> platforms = new ArrayList<Platform>();
	public ArrayList<Audio> sounds;
	public ArrayList<Timer> gameTimers = new ArrayList<Timer>();
	public StaticBloc[] nuages;
	public Timer gravityTimer,jumpTimer, deadTimer;
	public Boolean[] keyTab, collisionsTab ;
	public StaticBloc background, background1;
	public Mario mario;
	public int dx, itemCompteur;
	public int compteur = 0, soundCompteur;
	public int[] jumpCompteur = {0, 0};
	public Boolean usingItem;
	public Image pcImage = (new ImageIcon(getClass().getResource("/image/piece.png"))).getImage();
	public Audio mainTheme;

	////****CONSTRUCTEUR****////
	
	public GameComponent() {
		setLayout(null); addKeyListener(this); setFocusable(true); requestFocusInWindow(); setDoubleBuffered(true);
		collisionsTab = new Boolean[4];
		setDefaultCollisionsTab(); setBackground(); setDecoration();setTuyau(); setSol(); setDx(2); setBlocs(); 
		setCastles(300, 314); genererMario(); setEnnemies(); setDx(2); setFlag(); setPlatform(); setPiece();setGravity();
		setJumpTimer();
		itemCompteur = 0; usingItem = false;
		soundCompteur = 82000; 
		setKeyTab();// 0 : VK_LEFT -- 1 : VK_RIGHT -- 2 : VK_SPACE
		setAudio(); setArrayTimer();
	}
	
	////****GETTERS****////
	
	////****SETTERS****////
	public void setBlocs() {

		int[][] brownBlocSetter = {{400, 335, 55, 55}, {510, 335, 55, 55}, {700, 335, 55, 55}, {755, 335, 55, 55}, {810, 335, 55, 55}, 
								  {980, 550, 55, 55}, {1035, 550, 55, 55}, {1090, 550, 55, 55}, {1700, 550, 55, 55}, 
								  {1755, 550, 55, 55}, {1810, 550, 55, 55}, {1865, 550, 55, 55}, {1920, 550, 55, 55}, {1755, 495, 55, 55},
								  {1810, 495, 55, 55}, {1865, 495, 55, 55}, {1920, 495, 55, 55},{1810, 440, 55, 55}, {1865, 440, 55, 55}, 
								  {1920, 440, 55, 55},  {1865, 385, 55, 55}, {1920, 385, 55, 55}, {1920, 330, 55, 55}, {1975, 330, 55,55},
								  {2030, 330, 55, 55}, {2085, 330, 55, 55}, {2140, 330, 55, 55}, {2195, 330, 55, 55}};
 		int[][] yellowBlocSetter = {{455, 335, 55, 55}, {600, 200, 55, 55}, {2600, 335, 55, 55}};
 		int [][] itemSetter = {{460, 340, 45, 45}, {605, 202, 45, 45}, {2605, 340, 45, 45}};
 		String[] itemString = {"champignon", "etoile", "champignon"};
 		
		for(int i = 0; i<brownBlocSetter.length; i++) {
			StaticBloc sb = new StaticBloc(brownBlocSetter[i][0], brownBlocSetter[i][1], brownBlocSetter[i][2], brownBlocSetter[i][3]);
			sb.setNom(String.format("StaticBloc%d", i));
			blocs.add(sb);
			objets.add(sb);
		}
		for(int i = 0; i<yellowBlocSetter.length; i++) {
			//Creation des items
			Item it = new Item(itemSetter[i][0], itemSetter[i][1], itemSetter[i][2], itemSetter[i][3], itemString[i]);
			it.setIndice(objets.size());
			items.add(it);
			objets.add(it);
			
			//Creation des YellowBox
			YellowBox yb = new YellowBox(yellowBlocSetter[i][0], yellowBlocSetter[i][1], yellowBlocSetter[i][2], yellowBlocSetter[i][3]);
			yb.setItem(it);
			yb.setNom(String.format("YellowBox%d", i));
			yellowBoxes.add(yb);
			objets.add(yb);
		}
	}
	public void setBackground() {
		Image backgroundImage = (new ImageIcon(getClass().getResource("/image/blue_screen.png"))).getImage();
		background = new StaticBloc(0, 0, 1350, 800);
		background1 = new StaticBloc(1300, 0, 1350, 800);
		background.setImage(backgroundImage);
		background1.setImage(backgroundImage);
		background.setNom("background");
		background1.setNom("background1");
	}
	public void setSol() {

		Image brickImage = (new ImageIcon(getClass().getResource("/image/brick.png"))).getImage();
		for(int i = 0; i<20; i++) {
			StaticBloc sb = new StaticBloc(i*100, 600, 102, 100);
			sb.setImage(brickImage);
			sb.setNom("brique");
			bricks.add(sb);
			objets.add(sb);
		}
	}
	public void setGravity() {
		this.gravityTimer = new Timer(6, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Personnage personnage : personnages) {
					checkCollisions(personnage);
					if(personnage.getY()<= personnage.positionYInit-1 && !personnage.staticBlocCollision[0] && 
							!personnage.yellowBoxCollision[0] && !personnage.floorCollision[0] && 
							!personnage.tuyauCollision[0] && !personnage.platformCollision[0]) {
						personnage.setY(personnage.getY() + 4);
					}
				}
			}
		});
		gravityTimer.setCoalesce(true);
		this.gravityTimer.start();
		gameTimers.add(gravityTimer);
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public void setEnnemies() {
		Ennemy goomba0 = new Ennemy(1200, 400, 70, 70, "goomba"); goomba0.setDirectionDroite(true); personnages.add(goomba0); ennemies.add(goomba0);
		Ennemy goomba1 = new Ennemy(1300, 400, 70, 70, "goomba"); goomba1.setDirectionDroite(false); personnages.add(goomba1); ennemies.add(goomba1);
		Ennemy goomba2 = new Ennemy(2000, 400, 70, 70, "goomba"); goomba2.setDirectionDroite(true); personnages.add(goomba2); ennemies.add(goomba2);
	}
	public void setCastles(int longueur, int hauteur ) {
		Image castleImage = (new ImageIcon(getClass().getResource("/image/castle.png"))).getImage();
		
		startCastle= new Castle(0, 300, longueur, hauteur);
		startCastle.setImage(castleImage);
		
		endCastle=new Castle(3300, 300, longueur, hauteur);
		
		castles.add(startCastle);
		castles.add(endCastle);
		
		objets.add(startCastle);
		objets.add(endCastle);
	}
	public void setTuyau() {
		int[][] tuyauSetter = {{900, 530, 80, 80}, {1600, 530, 80, 80}, {2400, 530, 80, 80}};
		for(int i = 0; i<tuyauSetter.length; i++) {
			Tuyau tuyau = new Tuyau(tuyauSetter[i][0], tuyauSetter[i][1], tuyauSetter[i][2], tuyauSetter[i][3]);
			tuyaux.add(tuyau);
			objets.add(tuyau);
		}
	}
	public void setDecoration() {
		
		int[][] nuageSetter = {{ 0, 20, 100, 80}, {60, 20, 100, 80}, {200, 150, 120, 100}, {340, 65, 80, 80}, {380, 168, 80, 80},
							   {500, 40, 130, 110}, {565, 40, 130, 110}, {760, 200, 80, 70}, {899, 0, 60, 50}, {1000, 250, 40, 40}, 
							   {1020, 250, 40, 40}, {1100, 460, 130, 120}};
		
		Image nuage1Image = (new ImageIcon(getClass().getResource("/image/nuage1.png"))).getImage();
		nuages = new StaticBloc[nuageSetter.length];
		for(int i = 0; i<nuages.length; i++) {
			StaticBloc nuage = new StaticBloc(i*50, 0, 40, 30);
			nuage.setImage(nuage1Image);
			nuage.setNom("nuage");
			nuage.setAll(nuageSetter[i][0], nuageSetter[i][1], nuageSetter[i][2], nuageSetter[i][3]);
			nuages[i] = nuage;
			objets.add(nuage);
		}
	}
	public void setDefaultCollisionsTab() {
		for(int i = 0; i<collisionsTab.length; i++) {
			collisionsTab[i] = false;
		}
	}
	public void setJumpTimer() {
		jumpTimer = new Timer(6, new ActionListener() {
			final int Yderivate = 6;
			@Override
			public void actionPerformed(ActionEvent e) {
				checkCollisions(mario);
				mario.isJumping = true;
				if(mario.staticBlocCollision[2] || mario.yellowBoxCollision [2] || jumpCompteur[0] > 45 || mario.platformCollision[2]) {
					mario.isJumping = false;
					mario.setDefaultImage("mario");
					jumpCompteur[0] = 0;
					gravityTimer.restart();
					jumpTimer.stop();
	
				}
				if(mario.isJumping) {
					mario.setY(mario.getY()-Yderivate);
				}
				jumpCompteur[0] ++;
				//checkCollisions(mario);
			}
		});
		jumpTimer.setCoalesce(true);
		gameTimers.add(jumpTimer);
	}
	public void setPiece() {
		int[][] pieceSetter = {{408, 290, 40, 40}, {463, 290, 40, 40}, {518, 290, 40, 40}, {708, 290, 40, 40}, {763, 290, 40, 40}, 
				{803, 290, 40, 40}};
		for(int i = 0; i<pieceSetter.length; i++ ) {
			Piece piece = new Piece(pieceSetter[i][0], pieceSetter[i][1], pieceSetter[i][2], pieceSetter[i][3]);
			piece.animer1();
			pieces.add(piece);
			objets.add(piece);
		}
		Piece pc = new Piece(600, 500, 40, 40);
		pc.animer1();
		pieces.add(pc);
		objets.add(pc);
		
	}
	public void setKeyTab() {
		keyTab = new Boolean[3];
		Arrays.fill(keyTab, false);
	}
	public void setPlatform(){}
	public void setAudio() {
		mainTheme = new Audio("/Sound/maintheme.wav");
		mainTheme.setLoop(true);
	}
	public void setFlag() {
		flag = new Drapeau(3000, 200, 100, 400);
		flag.setVisible(true);
		this.add(flag);
		gameTimers.add(flag.animationTimer);
		
	}
	public void setArrayTimer() {
		for(Personnage p : personnages) {
			gameTimers.add(p.timerMarche);
			gameTimers.add(p.deadTimer);
			gameTimers.add(p.animationTimer);
		}
		gameTimers.add(mario.invincible);
		gameTimers.add(mario.mushroomTimer);
		
		for(Item it : items) {
			gameTimers.add(it.appearTimer);
		}
		for(Piece p : pieces) {
			gameTimers.add(p.animationTimer);
		}
	}
	public void genererMario() {
		mario = new Mario(400, 470, 80, 120);
		mario.setNom("mario");
		personnages.add(mario);
	}
	
	////****METHODES****////
	@Override
	public void paintComponent(Graphics g) {
		//------------------------------------Conversion G2D--------------------------------//
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		//------------------------------------Personnage et objets--------------------------//
		Image[] vies = Arrays.copyOf(mario.Vies, mario.Vies.length);
		checkCollisions(mario);
		g2d.drawImage(background.getImage(), background.getX(), background.getY(), background.getLargeur(), background.getHauteur(), this);
		bougerMonde(); 
		for(Objet objet : objets ) {g2d.drawImage(objet.getImage(), objet.getX(), objet.getY(), objet.getLargeur(), objet.getHauteur(), this);}
		for(Personnage perso : personnages) {g2d.drawImage(perso.image, perso.getX(), perso.getY(), perso.getLargeur(), perso.getHauteur(), this);}
		g2d.setFont(new Font("Algerian", Font.PLAIN, 30));
		String pcNumber = String.valueOf(mario.totalOfMoney);
		g2d.drawImage(pcImage, 1150, 10, 40, 40, this);
		g2d.drawString(pcNumber, 1200, 40);
		
		//////******Dessin de la vie de mario******//////
		for(int i = 0; i<vies.length; i++) {
			//final int d = 50;
			//final int h = 70;
			//int[] xPoints = {100 + i*2*d, 100 + 2*d + d, 100 + 1};
			//int[] yPoints = {30, h, 30};// trois coordonnées Y pour un triangle
			//g2d.fillOval(100 + i*2*d,  40,  d, d);
			//g2d.fillOval(100 + i*2*d + d,  40,  d,  d);
			//g2d.fillPolygon(xPoints, yPoints, 3);
			g2d.drawImage(vies[i], 10+i*60, 10, 50, 50, this);
		}
		//***Dessin des Items***//
		for(int i = 0; i<3; i++) {
			Ellipse2D.Float contour = new Ellipse2D.Float(i*70+400, 10, 60, 60);
			Ellipse2D.Double itemEllipse = new Ellipse2D.Double(i*60+400, 10, 50, 50);
			g2d.setColor(new Color(0, 100, 230, 130));
			g2d.fill(itemEllipse);
			g2d.setStroke(new BasicStroke(6.0f)); // Épaisseur du contour (en pixels)
	        g2d.setColor(new Color(255, 200, 80));            // Couleur du contour
	        g2d.draw(itemEllipse);
		}
		for(int i = 0; i<mario.itemStock.size(); i++) {
			g2d.drawImage(mario.itemStock.get(i).getImage(), i*60 + 405, 15, 40, 40, this);
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			usingItem = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			mario.setDirectionDroite(true);
			if(!mario.staticBlocCollision[1] && !mario.yellowBoxCollision[1] && !mario.tuyauCollision[1] && 
					!mario.castleCollision[1] && !mario.platformCollision[1]) {
				mario.setMarcheAutorisation(true);
				//mario.marcher("mario", 3);
				setDx(2);
				keyTab[0] = true;
			}
		}
			
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			mario.setDirectionDroite(false);
			if(!mario.staticBlocCollision[3] && !mario.yellowBoxCollision[3] && !mario.tuyauCollision[3] && 
					!mario.castleCollision[3] && !mario.platformCollision[3]) {
				mario.setMarcheAutorisation(true);
				//mario.marcher("mario", 3);
				setDx(2);
				keyTab[1] = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Audio.playSound("/Sound/jump.wav");
			if(gravityTimer.isRunning()) {gravityTimer.stop();}
			if(!jumpTimer.isRunning()) {mario.sauter("mario");jumpTimer.start();}
			keyTab[2] = true;
		}	
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keyTab[0] = false;
			mario.setMarcheAutorisation(false);
			mario.setDefaultImage("mario");
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			keyTab[1] = false;
			mario.setMarcheAutorisation(false);
			mario.setDefaultImage("mario");
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
		}
}
	public void bougerMonde() {
		if(mario.getMarcheAutorisation()) {
			if(background.getX() + background.getLargeur()<=0) {
				background.setX(background.getLargeur());
			}
			if(background1.getX() + background1.getLargeur()<=0) {
				background1.setX(background1.getLargeur());
			}
			for(StaticBloc nuage : nuages) {
				if(nuage.getX()+nuage.getLargeur()<0) {
					nuage.setX(1300);
				}
				if(nuage.getX()>1300) {
					nuage.setX(-nuage.getLargeur());
				}
			}
			for(StaticBloc brick : bricks) {
				if(brick.getX()+brick.getLargeur()<0) {
					brick.setX(1300);
				}
				if(brick.getX()>1300) {
					brick.setX(-brick.getLargeur());
				}
			}
			if(mario.getDirectionDroite() == true) {
				for(Objet objet : objets) {
					objet.setX(objet.getX()-dx);
				}
				for(int i = 1; i<personnages.size(); i++) {
					personnages.get(i).setX(personnages.get(i).getX()-dx);
				}
				flag.setX(flag.getX()-dx);
			}
			else {
				for(Objet objet : objets) {
					objet.setX(objet.getX()+dx);
				}
				for(int i = 1; i<personnages.size(); i++) {
					personnages.get(i).setX(personnages.get(i).getX()+dx);
				}
				flag.setX(flag.getX()+dx);
			}
		}
	}
	public void checkCollisions(Personnage personnage) {
		
		personnage.setCollisionDefaut();
		
		for(Ennemy en : ennemies) {
			mario.collisionWithEnnemy(en);
			if(mario.ennemyCollision[0]) {
				if(en.isAlive == true) {
					Audio.playSound("/Sound/deadennemy.wav");
					deadEnnemy(en);
					//ennemies.remove(en);
				}
			}
			if(mario.ennemyCollision[1] || mario.ennemyCollision[2] || mario.ennemyCollision[3]) {
				if(mario.canLooseLife && en.isAlive) {
					Audio.playSound("/Sound/hurt.wav");
					mario.canLooseLife = false;
					mario.decreaseLife();
					mario.recup();
				}
				if(mario.isInvincible && en.isAlive) {
					Audio.playSound("/Sound/deadennemy.wav");
					deadEnnemy(en);
				}
			}
		}
		////****COLLISION PIECES****////
		for(Piece pc : pieces) {
			if(mario.collisionADroite(pc) || mario.collisionAGauche(pc) ||
					mario.collisionEnBas(pc) || mario.collisionEnHaut(pc) ) {
				if(!pc.isTaken) {
					pc.isTaken = true;
					mario.totalOfMoney ++;
					Audio.playSound("/Sound/piece.wav");
					pc.setVisible(false);
					objets.remove(pc);
					pieces.remove(pc);
					break;
				}
				else {
					if(pc.isVisible()) {
						pc.setVisible(false);
					}
				}
			}
		}
		for(Item it : items) {
			if(mario.collisionADroite(it) || mario.collisionAGauche(it) || 
					mario.collisionEnBas(it) || mario.collisionEnHaut(it)) {
				if(!it.isTaken && it.isPrenable == true) {
					Audio.playSound("/Sound/collectitem.wav");
					it.isTaken = true;
					it.isPrenable = false;
					mario.addItem(it);
					objets.remove(it);
					items.remove(it);
					break;
				}
			}
		}
		for(StaticBloc bloc : blocs) { // Collision avec les blocs marrons
			if(personnage.collisionADroite(bloc)) {personnage.staticBlocCollision[3] = true;}
			if(personnage.collisionAGauche(bloc)) {personnage.staticBlocCollision[1] = true;}
			if(personnage.collisionEnBas(bloc)) {personnage.staticBlocCollision[2] = true;}
			if(personnage.collisionEnHaut(bloc)) {personnage.staticBlocCollision[0] = true;personnage.recalibrerY(bloc);}
		}
		for(Tuyau tuyau : tuyaux) { // Collision avec les tuyaux
			if(personnage.collisionADroite(tuyau)) {personnage.tuyauCollision[3] = true;}
			if(personnage.collisionAGauche(tuyau)) {personnage.tuyauCollision[1] = true;}
			if(personnage.collisionEnBas(tuyau)) {personnage.tuyauCollision[2] = true;}
			if(personnage.collisionEnHaut(tuyau)) {personnage.tuyauCollision[0] = true;personnage.recalibrerY(tuyau);}
		}
		for(YellowBox yb : yellowBoxes) { // Collision avec les boites jaunes
			if(personnage.collisionADroite(yb)) {personnage.yellowBoxCollision[3] = true;}
			if(personnage.collisionAGauche(yb)) {personnage.yellowBoxCollision[1] = true;}
			if(personnage.collisionEnBas(yb)) {
				personnage.yellowBoxCollision[2] = true;
				if(yb.item.hasBeenAnime == false) {
					Audio.playSound("/Sound/impact.wav");
					yb.setImage("blocvide", ".png");
					yb.item.animer();
					yb.item.hasBeenAnime = true;
					yb.item.isPrenable = true;
				}
				break;}
			if(personnage.collisionEnHaut(yb)) {personnage.yellowBoxCollision[0] = true;personnage.recalibrerY(yb);}
		}
		for(Platform p : platforms) {
			if(personnage.collisionADroite(p)) {personnage.tuyauCollision[3] = true;}
			if(personnage.collisionAGauche(p)) {personnage.tuyauCollision[1] = true;}
			if(personnage.collisionEnBas(p)) {personnage.tuyauCollision[2] = true;}
			if(personnage.collisionEnHaut(p)) {personnage.tuyauCollision[0] = true;personnage.recalibrerY(p);}
		}
		for(Castle castle : castles) { // Collision avec les chateaux de début et de fin
			if(personnage.collisionADroite(castle)) {personnage.castleCollision[3] = true; break;}
			else {personnage.castleCollision[3] = false;}
			if(personnage.collisionAGauche(castle)) {personnage.castleCollision[1] = true; break;}
			else {personnage.castleCollision[1] = false;}
		}
		for(StaticBloc brick : bricks) { // Collision avec le sol
			if(personnage.collisionEnHaut(brick)) {personnage.floorCollision[0] = true;}
		}
		mario.flagCollision[0] = mario.collisionEnHaut(flag);
		mario.flagCollision[1] = mario.collisionAGauche(flag);
		mario.flagCollision[2] = mario.collisionEnBas(flag);
		mario.flagCollision[3] = mario.collisionADroite(flag);
	}
	public String booleanPrinter(Boolean b) {
		if(b == true) {
			return "true";
		}
		return "false";
	}
	public void deadEnnemy(Ennemy ennemy) {
		
		ennemy.setMarcheAutorisation(false);
		ennemy.dead();
		ennemy.deadTimer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ennemy.deadCompteur>20) {
					ennemy.deadCompteur = 0;
					personnages.remove(ennemy);
					ennemies.remove(ennemy);
					ennemy.deadTimer.stop();
				}
				else {
					Image image;
					ImageIcon icon;
					if(ennemy.isDroite) {
						icon = new ImageIcon(getClass().getResource("/image/"+ennemy.nom+"_dead_right.png"));
					}
					else {icon = new ImageIcon(getClass().getResource("/image/"+ennemy.nom+"_dead_left.png"));}
					image = icon.getImage();
					ennemy.setImage(image);
				}
				ennemy.deadCompteur ++;
			}
		});
		ennemy.deadTimer.setCoalesce(true);
		ennemy.deadTimer.start();
		gameTimers.add(deadTimer);
	}
	public Integer calcuerX(Objet objetA, Objet objetB) {
		int milieu = (int)((objetA.getX()+objetA.getLargeur()+objetA.getX())/2);
		int X = (int)Math.abs(milieu - (objetB.getLargeur()/2));
		return X;
	}
	public Integer calculerY(Objet objetA, Objet objetB, int space) {
		return objetA.getY() - objetB.getHauteur() - space ;
	}
	public void stopAllTimers() {
		
		for(Timer timer : gameTimers) {
			if(timer != null) {
				if(timer.isCoalesce()) {timer.setCoalesce(false);}
				if(timer.isRepeats()) {timer.setRepeats(false);}
				if(timer.isRunning()) {timer.stop();}
			}
		}
	}
	public void destroy() {
		mainTheme.stop(); mainTheme = null;
		stopAllTimers();
		objets = null; ennemies = null; startCastle = null; endCastle = null; personnages = null; tuyaux = null; 
		blocs = null; bricks = null; castles = null; yellowBoxes = null; pieces = null; items = null; sounds = null; 
		gameTimers = null; nuages = null; keyTab = null; collisionsTab = null; background = null; background1 = null; mario = null; 
		jumpCompteur = null; pcImage = null; platforms = null;
	}
	public void addEnnemy(int x, int y, int width, int height, String name, Boolean dir) {
		
		final int F = 600; // Y Position of the floor
		Ennemy en = new Ennemy(x, y, width, height, name);
		en.setMinPos(F - height);
		en.setDirectionDroite(dir);
		personnages.add(en);
		ennemies.add(en);
	}
}