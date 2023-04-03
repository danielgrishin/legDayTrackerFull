package com.example.legdaytrackerBetter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.legdaytracker.R
//import com.example.legdaytracker.databinding.ActivityMainBinding
////import com.example.legdaytrackerBetter.databinding.ActivityMainBinding
////import com.example.legdaytrackerBetter.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [LegDayListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LegDayListFragment : Fragment() {
    private val legDays = mutableListOf<DisplayLegDay>()
    private lateinit var legDayRecyclerView: RecyclerView
    private lateinit var legDayAdapter: LegDayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        lifecycleScope.launch {
            (activity?.application as LegDayApplication).db.legDayDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayLegDay(
                        entity.date,
                        entity.squat,
                        entity.legExtension,
                        entity.legCurl
                    )
                }.also { mappedList ->
                    legDays.clear()
                    legDays.addAll(mappedList)
                    legDayAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_leg_day_list, container, false)
        val layoutManager = LinearLayoutManager(context)
        legDayRecyclerView = view.findViewById(R.id.leg_day_recycler_view)
        legDayRecyclerView.layoutManager = layoutManager
        legDayRecyclerView.setHasFixedSize(true)
        legDayAdapter = LegDayAdapter(legDays)
        legDayRecyclerView.adapter = legDayAdapter

        // Update the return statement to return the inflated view from above
        return view
    }

    companion object {
        fun newInstance(): LegDayListFragment {
            return LegDayListFragment()
        }

    }
}

