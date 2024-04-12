package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainViewModel mainViewModel;
    private Toolbar toolbar;
    private TextView txtToolbar;
    private LinearLayout placeHolder;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Main Activity");
        //toolbar.setNavigationIcon();
        //toolbar.setNavigationOnClickListener();
        toolbar.getMenu().clear();
        //toolbar.inflateMenu(R.menu.delete_menu);

        txtToolbar = (TextView)findViewById(R.id.text_toolbar);
        placeHolder = (LinearLayout)findViewById(R.id.place_holder);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { Log.d(TAG,"onCreateOptionsMenu()");
        //getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        switch (item.getItemId()) {
            case R.id.item1:
                Log.d(TAG,"Item 1 selected");
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Log.d(TAG,"Item 2 selected");
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Log.d(TAG,"Item 3 selected");
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Log.d(TAG,"Sub Item 1 selected");
                Toast.makeText(this, "Sub Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Log.d(TAG,"Sub Item 2 selected");
                Toast.makeText(this, "Sub Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem3:
                Log.d(TAG,"Sub Item 3 selected (Options Menu Disabled)");
                Toast.makeText(this, "Sub Item 3 selected (Options Menu Disabled)", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        */
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Log.d(TAG,"onNavigationItemSelected home");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout,new HomeFragment())
                    .commitNow();
            return true;
        } else if (item.getItemId() == R.id.search) {
            Log.d(TAG,"onNavigationItemSelected search");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout,new SearchFragment())
                    .commitNow();
            return true;
        } else if (item.getItemId() == R.id.edit) {
            Log.d(TAG,"onNavigationItemSelected edit");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout,new EditFragment())
                    .commitNow();
            return true;
        } else if (item.getItemId() == R.id.delete) {
            Log.d(TAG,"onNavigationItemSelected delete");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout,new DeleteFragment())
                    .commitNow();
            return true;
        } else if (item.getItemId() == R.id.utilities) {
            Log.d(TAG,"onNavigationItemSelected utilities");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout,new UtilitiesFragment())
                    .commitNow();
            return true;
        } else {
            Log.d(TAG,"onNavigationItemSelected default");
            Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public TextView getTxtToolbar() {
        return txtToolbar;
    }

    public LinearLayout getPlaceHolder() {
        return placeHolder;
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG,"onBackPressed()");
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}