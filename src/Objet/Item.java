package Objet;

import javax.swing.Timer;

import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Item extends Objet{
	
	public String nom = "";
	public Timer appearTimer;
	private int compteur;
	public Boolean isPrenable, isTaken, hasBeenAnime, animationEnable;

	public Item(int posX, int posY, int largeur, int hauteur, String nom) {
		super(posX, posY, largeur, hauteur);
		this.posX = posX; this.posY = posY; this.largeur = largeur; this.posY = posY; this.nom = nom;
		this.icone = new ImageIcon(getClass().getResource("/image/"+nom+".png"));
		this.image = icone.getImage();
		this.image = this.image.getScaledInstance(largeur, hauteur, Image.SCALE_FAST);
		this.compteur = 0; isPrenable = false; isTaken = false; hasBeenAnime = false; animationEnable = false;
		//animer();
	}
	
	public void setItem(String nom) {
		this.icone = new ImageIcon(getClass().getResource("/image/"+nom+".png"));
		this.image = icone.getImage().getScaledInstance(largeur, hauteur, Image.SCALE_FAST);
		this.setNom(nom);
	}
	@Override
	public void animer() {
		appearTimer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(compteur>10) {
					//compteur = 0;
					isPrenable = true;
					appearTimer.stop();
				}
				else {
					setY(getY()-5);
					compteur += 1;
				}
			}
		});
		appearTimer.setCoalesce(true);
		appearTimer.start();
	}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public Timer getAppearTimer() {return appearTimer;}
	public void setAppearTimer(Timer appearTimer) {this.appearTimer = appearTimer;}
	public Boolean getIsPrenable() {return isPrenable;}
	public void setIsPrenable(Boolean isPrenable) {this.isPrenable = isPrenable;}
	public Boolean getIsTaken() {return isTaken;}
	public void setIsTaken(Boolean isTaken) {this.isTaken = isTaken;}
}
