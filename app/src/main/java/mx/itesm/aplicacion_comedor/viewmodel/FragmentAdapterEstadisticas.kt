package mx.itesm.aplicacion_comedor.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapterEstadisticas(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private var fragmentList: ArrayList<Fragment> = ArrayList()
    private var fragmentTitle:ArrayList<String> = ArrayList()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle[position]
    }

    fun addFragment(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        fragmentTitle.add(title)
    }
}
