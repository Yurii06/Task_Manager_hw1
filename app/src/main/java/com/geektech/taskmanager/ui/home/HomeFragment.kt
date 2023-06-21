package com.geektech.taskmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geektech.taskmanager.App
import com.geektech.taskmanager.R
import com.geektech.taskmanager.databinding.FragmentHomeBinding
import com.geektech.taskmanager.model.Task
import com.geektech.taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val adapter = TaskAdapter(this::longClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertData()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun insertData() {
        val list = App.db.taskDao().getAll()
        adapter.setTasks(list)
    }

    private fun longClick(task: Task) {
        val alertDialogDelete = AlertDialog.Builder(requireContext())
        alertDialogDelete.setMessage("Вы действительно хотите удалить эту задачу?")

        alertDialogDelete.setPositiveButton("Удалить") { _, _ ->
            App.db.taskDao().delete(task)
            insertData()
        }

        alertDialogDelete.setNegativeButton("Отменить") { dialog, _ ->
            dialog?.cancel()
        }

        alertDialogDelete.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}