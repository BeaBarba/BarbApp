package com.example.myapplication.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@Composable
fun DatePickerModal(
    datePickerState: DatePickerState,
    openDialogState: MutableState<Boolean>,
    onDateSelected: (Long?) -> Unit,
    onDismiss: (() -> Unit)? = null
) {
    if(!openDialogState.value){ return }

    val TEXT_COLOR = MaterialTheme.colorScheme.onPrimaryContainer
    val CONTAINER_COLOR = MaterialTheme.colorScheme.primaryContainer

    DatePickerDialog(
        onDismissRequest = {
            openDialogState.value = false
            onDismiss?.invoke()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    openDialogState.value = false
                }
            ) {
                Text("OK", color = TEXT_COLOR)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialogState.value = false
                    onDismiss?.invoke()
                }
            ) {
                Text("Cancel", color = TEXT_COLOR)
            }
        },
        colors = DatePickerDefaults.colors(containerColor = CONTAINER_COLOR)
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                dividerColor = TEXT_COLOR,
                titleContentColor = TEXT_COLOR,
                headlineContentColor = TEXT_COLOR,
                navigationContentColor = TEXT_COLOR,
                yearContentColor = TEXT_COLOR,
                dayContentColor = TEXT_COLOR,
                weekdayContentColor = TEXT_COLOR,
                dateTextFieldColors = outlinedTextFieldColor()
            )
        )
    }
}

@Composable
fun DatePickerFieldToModal(
    title : String = "Data"
) {
    var selectedDate by remember {mutableStateOf<Long?>(null)}
    var showModal = remember {mutableStateOf(false)}
    val datePickerState = rememberDatePickerState()

    OutlinedTextField(
        value = selectedDate?.let {convertMillisToDate(it)} ?: "",
        onValueChange = {},
        readOnly = true,
        placeholder = { Text("DD/MM/YYYY") },
        label = {Text(text = title, fontSize = MaterialTheme.typography.titleMedium.fontSize)},
        leadingIcon = {
            IconButton(
                onClick = { showModal.value = true},
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) {
                Icon(Icons.Default.DateRange, contentDescription = "Select date")
            }
        },
        trailingIcon = {
            IconButton(
                onClick = { selectedDate = null},
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) {
                Icon(Icons.Outlined.Cancel, "Cancel")
            }
        },
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable{showModal.value = true},
        colors =  outlinedTextFieldColor()
    )

    if (showModal.value) {
        DatePickerModal(
            datePickerState = datePickerState,
            openDialogState = showModal,
            onDateSelected = { selectedDate = it },
            onDismiss = { showModal.value = false }
        )
    }
}

