package com.example.lupin

import com.google.gson.annotations.SerializedName




class Output {
    var id:Int
    var title:String
    var body:String
    var userId:Int

    constructor(id:Int, title:String, body:String, userId:Int){
        this.id= id
        this.title = title
        this.body = body
        this.userId = userId
    }
}

