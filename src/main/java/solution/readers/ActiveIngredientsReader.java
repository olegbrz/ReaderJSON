package solution.readers;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class ActiveIngredientsReader extends ChainElement {
    protected String allowedKey = "activeIngredients";
    protected String NAME_FIELD_TAGNAME = "name";

    public ActiveIngredientsReader(ChainElement successor) {
        super(successor);
        this.setAllowedKey(allowedKey);
    }

    @Override
    public StringBuffer read(JsonReader jr) throws IOException {
        StringBuffer activeIngredientData = new StringBuffer();
        jr.beginArray();
        while (jr.hasNext()) {
            jr.beginObject();
            activeIngredientData.append(readActiveIngredientsEntry(jr)).append("\n");
            jr.endObject();
        }
        activeIngredientData.append("\n");
        jr.endArray();
        return activeIngredientData;
    }

    public String readActiveIngredientsEntry(JsonReader jr) throws IOException {
        String activeIngredientName = null;
        while (jr.hasNext()) {
            String name = jr.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                activeIngredientName = jr.nextString();
            } else {
                jr.skipValue();
            }
        }

        return activeIngredientName;
    }
}
