package com.softconstruct.task.fragment.homefragment

import android.util.Log
import com.softconstruct.task.base.FragmentBaseMVVM
import com.softconstruct.task.base.utils.viewBinding
import com.softconstruct.task.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : FragmentBaseMVVM<HomeFragmentViewModel,FragmentHomeBinding>() {

    override val viewModel: HomeFragmentViewModel by viewModel()
    override val binding: FragmentHomeBinding by viewBinding()

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun initView() {
        viewModel.getArticles()
    }

    override fun observes() {
        with(viewModel){
            observe(getArticlesSuccess){
                Log.d("TAG", "observes: $it")
            }
        }

    }

    override fun navigateUp() {

    }
}