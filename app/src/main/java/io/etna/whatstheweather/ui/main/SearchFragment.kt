package io.etna.whatstheweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.etna.whatstheweather.R
import io.etna.whatstheweather.ui.main.SearchRecycleAdapter.SearchRecycleAdapter
import io.etna.whattheweather.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment() {

    private val mViewModel: MainViewModel by sharedViewModel()

    private lateinit var mAdapter: SearchRecycleAdapter
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mCityInputText: AppCompatEditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.recycler_view)
        mCityInputText = view.findViewById(R.id.city_input)

        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = mLayoutManager
        mAdapter = SearchRecycleAdapter(context!!)
        mRecyclerView.adapter = mAdapter

        mViewModel.searchWeatherLocation.removeObservers(viewLifecycleOwner)
        mViewModel.searchWeatherLocation.observe(viewLifecycleOwner, Observer {
            mAdapter.weatherLocations = it
            mAdapter.notifyDataSetChanged()
        })

        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mViewModel.addFavoriteLocationWeather(mAdapter.weatherLocations[viewHolder.adapterPosition])
                mAdapter.notifyDataSetChanged()
            }
        })

        touchHelper.attachToRecyclerView(mRecyclerView)

        mCityInputText.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    if (v.text.isNotBlank()) {
                        mViewModel.searchLocationWeather(v.text.toString())
                        false
                    } else {
                        true
                    }
                }
                else -> false
            }
        }
    }
}