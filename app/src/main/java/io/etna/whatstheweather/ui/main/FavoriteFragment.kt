package io.etna.whatstheweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.etna.whatstheweather.R
import io.etna.whatstheweather.ui.main.SearchRecycleAdapter.SearchRecycleAdapter
import io.etna.whattheweather.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteFragment : Fragment() {

    private val mViewModel: MainViewModel by sharedViewModel()

    private lateinit var mAdapter: SearchRecycleAdapter
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_favorite, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.recycler_view)

        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = mLayoutManager
        mAdapter = SearchRecycleAdapter(context!!)
        mRecyclerView.adapter = mAdapter

        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mViewModel.removeFavoriteLocationWeather(mAdapter.weatherLocations[viewHolder.adapterPosition])
                mAdapter.notifyDataSetChanged()
            }
        })

        touchHelper.attachToRecyclerView(mRecyclerView)

        mViewModel.favoriteWeatherWeatherLocation.removeObservers(viewLifecycleOwner)
        mViewModel.favoriteWeatherWeatherLocation.observe(viewLifecycleOwner, Observer {
            mAdapter.weatherLocations = it
            mAdapter.notifyDataSetChanged()
        })

    }
}