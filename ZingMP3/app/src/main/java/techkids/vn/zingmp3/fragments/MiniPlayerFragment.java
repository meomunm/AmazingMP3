package techkids.vn.zingmp3.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import techkids.vn.zingmp3.R;
import techkids.vn.zingmp3.databases.TopSongModel;
import techkids.vn.zingmp3.events.OnClickTopSong;

/**
 * Created by ADMIN on 7/28/2017.
 */

public class MiniPlayerFragment extends Fragment {
    public static final String TAG = MiniPlayerFragment.class.toString();
    private TopSongModel topSongModel;

    public MiniPlayerFragment() {

    }

    @BindView(R.id.iv_back_fragment_mini_player)
    ImageView ivBackMiniPlayerFragment;
    @BindView(R.id.iv_download_fragment_mini_player)
    ImageView ivDownloadMiniPlayerFragment;
    @BindView(R.id.tv_name_fragment_mini_player)
    TextView tvNameMiniPlayerFragment;
    @BindView(R.id.tv_artist_fragment_mini_player)
    TextView tvArtistMiniPlayerFragment;
    @BindView(R.id.iv_big_image_fragment_mini_player)
    ImageView ivBigImageFragmentMiniPlayer;
    @BindView(R.id.iv_small_image_fragment_mini_player)
    ImageView ivSmallImageFragmentMiniPlayer;
    @BindView(R.id.sb_fragment_mini_player)
    SeekBar sbMiniPlayerFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mini_player, container, false);
        this.setupUI(view);
        ivBackMiniPlayerFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                } else
                    Toast.makeText(getContext(), "Has an bug in MiniPlayerFragment.class", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        tvNameMiniPlayerFragment.setText(topSongModel.getName());
        tvArtistMiniPlayerFragment.setText(topSongModel.getArtist());

        Picasso.with(getContext()).load(topSongModel.getImage()).transform(new BlurTransformation(getContext())).into(ivBigImageFragmentMiniPlayer);
        Picasso.with(getContext()).load(topSongModel.getImage()).into(ivSmallImageFragmentMiniPlayer);
        sbMiniPlayerFragment.setPadding(0,0,0,0);

    }

    @Subscribe(sticky = true)
    public void onReceivedMusicType(OnClickTopSong onClickTopSong) {
        topSongModel = onClickTopSong.getTopSongModel();
    }


}
