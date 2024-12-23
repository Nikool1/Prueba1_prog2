package com.nicolasaburto.prueba1.modelo

class Mesa(val itemMenu: Menu, var cantidad: Int) {

    // Calcula el subtotal
    fun calcularSubtotal(): Int {
        return itemMenu.precio * cantidad
    }
}
