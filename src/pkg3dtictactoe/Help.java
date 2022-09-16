//NAME: KRISH MODI
//DATE: January 28 2021
//DESCRIPTION: The help menu
package pkg3dtictactoe;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Help extends JFrame implements ActionListener {
    
    JLabel lblTitle;
    JLabel lblWinCondition;
    JLabel lblWin1;
    JLabel lblWin2;
    JLabel lblWin3;
    JLabel lblWin4;
    JLabel lblWin5;
    JLabel lblWin6;
    JLabel lblWin7;
    JButton btnBack;
    
    //Constructor
    public Help() {
        addComponents();
        this.setSize(1117, 382);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //Creates all of the images and labels. Made using GUI
    public void addComponents() {
        lblTitle = new JLabel();
        lblWinCondition = new JLabel();

        try {
            lblWin1 = new JLabel(new ImageIcon(ImageIO.read(new File("Win1.png")).getScaledInstance(151, 245, Image.SCALE_SMOOTH)));
            lblWin2 = new JLabel(new ImageIcon(ImageIO.read(new File("Win2.png")).getScaledInstance(151, 245, Image.SCALE_SMOOTH)));
            lblWin3 = new JLabel(new ImageIcon(ImageIO.read(new File("Win3.png")).getScaledInstance(151, 245, Image.SCALE_SMOOTH)));
            lblWin4 = new JLabel(new ImageIcon(ImageIO.read(new File("Win4.png")).getScaledInstance(151, 245, Image.SCALE_SMOOTH)));
            lblWin5 = new JLabel(new ImageIcon(ImageIO.read(new File("Win5.png")).getScaledInstance(151, 245, Image.SCALE_SMOOTH)));
            lblWin6 = new JLabel(new ImageIcon(ImageIO.read(new File("Win6.png")).getScaledInstance(151, 245, Image.SCALE_SMOOTH)));
            lblWin7 = new JLabel(new ImageIcon(ImageIO.read(new File("Win7.png")).getScaledInstance(151, 245, Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            lblTitle.setText("An error occured");
        }
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(200, 200, 200));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitle.setText("How to Play");

        lblWinCondition.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblWinCondition.setText("Win Conditions");

        btnBack.setText("Back");
        
        JPanel main = new JPanel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(main);
        main.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblWin1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblWin2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblWin3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblWin4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblWin5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblWin6, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblWin7, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(lblWinCondition)))
                        .addGap(463, 463, 463))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle)
                    .addComponent(btnBack))
                .addGap(18, 18, 18)
                .addComponent(lblWinCondition)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWin3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWin2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWin1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWin6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWin5, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWin7, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWin4, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
        
        btnBack.addActionListener(this);
        
        main.setVisible(true);
        this.add(main);
        this.setVisible(true);
    }
    
    //Goes back to main menu when back is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            Menu menu = new Menu();
            this.setVisible(false);
        }
    }
}
