package com.softconstruct.task.fragment.allarticlesfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.softconstruct.entity.responsemodel.Result
import com.softconstruct.entity.uimodel.ArticleUI
import com.softconstruct.task.R
import com.softconstruct.task.base.adapter.BaseAdapter
import com.softconstruct.task.base.adapter.BaseViewHolder
import com.softconstruct.task.databinding.ArticlesItemBinding

class AllArticleFragmentAdapter : BaseAdapter<ArticlesItemBinding, ArticleUI, AllArticleFragmentAdapter.AllArticleViewHolder>(QuestionsDiffCallback()) {


    private var selectFavoriteCallBack: (item: ArticleUI) -> Unit = {}
    private var deleteFavoriteCallBack: (item: ArticleUI) -> Unit = {}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllArticleViewHolder {
        return AllArticleViewHolder(
            ArticlesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class AllArticleViewHolder(private val binding: ArticlesItemBinding) :
        BaseViewHolder<ArticleUI, ArticlesItemBinding>(binding) {

        @SuppressLint("UseCompatLoadingForDrawables")
        override fun bind(item: ArticleUI) {
            with(binding) {
                Glide.with(articleImage.context)
                    .load(item.fields?.thumbnail)
                    .into(binding.articleImage)
                articleTitle.text = item.webTitle
                articleType.text = item.type

                if(item.isFavorite){
                    favoriteArticle.setImageDrawable(articleImage.context.getDrawable(R.drawable.ic_baseline_favorite_24))
                }else{
                    favoriteArticle.setImageDrawable(articleImage.context.getDrawable(R.drawable.ic_baseline_favorite_no_selected24))
                }
                favoriteArticle.setOnClickListener {
                    if(item.isFavorite){
//                        favoriteArticle.setImageDrawable(articleImage.context.getDrawable(R.drawable.ic_baseline_favorite_no_selected24))
                        deleteFavoriteCallBack.invoke(item.apply { isFavorite = false })
                    }else{
//                        favoriteArticle.setImageDrawable(articleImage.context.getDrawable(R.drawable.ic_baseline_favorite_24))
                        selectFavoriteCallBack.invoke(item.apply { isFavorite = true })
                    }
                }
            }
        }
    }

    fun addSelectFavoriteCallBack(callback: (item: ArticleUI) -> Unit){
        selectFavoriteCallBack = callback
    }

    fun deleteFavoriteCallBack(callback: (item: ArticleUI) -> Unit){
        deleteFavoriteCallBack = callback
    }

}

class QuestionsDiffCallback : DiffUtil.ItemCallback<ArticleUI>() {
    override fun areItemsTheSame(oldItem: ArticleUI, newItem: ArticleUI): Boolean =
        oldItem.id == newItem.id


    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ArticleUI, newItem: ArticleUI): Boolean =
        oldItem == newItem
}