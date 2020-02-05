package view;

import javax.swing.*;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;

/**
 * JTextField that can be read, serving as the input for a playable game.
 */

public class InteractionField extends JTextField implements Readable  {


  @Override
  public int read(CharBuffer charBuffer) throws IOException {
    charBuffer.put(this.getText());
    return this.getText().length();
  }
}

