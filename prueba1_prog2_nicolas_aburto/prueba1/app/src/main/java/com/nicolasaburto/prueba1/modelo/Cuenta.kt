package com.nicolasaburto.prueba1.modelo
class Cuenta(
    private val numeroMesa: Int,
    var aceptaPropina: Boolean = true // Indica si la mesa acepta propina
) {
    private val items: MutableList<Mesa> = mutableListOf()
    // Agrega un nuevo pedido a la cuenta
    fun agregarItem(itemMenu: Menu, cantidad: Int) {
        val itemExistente = items.find { it.itemMenu == itemMenu }
        if (itemExistente != null) {
            if (cantidad > 0) {
                itemExistente.cantidad = cantidad
            } else {
                items.remove(itemExistente)
            }
        } else if (cantidad > 0) {
            items.add(Mesa(itemMenu, cantidad))
        }
    }
    /// Calcular el total sin propina
    fun calcularTotalSinPropina(): Int {
        return items.sumOf { it.calcularSubtotal() }
    }
    // Calcular la propina (10% del total sin propina)
    fun calcularPropina(): Int {
        return if (aceptaPropina) (calcularTotalSinPropina() * 0.1).toInt() else 0
    }
    // Calcular el total con propina
    fun calcularTotalConPropina(): Int {
        return calcularTotalSinPropina() + calcularPropina()
    }
}
