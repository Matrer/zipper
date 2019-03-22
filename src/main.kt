import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView


fun main()  {

    var toZip =""
    val Sevenzip = SevenZipper()
    val zip = Zipper()



    val chooser = JFileChooser(FileSystemView.getFileSystemView().homeDirectory)
    chooser.fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES

    val wynik = chooser.showDialog(null,"Compress")
    when (wynik) {
        JFileChooser.APPROVE_OPTION -> toZip = """"${chooser.selectedFile.path}""""
        JFileChooser.CANCEL_OPTION -> return
    }
    val type = fileType(toZip)


    val time:Float = when(type.toLowerCase())
    {
        "folder"-> Sevenzip.Compress(toZip,SevenZipper.LZMA2,SevenZipper.ULTRA)

        "bin"-> zip.Compress(toZip,Zipper.LZMA,Zipper.ULTRA)
        "txt"-> zip.Compress(toZip,Zipper.BZip2,Zipper.ULTRA)

        "bmp", "jpg" -> zip.Compress(toZip,Zipper.PPMd,Zipper.ULTRA)

        "mp3"-> zip.Compress(toZip,Zipper.Deflate,Zipper.ULTRA)
        "wav"-> Sevenzip.Compress(toZip,SevenZipper.PPMd,SevenZipper.ULTRA)

        "mp4", "mpg" -> Sevenzip.Compress(toZip,SevenZipper.PPMd,SevenZipper.ULTRA)
        else -> zip.Compress(toZip)
    }

    print("$time [s]")


}

fun fileType(path:String):String
{
    val lastIndex = path.lastIndexOf('.')
    if(lastIndex == -1)
    {
        return "folder"
    }

    val start = lastIndex+1
    val end = path.length -1

    return path.substring(start,end)
}