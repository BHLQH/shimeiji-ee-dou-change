package com.group_finity.mascot;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.group_finity.mascot.behavior.Behavior;
import com.group_finity.mascot.environment.MascotEnvironment;
import com.group_finity.mascot.exception.CantBeAliveException;
import com.group_finity.mascot.image.MascotImage;
import com.group_finity.mascot.image.TranslucentWindow;
import com.group_finity.mascot.sound.Sounds;

/**
 * Mascot object.
 *
 * The mascot represents the long-term, complex behavior and (@link Behavior),
 * Represents a short-term movements in the monotonous work with (@link Action).
 *
 * The mascot they have an internal timer, at a constant interval to call (@link Action).
 * (@link Action) is (@link #animate (Point, MascotImage, boolean)) method or by calling
 * To animate the mascot.
 *
 * (@link Action) or exits, the other at a certain time is called (@link Behavior), the next move to (@link Action).
 *
 * Original Author: Yuki Yamada of Group Finity (http://www.group-finity.com/Shimeji/)
 * Currently developed by Shimeji-ee Group.
 */
public class Mascot {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(Mascot.class.getName());

	private static AtomicInteger lastId = new AtomicInteger();

	private final int id;//每个桌宠都有一个唯一的标识符。
	
	private String imageSet = "";//指定桌宠使用的图像集

	/**
	 * A window that displays the mascot.
	 * 展示桌宠的窗口。
	 */
	private final TranslucentWindow window = NativeFactory.getInstance().newTransparentWindow();

	/**
	 * Managers are managing the mascot.
	 * 管理者正在管理的桌宠。
	 */
	private Manager manager = null;

	/**
	 * Mascot ground coordinates.
	 * Or feet, for example, when part of the hand is hanging.
	 * 吉祥物地面坐标 或脚，例如，当手的一部分悬空时。
	 */
	private Point anchor = new Point(0, 0);//桌宠的位置坐标。

	/**
	 * Image to display.
	 * 当前显示的图像
	 */
	private MascotImage image = null;

	/**
	 * Whether looking right or left.
	 * The original image is treated as left, true means picture must be inverted.
	 * 表示桌宠是否朝右看
	 * 	 * 原始图像被视为左图，true 表示图像必须反转。
	 */
	private boolean lookRight = false;

	/**
	 * Object representing the long-term behavior.
	 * 表示桌宠的长期行为。
	 */
	private Behavior behavior = null;

	/**
	 * Increases with each tick of the timer.
	 * 计时器的时间。
	 */
	private int time = 0;

	/**
	 * Whether the animation is running.
	 * 表示动画是否正在运行。
	 */
	private boolean animating = true;

	private MascotEnvironment environment = new MascotEnvironment(this);//这行代码创建了一个名为environment的私有属性（成员变量）
	//this关键字表示当前对象（即Mascot类的实例）。因此，这行代码在创建一个MascotEnvironment对象，并将当前Mascot实例作为参数传递给它。
        private String sound = null;//桌宠的声音。
    
    protected DebugWindow debugWindow = null;//创建调试窗口

