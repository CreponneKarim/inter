package com.equipe_24.smsapp.sms_cars_fragments

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.equipe_24.smsapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class CarsListSms : Fragment() {

    var CarList : Array<CarSmsClass?> = arrayOfNulls<CarSmsClass>(10)
    var Size : Int = 0

    fun saveData(){
        val  sharedPreferences = activity?.getSharedPreferences("sh",Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(CarList)
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("vehicules",json).apply()
            sharedPreferences.edit().putInt("nb_vehicules",Size).apply()
        }
    }

    fun loadData(){
        val  sharedPreferences = activity?.getSharedPreferences("sh",Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences?.getString("vehicules",null)
        val type = object : TypeToken<Array<CarSmsClass>>(){}.type//converting the json to list
        if (json!=null)
        {
            CarList = gson.fromJson(json,type)//returning the list
            Size = sharedPreferences?.getInt("nb_vehicules",0)!!
        }
    }

    fun InitLayoutCarsList(layout: LinearLayout){
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layout.orientation = LinearLayout.HORIZONTAL
        layout.gravity = Gravity.CENTER
        layout.weightSum = 1F
        layout.setBackgroundResource(R.drawable.button_rounded_corners)
        layout.setPadding(20)
    }

    fun InitCarSmsButton(tvDyn: Button, context: Context?, i : Int ) {
        tvDyn.text = "  "+CarList[i]?.getCarName()+"  "

        tvDyn.textSize= 18.0F
        tvDyn.setBackgroundResource(R.drawable.layout_rounded_corners)
        tvDyn.setPadding(10, 10, 10, 10)
        tvDyn.setTextColor(0xffffffff.toInt())
        tvDyn.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            0.19F
        )

        tvDyn.setOnClickListener {

            val bndl : Bundle = Bundle()
            bndl.putInt("indice_voiture",i)

            var frag_send_sms = SendSmsFragment()

            frag_send_sms.arguments = bndl


            val fragManager = requireActivity().supportFragmentManager
            fragManager.beginTransaction().apply{
                replace(R.id.fragment_container_sms, frag_send_sms)
                addToBackStack(null)        //TODO add a name
                commit()
            }
        }
    }

    fun InitCarDeleteButton(
        tvDyn: ImageButton,
        text: String,
        context: Context?,
        index: Int,
        fragmentManager: FragmentManager
    ){

        tvDyn.setBackgroundResource(R.drawable.layout_rounded_corners)
        tvDyn.setImageResource(R.drawable.ic_baseline_delete_24)

        tvDyn.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            0.41F
        )

        tvDyn.setPadding(10, 10, 10, 10)

        tvDyn.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Veuillez confirmer la suppression")
                    .setCancelable(false)
                    .setPositiveButton("Confirmer") { dialog, id ->
                        // Delete from the list
                        var bundle = Bundle()
                        val size = Size
                        DeleteItemCarArray(index)
                        saveData()
                        val fragment = CarsListSms()

                        fragmentManager.beginTransaction().replace(
                            R.id.fragment_container_sms,
                            fragment
                        ).commit()
                    }
                    .setNegativeButton("Annuler") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
            val alert = builder.create()
            alert.show()

        }
    }

    fun InitCarSettingsButton(tvDyn: ImageButton, text: String, context: Context?,  index: Int, fragmentManager: FragmentManager){


        tvDyn.setImageResource(R.drawable.ic_baseline_delete_24)

        tvDyn.setBackgroundResource(R.drawable.layout_rounded_corners)
        tvDyn.setImageResource(R.drawable.ic_baseline_settings_24)
        tvDyn.setPadding(10, 10, 10, 10)

        tvDyn.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            0.41F
        )

        tvDyn.setOnClickListener {

            val settingsSms = CarSettingsSms()
            val bundle = Bundle()
            bundle.putString("nom_vehicule", CarList.get(index)?.getCarName())
            bundle.putInt("indice_voiture", index)
            settingsSms.arguments = bundle
            fragmentManager.beginTransaction().replace(R.id.fragment_container_sms, settingsSms).addToBackStack(
                null
            ).commit()
        }
    }

    fun DeleteItemCarArray(index: Int) {
        var k = 0
        for (k in index..(Size-2))
        {
            CarList[k]=CarList[k+1]
        }
        Size = Size -1
    }

    fun CreateCarButtons(i : Int): LinearLayout{
        var layout = LinearLayout(context)
        InitLayoutCarsList(layout)

        val tvDyn = Button(context) //LE BOUTON QUI ENVOIE LE SMS
        InitCarSmsButton(tvDyn, context,i) //INITIALISER LE BOUTON

        val tvDyn2 = ImageButton(context) //BOUTON EN PLUS
        InitCarDeleteButton(
                tvDyn2,
                "X",
                context,
                i,
                requireActivity().supportFragmentManager
        )

        val tvDyn5 = ImageButton(context) //BOUTON EN PLUS
        InitCarSettingsButton(
                tvDyn5,
                "S",
                context,
                i,
                requireActivity().supportFragmentManager
        )

        val tvDyn3 = TextView(context)
        tvDyn3.text=" "
        tvDyn3.textSize=24.0F
        //val tvDyn3 = View(context)

        val tvDyn4 = TextView(context)
        tvDyn4.text=" "
        tvDyn4.textSize=24.0F

        layout.addView(tvDyn)
        layout.addView(tvDyn3)
        layout.addView(tvDyn5)
        layout.addView(tvDyn4)
        layout.addView(tvDyn2)

        return layout

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cars_list_sms, container, false)

        val carsList : LinearLayout = view.findViewById(R.id.CarsListLinearLayout)
        var number = arguments?.getLong("code_vehicule")
        var name = arguments?.getString("nom_vehicule")

        loadData()

        if (number!=null && name!=null) {

            val car = CarSmsClass(name, number)
            CarList.set(Size, car)
            Size = Size + 1
            saveData()

        }

        arguments = null


        var i=0 //POUR LE PARCOURS
        for (i in 0..Size-1)
        {

            val tvDyn6 = TextView(context)
            tvDyn6.text = ""
            tvDyn6.setPadding(0, 0, 0, 0)

            carsList.addView(CreateCarButtons(i))
            carsList.addView(tvDyn6)
        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val AddCarButton : Button = view.findViewById(R.id.AddCarButton)

        AddCarButton.setOnClickListener {
            val dialog : BottomSheetDialogFragment = AddCarSms()

            val frgManager : FragmentManager =  requireActivity().supportFragmentManager

            if (Size==10){
                    Toast.makeText(context, "La liste est complete", Toast.LENGTH_SHORT).show()
            }
            else
            {
                    dialog.show(frgManager, "Opens Add Car Dialog")
            }

        }
    }
}

