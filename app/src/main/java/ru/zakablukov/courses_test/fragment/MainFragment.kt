package ru.zakablukov.courses_test.fragment

import android.os.Bundle
import android.util.Log
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
import ru.zakablukov.courses_test.databinding.FragmentMainBinding
import ru.zakablukov.courses_test.enum.LoadState
import ru.zakablukov.courses_test.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModel()
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        courseAdapter = CourseAdapter()
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCoursesRecyclerView()
        initSortButton()
        viewModel.getCourses()

        observeCoursesLoadState()
        observeCoursesResult()
    }

    private fun initCoursesRecyclerView() {
        with(binding.coursesRecyclerView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = courseAdapter
        }
    }

    private fun initSortButton() {
        binding.sortButton.setOnClickListener {
            viewModel.sortCourses()
        }
    }

    private fun observeCoursesResult() {
        with(viewLifecycleOwner) {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.coursesResult.collect { courses ->
                        courses?.let {
                            courseAdapter.update(it.toMutableList())
                        }
                    }
                }
            }
        }
    }

    private fun observeCoursesLoadState() {
        with(viewLifecycleOwner) {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.coursesLoadState.collect { loadState ->
                        when(loadState){
                            LoadState.LOADING -> Log.d(COURSES_LOAD_TAG, loadState.name)
                            LoadState.SUCCESS -> Log.d(COURSES_LOAD_TAG, loadState.name)
                            LoadState.ERROR -> Log.d(COURSES_LOAD_TAG, loadState.name)
                            null -> Log.d(COURSES_LOAD_TAG, "init")
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val COURSES_LOAD_TAG = "Courses from api"
    }

}