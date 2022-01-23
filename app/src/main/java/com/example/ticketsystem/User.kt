package com.example.ticketsystem

import java.io.Serializable

data class  User(var shopId:String ?=null,var shop_name : String ?= null,var shop_city : String ?= null,var contact_no : String ?= null,
                var shop_location : String ?= null, var openingtime : String ?= null,
                var closingtime : String ?= null) : Serializable
