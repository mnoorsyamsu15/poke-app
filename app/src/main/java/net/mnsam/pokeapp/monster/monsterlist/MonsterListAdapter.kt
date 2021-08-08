package net.mnsam.pokeapp.monster.monsterlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.mnsam.pokeapp.databinding.MonsterListItemBinding
import net.mnsam.pokeapp.repository.list.MonsterDomain

class MonsterListAdapter : ListAdapter<MonsterDomain, MonsterListAdapter.ViewHolder>(DIFFER) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MonsterListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: MonsterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(monster: MonsterDomain) {
            Glide.with(binding.root.context)
                .load(monster.spriteDefaultUrl)
                .into(binding.ivMonster)
            val element1 = monster.element.getOrNull(0)
            val element2 = monster.element.getOrNull(1)
            binding.tvName.text = monster.name.replaceFirstChar { it.uppercase() }
            binding.tvElement1.visibility = if (element1 != null) View.VISIBLE else View.GONE
            binding.tvElement2.visibility = if (element1 != null) View.VISIBLE else View.GONE
            binding.tvElement1.text = element1
            binding.tvElement2.text = element2
        }
    }

    companion object {
        val DIFFER = object : DiffUtil.ItemCallback<MonsterDomain>() {
            override fun areItemsTheSame(oldItem: MonsterDomain, newItem: MonsterDomain): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: MonsterDomain,
                newItem: MonsterDomain
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}
