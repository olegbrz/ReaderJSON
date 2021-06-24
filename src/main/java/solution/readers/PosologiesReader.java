package solution.readers;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class PosologiesReader extends ChainReader {
    protected String allowedKey = "posologies";
    protected String DESCRIPTION_FIELD_TAGNAME = "description";

    public PosologiesReader(ChainReader successor) {
        super(successor);
        this.setAllowedKey(allowedKey);
    }

    public String readEntry(JsonReader jr) throws IOException {
        String medName = null;
        while (jr.hasNext()) {
            String name = jr.nextName();
            if (name.equals(DESCRIPTION_FIELD_TAGNAME)) {
                medName = jr.nextString();
            } else {
                jr.skipValue();
            }
        }

        return medName;
    }
}
