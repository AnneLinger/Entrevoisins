package com.openclassrooms.entrevoisins.ui.neighbour_list.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.activities.AddNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.adapters.ListNeighbourPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    //ViewPager adapter
    ListNeighbourPagerAdapter mPagerAdapter;

    //TAG for lifecycle (logs)
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        //ViewPager configuration
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        Log.d(TAG, "onCreate:  Anne");
    }

    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);

        Log.d(TAG, "addNeighbour:  Anne");
    }
}
