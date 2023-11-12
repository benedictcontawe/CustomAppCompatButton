package com.example.customappactionsheet

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView

class ActionSheetViewHolder : BaseActionSheetViewHolder {

    companion object {
        private val TAG : String = ActionSheetViewHolder::class.java.simpleName
    }

    private val listener : ActionSheetListener
    private val cardView : CardView
    private val textView : TextView

    constructor(listener : ActionSheetListener, view : View) : super(view) {
        this.listener = listener
        this.cardView = view.findViewById(R.id.card)
        this.textView = view.findViewById(R.id.text)
    }

    override fun bindDataToViewHolder(item: ActionSheetViewHolderModel, position: Int) {
        textView.setText(item.data)
        cardView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                listener.onClickItem(item, position)
            }
        })
    }
}