package sj.hisnul.compose

import com.example.mushafconsolidated.R

 open class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.ic_web_home_24,"home")
    object MyNetwork: BottomNavItem("My Network",R.drawable.ic_dua_one,"Dua ")
    object AddPost: BottomNavItem("Post",R.drawable.ic_baseline_bookmark_add_24,"Bookmark")

}