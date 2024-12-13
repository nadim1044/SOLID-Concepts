// Here send Notification method break the principle of Single responsibility
open class TaskManager {
    fun createTask(name: String) {
        println("Task Created $name")
    }

    fun updateTask(name: String) {
        println("Task Updated $name")
    }

    fun removeTask(taskId: Int) {
        println("Task Removed $taskId")
    }

    fun sendNotification() {
        println("Notification Sent")
    }
}

class TaskManager2 {
    fun createTask(name: String) {
        println("Task Created $name")
    }

    fun updateTask(name: String) {
        println("Task Updated $name")
    }

    fun removeTask(taskId: Int) {
        println("Task Removed $taskId")
    }
}

class NotificationManager {
    fun sendNotification() {
        println("Notification Sent")
    }
}
