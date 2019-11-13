package com.jordanrosas.kehacer.ui.chores

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jordanrosas.kehacer.data.cache.TaskCacheDataSource
import com.jordanrosas.kehacer.data.repository.task.TaskDataFactory
import com.jordanrosas.kehacer.data.repository.task.TaskDataRepository
import com.jordanrosas.kehacer.domain.usecase.InsertTask
import com.jordanrosas.kehacer.ui.R
import com.jordanrosas.kehacer.ui.chores.viewmodel.ChoresActivityViewModel
import com.jordanrosas.kehacer.ui.manager.RealmDataBaseManager
import com.jordanrosas.kehacer.ui.utils.ViewModelFactory

class ChoresActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var viewModel: ChoresActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataBaseManager = RealmDataBaseManager()
        val taskCacheSource = TaskCacheDataSource(dataBaseManager)

        val taskDataFactory = TaskDataFactory(, taskCacheSource)
        val taskRepository = TaskDataRepository(taskDataFactory)
        val insertTask = InsertTask(taskRepository)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(insertTask)
        )
            .get(ChoresActivityViewModel::class.java)

        /**
         * Para mandar llamar el view model del Activity desde un
         * fragmento se llama de la siguiente forma:
         *
         * ViewModelProviders.of(activity).get(ChoresActivityViewModel::class.java)
         */


        /*
        Integrar Retrofit

         */
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        return true
    }
}
