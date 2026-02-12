package ru.zakablukov.courses_test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.zakablukov.courses_test.databinding.ItemCourseBinding
import ru.zakablukov.domain.model.Course
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class CourseAdapter : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    private var items: MutableList<Course> = emptyList<Course>().toMutableList()

    class CourseViewHolder(
        binding: ItemCourseBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val titleTextView = binding.courseTitleTextView
        val descriptionTextView = binding.courseDescriptionTextView
        val priceTextView = binding.coursePriceTextView
        val rateTextView = binding.rateTextView
        val startDateTextView = binding.dateTextView
        val favouritesButton = binding.favouritesButton
        val moreButton = binding.moreButton
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CourseViewHolder,
        position: Int
    ) {
        val data = items[position]

        with(holder) {
            titleTextView.text = data.title
            descriptionTextView.text = data.description
            priceTextView.text = data.price
            rateTextView.text = data.rate
            startDateTextView.text =
                DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.forLanguageTag(LOCALE_STRING))
                    .withZone(ZoneId.systemDefault())
                    .format(data.startDate)
                    .split(" ")
                    .joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(data: MutableList<Course>) {
        items.addAll(data)
        notifyItemRangeInserted(items.size - 1, data.size)
    }

    companion object {
        private const val DATE_PATTERN = "dd MMMM yyyy"
        private const val LOCALE_STRING = "ru"
    }

}