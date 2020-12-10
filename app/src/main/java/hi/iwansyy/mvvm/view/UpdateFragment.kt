package hi.iwansyy.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import hi.iwansyy.mvvm.databinding.FragmentUpdateBinding
import hi.iwansyy.mvvm.model.PostsModel
import hi.iwansyy.mvvm.model.PostsService
import hi.iwansyy.mvvm.repository.ClientPosts
import hi.iwansyy.mvvm.repository.PostsRemoteRepository
import hi.iwansyy.mvvm.repository.PostsRemoteViewRepositoryImpl
import hi.iwansyy.mvvm.state.StatePosts
import hi.iwansyy.mvvm.viewmodel.PostViewModel
import hi.iwansyy.mvvm.viewmodel.PostsViewModelFactory
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
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
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false).apply {
            etTitle.setText(args.update.title)
            etBody.setText(args.update.body)

            btnSave.setOnClickListener {
                if (etTitle.text.isNullOrEmpty() || etBody.text.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Field must de filled", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val body = PostsModel(
                        id = args.update.id,
                        title = etTitle.text.toString(),
                        body = etBody.text.toString()
                    )
                    viewModel.updatePosts(body)
                }
            }
        }

        viewModel.state.observe(viewLifecycleOwner) { statePosts ->
            when (statePosts) {
                is StatePosts.Loading -> ShowLoading(true)
                is StatePosts.SuccessfullyUpdatePosts -> {
                    ShowLoading(false)
                    Toast.makeText(
                        requireContext(),
                        "Sukses Update id ${statePosts.posts.id}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is StatePosts.Error -> ShowLoading(false)
                else -> {
                }
            }
        }
        return binding.root
    }

    private fun ShowLoading(isLoading: Boolean) {
        binding.pgBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}