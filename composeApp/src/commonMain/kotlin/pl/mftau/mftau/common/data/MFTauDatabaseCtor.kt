package pl.mftau.mftau.common.data

import androidx.room.RoomDatabaseConstructor

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MFTauDatabaseCtor : RoomDatabaseConstructor<MFTauDatabase> {
    override fun initialize(): MFTauDatabase
}