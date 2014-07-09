/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedex.clases;
//import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.LayoutStyle.*;
/*
 *
 *
 * @author Pablo
 */
public class PokeDexGuiTest extends JFrame implements ActionListener
{
    public PokeDex dex;
    private JComponent titlePane, contentPane, bottomPane;
    private JLabel titleLabel, resizeLabel;
    private JButton closeButton;
    private Window w = this;
    private ActionListener closeListener;

    public PokeDexGuiTest()
    {
        super("PokeDex");
        dex = new PokeDex();
        dex.cargar();
    }
    
    public PokeDexGuiTest(String nombre)
    {
        super(nombre);
        dex = new PokeDex();
        dex.cargar();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
	setBackground(new Color(0,0,0,0));
	setContentPane(createContentPane());
	initComponents();
	setSize(200, 300);
	setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void initComponents()
    {
        titleLabel = new JLabel(getTitle());
	titleLabel.setForeground(Color.WHITE);
	closeButton = new JButton();
	closeButton.setIcon(new ImageIcon(PokeDexGuiTest.class.getResource("close.png")));
	closeButton.setRolloverIcon(new ImageIcon(PokeDexGuiTest.class.getResource("close_hover.png")));
	closeButton.setPressedIcon(new ImageIcon(PokeDexGuiTest.class.getResource("close_pressed.png")));
	closeButton.setFocusable(false);
	closeButton.setFocusPainted(false);
	closeButton.setBorderPainted(false);
	closeButton.setContentAreaFilled(false);
	//titlePane = createTitlePane();
	resizeLabel = new JLabel(new ImageIcon(PokeDexGuiTest.class.getResource(
			"resize_corner_dark.png")));
	//bottomPane = createBottomPane();

	setLayout(new BorderLayout());
	//add(titlePane, BorderLayout.NORTH);
	add(contentPane, BorderLayout.CENTER);
	//add(bottomPane, BorderLayout.SOUTH);
        
    }
    
    
    private JComponent createContentPane()
    {
	return new JComponent()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
		Graphics2D g2 = (Graphics2D)g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16);
                Composite old = g2.getComposite();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER).derive(0.75f));
                g2.setColor(Color.BLACK);
                g2.fill(shape);
                g2.setComposite(old);
                g2.dispose();
            }
	};
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }

    
}
