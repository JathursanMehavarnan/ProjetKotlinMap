package fr.epsi.projetkotlinmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

class HomeActivity : MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val buttonScanQrCode = findViewById<Button>(R.id.buttonScanQrCode)
        val buttonSignUp = findViewById<Button>(R.id.buttonSignUp)
        val prefs = getSharedPreferences("MyPreference", Context.MODE_PRIVATE)

        setHeaderTxt("Création de compte")

        val firstName = prefs.getString("firstName", null)
        val lastName = prefs.getString("lastName", null)
        val email = prefs.getString("email", null)
        val address = prefs.getString("address", null)
        val zipCode = prefs.getString("zipcode", null)
        val city = prefs.getString("city", null)
        val cardRef = prefs.getString("cardRef", null)

        if (firstName != null && lastName != null && email != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("firstName", firstName)
            intent.putExtra("lastName", lastName)
            intent.putExtra("email", email)
            intent.putExtra("address", address)
            intent.putExtra("zipcode", zipCode)
            intent.putExtra("city", city)
            intent.putExtra("cardRef", cardRef)
            startActivity(intent)
            finish()

        }

        buttonScanQrCode.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, QrCodeActivity::class.java)
            startActivity(intent)
        })

        buttonSignUp.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, SignUpActivity::class.java)
            startActivity(intent)
        })
    }
}
