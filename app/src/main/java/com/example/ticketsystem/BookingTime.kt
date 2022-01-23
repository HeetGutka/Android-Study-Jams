package com.example.ticketsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ticketsystem.databinding.FragmentBookingTimeBinding
import com.example.ticketsystem.model.AppointmentViewModel

class BookingTime : Fragment() {

    private var binding: FragmentBookingTimeBinding? = null

    private val sharedViewModel: AppointmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentBookingTimeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        val intent = activity?.intent
        val user = intent?.getSerializableExtra("user")
        user?.let {
            Toast.makeText(context,"The data is $it",Toast.LENGTH_SHORT).show()
        }
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.timeFragment = this

    }

    fun goToNextScreen() {
        sharedViewModel.setDate()
        findNavController().navigate(R.id.action_bookingTime_to_bookingAvailability)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}