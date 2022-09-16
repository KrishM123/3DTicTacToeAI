//NAME: KRISH MODI
//DATE: January 28 2021
//Description: Implements Reinforced R algorithm to a given board to get best possible move. This will learn by playing against itself.
package pkg3dtictactoe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class ReinforcedAI {
    //I am used to programming in Python and I know that I needed to use something like a python dictionary to store all of the knowledge
    Map<String, Double> knowledge = new HashMap<>();    //Stores the utility of an action given a board that it learns from playing itself in a dictionary type object
    double learning_rate;       //Learning rate stores how much to change previous utility values based on new information
    double exploration_rate;    //Exploration rate stores how often the training should try new action that are not the best
    //exploration_rate counteracts the greedy nature of the algorithm which will obly 
    int games_to_train;         //Stores how many games it should play to learn about 3D tic tac toe
    
    String N = "N";
    String X = "X";
    String O = "O";
    
    //Constructor to relearn all information
    public ReinforcedAI(double lr, double er, int n, String file) throws IOException {
        learning_rate = lr;
        exploration_rate = er;
        games_to_train = n;
        train();        //Trains AI
        save_knowledge(file);   //Stores all learned knowledge in the file specified
    }
    
    //Constructor to load previouse knowledge that it has learned before through training
    //10000.txt stores the knowledge it has learned from playing against itself 10000 times
    public ReinforcedAI(String file) throws FileNotFoundException, IOException {
        read_knowledge(file);   //Reads knowledge from a given file
    }
    
    
    
    //Updates old knowledge from any new information it learns
    public void update(Board old_state, int action, Board new_state, double reward) {
        double old_reward = get_reward(old_state, action);      //Utility it had learned before
        double best_future = best_future_reward(new_state);     //Best future reward from its knowledge given its new state
        update_reward_value(old_state, action, old_reward, reward, best_future);    //Uses the Q learning formula to update the old utility value
    }
    
    //Returns the old utility that is already in knowledge
    public double get_reward(Board state, int action) {
        if (knowledge.containsKey(Key(state, action))) {
            return knowledge.get(Key(state, action));
        }
        return 0.0;
    }
    
    //Updates utility value according to the Q learning equation
    public void update_reward_value(Board state, int action, double old_reward, double reward, double future_reward) {
        if (knowledge.containsKey(Key(state, action))) {
            knowledge.replace(Key(state, action), old_reward + (learning_rate * ((reward + future_reward) - old_reward)));
        } else {
            knowledge.put(Key(state, action), old_reward + (learning_rate * ((reward + future_reward) - old_reward)));
        }        
    }
    
    //Returns the best utility of a state
    public double best_future_reward(Board state) {
        //Gets all of the possible moves given a state
        ArrayList<Integer> actions = state.possible_moves();
        
        ArrayList<Double> values = new ArrayList<>();   //Stores the utililities of the immediate children of a board
        //For all of the children, if there are children that have a utility, add ot values
        for (int action_pos = 0; action_pos < actions.size(); action_pos ++) {
            if (knowledge.containsKey(Key(state, actions.get(action_pos)))) {
                values.add(knowledge.get(Key(state, actions.get(action_pos))));
            } else {
                values.add(0.0);
            }
        }
        if (values.isEmpty()) {
            return 0;
        }
        //Return the maximum utility from the values
        double currentMax = 0;
        for (int pos = 0; pos < values.size(); pos++) {
            if (values.get(pos) > currentMax) {
                currentMax = values.get(pos);
            }
        }
        return currentMax;
    }
    
    //Chooses the best action given a state. Explore is true if the AI is training, false if the AI is actually playing
    public int choose_action(Board state, boolean explore) {
        Random number = new Random();
        double old = -999999.0;
        int action = -1;
        //For every key in knowledge, if the board is equal to current state and the utility of the key is more than current max, store action and update current max with this new max
        for (String keys : knowledge.keySet()) {
            if (keys.contains(state.state_to_string())) {
                if (knowledge.get(keys) > old) {
                    old = knowledge.get(keys);
                    action = Integer.parseInt(keys.split(" ")[1]);
                }
            }
        }
        //Chooses a random action
        ArrayList<Integer> possible_moves = state.possible_moves();
        int random_action = possible_moves.get(number.nextInt(possible_moves.size()));
        //If no action is found in current knowledge set, return random action
        if (action == -1) {
            return random_action;
        } else {
            double choice = number.nextDouble();    //choice contains whether the random action will be chosen or the best action
            //If the AI is exploring and the choice indicates it should explore, return the random action, else return the best possible action
            if (choice < exploration_rate && explore) {
                return random_action;
            } else {
                return action;
            }
        }
    }
        
    //Represents a state action pair as a string to be stored in knowledge
    public String Key(Board state, int action) {
        return state.state_to_string() + " " + Integer.toString(action);
    }
    
    //Runs the AI to play against itself n times to learn knowledge
    public void train() {
        Board game = new Board();
        //Plays n times
        for (int gameNum = 1; gameNum != games_to_train; gameNum++) {
            System.out.println("Playing game " + Integer.toString(gameNum));
            
            String[] old_moves = new String[]{" ", " "};        //Stores the old moves takes by each agent to get to current state
            //Until the game is not over
            while (true) {
                //Store the old state in a new object
                Board old_state = new Board(game.get_board());
                int action = choose_action(old_state, true);    //Chooses best action based on current knowledge with explore being true
                //X is 0, O is 1
                int binary_player = 0;
                if (game.get_player().equals("O"))  {
                    binary_player = 1;
                }
                //Stores the move taken to achieve the current state in the old_moves array in the correct position given it's binary player
                old_moves[binary_player] = Key(old_state, action);
                //Make move for a chosen action
                game.make_move(action);
                Board new_state = new Board(game.get_board()); //Stores the child board in new_state
                
                //If game is over, update the last two old moves with the utility of the game
                if (game.terminal()) {
                    //If someone one
                    if (!game.get_winner().equals(N)) {
                        //Update knowledge with this new information
                        //Winning moves get a plus one
                        update(old_state, action, new_state, 1);
                        binary_player = 0;
                        if (game.get_player().equals("O"))  {
                            binary_player = 1;
                        }
                        String last_player_info = old_moves[Math.abs(binary_player - 1)];
                        //Losing moves get -1
                        update(new Board(last_player_info.split(" ")[0]), Integer.parseInt(last_player_info.split(" ")[1]), old_state, -1);
                        game.reset();   //Resets the game to be played again
                        break;
                    } else {
                        //If the game ended in a tie, both of the players get a -0.5 for their chosen moves
                        update(old_state, action, new_state, -0.5);
                        binary_player = 0;
                        if (game.get_player().equals("O"))  {
                            binary_player = 1;
                        }
                        String last_player_info = old_moves[Math.abs(binary_player - 1)];
                        update(new Board(last_player_info.split(" ")[0]), Integer.parseInt(last_player_info.split(" ")[1]), old_state, -0.5);
                        game.reset();
                        break;
                    }
                } else {
                    //If no one won yet, update the moves with a 0
                    binary_player = 0;
                    if (game.get_player().equals("O"))  {
                        binary_player = 1;
                    }
                    String last_player_info = old_moves[Math.abs(binary_player - 1)];
                    update(new Board(last_player_info.split(" ")[0]), Integer.parseInt(last_player_info.split(" ")[1]), old_state, 0);
                }
            }
        }
    }
    
    
    
    //Saves the knowledge key map to the given file name
    public void save_knowledge(String file_name) throws IOException {
        FileWriter file = new FileWriter("file_name");
        for (String keys : knowledge.keySet()) {
            //Writes the keys in a pre determined format to be read again
            file.write(keys + ":" + knowledge.get(keys) + "\n");
        }
        file.close();
    }
    
    //Reads the knowledge from a given file
    //If the file does not exist, retrains the AI and stores new knowldge
    public void read_knowledge(String file_name) throws FileNotFoundException, IOException {
        //Try and read the knowledge from the specified file name
        try {
            File file = new File(file_name);
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String data = read.nextLine();
                //Read using the pre determined storage key
                knowledge.put(data.split(":")[0], Double.parseDouble(data.split(":")[1]));
            }
            read.close();
        } catch (FileNotFoundException ex) {
            //if cannot read data, re train the AI and store the new knowledge
            System.out.println("Could not load data");
            learning_rate = 0.8;
            exploration_rate = 0.1;
            games_to_train = 100;
            train();
            save_knowledge(file_name);
            read_knowledge(file_name);
        }
    }
    
    //This method is called by Game
    public int ai_move(Board state) {
        return choose_action(state, false);     //Returns the best action without exploring
    }
}
