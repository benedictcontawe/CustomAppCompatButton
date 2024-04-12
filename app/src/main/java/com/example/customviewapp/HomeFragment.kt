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
import androidx.databinding.DataBindingUtil
import com.example.customviewapp.databinding.HomeBinder

class HomeFragment : BaseFragment {

    companion object {
        private val TAG = HomeFragment::class.java.getSimpleName()
    }

    constructor() {

    }

    private var binder : HomeBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        return binder?.getRoot()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d(TAG, "onCreateOptionsMenu()")
        setNavigationIcon(null)
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        return if (item.getItemId() == R.id.item1) {
            Log.d(TAG, "Item 1 selected")
            Toast.makeText(context, "Item 1 selected", Toast.LENGTH_SHORT).show()
            true
        } else if (item.getItemId() == R.id.item2) {
            Log.d(TAG, "Item 2 selected")
            Toast.makeText(context, "Item 2 selected", Toast.LENGTH_SHORT).show()
            true
        } else if (item.getItemId() == R.id.item3) {
            Log.d(TAG, "Item 3 selected")
            Toast.makeText(context, "Item 3 selected", Toast.LENGTH_SHORT).show()
            true
        } else if (item.getItemId() == R.id.subitem1) {
            Log.d(TAG, "Sub Item 1 selected")
            Toast.makeText(context, "Sub Item 1 selected", Toast.LENGTH_SHORT).show()
            true
        } else if (item.getItemId() == R.id.subitem2) {
            Log.d(TAG, "Sub Item 2 selected")
            Toast.makeText(context, "Sub Item 2 selected", Toast.LENGTH_SHORT).show()
            true
        } else if (item.getItemId() == R.id.subitem3) {
            Log.d(TAG, "Sub Item 3 selected (Options Menu Disabled)")
            Toast.makeText(context, "Sub Item 3 selected (Options Menu Disabled)", Toast.LENGTH_SHORT).show()
            disableOptionsMenu()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}