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

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import org.jetbrains.annotations.NotNull;

public class UtilitiesFragment extends BaseFragment {

    private static final String TAG = UtilitiesFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(null);
        setIcon(null);
        resetToolBarState();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView()");
        return inflater.inflate(R.layout.fragment_utilities, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG,"onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, MenuInflater inflater) {
        Log.d(TAG,"onCreateOptionsMenu()");
        inflater.inflate(R.menu.utilities_menu, menu);
        setNavigationIcon(R.drawable.ic_arrow_back);
        setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPlaceHolderVisibility(View.VISIBLE);
                reCreateOptionsMenu();
            }
        });
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        if (mainViewModel.getUtilityIndicator()) {
            menu.findItem(R.id.on_off_menu).setEnabled(true);
            menu.findItem(R.id.off_menu).setEnabled(true);
            menu.findItem(R.id.on_menu).setEnabled(false);
            menu.findItem(R.id.on_off_menu).setTitle(getResources().getString(R.string.on).toUpperCase());

        } else {
            menu.findItem(R.id.on_off_menu).setEnabled(false);
            menu.findItem(R.id.off_menu).setEnabled(false);
            menu.findItem(R.id.on_menu).setEnabled(true);
            menu.findItem(R.id.on_off_menu).setTitle(getResources().getString(R.string.off).toUpperCase());
        }
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.on_menu) {
            Log.d(TAG, "On Menu selected");
            mainViewModel.setUtilitiyIndicator(true);
            reCreateOptionsMenu();
            return true;
        } else if (item.getItemId() == R.id.off_menu) {
            Log.d(TAG, "Off Menu selected");
            mainViewModel.setUtilitiyIndicator(false);
            reCreateOptionsMenu();
            return true;
        } else if (item.getItemId() == R.id.exit_menu) {
            Log.d(TAG, "Exit Menu selected");
            ActivityCompat.finishAffinity(getMainActivity());
            Toast.makeText(getContext(), "Exit Menu selected", Toast.LENGTH_SHORT).show();
            System.exit(0);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        setPlaceHolderVisibility(View.GONE);
    }
}