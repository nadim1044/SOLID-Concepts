# SOLID Principles

This repository discusses SOLID principles in software development, providing clear examples and explanations for each
principle.

---

## Table of Contents

- [Single Responsibility Principle (SRP)](#single-responsibility-principle-srp)
- [Open/Closed Principle (OCP)](#openclosed-principle-ocp)
- [Liskov Substitution Principle (LSP)](#liskov-substitution-principle-lsp)
- [Interface Segregation Principle (ISP)](#interface-segregation-principle-isp)
- [Dependency Inversion Principle (DIP)](#dependency-inversion-principle-dip)
- [Comparing LSP and ISP](#comparing-lsp-and-isp)

---

## Single Responsibility Principle (SRP)

### Definition:

Each class should have one, and only one, reason to change.

### Key Idea:

- A class should focus on a single responsibility to reduce complexity and improve maintainability.

### Example:

#### Violating SRP:

```kotlin
class TaskManager {
    fun createTask(name: String) {
        println("Task Created: $name")
    }

    fun updateTask(name: String) {
        println("Task Updated: $name")
    }

    fun removeTask(taskId: Int) {
        println("Task Removed: $taskId")
    }

    fun sendNotification() {
        println("Notification Sent")
    }
}
```

#### Following SRP:

```kotlin
class TaskManager {
    fun createTask(name: String) {
        println("Task Created: $name")
    }

    fun updateTask(name: String) {
        println("Task Updated: $name")
    }

    fun removeTask(taskId: Int) {
        println("Task Removed: $taskId")
    }
}

class NotificationManager {
    fun sendNotification() {
        println("Notification Sent")
    }
}
```

---

## Open/Closed Principle (OCP)

### Definition:

Software entities (classes, modules, functions) should be open for extension but closed for modification.

### Key Idea:

- Code should be designed to allow new functionality to be added without altering existing code.

### Example:

#### Violating OCP:

```kotlin
class Vehicle(type: String) {
    var vehicleType: String = type

    fun accelerateVehicle() {
        if (vehicleType == "Car") {
            println("Car speed increased")
        } else if (vehicleType == "Truck") {
            println("Truck speed increased")
        }
    }
}
```

#### Following OCP:

```kotlin
interface VehicleInterface {
    fun accelerateVehicle()
    fun brakeVehicle()
}

class Car : VehicleInterface {
    override fun accelerateVehicle() {
        println("Car speed increased")
    }

    override fun brakeVehicle() {
        println("Car speed decreased")
    }
}

class Truck : VehicleInterface {
    override fun accelerateVehicle() {
        println("Truck speed increased")
    }

    override fun brakeVehicle() {
        println("Truck speed decreased")
    }
}
```

---

## Liskov Substitution Principle (LSP)

### Definition:

Derived classes must be substitutable for their base classes without altering the behavior of the program.

### Key Idea:

- Subtypes must honor the contracts defined by their parent types.

### Example:

#### Violating LSP:

```kotlin
open class Bird {
    open fun fly() {
        println("Bird is flying")
    }
}

class Penguin : Bird() {
    override fun fly() {
        throw UnsupportedOperationException("Penguins can't fly")
    }
}
```

#### Following LSP:

```kotlin
open class Bird

open class FlyingBird : Bird() {
    open fun fly() {
        println("Bird is flying")
    }
}

class Penguin : Bird()
```

---

## Interface Segregation Principle (ISP)

### Definition:

A class should not be forced to implement interfaces it does not use.

### Key Idea:

- Break down large interfaces into smaller, more specific ones to avoid forcing unnecessary implementations.

### Example:

#### Violating ISP:

```kotlin
interface Animal {
    fun fly()
    fun swim()
}

class Dog : Animal {
    override fun fly() {
        throw UnsupportedOperationException("Dogs can't fly")
    }

    override fun swim() {
        println("Dog is swimming")
    }
}
```

#### Following ISP:

```kotlin
interface Animal2 {
    fun eat()
    fun sleep()
}

interface Flyable {
    fun fly()
}

class Dog : Animal2 {
    override fun eat() {
        println("Dog is eating")
    }
    override fun sleep() {
        println("Dog is sleeping")
    }
}
```

---

## Dependency Inversion Principle (DIP)

### Definition:

Depend on abstractions rather than concrete implementations.

1. High-level modules should not depend on low-level modules. Both should depend on abstractions.
2. Abstractions should not depend on details. Details should depend on abstractions.
   Abstraction tells what things will be done and Details tells about how things will be done. Basically abstraction
   achieve through interface or abstract class and Details through concrete class.

- Dependency injection is often used to achieve this principle.

### Example:

#### Violating DIP:

```kotlin
data class Order(val id: Int, val name: String)

class OrderRepository {
    fun saveOrder(order: Order) {
        // Logic to save the order
    }
}

class OrderService {
    private val orderRepository: OrderRepository = OrderRepository()

    fun processOrder(order: Order) {
        orderRepository.saveOrder(order)
    }
}
```

#### Following DIP:

```kotlin
// Abstraction
interface OrderRepository {
    fun saveOrder(order: Order)
}

// Implementation
class OrderRepositoryImpl : OrderRepository {
    override fun saveOrder(order: Order) {
        // Logic to save the order
    }
}

// OrderService depends on the abstraction (OrderRepository) instead of the concrete implementation
//  if we have provided OrderRepositoryImpl in constructor then it might have depended on Details
// But right now it depends on abstraction as DIP state
class OrderService(private val orderRepository: OrderRepository) {
    fun processOrder(order: Order) {
        orderRepository.saveOrder(order)
    }
}
```

---

## Comparing LSP and ISP

### Interface Segregation Principle (ISP):

- **Focus**: Ensures that interfaces are small, specific, and tailored to the needs of individual clients.
- **Goal**: Prevents classes from being forced to implement methods they don't need.

#### Example:

```kotlin
interface Animal {
    fun fly()
    fun swim()
}

class Dog : Animal {
    override fun fly() {
        throw UnsupportedOperationException("Dogs can't fly")
    }

    override fun swim() {
        println("Dog is swimming")
    }
}

// Refactored:
interface Swimmable {
    fun swim()
}

interface Flyable {
    fun fly()
}

class Dog : Swimmable {
    override fun swim() {
        println("Dog is swimming")
    }
}
```

### Liskov Substitution Principle (LSP):

- **Focus**: Ensures that derived classes can substitute their base classes without breaking the application.
- **Goal**: Maintains the behavior expected of a base class when used through its derived classes.

#### Example:

```kotlin
open class Bird {
    open fun fly() {
        println("Bird is flying")
    }
}

class Penguin : Bird() {
    override fun fly() {
        throw UnsupportedOperationException("Penguins can't fly")
    }
}

// Refactored:
open class Bird

open class FlyingBird : Bird() {
    open fun fly() {
        println("Bird is flying")
    }
}

class Penguin : Bird()
```

---

By adhering to SOLID principles, you can design software that is robust, scalable, and maintainable.

