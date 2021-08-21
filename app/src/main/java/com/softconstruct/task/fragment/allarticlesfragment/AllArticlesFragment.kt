package com.softconstruct.task.fragment.allarticlesfragment

import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softconstruct.task.base.utils.gone
import com.softconstruct.task.base.utils.viewBinding
import com.softconstruct.task.base.utils.visible
import com.softconstruct.task.broadcast.NetworkChangeReceiver
import com.softconstruct.task.databinding.FragmentAllArticlesBinding
import com.softconstruct.task.fragment.homefragment.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllArticlesFragment : Fragment() {

    val viewModel: AllArticlesFragmentViewModel by viewModel()
    private val binding: FragmentAllArticlesBinding by viewBinding()

    private var networkReceiver: NetworkChangeReceiver = NetworkChangeReceiver()
    private var isLoading = false

    private val adapter: AllArticleFragmentAdapter by lazy {
        AllArticleFragmentAdapter().apply {
            addSelectFavoriteCallBack {
                viewModel.saveFavorite(it)
            }
            deleteFavoriteCallBack {
                viewModel.deleteFavoriteArticle(it)
            }
            addOnItemClickCallBack {
                HomeFragment.goToDetail.invoke(it)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter()
        filter.addAction(CONNECTIVITY_ACTION)
        activity?.registerReceiver(networkReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        activity?.unregisterReceiver(networkReceiver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        networkReceiver.apply {
            addIsOnLineCallBack { isConnected ->
                if (isConnected) {
                    getArticles()
                } else {

                }
            }
        }

        with(binding) {
            allArticles.adapter = adapter
            observer()
            progressBarCenter.visible()
            viewModel.getArticles()
            allArticles.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    getArticles()
                }
            })
        }
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
        val CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"
    }

    private fun observer() {
        with(viewModel) {
            getArticlesError.observe(viewLifecycleOwner) {
                isLoading = false
                binding.progressBar.gone()
                binding.progressBarCenter.gone()
            }
            allArticleLiveData.observe(viewLifecycleOwner) {
                binding.progressBar.gone()
                binding.progressBarCenter.gone()
                isLoading = false
                adapter.submitList(it.toMutableList())
            }
        }
    }

    private fun getArticles() {
        val recyclerView = binding.allArticles
        val visibleItemCount: Int = recyclerView.layoutManager?.childCount ?: 0
        val totalItemCount: Int = recyclerView.layoutManager?.itemCount ?: 0
        val firstVisibleItemPosition: Int =
            (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()
                ?: 0

        if (visibleItemCount == 0 && !isLoading) {
            viewModel.getArticles()
            isLoading = true
            binding.progressBarCenter.visible()
        } else if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 3
            && firstVisibleItemPosition >= 0
            && totalItemCount >= 20 && !isLoading
        ) {
            viewModel.getArticles()
            isLoading = true
            binding.progressBar.visible()
        }
    }

}