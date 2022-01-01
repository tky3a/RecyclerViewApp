package ja.tutorial.recyclerviewapp

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// RecyclerViewにはOnItemClickListenerがset出来ないので自作
class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    // ーーーーーーーー　Adopterで参照する値を生成する　startーーーーーーーーーー
    // 変数の値がAdopterで参照する名前になる
    val tvName: TextView = view.findViewById(R.id.tvName)
    val btnDel: ImageButton = view.findViewById(R.id.btnDel)
    // ーーーーーーーー　Adopterで参照する値を生成する　endーーーーーーーーーー

    init {
        // レイアウト初期設定がある場合
        println("ここで初期値を設定する $view")
    }
}