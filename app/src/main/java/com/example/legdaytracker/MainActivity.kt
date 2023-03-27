package com.example.legdaytracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.legdaytracker.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val legDays = mutableListOf<DisplayLegDay>()
    private lateinit var legDayRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addToList = findViewById<Button>(R.id.addLegDayButton)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
        //find and assign the recyclerView
        legDayRecyclerView = findViewById(R.id.legDays)

        // TODO: Set up ArticleAdapter with articles
        val legDayAdapter = LegDayAdapter( legDays)
        legDayRecyclerView.adapter = legDayAdapter

        //TODO: Review how this adds itemDecoration
        legDayRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            legDayRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        lifecycleScope.launch {
            (application as LegDayApplication).db.legDayDao().getAll().collect { databaseList ->
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

        addToList.setOnClickListener {
            val intent = Intent(this, LegDayFragActivity::class.java)
            startActivity(intent)
        }
    }
}