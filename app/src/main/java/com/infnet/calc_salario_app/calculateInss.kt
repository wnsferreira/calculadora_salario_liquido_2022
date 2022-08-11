
fun calculaInss(salario_bruto: Float): Float {
    if (salario_bruto < 1659.38) {return 0.08F}
    else if (salario_bruto < 2765.66) {return salario_bruto * 0.09F}
    else if (salario_bruto < 5531.31) {return salario_bruto * 0.11F}
    else return 608.44F
}