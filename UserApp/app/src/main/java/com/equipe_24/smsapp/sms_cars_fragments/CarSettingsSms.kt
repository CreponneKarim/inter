package com.equipe_24.smsapp.sms_cars_fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.equipe_24.smsapp.R
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarSettingsSms.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarSettingsSms : Fragment() {

    var CarList : Array<CarSmsClass?> = arrayOfNulls<CarSmsClass>(10)
    var Size : Int = 0

    fun saveData(){
        val  sharedPreferences = activity?.getSharedPreferences("sh", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(CarList)
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("vehicules", json).apply()
            sharedPreferences.edit().putInt("nb_vehicules", Size).apply()
        }
    }

    fun loadData(){
        val  sharedPreferences = activity?.getSharedPreferences("sh", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences?.getString("vehicules", null)
        val type = object : TypeToken<Array<CarSmsClass>>(){}.type//converting the json to list
        if (json!=null)
        {
            CarList = gson.fromJson(json, type)//returning the list
            Size = sharedPreferences?.getInt("nb_vehicules", 0)!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.root_menu, container, false)
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
        val index : Int = requireArguments().getInt("indice_voiture")

        val butn1 : Button = view.findViewById(R.id.button_list_utilisateurs)
        butn1.setOnClickListener{
            //load the list fragment here
        }


        val switch1:SwitchMaterial = view.findViewById(R.id.switch_detection_vol)

        switch1.isChecked = CarList[index]?.getChecked()!!

        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                CarList[index]?.setChecked(true)
                Toast.makeText(context,"i am true",Toast.LENGTH_SHORT).show()
            }else{
                CarList[index]?.setChecked(false)
                Toast.makeText(context,"i am false",Toast.LENGTH_SHORT).show()
            }
            saveData()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarSettingsSms.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarSettingsSms().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}