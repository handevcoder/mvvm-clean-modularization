package hi.iwansyy.mvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hi.iwansyy.mvvm.databinding.ItemPostsBinding
import hi.iwansyy.mvvm.model.PostsModel
import kotlinx.android.synthetic.main.fragment_posts.view.*

class AdapterPost(private val context: Context, private val listener: PostsListener) :
    RecyclerView.Adapter<AdapterPost.ViewHolder>() {
    var list = mutableListOf<PostsModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: ItemPostsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(postsModel: PostsModel) {
            binding.run {
                tvId.text = postsModel.id.toString()
                tvTitle.text = postsModel.title.toString()
                tvBody.text = postsModel.body.toString()
            }
        }
    }

    fun insertPosts(postsModel: PostsModel) {
        list.add(0, postsModel)
        notifyItemInserted(0)
    }

    fun updatePosts(postsModel: PostsModel) {
        val index = list.indexOfFirst { it.id == postsModel.id }
        if (index != -1) {
            list[index] = postsModel
            notifyItemChanged(index)
        }
    }

    fun deletePosts(id: Int) {
        val index = list.indexOfFirst { it.id == id }
        if (index != -1) {
            list.removeAt(index)
            notifyItemRemoved(index)
        }
    }


    interface PostsListener {
        fun onUpdate(postsModel: PostsModel)
        fun onDelete(postsModel: PostsModel)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPostsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model by lazy {
            list[position]
        }
        holder.bindData(model)
        holder.binding.run {
            root.setOnClickListener { listener.onUpdate(model) }
            tvId.setOnClickListener { listener.onDelete(model) }
        }
    }

    override fun getItemCount(): Int = list.size
}