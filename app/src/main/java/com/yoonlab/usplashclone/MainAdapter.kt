package com.yoonlab.usplashclone

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import retrofit2.Response

class MainAdapter(val response : MutableList<SearchResult.Result?>, val context: Context) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        Log.d("Create Holder", "")
        val inflatedView = LayoutInflater.from(parent?.context).inflate(R.layout.recycler_view_layout, parent, false)
        return MainViewHolder(inflatedView, context)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val targetItem = response[position]
        holder.apply {
            itemBinder(targetItem, context)
        }
    }

    override fun getItemCount(): Int {
        return response.size
    }

}


class MainViewHolder(view: View, context: Context): RecyclerView.ViewHolder(view) {
    var itemContainer : View = view

    fun itemBinder(data: SearchResult.Result?, context: Context) {
        itemContainer.findViewById<TextView>(R.id.usrInfoTextView).text = data?.user?.username.toString()
        Glide.with(context).load(data?.urls?.regular.toString() + "&fit").
                placeholder(R.drawable.ic_baseline_image_search_24).
                into(itemContainer.findViewById<ImageView>(R.id.resultImageView))
        Glide.with(context).load(data?.user?.profileImage?.medium.toString()+ "&auto=format").
                into(itemContainer.findViewById<ImageView>(R.id.profileImageView))

    }
}