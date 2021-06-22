package com.fakher.fizzbuzz.ui.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.fakher.fizzbuzz.R
import com.fakher.fizzbuzz.databinding.ItemFormBinding
import com.fakher.presentation.model.Form

class StatisticAdapter : RecyclerView.Adapter<StatisticAdapter.StatisticVH>() {

    private val formList = mutableListOf<Form>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFormBinding.inflate(layoutInflater, parent, false)

        return StatisticVH(binding)
    }

    override fun onBindViewHolder(holder: StatisticVH, position: Int) {
        val currForm = formList[position]
        holder.binding.form = currForm
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int =
        formList.size

    fun setValues(forms: List<Form>) {
        formList.clear()
        formList.addAll(forms)
        notifyDataSetChanged()
    }

    class StatisticVH(val binding: ItemFormBinding) :
        RecyclerView.ViewHolder(binding.root)
}

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}