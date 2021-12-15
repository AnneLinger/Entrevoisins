package com.openclassrooms.entrevoisins.ui.neighbour_list.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.adapters.MyNeighbourRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class NeighbourFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private static final NeighbourApiService mApiService = DI.getNeighbourApiService();
    private static List<Neighbour> mNeighbourList;

    //Fragment initialization parameter
    private static final String IS_FAVORITE = "IS_FAVORITE";

    //TAG for lifecycle (logs)
    private final String TAG = getClass().getSimpleName();

    /**
     * Create and return a new instance
     *
     * @param isFavorite
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(boolean isFavorite) {
        NeighbourFragment fragment = new NeighbourFragment();

        //Set arguments
        Bundle args = new Bundle();
        args.putBoolean(IS_FAVORITE, isFavorite);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate:  Anne");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();

        //RecyclerView configuration
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        initList();

        return view;
    }

    /**
     * Init the list of neighbours
     */
    private void initList() {
        //Recover the neighbour list or the favorite neighbours
        mNeighbourList = mApiService.getNeighbours(getArguments().getBoolean(IS_FAVORITE));

        //Items of the RecyclerView filled with mNeighbourList
        mRecyclerView.setAdapter((new MyNeighbourRecyclerViewAdapter(mNeighbourList)));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();

        Log.d(TAG, "onResume:  Anne");
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();

        Log.d(TAG, "onStart:  Anne");
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();

        Log.d(TAG, "onStop:  Anne");
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour, false);
        mApiService.deleteNeighbour(event.neighbour, true);
        initList();
    }
}
