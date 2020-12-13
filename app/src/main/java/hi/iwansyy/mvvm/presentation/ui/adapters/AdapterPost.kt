package hi.iwansyy.mvvm.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hi.iwansyy.mvvm.databinding.ItemPostsBinding
import hi.iwansyy.mvvm.domain.PostsDomain

class AdapterPost(private val context: Context, private val listener: PostsListener) :
    RecyclerView.Adapter<AdapterPost.ViewHolder>() {

    interface PostsListener {
        fun onUpdate(domain: PostsDomain)
        fun onDelete(id: Int)
    }

    var list = mutableListOf<PostsDomain>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun insertPosts(domain: PostsDomain) {
        list.add(0, domain)
        notifyItemInserted(0)
    }

    fun updatePosts(domain: PostsDomain) {
        val index = list.indexOfFirst { it.id == domain.id }
        if (index != -1) {
            list[index] = domain
            notifyItemChanged(index)
        }
    }

    fun deletePosts(domain: PostsDomain) {
        val index = list.indexOfFirst { it.id == domain.id }
        if (index != -1) {
            list.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    inner class ViewHolder(val binding: ItemPostsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(domain: PostsDomain) {
            binding.run {
                tvId.text = domain.id.toString()
                tvTitle.text = domain.title
                tvBody.text = domain.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPostsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val domain by lazy {
            list[position]
        }
        holder.bindData(domain)
        holder.binding.run {
            root.setOnClickListener { listener.onUpdate(domain) }
            tvId.setOnClickListener { listener.onDelete(domain.id) }
        }

    }

    override fun getItemCount(): Int = list.size
}