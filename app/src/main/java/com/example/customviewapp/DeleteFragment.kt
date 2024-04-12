package com.example.customviewapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.customviewapp.databinding.DeleteBinder

class DeleteFragment : BaseFragment, OnClickListener {

    companion object {
        private val TAG = DeleteFragment::class.java.getSimpleName()
    }

    constructor() {

    }

    private var binder : DeleteBinder? = null
    private var deleteSelected : MenuItem? = null;
    private var deleteAll : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(TAG)
        setIcon(null)
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_delete,container,false)
        return binder?.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder?.buttonIncrease?.setOnClickListener(this)
        binder?.buttonDecrease?.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d(TAG, "onCreateOptionsMenu()")
        when (mainViewModel.counter) {
            -1 -> {
                inflater.inflate(R.menu.delete_menu, menu)
                setTitle("Delete Fragment")
                setNavigationIcon(null)
                setNavigationOnClickListener(null)
            }
            else -> {
                inflater.inflate(R.menu.delete_counter_menu, menu)
                setTitle(null)
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener(object : OnClickListener {
                    override fun onClick(view : View?) {
                        resetToolBarState()
                    }
                })
            }
        }
        super.onCreateOptionsMenu(menu, inflater)
        deleteSelected = menu.findItem(R.id.delete_selected) as MenuItem?
        deleteAll = menu.findItem(R.id.delete_all) as MenuItem?
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        return if (item.getItemId() == R.id.action_delete) {
            Log.d(TAG, "onClick action_delete")
            binder?.buttonIncrease?.setVisibility(View.VISIBLE)
            binder?.buttonDecrease?.setVisibility(View.VISIBLE)
            mainViewModel.counter = 0
            reCreateOptionsMenu()
            setToolbarVisibility(View.VISIBLE)
            setToolbarText(mainViewModel.counterString)
            true
        } else if (item.getItemId() == R.id.delete_selected) {
            Log.d(TAG, "onClick delete_selected")
            Toast.makeText(requireContext(), "Delete Selected " + mainViewModel.counterString, Toast.LENGTH_SHORT).show()
            resetToolBarState()
            true
        } else if (item.getItemId() == R.id.delete_all) {
            Log.d(TAG, "onClick delete_all")
            Toast.makeText(context, "Delete All", Toast.LENGTH_SHORT).show()
            resetToolBarState()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    protected override fun resetToolBarState() {
        Log.d(TAG, "resetToolBarState()")
        binder?.buttonIncrease?.setVisibility(View.INVISIBLE)
        binder?.buttonDecrease?.setVisibility(View.INVISIBLE)
        mainViewModel.counter = 0
        setToolbarText(mainViewModel.counterString)
        super.resetToolBarState()
    }

    override fun onClick(view : View?) {
        if (view?.getId() == binder?.buttonIncrease?.getId()) {
            Log.d(TAG, "onClick btnIncrease")
            mainViewModel.incrementCounter()
            setIcons()
            setToolbarText(mainViewModel.counterString)
        } else if (view?.getId() == binder?.buttonDecrease?.getId()) {
            Log.d(TAG, "onClick btnDecrease")
            mainViewModel.decrementCounter()
            setIcons()
            setToolbarText(mainViewModel.counterString)
        } else {
            Log.d(TAG, "onClick default")
        }
    }

    private fun setIcons() {
        if (mainViewModel.isEqualCounter(0)) {
            deleteSelected?.setVisible(false)
            deleteAll?.setVisible(true)
        } else {
            deleteSelected?.setVisible(true)
            deleteAll?.setVisible(false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.counter = -1
    }
}