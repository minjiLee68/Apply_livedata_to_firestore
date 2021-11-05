package com.sophia.firestore_mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sophia.firestore_mvvm.databinding.ItemBinding

class AppAdapter(private var mInFor: List<Infor>) : ListAdapter<Infor, AppAdapter.InForViewHolder>(

    object : DiffUtil.ItemCallback<Infor>() {
        override fun areItemsTheSame(oldItem: Infor, newItem: Infor): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Infor, newItem: Infor): Boolean {
            return oldItem.name == newItem.name && oldItem.birth == newItem.birth
        }

    }

) {

    inner class InForViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(infor: Infor) {
                binding.itemName.text = infor.name
                binding.itemBirth.text = infor.birth
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InForViewHolder =
        InForViewHolder(ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: InForViewHolder, position: Int) {
        val infor = mInFor[position]
        holder.bind(infor)
    }
}