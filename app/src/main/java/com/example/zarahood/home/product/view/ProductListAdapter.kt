package com.example.zarahood.home.product.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zarahood.R
import com.example.zarahood.databinding.ProductRecyclerItemViewBinding
import com.example.zarahood.home.product.uimodels.Product
import com.example.zarahood.utils.setRemoteImage


class ProductListAdapter :
    ListAdapter<Product, ProductListAdapter.ShopItemViewHolder>(
        Product.DIFF_UTIL
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ProductRecyclerItemViewBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    ).run(::ShopItemViewHolder)


    override fun onBindViewHolder(
        holder: ShopItemViewHolder,
        position: Int
    ) = getItem(position)
        .run(holder::onBind)

    class ShopItemViewHolder(
        private val itemBinding: ProductRecyclerItemViewBinding
    ) : RecyclerView.ViewHolder(
        itemBinding.root
    ) {
        private var isExpanded: Boolean = false

        init {
            itemBinding.productsCard.setOnClickListener {
                itemBinding.rewardsTv.visibility =
                    if (itemBinding.rewardsTv.isShown) View.GONE else View.VISIBLE
                isExpanded = true
                return@setOnClickListener
            }
            itemBinding.rewardsTv.setOnClickListener {
                if (isExpanded) {
                    Toast.makeText(
                        itemBinding.root.context,
                        itemBinding.root.context.resources.getString(
                            R.string.item_click_toast_msg
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    isExpanded = false
                }
                return@setOnClickListener

            }
        }

        fun onBind(item: Product) {
            itemBinding.clothesTitle.text = item.name
            itemBinding.clothesDescTv.text = item.productDesc
            itemBinding.clothesPriceTv.text = itemBinding.root.context.getString(
                R.string.price_tv, item.price
            )
            itemBinding.clothesImg.setRemoteImage(
                imageUrl = item.productUrl,
                onErrorBlock = {
                    itemBinding.clothesImg.setImageResource(R.drawable.image_gradient)
                }
            )
        }
    }
}