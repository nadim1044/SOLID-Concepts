# SOLID-Concepts

### This repository is going to take about SOLID concepts.

## Singe Responsibility

Each class must have one, and only one, reason to change.
Like you class NotificationManager,TaskManager and etc. should have its own responsibility.

```kotlin
// Here send Notification method break the principle of Single responsibility
class TaskManager {
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
```

## Open/Closed principle

### Software entities (such asclasses and methods) must be open for extension but closed for modification.

###  

## Liskov Substitution

### Derived classes (or child classes) must be able to use parent class without any error.

## Interface Segregation principle

### A class should not be forced to implement interfaces and methods that will not be used.

## Dependency Inversion Principle

### Depend on abstractions rather than concrete implementations

