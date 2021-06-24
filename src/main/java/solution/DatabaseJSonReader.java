package solution;

import com.google.gson.stream.JsonReader;
import solution.readers.MedicinesReader;
import solution.readers.PhysiotherapiesReader;
import solution.readers.RescueMedicinePresentationsReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by olegbrz on 15/6/21.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {

  public DatabaseJSonReader() {}

  public String parse(String jsonFileName) throws IOException {

    InputStream usersIS = new FileInputStream(jsonFileName);
    JsonReader reader = new JsonReader(new InputStreamReader(usersIS, StandardCharsets.UTF_8));

    ChainOfResponsability cr =
        new ChainOfResponsability(
            new MedicinesReader(
                new RescueMedicinePresentationsReader(new PhysiotherapiesReader(null))));

    reader.beginObject();
    StringBuilder readData = new StringBuilder();
    while (reader.hasNext()) {
      String name = reader.nextName();
      StringBuffer res = cr.handleRead(name, reader);
      if (res != null) {
        readData.append(res).append("\n");
      }
    }

    reader.endObject();
    reader.close();
    usersIS.close();

    return new String(readData);
  }
}
