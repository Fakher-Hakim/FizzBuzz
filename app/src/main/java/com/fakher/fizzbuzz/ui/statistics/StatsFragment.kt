package com.fakher.fizzbuzz.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.fakher.fizzbuzz.R
import com.fakher.fizzbuzz.databinding.FragmentStatsBinding
import com.fakher.fizzbuzz.databinding.FragmentStatsBindingImpl
import com.fakher.fizzbuzz.di.injector
import com.fakher.presentation.model.Form
import com.fakher.presentation.stats.StatsViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import java.util.*

class StatsFragment : Fragment() {

    private lateinit var binding: FragmentStatsBinding
    private lateinit var chart: BarChart
    private lateinit var formAdapter: StatisticAdapter
    private val viewModel: StatsViewModel by lazy {
        ViewModelProviders.of(this, activity?.injector?.getStatsVMFactory())
            .get(StatsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        formAdapter = StatisticAdapter()
        val binding = FragmentStatsBindingImpl.inflate(inflater, container, false)
            .apply {
                binding = this
                binding.adapter = formAdapter
                viewmodel = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chart = binding.barChart

        viewModel.stats.observe(viewLifecycleOwner, {
            formAdapter.setValues(it)
            setChartData(it.take(5))
        })

        viewModel.getStats()
    }

    private fun setChartData(forms: List<Form>) {
        configureChartView()
        val entries = ArrayList<BarEntry>()
        forms.forEachIndexed { index, form ->
            entries.add(BarEntry(index.toFloat(), form.hits.toFloat()))
        }
        val dataSet = BarDataSet(entries, "Form Hits")
        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(dataSet)
        val data = BarData(dataSets)
        data.setValueTextSize(10f)
        data.barWidth = 0.9f

        chart.data = data

        if (chart.data != null &&
            chart.data.dataSetCount > 0
        ) {
            val set1 = chart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = entries
            set1.colors = listOf(
                resources.getColor(R.color.purple_200),
                resources.getColor(R.color.purple_500),
                resources.getColor(R.color.purple_700)
            )
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        }
    }

    private fun configureChartView() {
        chart.setDrawValueAboveBar(true)
        chart.description.isEnabled = false
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(false)
    }
}