package com.omkale.bookhub.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.omkale.bookhub.*
import com.omkale.bookhub.fragment.AboutAppFragment
import com.omkale.bookhub.fragment.DashboardFragment
import com.omkale.bookhub.fragment.FavouritesFragment
import com.omkale.bookhub.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinaterLayout: CoordinatorLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var navigationView: NavigationView
    lateinit var frameLayout: FrameLayout
    var previousMenuItem: MenuItem?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)
        coordinaterLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navigationView)
        frameLayout = findViewById(R.id.frameLayout)

        setupToolBar()
        openDashboard()

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
            if(previousMenuItem!=null){
                previousMenuItem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it
            when(it.itemId){
                R.id.dashboard ->{
                 openDashboard()
                    drawerLayout.closeDrawers()

                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        ProfileFragment()
                    ).addToBackStack("Profile").commit()
                    supportActionBar?.title="Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.aboutApp ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        AboutAppFragment()
                    ).addToBackStack("About App").commit()
                    supportActionBar?.title="About App"
                    drawerLayout.closeDrawers()
                }
                R.id.favourites ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        FavouritesFragment()
                    ).addToBackStack("Favourites").commit()
                    supportActionBar?.title="Favourites"
                    drawerLayout.closeDrawers()
                }
                else->{
                    println("Something went wrong")
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }


//  set toolbar
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

    //open dashboard
    fun openDashboard(){
        val fragment= DashboardFragment()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()
        supportActionBar?.title="Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)
    }

    override fun onBackPressed() {
        val frag= supportFragmentManager.findFragmentById(R.id.frameLayout)
        when(frag){
            !is DashboardFragment ->openDashboard()
            else ->super.onBackPressed()
        }

    }
}