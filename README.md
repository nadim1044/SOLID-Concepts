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

Software entities (such asclasses and methods) must be open for extension but closed for modification.

```kotlin
class Vehicle(
    type: String
) {
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

```kotlin
interface VehicleInterface {
    fun accelerateVehicle()
    fun breakVehicle()
}

class Car : VehicleInterface {
    override fun accelerateVehicle() {
        println("Increased the speed of Car")
    }

    override fun breakVehicle() {
        println("Decreased the speed of Car")
    }
}

class Truck : VehicleInterface {
    override fun accelerateVehicle() {
        println("Increased the speed of Truck")
    }

    override fun breakVehicle() {
        println("Decreased the speed of Truck")
    }
}
```

## Liskov Substitution

Derived classes (or child classes) must be able to use parent class without any error.
On previous example of OpenClosedPrinciple we have created Vehicle interface, and it had two functions
accelerateVehicle and breakVehicle. Does each and every vehicle have feature of accelerate???
answer No. Bicycle don't have this feature. So here we break the Liskov principle by implementing vehicle interface.

```kotlin
interface Vehicle2 {
    fun breakVehicle()
}
open class AccelerateVehicle : Vehicle2 {
    override fun breakVehicle() {
        println("Decrease the speed")
    }
    open fun accelerateVehicle() {}
}

open class PedalVehicle : Vehicle2 {
    override fun breakVehicle() {
        println("Decrease the speed")
    }
    open fun pedalVehicle() {
        println("Increase the speed")
    }
}


class Bicycle : PedalVehicle() {
    override fun pedalVehicle() {
        println("Increased the speed")
    }

    override fun breakVehicle() {
        println("Decreased the speed")
    }
}

class Bike : AccelerateVehicle() {
    override fun accelerateVehicle() {
        println("Increase the speed of Bike")
    }

    override fun breakVehicle() {
        println("Decrease the speed of Bike")
    }
}
```

## Interface Segregation principle

A class should not be forced to implement interfaces and methods that will not be used.
Interface Segregation Principle tells us we should not enforce class to implement interface that it does not use.
Suppose we have created interface of Animal and adds a function of fly. And we create class of Dog with it.
Does dog can fly? of course not. at least i have not seen flying Dogs? HAHA
So we must segregate the interface of flying here.

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

interface Animal2 {
    fun eat()
    fun sleep()
    fun swim()
}

interface Flyable {
    fun fly()
}

class Dog2 : Animal2 {

    override fun eat() {
        TODO("Not yet implemented")
    }

    override fun sleep() {
        TODO("Not yet implemented")
    }

    override fun swim() {
        println("Dog is swimming")
    }
}
```

## Dependency Inversion Principle

Depend on abstractions rather than concrete implementations.
It talks about two thing

1. High level module should not depend on low module both should depend on abstraction.
2. Abstractions should not depend on details. Details should depend on abstractions.
   Abstraction tells what things will be done and Details tells about how things will be done. Basically abstraction
   achieve through interface or abstract class and Details through concrete class.

Suppose you have repository class OrderRepository and service class OrderService. In your service class you are creating
instance of OrderRepository just like below...

```kotlin
data class Order(
    val id: Int,
    val name: String
)

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

Breaking Dependency Inversion:

1. Direct Instantiation:
   The OrderService class directly creates an instance of OrderRepository using OrderRepository().
   This makes OrderService dependent on the concrete implementation of OrderRepository, tightly coupling these two
   classes.
2. No Abstraction:
   There is no abstraction (e.g., an interface or abstract class) for OrderRepository.
   The high-level OrderService module depends directly on the low-level implementation OrderRepository, which violates
   DIP.

```kotlin

// Define an abstraction (interface) for OrderRepository
interface OrderRepository {
    fun saveOrder(order: Order)
}

// A concrete implementation of OrderRepository
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

### Have you thought that Liskov of Substitution and Interface segregation looks similar

#### Interface Segregation Principle (ISP)

Focus: Ensures that interfaces are small, specific, and tailored to the needs of individual clients.
Goal: Prevents classes from being forced to implement methods they don't need.

Violation Example: Suppose you have a generic interface for all animals:

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

Here, the Dog class is forced to implement fly(), which is irrelevant to dogs. ISP suggests splitting the interface:

```kotlin
interface Swim {
    fun swim()
}

interface Fly {
    fun fly()
}

class Dog : Swim {
    override fun swim() {
        println("Dog is swimming")
    }
}

```

##### Liskov Substitution Principle (LSP)

Focus: Ensures that derived classes can substitute their base classes without breaking the application.

Goal: Maintains the behavior expected of a base class when used through its derived classes.

Violation Example: Suppose you have a base class Bird:

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

The Penguin class violates LSP because substituting a Penguin for a Bird results in unexpected behavior when calling
fly().

A better design would refactor Bird into separate hierarchies:

```kotlin
open class Bird
open class FlyingBird : Bird() {
    open fun fly() {
        println("Bird is flying")
    }
}

class Penguin : Bird()

```


