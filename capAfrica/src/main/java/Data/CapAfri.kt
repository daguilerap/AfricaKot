package Data

class CapAfri {
    var id: Int =0
    var pais: String=""
    var capital: String=""

    constructor(id: Int, pais: String, capital: String) {
        this.id=id
        this.pais = pais
        this.capital = capital
    }

    constructor(pais: String, capital: String) {
        this.pais = pais
        this.capital = capital
    }

}