package com.geektech.taskmanager.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.geektech.taskmanager.R
import com.geektech.taskmanager.databinding.FragmentProfileBinding
import com.google.android.material.imageview.ShapeableImageView


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileImage = view.findViewById<ShapeableImageView>(R.id.iv_profile_image)
        Glide.with(this)
            .load(R.drawable.ic_profile)
            .into(profileImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}