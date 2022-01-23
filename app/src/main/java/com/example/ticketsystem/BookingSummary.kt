package com.example.ticketsystem

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ticketsystem.databinding.FragmentBookingSummaryBinding
import com.example.ticketsystem.model.AppointmentViewModel

class BookingSummary : Fragment() {

    private var binding: FragmentBookingSummaryBinding? = null

    private val sharedViewModel: AppointmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentBookingSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            viewModel = sharedViewModel

            // Assign the fragment
            summaryFragment = this@BookingSummary
        }
    }

    fun confirmBooking() {
        val appointmentSummary = getString(
            R.string.order_details,
            sharedViewModel.currentDate.toString(),
            "Super Mart",
            sharedViewModel.currentDate.toString()
        )

        val show = Toast.makeText(requireContext(), "" +appointmentSummary, Toast.LENGTH_SHORT)
        show.show()
        findNavController().navigate(R.id.action_bookingSummary_to_bookingConfirm)
    }

    fun cancelBooking() {
        val intent = Intent(getActivity(), MainActivity::class.java)
        startActivity(intent)
    }
}