package com.softconstruct.task.fragment.splashfragment

import android.os.Bundle
import com.softconstruct.task.R
import com.softconstruct.task.base.FragmentBaseMVVM
import com.softconstruct.task.base.utils.hasNetwork
import com.softconstruct.task.base.utils.setSoftConstructFonts
import com.softconstruct.task.base.utils.viewBinding
import com.softconstruct.task.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashFragment : FragmentBaseMVVM<SplashFragmentViewModel, FragmentSplashBinding>() {

    override val viewModel: SplashFragmentViewModel by viewModel()
    override val binding: FragmentSplashBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (context?.hasNetwork() == true) {
            viewModel.deleteAllArticles()
        }
    }

    override fun initView() {
        binding.softConstruct.setSoftConstructFonts()
        viewModel.goToHomePage()
    }

    override fun navigateUp() {
        navigateBackStack()
    }

    override fun observes() {
        with(viewModel) {
            observe(goToHome) {
                navigateFragment(R.id.action_splashFragment_to_homeFragment)
            }
        }
    }
}