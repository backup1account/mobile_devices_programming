package com.example.list3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findViewById<FragmentContainerView>(R.id.taskListFragment) != null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment, TaskListFragment())
                .commit()
        }
    }
}