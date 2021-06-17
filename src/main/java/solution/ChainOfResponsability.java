package solution;

import com.google.gson.stream.JsonReader;
import solution.readers.ChainElement;

import java.io.IOException;

public class ChainOfResponsability {
  ChainElement ce;

  public ChainOfResponsability(ChainElement ce) {
    this.ce = ce;
  }

  public StringBuffer handleRead(String key, JsonReader jr) throws IOException {
    return ce.handleRead(key, jr);
  }
}
