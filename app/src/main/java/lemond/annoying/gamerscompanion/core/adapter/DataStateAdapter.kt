package lemond.annoying.gamerscompanion.core.adapter


import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.databinding.ListItemViewMoreBinding
import lemond.annoying.gamerscompanion.main_activity.fragment_news.view.ViewMoreNewsViewHolder


abstract class DataStateAdapter<T> protected constructor(private val columnCount: Int, private val isViewMoreVisible: Boolean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList: Collection<T>? = null

    enum class ViewType {
        CONTENT,
        VIEW_MORE
    }

    protected constructor(isViewMoreVisible: Boolean) : this(1, isViewMoreVisible)

    fun setDataList(dataList: Collection<T>?) {
        val previousCount = itemCount
        this.dataList = dataList
        notifyChanges(previousCount, itemCount)
    }

    fun getDataList() : Collection<T>? {
        return dataList
    }

    private fun notifyChanges(previousCount: Int, newCount: Int) {
        if (previousCount == newCount) {
            notifyItemRangeChanged(0, newCount)
        } else if (previousCount > newCount) {
            notifyItemRangeChanged(0, newCount)
            notifyItemRangeRemoved(newCount, previousCount - newCount)
        } else if (previousCount < newCount) {
            notifyItemRangeChanged(0, previousCount)
            notifyItemRangeInserted(previousCount, newCount - previousCount)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val type = ViewType.values()[viewType]
        when (type) {
            DataStateAdapter.ViewType.VIEW_MORE -> return getViewMoreViewHolder(parent)
            DataStateAdapter.ViewType.CONTENT -> return getContentViewHolder(parent)
            else -> return getContentViewHolder(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1 && isViewMoreVisible) {
            ViewType.VIEW_MORE.ordinal
        } else {
            ViewType.CONTENT.ordinal
        }
    }

    override fun getItemCount(): Int {
        var count = 0

        if (isViewMoreVisible) {
            count++
        }

        if (dataList != null) {
            count += dataList!!.size
        }

        return count
    }

    fun getSpanSizeForGrid(position: Int): Int {
        return if (getItemViewType(position) == ViewType.CONTENT.ordinal) {
            1
        } else {
            this.columnCount
        }
    }

    /**
     * abstract method for custom content viewholder
     *
     * @param parent
     * @return
     */
    abstract fun getContentViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    /**
     * abstract method for custom view more viewholder, return null if view more button is disabled
     *
     * @param parent
     * @return
     */
    private fun getViewMoreViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val viewMoreBinding = DataBindingUtil.inflate<ListItemViewMoreBinding>(LayoutInflater.from(parent.context), R.layout.list_item_view_more, parent, false)
        return ViewMoreNewsViewHolder(viewMoreBinding)
    }

}
