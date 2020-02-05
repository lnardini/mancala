import controller.MancalaController;
import controller.MancalaPlayableController;
import model.IModel;
import model.MancalaModel;
import view.MancalaPlayableView;
import view.MancalaView;

public class Mancala {

  public static void main(String[] args) {
    IModel model = new MancalaModel();
    MancalaView view = new MancalaPlayableView(model);
    MancalaController controller = new MancalaPlayableController(model, view);
    int difficulty = 3;
    int players = 1;
    //parsing arguments
    for (int i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("-d") && (args.length - i >= 2)) {
        difficulty = Integer.parseInt(args[i + 1]);
      } else if (args[i].equalsIgnoreCase("-p") && args.length - i >= 2) {
        players = Integer.parseInt(args[i + 1]);
      }
    }
    view.render();
    if (players == 1) {
      controller.playSinglePlayerGame(difficulty);
    } else if (players == 2) {
      controller.playTwoPlayerGame();
    } else {
      throw new IllegalArgumentException("Invalid number of players: Input 1 or 2 \n");
    }
  }
}
