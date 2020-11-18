package com.example.uiapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_next).setOnClickListener {nextPage()}

        val switch1 =findViewById<Switch>(R.id.switch1);
        switch1.setOnCheckedChangeListener(this);

        val seekBar = findViewById<SeekBar>(R.id.seekBar_age)
        val seekBarDum = findViewById<SeekBar>(R.id.seekBar_dumm)
        seekBarDum.setOnSeekBarChangeListener(this)
        seekBar.setOnSeekBarChangeListener(this)
    }

    private fun nextPage(){
        val fname = findViewById<EditText>(R.id.first_name)
        val lname = findViewById<EditText>(R.id.last_name)
        val email = findViewById<EditText>(R.id.email_add)
        val pnum = findViewById<TextView>(R.id.phone_num)
        val ageVal = findViewById<TextView>(R.id.ageVal)


        val preferences = getSharedPreferences("sharedPrefs", 0)
        val editor = preferences.edit()
        editor.putString("fname", fname.text.toString())
        editor.putString("lname", lname.text.toString())
        editor.putString("email", email.text.toString())
        editor.putString("pnum", pnum.text.toString())
        editor.putString("ageval", ageVal.text.toString())
        editor.commit()

        val intent = Intent(this, secondpage::class.java)
        startActivity(intent)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        val pnum = findViewById<TextView>(R.id.phone_num)
        when (buttonView!!.id) {
            R.id.switch1 -> if (isChecked === true) {
                pnum.setVisibility(View.VISIBLE)
            } else {
                pnum.setVisibility(View.INVISIBLE)
            }
        }
        Log.i("PhoneNumber", pnum.text.toString())
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        val ageVal = findViewById<TextView>(R.id.ageVal)
        val seekBarDum = findViewById<SeekBar>(R.id.seekBar_dumm)
        seekBarDum.setVisibility(View.INVISIBLE)
        val seekBar = findViewById<SeekBar>(R.id.seekBar_age)
        seekBar.setVisibility(View.VISIBLE)
        if(p1 < 18)
            ageVal.text = "18"
        else
            ageVal.text = "$p1"
        Log.i("Age", ageVal.text.toString())

    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }
}