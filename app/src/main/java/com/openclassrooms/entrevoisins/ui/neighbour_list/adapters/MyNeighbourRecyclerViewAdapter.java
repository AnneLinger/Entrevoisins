package com.openclassrooms.entrevoisins.ui.neighbour_list.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.activities.NeighbourDetailsActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbourList;
    private final String NEIGHBOUR = "NEIGHBOUR";

    //Constructor
    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbourList = items;
    }

    //Create ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    //ViewHolder configuration
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        //ViewHolder filled with neighbour details
        Neighbour neighbour = mNeighbourList.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        //Button call a new DeleteNeighbourEvent
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });

        //itemView navigate to NeighbourDetailsActivity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gson gson = new Gson();
                String neighbourJson = gson.toJson(neighbour);

                Intent intent = new Intent(view.getContext(), NeighbourDetailsActivity.class);

                intent.putExtra(NEIGHBOUR, neighbourJson);

                view.getContext().startActivity(intent);
            }
        });
    }

    //Return the total count of items
    @Override
    public int getItemCount() {
        return mNeighbourList.size();
    }

    //Class ViewHolder for UI components
    public class ViewHolder extends RecyclerView.ViewHolder {

        //UI components
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        //Constructor
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
