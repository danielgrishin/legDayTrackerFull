package com.example.legdaytracker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.legdaytracker.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LegDayFragActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_leg_day)

        val legDayDate = findViewById<EditText>(R.id.editTextDate)
        val legDaySquat = findViewById<EditText>(R.id.inpSquat)
        val legDayLegExtension = findViewById<EditText>(R.id.inpLegExt)
        val legDayLegCurl = findViewById<EditText>(R.id.inpLegCurl)
        val legDaySubmit = findViewById<Button>(R.id.submitButton)

        legDaySubmit.isEnabled = false

        legDayDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                legDaySubmit.isEnabled =
                    !isEmpty(legDayDate) && !isEmpty(legDaySquat) && !isEmpty(legDayLegExtension) && !isEmpty(
                        legDayLegCurl
                    )

            }
        })

        legDaySquat.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                legDaySubmit.isEnabled =
                    !isEmpty(legDayDate) && !isEmpty(legDaySquat) && !isEmpty(legDayLegExtension) && !isEmpty(
                        legDayLegCurl
                    )

            }
        })

        legDayLegCurl.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                legDaySubmit.isEnabled =
                    !isEmpty(legDayDate) && !isEmpty(legDaySquat) && !isEmpty(legDayLegExtension) && !isEmpty(
                        legDayLegCurl
                    )

            }
        })

        legDayLegExtension.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                legDaySubmit.isEnabled =
                    !isEmpty(legDayDate) && !isEmpty(legDaySquat) && !isEmpty(legDayLegExtension) && !isEmpty(
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

}