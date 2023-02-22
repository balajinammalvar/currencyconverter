package online.balaji.currencyconverter.view.scopfunction

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import online.balaji.currencyconverter.R

class ScopeFunctionActivity : AppCompatActivity() {

    private var number:Int?=null
    private var i=2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scope_function)
        /* let it*/
        //let have a it object it will return the last line or unit
        /*if we access a null global variable it may return null becoz used
        * by other Thread if we use let it only access if it is Null */
        /*if u remove the Ln 29 it will return unit*/
        var n=number?.let {
            var i=it+1
            i
        }?:10
//        if (number!=null){
            /*Error bec*/
//            var i=number+1
//        }

        /* also it*/
        /*also is smiliar to  let but it will not return the last line
        * it will return the object wht it was called on */
        var num=getNumber()
        Log.d("Scope Function ", "onCreate: $num")


        /*apply this*/
        /*apply is to modify object lot of changes to a object */
        /*in below intent all the changes applied intent we can use to start activity */
        /*if u have a person class but u have to change a lot of values
        * for a particular person u can use instant of person.name = ,person.age =*/
        var intent =Intent().apply {
            putExtra("","")
            putExtra("",0)
            action=""
        }
        startActivity(intent)

        /*run this*/
        /*run also like apply but it will not return the object
        * it will return the last line of the run applied*/
        var intentRun =Intent().run {
            putExtra("","")
            putExtra("",0)
            action=""
            this
        }

        /*with this */
        /*with is also like run return the last line*/
        var with =with(Intent()){
            0
        }

    }

    fun getNumber()=(i*1).also {
        it+10
    }
}