package com.softconstruct.task.fragment.splashfragment

import android.os.Handler
import android.os.Looper
import com.softconstruct.task.base.FragmentBaseMVVM
import com.softconstruct.task.base.utils.viewBinding
import com.softconstruct.task.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.softconstruct.task.R
import com.softconstruct.task.base.utils.setSoftConstructFonts


class SplashFragment : FragmentBaseMVVM<SplashFragmentViewModel,FragmentSplashBinding>() {

   override val viewModel: SplashFragmentViewModel by viewModel()
   override val binding: FragmentSplashBinding by viewBinding()

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }

    override fun initView() {
        binding.softConstruct.setSoftConstructFonts()

        Handler(Looper.getMainLooper()).postDelayed({
            navigateFragment(R.id.action_splashFragment_to_homeFragment)
        },2000)
    }

    override fun navigateUp() {
        navigateBackStack()
    }
}