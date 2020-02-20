package com.example.android.android_me.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.android_me.R
import com.example.android.android_me.data.AndroidImageAssets

class AndroidMeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_me)
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
    }
}
