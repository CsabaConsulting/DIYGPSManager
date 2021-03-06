package dev.csaba.diygpsmanager.data

import androidx.fragment.app.FragmentActivity


fun getPreferenceString(preferences: EncryptedPreferenceDataStore, name: String, defValue: String = ""): String {
    return preferences.getString(name, defValue) ?: return ""
}

fun mapValueToInterval(intervals: IntArray, value: Int): Int {
    if (value < 0)
        return intervals.first()

    if (value >= intervals.size)
        return intervals.last()

    return intervals[value]
}

fun FragmentActivity.getSecondaryFirebaseConfiguration(): FirebaseProjectConfiguration {
    val preferences = EncryptedPreferenceDataStore.getInstance(applicationContext)
    return FirebaseProjectConfiguration(
        getPreferenceString(preferences, "project_id"),
        getPreferenceString(preferences, "application_id"),
        getPreferenceString(preferences, "api_key"),
        getPreferenceString(preferences, "auth_type", "email"),
        getPreferenceString(preferences, "email"),
        getPreferenceString(preferences, "code"),
        preferences.getString("look_back_minutes", "10")!!.toInt()
    )
}

fun FragmentActivity.hasAuthConfiguration(): Boolean {
    val configuration = this.getSecondaryFirebaseConfiguration()
    return configuration.projectId.isNotBlank() &&
        configuration.applicationId.isNotBlank() &&
        configuration.apiKey.isNotBlank() &&
        (configuration.authType != "email" ||
            (configuration.email.isNotBlank() && configuration.code.isNotBlank()))
}
