package com.example.posterapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {
    private List<Poster> posterList = new ArrayList<>();  // Avoid NullPointerException
    private PostersListener postersListener;

    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        if (posterList != null) {
            this.posterList = posterList;
        }
        this.postersListener = postersListener;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    public List<Poster> getSelectedPosters() {
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : this.posterList) {
            if (poster.isSelected) {
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }

    class PosterViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layoutPosters;
        View viewBackground;
        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutposters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        void bindPosters(final Poster poster) {
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);

            // Set initial state based on selection
            if (poster.isSelected) {
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }

            layoutPosters.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    poster.isSelected = !poster.isSelected; // Toggle selection state

                    if (poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                    } else {
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                    }

                    // Notify listener of selection state
                    if (postersListener != null) {
                        postersListener.onPosterAction(!getSelectedPosters().isEmpty());
                    }
                }
            });
        }
    }
}
