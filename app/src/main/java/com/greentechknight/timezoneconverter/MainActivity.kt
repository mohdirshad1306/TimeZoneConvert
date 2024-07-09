package com.greentechknight.timezoneconverter

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //*************************gert all elements
        val btn_click_me = findViewById(R.id.datePicker) as Button
        val result = findViewById<TextView>(R.id.Result) as TextView // where result will be stored
        val spinHour = findViewById<Spinner>(R.id.Hour) as Spinner
        val spinMinutes = findViewById<Spinner>(R.id.Minute) as Spinner
        val Meridium = findViewById<Spinner>(R.id.Meridium) as Spinner
//        val fromTZ = findViewById<Spinner>(R.id.FromTimeZone) as Spinner
//        val ToTZ = findViewById<Spinner>(R.id.ToTimeZone) as Spinner
        val fromTZ = findViewById<AutoCompleteTextView>(R.id.FromTimeZone)
        val ToTZ = findViewById<AutoCompleteTextView>(R.id.ToTimeZone)
        //*************************set date picker

        setDatePicker(btn_click_me, spinHour,
            spinMinutes ,
            Meridium ,
            fromTZ ,
            ToTZ ,
            result)



        //*********************************** set hour spinner
        // Create the instance of ArrayAdapter
        val Hours : ArrayList<Int> = ArrayList(12)
        for(i in 0..12 step 1){
            Hours.add(i)
        }
        setSpinner(spinHour, Hours as List<Any>)
        spinHour.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {

                val dateSelected = findViewById<TextView>(R.id.SelectedDate) as TextView // date selected
                // input values
                val SelectedDateTxt : CharSequence? = dateSelected.text //date
                val hour = spinHour.selectedItem.toString() //hour
                val min = spinMinutes.selectedItem.toString() // minutes
                val mer = Meridium.selectedItem.toString() // meridian
                val fromTimeZone = fromTZ.text.toString() // from time zone
                val ToTimeZone = ToTZ.text.toString() // to time zone
                var res : String= calculateDate(fromTimeZone, ToTimeZone, SelectedDateTxt as String, hour, min, mer)
                result.setText((res))

            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }

        //************************************** set minutes spinner

        // create arralist of minutes
        val Minutes : ArrayList<Int> = ArrayList(60)
        for(i in 0..60 step 1){
            Minutes.add(i)
        }
        setSpinner(spinMinutes, Minutes as List<Any>)
        spinMinutes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {

                val dateSelected = findViewById<TextView>(R.id.SelectedDate) as TextView // date selected
                // input values
                val SelectedDateTxt : CharSequence? = dateSelected.text //date
                val hour = spinHour.selectedItem.toString() //hour
                val min = spinMinutes.selectedItem.toString() // minutes
                val mer = Meridium.selectedItem.toString() // meridian
                val fromTimeZone = fromTZ.text.toString() // from time zone
                val ToTimeZone = ToTZ.text.toString() // to time zone
                var res : String= calculateDate(fromTimeZone, ToTimeZone, SelectedDateTxt as String, hour, min, mer)
                result.setText((res))

            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }

        //******************************************meridium set
        val merValues = mutableListOf<String>("AM", "PM")
        setSpinner(Meridium, merValues)
        Meridium.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {

                val dateSelected = findViewById<TextView>(R.id.SelectedDate) as TextView // date selected
                // input values
                val SelectedDateTxt : CharSequence? = dateSelected.text //date
                val hour = spinHour.selectedItem.toString() //hour
                val min = spinMinutes.selectedItem.toString() // minutes
                val mer = Meridium.selectedItem.toString() // meridian
                val fromTimeZone = fromTZ.text.toString() // from time zone
                val ToTimeZone = ToTZ.text.toString() // to time zone
                var res : String= calculateDate(fromTimeZone, ToTimeZone, SelectedDateTxt as String, hour, min, mer)
                result.setText((res))

            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }
        //******************************************from time zone  set
        val times = TimeZone.getAvailableIDs()
        var TZ1 : MutableList<String> = mutableListOf("Convert From")
        for(time in times) {
           TZ1.add(time)
        }

        TZ1.add(0, "Convert From")
        setCombo(fromTZ, TZ1)
        fromTZ.setOnItemClickListener(AdapterView.OnItemClickListener { parent, arg1, pos, id ->

            val dateSelected = findViewById<TextView>(R.id.SelectedDate) as TextView // date selected
                // input values
                val SelectedDateTxt : CharSequence? = dateSelected.text //date
                val hour = spinHour.selectedItem.toString() //hour
                val min = spinMinutes.selectedItem.toString() // minutes
                val mer = Meridium.selectedItem.toString() // meridian
                val fromTimeZone = fromTZ.text.toString() // from time zone
                val ToTimeZone = ToTZ.text.toString() // to time zone
                var res : String= calculateDate(fromTimeZone, ToTimeZone, SelectedDateTxt as String, hour, min, mer)
                result.setText((res))
        })
