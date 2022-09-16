//NAME: KRISH MODI
//DATE: January 28 2021
//DESCRIPTION: This stores all of the information for a given state
package pkg3dtictactoe;

import java.util.ArrayList;

public class Board {
    //Coordinate system is [z][x][y]
    //Continous system is [x] where x is between 0 - 26
    String N = "N";
    String X = "X";
    String O = "O";
    //A board can be represented as a 3D array or a string ("NNNNNNNNNNNNNNNNNNNNNNNNNNN" = empty board)
    String[][][] board;
    
    //Constructor for an empty board
    public Board() {
        board = new String[][][] {
            {{N, N, N}, {N, N, N}, {N, N, N}},
            {{N, N, N}, {N, N, N}, {N, N, N}},
            {{N, N, N}, {N, N, N}, {N, N, N}}
        };
    }
    
    //Constructor to make a board equal to a given 3D String array
    public Board(String[][][] new_board) {
        board = new_board;
    }
    
    //Constructor to make a board equal to a given string representing a 3D array
    public Board(String new_board) {
        board = new String[][][] {
                {{N, N, N}, {N, N, N}, {N, N, N}},
                {{N, N, N}, {N, N, N}, {N, N, N}},
                {{N, N, N}, {N, N, N}, {N, N, N}}
            };
        
        for (int string_pos = 0; string_pos < new_board.length(); string_pos++) {
            int[] coordinates = convert_to_coordinates(string_pos);
            
            board[coordinates[0]][coordinates[1]][coordinates[2]] = Character.toString(new_board.charAt(string_pos));
        }
    }
    
    
    
