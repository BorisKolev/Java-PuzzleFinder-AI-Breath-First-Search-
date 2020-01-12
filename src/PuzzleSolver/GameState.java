package PuzzleSolver;
import java.util.ArrayList;

public class GameState {

    private int spaceX_Pos;
    private int spaceY_Pos;
    private char[][] board;
    static final char[][] INITIAL_BOARD = { {'1','2', '3'},
            {'4','5', '6'},
            {'7','8', ' '} };

    private static final char[][] GOAL_BOARD = { {'8','7', '6'},
            {'5','4', '3'},
            {'2','1', ' '} };

    /*
        GameState is a constructor that takes 2D char array holding a board configuration as argument.
        private variable spaceX_Pos is used to point in which row the ' ' is
        private variable spaceY_Pos is used to point in which column the ' ' is
    */

    GameState(char[][] board) {
        this.board = board;

        // Looping through the board and finding the coordination of spaceX_Pos and spaceY_Pos
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if (board[i][j] == ' ') {
                    this.spaceX_Pos = i;
                    this.spaceY_Pos = j;
                    break;
                }
            }
        }
    }

    /*
    clone returns a new GameState with the same board configuration as the current GameState.
    */
    public GameState clone(){
        char[][] clonedBoard = new char[3][3];
        for(int i = 0; i < clonedBoard.length; i++){
            System.arraycopy(this.board[i], 0, clonedBoard[i], 0, clonedBoard[0].length);
        }
        return new GameState(clonedBoard);
    }

    /*
        In case you need to print the position of SpaceX_Pos and SpaceY_Pos
     */
    public int getSpaceX_Pos() {
        return spaceX_Pos;
    }

    public int getSpaceY_Pos() {
        return spaceY_Pos;
    }

    /*
    isGoal returns true if and only if the board configuration of the current GameState is the goal
    configuration.
    */
    boolean isGoal() {
        for(int i = 0; i < 3; i++){
            for(int j=0; j < 3; j++){
                if(this.board[i][j] != GOAL_BOARD[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    /*
        Takes another GameState object as an argument and checks whether
        this board is the same as the argument board
     */
    boolean sameBoard(GameState gs){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (this.board[i][j] != gs.board[i][j]) return false;
            }
        }
        return true;
    }

    /*
        In case you want to print the GameState object
        It will print the current board
        Using StringBuilder since it is more efficient
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (char[] chars : this.board) {
            for (char aChar : chars) {
                s.append(aChar);
            }
        }
        return s + "]";
    }

    /*
        possibleMoves returns a list of all GameStates that can be reached in a single move from the current GameState.
     */
    ArrayList<GameState> possibleMoves() {
        ArrayList<GameState> moves = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                // checks the four positions around ' '
                if ((this.spaceX_Pos - 1 == i && spaceY_Pos == j) || // if one row above and same column
                        (this.spaceX_Pos == i && this.spaceY_Pos + 1 == j) || // or if same row and one column right
                        (this.spaceX_Pos + 1 == i && this.spaceY_Pos == j) || // or if one row below and same column
                        (this.spaceX_Pos == i && this.spaceY_Pos - 1 == j)){  // or if same row and one column left CLONE

                    GameState newState = this.clone();
                    newState.board[this.spaceX_Pos][this.spaceY_Pos] = this.board[i][j]; // put the number into ' '
                    newState.board[i][j] = ' '; // put ' ' in the new position
                    newState.spaceX_Pos = i; // relocate spaceX_Pos
                    newState.spaceY_Pos = j; // relocate spaceY_Pos
                    moves.add(newState); // add it to the list

                }
            }
        }
        return moves;
    }

}
