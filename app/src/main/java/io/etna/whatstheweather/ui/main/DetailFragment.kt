package io.etna.whatstheweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.etna.whatstheweather.R
import io.etna.whatstheweather.adapter.WeatherAdapter
import io.etna.whattheweather.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DetailFragment : Fragment() {

    private val mViewModel: MainViewModel by sharedViewModel()

    private lateinit var mAdapter: WeatherAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mNameTextView: AppCompatTextView
    private lateinit var mVisibilityTextView: AppCompatTextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNameTextView = view.findViewById(R.id.location_name)
        mVisibilityTextView = view.findViewById(R.id.location_visibility)
        mRecyclerView = view.findViewById(R.id.recycler_view)

        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = mLayoutManager
        mAdapter = WeatherAdapter(context!!)
        mRecyclerView.adapter = mAdapter

        mRecyclerView.addItemDecoration(
            DividerItemDecoration(mRecyclerView.context, mLayoutManager.orientation)
        )

        mViewModel.currentWeatherLocation.removeObservers(viewLifecycleOwner)
        mViewModel.currentWeatherLocation.observe(viewLifecycleOwner, Observer {
            if (it.name.isNotBlank()) {
                mNameTextView.text = it.name
            }

            if (it.visibility > 0) {
                mVisibilityTextView.text = it.visibility.toString()
            }

            if (it.weather.isNotEmpty()) {
                mAdapter.weathers = it.weather
                mAdapter.notifyDataSetChanged()
            }
        })
    }
}