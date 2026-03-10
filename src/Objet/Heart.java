package Objet;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Heart extends Objet{
	
	public Boolean isEmpty;

	public Heart(int posX, int posY, int largeur, int hauteur) {
		super(posX, posY, largeur, hauteur);
		this.isEmpty = true;
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
	}

}
