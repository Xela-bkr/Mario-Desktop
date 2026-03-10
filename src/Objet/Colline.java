package Objet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Colline extends Objet{
	
	//****VARIABLES****//
	private ArrayList<Double> posX = new ArrayList<Double>();
	private ArrayList<Double> poxY = new ArrayList<Double>();

	private int ray;
	
	private Color color;
	
	private Boolean patch;
	private Color patchColor;

	//****CONSTRUCTEUR****//
	public Colline(int posX, int posY, int largeur, int hauteur) {
		super(posX, posY, largeur, hauteur);
		this.setBounds(posX, posY, largeur, hauteur);
		this.setOpaque(true);
		this.ray = largeur;
		setPosXY(); setPatchPainted(true);
	}
	public Colline(int posX, int posY, int largeur, int hauteur, Color color) {
		super(posX, posY, largeur, hauteur);
		this.setBounds(posX, posY, largeur, hauteur);
		this.ray = largeur;
		this.color = color;
		setPosXY(); setPatchPainted(true);
	}
	
	//****GETTERS****//
	
	//****SETTERS****//
	public void setPosXY(){for(int i = 0; i<this.largeur; i++) {posX.add((double)i);}}
	public void setPatchColor(Color color) {this.patchColor = color;}
	public void setPatchPainted(Boolean b) {this.patch = true;}
	//****METHODES****//
	@Override 
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		if(this.color == null) {g2d.setColor(Color.green);}
		else {g2d.setColor(this.color);}
		
		Ellipse2D.Double ellipse = new Ellipse2D.Double(0,  0,  ray,  ray);
		Rectangle2D.Double rectangle = new Rectangle2D.Double(0,  ray/2,  getLargeur(), getHauteur());
		g2d.draw(ellipse);
		g2d.fill(ellipse);
		g2d.draw(rectangle);
		g2d.fill(rectangle);
		
		if(this.patch) {
			
			final int x0 = getLargeur()/4;
			final int y0 = getHauteur()/4;
			
			final int x1 = 3*getLargeur()/4;
			final int y1 = getHauteur() - 2*getHauteur()/3;
			
			final int x2 = getLargeur()/5;
			final int y2 = 4*getHauteur()/5;
			
			Ellipse2D.Double patch = new Ellipse2D.Double(x0, y0,  getLargeur()/5,  getLargeur()/6);
			Ellipse2D.Double patch1 = new Ellipse2D.Double(x1, y1,  getLargeur()/5,  getLargeur()/6);
			Ellipse2D.Double patch2 = new Ellipse2D.Double(x2,  y2,  getLargeur()/5,  getLargeur()/6);
			
			if(patchColor != null) {g2d.setColor(patchColor);}
			else {g2d.setColor(Color.cyan);}
			
			g2d.draw(patch);
			g2d.fill(patch);
			
			g2d.draw(patch1);
			g2d.fill(patch1);
			
			g2d.draw(patch2);
			g2d.fill(patch2);
		}
	}

}
