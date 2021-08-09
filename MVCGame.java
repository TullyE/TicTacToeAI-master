/**
MVCGame
Where I actually create an instance of each M, V, and C
Tully Eva
07/06/2021
*/
import javax.sound.sampled.*;
import tictactoe.ticTacToeController;
import tictactoe.ticTacToeModel;
import tictactoe.ticTacToeView;
import java.net.URL;

public class MVCGame 
{
   public static void main(String[] args) throws Exception
   {
      //https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java
      //https://stackoverflow.com/questions/64509691/how-can-i-add-some-sound-to-my-java-jframe
      //Plays audio
      URL file = new URL("file:///" + System.getProperty("user.dir") + "/audio" + "/Marcos%20H.%20Bolanos%20-%20A%20Simple%20Life.wav");
      System.out.println("file:///" + System.getProperty("user.dir") + "/audio" + "/Marcos%20H.%20Bolanos%20-%20A%20Simple%20Life.wav");
      AudioInputStream ais = AudioSystem.getAudioInputStream(file);
      Clip clip = AudioSystem.getClip();
      clip.open(ais);
      clip.setFramePosition(0);
      clip.start();
      while (true)
      {
         ticTacToeModel ticTacToeM = new ticTacToeModel();
         ticTacToeView ticTacToeV = new ticTacToeView();
         ticTacToeController ticTacToeC = new ticTacToeController(ticTacToeV, ticTacToeM);
         ticTacToeC.logic();
      }
   }
}