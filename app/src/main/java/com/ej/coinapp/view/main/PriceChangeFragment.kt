package com.ej.coinapp.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ej.coinapp.R
import com.ej.coinapp.databinding.FragmentPriceChangeBinding
import com.ej.coinapp.view.adapter.PriceListUpDownRVAdapter
import timber.log.Timber


class PriceChangeFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()
    private var _binding : FragmentPriceChangeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPriceChangeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllSelectedCoinData()
        viewModel.arr15min.observe(viewLifecycleOwner){
            Timber.tag("데이터15").d(it.toString())

            val priceListUpDownRVAdapter = PriceListUpDownRVAdapter(requireContext(),it)
            binding.price15m.adapter = priceListUpDownRVAdapter
            binding.price15m.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.arr30min.observe(viewLifecycleOwner){
            Timber.tag("데이터30").d(it.toString())

            val priceListUpDownRVAdapter = PriceListUpDownRVAdapter(requireContext(),it)
            binding.price30m.adapter = priceListUpDownRVAdapter
            binding.price30m.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.arr45min.observe(viewLifecycleOwner){
            Timber.tag("데이터45").d(it.toString())

            val priceListUpDownRVAdapter = PriceListUpDownRVAdapter(requireContext(),it)
            binding.price45m.adapter = priceListUpDownRVAdapter
            binding.price45m.layoutManager = LinearLayoutManager(requireContext())
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PriceChangeFragment()
    }
}