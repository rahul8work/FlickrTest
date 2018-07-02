package com.test.adapter

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.test.R
import com.test.model.Pics
import com.squareup.picasso.Picasso

class PicsAdapter(private val myDataset: List<Pics>?, private val onItemClick: OnItemClick) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ImageType = 0
        const val InfoType = 1
    }

    class ImageViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
    class TextViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ImageType) {
            val imageView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.picsadapter_image_view, parent, false) as ImageView

            return ImageViewHolder(imageView)
        } else {
            val textView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.picsadapter_text_view, parent, false) as TextView

            return TextViewHolder(textView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pic = myDataset?.get(position)
        if(holder is ImageViewHolder){

            val imageViewHolder = holder
            val url = "https://farm"+pic?.farm+".staticflickr.com/"+pic?.server+"/"+pic?.id+"_"+pic?.secret+".jpg"
            Picasso.get().load(url).into(imageViewHolder.imageView)

            imageViewHolder.imageView.setOnClickListener{
                onItemClick.onClick(position)
            }

            if(pic?.clickedImage == true){
                imageViewHolder.imageView.setBackgroundColor(ContextCompat.getColor(imageViewHolder.imageView.context, R.color.colorAccent))
            }else{
                imageViewHolder.imageView.setBackgroundColor(ContextCompat.getColor(imageViewHolder.imageView.context, android.R.color.white))
            }

        }else if(holder is TextViewHolder){

            val textViewHolder = holder
            if(getClickImagePosition() % 2 == 0){
                textViewHolder.textView.background = ContextCompat.getDrawable(textViewHolder.textView.context, R.drawable.chat1)
            }else{
                textViewHolder.textView.background = ContextCompat.getDrawable(textViewHolder.textView.context, R.drawable.chat0)
            }
            textViewHolder.textView.text = "Title : "+pic?.title +"\nOwner : "+pic?.owner+"\nIs picture public : "+ pic?.ispublic

        }
    }

    override fun getItemCount() = myDataset?.size ?: 0

    override fun getItemViewType(position: Int): Int {
        if(myDataset?.get(position)?.showInfo == true) return InfoType
        else return ImageType
    }

    fun getClickImagePosition() : Int{
        var clickImage = -1
        for (pic in myDataset!!.withIndex()) {
            if(pic.value.clickedImage == true) clickImage = pic.index
        }
        return clickImage
    }

    interface OnItemClick{
        fun onClick(position: Int)
    }
}