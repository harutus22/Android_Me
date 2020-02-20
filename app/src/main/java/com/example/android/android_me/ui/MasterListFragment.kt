package com.example.android.android_me.ui


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView

import com.example.android.android_me.R
import com.example.android.android_me.data.AndroidImageAssets
import kotlin.ClassCastException

/**
 * A simple [Fragment] subclass.
 */
class MasterListFragment : Fragment() {

    lateinit var mCallback: OnImageClickListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_master_list, container, false)
        val gridView = view.findViewById<GridView>(R.id.images_grid_view)

        val adapter = MasterListAdapter(context!!, AndroidImageAssets.getAll())
        gridView.adapter = adapter

        gridView.setOnItemClickListener { parent, view, position, id ->
            mCallback.onImageSelected(position)
        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            mCallback = context as OnImageClickListener
        } catch (e: ClassCastException){
            throw ClassCastException(context.toString() + "must implement OnImageClickListener" )
        }
    }

interface OnImageClickListener{
    fun onImageSelected(position: Int)
}
}
