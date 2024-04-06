package android.test.practice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * @author: Frankie
 * @Date: 2024/4/6
 * @Description:
 */
class RecyclerActivity : AppCompatActivity() {
    lateinit var adapter: UnitRecyclerAdapter;
    lateinit var recylcer: RecyclerView;
    var list: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        initView();
    }

    private fun initView() {
        adapter = UnitRecyclerAdapter();
        recylcer = findViewById(R.id.recylcer);
        recylcer.adapter = adapter;
        recylcer.layoutManager=LinearLayoutManager(this);
        for (index in 1..100){
            list.add("this is the "+index+" item")
        }

        adapter.setData(list)
    }
}