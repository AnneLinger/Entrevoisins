package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailsActivity extends AppCompatActivity{

    //TAG for lifecycle (logs)
    private final String TAG = getClass().getSimpleName();

    //Key for Gson
    private final String NEIGHBOUR = "NEIGHBOUR";

    //Object
    private Neighbour mNeighbour;

    //Social String
    private final String mSocialText = "www.facebook.fr/";

    //UI components
    @BindView(R.id.tb_details)
    Toolbar mToolbar;
    @BindView(R.id.im_avatar)
    ImageView mAvatar;
    @BindView(R.id.tv_name)
    TextView mName;
    @BindView(R.id.bt_favourite)
    FloatingActionButton mFavouriteButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        Log.d(TAG, "onCreate:  Anne");

        //UI connexion
        ButterKnife.bind(this);

        //Toolbar configuration
        this.configureToolbar();

        //Gson to recover object Neighbour
        Gson gson = new Gson();
        mNeighbour = gson.fromJson(getIntent().getStringExtra(NEIGHBOUR), Neighbour.class);

        //Neighbour methods on UI components to recover details
        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(mAvatar);
        mName.setText(mNeighbour.getName());
        mLittleName.setText(mNeighbour.getName());
        mAddress.setText(mNeighbour.getAddress());
        mPhoneNumber.setText(mNeighbour.getPhoneNumber());
        mSocial.setText(mSocialText+mNeighbour.getName().toLowerCase(Locale.ROOT));
        mAboutText.setText(mNeighbour.getAboutMe());
    }

    //Method for toolbar configuration
    private void configureToolbar(){
        //Toolbar as ActionBar
        setSupportActionBar(mToolbar);

        //Back button enable
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Title hidden
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}