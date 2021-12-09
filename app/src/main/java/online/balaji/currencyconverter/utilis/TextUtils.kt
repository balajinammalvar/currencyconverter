package online.balaji.currencyconverter.utilis

import android.content.Context
import android.widget.Toast

class TextUtils {
    /*Text utils function can write here to call commonly in project*/
    companion object {
        //	Method to check text empty or null
        fun isValidString(string: String?): Boolean {
            return if (string == null) {
                false
            } else if (string.trim { it <= ' ' }.length <= 0) {
                false
            } else string != "null"
        }

        // Method to show toast
        fun toast(view: Context, message: String) {
            Toast.makeText(view, message, Toast.LENGTH_SHORT).show()
        }
    }


}