package com.group_finity.mascot;

/**
 *
 * @author Kilkakon
 */
public class DebugWindow extends javax.swing.JFrame
{
    public DebugWindow( )
    {
        initComponents( );
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblShimejiX = new javax.swing.JLabel();
        lblShimejiXValue = new javax.swing.JLabel();
        lblShimejiYValue = new javax.swing.JLabel();
        lblShimejiY = new javax.swing.JLabel();
        lblWindowX = new javax.swing.JLabel();
        lblWindowY = new javax.swing.JLabel();
        lblWindowWidth = new javax.swing.JLabel();
        lblWindowHeight = new javax.swing.JLabel();
        lblWindowXValue = new javax.swing.JLabel();
        lblWindowYValue = new javax.swing.JLabel();
        lblWindowWidthValue = new javax.swing.JLabel();
        lblWindowHeightValue = new javax.swing.JLabel();
        lblBehaviour = new javax.swing.JLabel();
        lblBehaviourValue = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblShimejiX.setText("Shimeji X");

        lblShimejiXValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblShimejiXValue.setText("jLabel2");

        lblShimejiYValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblShimejiYValue.setText("jLabel3");

        lblShimejiY.setText("Shimeji Y");

        lblWindowX.setText("Window X");

        lblWindowY.setText("Window Y");

        lblWindowWidth.setText("Window W");

        lblWindowHeight.setText("Window H");

        lblWindowXValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblWindowXValue.setText("jLabel9");

        lblWindowYValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblWindowYValue.setText("jLabel10");

        lblWindowWidthValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblWindowWidthValue.setText("jLabel11");

        lblWindowHeightValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblWindowHeightValue.setText("jLabel12");

        lblBehaviour.setText("Behaviour");

        lblBehaviourValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBehaviourValue.setText("jLabel14");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBehaviour)
                        .addGap(45, 45, 45)
                        .addComponent(lblBehaviourValue, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblShimejiX)
                            .addComponent(lblShimejiY)
                            .addComponent(lblWindowX)
                            .addComponent(lblWindowY)
                            .addComponent(lblWindowWidth)
                            .addComponent(lblWindowHeight))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblShimejiYValue)
                            .addComponent(lblShimejiXValue)
                            .addComponent(lblWindowXValue)
                            .addComponent(lblWindowYValue)
                            .addComponent(lblWindowWidthValue)
                            .addComponent(lblWindowHeightValue))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBehaviour)
                    .addComponent(lblBehaviourValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblShimejiX)
                    .addComponent(lblShimejiXValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblShimejiYValue)
                    .addComponent(lblShimejiY))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWindowX)
                    .addComponent(lblWindowXValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWindowY)
                    .addComponent(lblWindowYValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWindowWidth)
                    .addComponent(lblWindowWidthValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWindowHeight)
                    .addComponent(lblWindowHeightValue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void setBehaviour( String text )
    {
        lblBehaviourValue.setText( text );
    }
    
    void setShimejiX( int x )
    {
        lblShimejiXValue.setText( String.format( "%d", x ) );
    }
    
    void setShimejiY( int y )
    {
        lblShimejiYValue.setText( String.format( "%d", y ) );
    }
    
    void setWindowX( int x )
    {
        lblWindowXValue.setText( String.format( "%d", x ) );
    }
    
    void setWindowY( int y )
    {
        lblWindowYValue.setText( String.format( "%d", y ) );
    }
    
    void setWindowWidth( int width )
    {
        lblWindowWidthValue.setText( String.format( "%d", width ) );
    }
    
    void setWindowHeight( int height )
    {
        lblWindowHeightValue.setText( String.format( "%d", height ) );
    }
    
    @Override
    public void setVisible( boolean b )
    {
        if( b )
        {
          lblBehaviour.setText( Main.getInstance( ).getLanguageBundle( ).getString( "Behaviour" ) );
          lblShimejiX.setText( Main.getInstance( ).getLanguageBundle( ).getString( "ShimejiX" ) );
          lblShimejiY.setText( Main.getInstance( ).getLanguageBundle( ).getString( "ShimejiY" ) );
          lblWindowX.setText( Main.getInstance( ).getLanguageBundle( ).getString( "WindowX" ) );
          lblWindowY.setText( Main.getInstance( ).getLanguageBundle( ).getString( "WindowY" ) );
          lblWindowWidth.setText( Main.getInstance( ).getLanguageBundle( ).getString( "WindowWidth" ) );
          lblWindowHeight.setText( Main.getInstance( ).getLanguageBundle( ).getString( "WindowHeight" ) );
        }
        super.setVisible( b );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBehaviour;
    private javax.swing.JLabel lblBehaviourValue;
    private javax.swing.JLabel lblShimejiX;
    private javax.swing.JLabel lblShimejiXValue;
    private javax.swing.JLabel lblShimejiY;
    private javax.swing.JLabel lblShimejiYValue;
    private javax.swing.JLabel lblWindowHeight;
    private javax.swing.JLabel lblWindowHeightValue;
    private javax.swing.JLabel lblWindowWidth;
    private javax.swing.JLabel lblWindowWidthValue;
    private javax.swing.JLabel lblWindowX;
    private javax.swing.JLabel lblWindowXValue;
    private javax.swing.JLabel lblWindowY;
    private javax.swing.JLabel lblWindowYValue;
    // End of variables declaration//GEN-END:variables
}