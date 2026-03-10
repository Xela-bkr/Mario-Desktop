package Objet;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Tuyau extends Objet{
	
	public Tuyau(int posX, int posY, int largeur, int hauteur) {
		super(posX, posY, largeur, hauteur);
		icone = new ImageIcon("src/image/tuyauvert.png");
		image = icone.getImage().getScaledInstance(largeur, hauteur, Image.SCALE_FAST);
		setNom("Tuyau");
	}

}
