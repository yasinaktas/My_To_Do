package com.yapss.my_to_do.data.model.sealed

sealed class Status(val status:String, val text:String){
    data object All:Status(status = "all","All")
    data object Pending:Status(status = "pending","Pending")
    data object Started:Status(status = "started","Started")
    data object Finished:Status(status = "finished","Completed")
}