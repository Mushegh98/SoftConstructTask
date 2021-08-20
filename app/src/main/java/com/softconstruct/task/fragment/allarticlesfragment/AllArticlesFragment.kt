package com.softconstruct.task.fragment.allarticlesfragment

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softconstruct.task.base.utils.hasNetwork
import com.softconstruct.task.base.utils.viewBinding
import com.softconstruct.task.broadcast.NetworkChangeReceiver
import com.softconstruct.task.databinding.FragmentAllArticlesBinding
import com.softconstruct.task.fragment.homefragment.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class AllArticlesFragment : Fragment() {

    val viewModel: AllArticlesFragmentViewModel by viewModel()
    private val binding: FragmentAllArticlesBinding by viewBinding()
    private var networkReceiver: NetworkChangeReceiver = NetworkChangeReceiver()
    private var isLoading = false
    val CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"

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

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter()
        filter.addAction(CONNECTIVITY_ACTION)
        activity?.registerReceiver(networkReceiver,filter)
    }

    override fun onStop() {
        super.onStop()
        activity?.unregisterReceiver(networkReceiver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        networkReceiver.apply {
            addIsOnLineCallBack { connected->
                if(connected){
                    getArticles()
                }else{

                }
            }
        }

        binding.allArticles.adapter = adapter

        observer()
        binding.progressBarCenter.visibility = View.VISIBLE
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
                getArticles()
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
                isLoading = false
                binding.progressBar.visibility = View.GONE
                binding.progressBarCenter.visibility = View.GONE
            }
            allArticleLiveData.observe(viewLifecycleOwner){
                binding.progressBar.visibility = View.GONE
                binding.progressBarCenter.visibility = View.GONE
                isLoading = false
                adapter.submitList(it.toMutableList())
            }
        }
    }

    private fun getArticles() {
        val recyclerView = binding.allArticles
        val visibleItemCount: Int = recyclerView.layoutManager!!.childCount
        val totalItemCount: Int = recyclerView.layoutManager!!.itemCount
        val firstVisibleItemPosition: Int = (recyclerView.layoutManager!! as? LinearLayoutManager)!!.findFirstVisibleItemPosition()

        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 3
            && firstVisibleItemPosition >= 0
            && totalItemCount >= 20 && !isLoading) {
            viewModel.getArticles()
            isLoading = true
            binding.progressBar.visibility = View.VISIBLE
        }
    }

}