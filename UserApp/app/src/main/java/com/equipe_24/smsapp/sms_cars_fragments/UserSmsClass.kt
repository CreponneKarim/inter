package com.equipe_24.smsapp.sms_cars_fragments

data class UserSmsClass(val nameT: String, val numeroT: Long){
    private val name : String = nameT
    private val number : Long = numeroT

    fun getUserName() : String
    {
        return name
    }
    fun getUserNumber() : String
    {
        return "+213"+number.toString()
    }

}
