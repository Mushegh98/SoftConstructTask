package com.softconstruct.task.fragment.allarticlesfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softconstruct.task.base.utils.hasNetwork
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
        deleteFavoriteCallBack {
            viewModel.deleteFavoriteArticle(it)
        }
    } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(context?.hasNetwork() == true){
            viewModel.deleteAllArticles()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.allArticles.adapter = adapter
        observer()
        viewModel.getArticles()
        binding.allArticles.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getArticles()
                }
            }
        })
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
                adapter.submitList(it.toMutableList())
                adapter.notifyDataSetChanged()
            }
        }

    }

}