package com.geektech.taskmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
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

    private val adapter = TaskAdapter(this::longClick, this::onClick)

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

        addTasks()
        navigateToTaskFragment()

        binding.recyclerView.adapter = adapter
    }

    private fun navigateToTaskFragment() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun addTasks() {
        val list = App.db.taskDao().getAll()
        adapter.setTasks(list)
    }

    private fun longClick(task: Task) {
        val alertDialogDelete = AlertDialog.Builder(requireContext())
        alertDialogDelete.setMessage(getString(R.string.delete_title))

        alertDialogDelete.setPositiveButton(getString(R.string.delete)) { _, _ ->
            App.db.taskDao().delete(task)
            addTasks()
        }

        alertDialogDelete.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog?.cancel()
        }
        alertDialogDelete.create().show()
    }

    private fun onClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf(TASK_KEY to task))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TASK_KEY = "task"
    }
}
