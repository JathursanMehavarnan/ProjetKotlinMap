package fr.epsi.projetkotlinmap

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.view.View
import android.widget.ImageView

open class MainActivity : AppCompatActivity() {
    fun setHeaderTxt(txt:String) {
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.setText(txt)
    }
    fun showBack(){
        val imageViewBack=findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility= View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}
