package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.data.database.AveragePaymentsTimesStatisticsResult
import com.example.myapplication.data.database.JobStatisticsResult
import com.example.myapplication.data.database.MaterialPriceHistoryResult
import com.example.myapplication.data.database.NumberOfJobsByReference
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

    @Query(
        "SELECT C.CF AS CF, P.Cognome || ' ' || C.Nome AS Nome, C.TempoMedioRiscossione " +
        "FROM CLIENTI AS C " +
            "JOIN PRIVATI AS P ON C.CF = P.CF " +

        "UNION " +

        "SELECT C.CF AS CF, A.RagioneSociale AS Nome, C.TempoMedioRiscossione " +
        "FROM CLIENTI AS C " +
            "JOIN AZIENDE AS A ON C.CF = A.CF " +
        "ORDER BY 2 DESC "
    )
    suspend fun getAveragePaymentsTimesStatistics() : List<AveragePaymentsTimesStatisticsResult>

    @Query(
        "WITH " +
            "Riferimento_Clienti AS ( " +
                "SELECT C.CF AS CF, R.Cognome || ' ' || R.Nome AS Nome " +
                "FROM RIFERIMENTI AS R " +
                    "JOIN CLIENTI AS C ON (C.Riferimento = R.Id) " +
            "), " +
            "Presenta_Privati AS ( " +
                "SELECT PR.Presentato AS CF, P.Cognome || ' ' || C.Nome AS Nome " +
                "FROM PRESENTA AS PR " +
                    "JOIN CLIENTI AS C ON (C.CF = PR.Presentatore) " +
                    "JOIN PRIVATI AS P ON (C.CF = P.CF) " +
            "), " +
            "Presenta_Aziende AS ( " +
                "SELECT PR.Presentato AS CF, A.RagioneSociale AS Nome " +
                "FROM PRESENTA AS PR " +
                    "JOIN CLIENTI AS C ON (C.CF = PR.Presentatore) " +
                    "JOIN AZIENDE AS A ON (C.CF = A.CF) " +
            "), " +
            "Riferimenti_Lista AS ( " +
                "SELECT CF, Nome FROM Riferimento_Clienti " +
                "UNION " +
                "SELECT CF, Nome FROM Presenta_Privati " +
                "UNION " +
                "SELECT CF, Nome FROM Presenta_Aziende " +
            "), " +
            "Lavori AS ( " +
                "SELECT Id, Cliente, 'Intervento' AS Tipo " +
                "FROM INTERVENTI " +
                "WHERE Cantiere IS NULL " +
                    "AND Cliente IS NOT NULL " +

                "UNION ALL " +

                "SELECT Id, Cliente, 'Cantiere' AS Tipo " +
                "FROM CANTIERI " +
                "WHERE CLIENTE IS NOT NULL " +
            ") " +

            "SELECT RIF.Nome AS Riferimento, COUNT(L.Id) AS NumeroInterventi " +
            "FROM Lavori AS L " +
                "JOIN CLIENTI AS C ON (L.Cliente = C.CF) " +
                "JOIN Riferimenti_Lista AS RIF ON (C.CF = RIF.CF) " +
            "GROUP BY RIF.Nome " +
            "ORDER BY 2 DESC "
    )
    suspend fun getNumberOfJobsByReference() : List<NumberOfJobsByReference>
}