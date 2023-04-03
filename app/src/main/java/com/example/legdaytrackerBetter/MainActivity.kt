package com.example.legdaytrackerBetter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.legdaytrackerBetter.R
import com.example.legdaytrackerBetter.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
//    private val legDays = mutableListOf<DisplayLegDay>()
//    private lateinit var legDayRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val legDayListFragment: Fragment = LegDayListFragment()
        val graphFragment: Fragment = GraphFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        val addToList: Button = findViewById(R.id.addLegDayButton)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_list -> fragment = legDayListFragment
                R.id.nav_growth -> fragment = graphFragment
            }
            replaceFragment(fragment)
            true
        }

        addToList.setOnClickListener {
            val intent = Intent(this, LegDayFragActivity::class.java)
            startActivity(intent)
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_list

    }
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
        //find and assign the recyclerView
//        legDayRecyclerView = findViewById(R.id.legDays)
//
//        // TODO: Set up ArticleAdapter with articles
//        val legDayAdapter = LegDayAdapter( legDays)
//        legDayRecyclerView.adapter = legDayAdapter
//
//        //TODO: Review how this adds itemDecoration
//        legDayRecyclerView.layoutManager = LinearLayoutManager(this).also {
//            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
//            legDayRecyclerView.addItemDecoration(dividerItemDecoration)
//        }
//
//        lifecycleScope.launch {
//            (application as LegDayApplication).db.legDayDao().getAll().collect { databaseList ->
//                databaseList.map { entity ->
//                    DisplayLegDay(
//                        entity.date,
//                        entity.squat,
//                        entity.legExtension,
//                        entity.legCurl
//                    )
//                }.also { mappedList ->
//                    legDays.clear()
//                    legDays.addAll(mappedList)
//                    legDayAdapter.notifyDataSetChanged()
//                }
//            }
//        }

    private fun replaceFragment(legDayListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, legDayListFragment)
        fragmentTransaction.commit()
    }

}