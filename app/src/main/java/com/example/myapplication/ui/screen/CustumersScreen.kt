package com.example.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.CardItemAvatar
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun CustumersScreen(modifier : Modifier){
    val custumers = listOf(
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
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {TopAppBar("Custumers")},
        floatingActionButton = { AddButton("Custumers")}
    ){ contentPadding ->

        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp,8.dp,8.dp,135.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ){
            item{ SearchAppBar("Custumers", contentPadding) }
            items(custumers) { custumer ->
                    CardItemAvatar(custumer)
            }
        }
    }
}
