/// On previous example of OpenClosedPrinciple we have created Vehicle interface, and it had two functions
/// accelerateVehicle and breakVehicle. Does each and every vehicle have feature of accelerate???
/// answer No. Bicycle don't have this feature. So here we break the Liskov principle by implementing vehicle interface.
/// Here is refactored code.

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