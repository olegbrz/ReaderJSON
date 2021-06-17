package solution.readers;

import com.google.gson.stream.JsonReader;
import com.sun.net.httpserver.Filter;

import java.io.IOException;

public class RescueMedicinePresentationsReader extends ChainElement {
  protected String allowedKey = "rescueMedicinePresentations";
  protected String MEDREF_FIELD_TAGNAME = "medicineRef";
  protected String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
  protected String INHREF_FIELD_TAGNAME = "inhalerRef";
  protected String DOSE_FIELD_TAGNAME = "dose";

  public RescueMedicinePresentationsReader(ChainElement successor) {
    super(successor);
    this.setAllowedKey(allowedKey);
  }


  @Override
  public StringBuffer read(JsonReader jr) throws IOException {
    StringBuffer rescueMedicinePresentationData = new StringBuffer();
    jr.beginArray();
    while (jr.hasNext()) {
      jr.beginObject();
      rescueMedicinePresentationData
          .append(readRescueMedicinePresentationEntry(jr))
          .append("\n");
      jr.endObject();
    }
    rescueMedicinePresentationData.append("\n");
    jr.endArray();
    return rescueMedicinePresentationData;
  }

  public String readRescueMedicinePresentationEntry(JsonReader jr) throws IOException {
    String medRef = null;
    String aiRef = null;
    String inhRef = null;
    String dose = null;
    while (jr.hasNext()) {
      String name = jr.nextName();
      if (name.equals(MEDREF_FIELD_TAGNAME)) {
        medRef = jr.nextString();
      } else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
        aiRef = jr.nextString();
      } else if (name.equals(INHREF_FIELD_TAGNAME)) {
        StringBuffer res = new StringBuffer();
        jr.beginArray();
        while (jr.hasNext()) {
          res.append(jr.nextString()).append(",");
        }
        jr.endArray();
        res.deleteCharAt(res.length() - 1);
        inhRef = new String(res);
      } else if (name.equals(DOSE_FIELD_TAGNAME)) {
        StringBuffer res = new StringBuffer();
        jr.beginArray();
        while (jr.hasNext()) {
          res.append(jr.nextString()).append(",");
        }
        jr.endArray();
        res.deleteCharAt(res.length() - 1);
        dose = new String(res);
      } else {
        jr.skipValue();
      }

    }
    return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose;
  }
}

