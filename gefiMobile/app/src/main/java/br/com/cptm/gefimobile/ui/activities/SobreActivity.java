package br.com.cptm.gefimobile.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.cptm.gefimobile.R;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        setTitle("Sobre");
    }
}