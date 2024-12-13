// Defining the Order data class
data class Order(
    val id: Int,
    val name: String
)

class OrderRepository {
    fun saveOrder(order: Order) {
        // Logic to save the order
    }
}

class OrderService2 {
    private val orderRepository: OrderRepository = OrderRepository()

    fun processOrder(order: Order) {
        orderRepository.saveOrder(order)
    }
}

// Define an abstraction (interface) for OrderRepository
interface OrderRepository2 {
    fun saveOrder(order: Order)
}

// A concrete implementation of OrderRepository
class OrderRepositoryImpl : OrderRepository2 {
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
