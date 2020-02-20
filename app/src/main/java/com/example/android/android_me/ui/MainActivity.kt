package com.example.android.android_me.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.android.android_me.R
import com.example.android.android_me.data.AndroidImageAssets
import kotlinx.android.synthetic.main.fragment_master_list.*

const val HEAD_INDEX = "headIndex"
const val BODY_INDEX = "bodyIndex"
const val LEG_INDEX = "legIndex"


class MainActivity : AppCompatActivity(), MasterListFragment.OnImageClickListener {

    private var headIndex = 0
    private var bodyIndex = 0
    private var legIndex = 0

    private var mTwoPane = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findViewById<LinearLayout>(R.id.android_me_linear_layout) != null){
            mTwoPane = true

            nextButton.visibility = View.GONE

            images_grid_view.numColumns = 2

            if (savedInstanceState == null) {

                val bundle = intent.extras
                val head = bundle!!.getInt(HEAD_INDEX)
                val body = bundle.getInt(BODY_INDEX)
                val legs = bundle.getInt(LEG_INDEX)

                val headFragment = BodyPartFragment()
                headFragment.setImageIds(AndroidImageAssets.getHeads())
                headFragment.setListIndex(head)
                val fragmentManager = supportFragmentManager

                val bodyFragment = BodyPartFragment()
                bodyFragment.setImageIds(AndroidImageAssets.getBodies())
                bodyFragment.setListIndex(body)

                val legsFragment = BodyPartFragment()
                legsFragment.setImageIds(AndroidImageAssets.getLegs())
                legsFragment.setListIndex(legs)

                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment).add(R.id.body_container, bodyFragment).add(R.id.leg_container, legsFragment)
                        .commit()
            }
        } else {
            mTwoPane = false
        }

    }

    override fun onImageSelected(position: Int) {
        Toast.makeText(this, "Position clicked = $position", Toast.LENGTH_SHORT).show()

        val bodyPartNumber = position / 12
        val listIndex = position - 12 * bodyPartNumber

        if (mTwoPane){
            val newFragment = BodyPartFragment()
            when(bodyPartNumber){
                0 -> {
                    newFragment.setImageIds(AndroidImageAssets.getBodies())
                    newFragment.setListIndex(listIndex)
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit()
                }
                1 -> {
                    newFragment.setImageIds(AndroidImageAssets.getLegs())
                    newFragment.setListIndex(listIndex)
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.legs_container, newFragment)
                            .commit()
                }
                2 -> {
                    newFragment.setImageIds(AndroidImageAssets.getHeads())
                    newFragment.setListIndex(listIndex)
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit()
                }
            }
        } else {
            when (bodyPartNumber) {
                0 -> headIndex = listIndex
                1 -> bodyIndex = listIndex
                2 -> legIndex = listIndex
            }
        }

        val bundle = Bundle()
        bundle.putInt(HEAD_INDEX, headIndex)
        bundle.putInt(BODY_INDEX, bodyIndex)
        bundle.putInt(LEG_INDEX, legIndex)

        val intent = Intent(this, AndroidMeActivity::class.java)
        intent.putExtras(bundle)

        nextButton.setOnClickListener {
            startActivity(intent)
        }

    }
}
