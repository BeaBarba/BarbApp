package com.example.myapplication.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    val TEXT_COLOR = MaterialTheme.colorScheme.onPrimaryContainer
    val CONTAINER_COLOR = MaterialTheme.colorScheme.primaryContainer

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }) { Text("OK", color = TEXT_COLOR) }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel", color = TEXT_COLOR) } },
        colors = DatePickerDefaults.colors(
            containerColor = CONTAINER_COLOR,
        )
    ) { DatePicker(
        state = datePickerState,
        colors = DatePickerDefaults.colors(
            containerColor = CONTAINER_COLOR,
            dividerColor = TEXT_COLOR,
            titleContentColor = TEXT_COLOR,
            headlineContentColor = TEXT_COLOR,
            navigationContentColor = TEXT_COLOR,
            yearContentColor = TEXT_COLOR,
            dayContentColor = TEXT_COLOR,
            weekdayContentColor = TEXT_COLOR,
            dateTextFieldColors = OutlinedTextFieldDefaults.colors(
                /* Color Text */
                focusedTextColor = TEXT_COLOR,
                unfocusedTextColor = TEXT_COLOR,
                disabledTextColor = TEXT_COLOR,
                errorTextColor = MaterialTheme.colorScheme.error,
                /* Color Container */
                focusedContainerColor = CONTAINER_COLOR,
                unfocusedContainerColor = CONTAINER_COLOR,
                disabledContainerColor = CONTAINER_COLOR,
                /* Color Label */
                focusedLabelColor = TEXT_COLOR,
                unfocusedLabelColor = TEXT_COLOR,
                /* Color Place Holder */
                focusedPlaceholderColor = TEXT_COLOR,
                unfocusedPlaceholderColor = TEXT_COLOR,
                disabledPlaceholderColor = TEXT_COLOR,
                /* Color Border */
                focusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                /* Color TrailingIcon */
                focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                /* Color TrailingIcon */
                focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    ) }
}

@Composable
fun DatePickerFieldToModal() {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showModal by remember { mutableStateOf(false) }
    val TEXT_COLOR = MaterialTheme.colorScheme.onPrimaryContainer
    val CONTAINER_COLOR = MaterialTheme.colorScheme.primaryContainer

    OutlinedTextField(
        value = selectedDate?.let { convertMillisToDate(it) } ?: "",
        onValueChange = { showModal = true },
        placeholder = { Text("DD/MM/YYYY") },
        label = { Text(text = "Data", fontSize = MaterialTheme.typography.titleMedium.fontSize) },
        leadingIcon = { IconButton(
            onClick = { showModal = true},
            shape = RoundedCornerShape(15),
            modifier = Modifier.size(30.dp),
        ) { Icon(Icons.Default.DateRange, contentDescription = "Select date") }
        },
        trailingIcon = {
            IconButton(
                onClick = { selectedDate = null},
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) { Icon(Icons.Outlined.Cancel, "Cancel") }
        },
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        colors =  OutlinedTextFieldDefaults.colors(
            /* Color Text */
            focusedTextColor = TEXT_COLOR,
            unfocusedTextColor = TEXT_COLOR,
            disabledTextColor = TEXT_COLOR,
            /* Color Container */
            focusedContainerColor = CONTAINER_COLOR,
            unfocusedContainerColor = CONTAINER_COLOR,
            disabledContainerColor = CONTAINER_COLOR,
            /* Color Label */
            focusedLabelColor = TEXT_COLOR,
            unfocusedLabelColor = TEXT_COLOR,
            disabledLabelColor = MaterialTheme.colorScheme.primary,
            /* Color Place Holder */
            focusedPlaceholderColor = TEXT_COLOR,
            unfocusedPlaceholderColor = TEXT_COLOR,
            disabledPlaceholderColor = TEXT_COLOR,
            /* Color Border */
            focusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
            /* Color TrailingIcon */
            focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            /* Color TrailingIcon */
            focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )

    if (showModal) {
        DatePickerModal(
            onDateSelected = { selectedDate = it },
            onDismiss = { showModal = false }
        )
    }
}