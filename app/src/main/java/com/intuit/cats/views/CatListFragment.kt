package com.intuit.cats.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.intuit.cats.R
import com.intuit.cats.models.Cat
import com.intuit.cats.network.APIResponse
import com.intuit.cats.network.getErrorResId
import com.intuit.cats.views.adapters.CatListAdapter
import com.intuit.cats.vm.CatViewModel
import kotlinx.android.synthetic.main.fragment_cat_list.catList
import kotlinx.android.synthetic.main.fragment_cat_list.progress
import javax.inject.Inject

class CatListFragment : Fragment() {

    companion object {
        fun newInstance(): CatListFragment {
            return CatListFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var interactionListener: CatListFragmentInteractionListener? = null

    private var catListAdapter: CatListAdapter? = null

    private val catViewModel: CatViewModel by viewModels({ requireActivity() }) {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cat_list, container, false)
        subscribeToCatBreeds()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        catViewModel.fetchCatBreeds()
    }

    private fun subscribeToCatBreeds() {
        catViewModel.catsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is APIResponse.Success -> {
                    bindCatBreeds(it.data)
                    progress.visibility = View.GONE
                }
                is APIResponse.Loading -> {
                    progress.visibility = View.VISIBLE
                }
                is APIResponse.Error -> {
                    progress.visibility = View.GONE
                    requireActivity().showToast(getString(getErrorResId(it)))
                }
            }
        }
    }

    private fun bindCatBreeds(cats: List<Cat>) {
        if (catListAdapter == null) {
            catListAdapter = CatListAdapter.newInstance(cats) {
                interactionListener?.onSelectCat(it)
            }
            catList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            catList.adapter = catListAdapter
        } else {
            catListAdapter?.update(cats)
        }
    }

    interface CatListFragmentInteractionListener {
        fun onSelectCat(id: String)
    }
}