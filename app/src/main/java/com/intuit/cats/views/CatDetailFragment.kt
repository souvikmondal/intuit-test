package com.intuit.cats.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.intuit.cats.R
import com.intuit.cats.vm.CatViewModel
import kotlinx.android.synthetic.main.fragment_cat_detail.description
import kotlinx.android.synthetic.main.fragment_cat_detail.title
import javax.inject.Inject

class CatDetailFragment : Fragment() {

    companion object {
        const val KEY_CAT_ID = "key_cat_id"

        fun newInstance(catId: String): CatDetailFragment {
            return CatDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_CAT_ID, catId)
                }
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val catViewModel: CatViewModel by viewModels({ requireActivity() }) {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cat_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getString(KEY_CAT_ID)
        if (id != null) {
            val catBreed = catViewModel.getCat(id)
            if (catBreed != null) {
                title.text = catBreed.name
                description.text = catBreed.description
            }
        }
    }
}