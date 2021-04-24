package com.equipe_24.smsapp.sms_cars_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.equipe_24.smsapp.R
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SendSmsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SendSmsFragment : Fragment() {

    var CarList : Array<CarSmsClass?> = arrayOfNulls<CarSmsClass>(10)
    var Size : Int = 0

    fun saveData(){
        val  sharedPreferences = activity?.getSharedPreferences("sh", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(CarList)
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("vehicules",json).apply()
            sharedPreferences.edit().putInt("nb_vehicules",Size).apply()
        }
    }

    fun loadData(){
        val  sharedPreferences = activity?.getSharedPreferences("sh", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences?.getString("vehicules",null)
        val type = object : TypeToken<Array<CarSmsClass>>(){}.type//converting the json to list
        if (json!=null)
        {
            CarList = gson.fromJson(json,type)//returning the list
            Size = sharedPreferences?.getInt("nb_vehicules",0)!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.send_sms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn1 : Button = view.findViewById(R.id.button_une_fois)
        val btn2 : Button = view.findViewById(R.id.button_periodique)

        btn1.setOnClickListener{
            val fragManager = requireActivity().supportFragmentManager

            val fragment_maps = MapsFragment()
            val bndl = Bundle()
            bndl.putInt("indice_voiture", requireArguments().getInt("indice_voiture"))
            bndl.putInt("temps",0)

            fragment_maps.arguments = bndl

            fragManager.beginTransaction().apply {
                replace(R.id.fragment_container_sms, fragment_maps)
                addToBackStack(null)        //TODO add a name
                commit()
            }
        }

        btn2.setOnClickListener{
            val fragManager = requireActivity().supportFragmentManager

            val fragment_bottom = periode_fragment()
            val bndl = Bundle()
            bndl.putInt("indice_voiture", requireArguments().getInt("indice_voiture"))

            fragment_bottom.arguments = bndl

            fragment_bottom.show(fragManager, "Opens Add Car Dialog")
        }
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment SendSmsFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            SendSmsFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}