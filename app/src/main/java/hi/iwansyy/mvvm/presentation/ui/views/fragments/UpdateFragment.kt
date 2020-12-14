package hi.iwansyy.mvvm.presentation.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import hi.iwansyy.data.persistances.contracts.PostsPersistanceContract
import hi.iwansyy.data.persistances.mappers.PostsMapper
import hi.iwansyy.data.persistances.mappers.PostsMapperInterface
import hi.iwansyy.data.persistances.repositories.PostsRepository
import hi.iwansyy.data.persistances.repositories.PostsRepositoryInterface
import hi.iwansyy.mvvm.databinding.FragmentUpdateBinding
import hi.iwansyy.mvvm.presentation.infrastructure.persistence.api.PostsPersistance
import hi.iwansyy.mvvm.presentation.ui.states.StatePosts
import hi.iwansyy.mvvm.presentation.ui.viewmodels.PostViewModel
import hi.iwansyy.mvvm.presentation.ui.views.activities.PostsUseCaseInterface
import hi.iwansyy.usecase.PostsUseCase
import org.koin.android.ext.android.get

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private val persistance: PostsPersistanceContract by lazy { PostsPersistance(get()) }
    private val mapper: PostsMapperInterface by lazy { PostsMapper() }
    private val repository: PostsRepositoryInterface by lazy { PostsRepository(persistance, mapper) }
    private val useCase: PostsUseCaseInterface by lazy { PostsUseCase(repository) }
    private val viewModel by viewModels<PostViewModel> { PostViewModel.getFactory(useCase) }
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
                    val body = hi.iwansyy.domain.PostsDomain(
                        userId = args.update.userId,
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
                is StatePosts.Loading -> showLoading(true)
                is StatePosts.SuccessfullyUpdatePosts -> {
                    showLoading(false)
                    Toast.makeText(
                            requireContext(),
                            "Sukses Update id ${statePosts.posts.id}",
                            Toast.LENGTH_SHORT
                    ).show()
                }
                is StatePosts.Error -> showLoading(false)
                else -> {
                }
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.onDestroy()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pgBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}