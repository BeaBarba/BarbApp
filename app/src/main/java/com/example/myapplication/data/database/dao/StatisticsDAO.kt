package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.data.database.JobStatisticsResult
import com.example.myapplication.data.database.MaterialPriceHistoryResult
import java.time.LocalDate


@Dao
interface StatisticsDAO {
    @Query(
        "SELECT M.Marca, M.Modello, M.Categoria, " +
                "R.PrezzoUnitario, " +
                "B.Data AS DataAcquisto, " +
                "V.Nome AS Venditore, " +
                "'Bolla' AS Tipo " +
        "FROM MATERIALI M " +
            "JOIN RILASCI R ON R.Materiale = M.Id " +
            "JOIN BOLLE B ON B.Id = R.Bolla " +
            "JOIN VENDITORI V ON B.Venditore = V.Id " +
        "WHERE M.Id = :materialId " +

        "UNION ALL " +

        "SELECT M.Marca, M.Modello, M.Categoria, " +
            "A.PrezzoUnitario, " +
            "F.Anno AS DataAcquisto, " +
            "V.Nome AS Venditore, " +
            "'Fattura' AS Tipo " +
        "FROM MATERIALI M " +
            "JOIN ACQUISTI A ON A.Materiale = M.Id " +
            "JOIN FATTURE_ACQUISTO F ON F.Id = A.Fattura " +
            "JOIN VENDITORI V ON F.Venditore = V.Id " +
        "WHERE M.Id = :materialId " +
        "ORDER BY DataAcquisto DESC;"
    )
    suspend fun getMaterialPriceHistory(materialId : Int) : List<MaterialPriceHistoryResult>

    @Query(
        "SELECT SUM(Elettrico) AS Elettrico, SUM(Allarme) AS Allarme, SUM(Condizionamento) AS Condizionamento " +
        "FROM INTERVENTI " +
        "WHERE Data >= :startDate " +
            "AND Data <= :endDate"
    )
    suspend fun getJobStatistics(startDate : LocalDate, endDate: LocalDate) : JobStatisticsResult
}

