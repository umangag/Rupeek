package com.umang.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by umangagarwal on 11,August,2019
 */
public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchListViewHolder> {

    private ArrayList<ModelClass.Locations> items;
    private CardClickListener cardClickListener;

    public SearchResultsAdapter(CardClickListener contactListener) {
        items = new ArrayList<>();
        this.cardClickListener = contactListener;
    }

    public void updateData(ArrayList<ModelClass.Locations> items) {
        this.items.clear();
        this.items = items;
        System.out.println("SearchResultsAdapter.updateData:::  "+items.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchResultsAdapter.SearchListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new SearchListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultsAdapter.SearchListViewHolder searchListViewHolder, int i) {
        searchListViewHolder.bindWithData(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class SearchListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image)
        ImageView imgView;

        @BindView(R.id.cityName)
        TextView cityName;

        @BindView(R.id.dates)
        TextView dates;

        @BindView(R.id.cardView)
        CardView cardView;

        public SearchListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.cardView) {
                cardClickListener.onCardClicked(getAdapterPosition());
            }
        }

        public void bindWithData(ModelClass.Locations items) {

            cityName.setText(items.place);
            dates.setText(items.date);

            Glide.with(imgView.getContext())
                    .load(items.url)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .into(imgView);

        }
    }

    public interface CardClickListener {

        void onCardClicked(int position);

    }
}
