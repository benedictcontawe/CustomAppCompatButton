package com.example.customviewapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.customviewapp.databinding.UtilitiesBinder

class UtilitiesFragment : BaseFragment {

    companion object {
        private val TAG = UtilitiesFragment::class.java.getSimpleName()
    }

    constructor() {

    }

    private var binder : UtilitiesBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setTitle(null);
        setIcon(null);
        resetToolBarState();
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_utilities,container,false)
        binder?.composeView?.setContent(content = {
            Box (
                modifier = Modifier.fillMaxSize().border(2.dp, Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Hello Compose View!")
            }
        })
        return binder?.getRoot()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d(TAG,"onCreateOptionsMenu()");
        inflater.inflate(R.menu.utilities_menu, menu);
        setNavigationIcon(R.drawable.ic_arrow_back);
        setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View?) {
                setPlaceHolderVisibility(View.VISIBLE);
                reCreateOptionsMenu();
            }
        });
        super.onCreateOptionsMenu(menu,inflater);
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        if (mainViewModel.utilitiyIndicator == true) {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == R.id.on_menu) {
            Log.d(TAG, "On Menu selected");
            mainViewModel.utilitiyIndicator = true
            reCreateOptionsMenu();
            return true;
        } else if (item.getItemId() == R.id.off_menu) {
            Log.d(TAG, "Off Menu selected");
            mainViewModel.utilitiyIndicator = false
            reCreateOptionsMenu();
            return true;
        } else if (item.getItemId() == R.id.exit_menu) {
            Log.d(TAG, "Exit Menu selected");
            ActivityCompat.finishAffinity(requireActivity());
            Toast.makeText(getContext(), "Exit Menu selected", Toast.LENGTH_SHORT).show();
            System.exit(0);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    override fun onPause() {
        super.onPause()
        setPlaceHolderVisibility(View.GONE)
    }
}