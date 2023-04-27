package fr.epsi.projetkotlinmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.content.SharedPreferences
import android.widget.Toast


class SignUpActivity : MainActivity() {

    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setHeaderTxt("Création de compte")
        showBack()
        prefs = getSharedPreferences("MyPreference", Context.MODE_PRIVATE)

        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val email = intent.getStringExtra("email")
        val address = intent.getStringExtra("address")
        val zipCode = intent.getStringExtra("zipCode")
        val city = intent.getStringExtra("city")
        val cardRef = intent.getStringExtra("cardRef")

        val editTextFirstName = findViewById<EditText>(R.id.firstNameForm)
        val editTextLastName = findViewById<EditText>(R.id.lastNameForm)
        val editTextEmail = findViewById<EditText>(R.id.emailForm)
        val editTextAddress = findViewById<EditText>(R.id.addressForm)
        val editTextZipCode = findViewById<EditText>(R.id.zipCodeForm)
        val editTextCity = findViewById<EditText>(R.id.cityForm)
        val editTextCardRef = findViewById<EditText>(R.id.cardRefForm)

        editTextFirstName.setText(firstName)
        editTextLastName.setText(lastName)
        editTextEmail.setText(email)
        editTextAddress.setText(address)
        editTextZipCode.setText(zipCode)
        editTextCity.setText(city)
        editTextCardRef.setText(cardRef)

        Log.d("SignUpActivity", "firstName: $firstName")
        Log.d("SignUpActivity", "lastName: $lastName")
        Log.d("SignUpActivity", "email: $email")
        Log.d("SignUpActivity", "address: $address")
        Log.d("SignUpActivity", "zipCode: $zipCode")
        Log.d("SignUpActivity", "city: $city")
        Log.d("SignUpActivity", "cardRef: $cardRef")
    }

        fun CreateAccount(view: android.view.View) {
            val firstName = findViewById<EditText>(R.id.firstNameForm).text.toString()
            val lastName = findViewById<EditText>(R.id.lastNameForm).text.toString()
            val email = findViewById<EditText>(R.id.emailForm).text.toString()
            val address = findViewById<EditText>(R.id.addressForm).text.toString()
            val zipCode = findViewById<EditText>(R.id.zipCodeForm).text.toString()
            val city = findViewById<EditText>(R.id.cityForm).text.toString()
            val cardRef = findViewById<EditText>(R.id.cardRefForm).text.toString()

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && address.isNotEmpty() &&
                zipCode.isNotEmpty() && city.isNotEmpty() && cardRef.isNotEmpty()) {

                val editor = prefs.edit()
                editor.putString("firstName", firstName)
                editor.putString("lastName", lastName)
                editor.putString("email", email)
                editor.putString("address", address)
                editor.putString("zipcode", zipCode)
                editor.putString("city", city)
                editor.putString("cardRef", cardRef)
                editor.apply()

                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                val profileIntent = Intent(this, ProfileUserActivity::class.java)
                profileIntent.putExtra("firstName", firstName)
                profileIntent.putExtra("lastName", lastName)
                profileIntent.putExtra("email", email)
                profileIntent.putExtra("address", address)
                profileIntent.putExtra("zipcode", zipCode)
                profileIntent.putExtra("city", city)
                profileIntent.putExtra("cardRef", cardRef)
                startActivity(profileIntent)
                finish()

            } else {
                Toast.makeText(this, "Complete all fields", Toast.LENGTH_SHORT).show()
            }
        }
}
