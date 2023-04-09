package fr.epsi.projetkotlinmap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class HomeActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val buttonScanQrCode = findViewById<Button>(R.id.buttonScanQrCode)
        val buttonSignUp = findViewById<Button>(R.id.buttonSignUp)

        setHeaderTxt("Création de compte")

        buttonScanQrCode.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, ::class.java)
            startActivity(intent)
        })

        buttonSignUp.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, ::class.java)
            startActivity(intent)
        })
    }
}