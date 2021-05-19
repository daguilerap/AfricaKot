package Data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataDbHelper(context:Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    private val db: SQLiteDatabase
    private val values: ContentValues
    companion object{
        private val DATABASE_VERSION=1
        private val DATABASE_NAME="capitalesafric"
    }

    init {
        db=this.writableDatabase
        values= ContentValues()
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_TABLE = ("CREATE TABLE " + Table.Capitales.TABLE_NAME + "("
                + Table.Capitales._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Table.Capitales.COLUM_CAPI + " TEXT,"
                + Table.Capitales.COLUM_PAIS + " TEXT" + ")")
        db!!.execSQL(CREATE_TABLE)
    }

    fun insert(afri: List<CapAfri>){

        for (i in afri) {
            values.put(Table.Capitales.COLUM_PAIS, i.pais)
            values.put(Table.Capitales.COLUM_CAPI, i.capital)
            db.insert(Table.Capitales.TABLE_NAME, null, values)
        }

    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun GetUsers(): HashMap<String, String> {
        val db = this.writableDatabase
        val user: HashMap<String, String> = HashMap()
        val query = "SELECT pais, capi FROM capitalesafric"
        val cursor: Cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            user.put(cursor.getString(cursor.getColumnIndex(Table.Capitales.COLUM_PAIS)),cursor.getString(cursor.getColumnIndex(Table.Capitales.COLUM_CAPI)))

        }
        return user
    }
}