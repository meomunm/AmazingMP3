package techkids.vn.zingmp3.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techkids.vn.zingmp3.R;
import techkids.vn.zingmp3.adapters.TopSongAdapter;
import techkids.vn.zingmp3.databases.MusicTypeModel;
import techkids.vn.zingmp3.databases.TopSongModel;
import techkids.vn.zingmp3.events.OnClickMusicType;
import techkids.vn.zingmp3.events.OnClickTopSong;
import techkids.vn.zingmp3.managers.MusicManager;
import techkids.vn.zingmp3.managers.ScreenManager;
import techkids.vn.zingmp3.networks.GetTopSong;
import techkids.vn.zingmp3.networks.RetrofitFactory;
import techkids.vn.zingmp3.networks.json_topsong_models.Entry;
import techkids.vn.zingmp3.networks.json_topsong_models.TopSongJsonModel;

/**
 * Created by ADMIN on 7/21/2017.
 */

public class TopSongFragment extends Fragment implements View.OnClickListener {
    private final String TAG = TopSongFragment.class.toString();
    private MusicTypeModel musicTypeModel;
    private TopSongAdapter topSongAdapter;
    private List<TopSongModel> topSongModelList = new ArrayList<>();

    private Animation rotation;
    private Boolean isRotating = false;

    @BindView(R.id.iv_back_top_song)
    ImageView ivBackTopSong;

    @BindView(R.id.iv_favourite_top_song)
    ImageView ivFavouriteTopSong;

    @BindView(R.id.iv_top_song)
    ImageView ivTopSong;

    @BindView(R.id.tv_top_song)
    TextView tvTopSong;

    @BindView(R.id.tv_size_top_song)
    TextView tvSizeTopSong;

    @BindView(R.id.iv_play_top_song)
    ImageView ivPlayTopSong;

    @BindView(R.id.rv_top_song)
    RecyclerView rvTopSong;

    public TopSongFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topsong, container, false);

        this.setupUI(view);
        this.loadData();
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        topSongAdapter = new TopSongAdapter(this.topSongModelList, getContext());
        rvTopSong.setAdapter(topSongAdapter);
        rvTopSong.setLayoutManager(new LinearLayoutManager(getContext()));

        ivTopSong.setImageResource(musicTypeModel.getImageID());
        ivPlayTopSong.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        tvTopSong.setText(musicTypeModel.getKey());
        tvSizeTopSong.setText(topSongModelList.size() + " songs");

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rvTopSong.addItemDecoration(dividerItemDecoration);

        topSongAdapter.setOnItemClick(this);

        ivBackTopSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 0)
                    getFragmentManager().popBackStack();
                else Toast.makeText(getContext(), "Co Bug roi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData() {
        GetTopSong getTopSong = RetrofitFactory.getInstance().create(GetTopSong.class);
        getTopSong.getTopSongModel(musicTypeModel.getId()).enqueue(new Callback<TopSongJsonModel>() {
            @Override
            public void onResponse(Call<TopSongJsonModel> call, Response<TopSongJsonModel> response) {
                for (Entry entry : response.body().getFeed().getEntries()) {
                    TopSongModel topSongModel = new TopSongModel();
                    topSongModel.setArtist(entry.getArtist().getLabel());
                    topSongModel.setImage(entry.getImages().get(2).getLabel());
                    topSongModel.setName(entry.getName().getLabel());
                    topSongModelList.add(topSongModel);
                }
                topSongAdapter.notifyDataSetChanged();
                tvSizeTopSong.setText(topSongModelList.size() + " songs");
            }

            @Override
            public void onFailure(Call<TopSongJsonModel> call, Throwable t) {
                Log.d(TAG, "onFailure: huhudcm bug");
            }
        });
    }

    @Subscribe(sticky = true)
    public void onReceivedMusicType(OnClickMusicType onClickMusicType) {
        musicTypeModel = onClickMusicType.getMusicTypeModel();
    }

    @Override
    public void onClick(View v) {
        TopSongModel topSongModel = (TopSongModel) v.getTag();
        //// TODO: 7/28/2017 ????? dùng như thế nào? tại sao nó lại lấy đúng models mình cần
        SeekBar sbMiniPlayer = (SeekBar) getActivity().findViewById(R.id.sb_mini_player);
        MusicManager.loadSearchSong(topSongModel, getContext(), sbMiniPlayer);

        this.setDataForMiniPlayer(topSongModel);

    }

    private void setDataForMiniPlayer(final TopSongModel topSongModel) {
        final RelativeLayout rlMiniPlayerContent = (RelativeLayout) getActivity().findViewById(R.id.rl_mini_player_content);
        rlMiniPlayerContent.setVisibility(View.VISIBLE);

        final ImageView ivImageMiniPlayer = (ImageView) getActivity().findViewById(R.id.iv_image_mini_player);
        ImageView ivPlayMiniPlayer = (ImageView) getActivity().findViewById(R.id.iv_play_mini_player);
        TextView tvNameMiniPlayer = (TextView) getActivity().findViewById(R.id.tv_name_mini_player);
        TextView tvArtistMiniPlayer = (TextView) getActivity().findViewById(R.id.tv_artist_mini_player);

        tvNameMiniPlayer.setText(topSongModel.getName());
        tvArtistMiniPlayer.setText(topSongModel.getArtist());
        Picasso.with(getContext()).load(topSongModel.getImage()).transform(new CropCircleTransformation()).into(ivImageMiniPlayer);

        //Next fragment when tap on mini player with Data(EventBus)
        rlMiniPlayerContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlMiniPlayerContent.setVisibility(View.GONE);
                ScreenManager.openFragment(getActivity().getSupportFragmentManager(), new MiniPlayerFragment(), R.id.layout_container);

                EventBus.getDefault().postSticky(new OnClickTopSong(topSongModel));
            }
        });

        //TODO: Rotate image with rotate.xml in res/anim
        ivPlayMiniPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRotating = !isRotating;
                if (isRotating) {
                    rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
                    rotation.setFillAfter(true);
                    ivImageMiniPlayer.startAnimation(rotation);
                    isRotating = true;
                } else ivImageMiniPlayer.clearAnimation();
                MusicManager.playPause();
            }
        });
    }
}
