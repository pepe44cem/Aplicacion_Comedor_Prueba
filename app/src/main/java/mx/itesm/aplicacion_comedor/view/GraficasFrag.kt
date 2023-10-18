package mx.itesm.aplicacion_comedor.view

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import mx.itesm.aplicacion_comedor.viewmodel.GraficasVM
import mx.itesm.aplicacion_comedor.databinding.FragmentGraficasBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

class GraficasFrag : Fragment() {

    companion object {
        fun newInstance() = GraficasFrag()
    }

    private val viewModel: GraficasVM by viewModels()
    private lateinit var binding: FragmentGraficasBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGraficasBinding.inflate(layoutInflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_graficas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarEventos()
        registrarObservadores()
        asignarFechasHoy()
        inicializarTodasGraficas()
    }

    private fun inicializarTodasGraficas(){

        actualizarTodasGraficas()
        estilizarTodasGraficas()
    }



    private fun estilizarTodasGraficas(){

        estilizarLineChart(binding.linearChartServicio)
        estilizarBarChart(binding.barChartServicio)

        estilizarLineChart(binding.linearChartHigiene)
        estilizarBarChart(binding.barChartHigiene)

        estilizarLineChart(binding.linearChartComida)
        estilizarBarChart(binding.barChartComida)


    }

    private fun estilizarLineChart(chart: LineChart){
        val yAxis: YAxis = chart.axisLeft // get the left or right axis
        chart.axisRight.isEnabled=false
        yAxis.setLabelCount(6, true) // set the number of labels, force boolean to true for exact count
        yAxis.axisMinimum = 0f // start at zero
        yAxis.axisMaximum = 5f // the axis maximum is 5
        chart.xAxis.isEnabled=false
        chart.legend.isEnabled=false
        chart.description.isEnabled=false
        yAxis.textColor=Color.WHITE
        yAxis.gridColor=Color.WHITE
    }

    private fun estilizarBarChart(chart: BarChart){
        val yAxis: YAxis = chart.axisLeft
        val xAxis: XAxis = chart.xAxis
        chart.axisRight.isEnabled=false
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.legend.isEnabled=false
        chart.description.isEnabled=false
        yAxis.textColor=Color.WHITE
        xAxis.textColor=Color.WHITE
        yAxis.gridColor=Color.WHITE
        xAxis.setDrawGridLines(false)

    }

    private fun actualizarLineChart(chart: LineChart, entries: ArrayList<Entry>){
        val dataSet = LineDataSet(entries, "")
        dataSet.lineWidth=4f
        dataSet.color=Color.parseColor("#d560f3")
        dataSet.valueTextColor=Color.WHITE
        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.notifyDataSetChanged()
        chart.invalidate()
    }

    private fun actualizarBarChart(chart: BarChart, entries: ArrayList<BarEntry>){
        val dataSet = BarDataSet(entries, "")
        dataSet.colors = ColorTemplate.JOYFUL_COLORS.toList()
        dataSet.valueTextColor=Color.WHITE
        val barData = BarData(dataSet)
        chart.data = barData
        chart.setFitBars(true) // make the x-axis fit exactly all bars
        chart.notifyDataSetChanged()
        chart.invalidate() // refresh the chart
    }


    private fun actualizarTodasGraficas(){
        actualizarLineChart(binding.linearChartServicio, viewModel.entriesLcServicio.value!!)
        actualizarBarChart(binding.barChartServicio, viewModel.entriesBcServicio.value!!)

        actualizarLineChart(binding.linearChartHigiene, viewModel.entriesLcServicio.value!!)
        actualizarBarChart(binding.barChartHigiene, viewModel.entriesBcServicio.value!!)

        actualizarLineChart(binding.linearChartComida, viewModel.entriesLcServicio.value!!)
        actualizarBarChart(binding.barChartComida, viewModel.entriesBcServicio.value!!)

    }

    private fun asignarFechasHoy() {
        val calendar = Calendar.getInstance()
        val yearHoy = calendar.get(Calendar.YEAR)
        val mesHoy = calendar.get(Calendar.MONTH)
        val diaHoy = calendar.get(Calendar.DAY_OF_MONTH)

        viewModel.cambiarFechaDesde(yearHoy,mesHoy,diaHoy)
        viewModel.cambiarFechaHasta(yearHoy,mesHoy,diaHoy)

    }

    private fun registrarEventos() {
        binding.desdeBtn.setOnClickListener{
            val yearInicial = viewModel.yearDesde.value ?: 2021
            val mesInicial = viewModel.mesDesde.value ?: 7
            val diaInicial = viewModel.diaDesde.value ?: 26


            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                { _, year, monthOfYear, dayOfMonth ->

                    viewModel.cambiarFechaDesde(year,monthOfYear,dayOfMonth)
                },
                yearInicial,
                mesInicial,
                diaInicial
            )

            datePickerDialog.show()
        }

        binding.hastaBtn.setOnClickListener{
            val yearInicial = viewModel.yearHasta.value ?: 2021
            val mesInicial = viewModel.mesHasta.value ?: 7
            val diaInicial = viewModel.diaHasta.value ?: 26


            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                { _, year, monthOfYear, dayOfMonth ->


                    viewModel.cambiarFechaHasta(year,monthOfYear,dayOfMonth)
                },
                yearInicial,
                mesInicial,
                diaInicial
            )

            datePickerDialog.show()
        }
    }

    private fun registrarObservadores() {
        viewModel.fechaHastaString.observe(viewLifecycleOwner, Observer { nuevaFechaHasta ->
            binding.hastaBtn.text=nuevaFechaHasta
        })
        viewModel.fechaDesdeString.observe(viewLifecycleOwner, Observer { nuevaFechaDesde ->
            binding.desdeBtn.text=nuevaFechaDesde
        })
    }


}