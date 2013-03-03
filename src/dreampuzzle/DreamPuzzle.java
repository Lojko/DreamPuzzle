package dreampuzzle;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Main Game class which constructs the GUI and the controls of the game.
 * @author Michael
 */
public class DreamPuzzle extends javax.swing.JFrame implements ActionListener, MouseListener
{
    //Game Variables
    private Totem m_StartTotem,m_SecondTotem,m_ThirdTotem,m_FourthTotem,m_FifthTotem,m_SixthTotem,m_EndTotem;
    private Icon m_EnergyBeamIcon;
    private JButton[] m_ButtonArray;
    private Totem[] m_TotemArray;
    private PuzzleEngine engine;

    // <editor-fold defaultstate="collapsed" desc="Netbeans Layout Variables">
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn17;
    private javax.swing.JButton btn18;
    private javax.swing.JButton btn19;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btn23;
    private javax.swing.JButton btn24;
    private javax.swing.JButton btn25;
    private javax.swing.JButton btn26;
    private javax.swing.JButton btn27;
    private javax.swing.JButton btn28;
    private javax.swing.JButton btn29;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn30;
    private javax.swing.JButton btn31;
    private javax.swing.JButton btn32;
    private javax.swing.JButton btn33;
    private javax.swing.JButton btn34;
    private javax.swing.JButton btn35;
    private javax.swing.JButton btn36;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBeamEntrance;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlGame;
    private javax.swing.JTextArea txtFeedback;
    // </editor-fold>

    /** Creates new form Game */
    public DreamPuzzle()
    {
        //Initialize arrays for buttons and totems
        m_ButtonArray = new JButton[36];
        m_TotemArray = new Totem[6];

        initComponents();
        constructButtonArray();

        //Puzzle One
        /*m_StartTotem = new Totem(7,5,new ImageIcon(getClass().getResource("Images/start.png")),btn1);
        m_EndTotem = new Totem(1,3,new ImageIcon(getClass().getResource("Images/end.png")),btn36);
        m_SecondTotem = new Totem(1,2, new ImageIcon(getClass().getResource("Images/second.png")));
        m_ThirdTotem = new Totem(6,7,new ImageIcon(getClass().getResource("Images/third.png")));
        m_FourthTotem = new Totem(3,6,new ImageIcon(getClass().getResource("Images/fourth.png")));
        m_FifthTotem = new Totem(2,3,new ImageIcon(getClass().getResource("Images/fifth.png")));
        m_SixthTotem = new Totem(7,5,new ImageIcon(getClass().getResource("Images/sixth.png")));*/

        //Puzzle Two
        m_StartTotem = new Totem(7,2,new ImageIcon(getClass().getResource("Images/start2.png")),btn31);
        m_EndTotem = new Totem(7,3,new ImageIcon(getClass().getResource("Images/end2.png")),btn36);
        m_SecondTotem = new Totem(6,7, new ImageIcon(getClass().getResource("Images/second2.png")));
        m_ThirdTotem = new Totem(3,2,new ImageIcon(getClass().getResource("Images/third2.png")));
        m_FourthTotem = new Totem(6,4,new ImageIcon(getClass().getResource("Images/fourth2.png")));
        m_FifthTotem = new Totem(8,6,new ImageIcon(getClass().getResource("Images/fifth2.png")));
        m_SixthTotem = new Totem(2,3,new ImageIcon(getClass().getResource("Images/sixth2.png")));
        m_TotemArray[0] = m_SecondTotem; m_TotemArray[1] = m_ThirdTotem; m_TotemArray[2] = m_FourthTotem;
        m_TotemArray[3] = m_FifthTotem; m_TotemArray[4] = m_SixthTotem; m_TotemArray[5] = m_EndTotem;

        m_EnergyBeamIcon = new ImageIcon(getClass().getResource("Images/beam.png"));
        lblBeamEntrance.setIcon(m_EnergyBeamIcon);

        //
        addListeners(m_StartTotem,m_EndTotem);

        engine = new PuzzleEngine(m_ButtonArray,m_TotemArray, pnlGame.getWidth(), pnlGame.getHeight(), 6, 6, m_StartTotem, m_EndTotem);
        engine.setGrid();
    }

