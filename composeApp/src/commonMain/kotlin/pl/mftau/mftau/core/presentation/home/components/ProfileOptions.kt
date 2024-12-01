package pl.mftau.mftau.core.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.LockReset
import androidx.compose.material.icons.outlined.NoAccounts
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.delete_account
import mftau.composeapp.generated.resources.reset_password
import mftau.composeapp.generated.resources.sign_out
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.auth.domain.model.User

@Composable
fun ProfileOptions(
    user: User?,
    signOut: () -> Unit,
    resetPassword: () -> Unit,
    deleteAccount: () -> Unit,
) {
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }

    AnimatedVisibility(visible = user != null) {
        Box {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { dropdownExpanded = true }
            ) {
                ProfilePicture(
                    photoUrl = user?.photoUrl,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            DropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false },
                modifier = Modifier.defaultMinSize(minWidth = 200.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
                ) {
                    ProfilePicture(photoUrl = user?.photoUrl)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = user?.email ?: "")
                }
                HorizontalDivider()
                Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(Res.string.sign_out)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.Logout,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            dropdownExpanded = false
                            signOut()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(Res.string.reset_password)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.LockReset,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            dropdownExpanded = false
                            resetPassword()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(Res.string.delete_account)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.NoAccounts,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            dropdownExpanded = false
                            deleteAccount()
                        }
                    )
                }
            }
        }
    }
}