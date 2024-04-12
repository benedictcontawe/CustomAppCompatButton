package com.example.customviewapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.customviewapp.databinding.MainBinder
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    companion object {
        private val TAG = MainActivity::class.java.getSimpleName()
    }

    private var binder : MainBinder? = null
    private val viewModel : MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState : Bundle?) {
        binder = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binder?.setLifecycleOwner(this@MainActivity)
        super.onCreate(savedInstanceState)
        setSupportActionBar(binder?.include?.toolbar)
        binder?.include?.toolbar?.setTitle(TAG)
        //binder?.include?.toolbar?.setNavigationIcon()
        //binder?.include?.toolbar?.setNavigationOnClickListener()
        binder?.include?.toolbar?.getMenu()?.clear()
        //binder?.include?.toolbar?.inflateMenu(R.menu.delete_menu)
        binder?.bottomNavigationView?.setOnItemSelectedListener(this@MainActivity)
    }

    override fun onCreateOptionsMenu(menu : Menu?) : Boolean {
        //getMenuInflater().inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        /*
        when (item.itemId) {
            R.id.item1 -> {
                Log.d(TAG, "Item 1 selected")
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.item2 -> {
                Log.d(TAG, "Item 2 selected")
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.item3 -> {
                Log.d(TAG, "Item 3 selected")
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.subitem1 -> {
                Log.d(TAG, "Sub Item 1 selected")
                Toast.makeText(this, "Sub Item 1 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.subitem2 -> {
                Log.d(TAG, "Sub Item 2 selected")
                Toast.makeText(this, "Sub Item 2 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.subitem3 -> {
                Log.d(TAG, "Sub Item 3 selected (Options Menu Disabled)")
                Toast.makeText(this, "Sub Item 3 selected (Options Menu Disabled)", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        */
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(menu : MenuItem): Boolean {
        return if (menu.getItemId() == R.id.home) {
            Log.d(TAG, "onNavigationItemSelected home")
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, HomeFragment())
                .commitNow()
            true
        } else if (menu.getItemId() == R.id.search) {
            Log.d(TAG, "onNavigationItemSelected search")
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, SearchFragment())
                .commitNow()
            true
        } else if (menu.getItemId() == R.id.edit) {
            Log.d(TAG, "onNavigationItemSelected edit")
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, EditFragment())
                .commitNow()
            true
        } else if (menu.getItemId() == R.id.delete) {
            Log.d(TAG, "onNavigationItemSelected delete")
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, DeleteFragment())
                .commitNow()
            true
        } else if (menu.getItemId() == R.id.utilities) {
            Log.d(TAG, "onNavigationItemSelected utilities")
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, UtilitiesFragment())
                .commitNow()
            true
        } else {
            Log.d(TAG, "onNavigationItemSelected default")
            Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show()
            false
        }
    }

    public fun getToolbar() : Toolbar? {
        return binder?.include?.toolbar
    }

    public fun getTxtToolbar() : TextView? {
        return binder?.include?.textToolbar
    }

    public fun getPlaceHolder() : LinearLayout? {
        return binder?.include?.placeHolder
    }
}