    // <editor-fold defaultstate="collapsed" desc="Button Array Construction">
    private void constructButtonArray()
    {
        m_ButtonArray[0] = btn1; m_ButtonArray[1] = btn2; m_ButtonArray[2] = btn3;
        m_ButtonArray[3] = btn4; m_ButtonArray[4] = btn5; m_ButtonArray[5] = btn6;
        m_ButtonArray[6] = btn7; m_ButtonArray[7] = btn8; m_ButtonArray[8] = btn9;
        m_ButtonArray[9] = btn10; m_ButtonArray[10] = btn11; m_ButtonArray[11] = btn12;
        m_ButtonArray[12] = btn13; m_ButtonArray[13] = btn14; m_ButtonArray[14] = btn15;
        m_ButtonArray[15] = btn16; m_ButtonArray[16] = btn17; m_ButtonArray[17] = btn18;
        m_ButtonArray[18] = btn19; m_ButtonArray[19] = btn20; m_ButtonArray[20] = btn21;
        m_ButtonArray[21] = btn22; m_ButtonArray[22] = btn23; m_ButtonArray[23] = btn24;
        m_ButtonArray[24] = btn25; m_ButtonArray[25] = btn26; m_ButtonArray[26] = btn27;
        m_ButtonArray[27] = btn28; m_ButtonArray[28] = btn29; m_ButtonArray[29] = btn30;
        m_ButtonArray[30] = btn31; m_ButtonArray[31] = btn32; m_ButtonArray[32] = btn33;
        m_ButtonArray[33] = btn34; m_ButtonArray[34] = btn35; m_ButtonArray[35] = btn36;
    }
        // </editor-fold>

    //Function which requires the start and end totems to set all of the listeners on the correct totem buttons
    private void addListeners(Totem startTotem, Totem endTotem)
    {
        //Add action and mouse listeners to all buttons apart from the start and end totems
        for(int i = 0; i < m_ButtonArray.length; i++)
        {
            if(m_ButtonArray[i] != startTotem.getTotemButton() && m_ButtonArray[i] != endTotem.getTotemButton())
            {
                m_ButtonArray[i].addActionListener(this);
                m_ButtonArray[i].addMouseListener(this);
            }
        }
    }

    //Overwritten paintComponent function which draws the beam
    protected void paintComponent(Graphics g)
    {
        ArrayList<Line2D> m_BeamLineList = engine.drawBeam();
        for( Line2D line : m_BeamLineList )
        {
            g.drawLine((int)line.getX1(), (int)line.getY1(), (int)line.getX2(), (int)line.getY2());
        }
    }

    //mouseEntered Event, used with the mouse listener, each time the player hovers over a button the beam is redrawn.
    public void mouseEntered(java.awt.event.MouseEvent evt)
    {
        paintComponent(pnlGame.getGraphics());
    }
    
    //mouseExit Event, used with the mouse listener, each time the player exitsr a button the beam is redrawn
    public void mouseExited(java.awt.event.MouseEvent evt)
    {
        paintComponent(pnlGame.getGraphics());
    }

    // <editor-fold defaultstate="collapsed" desc="Unused Mouse Events">
    public void mousePressed(java.awt.event.MouseEvent evt) {}
    public void mouseReleased(java.awt.event.MouseEvent evt) {}
    public void mouseClicked(java.awt.event.MouseEvent evt) {}
    // </editor-fold>

