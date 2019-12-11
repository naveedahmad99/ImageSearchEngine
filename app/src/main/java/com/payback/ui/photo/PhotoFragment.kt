package com.payback.ui.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.payback.R
import com.payback.databinding.FragmentPhotoBinding
import com.payback.di.Injectable
import com.payback.utils.autoCleared
import javax.inject.Inject

class PhotoFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var binding by autoCleared<FragmentPhotoBinding>()

    private lateinit var photoViewModel: PhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_photo,
            container,
            false
        )
//        binding = dataBinding
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val params = PhotoFragmentArgs.fromBundle(arguments!!)

        photoViewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(PhotoViewModel::class.java)

        photoViewModel.setId(params.photoId)
//        photoViewModel.photo.removeObservers(viewLifecycleOwner)
        photoViewModel.photo.observe(viewLifecycleOwner, Observer {

            if (it != null) {
                binding.photo = it
            }

        })
    }

}