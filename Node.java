package model.linkedList;

/**
 * Node in a linked list designed to be used in a Mancala board.
 */
public class Node {
  private int value;
  private Node next;

  Node(int value) {
    this.value = value;
  }

  void addNext(Node next) {
    if (this.next == null) {
      this.next = next;
    } else {
      this.next.addNext(next);
    }
  }

  Node getNext() {
    return this.next;
  }

  public void addValue(int val) {
    value += val;
  }

  public void clear() {
    value = 0;
  }

  public int getValue() {
    return value;
  }
}
