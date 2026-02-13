package ru.zakablukov.courses_test.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dev.androidbroadcast.vbpd.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.zakablukov.courses_test.R
import ru.zakablukov.courses_test.adapter.CourseAdapter
import ru.zakablukov.courses_test.databinding.FragmentFavouritesBinding
import ru.zakablukov.courses_test.viewmodel.FavouritesViewModel

class FavouritesFragment : Fragment() {

    private val binding by viewBinding(FragmentFavouritesBinding::bind)
    private val viewModel: FavouritesViewModel by viewModel()
    private lateinit var favouritesAdapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        favouritesAdapter = CourseAdapter(
            onFavClick = {
                viewModel.deleteFavCourse(it)
            }
        )

        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFavRecyclerView()

        observeFavCourses()
    }

    private fun initFavRecyclerView() {
        with(binding.favouritesRecyclerView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = favouritesAdapter
        }
    }

    private fun observeFavCourses() {
        with(viewLifecycleOwner) {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.favouriteCourses.collect { favs ->
                        favouritesAdapter.update(favs.toMutableList())
                    }
                }
            }
        }
    }

}