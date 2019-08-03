package com.example.customtextinputeditext.CustomView

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class ContactEditText : CustomEditText {

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    companion object{
        val code = 0
    }

    fun setListener(contactEditText : ContactEditText, showContacts: ()-> Unit, onError: (message : String)-> Unit){

        this.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view : View, motionEvent : MotionEvent): Boolean {
                val DRAWABLE_TOP : Int = 1
                val DRAWABLE_RIGHT : Int = 2
                val DRAWABLE_BOTTOM : Int = 3
                when(motionEvent.getAction()){
                    MotionEvent.ACTION_UP -> {
                        if(motionEvent.getRawX() >= (contactEditText.getRight() - contactEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            /**Your Action for clicking drawable end*/
                            try {
                                showContacts()
                            }
                            catch(ex: Exception){
                                onError(ex.toString())
                            }
                            return true
                        }
                    }
                }
                return false
            }
        })
    }
}