package UIComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Objet.Objet;
import Objet.StaticBloc;
import Objet.YellowBox;
import Personnage.Personnage;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/*Bouton héritant de la classe JButton
 * Réécriture du graphisme du bouton pour obtenir une animation --> @Override paintComponent(Graphics g)
 * 
 * 		Parametres Constructeur 
 * chemin, image, icon -> fond d'écran du bouton.
 * Integers -> width + height
 */

@SuppressWarnings("serial")
public class AnimatedButton extends JButton{
	
	//****VARIABLES DE CLASSE****//
	public Image image;
	private ImageIcon icon;
	public ArrayList<Objet> objet = new ArrayList<Objet>();
	public ArrayList<Personnage> personnage = new ArrayList<Personnage>();
	public int width, height;
	
	//****CONSTRUCTEUR****//
	
	public AnimatedButton(int width, int height) {
		super();
		this.width = width; this.height = height;
		setBorder(BorderFactory.createLineBorder(new Color(200, 100, 50, 200), 10));
		setProperties();
		setObjets();
	}
	public AnimatedButton(Image image, int width, int height) {
		super();
		this.image = image;
		this.width = width; this.height = height;
		setBorder(BorderFactory.createLineBorder(new Color(200, 100, 50, 200), 10));
		setProperties();
		setObjets();
	}
	public AnimatedButton(ImageIcon icone, int width, int height) {
		super();
		this.width = width; this.height = height;
		this.icon = icone;
		this.image = icone.getImage();
		setBorder(BorderFactory.createLineBorder(new Color(200, 100, 50, 200), 10));
		setProperties();
		setObjets();

	}
	
	//****GETTERS****//
	//public int getWidth() {return this.width;}
	//public int getHeight() {return this.height;}
	public Image getImage() {return this.image;}
	
	//****SETTERS****//
	public void setProperties() {
		setFocusable(true);
		setOpaque(true);
		setFocusPainted(true);
		setBorderPainted(false);
		setContentAreaFilled(false);
		requestFocusInWindow(true);
		//setVisible(true);
	}
	public void setBackground(String path) {
		this.image = null;
		icon = new ImageIcon(getClass().getResource(path));
		image = icon.getImage();
		//repaint();
	}
	public void setBackground(Image image) {this.image = image;}
	public void setBackground(ImageIcon icon) {
		ImageIcon icone = icon;
		this.image = icone.getImage();
		//repaint();
	}
	public void setObjets() {
		
	}// TODO @Override function for setting animated objects
	
	//****METHODES****//
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(this.image,  0,  0,  width,  height,  this);
		//g2d.drawLine(0,  0,  getWidth(),  getHeight());
		for (Objet objet : objet) {
		    if (objet.getImage() != null) {
		    	ImageIcon icon = new ImageIcon("src"+objet.imagePath);
		    	Image image0 = icon.getImage();
		        g2d.drawImage(image0, objet.getX(), objet.getY(), objet.getLargeur(), objet.getHauteur(), this);
		        //g2d.setColor(Color.RED);  // Choisis une couleur de test
		        //g2d.fillRect(objet.getX(), objet.getY(), objet.getLargeur(), objet.getHauteur());
		    } else {
		        // Si l'image est null, tu peux dessiner un rectangle de test pour vérifier que l'objet est bien là
		        g2d.setColor(Color.BLUE);  // Choisis une couleur de test
		        g2d.fillRect(objet.getX(), objet.getY(), objet.getLargeur(), objet.getHauteur());
		    }
		}
		if(personnage.size()>0) {
			for(Personnage p : personnage) {
				g2d.drawImage(p.getImage(), p.getX(), p.getY(), p.getLargeur(), p.getHauteur(), this);
			}
		}
	}
}
