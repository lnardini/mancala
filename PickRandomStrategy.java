package model.strategy;

import model.IModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PickRandomStrategy implements MancalaStrategy {
  private List<Integer> indexes;
  
  public PickRandomStrategy() {
    indexes = new ArrayList<>();
    indexes.add(6);
    indexes.add(5);
    indexes.add(4);
    indexes.add(3);
    indexes.add(2);
    indexes.add(1);
  }

  @Override
  public int getMoveIndex(IModel model) {
    while (indexes.size() > 0) {
      int index = new Random().nextInt(indexes.size());
      int pit = indexes.get(index);
      if (model.getMarblesAt(pit) != 0) {
        return pit;
      } else {
        indexes.remove(index);
      }
    }
    throw new IllegalStateException("No viable moves left");
  }
}
