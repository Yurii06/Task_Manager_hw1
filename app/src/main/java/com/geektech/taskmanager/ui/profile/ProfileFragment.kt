package com.geektech.taskmanager.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.geektech.taskmanager.R
import com.geektech.taskmanager.data.local.Pref
import com.geektech.taskmanager.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImage()
        initName()
    }

    private val fileChooserContract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { ImageUri ->
                if (ImageUri != null) {
                    Glide.with(requireContext())
                        .load(ImageUri)
                        .apply(RequestOptions.circleCropTransform())
                        .into(binding.ivProfileImage)

                    pref.saveImage(ImageUri.toString())
                }
            }

    private fun initName(){
        binding.etName.setText(pref.getName())
        binding.btnSaveName.setOnClickListener {
            pref.saveName(binding.etName.text.toString())
        }
    }
    private fun initImage(){
        Glide.with(requireContext())
            .load(pref.getImage() ?: R.drawable.ic_profile)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivProfileImage)

        binding.ivProfileImage.setOnClickListener {
            fileChooserContract.launch("image/*")
        }
    }
}
