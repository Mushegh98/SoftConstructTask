package com.softconstruct.task.fragment.allarticlesfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softconstruct.task.base.utils.hasNetwork
import com.softconstruct.task.base.utils.viewBinding
import com.softconstruct.task.databinding.FragmentAllArticlesBinding
import com.softconstruct.task.fragment.homefragment.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class AllArticlesFragment : Fragment() {

    val viewModel: AllArticlesFragmentViewModel by viewModel()
    private val binding: FragmentAllArticlesBinding by viewBinding()
    private var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
    private var isLoading = false
    private val adapter: AllArticleFragmentAdapter by lazy { AllArticleFragmentAdapter().apply {
        addSelectFavoriteCallBack {
            viewModel.saveFavorite(it)
        }
        deleteFavoriteCallBack {
            viewModel.deleteFavoriteArticle(it)
        }
        addOnItemClickCallBack {
            HomeFragment.goToDetail.invoke(it)
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
        binding.allArticles.layoutManager = linearLayoutManager
        observer()
        viewModel.getArticles()
        binding.allArticles.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    viewModel.getArticles()
//                }
//            }
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = linearLayoutManager.childCount
                val totalItemCount: Int = linearLayoutManager.itemCount
                val firstVisibleItemPosition: Int = linearLayoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 3
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= 20 && !isLoading) {
                    viewModel.getArticles()
                    isLoading = true
                    binding.progressBar.visibility = View.VISIBLE
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
                binding.progressBar.visibility = View.GONE
                isLoading = false
                adapter.submitList(it.toMutableList())
            }
        }

    }

}