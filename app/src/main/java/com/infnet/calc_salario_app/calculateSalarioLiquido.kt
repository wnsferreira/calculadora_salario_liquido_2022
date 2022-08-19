
fun calculaSalarioLiquido(salarioBruto: Float, pensaoAlimenticia: Float, quantDependentes: Float): Float? {

    val inss = calculaInss(salarioBruto)
    val ir = calculaIR(salarioBruto)
    val salarioLiquido =
        (salarioBruto - inss - ir - pensaoAlimenticia - (quantDependentes * 189.59F))

    return salarioLiquido
}