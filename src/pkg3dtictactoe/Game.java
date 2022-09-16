//NAME: KRISH MODI
//DATE: January 28 2022
//DESCRIPTION: This class is the game Jframe
package pkg3dtictactoe;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JFrame implements ActionListener {
    
    Board board = new Board();  //This object stores the board that the game will be played on
    ReinforcedAI reAI;
    MinimaxAI miniAI;
    int opponent;               //0: Another user; 1: reAI; 2: miniAI;
    boolean initial_move;       //Stores true if user went first, false if it was computer
    boolean user_move;          //Will contain whether it is the user's turn to move
    ArrayList<Integer> chosen = new ArrayList<>();  //Stores all of the moves already done so that they are not selected again
    
    JButton[] btnSquares = new JButton[27];     //Creates list of buttons
    JButton btnReset = new JButton("Reset");
    JLabel lblTitle = new JLabel("Welcome to");
    JLabel lblTitle2 = new JLabel("3D Tic-Tac-Toe");
    JLabel lblTurn = new JLabel("It is X's turn");
    
    //Constructor for 2 players
    public Game() throws FileNotFoundException {
        super("3d Tic-Tac-Toe");
        this.setSize(600, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        opponent = 0;
        user_move = true;
        initial_move = true;
    }
    
    //Constructor for playing against reinforced AI
    public Game(ReinforcedAI ai, boolean first_move) throws FileNotFoundException {
        super("3d Tic-Tac-Toe");
        this.setSize(600, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reAI = ai;
        opponent = 1;
        user_move = first_move;
        initial_move = first_move;
    }
    
    //Constructor for playing against minimax AI
    public Game(MinimaxAI ai, boolean first_move) throws FileNotFoundException {
        super("3d Tic-Tac-Toe");
        this.setSize(600, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miniAI = ai;
        opponent = 2;
        user_move = first_move;
        initial_move = first_move;
    }
    
    
    
    //Creates all of the components
    public void addComponents() throws IOException{
        //Main panel uses null layout
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(null);
        
        //Adds board image to main panel
        BufferedImage image = ImageIO.read(new File("Board.png"));
        JLabel boardImage = new JLabel(new ImageIcon(image));
        boardImage.setBounds(0, 0, 368, 473);
        pnlMain.add(boardImage);
        
        //Calls add buttons method
        addButtons(pnlMain);
        
        //Creates the right panel (Grid layout)
        JPanel pnlRight = new JPanel();
        JLabel lblEmpty = new JLabel("");
        pnlRight.setLayout(new GridLayout(6, 1));
        pnlRight.setBounds(368, 0, 200, 473);
        
        lblTitle.setFont(new Font("Serif", Font.PLAIN, 25));
        lblTitle2.setFont(new Font("Serif", Font.PLAIN, 25));
        pnlRight.add(lblEmpty);
        pnlRight.add(lblEmpty);
        pnlRight.add(lblTitle);
        pnlRight.add(lblTitle2);
        
        lblTurn.setFont(new Font("Serif", Font.PLAIN, 18));
        pnlRight.add(lblEmpty);
        pnlRight.add(lblTurn);
        
        btnReset.setVisible(false);
        pnlRight.add(btnReset);
        
        pnlRight.setVisible(true);
        pnlMain.add(pnlRight);
        
        //Adds action listener
        btnReset.addActionListener(this);
        pnlMain.setVisible(true);
        //Adds to JFrame
        this.add(pnlMain);
        this.setVisible(true);
    }
    
    //Adds all of the buttons in their correct location
    public void addButtons(JPanel main) {  
        for (int btnNum = 0; btnNum < 27; btnNum++) {
            //Configures each button
            btnSquares[btnNum] = new JButton();
            btnSquares[btnNum].addActionListener(this);
            btnSquares[btnNum].setMargin(new Insets(0,0,0,0));
            main.add(btnSquares[btnNum]);
        }
        
        //Positions each button where it needs to be
        btnSquares[0].setBounds(110, 397, 20, 20);
        btnSquares[1].setBounds(121, 352, 20, 20);
        btnSquares[2].setBounds(128, 313, 20, 20);
        btnSquares[3].setBounds(170, 397, 20, 20);
        btnSquares[4].setBounds(170, 352, 20, 20);
        btnSquares[5].setBounds(170, 313, 20, 20);
        btnSquares[6].setBounds(231, 397, 20, 20);
        btnSquares[7].setBounds(219, 352, 20, 20);
        btnSquares[8].setBounds(211, 313, 20, 20);
        btnSquares[9].setBounds(90, 275, 20, 20);
        btnSquares[10].setBounds(103, 220, 20, 20);
        btnSquares[11].setBounds(116, 175, 20, 20);
        btnSquares[12].setBounds(170, 275, 20, 20);
        btnSquares[13].setBounds(170, 220, 20, 20);
        btnSquares[14].setBounds(170, 175, 20, 20);
        btnSquares[15].setBounds(248, 275, 20, 20);
        btnSquares[16].setBounds(234, 220, 20, 20);
        btnSquares[17].setBounds(225, 175, 20, 20);
        btnSquares[18].setBounds(78, 138, 20, 20);
        btnSquares[19].setBounds(93, 75, 20, 20);
        btnSquares[20].setBounds(108, 20, 20, 20);
        btnSquares[21].setBounds(170, 138, 20, 20);
        btnSquares[22].setBounds(170, 75, 20, 20);
        btnSquares[23].setBounds(170, 20, 20, 20);
        btnSquares[24].setBounds(259, 138, 20, 20);
        btnSquares[25].setBounds(242, 75, 20, 20);
        btnSquares[26].setBounds(234, 20, 20, 20);
    }
    
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == btnReset) {
            boolean finished_reset = false;
            //Resets to initial conditions
            lblTurn.setText("It's X's turn");
            board.reset();
            user_move = initial_move;
            btnReset.setVisible(false);
            for (int pos = 0; pos < 27; pos++) {
                btnSquares[pos].setText("");
                btnSquares[pos].setEnabled(true);
            }
            chosen.clear(); //Clears chosen moves
            play(); //Calls play method
        }
        for (int btnNum = 0; btnNum < 27; btnNum++) {
            //If it the user's move and the button has not already been chosen
            if (e.getSource() == btnSquares[btnNum] && user_move && !chosen.contains(btnNum)) {
                //Sets text of chosen position to players symbol
                btnSquares[btnNum].setText(board.get_player());
                //Updates board object
                board.make_move(btnNum);
                //Changes the turn
                user_move = false;
                chosen.add(btnNum); //Adds chosen position to chosen moves
                checkTerminal();    //Checks if the game is over. If so, show reset button
                play();
            }
        }
    }
    
    //Method for AI move if playing against AI
    public void play() {
        //If game is not over
        if (!board.terminal()) {
            //If it is the AI's turn and the user is playing against AI
            if (!user_move && opponent > 0) {
                //Save board. This is to ensure that the pass by ref does not change the original board
                Board old_board = new Board(board.state_to_string());
                //Ask the corrosponding AI for the move given a state
                int ai_move;
                if (opponent == 1) {
                    ai_move = reAI.ai_move(board);
                } else {
                    ai_move = miniAI.ai_move(board);
                }
                board = new Board(old_board.state_to_string());
                
                //Updates components with the chosen AI move]
                btnSquares[ai_move].setText(board.get_player());
                //Adds chosen move to chosen
                chosen.add(ai_move);
                //Makes move on board
                board.make_move(ai_move);
            }
            //Changes the turn
            user_move = true;
            lblTurn.setText("It is " + board.get_player() + "'s turn");
            //Checks if the game is over
            checkTerminal();
        }
    }
    
    //Method to check if board is over. If so, show reset button and declare winner.
    public void checkTerminal() {
        //If game is over
        if (board.terminal()) {
            //Shows who won and the reset button
            lblTurn.setText("The winner is: " + board.get_winner());
            btnReset.setVisible(true);
            for (int pos = 0; pos < 27; pos++) {
                btnSquares[pos].setEnabled(false);
            }
        }
    }
}