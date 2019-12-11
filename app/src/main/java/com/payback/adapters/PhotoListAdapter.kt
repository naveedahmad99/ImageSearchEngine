package com.payback.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.payback.AppExecutors
import com.payback.R
import com.payback.databinding.PhotoItemBinding
import com.payback.models.Photo

class PhotoListAdapter(
    appExecutors: AppExecutors,
    private val photoClickCallback: ((Photo) -> Unit)?
) : DataBoundListAdapter<Photo, PhotoItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }
    }
) {

    override fun createBinding(parent: ViewGroup): PhotoItemBinding {
        val binding = DataBindingUtil.inflate<PhotoItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.photo_item,
            parent,
            false
        )
        binding.root.setOnClickListener {
            binding.photo?.let {
                photoClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: PhotoItemBinding, item: Photo) {
        binding.photo = item
    }


}
