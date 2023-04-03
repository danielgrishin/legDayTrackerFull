package com.example.legdaytrackerBetter

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.legdaytrackerBetter.R
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [GraphFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GraphFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_graph, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var squatDif = view.findViewById<TextView>(R.id.growthSquat)
        var legExtDif = view.findViewById<TextView>(R.id.growthLegExt)
        var legCurlDif = view.findViewById<TextView>(R.id.growthLegCurl)

        lifecycleScope.launch(IO) {
            val squatList:List<Int> = (activity?.application as LegDayApplication).db.legDayDao().getSquat()
            val lastIndex=squatList.lastIndex
            squatDif.text= "N/A"
            legExtDif.text= "N/A"
            legCurlDif.text="N/A"
            if (lastIndex>=2){
                squatDif.text=(squatList[lastIndex]-squatList[0]).toString()
                changeTvColor(squatDif)

                val legExtList:List<Int> = (activity?.application as LegDayApplication).db.legDayDao().getLegExt()
                legExtDif.text=(legExtList[lastIndex]-legExtList[0]).toString()
                changeTvColor(legExtDif)

                val legCurlList:List<Int> = (activity?.application as LegDayApplication).db.legDayDao().getLegCurl()
                legCurlDif.text=(legCurlList[lastIndex]-legCurlList[0]).toString()
                changeTvColor(legCurlDif)
            }
//            (activity?.application as LegDayApplication).db.legDayDao().getAll().collect { databaseList ->
//                databaseList.map { entity ->
//                    DisplayLegDay(
//                        entity.date,
//                        entity.squat,
//                        entity.legExtension,
//                        entity.legCurl
//                    )
//                }
//            }
        }

    }

    private fun changeTvColor(tv:TextView){
        if ("-" in tv.text){
            tv.setBackgroundColor(Color.parseColor("#faaaaa"))
        }else if(tv.text == "0"){
            tv.setBackgroundColor(Color.parseColor("#bdbdbd"))
        }else{
            tv.setBackgroundColor(Color.parseColor("#acfaaa"))
        }
    }

}