package UIComponent;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.border.AbstractBorder;

@SuppressWarnings("serial")
public class AnimatedBorder extends AbstractBorder{
	
	private Image image;
	private Component c;
	private int inset;
	
	public AnimatedBorder(Component c, String ImagePath, int inset) {
		ImageIcon icon = new ImageIcon(getClass().getResource(ImagePath));
		this.image = icon.getImage();
		this.c = c; this.inset = inset;
	}
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		super.paintBorder(c, g, x, y, width, height);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, x, y, width, height, null);
	}
	@Override
	public Insets getBorderInsets(Component c) {
		// TODO Auto-generated method stub
		return new Insets(inset, inset, inset, inset);
	}
	@Override
	public boolean isBorderOpaque() {
		// TODO Auto-generated method stub
		return false;
	}

}