//        fromTZ.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(
//                parentView: AdapterView<*>?,
//                selectedItemView: View?,
//                position: Int,
//                id: Long
//            ) {
//
//                val dateSelected = findViewById<TextView>(R.id.SelectedDate) as TextView // date selected
//                // input values
//                val SelectedDateTxt : CharSequence? = dateSelected.text //date
//                val hour = spinHour.selectedItem.toString() //hour
//                val min = spinMinutes.selectedItem.toString() // minutes
//                val mer = Meridium.selectedItem.toString() // meridian
//                val fromTimeZone = fromTZ.selectedItem.toString() // from time zone
//                val ToTimeZone = ToTZ.selectedItem.toString() // to time zone
//                var res : String= calculateDate(fromTimeZone, ToTimeZone, SelectedDateTxt as String, hour, min, mer)
//                result.setText((res))
//
//            }

//            override fun onNothingSelected(parentView: AdapterView<*>?) {
//                // your code here
//            }
//        }

        //****************************To time zone
        TZ1.removeAt(0)
        TZ1.add(0, "Convert To")
        setCombo( ToTZ, TZ1)

        ToTZ.setOnItemClickListener(AdapterView.OnItemClickListener { parent, arg1, pos, id ->

            val dateSelected = findViewById<TextView>(R.id.SelectedDate) as TextView // date selected
            // input values
            val SelectedDateTxt : CharSequence? = dateSelected.text //date
            val hour = spinHour.selectedItem.toString() //hour
            val min = spinMinutes.selectedItem.toString() // minutes
            val mer = Meridium.selectedItem.toString() // meridian
            val fromTimeZone = fromTZ.text.toString() // from time zone
            val ToTimeZone = ToTZ.text.toString() // to time zone
            var res : String= calculateDate(fromTimeZone, ToTimeZone, SelectedDateTxt as String, hour, min, mer)
            result.setText((res))
        })
