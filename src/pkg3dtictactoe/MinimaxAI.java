//NAME: KRISH MODI
//DATE: January 28 2022
//DESCRIPTION: Implements minimax algorithm to 3D tic tac toe
package pkg3dtictactoe;

import java.util.ArrayList;

public class MinimaxAI {
    
    //X is trying to minimize the utility
    //O is trying to maximize the utility
    
    Board board;
    ArrayList<MinimaxAI> children;      //Stores the immediate children of a given state. Children are basically the possible future states for a state
    ArrayList<Integer> children_utilities;  //Children utilities stores the utility value of a future state
    int generations;        //Generateion stores how many future generations much be computed for a given state
    
    //Constructor that defines how deep the AI will search for every state
    public MinimaxAI(int n) {
        generations = n;
        children = new ArrayList<>();
        children_utilities = new ArrayList<>();
    }
    
    //Creates all of the possible immediate states from a given state
    //Recursive method to create and store all possible children for the next n moves
    public void create_children(Board new_board) {
        board = new_board;
        //If it is supposed to compute atleast one more generation
        if (generations >= 1) {
            //Gets the possible moves from the current state
            ArrayList<Integer> moves = board.possible_moves();
            for (int move_pos = 0; move_pos < moves.size(); move_pos++) {
                //Applies the possible moves to get next generation of states
                Board child_board = new Board(board.state_to_string());
                child_board.make_move(moves.get(move_pos));
                //Asks the next created children states to create their own children from their state
                MinimaxAI child_state = new MinimaxAI(generations - 1);     //Generations - 1 because We already have the first generations made. There are n - 1 generations left
                //Generation - 1 also prevents the recursion from going on to infinity
                child_state.create_children(child_board);
                //Adds the created children to the children arraylist
                children.add(child_state);
            }
        }
    }
    
    //Gets utility of a given state
    //This is also a recursive method
    public int get_utility() {
        //If the board is over
        if (board.terminal()) {
            String winner = board.get_winner();
            //The utility of the finished game would be who won
            switch (winner) {
                case "X":
                    return -1;  //Utility is -1 if X wins
                case "O":
                    return 1;   //Utility is 1 if O wins
                default:
                    return 0;   //Utility is 0 if no one wins
            }
        } else {
            //Else if the game is not over, asks its children for their utility and based on the agent, tries to either maximize the score or minimize
            //If it contains children. ie. has possible moves
            if (!children.isEmpty()) {
                //Asks children states for their utilities
                for (int children_pos = 0; children_pos < children.size(); children_pos++) {
                    children_utilities.add(children.get(children_pos).get_utility());
                }
                //X will try and make a move so that it's future utility is as low as possible
                if (board.get_player().equals("X"))  {
                    int min = 99999;
                    for (int utility_pos = 0; utility_pos < children_utilities.size(); utility_pos++) {
                        if (children_utilities.get(utility_pos) < min) {
                            min = children_utilities.get(utility_pos);
                        }
                    }
                    return min;
                } else {
                    //O will try and make a move so that it's future utility is as high as possible
                    int max = -99999;
                    for (int utility_pos = 0; utility_pos < children_utilities.size(); utility_pos++) {
                        if (children_utilities.get(utility_pos) > max) {
                            max = children_utilities.get(utility_pos);
                        }
                    }
                    return max;
                }
            } else {
                return 0;
            }
        }
    }
    
    //Based on the utility of it's state. Ie. the best possible outcome for an agent assuming that the other agent is also trying to get their best outcome. Get next action
    public int get_action() {
        //Best utility is the utility of the board
        int best_utility = get_utility();
        int utility_pos = -1;
        for (int pos = 0; pos < children_utilities.size(); pos++) {
            if (best_utility == children_utilities.get(pos)) {
                utility_pos = pos; 
                break;
            }
        }
        //Identifies which immediate child provided it's utility
        String current_state = board.state_to_string();
        String next_state = children.get(utility_pos).get_board().state_to_string();
        //Identifies the move it must make to attain the child that provided it's utility
        for (int boardPos = 0; boardPos < 27; boardPos++) {
            if (current_state.charAt(boardPos) != next_state.charAt(boardPos)) {
                return boardPos;
            }
        }
        //-1 means an error has occured
        return -1;
    }
    
    //Returns the board
    public Board get_board() {
        return board;
    }
    
    //This is the method that is called by the Game class
    public int ai_move(Board new_board) {
        //Clears all previous children and their utilities
        children.clear();
        children_utilities.clear();
        //Gets best possible action for the input board
        create_children(new_board);
        return get_action();
    }
}
