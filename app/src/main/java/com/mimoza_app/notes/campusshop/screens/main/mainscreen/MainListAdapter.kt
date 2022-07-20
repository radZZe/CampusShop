package com.mimoza_app.notes.campusshop.screens.main.mainscreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.models.ShopItem
import java.util.*
import kotlin.collections.ArrayList

class MainListAdapter(val shopItemList: ArrayList<ShopItem>, private val onShopItemClickListener: ShopItemListener):  RecyclerView.Adapter<MainListAdapter.MainListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        val layout = R.layout.shop_item_card
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return MainListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {

        val shopItem = shopItemList[position]
        holder.tvTitle.text = shopItem.name.capitalize()
        holder.tvPrice.text = shopItem.price
        holder.tvBuilding.text = shopItem.building
        holder.ivPicture.setImageBitmap(getItemImage(shopItem.image))
        holder.view.setOnClickListener {
            onShopItemClickListener.onShopItemClicked(shopItem)
        }

    }

    override fun getItemCount(): Int {
        return shopItemList.size
    }

    class MainListViewHolder (val view: View): RecyclerView.ViewHolder(view){
        val tvTitle = view.findViewById<TextView>(R.id.card_title)
        val tvBuilding = view.findViewById<TextView>(R.id.card_building)
        val tvPrice = view.findViewById<TextView>(R.id.cardPriceValue)
        val ivPicture = view.findViewById<ImageView>(R.id.cardPhoto)
    }

    private fun getItemImage(encodedImage:String): Bitmap {
        val bytes = Base64.decode(encodedImage, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
    }

    fun getFilter() : Filter {
        return nameFilter
    }

    private val nameFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<ShopItem> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                shopItemList.let { filteredList.addAll(it) }
            } else {
                val query = constraint.toString().trim().toLowerCase()
                shopItemList.forEach {
                    if (it.name.toLowerCase(Locale.ROOT).contains(query)) {
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is ArrayList<*>) {
                shopItemList.clear()
                shopItemList.addAll(results.values as ArrayList<ShopItem>)
                notifyDataSetChanged()
            }
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return super.convertResultToString(resultValue)
        }
    }

    companion object {
        const val VIEW_TYPE = 1
        const val MAX_POOL_SIZE = 20
    }
}