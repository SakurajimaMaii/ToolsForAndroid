/*
 * Copyright 2022 VastGui guihy2019@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.govast.vastadapter.recycleradpter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.govast.vastadapter.AdapterClickListener
import cn.govast.vastadapter.AdapterClickRegister
import cn.govast.vastadapter.AdapterItem
import cn.govast.vastadapter.AdapterLongClickListener
import cn.govast.vastadapter.base.BaseViewHolder

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/10/10
// Description: 
// Documentation:
// Reference:

abstract class VastListAdapter(
    protected val dataSource: MutableList<AdapterItem>,
    protected val factories: MutableList<BaseViewHolder.BVAdpVHFactory>,
    protected var diffCallback: DiffUtil.ItemCallback<AdapterItem>
) : ListAdapter<AdapterItem, BaseViewHolder>(diffCallback), AdapterClickRegister {

    private val type2ItemType: MutableMap<String, Int> = HashMap()
    protected var onItemClickListener: AdapterClickListener? = null
    protected var onItemLongClickListener: AdapterLongClickListener? = null

    final override fun getItemViewType(position: Int): Int {
        val item: AdapterItem = dataSource[position]
        val type: String = item.getItemType()
        if (type2ItemType[type] == null) {
            throw RuntimeException("Not found the itemType according to the position.")
        } else {
            return type2ItemType[type]!!
        }
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val targetFactory: BaseViewHolder.BVAdpVHFactory = factories[viewType]
        return targetFactory.onCreateViewHolder(parent, viewType)
    }

    final override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemData: AdapterItem = dataSource[position]
        holder.onBindData(itemData)
        holder.itemView.setOnClickListener {
            if (null != itemData.getClickEvent()) {
                itemData.getClickEvent()?.clickEventListener(holder.itemView, position)
            } else {
                onItemClickListener?.clickEventListener(holder.itemView, position)
            }
        }
        holder.itemView.setOnLongClickListener {
            val res = if (null != itemData.getLongClickEvent()) {
                itemData.getLongClickEvent()?.longClickEventListener(holder.itemView, position)
            } else {
                onItemLongClickListener?.longClickEventListener(holder.itemView, position)
            }
            return@setOnLongClickListener res ?: false
        }
    }

    final override fun getItemCount() = dataSource.size

    init {
        for (i in factories.indices) {
            val factory: BaseViewHolder.BVAdpVHFactory = factories[i]
            val type: String = factory.getVAdpVHType()
            val itemType = type2ItemType[type]
            if (itemType != null) {
                val currentFactory: String = factory.javaClass.name
                val sameFactory: String = factories[itemType].javaClass.name
                throw RuntimeException("Same type found: $currentFactory and $sameFactory")
            }
            type2ItemType[type] = i
        }
    }

    final override fun registerClickEvent(l: AdapterClickListener?) {
        onItemClickListener = l
    }

    final override fun registerLongClickEvent(l: AdapterLongClickListener?) {
        onItemLongClickListener = l
    }

    final override fun getClickEvent(): AdapterClickListener? {
        return super.getClickEvent()
    }

    final override fun getLongClickEvent(): AdapterLongClickListener? {
        return super.getLongClickEvent()
    }

}