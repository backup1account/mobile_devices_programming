package com.example.list3.room


object RoomDataGenerator {
    private const val LISTS_COUNT = 8
    private const val EXERCISES_COUNT = 6

    val LIST_ITEMS: MutableList<TaskList> = ArrayList()
    val EXERCISE_ITEMS: MutableList<Exercises> = ArrayList()

    init {
        for (i in 1..LISTS_COUNT) {
            addListItem(createListItem(i))

            for (j in 1..EXERCISES_COUNT) {
                addExerciseItem(createExerciseItem(j, i))
            }
        }
    }

    private fun addListItem(item: TaskList) {
        LIST_ITEMS.add(item)
    }

    private fun addExerciseItem(item: Exercises) {
        EXERCISE_ITEMS.add(item)
    }

    private fun createListItem(number: Int): TaskList {
        return TaskList("List $number", "Here's description for $number task list")
    }

    private fun createExerciseItem(number: Int, listNumber: Int) : Exercises {
        return Exercises(
            "Exercise $number",
            "Here's exercise $number for list $listNumber",
            listNumber
        )
    }
}