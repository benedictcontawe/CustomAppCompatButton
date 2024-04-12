package com.example.customviewapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

public abstract class BaseFragment : Fragment() {

    companion object {
        private val TAG = BaseFragment::class.java.getSimpleName()
    }

    private var mainActivity: MainActivity? = null
    protected val mainViewModel : MainViewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableOptionsMenu()
        Log.d(TAG, "onCreate()")
        mainActivity = getActivity() as MainActivity?
    }

    protected fun getMainActivity() : MainActivity? {
        return mainActivity
    }

    protected fun enableOptionsMenu() {
        setHasOptionsMenu(true)
    }

    protected fun disableOptionsMenu() {
        setHasOptionsMenu(false)
    }

    protected fun reCreateOptionsMenu() {
        //setHasOptionsMenu(false);
        //setHasOptionsMenu(true);
        getMainActivity()?.invalidateOptionsMenu()
    }

    protected open fun resetToolBarState() {
        setToolbarVisibility(View.GONE)
        getMainActivity()?.invalidateOptionsMenu()
    }

    protected fun setToolbarVisibility(visibility: Int) {
        mainActivity?.getTxtToolbar()?.setVisibility(visibility)
    }

    protected fun setToolbarText(text: String?) {
        mainActivity?.getTxtToolbar()?.setText(text)
    }

    protected fun setNavigationIcon(icon: Int?) {
        mainActivity?.getToolbar()?.setNavigationIcon(mainViewModel?.getIcon(icon))
    }

    protected fun setNavigationOnClickListener(listener: View.OnClickListener?) {
        mainActivity?.getToolbar()?.setNavigationOnClickListener(listener)
    }

    protected fun setTitle(title: String?) {
        mainActivity?.getSupportActionBar()?.setTitle(title)
    }

    protected fun setIcon(icon: Int?) {
        mainActivity?.getSupportActionBar()?.setIcon(mainViewModel?.getIcon(icon))
    }

    protected fun setPlaceHolderVisibility(visibility: Int) {
        mainActivity?.getPlaceHolder()?.setVisibility(visibility)
    }
}