    //Returns who's turn it is given the board
    public String get_player() {
        //Stores amount of x's and o's on board
        int xCount = 0;
        int oCount = 0;
        
        for (int z = 0; z < 3; z++) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    //for every element on the board
                    //if element is x, increase xCount, else if element is o, increase oCount
                    if (board[z][x][y].equals(X)) {
                        xCount += 1;
                    } else if (board[z][x][y].equals(O)) {
                        oCount += 1;
                    }
                }
            }
        }
        //if there are more x's than o's, it's o's turn
        if (oCount < xCount) {
            return O;
        }
        //if there are the same amount of x's and o's, it's x's turn
        return X;
    }
    
    //Makes move on the board given continous coordinates
    public void make_move(int continous_coordinates) {
        int[] coordinates = convert_to_coordinates(continous_coordinates);
        int z = coordinates[0];
        int x = coordinates[1];
        int y = coordinates[2];
        board[z][x][y] = get_player();
    }
    
    //Returns the winner of a given board. If none, returns "N"
    public String get_winner() {
        String[] players = {X, O};
        //Different variables for different win conditions
        boolean won = true;
        boolean won2 = true;
        boolean won3 = true;
        boolean won4 = true;
        boolean won5 = true;
        boolean won6  = true;
        boolean won7  = true;
        boolean won8  = true;
        boolean won9  = true;
        boolean won10 = true;
        boolean won11 = true;
        boolean won12 = true;
        boolean won13 = true;
        
        //1st for loop loops through x and o to see if either won
        for (int p = 0; p < 2; p ++) {
            String symbol = players[p];
            won10 = true;
            won11 = true;
            for (int z = 0; z < 3; z++) {
                won4 = true;
                won5 = true;
                won6 = true;
                won7 = true;
                won8 = true;
                won9 = true;
                for (int x = 0; x < 3; x++) {
                    won = true;
                    won2 = true;
                    won3 = true;
                    for (int y = 0; y < 3; y++) {
                        //Flat verticle win ie ([0,0,0], [0,0,1], [0,0,2])
                        if (!board[z][x][y].equals(symbol)) {
                            won = false;
                        }
                        //Flat horizontal win ie ([0,0,0], [0,1,0], [0,2,0])
                        if (!board[z][y][x].equals(symbol)) {
                            won2 = false;
                        }
                        //Verticle win ie ([0,0,0], [1,0,0], [2,0,0]))
                        if (!board[y][x][z].equals(symbol)) {
                            won3 = false;
                        }
                    }
                    //If either conditions are true, return that symbol
                    if (won || won2 || won3) {
                        return symbol;
                    }
                    
                    //Diagnols on the x,y planes
                    if (!board[z][x][x].equals(symbol)) {
                        won4 = false;
                    }
                    if (!board[z][x][Math.abs(x - 2)].equals(symbol)) {
                        won5 = false;
                    }
                    //Diagnols on the x,z planes
                    if (!board[x][x][z].equals(symbol)) {
                        won6 = false;
                    }
                    if (!board[Math.abs(x - 2)][x][z].equals(symbol)) {
                        won7 = false;
                    }
                    //Diagnols on the y,z planes
                    if (!board[x][z][x].equals(symbol)) {
                        won8 = false;
                    }
                    if (!board[x][z][Math.abs(x - 2)].equals(symbol)) {
                        won9 = false;
                    }
                }
                //If any diagnol win conditions are met, return winner
                if (won4 || won5 || won6 || won7 || won8 || won9) {
                    return symbol;
                }
                //Checks the 4 3D diagnols
                if (!board[z][z][z].equals(symbol)) {
                    won10 = false;
                }
                if (!board[z][z][Math.abs(z - 2)].equals(symbol)) {
                    won11 = false;
                }
                if (!board[z][Math.abs(z - 2)][z].equals(symbol)) {
                    won12 = false;
                }
                if (!board[Math.abs(z - 2)][z][z].equals(symbol)) {
                    won13 = false;
                }
            }
            //If any 3D diagnols are met, return winner
            if (won10 || won11 || won12 || won13) {
                return symbol;
            }
        }
        return N;
    }
    
    //Returns true if the game is over, false if it now
    public boolean terminal() {
        boolean nonePresent = false;
        for (int z = 0; z < 3; z++) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (board[z][x][y].equals(N)) {
                        nonePresent = true;
                        break;
                    }
                }
            }
        }
        return !(nonePresent && get_winner().equals(N));
    }
    
    //Reset's the board to original values. All positions are N
    public void reset() {
        board = new String[][][] {
            {{N, N, N}, {N, N, N}, {N, N, N}},
            {{N, N, N}, {N, N, N}, {N, N, N}},
            {{N, N, N}, {N, N, N}, {N, N, N}}
        };
    }
    
    //Returns the board in a 3D array formay
    public String[][][] get_board() {
        return board;
    }
    
    //Returns an arraylist containing the possible moves available for the board
    public ArrayList<Integer> possible_moves() {
        ArrayList<Integer> moves = new ArrayList<>();
        //Loops through all of the positions
        for (int z = 0; z < 3; z++) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    //If posistion is empty
                    if (board[z][x][y].equals(N)) {
                        int[] coordinates = {z,x,y};
                        //Adds continous coordinate to move
                        moves.add(convert_to_continous(coordinates));
                    }
                }
            }
        }
        return moves;
    }
    
    
    
    //Formula to convert continous coordinates to (z, x, y) coordinates
    public static int[] convert_to_coordinates(int pos) {
        int firstRemainder = (pos - (pos % 9)) / 9;     //Z value
        int secondRemainder = ((pos - (firstRemainder * 9)) - ((pos - (firstRemainder * 9)) % 3)) / 3;  //X value
        int thirdRemainder = pos - (firstRemainder * 9) - (secondRemainder * 3);    //Y value
        int[] toReturn = {firstRemainder, secondRemainder, thirdRemainder};
        return toReturn;
    }
    
    //Formula to convert a set of coordinates to continous coordinates
    public static int convert_to_continous(int[] coordinates) {
        return ((coordinates[0] * 9) + (coordinates[1] * 3) + coordinates[2]);
    }

    //Formula to represent a 3D board array as a single string
    public String state_to_string() {
        String state_string = new String();
        //Loops through all of the positions
        for (int z = 0; z < 3; z++) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    //Adds position value to string
                    state_string += board[z][x][y];
                }
            }
        }
        return state_string;
    }
}
