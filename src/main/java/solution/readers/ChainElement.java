package solution.readers;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public abstract class ChainElement {
  protected String allowedKey;
  protected ChainElement successor;
  protected String FIELD_SEPARATOR = ";";

  public ChainElement(ChainElement successor) {
    this.successor = successor;
  }

  public StringBuffer handleRead(String key, JsonReader jr) throws IOException {
    if (canRead(key)) {
      return read(jr);
    } else if (successor != null) {
      return successor.handleRead(key, jr);
    } else {
      jr.skipValue();
      System.err.println("Category " + key + " not processed.");
      return(null);
    }
  }

  public abstract StringBuffer read(JsonReader jr) throws IOException;

  public boolean canRead(String key) {
    return allowedKey.equals(key);
  }

  public void setSuccessor(ChainElement successor) {
    this.successor = successor;
  }

  public void setAllowedKey(String allowedKey) {
    this.allowedKey = allowedKey;
  }
}
