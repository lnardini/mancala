package view;

import controller.MancalaController;

public interface MancalaView {

  void render();

  void addController(MancalaController controller);

  String getPlayerInput();
}
