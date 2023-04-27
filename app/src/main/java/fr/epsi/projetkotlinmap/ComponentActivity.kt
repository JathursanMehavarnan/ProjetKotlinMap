package fr.epsi.projetkotlinmap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class ComponentActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component)

        val iconUser = findViewById<ImageView>(R.id.user_icon)
        iconUser.visibility= View.VISIBLE
        iconUser.setOnClickListener {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }


    val viewPager = findViewById<ViewPager>(R.id.viewPager)
    val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
    val tabTextInactiveColor = resources.getColor(R.color.red)
    val tabTextActiveColor = resources.getColor(android.R.color.white)
    tabLayout.setTabTextColors(tabTextInactiveColor, tabTextActiveColor)
    val adapter = ViewPagerAdapter(supportFragmentManager)
    adapter.addFragment(UserCardFragment(), "Carte")
    adapter.addFragment(ProductOffersFragment(), "Offres")
    adapter.addFragment(StoreFragment(), "Magasins")
    viewPager.adapter = adapter
    tabLayout.setupWithViewPager(viewPager)

}
internal class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val fragmentTitleList: MutableList<String> = ArrayList()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }
    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
}
}