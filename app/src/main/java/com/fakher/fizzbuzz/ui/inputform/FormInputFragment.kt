package com.fakher.fizzbuzz.ui.inputform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.fakher.fizzbuzz.databinding.FragmentFormInputBinding
import com.fakher.fizzbuzz.databinding.FragmentFormInputBindingImpl
import com.fakher.fizzbuzz.di.injector
import com.fakher.presentation.form.FormInputViewModel

class FormInputFragment : Fragment() {

    private lateinit var binding: FragmentFormInputBinding
    private val viewModel: FormInputViewModel by lazy {
        ViewModelProviders.of(this, activity?.injector?.getFormInputVMFactory())
            .get(FormInputViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFormInputBindingImpl.inflate(inflater, container, false)
            .apply {
                binding = this
                viewmodel = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.statsButton.setOnClickListener {
            val action = FormInputFragmentDirections
                .actionFormInputFragmentToStatsFragment()
            findNavController().navigate(action)
        }
    }
}