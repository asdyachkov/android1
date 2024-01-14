package com.example.myfistapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged

/**
 * Learn more about activities
 * https://developer.android.com/guide/components/activities/activity-lifecycle
 */
class MainActivity : AppCompatActivity() {
    private var editTextValue = ""
    private var editPhoneValue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alertButton = findViewById<Button>(R.id.alert_button)
        val editText = findViewById<EditText>(R.id.editText)
        val numberButton = findViewById<Button>(R.id.number_button)
        val editText2 = findViewById<EditText>(R.id.editText2)

        editText.doAfterTextChanged {
            editTextValue = editText.text.toString()
        }

        editText.doAfterTextChanged {
            editPhoneValue = editText2.text.toString()
        }

        alertButton.setOnClickListener {
            startSecondActivityWithData()
        }

        numberButton.setOnClickListener {
            sendImplicitIntent()
        }

        println("MainActivity onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        println("MainActivity onRestart")
    }

    override fun onStart() {
        super.onStart()
        println("MainActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        println("MainActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        println("MainActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        println("MainActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("MainActivity onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val editText = findViewById<EditText>(R.id.editText)
        editTextValue = editText.text.toString()
        outState.putString("SAVED_TEXT_KEY", editTextValue)

        println("MainActivity onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val userString = savedInstanceState.getString("SAVED_TEXT_KEY")
        val editText = findViewById<EditText>(R.id.editText)
        editText.setText(userString)

        println("MainActivity onRestoreInstanceState")
    }

    private fun startSecondActivityWithData() {
        val intent = SecondActivity.createIntent(this, editTextValue)
        startActivity(intent)
    }

    private fun sendImplicitIntent() {
        val editText2 = findViewById<EditText>(R.id.editText2)
        editPhoneValue = editText2.text.toString()
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:" + editPhoneValue)
        }
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Sorry, I can't do it", Toast.LENGTH_SHORT).show()
        }
    }
}