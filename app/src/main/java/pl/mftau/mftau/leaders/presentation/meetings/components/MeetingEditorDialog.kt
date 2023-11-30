package pl.mftau.mftau.leaders.presentation.meetings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Diversity2
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.components.FullScreenDialog
import pl.mftau.mftau.common.presentation.components.MultiSelectDialog
import pl.mftau.mftau.common.utils.getDateFormatted
import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.ui.theme.ColorTheme
import pl.mftau.mftau.ui.theme.MFTauTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeetingEditorDialog(
    meeting: Meeting? = null,
    onSave: (Meeting) -> Unit = {},
    onDelete: (Meeting?) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    var name by rememberSaveable { mutableStateOf(meeting?.name ?: "") }
    var nameError by rememberSaveable { mutableStateOf(false) }
    var notes by rememberSaveable { mutableStateOf(meeting?.notes ?: "") }
    var type by rememberSaveable { mutableStateOf(meeting?.meetingType?.index ?: 0) }
    var typesDialogVisible by rememberSaveable { mutableStateOf(false) }
    val types = stringArrayResource(id = R.array.meeting_types)
    var date by rememberSaveable { mutableStateOf((meeting?.date ?: Timestamp.now()).toDate()) }
    var dateDialogVisible by rememberSaveable { mutableStateOf(false) }

    var deleteDialogVisible by rememberSaveable { mutableStateOf(false) }

    val verifyInput = {
        if (name.isBlank()) nameError = true
        !(nameError)
    }

    FullScreenDialog(
        isVisible = true,
        title = stringResource(id = if (meeting == null) R.string.add_meeting else R.string.edit_meeting),
        onSave = {
            if (verifyInput()) {
                onSave(
                    (meeting ?: Meeting()).copy(
                        name = name.trim(),
                        meetingType = MeetingType.fromIndex(type),
                        notes = notes.trim()
                    )
                )
                onDismiss()
            }
        },
        onDismiss = onDismiss,
        action = {
            if (meeting != null)
                IconButton(onClick = { deleteDialogVisible = !deleteDialogVisible }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(id = R.string.delete_meeting)
                    )
                }
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(id = R.string.meeting_name)) },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    isError = nameError,
                    singleLine = true,
                    supportingText = if (nameError) {
                        {
                            Text(text = stringResource(id = R.string.empty_field_error))
                        }
                    } else null,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    OutlinedTextField(
                        value = types[type],
                        onValueChange = {},
                        label = { Text(stringResource(id = R.string.meeting_type)) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null
                            )
                        },
                        enabled = false,
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledTextColor = MaterialTheme.colorScheme.onSurface,
                            disabledBorderColor = MaterialTheme.colorScheme.outline,
                            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier
                            .clickable { typesDialogVisible = true }
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = date.getDateFormatted(),
                        onValueChange = {},
                        label = { Text(stringResource(id = R.string.meeting_date)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Event,
                                contentDescription = null
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null
                            )
                        },
                        enabled = false,
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledTextColor = MaterialTheme.colorScheme.onSurface,
                            disabledBorderColor = MaterialTheme.colorScheme.outline,
                            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier
                            .clickable { dateDialogVisible = true }
                            .weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text(stringResource(id = R.string.meeting_notes)) },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    maxLines = 6,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    )

    MultiSelectDialog(
        isVisible = typesDialogVisible,
        imageVector = Icons.Default.Diversity2,
        titleId = R.string.meeting_type,
        currentValue = types[type],
        values = types,
        onSave = { type = types.indexOf(it) },
        onDismiss = { typesDialogVisible = false }
    )

    if (dateDialogVisible) {
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = date.time)
        DatePickerDialog(
            onDismissRequest = { dateDialogVisible = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        dateDialogVisible = false
                        datePickerState.selectedDateMillis?.let { date = Date(it) }
                    },
                ) {
                    Text(stringResource(id = R.string.save))
                }
            },
            dismissButton = {
                TextButton(onClick = { dateDialogVisible = false }) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    DeleteMeetingDialog(
        isVisible = deleteDialogVisible,
        onConfirm = {
            onDismiss()
            onDelete(meeting)
        },
        onDismiss = { deleteDialogVisible = false }
    )
}

@Preview(showBackground = true)
@Composable
fun MeetingEditorPreview() {
    MFTauTheme(dynamicColor = false, colorTheme = ColorTheme.DARK) {
        MeetingEditorDialog()
    }
}