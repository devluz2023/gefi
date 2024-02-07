package br.com.cptm.gefimobile.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

import br.com.cptm.gefimobile.R;
import br.com.cptm.gefimobile.models.LoginResponse;
import br.com.cptm.gefimobile.ui.adapters.ViewPagerAdapter;
import br.com.cptm.gefimobile.util.SessionManager;


public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);


        if(!sessionManager.isAuth()){
            intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabBar);
        ViewPager viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter pageAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());

        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);




        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String userName = sessionManager.getLoginResponse().getLogin();
        menu.findItem(R.id.menu_user).setTitle(userName);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.menu) {
            return true;
        }else if(id == R.id.menu_user){

        }else if(id == R.id.menu_about){
            intent = new Intent(MainActivity.this, SobreActivity.class);
            startActivity(intent);

        }else if(id == R.id.menu_settings){
            //Alterar a senha
            intent = new Intent(MainActivity.this, TrocaSenhaActivity.class);
            startActivity(intent);

        }else if(id == R.id.menu_logout){
            //volta para a pagina de login
            sessionManager.logout();
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }



        return super.onOptionsItemSelected(item);
    }
}

