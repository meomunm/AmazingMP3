package techkids.vn.zingmp3.networks.json_search_songs;

import java.util.List;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class SearchMain {
    private List<DocObject> docs;

    public SearchMain(List<DocObject> docs) {
        this.docs = docs;
    }

    public List<DocObject> getDocs() {
        return docs;
    }

    public void setDocs(List<DocObject> docs) {
        this.docs = docs;
    }
}
