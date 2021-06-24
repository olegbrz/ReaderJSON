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

    public String readEntry(JsonReader jr) throws IOException {
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