    //Button click event which alters the button, moves totems and prevents movement in specific scenarios
    public void actionPerformed(ActionEvent e)
    {
        repaint();
        engine.actionPerformed(txtFeedback, e);
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DreamPuzzle().setVisible(true);
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Netbeans Layout Generated Code">
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        pnlGame = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        btn18 = new javax.swing.JButton();
        btn19 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn26 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn28 = new javax.swing.JButton();
        btn29 = new javax.swing.JButton();
        btn30 = new javax.swing.JButton();
        btn31 = new javax.swing.JButton();
        btn32 = new javax.swing.JButton();
        btn33 = new javax.swing.JButton();
        btn34 = new javax.swing.JButton();
        btn35 = new javax.swing.JButton();
        btn36 = new javax.swing.JButton();
        lblBeamEntrance = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtFeedback = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 70)); // NOI18N
        lblTitle.setText("Dream Puzzle");

        pnlGame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlGame.setOpaque(true);
        pnlGame.setLayout(null);

        btn1.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn1);
        btn1.setBounds(4, 6, 80, 80);
        btn1.getAccessibleContext().setAccessibleName("btn1");

        btn2.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn2);
        btn2.setBounds(89, 6, 80, 80);

        btn3.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn3);
        btn3.setBounds(174, 6, 80, 80);

        btn4.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn4);
        btn4.setBounds(259, 6, 80, 80);

        btn5.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn5);
        btn5.setBounds(344, 6, 80, 80);

        btn6.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn6);
        btn6.setBounds(429, 6, 80, 80);

        btn7.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn7);
        btn7.setBounds(4, 91, 80, 80);

        btn8.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn8);
        btn8.setBounds(89, 91, 80, 80);

        btn9.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn9);
        btn9.setBounds(174, 91, 80, 80);

        btn10.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn10);
        btn10.setBounds(259, 91, 80, 80);

        btn11.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn11);
        btn11.setBounds(344, 91, 80, 80);

        btn12.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn12);
        btn12.setBounds(429, 91, 80, 80);

        btn13.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn13);
        btn13.setBounds(4, 176, 80, 80);

        btn14.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn14);
        btn14.setBounds(89, 176, 80, 80);

        btn15.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn15);
        btn15.setBounds(174, 176, 80, 80);

        btn16.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn16);
        btn16.setBounds(259, 176, 80, 80);

        btn17.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn17);
        btn17.setBounds(344, 176, 80, 80);

        btn18.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn18);
        btn18.setBounds(429, 176, 80, 80);

        btn19.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn19);
        btn19.setBounds(4, 261, 80, 80);

        btn20.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn20);
        btn20.setBounds(89, 261, 80, 80);

        btn21.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn21);
        btn21.setBounds(174, 261, 80, 80);

        btn22.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn22);
        btn22.setBounds(259, 261, 80, 80);

        btn23.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn23);
        btn23.setBounds(344, 261, 80, 80);

        btn24.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn24);
        btn24.setBounds(429, 261, 80, 80);

        btn25.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn25);
        btn25.setBounds(4, 346, 80, 80);

        btn26.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn26);
        btn26.setBounds(89, 346, 80, 80);

        btn27.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn27);
        btn27.setBounds(174, 346, 80, 80);

        btn28.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn28);
        btn28.setBounds(259, 346, 80, 80);

        btn29.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn29);
        btn29.setBounds(344, 346, 80, 80);

        btn30.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn30);
        btn30.setBounds(429, 346, 80, 80);

        btn31.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn31);
        btn31.setBounds(4, 431, 80, 80);

        btn32.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn32);
        btn32.setBounds(89, 431, 80, 80);

        btn33.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn33);
        btn33.setBounds(174, 431, 80, 80);

        btn34.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn34);
        btn34.setBounds(259, 431, 80, 80);

        btn35.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn35);
        btn35.setBounds(344, 431, 80, 80);

        btn36.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlGame.add(btn36);
        btn36.setBounds(429, 431, 80, 80);

        txtFeedback.setColumns(20);
        txtFeedback.setRows(5);
        jScrollPane1.setViewportView(txtFeedback);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBeamEntrance, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pnlGame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(lblTitle)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addComponent(lblBeamEntrance, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        pack();

    }// </editor-fold>
}
