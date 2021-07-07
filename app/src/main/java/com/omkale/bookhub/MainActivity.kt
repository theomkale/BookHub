package com.omkale.bookhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinaterLayout: CoordinatorLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var navigationView: NavigationView
    lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)
        coordinaterLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navigationView)
        frameLayout = findViewById(R.id.frameLayout)
        setupToolBar()

//        hamburger is also called as actionBarDrawerToggle
//        need to create a object of it to make it functional toggle is utility
        val actionBarDrawerToggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
//        this enables drawer to listen to values on click
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
//        enables to sync with current state of icon(close or open... hamburger or backarrow)
        actionBarDrawerToggle.syncState()

        //        for onclick on navigation item selected
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.dashboard->{
                    Toast.makeText(this@MainActivity,"Dashboard",Toast.LENGTH_SHORT).show()
                }
                R.id.profile->{
                    Toast.makeText(this@MainActivity,"Profile",Toast.LENGTH_SHORT).show()
                }
                R.id.aboutApp->{
                    Toast.makeText(this@MainActivity,"About App",Toast.LENGTH_SHORT).show()
                }
                R.id.favourites->{
                    Toast.makeText(this@MainActivity,"Favourites",Toast.LENGTH_SHORT).show()
                }
                else->{
                    println("Something went wrong")
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }



    fun setupToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "BookHub"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

//    find the click on action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id= item.itemId

    //hamburger is placed on home button position so check if home button was clicked
    if(id==android.R.id.home){
        //open the drawer from left side of the app
        drawerLayout.openDrawer(GravityCompat.START)
    }
        return super.onOptionsItemSelected(item)
    }

}