package tasks

object Migration {
    val tasks = mutableListOf<Task>()

    fun version(name:String, block: Task.() -> Unit) {
        val task = Task(name)
        task.block()
        tasks.add(task)
    }
}

typealias M = Migration