package Objet;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Piece extends Objet{
	
	public int compteur;
	public Boolean isAnimer;
	public Boolean isTaken;
	public Timer animationTimer;
	
	public Piece(int x, int y, int largeur, int hauteur) {
		super(x,y,largeur,hauteur);
		imagePath = "/image/piece.png";
		this.icone = new ImageIcon("src/image/piece.png");
		this.image = icone.getImage().getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
		this.nom = "piece"; this.isAnimer = true; compteur = 0; isTaken = false;
	}
	public void animer1() {
		animationTimer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (compteur < 20) {setLargeur(getLargeur() - 2);setX(getX() + 1);} 
				if(compteur >= 20 && compteur<40) {setLargeur(getLargeur() + 2);setX(getX() - 1);}
			    compteur++;
			    if (compteur >= 40) {compteur = 0;}
			}
		});animationTimer.start();
	}
}
