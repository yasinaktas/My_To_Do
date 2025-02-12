package com.yapss.my_to_do.data.model.sealed

import com.yapss.my_to_do.R

sealed class Routes(val route:String, val iconPassive: Int, val iconActive:Int, val label:String){
    data object ToDo: Routes("To Do", R.drawable.outline_check_box_outline_blank_24, R.drawable.outline_check_box_24,"To Do")
    data object Calendar: Routes("Calendar", R.drawable.outline_calendar_today_24, R.drawable.outline_calendar_month_24 ,"Calendar")
    data object Tags: Routes("Tags", R.drawable.outline_folder_24, R.drawable.outline_folder_special_24,"Tags")
    data object Profile: Routes("Profile", R.drawable.baseline_person_pin_24, R.drawable.baseline_person_pin_24,"Profile")
}