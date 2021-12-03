package com.openclassrooms.entrevoisins.ui.neighbour_list.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NeighbourDetailsActivity extends AppCompatActivity {

    //UI components
    @BindView(R.id.tb_details)
    Toolbar mToolbar;
    @BindView(R.id.im_avatar)
    ImageView mAvatar;
    @BindView(R.id.tv_name)
    TextView mName;
    @BindView(R.id.bt_favorite)
    FloatingActionButton mFavoriteButton;
    @BindView(R.id.tv_little_name)
    TextView mLittleName;
    @BindView(R.id.tv_address)
    TextView mAddress;
    @BindView(R.id.tv_phone_number)
    TextView mPhoneNumber;
    @BindView(R.id.tv_social)
    TextView mSocial;
    @BindView(R.id.tv_about_text)
    TextView mAboutText;

    private Neighbour mNeighbour;
    private final NeighbourApiService mApiService = DI.getNeighbourApiService();
    private final List<Neighbour> mFavoriteNeighboursList = mApiService.getNeighbours(true);

    //Key for Gson
    private final String NEIGHBOUR = "NEIGHBOUR";

    //TAG for lifecycle (logs)
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);

        //Toolbar configuration
        this.configureToolbar();

        //Gson to recover mNeighbour
        Gson gson = new Gson();
        mNeighbour = gson.fromJson(getIntent().getStringExtra(NEIGHBOUR), Neighbour.class);

        //Neighbour methods on UI components to recover details
        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(mAvatar);
        mName.setText(mNeighbour.getName());
        mLittleName.setText(mNeighbour.getName());
        mAddress.setText(mNeighbour.getAddress());
        mPhoneNumber.setText(mNeighbour.getPhoneNumber());
        mSocial.setText(getString(R.string.social_text) + mNeighbour.getName().toLowerCase(Locale.ROOT));
        mAboutText.setText(mNeighbour.getAboutMe());

        //Check if mNeighbour is a favorite or no to define mFavoriteButton
        if (mFavoriteNeighboursList.contains(mNeighbour)) {
            mFavoriteButton.setImageDrawable(getDrawable(R.drawable.ic_star_yellow_24dp));
        }

        Log.d(TAG, "onCreate:  Anne");
    }

    //Toolbar configuration
    private void configureToolbar() {
        //Toolbar as ActionBar
        setSupportActionBar(mToolbar);

        //Back button enable
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Title hidden
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    //FavoriteButton configuration
    @OnClick(R.id.bt_favorite)
    public void addOrRemoveAFavoriteNeighbour() {
        if (mFavoriteNeighboursList.contains(mNeighbour)) {
            mApiService.deleteNeighbour(mNeighbour, true);
            mFavoriteButton.setImageDrawable(getDrawable(R.drawable.ic_star_border_yellow_24dp));
            Toast toast = Toast.makeText(this, getString(R.string.remove_from_favorites), Toast.LENGTH_LONG);
            toast.show();
        } else {
            mApiService.addNeighbour(mNeighbour, true);
            mFavoriteButton.setImageDrawable(getDrawable(R.drawable.ic_star_yellow_24dp));
            Toast toast = Toast.makeText(this, getString(R.string.add_to_favorites), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}