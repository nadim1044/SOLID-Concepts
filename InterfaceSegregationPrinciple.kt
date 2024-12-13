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

interface Flying {
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

