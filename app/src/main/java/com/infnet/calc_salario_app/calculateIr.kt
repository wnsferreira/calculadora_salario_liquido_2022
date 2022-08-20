
fun calculaIR(salarioBruto: Float ?): Float {
    if (salarioBruto == null) {return 0.0F}
    if (salarioBruto < 1903.98) {return 0.0F}
    else if (salarioBruto < 2826.65) {return salarioBruto * 0.075F}
    else if (salarioBruto < 3751.05) {return salarioBruto * 0.15F}
    else if (salarioBruto < 4664.68) {return salarioBruto * 0.225F}
    else {return salarioBruto * 0.275F}
}