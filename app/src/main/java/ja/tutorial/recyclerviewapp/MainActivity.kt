package ja.tutorial.recyclerviewapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), RecyclerViewHolder.ItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // モデルから取得した値で表示
        val myList = mutableListOf<MyData>()
        var myData = MyData(1, "USA")
        myList.add(myData)
        myData = MyData(2, "Japan")
        myList.add(myData)
        myList.add(MyData(3, "Japan2"))
        myList.add(MyData(4, "Japan3"))
        myList.add(MyData(5, "Japan5"))
        myList.add(MyData(6, "Japan6"))
        myList.add(MyData(7, "Japan7"))
        myList.add(MyData(8, "Japan78"))
        myList.add(MyData(9, "Japan73"))
        myList.add(MyData(10, "Japand3"))

        // 作成したアダプッターを参照
        viewAdapter = MyAdapter(this, this, myList)
        viewManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // 表示するリストを取得
        recyclerView = findViewById<RecyclerView>(R.id.mainList).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        // ーーーーーーーー　ItemTouchHelper処理　start ーーーーーーーーー
        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT,
            0
        ) {
            // ドラッグドロップ
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder?.adapterPosition ?: 0
                val toPosition = target?.adapterPosition ?: 0

                recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)

                return true
            }

            // スワイプでの操作
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                println("スワイプした")
            }

        })

        touchHelper.attachToRecyclerView(recyclerView)
        recyclerView.addItemDecoration(touchHelper)
        // ーーーーーーーー　ItemTouchHelper処理　end ーーーーーーーーー
    }

    // RecyclerViewHolderに設定したメソッド
    // Adopterのクリックアクションから呼び出す
    override fun onItemClick(view: View, position: Int) {
        // 選択したリストの枠を太くする
        //view.background = getDrawable(R.drawable.list_border_select)
        Toast.makeText(this, "リストをタップ", Toast.LENGTH_LONG).show()
        //println("リストがタップされたらこの関数が呼び出される")
    }

    fun onAddItem(view: View) {
        println("プラスボタンタップしました")
    }
}