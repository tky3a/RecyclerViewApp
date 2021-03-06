package ja.tutorial.recyclerviewapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    private val context: Context,
    private val ItemClickListener: RecyclerViewHolder.ItemClickListener,
    private val itemList: MutableList<MyData>
) : RecyclerView.Adapter<RecyclerViewHolder>() {
    private var mRecyclerView: RecyclerView? = null
    private var activity: MainActivity? = null

    // Adapterがアタッチされた時に発火する関数　（コールバックメソッド）
    // AttachされたらrecyclerViewを代入
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    // Adapterがデタッチされた時に発火する関数　（コールバックメソッド）
    // Detachされたらnullを代入
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null

    }

    // ViewHolder オブジェクトを生成するための実装
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        // リストアイテム用のレイアウトを設定
        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.row_main, parent, false)

        // リストをタップした時のアクション
        mView.setOnClickListener { view ->
            // TODO: クリック処理
            println("リストがタップされました")
            mRecyclerView?.let {
                // RecyclerViewHolderに定義したonItemClick関数を呼び出す
                ItemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }

        // MainActivityを取得
        activity = parent.context as MainActivity?

        // レイアウトを返却
        return RecyclerViewHolder(mView)
    }


    // 表示内容の設定
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // holderからRecyclerViewHolderで設定した内容を参照できる（データ１行ずつの情報）
        holder?.let {
            it.tvName.text = itemList.get(index = position).name
        }
        // 削除ボタン押下
        holder?.btnDel.setOnClickListener {
            Toast.makeText(context, "削除します", Toast.LENGTH_LONG).show()
            // 削除
            itemList.remove(itemList[position])
            // 削除した状態を表示するため、リスト全体を更新
            this.notifyDataSetChanged()
        }
        //
//        holder?.btnMoved.setOnClickListener {
//            fun onTouch(view: View, event: MotionEvent): Boolean {
//                // イベントアクションが、ボタンのタップを感知したら発火
//                if (event.actionMasked == MotionEvent.ACTION_DOWN) {
//                    println("aiueo")
//                }
//                return view.onTouchEvent(event)
//            }
//        }

        //  TODO:ドラッグ＆ドロップアイコンのタップイベント
        // イベントの発火はできているけど、リストの移動ができていない
        holder.btnMoved.setOnTouchListener { v, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                // 長押しではなく、タッチしてすぐにドラッグ状態にする
                holder.itemView.startDragAndDrop(null, View.DragShadowBuilder(v), v, 0)
                println("うごく？ $activity.con")
            }
            return@setOnTouchListener true
        }
        Log.e("kakunin", "$holder")
    }

    // データのサイズを返してあげるだけ
    override fun getItemCount(): Int {
        return itemList.size
    }


}