package com.softconstruct.task.fragment.allarticlesfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softconstruct.task.base.utils.viewBinding
import com.softconstruct.task.databinding.FragmentAllArticlesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AllArticlesFragment : Fragment() {

    val viewModel: AllArticlesFragmentViewModel by viewModel()
    private val binding: FragmentAllArticlesBinding by viewBinding()
    private val adapter: AllArticleFragmentAdapter by lazy { AllArticleFragmentAdapter().apply {
        addSelectFavoriteCallBack {
            viewModel.saveFavorite(it)
        }
    } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        viewModel.getArticles()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllArticlesFragment()
    }

    fun observer() {
        with(viewModel){
            getArticlesSuccess.observe(viewLifecycleOwner){

            }
            getArticlesError.observe(viewLifecycleOwner){

            }
            allArticleLiveData.observe(viewLifecycleOwner){
                binding.allArticles.adapter = adapter
                adapter.submitList(it)
            }
        }

    }

}