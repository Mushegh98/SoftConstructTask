package com.softconstruct.task.fragment.homefragment

import android.util.Log
import androidx.core.os.bundleOf
import com.google.android.material.tabs.TabLayoutMediator
import com.softconstruct.entity.uimodel.ArticleUI
import com.softconstruct.task.R
import com.softconstruct.task.base.FragmentBaseMVVM
import com.softconstruct.task.base.utils.viewBinding
import com.softconstruct.task.databinding.FragmentHomeBinding
import com.softconstruct.task.fragment.adapter.FragmentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : FragmentBaseMVVM<HomeFragmentViewModel,FragmentHomeBinding>() {

    override val viewModel: HomeFragmentViewModel by viewModel()
    override val binding: FragmentHomeBinding by viewBinding()
    private var fragmentAdapter: FragmentAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
        var goToDetail = {_:ArticleUI-> }
    }

    override fun initView() {
//        viewModel.getArticles()
        goToDetail = {
            navigateFragment(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it))
        }
        fragmentAdapter = FragmentAdapter(childFragmentManager, lifecycle)
        with(binding){
            viewPager.adapter = fragmentAdapter
            val tabTitlesList = mutableListOf<String>().apply {
                add("All Articles")
                add("Favorite Articles")
            }
//            tabLayout.addTab(tabLayout.newTab().setText("All Articles"))
//            tabLayout.addTab(tabLayout.newTab().setText("Favorite Articles"))
            TabLayoutMediator(tabLayout,viewPager){ tab, position->
                tab.text = tabTitlesList[position]
            }.attach()
//            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
//                override fun onTabSelected(tab: TabLayout.Tab?) {
//                    tab?.position?.let { viewPager.currentItem = it }
//                }
//
//                override fun onTabUnselected(tab: TabLayout.Tab?) {
//
//                }
//
//                override fun onTabReselected(tab: TabLayout.Tab?) {
//
//                }
//
//            })
//            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//                override fun onPageSelected(position: Int) {
//                    tabLayout.selectTab(tabLayout.getTabAt(position))
//                }
//            })
        }
    }

    override fun observes() {
        with(viewModel){

        }

    }

    override fun navigateUp() {
        navigateBackStack()
    }
}