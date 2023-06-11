package com.geektech.taskmanager.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.geektech.taskmanager.R
import com.geektech.taskmanager.databinding.FragmentNotificationsBinding
import com.geektech.taskmanager.databinding.FragmentProfileBinding
import com.geektech.taskmanager.databinding.FragmentTaskBinding
import de.hdodenhof.circleimageview.CircleImageView


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

        val profileImage = view.findViewById<CircleImageView>(R.id.image_view_profile)
        Glide.with(this)
            .load(R.drawable.pizza)
            .into(profileImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}