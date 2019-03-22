import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.measureTimeMillis

class Zipper(val programDirectory:String = """C:\Program Files\7-Zip\7z.exe""") {
    companion object {
        const val LZMA = "LZMA"
        const val PPMd = "PPMd"
        const val BZip2 = "BZip2"
        const val Deflate64 = "Deflate64"
        const val Deflate = "Deflate"
        const val Copy = "Copy"
        //////////////////////////////
        const val FASTEST = 1
        const val NORMAL = 5
        const val ULTRA = 9
    }

    fun Compress(path:String,compressionMethod:String = Deflate,compresionLevel:Int = ULTRA):Float
    {
        val rt = Runtime.getRuntime()
        val runtime = "$programDirectory a -tzip -m0=$compressionMethod -mx=$compresionLevel $path.zip $path"

        val time = measureTimeMillis {
            val proc = rt.exec(runtime)
            val error = BufferedReader(InputStreamReader(proc.errorStream))
            val input = BufferedReader(InputStreamReader(proc.inputStream))
            while(proc.isAlive)
            {
                if(input.ready())
                {
                    var exe = input.lineSequence().joinToString("\n")
                    System.out.println(exe)
                }
                if(error.ready())
                {
                    var exe = error.lineSequence().joinToString("\n")
                    System.err.println(exe)
                }

            }
        }
        return time/1000f

    }
}