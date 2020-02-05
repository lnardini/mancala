package controller;

import java.awt.event.KeyListener;

public interface MancalaController extends KeyListener {

  void playTwoPlayerGame();

  void playSinglePlayerGame(int difficulty);

  void addIO(Readable rd, Appendable ap);
}
