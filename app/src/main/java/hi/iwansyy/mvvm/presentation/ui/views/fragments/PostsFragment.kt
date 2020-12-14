package hi.iwansyy.mvvm.presentation.ui.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import hi.iwansyy.mvvm.R
import hi.iwansyy.data.persistances.contracts.PostsPersistanceContract
import hi.iwansyy.data.persistances.mappers.PostsMapper
import hi.iwansyy.data.persistances.mappers.PostsMapperInterface
import hi.iwansyy.data.persistances.repositories.PostsRepository
import hi.iwansyy.data.persistances.repositories.PostsRepositoryInterface
import hi.iwansyy.mvvm.databinding.FragmentPostsBinding
import hi.iwansyy.mvvm.presentation.infrastructure.misc.showToast
import hi.iwansyy.mvvm.presentation.infrastructure.persistence.api.PostsPersistance
import hi.iwansyy.mvvm.presentation.ui.adapters.AdapterPost
import hi.iwansyy.mvvm.presentation.ui.states.StatePosts
import hi.iwansyy.mvvm.presentation.ui.viewmodels.PostViewModel
import hi.iwansyy.mvvm.presentation.ui.views.activities.PostsUseCaseInterface
import hi.iwansyy.usecase.PostsUseCase
import org.koin.android.ext.android.get

class PostsFragment : Fragment(), AdapterPost.PostsListener {

    private lateinit var binding: FragmentPostsBinding
    private val adapter by lazy { AdapterPost(requireContext(), this) }
    private val persistance: PostsPersistanceContract by lazy { PostsPersistance(get()) }
    private val mapper: PostsMapperInterface by lazy { PostsMapper() }
    private val repository: PostsRepositoryInterface by lazy { PostsRepository(persistance, mapper) }
    private val useCase: PostsUseCaseInterface by lazy { PostsUseCase(repository) }
    private val viewModel by viewModels<PostViewModel> { PostViewModel.getFactory(useCase) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false).apply {
            rvPosts.adapter = adapter
            btnAdd.setOnClickListener {
                findNavController().navigate(R.id.action_postsFragment_to_addFragment)
            }

        }
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is StatePosts.Loading -> showLoading(true)
                is StatePosts.Error -> {
                    showLoading(false)
                    showToast(it.exception.message ?: "Oops Something went wrong!")
                }
                is StatePosts.getAllPostsSuccessfully -> {
                    showLoading(false)
                    adapter.list = it.list.toMutableList()
                }
                is StatePosts.SuccessfullyDeletePosts -> {
                    showLoading(false)
                    adapter.deletePosts(it.posts)
                    Log.d("error","Sukses")
                    showToast("Delete Successfully")
                }
                else -> throw Exception("Unsupported State Type")
            }
        }
        return binding.root
    }

    private fun showLoading(isLoading: Boolean) {
        binding.run {
            pgBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            rvPosts.visibility = if (isLoading) View.GONE else View.VISIBLE
            btnAdd.visibility = if (isLoading) View.GONE else View.VISIBLE
        }
    }

    override fun onUpdate(domain: hi.iwansyy.domain.PostsDomain) {
        val action = PostsFragmentDirections.actionPostsFragmentToUpdateFragment(domain)
        findNavController().navigate(action)
    }

    override fun onDelete(id: Int) {
        viewModel.deletePosts(id)
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllPosts()
    }


}