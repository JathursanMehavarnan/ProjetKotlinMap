package fr.epsi.projetkotlinmap

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class QrCodeActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)
        setHeaderTxt("Scan du QR Code")
        showBack()
        scanQrCode()
    }

    data class QrCodeData(
        val firstName: String,
        val lastName: String,
        val email: String,
        val address: String,
        val zipcode: String,
        val city: String,
        val cardRef: String
    )

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null && result.contents != null) {
            try {
                val qrCodeDatas = Gson().fromJson(result.contents, QrCodeData::class.java)
                val registrationFormIntent = Intent(this, SignUpActivity::class.java)
                registrationFormIntent.putExtra("firstName", qrCodeDatas.firstName)
                registrationFormIntent.putExtra("lastName", qrCodeDatas.lastName)
                registrationFormIntent.putExtra("email", qrCodeDatas.email)
                registrationFormIntent.putExtra("address", qrCodeDatas.address)
                registrationFormIntent.putExtra("zipCode", qrCodeDatas.zipcode)
                registrationFormIntent.putExtra("city", qrCodeDatas.city)
                registrationFormIntent.putExtra("cardRef", qrCodeDatas.cardRef)
                startActivity(registrationFormIntent)
            } catch (e: Exception) {
                Toast.makeText(this, "QR code wrong", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}