package Objet;

import java.awt.Color;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Drapeau extends Objet{
	
	//****VARIABLES****//
	public Timer animationTimer;
	private int[] triangleX = new int[3];
	private int[] triangleY = new int[3];

	//****CONSTRUCTEUR****//
	public Drapeau(int posX, int posY, int largeur, int hauteur) {
		super(posX, posY, largeur, hauteur);
		setBounds(posX, posY, largeur, hauteur);
		setOpaque(false);
		triangleX[0] = getLargeur()/2 -20; triangleX[1] = getLargeur()/2 + 50; triangleX[2] = getLargeur()/2 -20;
		triangleY[0] = getHauteur()-60*2; triangleY[1] = getHauteur()-60-30; triangleY[2] = getHauteur()-60;
		// TODO Auto-generated constructor stub
	}
	
	//****GETTERS****//
	public int[] getTriangleX() {return this.triangleX;}
	public int[] getTriangleY() {return this.triangleY;}
	
	//****SETTERS****//
	public void setTriangleX(int a, int b, int c) {triangleX[0] = a; triangleX[1] = b; triangleX[2] = c;}
	public void setTriangleY(int a, int b, int c) {triangleY[0] = a; triangleY[1] = b; triangleY[2] = c; revalidate();repaint();}
	
	//****METHODES****//
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		Ellipse2D.Double ellipse = new Ellipse2D.Double(getLargeur()/2 - 10 -20, 3, 20, 20);// Cercle de rayon 10px.
		Rectangle2D.Double barre = new Rectangle2D.Double(getLargeur()/2 - 5 -20, 18, 10, getHauteur()); // Barre verticale de 10px de large
		Image bloc = (new ImageIcon(getClass().getResource("/image/blocvide.png"))).getImage();
		
		GradientPaint gradientPaint = new GradientPaint(getLargeur()/2 - 5 - 20,18, Color.gray, getLargeur(), getHauteur(), new Color(176, 176, 160), true);
		GradientPaint gradientPaint1 = new GradientPaint(getX(), getY() + getHauteur()/2, new Color(242, 203, 8), getX()+getLargeur(), getY() + getLargeur() + getHauteur()/2,new Color(0, 183, 4), true);//new Color(246, 183, 4)
		float milieuX1 = (triangleX[0]+triangleX[1])/2 + getX();
		float milieuY1 = (triangleY[0] + triangleY[1])/2;
		
		float milieuX2 = triangleX[2] + getX();
		float milieuY2 = triangleY[2];
		GradientPaint gradientPaint2 = new GradientPaint(milieuX1, milieuY1, new Color(218, 54, 50), milieuX2, milieuY2, new Color(252, 207, 16), true);
		
		g2d.setPaint(gradientPaint);
		g2d.draw(barre);
		g2d.fill(barre);
		//g2d.clip(barre);
		//g2d.setColor(new Color(242, 203, 8));
		g2d.setPaint(gradientPaint1);
		g2d.draw(ellipse);
		g2d.fill(ellipse);
		
		g2d.setPaint(null);
		g2d.drawImage(bloc, 0, getHauteur()-60, 60, 60, this);
		
		g2d.setPaint(gradientPaint2);
		g2d.fillPolygon(triangleX, triangleY, 3);
	}

}
