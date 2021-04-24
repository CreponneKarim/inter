package com.equipe_24.smsapp.sms_cars_fragments

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.equipe_24.smsapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddCarSms : BottomSheetDialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.SheetDialog) //SET TRANSPARENT THEME
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        return dialog

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_car_sms,container,false)

       // dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) //POUR QUE LE CAVIER NE COUVRE PAS LA FENETRE

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //POUR QUE LE CLAVIER NE COUVRE PAS LA FENTRE
        dialog?.setOnShowListener {
            val dialog = it as BottomSheetDialog
            val bottomSheet = dialog.findViewById<View>(R.id.design_bottom_sheet)
            bottomSheet?.let { sheet ->
                dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
                sheet.parent.parent.requestLayout()
            }
        }

        val validBtn : Button = view.findViewById(R.id.ValiderAddCarButton)
        val codeVehicule : EditText = view.findViewById(R.id.CarNumberEditText)
        val nomVehicule : EditText = view.findViewById(R.id.carNameEditText)
        validBtn.setOnClickListener {
            if (codeVehicule.text.trim().length==0 || nomVehicule.text.trim().length==0)
            {
                Toast.makeText(context,"C'est Vide",Toast.LENGTH_SHORT).show()
            }
            else
            {
                if (codeVehicule.text.length!=10 || codeVehicule.text.toString().get(0)!='0')
                {
                    Toast.makeText(context,"Veuillez entrer un nombre valide",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    val fragment = CarsListSms()
                    val bundle = Bundle()
                    bundle.putLong("code_vehicule",codeVehicule.text.toString().toLong())
                    bundle.putString("nom_vehicule",nomVehicule.text.toString())
                    fragment.arguments= bundle
                    val frgManager : FragmentManager =  requireActivity().supportFragmentManager
                    frgManager.beginTransaction().replace(R.id.fragment_container_sms,fragment).commit()
                }
                dialog?.dismiss()
            }

        }

    }

}