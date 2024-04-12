package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home Fragment");
        setIcon(null);
        resetToolBarState();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView()");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, MenuInflater inflater) {
        Log.d(TAG,"onCreateOptionsMenu()");
        setNavigationIcon(null);
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            Log.d(TAG, "Item 1 selected");
            Toast.makeText(getContext(), "Item 1 selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.item2) {
            Log.d(TAG, "Item 2 selected");
            Toast.makeText(getContext(), "Item 2 selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.item3) {
            Log.d(TAG, "Item 3 selected");
            Toast.makeText(getContext(), "Item 3 selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.subitem1) {
            Log.d(TAG, "Sub Item 1 selected");
            Toast.makeText(getContext(), "Sub Item 1 selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.subitem2) {
            Log.d(TAG, "Sub Item 2 selected");
            Toast.makeText(getContext(), "Sub Item 2 selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.subitem3) {
            Log.d(TAG, "Sub Item 3 selected (Options Menu Disabled)");
            Toast.makeText(getContext(), "Sub Item 3 selected (Options Menu Disabled)", Toast.LENGTH_SHORT).show();
            disableOptionsMenu();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated()");
    }
}