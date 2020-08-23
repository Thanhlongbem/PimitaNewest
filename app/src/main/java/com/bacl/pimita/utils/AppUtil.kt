package com.bacl.pimita.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

open class AppUtil {
    companion object{
        fun loadImageFromURL(url: String?, imageView: ImageView?, @DrawableRes resId: Int, listener: OnLoadingComplete) {
            val options = RequestOptions()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .placeholder(resId)
            Glide.with(imageView!!)
                    .load(url)
                    .apply(options)
                    .listener(object : RequestListener<Drawable?> {
                        override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                            listener.onClosed(resource)
                            return false
                        }
                    })
                    .into(imageView)
        }

        fun replaceFragmentToActivity(fragmentManager: FragmentManager,
                                      fragment: Fragment, frameId: Int, onBackstack: Boolean, tag: String?) {
            val transaction = fragmentManager.beginTransaction()
            //transaction.setCustomAnimations(R.anim.attack_fragment,R.anim.detack_fragment,R.anim.backstack_in,R.anim.backstack_out);
            transaction.replace(frameId, fragment, tag)
            if (onBackstack) {
                transaction.addToBackStack(null)
            }
            transaction.commit()
        }

        fun addFragmentToActivity(fragmentManager: FragmentManager,
                                  fragment: Fragment, frameId: Int, onBackstack: Boolean, tag: String?) {
            val currentFr = fragmentManager.findFragmentById(frameId)
            val transaction = fragmentManager.beginTransaction()
            if (currentFr != null) transaction.hide(currentFr)
            transaction.add(frameId, fragment, tag)
            if (onBackstack) {
                transaction.addToBackStack(tag)
            }
            transaction.commit()
        }

        fun addFragmentsToActivity(fragmentManager: FragmentManager, appearFragment: Fragment, fragments: List<Fragment>, frameId: Int, tag: String?) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, appearFragment, tag)
            for (fragment in fragments) {
                if (appearFragment === fragment) {
                    transaction.show(appearFragment)
                } else {
                    transaction.hide(fragment)
                }
            }
            transaction.commit()
        }
    }
}