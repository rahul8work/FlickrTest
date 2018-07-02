package com.test.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import android.view.View.VISIBLE
import com.test.R
import com.test.adapter.PicsAdapter
import com.test.model.Pics
import com.test.presenter.PicsPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PicsView {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val picsPresenter = PicsPresenter(this)
        picsPresenter.getPics()

    }

    override fun picsReady(pics: ArrayList<Pics>?) {
        if (pics != null) {

            progress_bar.visibility = GONE
            recycler_view.visibility = VISIBLE

            viewManager = GridLayoutManager(this, 2)
            viewManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    when (viewAdapter.getItemViewType(position)) {
                        PicsAdapter.ImageType -> return 1
                        PicsAdapter.InfoType -> return 2
                        else -> return -1
                    }
                }
            }

            viewAdapter = PicsAdapter(pics, object : PicsAdapter.OnItemClick {
                override fun onClick(position: Int) {

                    var index = -1
                    for (pic in pics.withIndex()) {
                        if (pic.value.showInfo == true) index = pic.index
                        if (pic.value.clickedImage == true) pic.value.clickedImage = false
                    }

                    if(index != -1) {

                        pics.remove(pics.get(index))

                    }else {

                        val parentPic = pics.get(position)
                        parentPic.clickedImage = true
                        val pic = Pics(parentPic.id, parentPic.owner, parentPic.secret, parentPic.server, parentPic.farm, parentPic.title, parentPic.ispublic, parentPic.isfriend, parentPic.isfamily, true)
                        pic.showInfo = true
                        if (position % 2 == 0)
                            pics.add(position + 2, pic)
                        else
                            pics.add(position + 1, pic)
                    }

                    viewAdapter.notifyDataSetChanged()
                }
            })

            recycler_view.adapter = viewAdapter
            recycler_view.layoutManager = viewManager

        }
    }

}
