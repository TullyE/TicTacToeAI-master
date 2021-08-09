package tictactoe;
import java.util.HashMap;
/**
ticTacToeModel
Model for Tic Tac Toe
I'm not really sure if I put the correct things in the Model
Tully Eva
07/06/2021
*/
//https://www.youtube.com/watch?v=dTVVa2gfht8
//watched to learn how to do MVC programming... I was concerned that the code was 500 lines
//I was talking to one of my friends and he said I should look into MVC so I did
public class ticTacToeModel 
{
   //Private Variable
   private String[] gameBoard = new String[]{"none", "none", "none","none", "none", "none","none", "none", "none"};
    /**
     * Check if there is a win on the current game board
     * @param _board
     * @return
     */
   public boolean checkWin(String[] _board)
   {
      for(int i = 0; i <= 6; i += 3)
      {
         if ((_board[i] == _board[i+1]) && (_board[i] == _board[i+2]) && (!_board[i].equals("none")))
         {
            return true;
         }
      }
      for (int i = 0; i <= 2; i += 1)
      {
         if ((_board[i] == _board[i+3]) && (_board[i] == _board[i+6]) && (!_board[i].equals("none")))
         {
            return true;
         }
      }
      if (((_board[4] == _board[0]) && (_board[4] == _board[8])) || ((_board[4] == _board[2]) && (_board[4] == _board[6])))
      {
         if (!_board[4].equals("none"))
         {
            return true;
         }
      }
      return false;
   }
    /**
     * Check if the current gameboard has any open spots
     * @param _board
     * @return
     */
   public boolean checkTie(String[] _board)
   {
      for(String mark : _board)
      {
         if (mark.equals("none"))
         {
            return false;
         }
      }
      return true;
   }
    /**
     * Add "x" or "o" to the gameBoard
     * @param pos
     * @param playerTurn
     */
   public void addMarkModel(int pos, int playerTurn)
   {
      String marker = "o";
      if (playerTurn % 2 == 0)
      {
         marker = "x";
      }
      this.gameBoard[pos] = marker;
   }
    /**
     * return the current state of the gameBoard
     * @return
     */
   public String[] getBoard()
   {
      return gameBoard;
   }
   /**
    * generate a random position for the AI
    */
   public int positionAI(String[] _board)
   {
      int val = 0; 
      for(int i = 0; i < _board.length; i += 1)
      {
         if (_board[i].equals("none"))
         {
            val = i;
            break;
         }
      }
      return val;
   }

   public int bestMove(String[] _board)
   {
      int bestScore = -100;
      int move = 100;
      for(int i = 0; i < 9; i += 1)
      {
         if(_board[i].equals("none"))
         {
            _board[i] = "o";
            int score = minimax(_board, 0, false);
            _board[i] = "none";
            if(score > bestScore)
            {
               bestScore = score;
               move = i;
            }
         }
      }
      return move;
   }
   public int minimax(String[] _board, int depth, boolean isMaximizing)
   {
      HashMap<String, Integer> scores = new HashMap<String,Integer>(); 
      scores.put("x", -10);
      scores.put("o", 10);
      scores.put("tie", 0);
      boolean win = checkWin(_board);
      boolean tie = checkTie(_board);
      if(win)
      {
         if(isMaximizing)
         {
            return scores.get("o")+depth; // swtich for diff mode
         }
         return scores.get("x")-depth; // switch for different mode
      }
      else if (tie)
      {
         return scores.get("tie");
      }
      if (isMaximizing)
      {
         int bestScore = -100;
         for(int i = 0; i < 9; i += 1)
         {
            if(_board[i].equals("none"))
            {
               _board[i] = "o";
               int score = minimax(_board, depth + 1, false);
               _board[i] = "none";
               bestScore = maximum(score, bestScore);
            }
         }
         return bestScore;
      }
      else
      {
         int bestScore = 100;
         for(int i = 0; i < 9; i += 1)
         {
            if(_board[i].equals("none"))
            {
               _board[i] = "x";
               int score = minimax(_board, depth + 1, true);
               _board[i] = "none";
               bestScore = minimum(score, bestScore);
            }
         }
         return bestScore;
      }
   }
   public int maximum(int num1, int num2)
   {
      if(num1 > num2)
      {
         return num1;
      }
      return num2;
   }
   public int minimum(int num1, int num2)
   {
      if(num1 > num2)
      {
         return num2;
      }
      return num1;
   }
}