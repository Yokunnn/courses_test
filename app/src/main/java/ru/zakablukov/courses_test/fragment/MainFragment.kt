package ru.zakablukov.courses_test.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dev.androidbroadcast.vbpd.viewBinding
import ru.zakablukov.courses_test.R
import ru.zakablukov.courses_test.adapter.CourseAdapter
import ru.zakablukov.courses_test.databinding.FragmentMainBinding
import ru.zakablukov.domain.model.Course
import java.time.LocalDate
import java.time.ZoneOffset

class MainFragment : Fragment() {

    private val binding by viewBinding(FragmentMainBinding::bind)
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
        courseAdapter.update(
            listOf<Course>(
                Course(
                    100,
                    "Java-разработчик с нуля",
                    "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
                    "999",
                    "4.9",
                    LocalDate.parse("2024-05-22").atStartOfDay().toInstant(ZoneOffset.UTC),
                    false,
                    LocalDate.parse("2024-02-02").atStartOfDay().toInstant(ZoneOffset.UTC)
                    ),
                Course(
                    100,
                    "Java-разработчик с нуля",
                    "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
                    "999",
                    "4.9",
                    LocalDate.parse("2024-05-22").atStartOfDay().toInstant(ZoneOffset.UTC),
                    false,
                    LocalDate.parse("2024-02-02").atStartOfDay().toInstant(ZoneOffset.UTC)
                ),
                Course(
                    100,
                    "Java-разработчик с нуля",
                    "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
                    "999",
                    "4.9",
                    LocalDate.parse("2024-05-22").atStartOfDay().toInstant(ZoneOffset.UTC),
                    false,
                    LocalDate.parse("2024-02-02").atStartOfDay().toInstant(ZoneOffset.UTC)
                ),
            ).toMutableList()
        )
    }

    private fun initCoursesRecyclerView() {
        with(binding.coursesRecyclerView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = courseAdapter
        }
    }

}