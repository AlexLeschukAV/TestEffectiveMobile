package com.example.common.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.fragment.app.Fragment
import com.example.common.databinding.ItemRespondBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class RespondDialog(
    context: Context,
     val title: String,
    private val respond: () -> Unit,
) : BottomSheetDialog(context) {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ItemRespondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameVacancy.text = "Резюме для отклика\n\n<${title}"
        window?.setGravity(Gravity.CENTER_HORIZONTAL)

        binding.addText.setOnClickListener {
            window?.setGravity(Gravity.TOP)
            binding.editMail.visibility = View.VISIBLE
            binding.addText.visibility = View.GONE
        }

        binding.btCallback.setOnClickListener {
            respond()
            dismiss()
        }
    }

    companion object {

        fun Fragment.showRespondDialog(title: String, respond: () -> Unit) {
            RespondDialog(
                context = requireContext(),
                title = title,
                respond = respond,
            ).show()
        }
    }
}