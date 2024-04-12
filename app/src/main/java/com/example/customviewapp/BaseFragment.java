package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

abstract public class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();
    private MainActivity mainActivity;
    protected MainViewModel mainViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableOptionsMenu();
        Log.d(TAG,"onCreate()");
        mainActivity = (MainActivity) getActivity(); //((AppCompatActivity)getActivity())
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    protected MainActivity getMainActivity() {
        return mainActivity;
    }

    protected void enableOptionsMenu() {
        setHasOptionsMenu(true);
    }

    protected void disableOptionsMenu() {
        setHasOptionsMenu(false);
    }

    protected void reCreateOptionsMenu() {
        //setHasOptionsMenu(false);
        //setHasOptionsMenu(true);
        getMainActivity().invalidateOptionsMenu();
    }

    protected void resetToolBarState() {
        setToolbarVisibility(View.GONE);
        getMainActivity().invalidateOptionsMenu();
    }

    protected void setToolbarVisibility(int visibility) {
        mainActivity.getTxtToolbar().setVisibility(visibility);
    }

    protected void setToolbarText(@Nullable String text) {
        mainActivity.getTxtToolbar().setText(text);
    }

    protected void setNavigationIcon(Integer icon) {
        mainActivity.getToolbar().setNavigationIcon(mainViewModel.getIcon(icon));
    }

    protected void setNavigationOnClickListener(@Nullable View.OnClickListener listener) {
        mainActivity.getToolbar().setNavigationOnClickListener(listener);
    }

    protected void setTitle(@Nullable String title) {
        mainActivity.getSupportActionBar().setTitle(title);
    }

    protected void setIcon(@Nullable Integer icon) {
        mainActivity.getSupportActionBar().setIcon(mainViewModel.getIcon(icon));
    }

    protected void setPlaceHolderVisibility(int visibility) {
        mainActivity.getPlaceHolder().setVisibility(visibility);
    }
}