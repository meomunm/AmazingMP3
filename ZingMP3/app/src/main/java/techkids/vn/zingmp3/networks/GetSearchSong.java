package techkids.vn.zingmp3.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import techkids.vn.zingmp3.networks.json_search_songs.SearchMain;

/**
 * Created by ADMIN on 7/22/2017.
 */

public interface GetSearchSong {
    @GET("http://api.mp3.zing.vn/api/mobile/search/song")
    Call<SearchMain> getSearchSong(@Query("requestdata") String requestdata);
}
