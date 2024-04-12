package com.example.customviewapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.customviewapp.databinding.EditBinder

class EditFragment : BaseFragment, OnClickListener{

    companion object {
        private val TAG = EditFragment::class.java.getSimpleName()
    }

    constructor() {

    }

    private var binder : EditBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Edit Fragment")
        setIcon(R.drawable.ic_edit)
        resetToolBarState()
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_edit,container,false)
        return binder?.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder?.buttonCopy?.setOnClickListener(this)
        binder?.buttonPaste?.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d(TAG, "onCreateOptionsMenu()")
        setNavigationIcon(null)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClick(view : View?) {
        if (view?.getId() == binder?.buttonCopy?.getId()) {
            Log.d(TAG,"onClick btnCopy")
            val textToCopy : String = binder?.editCopy?.getText().toString()
            val clipboard : ClipboardManager? = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
            val clip : ClipData = ClipData.newPlainText(null, textToCopy)
            clipboard?.setPrimaryClip(clip)
        } else if (view?.getId() == binder?.buttonPaste?.getId()) {
            Log.d(TAG,"onClick btnPaste")
            val clipboard : ClipboardManager? = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
            val clip : ClipData? = clipboard?.getPrimaryClip()
            val item : ClipData.Item? = clip?.getItemAt(0)
            val textToPaste : CharSequence? = item?.getText()
            binder?.textPaste?.setText(textToPaste)
        } else {
            Log.d(TAG,"onClick default")
        }
    }
}