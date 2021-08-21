package com.softconstruct.task.fragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.softconstruct.task.fragment.allarticlesfragment.AllArticlesFragment
import com.softconstruct.task.fragment.favoritearticlesfragment.FavoriteArticlesFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            ALL_ARTICLES_FRAGMENT -> {
                AllArticlesFragment.newInstance()
            }
            FAVORITE_ARTICLES_FRAGMENT -> {
                FavoriteArticlesFragment.newInstance()
            }
            else -> {
                AllArticlesFragment.newInstance()
            }
        }
    }

    companion object {
        const val ALL_ARTICLES_FRAGMENT = 0
        const val FAVORITE_ARTICLES_FRAGMENT = 1
    }
}