package studio.beway.iimandroidproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_commit.view.*

class CommitsAdapter(private val commits: List<FullCommit>) : RecyclerView.Adapter<CommitsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_commit, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fullCommit = commits[position]
        holder.bind(fullCommit.commit.author.name, fullCommit.commit.message)
    }

    override fun getItemCount(): Int = commits.count()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(name: String, message: String) {
            itemView.commitName.text = name
            itemView.commitMessage.text = message
        }
    }
}