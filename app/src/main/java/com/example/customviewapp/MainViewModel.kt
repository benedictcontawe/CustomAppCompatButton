package com.example.customviewapp

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel


class MainViewModel : AndroidViewModel {

    companion object {
        private val TAG = MainViewModel::class.java.getSimpleName()
    }

    constructor(application: Application?) : super(application!!) {

    }
    var utilitiyIndicator : Boolean? = null
        set(value) {
            field = value
        }
        get() = if (field == null) {
            false
        } else {
            field
        }

    public var counter = -1
        set(value) {
            field = value
        }
        get() = field

    fun incrementCounter() {
        counter++
    }

    fun decrementCounter() {
        if (isGreaterThanCounter(0)) counter--
    }

    fun isEqualCounter(value: Int): Boolean {
        return counter == value
    }

    fun isGreaterThanCounter(value: Int): Boolean {
        return counter > value
    }

    val counterString : String get() = counter.toString()

    fun getIcon(icon: Int?): Drawable? {
        return if (icon != null) ContextCompat.getDrawable(getApplication(), icon) else null
    }
}