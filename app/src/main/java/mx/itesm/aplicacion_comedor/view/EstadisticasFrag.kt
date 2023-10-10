package mx.itesm.aplicacion_comedor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import mx.itesm.aplicacion_comedor.viewmodel.EstadisticasVM
import mx.itesm.aplicacion_comedor.R
import mx.itesm.aplicacion_comedor.viewmodel.FragmentAdapterEstadisticas

class EstadisticasFrag : Fragment() {

    companion object {
        fun newInstance() = EstadisticasFrag()
    }

    private lateinit var viewModel: EstadisticasVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_estadistica, container, false)

        var viewPager = rootView.findViewById(R.id.viewpager) as ViewPager2
        var tablayout = rootView.findViewById(R.id.tabLayout) as TabLayout

        val fragmentAdapter = FragmentAdapterEstadisticas(requireActivity())
        fragmentAdapter.addFragment(DatosHoyFrag(), "DATOS HOY")
        fragmentAdapter.addFragment(GraficasFrag(), "GRÁFICAS")
        fragmentAdapter.addFragment(ComentariosFrag(), "COMENTARIOS")

        viewPager.adapter=fragmentAdapter

        TabLayoutMediator(tablayout, viewPager) { tab, position ->
            tab.text = fragmentAdapter.getPageTitle(position)
        }.attach()

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstadisticasVM::class.java)
        // TODO: Use the ViewModel
    }

}
