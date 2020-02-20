package com.example.android.android_me.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.android.android_me.R
import java.util.ArrayList

const val IMAGE_ID_LIST = "image_ids"
const val LIST_INDEX = "list_index"

class BodyPartFragment : androidx.fragment.app.Fragment() {

    private val TAG = "BodyPArtFragment"

    private var mImageIds: List<Int>? = null
    private var mListIndex: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST)
            mListIndex = savedInstanceState.getInt(LIST_INDEX)
        }
        val rootView = inflater.inflate(R.layout.fragment_body_part, container, false)

        val imageView = rootView.findViewById(R.id.body_part_image_view) as ImageView

        if (mImageIds != null){
            imageView.setImageResource(mImageIds!!.get(mListIndex))
            imageView.setOnClickListener {
                if (mListIndex < mImageIds?.size!!.minus(1)){
                    mListIndex++
                } else {
                    mListIndex = 0
                }
                imageView.setImageResource(mImageIds!!.get(mListIndex))
            }
        } else {
            Log.v(TAG, "This fragment has a null list of image id's")
        }
        return rootView
    }

    fun setImageIds(imageIds: List<Int>){
        mImageIds = imageIds
    }

    fun setListIndex(index: Int){
        mListIndex = index
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntegerArrayList(IMAGE_ID_LIST, mImageIds as ArrayList<Int>)
        outState.putInt(LIST_INDEX, mListIndex)
    }

}