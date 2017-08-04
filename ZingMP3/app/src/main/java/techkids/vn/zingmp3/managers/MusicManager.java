package techkids.vn.zingmp3.managers;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import hybridmediaplayer.HybridMediaPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techkids.vn.zingmp3.R;
import techkids.vn.zingmp3.databases.TopSongModel;
import techkids.vn.zingmp3.networks.GetSearchSong;
import techkids.vn.zingmp3.networks.RetrofitFactory;
import techkids.vn.zingmp3.networks.json_search_songs.DocObject;
import techkids.vn.zingmp3.networks.json_search_songs.SearchMain;
import techkids.vn.zingmp3.services.PlayMusicNotification;
import techkids.vn.zingmp3.utils.FuzzyMatch;
import techkids.vn.zingmp3.utils.Utils;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class MusicManager {
    public static HybridMediaPlayer hybridMediaPlayer;
    private final static String TAG = MusicManager.class.toString();


    public static void loadSearchSong(final TopSongModel topSongModel, final Context context, final SeekBar sbMiniPlayer, final ImageView ivPlayPause) {
        GetSearchSong getSearchSong = RetrofitFactory.getInstance().create(GetSearchSong.class);
        getSearchSong.getSearchSong("{\"q\":\" " + " " + topSongModel.getName() + "\", \"sort\":\"hot\", \"start\":\"0\", \"length\":\"10\"}").enqueue(new Callback<SearchMain>() {
            @Override
            public void onResponse(Call<SearchMain> call, Response<SearchMain> response) {
                if (response.body().getDocs().size() > 0) {
                    //1. list ratio
                    List<Integer> ratioList = new ArrayList<Integer>();
                    for (DocObject docObject : response.body().getDocs()) {
                        int ratio = FuzzyMatch.getRatio(
                                topSongModel.getName() + " " + topSongModel.getArtist(),
                                docObject.getTitle() + " " + docObject.getArtist(), false);

                        ratioList.add(ratio);
                    }
                    //2. get max
                    for (int i = 0; i < ratioList.size(); i++) {
                        if (Collections.max(ratioList) == ratioList.get(i)) {
                            String linkSource = response.body().getDocs().get(i).getSource().getLinkSource();
                            topSongModel.setLinkSourece(linkSource);
                            setupMusic(topSongModel, context);


                            updateSongRealtime(topSongModel, sbMiniPlayer, ivPlayPause);
                        }
                    }
                    //  Toast.makeText(context, topSongModel.getLinkSourece(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchMain> call, Throwable t) {
                Toast.makeText(context, "No connect access INTERNET ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void setupMusic(TopSongModel topSongModel, Context context) {
        if (hybridMediaPlayer != null) {
            hybridMediaPlayer.release();
            Log.d(TAG, "setupMusic: Đã xóa bài hát trước đó");
        }

        hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
        hybridMediaPlayer.setDataSource(topSongModel.getLinkSourece());

        hybridMediaPlayer.prepare();
        hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(HybridMediaPlayer hybridMediaPlayer) {
                hybridMediaPlayer.play();
                Log.d(TAG, "onPrepared: Đang phát nhạc");
            }
        });

    }

    public static void playPause() {
        if (hybridMediaPlayer.isPlaying()) {
            hybridMediaPlayer.pause();
            Log.d(TAG, "playPause: Đã pause nhạc");
        } else {
            hybridMediaPlayer.play();
            Log.d(TAG, "playPause: Play lại nhạc");
        }
        PlayMusicNotification.updateNotification(hybridMediaPlayer.isPlaying());
    }

    public static void updateSongRealtime(TopSongModel topSongModel, final SeekBar sbMiniPlayer, final ImageView ivMiniplayPause) {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sbMiniPlayer.setMax(hybridMediaPlayer.getDuration());
                sbMiniPlayer.setProgress(hybridMediaPlayer.getCurrentPosition());

                if (hybridMediaPlayer.isPlaying()) {
                    //todo set image play
                    ivMiniplayPause.setImageResource(R.drawable.exo_controls_pause);
                } else {
                    //todo set image pause
                    ivMiniplayPause.setImageResource(R.drawable.exo_controls_play );
                }
                //Todo: set text for mainPlayer textviewCurrent and textViewDuration


                handler.postDelayed(this, 100);
            }
        };
        runnable.run();
    }
    public static void updateSongRealtime(TopSongModel topSongModel, final SeekBar sbMiniPlayer, final ImageView ivMiniplayPause, final TextView tvCurrent, final TextView tvDuration) {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sbMiniPlayer.setMax(hybridMediaPlayer.getDuration());
                sbMiniPlayer.setProgress(hybridMediaPlayer.getCurrentPosition());

                if (hybridMediaPlayer.isPlaying()) {
                    //todo set image play
                    ivMiniplayPause.setImageResource(R.drawable.exo_controls_play);
                } else {
                    //todo set image pause
                    ivMiniplayPause.setImageResource(R.drawable.exo_controls_pause);
                }
                //Todo: set text for mainPlayer textviewCurrent and textViewDuration
                if (tvCurrent != null && tvDuration != null){
                    tvDuration.setText(Utils.convertTime(hybridMediaPlayer.getDuration()));
                    tvCurrent.setText(Utils.convertTime(hybridMediaPlayer.getCurrentPosition()));
                }

                handler.postDelayed(this, 100);
            }
        };
        runnable.run();
    }
}
