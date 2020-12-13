package hi.iwansyy.mvvm.presentation.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import hi.iwansyy.mvvm.data.persistances.contracts.PostsPersistanceContract
import hi.iwansyy.mvvm.data.persistances.mappers.PostsMapper
import hi.iwansyy.mvvm.data.persistances.mappers.PostsMapperInterface
import hi.iwansyy.mvvm.data.persistances.repositories.PostsRepository
import hi.iwansyy.mvvm.data.persistances.repositories.PostsRepositoryInterface
import hi.iwansyy.mvvm.databinding.FragmentAddBinding
import hi.iwansyy.mvvm.domain.PostsDomain
import hi.iwansyy.mvvm.presentation.infrastructure.persistence.api.PostsPersistance
import hi.iwansyy.mvvm.presentation.ui.states.StatePosts
import hi.iwansyy.mvvm.presentation.ui.viewmodels.PostViewModel
import hi.iwansyy.mvvm.presentation.ui.views.activities.PostsUseCaseInterface
import hi.iwansyy.mvvm.usecase.cases.posts.PostsUseCase
import org.koin.android.ext.android.get

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val persistance: PostsPersistanceContract by lazy { PostsPersistance(get()) }
    private val mapper: PostsMapperInterface by lazy { PostsMapper() }
    private val repository: PostsRepositoryInterface by lazy { PostsRepository(persistance, mapper) }
    private val useCase: PostsUseCaseInterface by lazy { PostsUseCase(repository) }
    private val viewModel by viewModels<PostViewModel> { PostViewModel.getFactory(useCase) }

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
                            PostsDomain(userId = 0, id = 0, title = etTitle.text.toString(), body = etBody.text.toString())
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