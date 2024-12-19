package com.example.demoroomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.demoroomdatabase.R
import com.example.demoroomdatabase.databinding.ViewHolderBinding
import com.example.demoroomdatabase.entity.User

class UserAdapter(
    private val data: List<User>,
    private val onDelete: (user: User) -> Unit,
    private val onUpdate: (user: User) -> Unit

) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    inner class UserViewHolder(private val binding: ViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.tvName.text = user.name
            binding.tvAge.text = user.age.toString()
        }

        fun showMenu(user: User) {
            binding.menu.setOnClickListener {
                val popupMenu = PopupMenu(it.context, it)
                popupMenu.inflate(R.menu.menu)

                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.acDelete -> {
                            onDelete(user)
                            true
                        }

                        R.id.acUpdate -> {
                            onUpdate(user)
                            true
                        }

                        else -> false
                    }

                }
                popupMenu.show()

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val viewBinding =
            ViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
        holder.showMenu(data[position])
    }

}