package solution.readers;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class MedicinesReader extends ChainElement {
    String allowedKey = "medicines";
    private static final String NAME_FIELD_TAGNAME = "name";

    public MedicinesReader(ChainElement successor) {
        super(successor);
        this.setAllowedKey(allowedKey);
    }

    @Override
    public StringBuffer read(JsonReader jr) throws IOException {
        StringBuffer medicineData = new StringBuffer();
        jr.beginArray();
        while (jr.hasNext()) {
            jr.beginObject();
            medicineData.append(readMedicineEntry(jr)).append("\n");
            jr.endObject();
        }
        medicineData.append("\n");
        jr.endArray();
        return medicineData;
    }

    public String readMedicineEntry(JsonReader jr) throws IOException {
        String medName = null;
        while (jr.hasNext()) {
            String name = jr.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                medName = jr.nextString();
            } else {
                jr.skipValue();
            }
        }

        return medName;
    }
}
