package techkids.vn.zingmp3.networks.json_topsong_models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class Name {
    @SerializedName("label")
    private String label;

    public Name(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
