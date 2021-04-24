package com.equipe_24.smsapp.sms_cars_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextClock
import android.widget.Toast
import androidx.core.graphics.toColor
import com.equipe_24.smsapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class periode_fragment():BottomSheetDialogFragment() {

    lateinit var editText : EditText
    lateinit var button1 : Button

    override fun getTheme(): Int{
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.send_sms_fragment, container, false)

        view.minimumHeight = 500

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText = view.findViewById(R.id.edit_nb_temps_edit)
        button1 = view.findViewById(R.id.button_bottom_sheet)

        button1.setOnClickListener {
            if (editText.text.isNullOrEmpty()) {
                Toast.makeText(context, "Please enter a valid number", Toast.LENGTH_SHORT).show()

            } else {
                val fragManager = requireActivity().supportFragmentManager

                val fragment_maps = MapsFragment()
                val bndl = Bundle()
                bndl.putInt("indice_voiture", requireArguments().getInt("indice_voiture"))
                bndl.putInt("temps", editText.text.toString().toInt())

                fragment_maps.arguments = bndl

                fragManager.beginTransaction().apply {
                    replace(R.id.fragment_container_sms, fragment_maps)
                    addToBackStack(null)        //TODO add a name
                    commit()
                }
                dialog?.dismiss()
            }

        }
    }
}