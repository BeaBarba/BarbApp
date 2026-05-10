package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.data.database.JobStatisticsResult
import com.example.myapplication.data.database.MaterialPriceHistoryResult
import com.example.myapplication.data.database.RevenueFromJobTypeResult
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

    @Query(
        "WITH " +
            "Tipo_Interventi_Cantiere AS ( " +
                "SELECT " +
                    "C.Id AS Id, " +
                    "SUM(I.Elettrico) AS Elettrico, " +
                    "SUM(I.Condizionamento) AS Condizionamento, " +
                    "SUM(I.Allarme) AS Allarme " +
                "FROM CANTIERI AS C " +
                    "JOIN INTERVENTI AS I ON I.Cantiere = C.Id " +
                "GROUP BY C.Id " +
            "), " +
            "Totale_Interventi_Cantiere AS ( " +
                "SELECT " +
                    "Id, " +
                    "(COALESCE(Elettrico, 0) + COALESCE(Allarme, 0) + COALESCE(Condizionamento, 0)) AS TOTALE " +
                "FROM Tipo_Interventi_Cantiere " +
            "), " +
            "Importi_Interventi AS ( " +
                "SELECT " +
                    "SUM(R.Importo) AS Totale_R, " +
                    "Elettrico, " +
                    "Condizionamento, " +
                    "Allarme " +
                "FROM RICAVI AS R " +
                    "JOIN INTERVENTI AS I ON R.Intervento = I.Id " +
                "GROUP BY Condizionamento, Elettrico, Allarme " +
            ") " +
        "SELECT " +
            "SUM(Sub.Elettrico) AS Elettrico, " +
            "SUM(Sub.Condizionamento) AS Condizionamento, " +
            "SUM(Sub.Allarme) AS Allarme " +
        "FROM ( " +
            "SELECT " +
                "SUM(CAST(TI.Elettrico AS FLOAT) / NULLIF(T.TOTALE, 0) * R.Importo) AS Elettrico, " +
                "SUM(CAST(TI.Condizionamento AS FLOAT) / NULLIF(T.TOTALE, 0) * R.Importo) AS Condizionamento, " +
                "SUM(CAST(TI.Allarme AS FLOAT) / NULLIF(T.TOTALE, 0) * R.Importo) AS Allarme " +
            "FROM Totale_Interventi_Cantiere AS T " +
                "JOIN Tipo_Interventi_Cantiere AS TI ON T.Id = TI.Id " +
                "JOIN RICAVI AS R ON R.Cantiere = T.Id " +
            "" +
            "UNION ALL " +
            "" +
            "SELECT " +
                "SUM(CASE WHEN Elettrico = 1 THEN Totale_R ELSE 0 END) AS Elettrico, " +
                "SUM(CASE WHEN Condizionamento = 1 THEN Totale_R ELSE 0 END) AS Condizionamento, " +
                "SUM(CASE WHEN Allarme = 1 THEN Totale_R ELSE 0 END) AS Allarme " +
            "FROM Importi_Interventi " +
        ") AS Sub; "
    )
    suspend fun getRevenueFromJobType() : RevenueFromJobTypeResult
}

