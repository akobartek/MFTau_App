package pl.mftau.mftau.leaders.presentation.people.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.components.FullScreenDialog
import pl.mftau.mftau.common.presentation.components.MultiSelectDialog
import pl.mftau.mftau.leaders.domain.model.Person
import pl.mftau.mftau.leaders.domain.model.PersonType
import pl.mftau.mftau.ui.theme.ColorTheme
import pl.mftau.mftau.ui.theme.MFTauTheme

@Composable
fun PersonEditorDialog(
    person: Person? = null,
    onSave: (Person) -> Unit = {},
    onDelete: (Person?) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    var name by rememberSaveable { mutableStateOf(person?.name ?: "") }
    var nameError by rememberSaveable { mutableStateOf(false) }
    var city by rememberSaveable { mutableStateOf(person?.city ?: "") }
    var cityError by rememberSaveable { mutableStateOf(false) }
    var type by rememberSaveable { mutableStateOf(person?.type?.index ?: 1) }
    var typesDialogVisible by rememberSaveable { mutableStateOf(false) }
    val types = stringArrayResource(id = R.array.person_types)
    var deleteDialogVisible by rememberSaveable { mutableStateOf(false) }

    val verifyInput = {
        if (name.isBlank()) nameError = true
        if (city.isBlank()) cityError = true
        !(nameError || cityError)
    }

    FullScreenDialog(
        isVisible = true,
        title = stringResource(id = if (person == null) R.string.add_person else R.string.edit_person),
        onSave = {
            if (verifyInput()) {
                onSave(
                    (person ?: Person()).copy(
                        name = name.trim(),
                        city = city.trim(),
                        type = PersonType.fromIndex(type)
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
                        contentDescription = stringResource(id = R.string.delete_person)
                    )
                }
        },
        content = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text(stringResource(id = R.string.person_name)) },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
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
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    placeholder = { Text(stringResource(id = R.string.city)) },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                    isError = cityError,
                    supportingText = if (cityError) {
                        {
                            Text(text = stringResource(id = R.string.empty_field_error))
                        }
                    } else null,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = types[type],
                        onValueChange = {},
                        placeholder = { Text(stringResource(id = R.string.city)) },
                        leadingIcon = {
                            PersonType.fromIndex(type).getTypeIcon()?.let {
                                Icon(
                                    imageVector = it,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(8.dp)
                                )
                            }
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
                        modifier = Modifier.clickable { typesDialogVisible = true }
                    )
                }
            }
        }
    )

    MultiSelectDialog(
        isVisible = typesDialogVisible,
        imageVector = Icons.Default.ManageAccounts,
        titleId = R.string.select_person_type,
        currentValue = types[type],
        values = types,
        onSave = { type = types.indexOf(it) },
        onDismiss = { typesDialogVisible = false }
    )

    DeletePersonDialog(
        isVisible = deleteDialogVisible,
        onConfirm = {
            onDismiss()
            onDelete(person)
        },
        onDismiss = { deleteDialogVisible = false }
    )
}

@Preview(showBackground = true)
@Composable
fun PersonEditorPreview() {
    MFTauTheme(dynamicColor = false, colorTheme = ColorTheme.DARK) {
        PersonEditorDialog()
    }
}