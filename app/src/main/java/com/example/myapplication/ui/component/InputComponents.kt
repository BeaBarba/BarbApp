package com.example.myapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun outlinedTextFieldColor(): TextFieldColors {
    return OutlinedTextFieldDefaults.colors(
        /* Color Text */
        focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
        unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
        disabledTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
        errorTextColor = MaterialTheme.colorScheme.error,
        /* Color Container */
        focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
        /* Color Label */
        focusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
        unfocusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
        disabledLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
        /* Color PlaceHolder */
        focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
        disabledPlaceholderColor= MaterialTheme.colorScheme.onPrimaryContainer,
        /* Color Border */
        focusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
        unfocusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
        disabledBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
        /* Color LeadingIcon */
        focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
        disabledLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
        /* Color TrailingIcon */
        focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    )
}

@Composable
fun CustomOutlineTextField(title : String) {
    var content by remember { mutableStateOf("") }
    OutlinedTextField(
        value = content,
        onValueChange = {newContent -> content = newContent},
        label = { Text(text = title, fontSize = MaterialTheme.typography.titleMedium.fontSize) },
        trailingIcon = {
            IconButton(
                onClick = { content = ""},
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) { Icon(Icons.Outlined.Cancel, "Delete", /*tint = MaterialTheme.colorScheme.onPrimaryContainer*/) }
        },
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        colors =  outlinedTextFieldColor()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionTextField(title : String, leadingIcon : @Composable (() -> Unit)? = null, isAutocompleteMode : Boolean = false, suggestions : List<String>? = null){
    var content by remember { mutableStateOf( "") }
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().menuAnchor(),
            value = content,
            onValueChange = { newContent ->
                content = newContent
                expanded = newContent.isNotEmpty()
            },
            label = { Text(text = title, fontSize = MaterialTheme.typography.titleMedium.fontSize) },
            leadingIcon = leadingIcon,
            trailingIcon = {
                IconButton(
                    onClick = {
                        content = ""
                        expanded = false
                    },
                    shape = RoundedCornerShape(15),
                    modifier = Modifier.size(30.dp),
                ) { Icon(Icons.Outlined.Cancel, "Delete Text") }
            },
            colors = OutlinedTextFieldDefaults.colors(
                /* Color Text */
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                disabledTextColor = MaterialTheme.colorScheme.primary,
                /* Color Container */
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
                /* Color Label */
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                disabledLabelColor = MaterialTheme.colorScheme.primary,
                /* Color Border */
                focusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                /* Color LeadingIcon */
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                disabledLeadingIconColor = MaterialTheme.colorScheme.primary,
                /* Color TrailingIcon */
                focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                disabledTrailingIconColor = MaterialTheme.colorScheme.primary,
            )
        )

        /* Suggestions */
        if(suggestions != null) {
            val filteredSuggestions = suggestions.filter { it.contains(content, ignoreCase = true) }
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(15.dp)
            ) {
                filteredSuggestions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { MenuText(selectionOption) },
                        onClick = {
                            content = selectionOption
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}