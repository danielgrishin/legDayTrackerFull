package com.example.legdaytracker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.legdaytracker.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class LegDayFragActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_leg_day)

        val legDayDate = findViewById<TextView>(R.id.editTextDate)
        val legDaySquat = findViewById<EditText>(R.id.inpSquat)
        val legDayLegExtension = findViewById<EditText>(R.id.inpLegExt)
        val legDayLegCurl = findViewById<EditText>(R.id.inpLegCurl)
        val legDaySubmit = findViewById<Button>(R.id.submitButton)

        legDayDate.text=stringDate(SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date()))

        legDaySubmit.isEnabled = false

        legDayDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                legDaySubmit.isEnabled =!isEmpty(legDaySquat) && !isEmpty(legDayLegExtension) && !isEmpty(
                        legDayLegCurl
                    )

            }
        })

        legDaySquat.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                legDaySubmit.isEnabled =!isEmpty(legDaySquat) && !isEmpty(legDayLegExtension) && !isEmpty(
                        legDayLegCurl
                    )

            }
        })

        legDayLegCurl.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                legDaySubmit.isEnabled =!isEmpty(legDaySquat) && !isEmpty(legDayLegExtension) && !isEmpty(
                        legDayLegCurl
                    )

            }
        })

        legDayLegExtension.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                legDaySubmit.isEnabled =!isEmpty(legDaySquat) && !isEmpty(legDayLegExtension) && !isEmpty(
                        legDayLegCurl
                    )

            }
        })

        legDaySubmit.setOnClickListener {
//            val item = DisplayLegDay(
//                legDayDate.text.toString(),
//                legDaySquat.text.toString(),
//                legDayLegExtension.text.toString(),
//                legDayLegCurl.text.toString()
//            )
            lifecycleScope.launch(IO) {
                //val memory = (application as LegDayApplication).db.legDayDao().getAll()
                //(application as LegDayApplication).db.legDayDao().deleteAll()
                (application as LegDayApplication).db.legDayDao().insert(
                    LegDayEntity(
                        date = legDayDate.text.toString(),
                        squat = legDaySquat.text.toString(),
                        legExtension = legDayLegExtension.text.toString(),
                        legCurl = legDayLegCurl.text.toString()
                    )
                )
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isEmpty(etText: EditText): Boolean {
        return etText.text.toString().trim { it <= ' ' }.isEmpty()
    }

    private fun stringDate(slashDate: String): String {
        val slashDateArray = slashDate.split("/").toList()
        val months = arrayOf("blah","JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC")
        return months[slashDateArray[0].toInt()]+" "+slashDateArray[1]
    }

}