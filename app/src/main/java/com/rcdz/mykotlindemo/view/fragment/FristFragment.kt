package com.rcdz.mykotlindemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.rcdz.mykotlindemo.R

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/12/19 11:49
 */
class FristFragment : Fragment() {
    lateinit var action:TextView
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
            return  inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        action=view.findViewById<TextView>(R.id.action)
        super.onViewCreated(view, savedInstanceState)
        action.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_fristFragment_to_twoFragment)
        }
    }
}