package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.CustomersCardsList
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun CustomersScreen(modifier : Modifier){
    val ctx = LocalContext.current
    val customers = listOf(
        "Alessandro", "Andrea", "Anna", "Alessia", "Amelia", "Antonio",
        "Barbara", "Bruno", "Beatrice", "Bianca", "Benedetto", "Bernardo",
        "Carlo", "Chiara", "Cristina", "Camilla", "Cesare", "Claudia",
        "Davide", "Daniela", "Diego", "Dario", "Diana", "Domenico",
        "Elena", "Emanuele", "Erica", "Elisa", "Enrico", "Eugenio",
        "Francesco", "Federica", "Fabio", "Flavio", "Francesca", "Franco",
        "Gabriele", "Giulia", "Giovanni", "Gabriella", "Giancarlo", "Giorgio",
        "Hanna", "Hugo", "Helena",
        "Ivan", "Isabella", "Irene", "Ilaria", "Ivo", "Ivana",
        "Jacopo", "Jasmine", "Julia", "Jasmine", "Jeremy", "Jessica",
        "Kevin", "Katia", "Kyle", "Katia", "Kenneth", "Kristen",
        "Luca", "Laura", "Leonardo", "Liliana", "Lorenzo", "Lucia",
        "Marco", "Martina", "Matteo", "Marina", "Mario", "Marisa",
        "Nicola", "Nadia", "Nina", "Nadine", "Nicolò", "Noemi",
        "Omar", "Olivia", "Ottavia", "Olimpia", "Oreste", "Ottavio",
        "Paolo", "Paola", "Pietro", "Patrizia", "Piero", "Pietro",
        "Quentin", "Quinn", "Quirino",
        "Riccardo", "Roberta", "Rocco", "Raffaella", "Raimondo", "Rebecca",
        "Simone", "Sofia", "Stefano", "Sabrina", "Salvatore", "Samantha",
        "Tommaso", "Tiziana", "Tobias", "Tania", "Teodoro", "Teresa",
        "Umberto", "Ursula", "Ugo", "Ubaldo", "Umberto", "Ursula",
        "Valentina", "Vincenzo", "Veronica", "Valeria", "Vincenzo", "Virginia",
        "William", "Wendy", "Walter", "Walter", "Wendy", "Wilhelm",
        "Xander", "Xenia", "Xiomara", "Xanthe", "Xenia", "Ximena",
        "Ylenia", "Yves", "Yolanda", "Yara", "Yves", "Yolanda",
        "Zeno", "Zoe", "Zoran", "Zaira", "Zeno", "Zita"
    )
    val letters = customers.map { it.get(0) }.distinct()
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar("Customers", { IconButton(
            content = { Icon(Icons.Filled.ArrowBack, "Back") }, onClick = {},
        )
        })},
        floatingActionButton = { AddButton("Customers"){} }
    ){ contentPadding ->
        Column (
            modifier = Modifier.padding(contentPadding)
        ){
            SearchAppBar("Customers", contentPadding)
            CustomersCardsList(letters, customers, ctx, contentPadding)
        }

    }
}