	public Mascot( final String imageSet ) //一个构造方法（constructor），用于创建Mascot类的实例,构造方法的名称与类名相同
	{//final String imageSet表示传入的图像集名称。
		this.id = lastId.incrementAndGet();//为当前实例设置唯一的id。
		this.imageSet = imageSet;//设置图像集名称。

		log.log(Level.INFO, "Created a mascot ({0})", this);//记录日志，表示已创建一个桌宠

		// Always show on top
		getWindow().asJWindow().setAlwaysOnTop(true);//确保桌宠窗口始终在顶部显示。

		// Register the mouse handler注册鼠标事件处理程序，以便在鼠标按下或释放时执行相应的操作。
		getWindow().asJWindow().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				Mascot.this.mousePressed(e);
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				Mascot.this.mouseReleased(e);
			}
		});

	}

	@Override
	public String toString() {
		return "mascot" + this.id;
	}

	private void mousePressed(final MouseEvent event) {

		if (event.isPopupTrigger()) {
			showPopupLater(event);
		} else {
			// Switch to drag the animation when the mouse is down
			if (getBehavior() != null) {
				try {
					getBehavior().mousePressed(event);
				} catch (final CantBeAliveException e) {
					log.log(Level.SEVERE, "Fatal Error", e);
	                                Main.showError( Main.getInstance( ).getLanguageBundle( ).getString( "SevereShimejiErrorErrorMessage" ) + "\n" + e.getMessage( ) + "\n" + Main.getInstance( ).getLanguageBundle( ).getString( "SeeLogForDetails" ) );
					dispose();
				}
			}
		}

	}

	private void mouseReleased(final MouseEvent event) {

		if (event.isPopupTrigger()) {
			showPopupLater(event);
		} else {
			if (getBehavior() != null) {
				try {
					getBehavior().mouseReleased(event);
				} catch (final CantBeAliveException e) {
					log.log(Level.SEVERE, "Fatal Error", e);
					Main.showError( Main.getInstance( ).getLanguageBundle( ).getString( "SevereShimejiErrorErrorMessage" ) + "\n" + e.getMessage( ) + "\n" + Main.getInstance( ).getLanguageBundle( ).getString( "SeeLogForDetails" ) );
					dispose();
				}
			}
		}

	}
	
	private void showPopupLater(final MouseEvent event) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				showPopup(event.getX(), event.getY());
			}
		});
	}

	private void showPopup(final int x, final int y) {
		final JPopupMenu popup = new JPopupMenu();

		popup.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuCanceled(final PopupMenuEvent e) {
			}

			@Override
			public void popupMenuWillBecomeInvisible(final PopupMenuEvent e) {
				setAnimating(true);
			}

			@Override
			public void popupMenuWillBecomeVisible(final PopupMenuEvent e) {
				setAnimating(false);
			}
		});

		// "Another One!" menu item
		final JMenuItem increaseMenu = new JMenuItem( Main.getInstance( ).getLanguageBundle( ).getString( "CallAnother" ) );
		increaseMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				Main.getInstance().createMascot(imageSet);
			}
		});

		// "Bye Bye!" menu item
		final JMenuItem disposeMenu = new JMenuItem( Main.getInstance( ).getLanguageBundle( ).getString( "Dismiss" ) );
		disposeMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				dispose();
			}
		});
		
		// "Follow Mouse!" Menu item
		final JMenuItem gatherMenu = new JMenuItem( Main.getInstance( ).getLanguageBundle( ).getString( "FollowCursor" ) );
		gatherMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				getManager().setBehaviorAll(Main.getInstance().getConfiguration(imageSet), Main.BEHAVIOR_GATHER, imageSet);
			}
		});

		// "Reduce to One!" menu item
		final JMenuItem oneMenu = new JMenuItem( Main.getInstance( ).getLanguageBundle( ).getString( "DismissOthers" ) );
		oneMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				getManager().remainOne(imageSet);
			}
		});

		// "Restore IE!" menu item
		final JMenuItem restoreMenu = new JMenuItem( Main.getInstance( ).getLanguageBundle( ).getString( "RestoreWindows" ) );
		restoreMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				NativeFactory.getInstance().getEnvironment().restoreIE();
			}
		});

		// Debug menu item
		final JMenuItem debugMenu = new JMenuItem( Main.getInstance( ).getLanguageBundle( ).getString( "RevealStatistics" ) );
		debugMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
                if( debugWindow == null )
                {
                    debugWindow = new DebugWindow( );
                }
				debugWindow.setVisible( true );
			}
		});
        
		// "Bye Everyone!" menu item
		final JMenuItem closeMenu = new JMenuItem( Main.getInstance( ).getLanguageBundle( ).getString( "DismissAll" ) );
		closeMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				Main.getInstance().exit();
			}
		});
	
		// Add the Behaviors submenu.  Currently slightly buggy, sometimes the menu ghosts.
		com.group_finity.mascot.menu.JLongMenu submenu = new com.group_finity.mascot.menu.JLongMenu( Main.getInstance( ).getLanguageBundle( ).getString( "SetBehaviour" ),30);
		// The MenuScroller would look better than the JLongMenu, but the initial positioning is not working correctly.
		//MenuScroller.setScrollerFor(submenu, 30, 125);
		submenu.setAutoscrolls(true);		
		JMenuItem item;
		com.group_finity.mascot.config.Configuration config = Main.getInstance().getConfiguration(getImageSet());
        Behavior behaviour = null;
		for( String behaviorName : config.getBehaviorNames() )
        {   
            final String command = behaviorName;
            try
            {
                behaviour = Main.getInstance().getConfiguration(getImageSet()).buildBehavior(command);
                if( !behaviour.isHidden( ) )
                {
                    item = new JMenuItem( Main.getInstance( ).getLanguageBundle( ).containsKey( behaviorName ) ? 
                                          Main.getInstance( ).getLanguageBundle( ).getString( behaviorName ) : 
                                          behaviorName.replaceAll( "([a-z])(IE)?([A-Z])", "$1 $2 $3" ).replaceAll( "  ", " " ) );
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {
                            try {									
                                setBehavior(Main.getInstance().getConfiguration(getImageSet()).buildBehavior(command));
                            } catch( Exception err ) {
                                log.log(Level.SEVERE, "Error ({0})", this);
                                Main.showError( Main.getInstance( ).getLanguageBundle( ).getString( "CouldNotSetBehaviourErrorMessage" ) + "\n" + err.getMessage( ) + "\n" + Main.getInstance( ).getLanguageBundle( ).getString( "SeeLogForDetails" ) );
                            }					
                        }
                    });
                    submenu.add( item );
                }
            }
            catch( Exception err )
            {
                // just skip if something goes wrong
            }
        }
		
		popup.add(increaseMenu);
		popup.add(new JSeparator());		
		popup.add(gatherMenu);
		popup.add(restoreMenu);
        popup.add(debugMenu);
		popup.add(new JSeparator());
		popup.add(submenu);
		popup.add(new JSeparator());
		popup.add(disposeMenu);	
		popup.add(oneMenu);
		popup.add(closeMenu);

		getWindow().asJWindow().requestFocus();
                
                // lightweight popups expect the shimeji window to draw them if they fall inside the shimeji window boundary
                // as the shimeji window can't support this we need to set them to heavyweight
                popup.setLightWeightPopupEnabled( false );
                popup.show(getWindow().asJWindow(), x, y);
	}

	void tick() //处理计时器的逻辑
	{
		if (isAnimating()) {
			if (getBehavior() != null) {

				try {
					getBehavior().next();
				} catch (final CantBeAliveException e) {
					log.log(Level.SEVERE, "Fatal Error.", e);
					Main.showError( Main.getInstance( ).getLanguageBundle( ).getString( "CouldNotGetNextBehaviourErrorMessage" ) + "\n" + e.getMessage( ) + "\n" + Main.getInstance( ).getLanguageBundle( ).getString( "SeeLogForDetails" ) );
					dispose();
				}

				setTime(getTime() + 1);
			}
            if( debugWindow != null )
            {
                debugWindow.setBehaviour( behavior.toString( ).substring( 9, behavior.toString( ).length( ) - 1 ).replaceAll( "([a-z])(IE)?([A-Z])", "$1 $2 $3" ).replaceAll( "  ", " " ) );
                debugWindow.setShimejiX( anchor.x );
                debugWindow.setShimejiY( anchor.y );
                debugWindow.setWindowX( environment.getActiveIE( ).getLeft( ) );
                debugWindow.setWindowY( environment.getActiveIE( ).getTop( ) );
                debugWindow.setWindowWidth( environment.getActiveIE( ).getWidth( ) );
                debugWindow.setWindowHeight( environment.getActiveIE( ).getHeight( ) );
            }
		}
	}

    public void apply( )//应用桌宠的行为。
    {
        if (isAnimating())
        {
            // Make sure there's an image
            if (getImage() != null)
            {
                // Set the window region
                getWindow().asJWindow().setBounds(getBounds());

                // Set Images
                getWindow().setImage(getImage().getImage());

                // Display
                if (!getWindow().asJWindow().isVisible())
                {
                    getWindow().asJWindow().setVisible(true);
                }

                // Redraw
                getWindow().updateImage();
            }
            else
            {
                if( getWindow().asJWindow().isVisible() )
                {
                    getWindow().asJWindow().setVisible(false);
                }
            }
            
            // play sound if requested
            if( sound != null && !Sounds.getSound( sound ).isRunning( ) && !Sounds.isMuted( ) )
            {
                Sounds.getSound( sound ).setMicrosecondPosition( 0 );
                Sounds.getSound( sound ).start( );
            }
        }
    }

	public void dispose() {
		log.log(Level.INFO, "destroy mascot ({0})", this);
        
        if( debugWindow != null )
        {
            debugWindow.setVisible( false );
            debugWindow = null;
        }

		getWindow().asJWindow().dispose();
		if (getManager() != null) {
			getManager().remove(Mascot.this);
		}
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(final Manager manager) {
		this.manager = manager;
	}

	public Point getAnchor() {
		return this.anchor;
	}

	public void setAnchor(Point anchor) {
		this.anchor = anchor;
	}

	public MascotImage getImage() {
		return this.image;
	}

	public void setImage(final MascotImage image) {
		this.image = image;
	}

	public boolean isLookRight() {
		return this.lookRight;
	}

	public void setLookRight(final boolean lookRight) {
		this.lookRight = lookRight;
	}

	public Rectangle getBounds() {
		// Central area of the window find the image coordinates and ground coordinates. The centre has already been adjusted for scaling
		final int top = getAnchor().y - getImage().getCenter().y;
		final int left = getAnchor().x - getImage().getCenter().x;
                
                final int scaling = Integer.parseInt( Main.getInstance( ).getProperties( ).getProperty( "Scaling", "1" ) );

		final Rectangle result = new Rectangle(left, top, getImage().getSize().width * scaling, getImage().getSize().height * scaling );

		return result;
	}

	public int getTime() {
		return this.time;
	}

	private void setTime(final int time) {
		this.time = time;
	}

	public Behavior getBehavior() {
		return this.behavior;
	}

	public void setBehavior(final Behavior behavior) throws CantBeAliveException {
		this.behavior = behavior;
		this.behavior.init(this);
	}

	public int getTotalCount() {
		return getManager().getCount();
	}

	private boolean isAnimating() {
		return this.animating;
	}

	private void setAnimating(final boolean animating) {
		this.animating = animating;
	}

	private TranslucentWindow getWindow() {
		return this.window;
	}

	public MascotEnvironment getEnvironment() {
		return environment;
	}
        
        public void setImageSet( final String set )
        {
            imageSet = set;
        }
	
	public String getImageSet() {
		return imageSet;
	}

	public String getSound( )
        {
            return sound;
	}

	public void setSound( final String name )
        {
            sound = name;
	}
}