//        ToTZ.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(
//                parentView: AdapterView<*>?,
//                selectedItemView: View?,
//                position: Int,
//                id: Long
//            ) {
//
//                val dateSelected = findViewById<TextView>(R.id.SelectedDate) as TextView // date selected
//                // input values
//                val SelectedDateTxt : CharSequence? = dateSelected.text //date
//                val hour = spinHour.selectedItem.toString() //hour
//                val min = spinMinutes.selectedItem.toString() // minutes
//                val mer = Meridium.selectedItem.toString() // meridian
//                val fromTimeZone = fromTZ.selectedItem.toString() // from time zone
//                val ToTimeZone = ToTZ.selectedItem.toString() // to time zone
//                Toast.makeText(applicationContext, "Converting .....", Toast.LENGTH_LONG)
//                if(!fromTimeZone?.contains("Convert")!!
//                    && !ToTimeZone?.contains("Convert")!!
//                    && SelectedDateTxt != "No date selected") {
//                    val input: String = "$SelectedDateTxt $hour:$min $mer" // input date string
//                    var resultTxt: String = convertDate(input, fromTimeZone, ToTimeZone) // convert to to time zone
//                    result.setText(resultTxt) // set the result text
//                }
//                else if(fromTimeZone.contains("Convert")){
//                    result.setText("Enter from time zone")
//                }
//                else if (ToTimeZone.contains("Convert")){
//                    result.setText("Enter To time zone")
//                }
//                else{
//                    result.setText("Date is not selected")
//                }
//            }
//
//            override fun onNothingSelected(parentView: AdapterView<*>?) {
//                // your code here
//            }
//        }

        //*********************result

    }
    fun setCombo(element: AutoCompleteTextView, arr: List<Any>){
        val adapter = CustomAdapter(this, android.R.layout.select_dialog_item , arr as List<String>)
       // val adapter = ArrayAdapter(this,
//            android.R.layout.select_dialog_item, arr)
        element.threshold = 1
        element.setAdapter(adapter)
    }

    fun calculateDate(fromTimeZone : String,
                      ToTimeZone: String,
                      SelectedDateTxt: String,
                      hour : String,
                      min: String,
                      mer : String) : String{
        Toast.makeText(applicationContext, "Converting .....", Toast.LENGTH_LONG)
        if(!fromTimeZone?.contains("Convert")!!
            && !ToTimeZone?.contains("Convert")!!
            && SelectedDateTxt != "No date selected") {
            val input: String = "$SelectedDateTxt $hour:$min $mer" // input date string
            var resultTxt: String = convertDate(input, fromTimeZone, ToTimeZone) // convert to to time zone
            return  resultTxt// set the result text
        }
        else if(fromTimeZone.contains("Convert")){
            return ("Enter from time zone")
        }
        else if (ToTimeZone.contains("Convert")){
            return ("Enter To time zone")
        }
        else{
            return ("Date is not selected")
        }
    }


    fun convertDate(dateInString : String, FromTimeZone : String?,  ToTimeZone : String?) : String {
        val DATE_FORMAT : String= "dd-M-yyyy hh:mm a";

        val formatter : SimpleDateFormat = SimpleDateFormat(DATE_FORMAT)
        val FromTZ : TimeZone = TimeZone.getTimeZone(FromTimeZone)
        formatter.setTimeZone(FromTZ)
        var fromDate  : Date = formatter.parse(dateInString)

        val ToTZ : TimeZone = TimeZone.getTimeZone(ToTimeZone)
        formatter.setTimeZone(ToTZ)
        val resultDate : String  = formatter.format(fromDate)
        return resultDate

    }

    fun setDatePicker(element : Button, spinHour : Spinner,
                      spinMinutes  : Spinner,
                      Meridium : Spinner,
                      fromTZ : AutoCompleteTextView,
                      ToTZ  : AutoCompleteTextView,
                      result : TextView){
        // set date picker
        val btn_click_me = findViewById(R.id.datePicker) as Button
        // set on-click listener
        element.setOnClickListener() { dateView : View ->renderDatePicker(dateView, spinHour,
            spinMinutes ,
            Meridium ,
            fromTZ ,
            ToTZ ,
            result )}
    }

    fun setSpinner(element : Spinner, arr: List<Any>){
        // Create the instance of ArrayAdapter
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            arr
        )
        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        element.adapter = ad
    }


    fun renderDatePicker(
        dateView: View,
        spinHour: Spinner,
        spinMinutes: Spinner,
        Meridium: Spinner,
        fromTZ: AutoCompleteTextView,
        ToTZ: AutoCompleteTextView,
        result: TextView){
        val dateSelected = findViewById<TextView>(R.id.SelectedDate) as TextView // date selected
        // input values
        val SelectedDateTxt : CharSequence? = dateSelected.text //date
        val hour = spinHour.selectedItem.toString() //hour
        val min = spinMinutes.selectedItem.toString() // minutes
        val mer = Meridium.selectedItem.toString() // meridian
        val fromTimeZone = fromTZ.text.toString() // from time zone
        val ToTimeZone = ToTZ.text.toString() // to time zone

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get((Calendar.MONTH))
        val day = cal.get((Calendar.DAY_OF_MONTH))
        DatePickerDialog (this,
            DatePickerDialog.OnDateSetListener { dateView, yearSelected, monthSelected, daySelected ->
                // val dateSelected = "$daySelected-$monthSelected-$yearSelected"
                var month =monthSelected+1
                findViewById<TextView>(R.id.SelectedDate).setText("$daySelected-$month-$yearSelected")
                // val datefrmt = SimpleDateFormat("mm/dd/yy")
                //val dateoutput = datefrmt.parse(dateSelected)
                Toast.makeText(applicationContext, "Converting .....", Toast.LENGTH_LONG)
                if(!fromTimeZone?.contains("Convert")!!
                    && !ToTimeZone?.contains("Convert")!!
                    && SelectedDateTxt != "No date selected") {
                    val input: String = "$SelectedDateTxt $hour:$min $mer" // input date string
                    var resultTxt: String = convertDate(input, fromTimeZone, ToTimeZone) // convert to to time zone
                    result.setText(resultTxt) // set the result text
                }
                else if(fromTimeZone.contains("Convert")){
                    result.setText("Enter from time zone")
                }
                else if (ToTimeZone.contains("Convert")){
                    result.setText("Enter To time zone")
                }
                else{
                    result.setText("Date is not selected")
                }

            },
            year,
            month,
            day).show()

    }

    fun displayToast(text : String){
        Toast.makeText(getApplicationContext(),
            text,
            Toast.LENGTH_LONG)
            .show();
    }
}