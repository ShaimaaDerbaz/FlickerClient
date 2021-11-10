package com.flickerclient.ui.activity

import PopularImagesAdapter
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flickerclient.data.ServiceLocator
import com.flickerclient.databinding.ActivityHomeBinding
import com.flickerclient.presenter.HomePresenter
import com.flickerclient.presenter.HomePresenterImp
import com.flickerclient.presenter.view.HomeView
import com.flickerclient.ui.adapter.EndlessScrollRecyclListener
import com.flickerclient.ui.model.FlickrImage


class HomeActivity : AppCompatActivity(), HomeView, PopularImagesAdapter.PopularItemListener {
    lateinit var binding: ActivityHomeBinding
    lateinit var mPresenter: HomePresenter
    var apiKey = "a3609e9f5da4b3adf065c943856621fb"
    var format = "json"
    var pageNum = 1
    var photosPerPage=5
    val IMAGE_URL="imageURL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        mPresenter = HomePresenterImp(ServiceLocator.instance!!.geFlickerImageRepository(), this)
        mPresenter.getPopularImages(apiKey, 5, pageNum, format, 1)

        val popularImagesAdapter = PopularImagesAdapter(mutableListOf(), this, this)
        var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
             layoutManager = GridLayoutManager(this,2, RecyclerView.VERTICAL, false)
        }
        binding.recyclerImages.layoutManager = layoutManager
        binding.recyclerImages.adapter = popularImagesAdapter
        binding.recyclerImages.itemAnimator

        binding.swipRefresh.setOnRefreshListener {
            pageNum = 1
            (binding.recyclerImages.adapter as PopularImagesAdapter).deleteItems()
            (binding.recyclerImages.adapter as PopularImagesAdapter).notifyDataSetChanged()
            mPresenter.getPopularImages(apiKey, photosPerPage, pageNum, format, 1)

        }
        initPagination()


    }

    fun initPagination() {

        binding.recyclerImages

        binding.recyclerImages.addOnScrollListener(object : EndlessScrollRecyclListener() {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {

                pageNum = (pageNum +1)
                mPresenter.getPopularImages(apiKey, photosPerPage, pageNum, format, 1)
            }
        })



    }

    override fun showPopularImages(popularImages: List<FlickrImage>) {
        binding.swipRefresh.isRefreshing = false
        if (pageNum == 1) {
            (binding.recyclerImages.adapter as PopularImagesAdapter).deleteItems()
        }
        (binding.recyclerImages.adapter as PopularImagesAdapter).addItems(popularImages)
        (binding.recyclerImages.adapter as PopularImagesAdapter).notifyDataSetChanged()


    }

    override fun onItemPopularClicked(image: FlickrImage?) {
        val i = Intent(this@HomeActivity, FullImageActivity::class.java)
        i.putExtra(IMAGE_URL,image?.imageUrl)
        this@HomeActivity.startActivity(i)
    }
}


