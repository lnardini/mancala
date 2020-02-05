package view;


import java.awt.*;
import java.io.IOException;

/**
 * A text area designed to also serve as a display for a game.
 * Simply append to this textArea and it updates the display.
 */
public class DisplayField  extends TextArea implements Appendable  {

  DisplayField() {
    super();
    this.setFont(new Font("displayFont", Font.BOLD, 30));
    this.setEditable(false);
  }

  @Override
  public Appendable append(CharSequence charSequence) throws IOException {
    this.setText(charSequence.toString());
    return this;
  }

  @Override
  public Appendable append(CharSequence charSequence, int i, int i1) throws IOException {
    Appendable builder = new StringBuilder();
    for (; i < i1; i++) {
      builder.append(charSequence.charAt(i));
    }
    this.append(builder.toString());
    return this;
  }

  @Override
  public Appendable append(char c) throws IOException {
    this.append(Character.toString(c));
    System.out.println("Test");
    return this;
  }
}
