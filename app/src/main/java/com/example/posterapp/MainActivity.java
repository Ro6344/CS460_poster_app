package com.example.posterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PostersListener {

    private Button buttonAddToWatchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView postersRecyclerView = findViewById(R.id.posterRecyclerView);
        buttonAddToWatchList = findViewById(R.id.buttonAddToWatchList);

        //prepare data
        List<Poster> posterList = new ArrayList<>();

        Poster barbie = new Poster();
        barbie.image =R.drawable.barbie;
        barbie.name = "Barbie";
        barbie.createdBy = "Greta Gerwig";
        barbie.rating = 4f;
        barbie.story = "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.";
        posterList.add(barbie);

        Poster coraline = new Poster();
        coraline.image =R.drawable.coraline;
        coraline.name = "Coraline";
        coraline.createdBy = "Henry Selick";
        coraline.rating = 5f;
        coraline.story = "Coraline moves into the PinkPalace...";
        posterList.add(coraline);

        Poster spiderman = new Poster();
        spiderman.image = R.drawable.spiderman;
        spiderman.name = "Spiderman";
        spiderman.createdBy = "Bob Persichetti";
        spiderman.rating = 5f;
        spiderman.story = "Bitten by a radioactive spider in the subway, Brooklyn teenager Miles Morales suddenly develops mysterious powers that transform him into the one and only Spider-Man.";
        posterList.add(spiderman);

        Poster conjuring = new Poster();
        conjuring.image = R.drawable.theconjuring;
        conjuring.name = "The Conjuring";
        conjuring.createdBy = "James Wan";
        conjuring.rating = 3f;
        conjuring.story = "In 1970, paranormal investigators and demonologists Lorraine and Ed Warren are summoned to the home of Carolyn and Roger";
        posterList.add(conjuring);

        Poster ponyo = new Poster();
        ponyo.image = R.drawable.ponyo;
        ponyo.name = "Ponyo";
        ponyo.createdBy = "Hayao Miyasaki";
        ponyo.rating = 5f;
        ponyo.story = "Using a forbidden excursion to see the surface world, a goldfish princess encounters a human boy named Sosuke, who gives her the name Ponyo.";
        posterList.add(ponyo);

        Poster anabelle = new Poster();
        anabelle.image =R.drawable.anabelle;
        anabelle.name = "Anabelle";
        anabelle.createdBy = "John R. Leonetti";
        anabelle.rating = 3f;
        anabelle.story = "John Form thinks he's found the perfect gift for his expectant wife, Mia: a vintage doll in a beautiful white dress. However, the couple's delight doesn't last long";
        posterList.add(anabelle);

        Poster corpseBride = new Poster();
        corpseBride.image =R.drawable.corpsebride;
        corpseBride.name = "Corpse Bride";
        corpseBride.createdBy = "Tim Burton";
        corpseBride.rating = 5f;
        corpseBride.story = "Victor and Victoria's families have arranged their marriage. Though they like each other, Victor is nervous about the ceremony.";
        posterList.add(corpseBride);

        Poster endgame = new Poster();
        endgame.image =R.drawable.endgame;
        endgame.name = "End Game";
        endgame.createdBy = "Anthony Russo";
        endgame.rating = 3f;
        endgame.story = "The fourth installment in the Avengers saga is the culmination of 22 interconnected Marvel films and the climax of a journey.";
        posterList.add(endgame);

        Poster whiplash = new Poster();
        whiplash.image =R.drawable.whiplash;
        whiplash.name = "Whiplash";
        whiplash.createdBy = "Damien Chazelle";
        whiplash.rating = 4f;
        whiplash.story = "Andrew Neiman is an ambitious young jazz drummer, in pursuit of rising to the top of his elite music conservatory.";
        posterList.add(whiplash);

        Poster hiddenFigures = new Poster();
        hiddenFigures.image =R.drawable.hiddenfigures;
        hiddenFigures.name = "Hidden Figures";
        hiddenFigures.createdBy = "Theodore Melfi";
        hiddenFigures.rating = 5f;
        hiddenFigures.story = "Three brilliant African-American women at NASA -- Katherine Johnson, Dorothy Vaughan and Mary Jackson -- serve as the brains behind one of the greatest operations in history: the launch of astronaut John Glenn into orbit.";
        posterList.add(hiddenFigures);



        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(posterAdapter);

        buttonAddToWatchList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Poster> selectPosters = posterAdapter.getSelectedPosters();
                StringBuilder posterNames = new StringBuilder();
                for (int i = 0; i < selectPosters.size(); i++) {
                    if(i == 0) {
                        posterNames.append(selectPosters.get(i).name);
                    } else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this,posterNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected) {
            buttonAddToWatchList.setVisibility(View.VISIBLE);
        } else {
            buttonAddToWatchList.setVisibility(View.GONE);
        }
    }
}