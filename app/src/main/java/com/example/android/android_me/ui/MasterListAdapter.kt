package com.example.android.android_me.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class MasterListAdapter
/**
 * Constructor method
 * @param imageIds The list of images to display
 */(// Keeps track of the context and list of images to display
        private val mContext: Context, private val mImageIds: List<Int>) : BaseAdapter() {
    /**
     * Returns the number of items the adapter will display
     */
    override fun getCount(): Int {
        return mImageIds.size
    }

    override fun getItem(i: Int): Int? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    /**
     * Creates a new ImageView for each item referenced by the adapter
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        if (convertView == null) { // If the view is not recycled, this creates a new ImageView to hold an image
            imageView = ImageView(mContext)
            // Define the layout parameters
            imageView.adjustViewBounds = true
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView as ImageView
        }
        // Set the image resource and return the newly created ImageView
        imageView.setImageResource(mImageIds[position])
        return imageView
    }

}
