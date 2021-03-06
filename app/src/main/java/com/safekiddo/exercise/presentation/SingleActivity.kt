package com.safekiddo.exercise.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.safekiddo.exercise.R
import com.safekiddo.exercise.network.Service
import com.safekiddo.exercise.network.model.PostDtoMapper
import com.safekiddo.exercise.persistance.PostDatabase
import com.safekiddo.exercise.repository.PostRepository
import com.safekiddo.exercise.repository.PostRepositoryImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SingleActivity : AppCompatActivity() {

    @Inject lateinit var service: Service
    @Inject lateinit var mapper: PostDtoMapper
    @Inject lateinit var database: PostDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        val repository: PostRepository = PostRepositoryImpl(service, mapper, database)
        repository.getPostsFromApi()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), AppBarConfiguration(findNavController(R.id.nav_host_fragment).graph))
    }
}