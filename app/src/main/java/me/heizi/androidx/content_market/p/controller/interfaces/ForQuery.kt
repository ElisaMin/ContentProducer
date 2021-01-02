package me.heizi.androidx.content_market.p.controller.interfaces

import android.database.Cursor
import android.net.Uri
import com.androidx.providers.p.controller.interfaces.PathController
import me.heizi.androidx.content_market.p.dao.Dao
import me.heizi.androidx.content_market.p.teamplates.QueryTemplate
import me.heizi.androidx.content_market.p.utils.Path.Companion.paths


open class ForQuery constructor(
        val dao: Dao,
        baseUri:String,
) : PathController<Cursor>(baseUri) {


    /**
     * Rem 语法糖 欢迎食用:
     * <pre>
     *     "select name from table  where id=? and col=?  " % [id,col]
     * </pre>
     * @param args
     */
    operator fun String.rem(args: Array<Any?>) = dao.query(this,args)

    /**
     * Query
     * 合成Map传入到Methond里面
     * @param uri
     * @param argKey
     * @param argValue
     * @return
     */
    fun query(uri: Uri, argKey:Array<out String>, argValue:Array<out String?>):Cursor {
        val path = uri.paths.asList()
        //评断是否为rootPath
        if (path.first()!=rootPath) return QueryTemplate.ErrorCursor("root path not found",baseUri)
        //生成map
        val mapArgs = hashMapOf<String,String?>()
        repeat(argKey.size) {
            try {
                mapArgs[argKey[it]] = argValue[it]
            }catch (e:Exception) {
                mapArgs[argKey[it]] = null
            }
        }
        //循环查找
        val lastPath = path.last()
        for ((_path,func) in map) {
            if (_path == lastPath) return func.call(this,mapArgs)
        }
        //报错
        return QueryTemplate.ErrorCursor("didn't adapted yet",baseUri)
    }


}
