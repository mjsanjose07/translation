package com.example.maryjoy.northerdialecttranslator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /*
    public static ArrayList<String> englishWords = new ArrayList<>();
    public static ArrayList<String> warayWords = new ArrayList<>();
    public static ArrayList<String> pangalatokWords = new ArrayList<>();
    public static ArrayList<String> hiligaynonWords = new ArrayList<>();
    public static ArrayList<String> ilocanoWords = new ArrayList<>();

    private static HashMap<String, String> englishHash = new HashMap<>();
    private static HashMap<String, String> warayHash = new HashMap<>();
    private static HashMap<String, String> pangalatokHash = new HashMap<>();
    private static HashMap<String, String> hiligaynonHash = new HashMap<>();
    private static HashMap<String, String> ilocanoHash = new HashMap<>();
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               Toast.makeText(getBaseContext(),"Follow the arrow!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar1, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_phrase) {
            Toast.makeText(this, "Under Maintenance! Prepare for the next update..", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_translate) {
            translateFragment Tf = new translateFragment();
            FragmentManager mngr = getSupportFragmentManager();
            mngr.beginTransaction()
                    .replace(R.id.mainLayout, Tf, Tf.getTag())
                    .commit();
        } else if (id == R.id.nav_about) {
            aboutFragment Af = new aboutFragment();
            FragmentManager mngr = getSupportFragmentManager();
            mngr.beginTransaction()
                    .replace(R.id.mainLayout, Af, Af.getTag())
                    .commit();
        } else if (id == R.id.nav_help) {
            helpFragment Hf = new helpFragment();
            FragmentManager mngr = getSupportFragmentManager();
            mngr.beginTransaction()
                    .replace(R.id.mainLayout, Hf, Hf.getTag())
                    .commit();
        }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

    }
/*
    public static HashMap getEnglishHashMap(){



        return englishHash;
    }

    public static HashMap getWarayHashMap(){

        return warayHash;
    }

    public static HashMap getPangalatokHashMap(){

        return pangalatokHash;
    }

    public static HashMap getHiligaynonHashMap(){

        return hiligaynonHash;
    }

    public static HashMap getIlocanoHashMap(){

        return ilocanoHash;
    }

    public static ArrayList getEnglishWords()
    {
        return englishWords;
    }

    public static ArrayList getWarayWords(){

        return warayWords;
    }
    public static ArrayList getPangalatokWords(){
        return pangalatokWords;
    }

    public static ArrayList getHiligaynonWords(){

        return hiligaynonWords;
    }

    public static ArrayList getIlocanoWords(){

        return ilocanoWords;
    }

*/
}
