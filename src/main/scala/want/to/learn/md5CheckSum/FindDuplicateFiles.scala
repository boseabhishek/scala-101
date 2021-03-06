package want.to.learn.md5CheckSum

import java.nio.file.{Files, Paths, StandardCopyOption}
import java.security.DigestInputStream
import java.security.MessageDigest


import scala.collection.parallel.immutable.ParVector
import scala.io.Source

/**
  * WARNING: Moving files is a dangerous operation, and this code does not
  *          rigorously check for errors. Use this code at your own risk.
  *
  * Notes
  * -----
  * - SHA-1 is what Git uses
  * - this code assumes you have a list of files generated by `find . -type f` inside
  *   your pictures folder on a Mac computer
  */
object FindDuplicateFiles extends App {

  println("Getting all filenames ...")


  // (1) get a list of all picture files
  val basePictureDir = "/Users/al/Pictures"
  val inputFile = "/Users/al/Pictures/all_picture_files.txt" //mine has 28k lines
  val rawListOfPictureFiles = Source.fromFile(inputFile).getLines.toVector
  // filenames begin with "./", so drop those first two characters
  val canonListOfPhotos = rawListOfPictureFiles.map(fname => basePictureDir + "/" + fname.drop(2))
  println(s"Done reading ${canonListOfPhotos.size} filenames ...")


  // (2) get checksum for each file
  // early tests showed that a parallel vector was much faster than a regular vector
  // (though that was when the SHA-1 algorithm was very slow)
  val (checksumFilenameTuples: ParVector[(String, String)], time) = timer {
    println("Getting checksums ...")
    val parallelListOfPhotos = canonListOfPhotos.par
    val listOfChecksumsAndFilenames: ParVector[(String, String)] = for {
      f <- parallelListOfPhotos
      checksum = fileChecksum(f, MessageDigest.getInstance("SHA-1"))
    } yield (checksum, f)
    listOfChecksumsAndFilenames
  }
  println(s"Checksum run time: $time seconds")


  // (3) now have a Seq[checksum, filename], where checksum is probably duplicated.
  // want to convert this to a Map(checksum, Seq[Filename])
  println("creating the map ...")
  var checksumFilenamesMap = scala.collection.mutable.Map[String, Seq[String]]()
  for ((cksum, fname) <- checksumFilenameTuples.toVector) {
    if (checksumFilenamesMap.contains(cksum)) {
      println("FOUND A DUPLICATE")
      val oldSeq = checksumFilenamesMap(cksum)  // get the old seq
      val newSeq = oldSeq :+ fname              // add the new fname to that seq
      checksumFilenamesMap(cksum) = newSeq      // update that element in the map
    } else {
      // the map doesn't contain the checksum, so just add a new entry
      checksumFilenamesMap(cksum) = Seq(fname)
    }
  }


  // (4) (optional) write all the `Map(checksum, Seq[Filename])` to a file
  println("writing to the file ...")
  import java.io._
  val pw = new PrintWriter(new File("/Users/al/Projects/Scala/DuplicateFiles/dups.dat"))
  for ((cksum,fnames) <- checksumFilenamesMap) {
    pw.write(s"cksum: $cksum, files: $fnames\n")
  }
  pw.close


  // (5) keep one copy of the file, and move the duplicates to my "dups" folder
  val dupsFolder = "/Users/al/Desktop/DuplicateImages"
  for ((cksum,fnames) <- checksumFilenamesMap) {
    if (fnames.size > 1) {
      // there are duplicate files
      val filenamesToMove = fnames.drop(1)  //keep the first file, mv the rest
      for (originalCanonFilename <- filenamesToMove) {
        val baseFilename = (new File(originalCanonFilename)).getName  //foo.jpg
        val nanoTime = System.nanoTime
        val newFilename = nanoTime + "-" + baseFilename        //123-foo.jpg
        val newCanonFilename = dupsFolder + "/" + newFilename
        val path = Files.move(
          Paths.get(originalCanonFilename),
          Paths.get(newCanonFilename),
          StandardCopyOption.REPLACE_EXISTING
        )
        if (path != null) {
          //println(s"moved the file $baseFilename successfully")
        } else {
          println(s"could NOT move the file $baseFilename")
        }
        println("")
      }
    }
  }


  private def timer[A](blockOfCode: => A) = {
    val startTime = System.nanoTime
    val result = blockOfCode
    val stopTime = System.nanoTime
    val delta = stopTime - startTime
    (result, delta/1000000000d)

  }

  //TODO convert to use Option
  private def fileChecksum(filepath: String, mdIn: MessageDigest) = {
    var mdOut: MessageDigest = null
    var dis: DigestInputStream = null
    // file hashing with DigestInputStream
    try {
      // code is 132x slower w/o BufferedInputStream
      dis = new DigestInputStream(new BufferedInputStream(new FileInputStream(filepath)), mdIn)
      while (dis.read != -1) {
        //TODO this seems to be necessary; see dis.read() javadoc
      }
      mdOut = dis.getMessageDigest
    }
    catch {
      case ioe: IOException => {
        //TODO handle this
        System.err.println(ioe.getMessage)
      }
    }
    finally {
      if (dis != null) dis.close()
    }
    // return value
    convertBytesToHex(mdOut.digest)
  }

  def convertBytesToHex(bytes: Seq[Byte]): String = {
    val sb = new StringBuilder
    for (b <- bytes) {
      sb.append(String.format("%02x", Byte.box(b)))
    }
    sb.toString
  }

}