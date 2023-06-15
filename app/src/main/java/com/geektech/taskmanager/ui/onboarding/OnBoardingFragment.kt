package com.geektech.taskmanager.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geektech.taskmanager.databinding.FragmentOnBoardingBinding
import com.geektech.taskmanager.ui.onboarding.adapter.OnBoardingAdapter
import me.relex.circleindicator.CircleIndicator3

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private val adapter = OnBoardingAdapter(this::onClick)
    private lateinit var indicator: CircleIndicator3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        indicator = binding.indicator
        binding.viewpager.adapter = adapter
        indicator.setViewPager(binding.viewpager)
    }

    private fun onClick() {
        findNavController().navigateUp()
    }
}