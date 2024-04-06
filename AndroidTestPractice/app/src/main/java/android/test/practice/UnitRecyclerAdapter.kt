package android.test.practice


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



/**
 * @author: Frankie
 * @Date: 2024/4/6
 * @Description:
 */
class UnitRecyclerAdapter : RecyclerView.Adapter<UnitRecyclerAdapter.MyHolder>() {
    var list: ArrayList<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.tv.text = list.get(position).toString();
    }

    fun setData(list: ArrayList<String>) {
        this.list=list;
    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var tv: TextView


        init {
            tv = itemView.findViewById<TextView>(R.id.tv)
            tv.setOnClickListener{
                tv.text="hello"
            }

        }
    }

}