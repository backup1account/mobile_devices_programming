package com.example.list4

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CapitalCityFragment : Fragment() {
    private var columnCount = 1

    private var countries: List<Country>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_capital_city_list, container, false)

        val rView = view.findViewById<RecyclerView>(R.id.list)
        val errView = view.findViewById<TextView>(R.id.error_text)

        val call = RetrofitFactory.call

        call.enqueue(object: Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful) {
                    val resCountries = response.body()
                    countries = resCountries

                    if (rView is RecyclerView && countries != null) {
                        with(rView) {
                            layoutManager = when {
                                columnCount <= 1 -> LinearLayoutManager(context)
                                else -> GridLayoutManager(context, columnCount)
                            }
                            adapter = CapitalCityRecyclerViewAdapter(countries!!)
                        }
                    }
                }
            }
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                view.findViewById<TextView>(R.id.country_text).visibility = View.INVISIBLE
                view.findViewById<TextView>(R.id.capital_text).visibility = View.INVISIBLE

                errView.text = "Failed to connect to ${RetrofitFactory.url}. " +
                        "Check your internet connection."
            }
        })
        return view
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}