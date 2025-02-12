package com.yapss.my_to_do.data.model.dto

import com.yapss.my_to_do.data.model.sealed.Status

data class DtoFilter(
    var status:Status
)