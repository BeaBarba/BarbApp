package com.example.myapplication.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(id : String){
    val queryInitial = stringResource(R.string.search)
    var query by remember { mutableStateOf(queryInitial) }
    var active by remember { mutableStateOf(false) }
    SearchBar(
        query = query,
        onQueryChange = {query = it},
        shape = RoundedCornerShape(40),
        modifier = Modifier
            .offset(y = -20.dp)
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        onSearch = {}, //Logica della ricerca
        active = active,
        onActiveChange = { //isActive -> // Logica di quando cambia lo stato

        },
        enabled = true,
        interactionSource = remember { MutableInteractionSource() },
        trailingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = stringResource(R.string.search),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        /*leadingIcon = {
            Icon(
                Icons.Filled.ArrowBack,
                "Back",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },*/
        placeholder = { Text(query.toString())},
        content = { Text("Dentro content")},
        tonalElevation = 0.dp,
        shadowElevation = 2.dp,
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primary,
            dividerColor = MaterialTheme.colorScheme.onPrimary,
            inputFieldColors = TextFieldColors(
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                disabledTextColor = MaterialTheme.colorScheme.onPrimary,
                errorTextColor = MaterialTheme.colorScheme.onPrimary,

                focusedContainerColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.primary,
                errorContainerColor = MaterialTheme.colorScheme.primary,

                cursorColor = MaterialTheme.colorScheme.onPrimary,
                errorCursorColor = MaterialTheme.colorScheme.onPrimary,

                textSelectionColors = TextSelectionColors(
                    backgroundColor = MaterialTheme.colorScheme.secondary.also { println("Background color: $it") },
                    handleColor = MaterialTheme.colorScheme.onSecondary.also { println("handle color: $it") },
                ),
                focusedIndicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                errorIndicatorColor = MaterialTheme.colorScheme.onPrimary,

                focusedSupportingTextColor = MaterialTheme.colorScheme.secondary,

                    focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                    errorLeadingIconColor = MaterialTheme.colorScheme.onPrimary,

                    focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
                    errorTrailingIconColor = MaterialTheme.colorScheme.onPrimary,

                    focusedLabelColor  = MaterialTheme.colorScheme.onPrimary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                    disabledLabelColor = MaterialTheme.colorScheme.onPrimary,
                    errorLabelColor = MaterialTheme.colorScheme.onPrimary,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimary,
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onPrimary,
                    errorPlaceholderColor = MaterialTheme.colorScheme.onPrimary,

                    unfocusedSupportingTextColor = MaterialTheme.colorScheme.onPrimary,
                    disabledSupportingTextColor = MaterialTheme.colorScheme.onPrimary,
                    errorSupportingTextColor = MaterialTheme.colorScheme.onPrimary,
                    focusedPrefixColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedPrefixColor = MaterialTheme.colorScheme.onPrimary,
                    disabledPrefixColor = MaterialTheme.colorScheme.onPrimary,
                    errorPrefixColor = MaterialTheme.colorScheme.onPrimary,
                    focusedSuffixColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedSuffixColor = MaterialTheme.colorScheme.onPrimary,
                    disabledSuffixColor = MaterialTheme.colorScheme.onPrimary,
                    errorSuffixColor = MaterialTheme.colorScheme.onPrimary
            )
        ),
    )
}