@Composable
fun CustomRangePickerHeader(
    selectedStartDateMillis: Long?,
    selectedEndDateMillis: Long?
) {
    val TEXT_COLOR = MaterialTheme.colorScheme.onPrimaryContainer
    val startDate = selectedStartDateMillis?.let { convertMillisToDate(it) } ?: "Inizio"
    val endDate = selectedEndDateMillis?.let { convertMillisToDate(it) } ?: "Fine"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = startDate,
                color = TEXT_COLOR,
                fontWeight = if (selectedStartDateMillis != null) FontWeight.Bold else FontWeight.Normal
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(" - ")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = endDate,
                color = TEXT_COLOR,
                fontWeight = if (selectedEndDateMillis != null) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

@Composable
fun DateRangePickerFullScreen(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    val dateRangePickerState = rememberDateRangePickerState()
    val TEXT_COLOR = MaterialTheme.colorScheme.onPrimaryContainer
    val CONTAINER_COLOR = MaterialTheme.colorScheme.primaryContainer
    var selectedStartDateMillis =  dateRangePickerState.selectedStartDateMillis
    var selectedEndDateMillis = dateRangePickerState.selectedEndDateMillis

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                DateRangePicker(
                    state = dateRangePickerState,
                    title = {
                        Row(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            IconButton(
                                onClick = onDismiss,
                                modifier = Modifier
                                    .size(40.dp)
                                    .weight(1.5f)
                            ) {
                                Icon(Icons.Filled.Close, contentDescription = "Close")
                            }
                            Spacer(Modifier.weight(3.0f))
                            val isSaveEnabled =
                                selectedStartDateMillis != null && selectedEndDateMillis != null
                            TextButton(
                                onClick = {
                                    onDateRangeSelected(
                                        Pair(
                                            dateRangePickerState.selectedStartDateMillis,
                                            dateRangePickerState.selectedEndDateMillis
                                        )
                                    )
                                    onDismiss()
                                },
                                modifier = Modifier.weight(2.0f),
                                enabled = isSaveEnabled
                            ) {
                                Text(
                                    "Salva",
                                    color = if (isSaveEnabled) TEXT_COLOR else TEXT_COLOR.copy(alpha = 0.5f)
                                )
                            }
                        }
                    },
                    headline = {
                        CustomRangePickerHeader(
                            selectedStartDateMillis = selectedStartDateMillis,
                            selectedEndDateMillis = selectedEndDateMillis
                        )
                    },
                    colors = DatePickerDefaults.colors(
                        titleContentColor = TEXT_COLOR,
                        headlineContentColor = TEXT_COLOR,
                        navigationContentColor = TEXT_COLOR,
                        dayInSelectionRangeContentColor = TEXT_COLOR,
                        dayInSelectionRangeContainerColor = CONTAINER_COLOR,
                        dayContentColor = TEXT_COLOR,
                        weekdayContentColor = TEXT_COLOR,
                        subheadContentColor = TEXT_COLOR,
                        dateTextFieldColors = outlinedTextFieldColor()
                    )
                )
            }
        }
    }
}

@Composable
fun CustomDateRangePicker(
    selectedStartDate: Long? = null,
    selectedEndDate: Long? = null,
    onRangeSelected: (Long?, Long?) -> Unit
) {
    var showRangeModal by remember { mutableStateOf(false) }

    val dateRangeText = when {
        selectedStartDate != null && selectedEndDate != null ->
            "${convertMillisToDate(selectedStartDate!!)} - ${convertMillisToDate(selectedEndDate!!)}"
        selectedStartDate != null ->
            convertMillisToDate(selectedStartDate!!) + " - "
        else -> ""
    }

    OutlinedTextField(
        value = dateRangeText,
        onValueChange = { },
        readOnly = true,
        placeholder = { Text("DD/MM/YYYY - DD/MM/YYYY") },
        label = { Text(text = "Range Data", fontSize = MaterialTheme.typography.titleMedium.fontSize) },
        leadingIcon = {
            IconButton(
                onClick = { showRangeModal = true },
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) { Icon(Icons.Default.DateRange, contentDescription = "Select date range") }
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onRangeSelected(null, null)
                },
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) { Icon(Icons.Outlined.Cancel, "Cancel range") }
        },
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        colors = outlinedTextFieldColor()
    )

    if (showRangeModal) {
        DateRangePickerFullScreen(
            onDateRangeSelected = { (start, end) ->
                onRangeSelected(start, end)
            },
            onDismiss = { showRangeModal = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: (TimePickerState) -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {},
        confirmButton = {
            TextButton(onClick = { onConfirm(timePickerState) }) {
                Text("OK")
            }
        },
        text = {
            TimePicker(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    clockDialUnselectedContentColor  = MaterialTheme.colorScheme.onPrimaryContainer,
                    periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePicker(
    title : String = "Ora"
){
    var showDialog by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf("") }

    OutlinedTextField(
        value = selectedTime,
        onValueChange = { showDialog = true },
        placeholder = { Text("hh:mm") },
        label = { Text(text = title, fontSize = MaterialTheme.typography.titleMedium.fontSize) },
        leadingIcon = {
            IconButton(
                onClick = { showDialog = true },
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) { Icon(Icons.Outlined.Schedule, contentDescription = "Select time") }
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    showDialog = false
                    selectedTime = ""
                },
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) { Icon(Icons.Outlined.Cancel, "Cancel") }
        },
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        colors = outlinedTextFieldColor()
    )

    if(showDialog){
        TimePickerDialog(
            onConfirm = { timePickerState ->
                val hour = timePickerState.hour
                val minute = timePickerState.minute
                selectedTime = "$hour:$minute"
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}