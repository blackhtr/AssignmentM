package com.blackhtr.musinsaassignment.layout

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.blackhtr.musinsaassignment.Utils

class CustomDecoration{
    companion object{
        const val DECORATION_MARGIN:Int = 15
        const val DECORATION_SPACE:Int = 4
    }
}
class LinearDecoration(context: Context) : RecyclerView.ItemDecoration(){
    private val pixelMargin:Int = Utils.convertToPixel(context, CustomDecoration.DECORATION_MARGIN)
    private val pixelSpace:Int = Utils.convertToPixel(context, CustomDecoration.DECORATION_SPACE)
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val childPosition:Int= parent.getChildAdapterPosition(view)
        val endItemPosition:Int = (parent.adapter?.itemCount?:0) -1
        outRect.left = if(0 == childPosition) pixelMargin else pixelSpace
        outRect.right = if(endItemPosition == childPosition) pixelMargin else pixelSpace
    }
}
class GridDecoration(context: Context, space:Int, topBottom:Int) : RecyclerView.ItemDecoration(){
    private val pixelTopBtm:Int = Utils.convertToPixel(context, topBottom)
    private val pixelSpace:Int = Utils.convertToPixel(context, space)
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = pixelSpace/2
        outRect.right = pixelSpace/2
        outRect.top = pixelTopBtm/2
        outRect.bottom = pixelTopBtm/2
    }
}