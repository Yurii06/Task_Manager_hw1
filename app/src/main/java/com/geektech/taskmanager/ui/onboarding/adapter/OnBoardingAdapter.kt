package com.geektech.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.geektech.taskmanager.databinding.ItemOnboardingBinding
import com.geektech.taskmanager.model.OnBoarding

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    val list = arrayListOf(
        OnBoarding(
            "Легко организуйте свои задачи и проекты",
            "Организуйте свои задачи, установите сроки и расставьте приоритеты в своей работе",
            "https://img.freepik.com/premium-vector/businessman-ticked-checkbox-after-completing-responsible-project-successful-completion-business-tasks-complete-task-accomplishment-project-done-checklist-successful-achievement-business-target_458444-1802.jpg"
        ),
        OnBoarding(
            "Всегда на связи с командой в любое время в любом месте",
            "Общайтесь, делитесь обновлениями и назначайте задачи в режиме реального времени",
            "https://d57439wlqx3vo.cloudfront.net/iblock/03c/03c3f81942edbe28e073436c1e47b227/64c181f8554dc9a0e27e654be545e45d.png"
        ),
        OnBoarding(
            "Все, что вы можете сделать в приложении",
            "Создавайте и систематизируйте задачи, устанавливайте напоминания и отслеживайте свой прогресс - все в одном месте.",
            "https://unpaper.com/wp-content/uploads/2019/06/22.-How-to-properly-utilise-the-task-list-to-complete-the-list-of-tasks.png"
        ),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun onBind(onBoarding: OnBoarding) {
            binding.tvTitle.text = onBoarding.title
            binding.tvDesc.text = onBoarding.desc
            Glide.with(binding.ivOnboard).load(onBoarding.image).into(binding.ivOnboard)
            binding.btnStart.isVisible = adapterPosition == list.lastIndex
            binding.btnStart.setOnClickListener {
                onClick()
            }
        }

    }
}