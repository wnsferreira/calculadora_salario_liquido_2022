
fun calculaInss(salarioBruto: Float ?): Float {
    if (salarioBruto == null) {return 608.44F}
    if (salarioBruto < 1659.38) {return 0.08F}
    else if (salarioBruto < 2765.66) {return salarioBruto * 0.09F}
    else if (salarioBruto < 5531.31) {return salarioBruto * 0.11F}
    else return 608.44F
}