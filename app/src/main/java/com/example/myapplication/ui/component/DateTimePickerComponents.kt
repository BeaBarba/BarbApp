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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.R
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
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

    val textColor = MaterialTheme.colorScheme.onPrimaryContainer
    val containerColor = MaterialTheme.colorScheme.primaryContainer

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
                Text(text = stringResource(R.string.ok), color = textColor)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDateSelected(null)
                    openDialogState.value = false
                    onDismiss?.invoke()
                }
            ) {
                Text(text = stringResource(R.string.clear), color = textColor)
            }
        },
        colors = DatePickerDefaults.colors(containerColor = containerColor)
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                dividerColor = textColor,
                titleContentColor = textColor,
                headlineContentColor = textColor,
                navigationContentColor = textColor,
                yearContentColor = textColor,
                dayContentColor = textColor,
                weekdayContentColor = textColor,
                dateTextFieldColors = outlinedTextFieldColor()
            )
        )
    }
}

@Composable
fun DatePickerFieldToModal(
    title : String = stringResource(R.string.date),
    onValueChange : (String) -> Unit,
    value : String = ""
) {
    val showModal = remember {mutableStateOf(false)}
    val datePickerState = rememberDatePickerState()

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        readOnly = true,
        placeholder = {Text("DD/MM/YYYY")},
        label = {Text(text = title, fontSize = MaterialTheme.typography.titleMedium.fontSize)},
        leadingIcon = {
            IconButton(
                onClick = {showModal.value = true},
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) {
                Icon(Icons.Default.DateRange, contentDescription = stringResource(R.string.select_date))
            }
        },
        trailingIcon = {
            IconButton(
                onClick = {onValueChange("")},
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) {
                Icon(Icons.Outlined.Cancel, contentDescription = stringResource(R.string.clear))
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
            onDateSelected = {dateSelected ->
                onValueChange(
                    dateSelected?.let{epochMilli -> Instant.ofEpochMilli(epochMilli)}?.atZone(ZoneId.of("Europe/Rome"))
                        ?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
                )
            },
            onDismiss = { showModal.value = false }
        )
    }
}

@Composable
fun CustomRangePickerHeader(
    selectedStartDateMillis: Long?,
    selectedEndDateMillis: Long?
) {
    val textColor = MaterialTheme.colorScheme.onPrimaryContainer
    val startDate = selectedStartDateMillis?.let { convertMillisToDate(it) } ?: stringResource(R.string.start)
    val endDate = selectedEndDateMillis?.let { convertMillisToDate(it) } ?: stringResource(R.string.end)

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
                color = textColor,
                fontWeight = if (selectedStartDateMillis != null) FontWeight.Bold else FontWeight.Normal
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(" - ")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = endDate,
                color = textColor,
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
    val textColor = MaterialTheme.colorScheme.onPrimaryContainer
    val containerColor = MaterialTheme.colorScheme.primaryContainer
    val selectedStartDateMillis =  dateRangePickerState.selectedStartDateMillis
    val selectedEndDateMillis = dateRangePickerState.selectedEndDateMillis

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
                                Icon(Icons.Filled.Close, contentDescription = stringResource(R.string.close))
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
                                    text = stringResource(R.string.save),
                                    color = if (isSaveEnabled) textColor else textColor.copy(alpha = 0.5f)
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
                        titleContentColor = textColor,
                        headlineContentColor = textColor,
                        navigationContentColor = textColor,
                        dayInSelectionRangeContentColor = textColor,
                        dayInSelectionRangeContainerColor = containerColor,
                        dayContentColor = textColor,
                        weekdayContentColor = textColor,
                        subheadContentColor = textColor,
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
            "${convertMillisToDate(selectedStartDate)} - ${convertMillisToDate(selectedEndDate)}"
        selectedStartDate != null ->
            convertMillisToDate(selectedStartDate) + " - "
        else -> ""
    }

    OutlinedTextField(
        value = dateRangeText,
        onValueChange = { },
        readOnly = true,
        placeholder = { Text("DD/MM/YYYY - DD/MM/YYYY") },
        label = { Text(text = stringResource(R.string.range) + stringResource(R.string.date), fontSize = MaterialTheme.typography.titleMedium.fontSize) },
        leadingIcon = {
            IconButton(
                onClick = { showRangeModal = true },
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) { Icon(Icons.Default.DateRange, contentDescription = stringResource(R.string.select_date) + stringResource(R.string.range)) }
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onRangeSelected(null, null)
                },
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) { Icon(Icons.Outlined.Cancel, contentDescription = stringResource(R.string.clear)) }
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
                Text(text = stringResource(R.string.ok))
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
    title : String = stringResource(R.string.time),
    onValueChange: (String) -> Unit,
    value : String = ""
){
    val showDialog = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = { showDialog.value = true },
        placeholder = { Text("hh:mm") },
        label = { Text(text = title, fontSize = MaterialTheme.typography.titleMedium.fontSize) },
        leadingIcon = {
            IconButton(
                onClick = {showDialog.value = true},
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) { Icon(Icons.Outlined.Schedule, contentDescription = stringResource(R.string.select_time)) }
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    showDialog.value = false
                    onValueChange("")
                },
                shape = RoundedCornerShape(15),
                modifier = Modifier.size(30.dp),
            ) { Icon(Icons.Outlined.Cancel, contentDescription = stringResource(R.string.clear)) }
        },
        readOnly = true,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        colors = outlinedTextFieldColor()
    )

    if(showDialog.value){
        TimePickerDialog(
            onConfirm = { timePickerState ->
                val hour = timePickerState.hour
                val minute = timePickerState.minute
                val formatTime = String.format(Locale.getDefault(), "%02d:%02d",hour,minute)
                showDialog.value = false
                onValueChange(formatTime)
            },
            onDismiss = { showDialog.value = false }
        )
    }
}