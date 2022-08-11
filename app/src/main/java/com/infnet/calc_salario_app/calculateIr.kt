
fun calculaIR(salario_bruto: Float): Float {
    if (salario_bruto < 1903.98) {return 0.0F}
    else if (salario_bruto < 2826.65) {return salario_bruto * 0.075F}
    else if (salario_bruto < 3751.05) {return salario_bruto * 0.15F}
    else if (salario_bruto < 4664.68) {return salario_bruto * 0.225F}
    else {return salario_bruto * 0.275F}
}