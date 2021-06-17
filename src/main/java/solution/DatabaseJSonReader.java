package solution;

import com.google.gson.stream.JsonReader;
import solution.readers.MedicinesReader;
import solution.readers.RescueMedicinePresentationsReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jmalvarez on 11/5/16.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {

  public DatabaseJSonReader() {}

  public String parse(String jsonFileName) throws IOException {

    InputStream usersIS = new FileInputStream(jsonFileName);
    JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

    ChainOfResponsability cr =
        new ChainOfResponsability(new MedicinesReader(new RescueMedicinePresentationsReader(null)));

    reader.beginObject();
    StringBuffer readData = new StringBuffer();
    while (reader.hasNext()) {
      String name = reader.nextName();
      StringBuffer res = cr.handleRead(name, reader);
      if (res != null) {
        readData.append(res).append("\n");}
    }

    reader.endObject();
    reader.close();
    usersIS.close();

    return new String(readData);
  }
}
