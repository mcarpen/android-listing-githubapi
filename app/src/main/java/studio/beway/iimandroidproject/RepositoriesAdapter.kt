package studio.beway.iimandroidproject

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*

class RepositoriesAdapter(private val repos: List<Repo>) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repos[position]
        holder.bind(repo.name)
    }

    override fun getItemCount(): Int = repos.count()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(name: String) {
            itemView.textView.text = name

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, RepoActivity::class.java)
                intent.putExtra("name", name)
                itemView.context.startActivity(intent)
            }
        }
    }
}