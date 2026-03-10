package Objet;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;


import Game.GameComponent;

public class StaticBloc extends Objet{
	
	public StaticBloc(int posX, int posY, int largeur, int hauteur) {
		super(posX, posY, largeur, hauteur);
		imagePath = "/image/brownbloc.png";
		this.icone = new ImageIcon(getClass().getResource("/image/brownbloc.png"));
		this.image = icone.getImage();
		setCompteur(0);
	}
	public StaticBloc(int posX, int posY, int largeur, int hauteur, Image image) {
		super(posX, posY, largeur, hauteur);
		this.image = image;
		setCompteur(0);
	}
	public StaticBloc(int posX, int posY, int largeur, int hauteur, String path) {
		super(posX, posY, largeur, hauteur);
		imagePath = path;
		this.icone = new ImageIcon(getClass().getResource(imagePath));
		this.image = icone.getImage();
		setCompteur(0);
	}
}
