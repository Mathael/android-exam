package com.morpion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.morpion.activities.GameActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickPlayGame(View view) {
        final EditText viewJoueur1 = (EditText) findViewById(R.id.edittext_joueur1);
        final EditText viewJoueur2 = (EditText) findViewById(R.id.edittext_joueur2);

        String joueur1 = viewJoueur1.getText().toString();
        String joueur2 = viewJoueur2.getText().toString();

        final Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("joueur1", joueur1);
        intent.putExtra("joueur2", joueur2);
        startActivity(intent);
    }
}
