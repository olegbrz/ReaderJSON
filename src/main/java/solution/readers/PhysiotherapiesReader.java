package solution.readers;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class PhysiotherapiesReader extends ChainElement{
    protected String allowedKey = "physiotherapies";
    protected String NAME_FIELD_TAGNAME = "name";
    protected String IMAGE_FIELD_TAGNAME = "image";

    public PhysiotherapiesReader(ChainElement successor) {
        super(successor);
        this.setAllowedKey(allowedKey);
    }


    public String readEntry(JsonReader jr) throws IOException {
        String nameTag = null;
        String imageTag = null;
        while (jr.hasNext()) {
            String name = jr.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                nameTag = jr.nextString();
            }
            else if (name.equals(IMAGE_FIELD_TAGNAME)) {
                imageTag = jr.nextString();
            }
            else {
                jr.skipValue();
            }
        }
        return nameTag + FIELD_SEPARATOR + imageTag;
    }
}
