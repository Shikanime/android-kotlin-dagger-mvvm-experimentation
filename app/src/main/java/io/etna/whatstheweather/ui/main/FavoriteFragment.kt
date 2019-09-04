package io.etna.whatstheweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.etna.whatstheweather.R
import io.etna.whatstheweather.adapter.WeatherLocationAdapter
import io.etna.whattheweather.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteFragment : Fragment() {

    private val mViewModel: MainViewModel by sharedViewModel()

    private lateinit var mNavController: NavController
    private lateinit var mAdapter: WeatherLocationAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_favorite, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.recycler_view)
        mNavController = Navigation.findNavController(view)

        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = mLayoutManager
        mAdapter = WeatherLocationAdapter(context!!)
        mRecyclerView.adapter = mAdapter

        mRecyclerView.addItemDecoration(
            DividerItemDecoration(mRecyclerView.context, mLayoutManager.orientation)
        )

        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        mViewModel.removeFavoriteLocationWeather(mAdapter.weatherLocations[viewHolder.adapterPosition])
                        mAdapter.notifyDataSetChanged()
                    }
                    ItemTouchHelper.RIGHT -> {
                        mViewModel.openWeatherLocation(mAdapter.weatherLocations[viewHolder.adapterPosition])
                        mNavController.navigate(R.id.action_navigation_favorite_to_detail_fragment)
                    }
                }
            }
        })

        touchHelper.attachToRecyclerView(mRecyclerView)

        mViewModel.favoriteWeatherWeatherLocation.observe(viewLifecycleOwner, Observer {
            mAdapter.weatherLocations = it
            mAdapter.notifyDataSetChanged()
        })

    }
}