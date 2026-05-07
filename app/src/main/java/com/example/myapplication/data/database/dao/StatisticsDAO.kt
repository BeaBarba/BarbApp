package com.example.myapplication.data.database.dao

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Query
import java.time.LocalDate

data class MaterialPriceHistory(
    @ColumnInfo(name = "Marca") val brand : String,
    @ColumnInfo(name = "Modello") val model : String,
    @ColumnInfo(name = "Categoria") val category : String,
    @ColumnInfo(name = "PrezzoUnitario") val price : Float,
    @ColumnInfo(name = "DataAcquisto") val date : LocalDate,
    @ColumnInfo(name = "Venditore") val sellerName : String
)

@Dao
interface StatisticsDAO {
    @Query(
        "SELECT M.Marca, M.Modello, M.Categoria, " +
                "R.PrezzoUnitario, B.Data AS DataAcquisto, " +
                "V.Nome AS Venditore " +
        "FROM MATERIALI M " +
            "JOIN RILASCI R ON R.Materiale = M.Id " +
            "JOIN Bolle B ON B.Id = R.Bolla " +
            "JOIN VENDITORI V ON B.Venditore = V.Id " +
        "WHERE M.Id = :materialId " +

        "UNION ALL " +

        "SELECT M.Marca, M.Modello, M.Categoria, " +
            "A.PrezzoUnitario, F.Anno AS DataAcquisto, " +
            "V.Nome AS Venditore " +
        "FROM MATERIALI M " +
            "JOIN ACQUISTI A ON A.Materiale = M.Id " +
            "JOIN FATTURE_ACQUISTO F ON F.Id = A.Fattura " +
            "JOIN VENDITORI V ON F.Venditore = V.Id " +
        "WHERE M.Id = :materialId;"
    )
    suspend fun getMaterialPriceHistory(materialId : Int) : List<MaterialPriceHistory>
}