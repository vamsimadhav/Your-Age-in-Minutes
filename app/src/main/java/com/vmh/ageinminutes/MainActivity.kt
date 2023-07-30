package com.vmh.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.vmh.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var selectDateButton: Button
    lateinit var ageInMinutes: TextView
    lateinit var pickedDate: TextView

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pickedDate = binding.selectedDate
        ageInMinutes = binding.ageInMinutes
        selectDateButton = binding.selectedDateButton

        selectDateButton.setOnClickListener{view ->
            datePickerHelper(view)
        }
    }

    fun datePickerHelper(view: View){
        val calender = Calendar.getInstance()
        val month = calender.get(Calendar.MONTH)
        val year = calender.get(Calendar.YEAR)
        val dayOfMonth = calender.get(Calendar.DAY_OF_MONTH)
        
        val dp = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view,
                    selectedYear,
                    selectedMonth,
                    selectedDay ->

                val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"

                pickedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = sdf.parse(selectedDate)

                val selectedDateInMinutes = date.time / (60000*60)
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time / (60000 * 60)

                val ageInMinute = currentDateInMinutes - selectedDateInMinutes
                ageInMinutes.text = ageInMinute.toString()
            }
            ,year
            ,month
            ,dayOfMonth
        )
        dp.datePicker.maxDate = Date().time - 86400000
        dp.show()
    }
}