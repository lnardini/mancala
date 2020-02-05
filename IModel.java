package model;

import model.MancalaModel.Turn;
public interface IModel {


  void updateGameState();

  /**
   * Sums the marbles remaining on a player's side of the board
   * @param player the 1-indexed player number
   * @return the number of marbles left in a player's active pits
   */
  int sumPlayerMarbles(int player);

  int getMarblesAt(int pitIndex);

  boolean move(int pitIndex);

  int getTurn();

  boolean isGameOver();


}
