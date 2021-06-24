package solution.readers;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public abstract class ChainReader {
  protected String allowedKey;
  protected ChainReader successor;
  protected String FIELD_SEPARATOR = ";";

  public ChainReader(ChainReader successor) {
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
      return (null);
    }
  }

  public StringBuffer read(JsonReader jr) throws IOException {
    StringBuffer data = new StringBuffer();
    jr.beginArray();
    while (jr.hasNext()) {
      jr.beginObject();
      data.append(readEntry(jr)).append("\n");
      jr.endObject();
    }
    data.append("\n");
    jr.endArray();
    return data;
  }

  public abstract String readEntry(JsonReader jr) throws IOException;

  public boolean canRead(String key) {
    return allowedKey.equals(key);
  }

  public void setSuccessor(ChainReader successor) {
    this.successor = successor;
  }

  public void setAllowedKey(String allowedKey) {
    this.allowedKey = allowedKey;
  }
}
