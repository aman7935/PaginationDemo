package com.example.paginationdemo

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArraySet
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationdemo.adapter.RvAdapter
import com.example.paginationdemo.api.RetrofitInstance
import com.example.paginationdemo.databinding.ActivityMainBinding
import com.example.paginationdemo.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private val users = mutableListOf<User>()
    private var page = 1
    private var isScrolling = false
    private var totalCount = 0
    private val adapter by lazy { RvAdapter(users) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val apiService = RetrofitInstance.userApi
        val repository = UserRepository(apiService)
        val factory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]


        binding.recyclerView2.layoutManager = LinearLayoutManager(this)
        binding.recyclerView2.adapter = adapter


        userViewModel._users.observe(this) { response ->
            isScrolling = false
            binding.progressBar.visibility = View.GONE

            response?.let {
                users.addAll(it.users)
                totalCount = it.pagination.total
                adapter.notifyDataSetChanged()
                Log.d(TAG, "Fetched users: ${users.size}")
            }
        }

        loadUsers()


        binding.recyclerView2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)
                val layoutManager = rv.layoutManager as LinearLayoutManager
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (!isScrolling && users.size < totalCount && lastVisible == totalItemCount - 1) {
                    page++
                    loadUsers()
                }
            }
        })
    }

    private fun loadUsers() {
        isScrolling = true
        binding.progressBar.visibility = View.VISIBLE

        val headers = mapOf(
            "Authorization" to "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY3ZjYxZWUzYjQ1MDRlOWVjZDhhYzI2MSIsInJvbGUiOiJhZG1pbiIsImlhdCI6MTc0NzEzMDg4NywiZXhwIjoxNzQ5NzIyODg3fQ.PRbMCqlfVfZgD_wbHrU0YX4zHyrWi1bmqcYk_VHwi7E"
        )
        Toast.makeText(this, "$page page is loaded", Toast.LENGTH_SHORT).show()

        userViewModel.loadUser(page, headers, "user", 2)
    }
}
