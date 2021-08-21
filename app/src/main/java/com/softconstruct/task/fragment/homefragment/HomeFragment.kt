package com.softconstruct.task.fragment.homefragment

import com.google.android.material.tabs.TabLayoutMediator
import com.softconstruct.entity.uimodel.ArticleUI
import com.softconstruct.task.base.FragmentBaseMVVM
import com.softconstruct.task.base.utils.setSoftConstructFonts
import com.softconstruct.task.base.utils.viewBinding
import com.softconstruct.task.databinding.FragmentHomeBinding
import com.softconstruct.task.fragment.adapter.FragmentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : FragmentBaseMVVM<HomeFragmentViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeFragmentViewModel by viewModel()
    override val binding: FragmentHomeBinding by viewBinding()
    private var fragmentAdapter: FragmentAdapter? = null

    companion object {
        var goToDetail = { _: ArticleUI -> }
        const val ALL_ARTICLES = "All Articles"
        const val FAVORITE_ARTICLES = "Favorite Articles"
    }

    override fun initView() {

        goToDetail = {
            navigateFragment(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it))
        }
        fragmentAdapter = FragmentAdapter(childFragmentManager, lifecycle)

        with(binding) {
            softConstructHeader.setSoftConstructFonts()
            viewPager.adapter = fragmentAdapter
            val tabTitlesList = mutableListOf<String>().apply {
                add(ALL_ARTICLES)
                add(FAVORITE_ARTICLES)
            }
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabTitlesList[position]
            }.attach()
        }
    }

    override fun navigateUp() {
        navigateBackStack()
    }
}