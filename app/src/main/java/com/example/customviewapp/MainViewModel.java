package com.example.customviewapp;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;

public class MainViewModel extends AndroidViewModel {

    public MainViewModel(Application application) {
        super(application);
    }

    private Boolean utilitiyIndicator;
    private int counter = -1;

    public void setUtilitiyIndicator(Boolean utilitiyIndicator) {
        this.utilitiyIndicator = utilitiyIndicator;
    }

    public Boolean getUtilityIndicator() {
        if (utilitiyIndicator == null) {
            return false;
        } else {
            return utilitiyIndicator;
        }
    }

    public void incrementCounter() {
        this.counter++;
    }

    public void decrementCounter() {
        if (isGreaterThanCounter(0))
            this.counter--;
    }

    public Boolean isEqualCounter(int value) {
        return this.counter == value;
    }

    public Boolean isGreaterThanCounter(int value) {
        return this.counter > value;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getCounterString() {
        return String.valueOf(counter);
    }

    public int getCounterInteger() {
        return counter;
    }

    public Drawable getIcon(Integer icon) {
        return icon != null ? ContextCompat.getDrawable(getApplication(), icon) : null;
    }
}