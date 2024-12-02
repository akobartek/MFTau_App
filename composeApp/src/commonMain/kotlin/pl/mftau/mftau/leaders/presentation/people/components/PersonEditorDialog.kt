package pl.mftau.mftau.leaders.presentation.people.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.add_person
import mftau.composeapp.generated.resources.delete_person
import mftau.composeapp.generated.resources.edit_person
import mftau.composeapp.generated.resources.empty_field_error
import mftau.composeapp.generated.resources.person_city
import mftau.composeapp.generated.resources.person_name
import mftau.composeapp.generated.resources.person_notes
import mftau.composeapp.generated.resources.person_type
import mftau.composeapp.generated.resources.person_types
import mftau.composeapp.generated.resources.select_person_type
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.common.presentation.composables.FullScreenDialog
import pl.mftau.mftau.common.presentation.composables.HeightSpacer
import pl.mftau.mftau.common.presentation.composables.MultiSelectDialog
import pl.mftau.mftau.common.utils.randomUUID
import pl.mftau.mftau.leaders.domain.model.Person
import pl.mftau.mftau.leaders.domain.model.PersonType

@Composable
fun PersonEditorDialog(
    person: Person? = null,
    onSave: (Person) -> Unit = {},
    onDelete: (Person?) -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    var name by rememberSaveable { mutableStateOf(person?.name ?: "") }
    var nameError by rememberSaveable { mutableStateOf(false) }
    var city by rememberSaveable { mutableStateOf(person?.city ?: "") }
    var cityError by rememberSaveable { mutableStateOf(false) }
    var notes by rememberSaveable { mutableStateOf(person?.notes ?: "") }
    var type by rememberSaveable { mutableStateOf(person?.type?.index ?: 1) }
    var typesDialogVisible by rememberSaveable { mutableStateOf(false) }
    val types = stringArrayResource(Res.array.person_types)
    var deleteDialogVisible by rememberSaveable { mutableStateOf(false) }

    val verifyInput = {
        if (name.isBlank()) nameError = true
        if (city.isBlank()) cityError = true
        !(nameError || cityError)
    }

    FullScreenDialog(
        isVisible = true,
        title = stringResource(if (person == null) Res.string.add_person else Res.string.edit_person),
        onSave = {
            if (verifyInput()) {
                onSave(
                    (person ?: Person(id = randomUUID())).copy(
                        name = name.trim(),
                        city = city.trim(),
                        type = PersonType.fromIndex(type),
                        notes = notes.trim()
                    )
                )
                onDismiss()
            }
        },
        onDismiss = onDismiss,
        action = {
            if (person != null)
                IconButton(onClick = { deleteDialogVisible = !deleteDialogVisible }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(Res.string.delete_person),
                    )
                }
        },
        content = { focusManager ->
            val (nameRef, cityRef, notesRef) = remember { FocusRequester.createRefs() }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState()),
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(Res.string.person_name)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next,
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) },
                    ),
                    isError = nameError,
                    singleLine = true,
                    supportingText = if (nameError) {
                        {
                            Text(text = stringResource(Res.string.empty_field_error))
                        }
                    } else null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(nameRef)
                        .focusProperties { next = cityRef },
                )
                HeightSpacer(height = 16.dp)
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text(stringResource(Res.string.person_city)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next,
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) },
                    ),
                    isError = cityError,
                    supportingText = if (cityError) {
                        {
                            Text(text = stringResource(Res.string.empty_field_error))
                        }
                    } else null,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(cityRef)
                        .focusProperties { next = notesRef },
                )
                HeightSpacer(height = 16.dp)
                OutlinedTextField(
                    value = types[type],
                    onValueChange = {},
                    label = { Text(stringResource(Res.string.person_type)) },
                    leadingIcon = {
                        PersonType.fromIndex(type).getTypeIcon()?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(8.dp),
                            )
                        }
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
                    modifier = Modifier.clickable { typesDialogVisible = true },
                )
                HeightSpacer(height = 16.dp)
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text(stringResource(Res.string.person_notes)) },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    maxLines = 6,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(notesRef),
                )
                HeightSpacer(height = 24.dp)
            }
        }
    )

    MultiSelectDialog(
        isVisible = typesDialogVisible,
        imageVector = Icons.Default.ManageAccounts,
        titleId = Res.string.select_person_type,
        currentValue = types[type],
        values = types,
        onSave = { type = types.indexOf(it) },
        onDismiss = { typesDialogVisible = false },
    )

    DeletePersonDialog(
        isVisible = deleteDialogVisible,
        onConfirm = {
            onDismiss()
            onDelete(person)
        },
        onDismiss = { deleteDialogVisible = false },
    )
}