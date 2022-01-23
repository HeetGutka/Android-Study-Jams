package com.example.ticketsystem
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ticketsystem.databinding.FragmentBookingAvailabilityBinding
import com.example.ticketsystem.model.AppointmentViewModel

class BookingAvailability : Fragment() {

    private var binding: FragmentBookingAvailabilityBinding? = null

    private val sharedViewModel: AppointmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentBookingAvailabilityBinding.inflate(inflater, container, false)
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
            availabilityFragment = this@BookingAvailability
        }
    }

    fun goToNextScreen() {
        sharedViewModel.setDate()
        findNavController().navigate(R.id.action_bookingAvailability_to_bookingSummary)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}