package com.example.customviewapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnCloseListener
import android.widget.Toast

class SearchFragment : BaseFragment() {

    companion object {
        private val TAG = SearchFragment::class.java.getSimpleName()
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Search Fragment")
        setIcon(null)
        resetToolBarState()
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        Log.d(TAG, "onCreateView()")
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d(TAG, "onCreateOptionsMenu()")
        setNavigationIcon(null)
        inflater.inflate(R.menu.search_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val searchMenuItem = menu.findItem(R.id.action_search)
        val searchView : SearchView = searchMenuItem.getActionView() as SearchView
        searchView.setQueryHint("Query Hint Search . . .")
        searchView.setMaxWidth(Int.MAX_VALUE)
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE)
        //searchView.setImageResource(R.drawable.ic_clear) // Optional: set custom icon for clear button
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText : String) : Boolean {
                Log.d(TAG, "onQueryTextChange $newText")
                return true
            }

            override fun onQueryTextSubmit(query : String) : Boolean {
                Log.d(TAG, "onQueryTextSubmit $query")
                return true
            }
        })
        searchView.setOnCloseListener(object : OnCloseListener {
            override fun onClose(): Boolean {
                Log.d(TAG, "onClose")
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        if (item.itemId == R.id.action_search) {
            Log.d(TAG, "Search View selected")
            Toast.makeText(requireContext(), "Search View selected", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
