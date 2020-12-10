package hi.iwansyy.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import hi.iwansyy.mvvm.databinding.FragmentAddBinding
import hi.iwansyy.mvvm.model.PostsModel
import hi.iwansyy.mvvm.model.PostsService
import hi.iwansyy.mvvm.repository.ClientPosts
import hi.iwansyy.mvvm.repository.PostsRemoteRepository
import hi.iwansyy.mvvm.repository.PostsRemoteViewRepositoryImpl
import hi.iwansyy.mvvm.state.StatePosts
import hi.iwansyy.mvvm.viewmodel.PostViewModel
import hi.iwansyy.mvvm.viewmodel.PostsViewModelFactory
import org.koin.android.viewmodel.ext.android.viewModel

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val viewModel by viewModel<PostViewModel>()
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
        binding = FragmentAddBinding.inflate(inflater, container, false).apply {
            btnSave.setOnClickListener {
                if (etTitle.text.toString().isEmpty() && etBody.text.toString().isEmpty()) {
                    Toast.makeText(requireContext(), "Field must de filled", Toast.LENGTH_SHORT)
                            .show()
                } else {
                    val body =
                            PostsModel(title = etTitle.text.toString(), body = etBody.text.toString())
                    viewModel.insertPosts(body)
                }
            }
        }
        viewModel.state.observe(viewLifecycleOwner) { statePosts ->
            when (statePosts) {
                is StatePosts.Loading -> showLoading(true)
                is StatePosts.SuccessfullyInsertPosts -> {
                    showLoading(false)
                    Toast.makeText(context, "Sukses ${statePosts.posts.id}", Toast.LENGTH_SHORT)
                            .show()
                }
                is StatePosts.Error -> {
                    showLoading(false)
                }
                else -> {
                }
            }
        }
        return binding.root
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pgBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}