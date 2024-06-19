package com.group_finity.mascot.imagesetchooser;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Displays information about an image set.  Used by ImageSetChooser
 */
public class ImageSetChooserPanel extends javax.swing.JPanel {

    public ImageSetChooserPanel() {
        initComponents();
    }

    public ImageSetChooserPanel( String name, String actions,
			String behaviors, String imageLocation ) {
        initComponents();

        this.name.setText( name );
        this.actionsFile.setText( actions );
        this.behaviorsFile.setText( behaviors );
        try {
          BufferedImage img = ImageIO.read(new File(imageLocation));
          this.image.setIcon( new ImageIcon( img.getScaledInstance(60, 60, Image.SCALE_DEFAULT) ) );
        } catch( Exception e ) {
		  //Doesn't matter, the image just won't show
        }
    }

    public void setCheckbox( boolean value ) {
        checkbox.setSelected(value);
    }

	public String getImageSetName() {
		return this.name.getText();
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    checkbox = new javax.swing.JCheckBox();
    name = new javax.swing.JLabel();
    actionsFile = new javax.swing.JLabel();
    behaviorsFile = new javax.swing.JLabel();
    image = new javax.swing.JLabel();

    setBorder(javax.swing.BorderFactory.createEtchedBorder());
    setMinimumSize(new java.awt.Dimension(248, 80));
    setPreferredSize(new java.awt.Dimension(248, 80));
    setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    checkbox.setOpaque(false);
    add(checkbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

    name.setText("Builder");
    add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

    actionsFile.setText("img/Builder/conf/actionsxml");
    add(actionsFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

    behaviorsFile.setText("img/Builder/conf/behaviors.xml");
    add(behaviorsFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

    image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 60, 60));
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel actionsFile;
  private javax.swing.JLabel behaviorsFile;
  private javax.swing.JCheckBox checkbox;
  private javax.swing.JLabel image;
  private javax.swing.JLabel name;
  // End of variables declaration//GEN-END:variables

}
