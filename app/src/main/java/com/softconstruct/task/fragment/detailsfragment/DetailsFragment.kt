package com.softconstruct.task.fragment.detailsfragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.softconstruct.task.R
import com.softconstruct.task.base.FragmentBaseMVVM
import com.softconstruct.task.base.utils.viewBinding
import com.softconstruct.task.databinding.FragmentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : FragmentBaseMVVM<DetailsFragmentViewModel,FragmentDetailsBinding>() {

    override val viewModel: DetailsFragmentViewModel by viewModel()
    override val binding: FragmentDetailsBinding by viewBinding()

    private val args: DetailsFragmentArgs by navArgs()

       companion object {
           @JvmStatic
           fun newInstance() = DetailsFragment()
       }

    override fun initView() {
        val article = args.article
        with(binding){
            context?.let {
                Glide.with(it)
                    .load(article?.fields?.thumbnail)
                    .into(detailImage)
            }

            detailTitle.text = article?.webTitle
            detailBody.text =
                article?.fields?.body?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) };
        }
    }

    override fun navigateUp() {
        navigateBackStack()
    }

    override fun initViewClickListeners() {
        binding.backBtn.setOnClickListener {
            navigateUp()
        }
    }
}