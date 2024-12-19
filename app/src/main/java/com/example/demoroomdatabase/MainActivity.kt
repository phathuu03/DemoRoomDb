package com.example.demoroomdatabase

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoroomdatabase.adapter.UserAdapter
import com.example.demoroomdatabase.databinding.ActivityMainBinding
import com.example.demoroomdatabase.entity.User
import com.example.demoroomdatabase.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var isUpdate = false
    private lateinit var newUser: User

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val editName = binding.editName

        val editAge = binding.editAge
        val btnInsert = binding.btnInsert
        val btnUpdate = binding.btnUpdate
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.btnClear.setOnClickListener {
            userViewModel.clearData()
            editAge.text.clear()
            editName.text.clear()
            btnUpdate.visibility = View.INVISIBLE
            btnInsert.visibility = View.VISIBLE
        }


        btnInsert.setOnClickListener {
            try {
                userViewModel.insert(
                    User(
                        name = editName.text.toString(),
                        age = editAge.text.toString().toInt()

                    )
                )
                editAge.text.clear()
                editName.text.clear()

            } catch (e: Exception) {
            }
        }

        btnUpdate.setOnClickListener {
            try {
                newUser.name = editName.text.toString()
                newUser.age = editAge.text.toString().toInt()
                userViewModel.update(newUser)
                btnUpdate.visibility = View.INVISIBLE
                btnInsert.visibility = View.VISIBLE
                editAge.text.clear()
                editName.text.clear()
            } catch (_: Exception) {
            }
        }


        userViewModel.users.observe(this, Observer { users ->
            recyclerView.adapter = UserAdapter(
                data = users,
                onDelete = { user ->
                    userViewModel.delete(user)
                },
                onUpdate = { user ->
                    try {
                        editName.setText(user.name)
                        editAge.setText(user.age.toString())
                        newUser = user
                        btnUpdate.visibility = View.VISIBLE
                        btnInsert.visibility = View.INVISIBLE
                    } catch (_: Exception) {
                    }

                }
            )
        })

//        if (isUpdate) {
//            btnUpdate.visibility = View.VISIBLE
//            btnInsert.visibility = View.GONE
//        } else {
//            btnUpdate.visibility = View.GONE
//            btnInsert.visibility = View.VISIBLE
//        }


    }


}