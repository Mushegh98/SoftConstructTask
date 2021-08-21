package com.softconstruct.task.fragment.allarticlesfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.softconstruct.entity.uimodel.ArticleUI
import com.softconstruct.task.R
import com.softconstruct.task.base.adapter.BaseAdapter
import com.softconstruct.task.base.adapter.BaseViewHolder
import com.softconstruct.task.databinding.ArticlesItemBinding

class AllArticleFragmentAdapter :
    BaseAdapter<ArticlesItemBinding, ArticleUI, AllArticleFragmentAdapter.AllArticleViewHolder>(
        ArticlesDiffCallback()
    ) {

    private var selectFavoriteCallBack: (item: ArticleUI) -> Unit = {}
    private var deleteFavoriteCallBack: (item: ArticleUI) -> Unit = {}
    private var onItemClickCallBack: (item: ArticleUI) -> Unit = {}

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

        @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
        override fun bind(item: ArticleUI) {
            with(binding) {

                Glide.with(articleImage.context)
                    .load(item.fields?.thumbnail)
                    .placeholder(articleImage.context.getDrawable(R.drawable.softconstruct_logo))
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
                    .into(binding.articleImage)

                articleTitle.text = item.webTitle
                articleType.text = "#" + item.type

                if (item.isFavorite) {
                    favoriteArticle.setImageDrawable(articleImage.context.getDrawable(R.drawable.heart_selected))
                } else {
                    favoriteArticle.setImageDrawable(articleImage.context.getDrawable(R.drawable.heart_no_selected))
                }

                favoriteArticle.setOnClickListener {
                    if (item.isFavorite) {
                        deleteFavoriteCallBack.invoke(item)
                    } else {
                        selectFavoriteCallBack.invoke(item)
                    }
                }
            }
        }

        override fun onItemClick(item: ArticleUI) {
            onItemClickCallBack.invoke(item)
        }
    }

    fun addSelectFavoriteCallBack(callback: (item: ArticleUI) -> Unit) {
        selectFavoriteCallBack = callback
    }

    fun deleteFavoriteCallBack(callback: (item: ArticleUI) -> Unit) {
        deleteFavoriteCallBack = callback
    }

    fun addOnItemClickCallBack(callback: (item: ArticleUI) -> Unit) {
        onItemClickCallBack = callback
    }
}

class ArticlesDiffCallback : DiffUtil.ItemCallback<ArticleUI>() {
    override fun areItemsTheSame(oldItem: ArticleUI, newItem: ArticleUI): Boolean =
        oldItem.id == newItem.id


    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ArticleUI, newItem: ArticleUI): Boolean =
        oldItem == newItem
}