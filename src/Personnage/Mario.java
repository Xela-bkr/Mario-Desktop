package Personnage;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Timer;

import Game.Audio;

import java.awt.Image;
import javax.swing.ImageIcon;

import Objet.Item;

public class Mario extends Personnage{
	
	public int totalOfMoney, invincibleTimerCompteur;
	public Image[] Vies;
	public String marioMoney;
	public int life, compteurMushroomTimer;
	public ArrayList<Item> itemStock;
	public Boolean isInvincible, canLooseLife;
	public Timer invincible, mushroomTimer;
	public Audio mushroom, star;

	public Mario(int x, int y, int longueur, int hauteur) {
		super(x, y, longueur, hauteur);
		this.animer();
		setDefaultImage("mario");
		setDefaultLife(3);
		setDefaultLifeArray();
		totalOfMoney = 0; isInvincible = false; invincibleTimerCompteur = 0;
		itemStock = new ArrayList<Item>();
		compteurMushroomTimer = 0; canLooseLife = true;
		setInvincibleTimer(); setMushroomTimer(); setAudio();
	}
	
	public void setDefaultLife(int i) {this.life = i;}
	public void collisionWithEnnemy(Personnage ennemy) {
		final int ERR = 10;
		//Collision en haut de l'ennemi
		if(this.y+this.hauteur >= ennemy.getY() &&
				this.y+this.hauteur<=ennemy.getY()+ennemy.getHauteur()-10 &&
				this.x <= ennemy.getX()+ennemy.getLargeur()-10 &&
				this.x+this.largeur >= ennemy.getX()+10) {this.ennemyCollision[0] = true;}
		else {ennemyCollision[0] = false;}
		
		//Collision à gauche de l'ennemi
	    if (this.x + this.largeur>= ennemy.getX() + ERR && 
	        this.x + this.largeur <= ennemy.getX() + ennemy.getLargeur() - ERR &&
	        this.y < ennemy.getY() + ennemy.getHauteur() && 
	        this.y + this.hauteur > ennemy.getY() ){ennemyCollision[1] = true;}
	    else {ennemyCollision[1] = false;}
	    
	    //Collision à droite de l'ennemi
	    if (this.x >= ennemy.getX() + ERR && 
		        this.x <= ennemy.getX() + ennemy.getLargeur() - ERR &&
		        this.y < ennemy.getY() + ennemy.getHauteur() && 
		        this.y + this.hauteur > ennemy.getY() ){ennemyCollision[3] = true;}
	    else {ennemyCollision[3] = false;}
	    
	    //Colision en bas de l'ennemi
	    if(this.y<=ennemy.getY()+ennemy.getHauteur() &&
				this.y>ennemy.getY() &&
				this.x+this.largeur>=ennemy.getX()&&
				this.x<=ennemy.getX()+ennemy.getLargeur()) {ennemyCollision[2] = true;}
	    else {ennemyCollision[2] = false;}
	}
	public void setDefaultLifeArray() {
		Vies = new Image[3];
		Arrays.fill(Vies, (new ImageIcon(getClass().getResource("/image/fullheart.png")).getImage().getScaledInstance(50, 50, Image.SCALE_FAST)));
	}
	public void addItem(Item it) {if(itemStock.size()<3) {itemStock.add(it);}}
	public void decreaseLife() {
		life--;
		if(life == 0) {
			System.out.println("GameOver");
		}
		else {
			updateVies();
		}
	}
	public void updateVies(){
		for(int i = 0; i<3; i++) {
			ImageIcon emptyHeartIcon = new ImageIcon(getClass().getResource("/image/emptyheart.png"));
			Image emptyHeartImage = emptyHeartIcon.getImage();
			Vies[i] = emptyHeartImage;
		}
		for(int i = 0; i<life; i++) {
			ImageIcon fullHeartIcon = new ImageIcon(getClass().getResource("/image/fullheart.png"));
			Image fullHeartImage = fullHeartIcon.getImage();
			Vies[i] = fullHeartImage;
		}
	}
	public void setInvincibleTimer() {
		invincible = new Timer(10000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(invincibleTimerCompteur >=1) {
					isInvincible = false;
					enableLoosingLife(true);
					invincible.stop();
				}
				else {
					isInvincible = true;
					enableLoosingLife(false);
					invincibleTimerCompteur ++;
				}
			}
		});
		invincible.setCoalesce(true);
		//invincible.setRepeats(false);
	}
	public void recup() {
		Timer recup = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canLooseLife = true;
			}
		});recup.start();
	}
	public void enableLoosingLife(Boolean b) {canLooseLife = b;}
	public void setMushroomTimer() {
		
		mushroomTimer = new Timer(50, new ActionListener() {
			int mushCompteur = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mushCompteur>10) {
					setLargeur(79);
					setHauteur(120);
					mushCompteur = 0;
					mushroomTimer.stop();
				}
				if(getLargeur()>=80 && getLargeur()< 100) {
					setLargeur(getLargeur()+10);
				}
				
				if(getLargeur()>=100) {setLargeur(80);}
				if(getHauteur()>=120 && getLargeur()< 140) {
					setHauteur(getHauteur()+10);}
				if(getHauteur()>=140) {setHauteur(120);}
				mushCompteur ++;
			}	
		});
	}
	public void setAudio() {
		mushroom = new Audio("/Sound/mushroom.wav");
		star = new Audio("/Sound/staritem.wav");
	}
	public void animer() {}
}
