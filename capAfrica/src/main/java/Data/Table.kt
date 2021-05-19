package Data

import java.util.HashMap

class Table {
    abstract class Capitales{
        companion object{
            val _ID="id"
            val TABLE_NAME ="capitalesafric"
            val COLUM_PAIS ="pais"
            val COLUM_CAPI ="capi"
            val afri: MutableList<CapAfri> = ArrayList()
        }
    }
}