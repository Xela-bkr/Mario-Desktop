package Personnage;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

import Objet.Castle;
import Objet.Objet;
import javax.swing.Timer;

import Game.GameComponent;
import Game.GameLoop;

public class Personnage {
	
	//****VARIABLES****//
	public int x, y, largeur, hauteur;
	public Image image;
	protected ImageIcon icon;
	protected int compteur_pas, animationCompteur;
	public int deadCompteur;
	public Boolean isDroite,isMarche, isJumping, isAlive;
	public Boolean[] collisions, staticBlocCollision, tuyauCollision, yellowBoxCollision, ennemyCollision, floorCollision, 
	castleCollision, flagCollision, platformCollision;
	// HAUT..GAUCHE..BAS..DROITE --> Par rapport à l'objet
	public int positionYInit;
	public int positionYLimit;
	public String nom;
	public Timer timerMarche, deadTimer, animationTimer;
	
	//****CONSTRUCTEUR****//
	/**
	 * @param x
	 * @param y
	 * @param largeur
	 * @param hauteur
	 */
	public Personnage(int x, int y, int largeur, int hauteur) {
		this.x = x; this.y = y; this.largeur = largeur; this.hauteur = hauteur;
		this.positionYInit = 484; positionYLimit = 390; compteur_pas = 0; this.isJumping = false; this.isAlive = true;
		this.collisions = new Boolean[4]; this.staticBlocCollision = new Boolean[4]; this.tuyauCollision = new Boolean[4];
		this.yellowBoxCollision = new Boolean[4]; this.ennemyCollision = new Boolean[4]; this.floorCollision = new Boolean[4];
		this.castleCollision = new Boolean[4]; animationCompteur = 0; deadCompteur = 0; this.flagCollision = new Boolean[4];
		this.platformCollision = new Boolean[4];
		setDirectionDroite(true); setMarcheAutorisation(false); setCollisionDefaut();
	}

	//****GETTERS****//
	public int getX() {return x;}
	public int getY() {return y;}
	public int getLargeur() {return largeur;}
	public int getHauteur() {return hauteur;}
	public Image getImage() {return image;}
	public String getNom() {return this.nom;}
	public Boolean getDirectionDroite() {return isDroite;}
	public Boolean getMarcheAutorisation() {return isMarche;}
	public Boolean getCollisionHaut() {return collisions[0];}
	public Boolean getCollisionBas() {return collisions[2];}
	public Boolean getCollisionGauche() {return collisions[1];}
	public Boolean getCollisionDroite() {return collisions[3];}
	
	//****SETTERS****//
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void setLargeur(int longueur) {this.largeur = longueur;}
	public void setHauteur(int hauteur) {this.hauteur = hauteur;}
	public void setImage(Image image) {if(image != null) {this.image = image;}}
	public void setImage(String nom, String extention) {
		ImageIcon ico= new ImageIcon(getClass().getResource("/image/"+nom+"."+extention));
		this.image = ico.getImage();
	}
	public void setDefaultImage(String nom) {
		this.nom = nom;
		if(this.getDirectionDroite()) {
			this.image = (new ImageIcon(getClass().getResource("/image/"+nom+"_arret_droite.png"))).getImage();
		}
		else {
			this.image = (new ImageIcon(getClass().getResource("/image/"+nom+"_arret_gauche.png"))).getImage();
		}
	}
	public void setDirectionDroite(Boolean b) {isDroite = b;}
	public void setMarcheAutorisation(Boolean b) {isMarche = b;}
	public void setCollisionDefaut() {
		for(int i = 0; i<collisions.length ; i++) {
			collisions[i] = false;
			yellowBoxCollision[i] = false;
			staticBlocCollision[i] = false;
			ennemyCollision[i] = false;
			tuyauCollision[i] = false;
			floorCollision[i] = false;
			castleCollision[i] = false;
			flagCollision[i] = false;
			platformCollision[i] = false;
		}
	}
	public void setCollisionHaut(Boolean b) {collisions[0] = b;}
	public void setCollisionBas(Boolean b) {collisions[2] = b;}
	public void setCollisionGauche(Boolean b) {collisions[1] = b;}
	public void setCollisionDroite(Boolean b) {collisions[3] = b;}
	public void setNom(String nom) {this.nom = nom;}
	public void setTimerMarche() {}
	
	//****METHODES****//
	public Boolean collisionEnHaut(Objet objet) { 
		final int ERR = 1;
		final int lateralERR = 5;
		if(this.y+this.hauteur >= objet.getY()-ERR &&
				this.y+this.hauteur<=objet.getY()+objet.getHauteur()/2 &&
				this.x <= objet.getX()+objet.getLargeur()-lateralERR &&
				this.x+this.largeur >= objet.getX()+lateralERR) {return true;}
		return false;
	}
	public Boolean collisionAGauche(Objet objet) { // Collision à gauche de l'objet
		final int ERR = 3;
		final int lateralERR = 5;
	    if (this.x + this.largeur>= objet.getX() - ERR && 
	        this.x + this.largeur < objet.getX() + objet.getLargeur() &&
	        this.y <= objet.getY() + objet.getHauteur()  && 
	        this.y + this.hauteur >= objet.getY() + lateralERR){return true;}
	    return false;
	}
	public Boolean collisionADroite(Objet objet) { // Collision à droite de l'objet
		final int ERR = 0;
		final int lateralERR = 5;
	    if (this.x >= objet.getX() && 
	        this.x <= objet.getX() + objet.getLargeur() + ERR &&
	        this.y <= objet.getY() + objet.getHauteur()  && 
	        this.y + this.hauteur >= objet.getY() + lateralERR){return true;}
	   return false;
	}
	public Boolean collisionEnBas(Objet objet) { //Collision en bas de l'objet
		int ERR = 0;
		if(this.y<=objet.getY()+objet.getHauteur()+ERR &&
			this.y>objet.getY() &&
			this.x+this.largeur>=objet.getX()&&
			this.x<=objet.getX()+objet.getLargeur()) {return true;}
		return false;
	}
	public Image marcher(String perso, int frequence) {
		Image image; ImageIcon icon; String path;
		if(isMarche) {
			if(isDroite) {
				if(compteur_pas>frequence) {path = "/image/" + perso + "_walk_droite.png";}
				else {path = "/image/" + perso + "_arret_droite.png";}
			}
			else {
				if(compteur_pas>frequence) {path = "/image/" + perso + "_walk_gauche.png";}
				else {path = "/image/" + perso + "_arret_gauche.png";}
			}
		}
		else {
			if(isDroite) {path = "/image/" + perso + "_arret_droite.png";}
			else {path = "/image/" + perso + "_arret_gauche.png";}
		}
		if(compteur_pas == 2*frequence) {compteur_pas = 0;}
		compteur_pas += 1;
		icon = new ImageIcon(getClass().getResource(path));
		image = icon.getImage();
		this.image = image;
		return image;
	} 
	public Image sauter(String nom) {
		Image image; ImageIcon icon; String path;
		
		if(this.isDroite) {path = "/image/"+nom+"_jump_right.png";}
		else {path = "/image/"+nom+"_jump_left.png";}
		
		icon = new ImageIcon(getClass().getResource(path));
		image = icon.getImage();
		this.image = image; return image;
	}
	public void recalibrerY(Objet objet) {
		setY(objet.getY()-2-hauteur);
	}
	public void dead() {
		this.isAlive = false;
	}
	public void stopTimerMarche() {if(timerMarche != null && timerMarche.isRunning()) {timerMarche.stop();}}
}
