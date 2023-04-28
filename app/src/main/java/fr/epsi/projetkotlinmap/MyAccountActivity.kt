package fr.epsi.projetkotlinmap

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MyAccountActivity : MainActivity() {
    private lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

        preferences = getSharedPreferences("MyPreference", Context.MODE_PRIVATE)
        val firstName = preferences.getString("firstName", "")
        val lastName = preferences.getString("lastName", "")
        val email = preferences.getString("email", "")
        val address = preferences.getString("address", "")
        val zipCode = preferences.getString("zipcode", "")
        val city = preferences.getString("city", "")

        findViewById<EditText>(R.id.myFirstName).setText(firstName)
        findViewById<EditText>(R.id.myLastName).setText(lastName)
        findViewById<EditText>(R.id.myEmail).setText(email)
        findViewById<EditText>(R.id.myEmail).setText(address)
        findViewById<EditText>(R.id.myZipCode).setText(zipCode)
        findViewById<EditText>(R.id.myCity).setText(city)
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener { onSaveButtonClicked() }
    }

    private fun onSaveButtonClicked() {
        val firstName = findViewById<EditText>(R.id.myFirstName).text.toString()
        val lastName = findViewById<EditText>(R.id.myLastName).text.toString()
        val email = findViewById<EditText>(R.id.myEmail).text.toString()
        val address = findViewById<EditText>(R.id.myAddress).text.toString()
        val zipCode = findViewById<EditText>(R.id.myZipCode).text.toString()
        val city = findViewById<EditText>(R.id.myCity).text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && address.isNotEmpty() &&
            zipCode.isNotEmpty() && city.isNotEmpty()
        ) {

            val editor = preferences.edit()
            editor.putString("firstName", firstName)
            editor.putString("lastName", lastName)
            editor.putString("email", email)
            editor.putString("address", address)
            editor.putString("zipCode", zipCode)
            editor.putString("city", city)
            editor.apply()

            Toast.makeText(this, "Modification saved", Toast.LENGTH_SHORT).show()
            val editIntent = Intent(this, MainActivity::class.java)
            editIntent.putExtra("firstName", firstName)
            editIntent.putExtra("lastName", lastName)
            editIntent.putExtra("email", email)
            editIntent.putExtra("address", address)
            editIntent.putExtra("zipCode", zipCode)
            editIntent.putExtra("city", city)
            startActivity(editIntent)
            finish()
        } else {
            Toast.makeText(this, "Complete all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
