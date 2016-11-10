package com.morpion.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.morpion.R;
import com.morpion.game.JeuMorpion9cases;

public class GameActivity extends AppCompatActivity {

    private final JeuMorpion9cases game = new JeuMorpion9cases();
    private boolean isTurnOfPlayer1 = true;
    private String joueur1;
    private String joueur2;
    private TextView currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String p1 = getIntent().getExtras().getString("joueur1");
        String p2 = getIntent().getExtras().getString("joueur2");

        if(p1 == null || p1.equals("")) p1 = "Joueur 1";
        if(p2 == null || p2.equals("")) p2 = "Joueur 2";

        setJoueur1(p1);
        setJoueur2(p2);

        // Initialize par le joueur 1 qui commence en premier
        currentPlayer = (TextView) findViewById(R.id.label_name);
        currentPlayer.setText(getJoueur1());
    }

    public void choose(View view)
    {
        final ImageView image = (ImageView) view;
        final TextView label_win = (TextView) findViewById(R.id.label_win);
        int id = Integer.parseInt(view.getTag().toString()) - 1;

        if(isTurnOfPlayer1)
        {
            System.out.println("Joueur 1 choisi la case : "+(id)); // log

            image.setImageResource(R.drawable.ic_joueur1);
            game.setJoueur1(id);

            isTurnOfPlayer1 = false;

            if(game.isEgalite())
            {
                label_win.setText("Égalité !");

            }
            else if (game.isJoueur1Win()) {
                System.out.println("Le gagnant est le joueur 1");
                label_win.setText("Le gagnant est le joueur 1");
            }
            else
            {
                currentPlayer.setText(getJoueur2());
            }
        }
        else
        {
            System.out.println("Joueur 2 choisi la case : "+(id)); // log

            currentPlayer.setText(getJoueur2());
            image.setImageResource(R.drawable.ic_joueur2);
            game.setJoueur2(id);

            isTurnOfPlayer1 = true;

            if(game.isEgalite())
            {
                label_win.setText("Égalité !");

            }
            else if (game.isJoueur2Win()) {
                System.out.println("Le gagnant est le joueur 2");
                label_win.setText("Le gagnant est le joueur 2");
            }
            else
            {
                currentPlayer.setText(getJoueur1());
            }
        }
    }

    public void restart(View view) {
        final TextView label_win = (TextView) findViewById(R.id.label_win);
        label_win.setText("");

        isTurnOfPlayer1 = true;
        game.reset();

        final GridLayout grid = (GridLayout) findViewById(R.id.grid_game);
        for (int i = 0; i < 9; i++) {
            ImageView image = (ImageView) grid.getChildAt(i);
            image.setImageResource(0);
        }
    }

    public String getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(String joueur1) {
        this.joueur1 = joueur1;
    }

    public String getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(String joueur2) {
        this.joueur2 = joueur2;
    }
}
