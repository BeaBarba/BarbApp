package com.example.myapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    viewName = "Carrello",
    value = """ SELECT M.Id AS Materiale, SUM(P.Quantità) AS Prenotati, SUM(P.Quantità - M.Disponibilità) AS Mancano
                FROM MATERIALI AS M
                    JOIN PRENOTAZIONI AS P ON (M.Id = P.Materiale)
                GROUP BY M.Id
                HAVING Mancano > 0
            """
)
data class CartView(
    @ColumnInfo("Materiale") val materialId : Int,
    @ColumnInfo("Prenotati") val reservedItems : Float,
    @ColumnInfo("Mancano") val missingItems : Float
)