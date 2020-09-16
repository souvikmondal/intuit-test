package com.intuit.cats.views

import android.app.Activity
import android.widget.Toast
import com.intuit.cats.CatApp
import com.intuit.cats.di.AppComponent

fun Activity.getAppComponent(): AppComponent = (application as CatApp).appComponent

fun Activity.showToast(string: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this,  string, duration).show()
}