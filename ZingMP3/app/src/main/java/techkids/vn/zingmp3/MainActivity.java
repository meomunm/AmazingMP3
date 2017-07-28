package techkids.vn.zingmp3;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import techkids.vn.zingmp3.adapters.PagerAdapter;
import techkids.vn.zingmp3.managers.MusicManager;

public class MainActivity extends AppCompatActivity {
    private PagerAdapter pagerAdapter;
    private TabLayout tlTab;
    private ViewPager vpContent;

    private RelativeLayout rlMiniPlayerContent;
    private SeekBar skMiniPlayer;

    private final String TAG = MainActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlMiniPlayerContent = (RelativeLayout) findViewById(R.id.rl_mini_player_content);
        rlMiniPlayerContent.setVisibility(View.GONE);
        skMiniPlayer = (SeekBar) findViewById(R.id.sb_mini_player);
        skMiniPlayer.setPadding(0, 0, 0, 0);

        this.setUI();
    }


    private void setUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        vpContent = (ViewPager) findViewById(R.id.vp_content);
        vpContent.setAdapter(pagerAdapter);

        tlTab = (TabLayout) findViewById(R.id.tl_tab);
        tlTab.setupWithViewPager(vpContent);


        vpContent.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlTab));
        tlTab.setTabGravity(TabLayout.GRAVITY_FILL);

        this.setTabLayout(tlTab);

        tlTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(100);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
            }
        });
    }

    private void setTabLayout(TabLayout tab) {
        tab.getTabAt(0).setIcon(R.drawable.ic_library_music_black_24dp);
        tab.getTabAt(1).setIcon(R.drawable.ic_favorite_black_24dp);
        tab.getTabAt(2).setIcon(R.drawable.ic_file_download_black_24dp);
        tab.getTabAt(0).getIcon().setAlpha(255);
        tab.getTabAt(1).getIcon().setAlpha(100);
        tab.getTabAt(2).getIcon().setAlpha(100);
    }


}
