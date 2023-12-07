package com.football.view.fifa.ui.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.football.view.fifa.R
import com.football.view.fifa.databinding.FragmentPlayerDetailDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerDetailDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentPlayerDetailDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayerDetailDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.btnCheck.setOnClickListener {
//            dismiss()
//        }
    }

}