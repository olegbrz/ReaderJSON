package solution;

import com.google.gson.stream.JsonReader;
import solution.readers.ChainReader;

import java.io.IOException;

public class ChainOfResponsability {
  ChainReader ce;

  public ChainOfResponsability(ChainReader ce) {
    this.ce = ce;
  }

  public StringBuffer handleRead(String key, JsonReader jr) throws IOException {
    return ce.handleRead(key, jr);
  }
}
