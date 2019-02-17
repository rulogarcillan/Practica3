package tuppersoft.com.data.repositories

import android.content.Context
import android.preference.PreferenceManager

object PreferencesRepository {

    fun <T> savePreference(mContext: Context, key: String, value: T) {

        //mContext.getSharedPreferences(BUNDLE_PREFERENCES, Context.MODE_PRIVATE)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        val editor = sharedPreferences.edit()

        when (value) {
            is Boolean -> editor.putBoolean(key, value as Boolean)
            is Float -> editor.putFloat(key, value as Float)
            is Int -> editor.putInt(key, value as Int)
            is Long -> editor.putLong(key, value as Long)
            is String -> editor.putString(key, value as String)
            else -> return
        }
        editor.apply()
    }

    fun <T> loadPreference(mContext: Context, key: String, defaultValue: T): Any {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        return when (defaultValue) {
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue as Boolean)
            is Float -> sharedPreferences.getFloat(key, defaultValue as Float)
            is Int -> sharedPreferences.getInt(key, defaultValue as Int)
            is Long -> sharedPreferences.getLong(key, defaultValue as Long)
            is String -> sharedPreferences.getString(key, defaultValue as String)
            else -> defaultValue as Any
        }
    }

}