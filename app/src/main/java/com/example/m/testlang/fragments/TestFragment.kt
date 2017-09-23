package com.example.m.testlang.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.m.testlang.R
import kotlinx.android.synthetic.main.fragment_test.view.*

/**
 * A simple [Fragment] subclass.
 */
class TestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View=inflater!!.inflate(R.layout.fragment_test, container, false)
        view.tv_test.isSelected = true
        return view
    }
}