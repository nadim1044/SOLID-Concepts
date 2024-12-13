/// Here is example of vehicle that have function to increase the speed of it.
/// what if you require to add more vehicle?
/// You are going to modify?? Yes that will break the rule of Open/Closed principle.
/// So you have refactor the code fulfill the requirement of Open/Closed principle
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

/// Here it is...
/// Now you dont't need to do any change.
/// Your vehicle interface provide abstraction and you can create the as much as
/// vehicle you want without modifying existing classes.
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
