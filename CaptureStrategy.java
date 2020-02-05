package model.strategy;

import model.IModel;

public class CaptureStrategy implements MancalaStrategy {
  private MancalaStrategy backup;

  public CaptureStrategy(MancalaStrategy strategy) {
    this.backup = strategy;
  }

  @Override
  public int getMoveIndex(IModel model) {
    System.out.println("Seeing if capture is available");
    //loop searching for catchable pit
    for (int i = 6; i >= 2; i--) {
      if (model.getMarblesAt(i) == 13) {
        return 7 - i; //a 13 pit marble will guarantee a capture of 2 or more marbles
      }
      if (model.getMarblesAt(i) == 0 && model.getMarblesAt(14 - i) != 0) { //found pit to capture
        //loop searching for pit whose move would end up in capture pit
        for (int j = 1; j <= 5; j++) {
          if (model.getMarblesAt(j) != 0 && model.getMarblesAt(j) < 14 && j + model.getMarblesAt(j) == i) {
            return 7 - j;
          }
        }
      }
    }
    return backup.getMoveIndex(model);
  }
}
