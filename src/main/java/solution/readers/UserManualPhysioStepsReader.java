package solution.readers;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class UserManualPhysioStepsReader extends ChainElement{
    protected String allowedKey = "userManualPhysioSteps";
    protected String STEPTITLE_FIELD_TAGNAME = "stepTitle";
    protected String STEPIMAGE_FIELD_TAGNAME = "stepImage";
    protected String STEPTEXT_FIELD_TAGNAME = "stepText";
    protected String PHYSIOREF_FIELD_TAGNAME = "physioRef";

    public UserManualPhysioStepsReader(ChainElement successor) {
        super(successor);
        this.setAllowedKey(allowedKey);
    }

    @Override
    public String readEntry(JsonReader jr) throws IOException {
        String stepTitle = null;
        String stepImage = null;
        String stepText = null;
        String physioRef = null;
        while (jr.hasNext()) {
            String name = jr.nextName();
            if (name.equals(STEPTITLE_FIELD_TAGNAME)) {
                stepTitle = jr.nextString();
            } else if (name.equals(STEPIMAGE_FIELD_TAGNAME)) {
                stepImage = jr.nextString();
            } else if (name.equals(STEPTEXT_FIELD_TAGNAME)) {
                stepText = jr.nextString();
            } else if (name.equals(PHYSIOREF_FIELD_TAGNAME)) {
                physioRef = jr.nextString();
            } else {
                jr.skipValue();
            }

        }
        return stepTitle + FIELD_SEPARATOR + stepImage + FIELD_SEPARATOR + stepText + FIELD_SEPARATOR + physioRef;
    }
}
