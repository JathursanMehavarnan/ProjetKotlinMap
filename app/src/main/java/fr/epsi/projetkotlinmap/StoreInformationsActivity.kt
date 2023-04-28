package fr.epsi.projetkotlinmap

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.json.JSONObject

class StoreInformationsActivity : MainActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_informations)
        val selectJson = intent.getStringExtra("selectedStore")
        val selectStore = JSONObject(selectJson)
        val storePicture = findViewById<ImageView>(R.id.storePicture)
        val infoAddress = findViewById<TextView>(R.id.infoAddress)
        val infoZipcodeCity = findViewById<TextView>(R.id.infoZipcodeCity)
        val infoDescription = findViewById<TextView>(R.id.infoDescription)
        setHeaderTxt(selectStore.getString("name"))
        showBack()

        Picasso.get().load(selectStore.getString("pictureStore")).into(storePicture)
        infoAddress.text = selectStore.getString("address")
        infoZipcodeCity.text = "${selectStore.getString("zipcode")} ${selectStore.getString("city")}"
        infoDescription.text ="Description: " + selectStore.getString("description")
    }
}

