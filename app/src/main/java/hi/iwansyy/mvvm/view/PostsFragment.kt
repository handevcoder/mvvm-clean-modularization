package hi.iwansyy.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hi.iwansyy.mvvm.R
import hi.iwansyy.mvvm.adapter.AdapterPost
import hi.iwansyy.mvvm.databinding.FragmentPostsBinding
import hi.iwansyy.mvvm.model.PostsModel
import hi.iwansyy.mvvm.state.StatePosts
import hi.iwansyy.mvvm.viewmodel.PostViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PostsFragment : Fragment(), AdapterPost.PostsListener {

    private lateinit var binding: FragmentPostsBinding
    private val viewModel by viewModel<PostViewModel>()
    private val adapter by lazy { AdapterPost(requireContext(), this) }

    /*private val service: PostsService by lazy { ClientPosts.service }
    private val remoteRepository: PostsRemoteRepository by lazy {
        PostsRemoteViewRepositoryImpl(
            service
        )
    }
    private val viewModelFactory by lazy { PostsViewModelFactory(remoteRepository) }
    private val viewModel by viewModels<PostViewModel> { viewModelFactory }
    */
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false).apply {
            rvPosts.adapter = adapter
            viewModel.state.observe(viewLifecycleOwner) {
                when (it) {
                    is StatePosts.Loading -> showLoading(true)
                    is StatePosts.getAllPostsSuccessfully -> {
                        showLoading(false)
                        adapter.list = it.list.toMutableList()
                    }
                    is StatePosts.SuccessfullyUpdatePosts -> {
                        showLoading(false)
                        adapter.updatePosts(it.posts)
                    }
                    is StatePosts.SuccessfullyDeletePosts -> {
                        showLoading(false)
                        adapter.deletePosts(it.id)
                    }

                    is StatePosts.Error -> {
                        showLoading(false)
                        Toast.makeText(
                                requireContext(),
                                "${it.exception.message}",
                                Toast.LENGTH_SHORT
                        )
                                .show()
                    }
                    else -> {
                    }
                }
            }

            btnAdd.setOnClickListener {
                findNavController().navigate(R.id.action_postsFragment_to_addFragment)
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllPosts()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pgBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onUpdate(postsModel: PostsModel) {
        val action = PostsFragmentDirections.actionPostsFragmentToUpdateFragment(postsModel)
        findNavController().navigate(action)
    }

    override fun onDelete(postsModel: PostsModel) {
        viewModel.deletePosts(postsModel)
    }
}