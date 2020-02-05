package model.linkedList;


public class CircularList {
  private Node p1Score;
  private Node p2Score;
  private int length;

  /**
   * Creates a new Linked list of mancala pits.
   * @param length the number of elements in the list
   */
  public CircularList(int length) {
    if (length % 2 == 1) {
      throw new IllegalArgumentException("Must have even length");
    }
    p1Score = new Node(0);
    p2Score = new Node(0);
    //adding playing pits
    for (int i = 0; i < (length / 2) - 1; i++) {
      p1Score.addNext(new Node(4));
      p2Score.addNext(new Node(4));
    }
    //connecting lists
    p1Score.addNext(p2Score);
    p2Score.addNext(p1Score);
  }

  /**
   * Returns the node at the specified index in the list.
   * @param index the index of the desired node
   * @return the desired node
   */
  public Node get(int index) {
    Node temp = p1Score;
    while (index > 0) {
      temp = temp.getNext();
      index-= 1;
    }
    return temp;
  }
}
