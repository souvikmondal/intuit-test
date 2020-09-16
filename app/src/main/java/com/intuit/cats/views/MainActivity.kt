package com.intuit.cats.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.intuit.cats.R

class MainActivity : AppCompatActivity(), CatListFragment.CatListFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: run {
            supportFragmentManager.commit {
                replace(R.id.frameFragment, CatListFragment.newInstance())
            }
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        when (fragment) {
            is CatListFragment -> {
                fragment.interactionListener = this@MainActivity
            }
        }
    }

    override fun onSelectCat(id: String) {
        supportFragmentManager.commit {
            add(R.id.frameFragment, CatDetailFragment.newInstance(id)).addToBackStack(null)
        }
    }
}