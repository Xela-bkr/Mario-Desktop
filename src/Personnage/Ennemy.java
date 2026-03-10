package Personnage;

import java.awt.Image;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Ennemy extends Personnage{
	
	//****VARIABLES****//
	
	public String nom;
	public Boolean isAlive;
	public int timerMarcheCompteur;
	
	//****CONSTRUCTEUR****//
	/**
	 * @param x
	 * @param y
	 * @param largeur
	 * @param hauteur
	 * @param nom
	 */
	public Ennemy(int x, int y, int largeur, int hauteur, String nom) {
		super(x, y, largeur, hauteur);
		this.nom = nom;
		isAlive = true;
		this.positionYInit = 535;
		setDefaultImage(nom);
		setMarcheAutorisation(true);
		//setThreadMarche();
		setTimerMarche();
		timerMarcheCompteur = 0;
	}
	public Ennemy(int x, int y, int largeur, int hauteur, String nom, int minPosY) {
		super(x, y, largeur, hauteur);
		this.positionYInit = minPosY; this.nom = nom; this.isAlive = true; this.positionYInit = 535;
		setDefaultImage(nom); setMarcheAutorisation(true); setTimerMarche();
		timerMarcheCompteur = 0;
	}
	
	//****GETTERS****//
	
	//****SETTERS****//
	
	@Override
	public void setDefaultImage(String nom) {
		if(this.getDirectionDroite() == true) {
			this.icon = new ImageIcon(getClass().getResource("/image/"+nom+"_stand_right.png"));
			this.image = icon.getImage();
			//this.image = (new ImageIcon("src/image/"+nom+"_stand_right.png")).getImage().getScaledInstance(largeur, hauteur, Image.SCALE_FAST);
		}
		else {
			this.icon = new ImageIcon(getClass().getResource("/image/"+nom+"_stand_right.png"));
			this.image = icon.getImage();
			//this.image = (new ImageIcon("src/image/"+nom+"_stand_left.png")).getImage().getScaledInstance(largeur, hauteur, Image.SCALE_FAST);
		}
	} 
	public void setMinPos(int pos) {this.positionYInit = pos;}
	public void setTimerMarche() {
		timerMarche = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {this.marcher(nom, 12);}
			private void marcher(String perso, int frequence) {
				if(frequence%3 != 0) {
					System.out.println("la fréquence de pas doit être multiple de 3 pour un ennemi");
					//return null;
				}
				Image image; ImageIcon icon; String path;
				if(isMarche) {
					if(isDroite) {
						if(compteur_pas<frequence/3) {path = "/image/" + perso + "_walk_right_0.png";}
						else if(compteur_pas >=frequence /3 && compteur_pas <2*frequence/3) {path = "/image/" + perso + "_walk_right_1.png";}
						else {path = "/image/" + perso + "_stand_right.png";}
						if(!staticBlocCollision[1] && !yellowBoxCollision[1] && !tuyauCollision[1] && !castleCollision[1]) {x +=3;}
						else {setDirectionDroite(false);}
					}
					else {
						if(compteur_pas<frequence/3) {path = "/image/" + perso + "_walk_left_0.png";}
						else if(compteur_pas >=frequence /3 && compteur_pas <2*frequence/3) {path = "/image/" + perso + "_walk_left_1.png";}
						else {path = "/image/" + perso + "_stand_left.png";}
						if(!staticBlocCollision[3] && !yellowBoxCollision[3] && !tuyauCollision[3] && !castleCollision[3]) {x -=3;}
						else {setDirectionDroite(true);}
					}
				}
				else {
					if(isDroite) {path = "/image/" + perso + "_stand_right.png";}
					else {path = "/image/" + perso + "_stand_left.png";}
				}
				if(compteur_pas == frequence) {compteur_pas = 0;}
				compteur_pas += 1;
				icon = new ImageIcon(getClass().getResource(path));
				image = icon.getImage();
				setImage(image);
				
			}
		});
		timerMarche.setCoalesce(true);
		timerMarche.start();
	}
	
	//****METHODES****//
	
	@Override
	public Image marcher(String perso , int frequence) {
		if(frequence%3 != 0) {
			System.out.println("la fréquence de pas doit être multiple de 3 pour un ennemi");
			return null;
		}
		Image image; ImageIcon icon; String path;
		//if(isMarche) {
			if(isDroite) {
				if(compteur_pas<frequence/3) {path = "image/" + perso + "_walk_right_0.png";}
				else if(compteur_pas >=frequence /3 && compteur_pas <2*frequence/3) {path = "image/" + perso + "_walk_right_1.png";}
				else {path = "image/" + perso + "_stand_right.png";}
				if(!staticBlocCollision[1] && !yellowBoxCollision[1] && !tuyauCollision[1] && !castleCollision[1]) {
					this.x +=3;
				}
				else {
					this.setDirectionDroite(false);
				}
			}
			else {
				if(compteur_pas<frequence/3) {path = "image/" + perso + "_walk_left_0.png";}
				else if(compteur_pas >=frequence /3 && compteur_pas <2*frequence/3) {path = "image/" + perso + "_walk_left_1.png";}
				else {path = "image/" + perso + "_stand_left.png";}
				if(!staticBlocCollision[3] && !yellowBoxCollision[3] && !tuyauCollision[3] && !castleCollision[3]) {
					this.x -=3;
				}
				else {
					this.setDirectionDroite(true);
				}
			}
		if(compteur_pas == frequence) {compteur_pas = 0;}
		compteur_pas += 1;
		icon = new ImageIcon(getClass().getResource(path));
		image = icon.getImage();
		setImage(image);
		return image;
	}
	public void dead() {
		timerMarche.stop();
		isAlive = false;
	}
	public void animer() {}
}
