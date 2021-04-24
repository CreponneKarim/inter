package com.equipe_24.smsapp.sms_cars_fragments

data class CarSmsClass(val nameT: String, val numeroT: Long) {
    private val name : String = nameT
    private val numero : Long = numeroT
    private var checked : Boolean = false

    private var users : Array<UserSmsClass?> = arrayOfNulls<UserSmsClass>(10)
    private var nb_users : Int = 0

    fun getCarName() : String{
        return this.name
    }
    fun getCarNumber() : String{
        return "+213"+this.numero.toString()
    }

    fun getChecked():Boolean{   return  this.checked}

    fun setChecked(v : Boolean){
        this.checked = v
    }

    fun addUser(u : UserSmsClass){
        if (nb_users!=10) {
            users.set(nb_users, u)
            nb_users++
        }
    }
}
