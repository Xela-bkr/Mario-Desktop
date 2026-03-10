package Objet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public abstract class Objet extends JComponent {
	
	//****VARIABLES****//
	protected int posX;
	protected int posY;
	protected int largeur;
	protected int hauteur;
	protected int indice;
	public int rad;
	public Image image;
	protected ImageIcon icone;
	public String imagePath;
	protected String nom;
	protected int[] compteur = new int[1];
	public Boolean isRight, DOWN;
	
	//****CONSTRUCTEUR****//
	public Objet(int posX, int posY, int largeur, int hauteur) {
		this.posX = posX; this.posY = posY; this.largeur = largeur; this.hauteur = hauteur;
		isRight = true; DOWN = false;
	}
	
	//****GETTERS****//
	public int getX() {return this.posX;}
	public int getY() {return this.posY;}
	public int getLargeur() {return this.largeur;}
	public int getHauteur() {return this.hauteur;}
	public int getIndice() {return this.indice;}
	public Image getImage() {return this.image;}
	public ImageIcon getIcon() {return this.icone;}
	public String getNom() {return this.nom;}
	public String getImagePath() {return this.imagePath;}
	
	//****SETTERS****//
	
	public void setX(int x) {this.posX = x;}
	public void setY(int y) {this.posY = y;}
	public void setLargeur(int largeur) {this.largeur = largeur;}
	public void setHauteur(int hauteur) {this.hauteur = hauteur;}
	public void setIndice(int i) {this.indice = i;}
	public void setImage(Image image) {
		if(image != null) {
			this.image = image;
		}
		else {
			System.out.println("Erreur Image : Package -> Objet; Class -> Objet; Methode -> setImage()");
		}
	}
	public void setImage(String nom, String format) {
		this.imagePath = "/image/"+nom+"."+format;
		this.icone = new ImageIcon(getClass().getResource(imagePath));
		this.image = icone.getImage();
	}
	public void setNom(String nom) {this.nom = nom;}
	public void setAll(int x, int y, int largeur, int hauteur) {
		this.setX(x); this.setY(y); this.setLargeur(largeur); this.setHauteur(hauteur);
	}
	public void setCompteur(int i) {this.compteur[0] = i;}
	
	//****METHODES****//
	public void animer() {}

}
	
