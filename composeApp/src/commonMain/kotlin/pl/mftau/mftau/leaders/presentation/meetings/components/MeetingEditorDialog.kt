package pl.mftau.mftau.leaders.presentation.meetings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Diversity2
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.gitlive.firebase.firestore.Timestamp
import dev.gitlive.firebase.firestore.fromMilliseconds
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.add_meeting
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.check_presence
import mftau.composeapp.generated.resources.delete_meeting
import mftau.composeapp.generated.resources.edit_meeting
import mftau.composeapp.generated.resources.empty_field_error
import mftau.composeapp.generated.resources.justify_absence
import mftau.composeapp.generated.resources.mark_as_absent
import mftau.composeapp.generated.resources.mark_as_present
import mftau.composeapp.generated.resources.meeting_date
import mftau.composeapp.generated.resources.meeting_name
import mftau.composeapp.generated.resources.meeting_notes
import mftau.composeapp.generated.resources.meeting_type
import mftau.composeapp.generated.resources.meeting_types
import mftau.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.common.presentation.composables.FullScreenDialog
import pl.mftau.mftau.common.presentation.composables.MultiSelectDialog
import pl.mftau.mftau.common.presentation.composables.UnsavedChangesDialog
import pl.mftau.mftau.common.utils.BackHandler
import pl.mftau.mftau.common.utils.getFormattedDate
import pl.mftau.mftau.common.utils.randomUUID
import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.leaders.domain.model.Person
import pl.mftau.mftau.leaders.presentation.people.components.PersonCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeetingEditorDialog(
    meeting: Meeting? = null,
    people: List<Person> = listOf(),
    currentTab: Int = 0,
    onSave: (Meeting) -> Unit = {},
    onDelete: (Meeting?) -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    var name by rememberSaveable { mutableStateOf(meeting?.name ?: "") }
    var nameError by rememberSaveable { mutableStateOf(false) }
    var notes by rememberSaveable { mutableStateOf(meeting?.notes ?: "") }
    var type by rememberSaveable { mutableStateOf(meeting?.meetingType?.index ?: currentTab) }
    var typesDialogVisible by rememberSaveable { mutableStateOf(false) }
    val types = stringArrayResource(Res.array.meeting_types)
    var dateMillis by rememberSaveable {
        mutableStateOf((meeting?.date ?: Timestamp.now()).seconds * 1000)
    }
    var dateDialogVisible by rememberSaveable { mutableStateOf(false) }

    val attendanceList = remember {
        meeting?.attendanceList?.toMutableStateList() ?: mutableStateListOf()
    }
    val absenceList = remember {
        mutableStateMapOf(*(meeting?.absenceList ?: mapOf()).toList().toTypedArray())
    }

    var absenceDialogVisible by rememberSaveable { mutableStateOf<String?>(null) }
    var deleteDialogVisible by rememberSaveable { mutableStateOf(false) }
    var unsavedChangesDialogVisible by rememberSaveable { mutableStateOf(false) }
    val onBackPressed = { unsavedChangesDialogVisible = true }

    val verifyInput = {
        if (name.isBlank()) nameError = true
        !(nameError)
    }

    BackHandler { onBackPressed() }

    FullScreenDialog(
        isVisible = true,
        title = stringResource(if (meeting == null) Res.string.add_meeting else Res.string.edit_meeting),
        onSave = {
            if (verifyInput()) {
                onSave(
                    (meeting ?: Meeting(id = randomUUID())).copy(
                        name = name.trim(),
                        meetingType = MeetingType.fromIndex(type),
                        date = Timestamp.fromMilliseconds(dateMillis.toDouble()),
                        notes = notes.trim(),
                        attendanceList = attendanceList.toMutableList(),
                        absenceList = absenceList.toMap(),
                    )
                )
                onDismiss()
            }
        },
        onDismiss = onBackPressed,
        action = {
            if (meeting != null)
                IconButton(onClick = { deleteDialogVisible = !deleteDialogVisible }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(Res.string.delete_meeting),
                    )
                }
        },
        content = {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    4.dp,
                    Alignment.CenterHorizontally
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
            ) {
                item(
                    key = "MEETING_EDITOR",
                    span = { GridItemSpan(this.maxLineSpan) }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text(stringResource(Res.string.meeting_name)) },
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                            isError = nameError,
                            maxLines = 3,
                            supportingText = if (nameError) {
                                {
                                    Text(text = stringResource(Res.string.empty_field_error))
                                }
                            } else null,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            OutlinedTextField(
                                value = types[type],
                                onValueChange = {},
                                label = { Text(stringResource(Res.string.meeting_type)) },
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
                                value = dateMillis.getFormattedDate(),
                                onValueChange = {},
                                label = { Text(stringResource(Res.string.meeting_date)) },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Event,
                                        contentDescription = null,
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = null,
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
                                    .weight(1f),
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = notes,
                            onValueChange = { notes = it },
                            label = { Text(stringResource(Res.string.meeting_notes)) },
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                            maxLines = 7,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                if (people.isNotEmpty()) {
                    item(
                        key = "ATTENDANCE_LIST_HEADER",
                        span = { GridItemSpan(this.maxLineSpan) }
                    ) {
                        Text(
                            text = stringResource(Res.string.check_presence),
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                    items(
                        count = people.size,
                        key = { people[it].id }
                    ) { index ->
                        val person = people[index]
                        val currentStatus = when {
                            attendanceList.contains(person.id) -> 0
                            absenceList.containsKey(person.id) -> 1
                            else -> 2
                        }

                        PersonCard(
                            person = person,
                            showOnlyName = true,
                            additionalContent = {
                                MultiChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                                    SegmentedButton(
                                        checked = currentStatus == 0,
                                        onCheckedChange = {
                                            if (!attendanceList.contains(person.id))
                                                attendanceList.add(person.id)
                                            absenceList.remove(person.id)
                                        },
                                        icon = {},
                                        shape = SegmentedButtonDefaults.itemShape(
                                            index = 0,
                                            count = 3
                                        )
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ThumbUp,
                                            contentDescription = stringResource(Res.string.mark_as_present)
                                        )
                                    }
                                    SegmentedButton(
                                        checked = currentStatus == 1,
                                        onCheckedChange = { absenceDialogVisible = person.id },
                                        icon = {},
                                        shape = SegmentedButtonDefaults.itemShape(
                                            index = 1,
                                            count = 3
                                        )
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Balance,
                                            contentDescription = stringResource(Res.string.justify_absence)
                                        )
                                    }
                                    SegmentedButton(
                                        checked = currentStatus == 2,
                                        onCheckedChange = {
                                            attendanceList.removeAll { it == person.id }
                                            absenceList.remove(person.id)
                                        },
                                        icon = {},
                                        shape = SegmentedButtonDefaults.itemShape(
                                            index = 2,
                                            count = 3
                                        )
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ThumbDown,
                                            contentDescription = stringResource(Res.string.mark_as_absent)
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    )

    MultiSelectDialog(
        isVisible = typesDialogVisible,
        imageVector = Icons.Default.Diversity2,
        titleId = Res.string.meeting_type,
        currentValue = types[type],
        values = types,
        onSave = { type = types.indexOf(it) },
        onDismiss = { typesDialogVisible = false },
    )

    if (dateDialogVisible) {
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = dateMillis)
        DatePickerDialog(
            onDismissRequest = { dateDialogVisible = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        dateDialogVisible = false
                        datePickerState.selectedDateMillis?.let { dateMillis = it }
                    },
                ) {
                    Text(stringResource(Res.string.save))
                }
            },
            dismissButton = {
                TextButton(onClick = { dateDialogVisible = false }) {
                    Text(stringResource(Res.string.cancel))
                }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (absenceDialogVisible != null)
        AbsenceReasonDialog(
            onConfirm = { reason ->
                attendanceList.removeAll { it == absenceDialogVisible }
                absenceList[absenceDialogVisible ?: ""] = reason
                absenceDialogVisible = null
            },
            onDismiss = { absenceDialogVisible = null },
            currentReason = absenceList[absenceDialogVisible] ?: "",
        )

    UnsavedChangesDialog(
        isVisible = unsavedChangesDialogVisible,
        onDiscard = onDismiss,
        onDismiss = { unsavedChangesDialogVisible = false },
    )

    DeleteMeetingDialog(
        isVisible = deleteDialogVisible,
        onConfirm = {
            onDismiss()
            onDelete(meeting)
        },
        onDismiss = { deleteDialogVisible = false },
    )
}