package common
import java.io._
import java.nio.file._
import london.Character

trait Stats {  
  private def statsFile(implicit c: Character) = Paths.get("%s.stats".format(c.name))
  private def loadStats(implicit c: Character) = if (Files.exists(statsFile)) {
    Map()
  } else {
    Map()
  }
  /*
  private val cookies = loadCookies().getOrElse(mutable.Set[Cookie]())
  
  private def saveCookies(cs: Iterable[Cookie]) {
    Files.deleteIfExists(tin)
    
    val writer = new ObjectOutputStream(new FileOutputStream(tin.toFile()))
    writer.writeObject(cookies.map { c => 
      SerializableCookie(c.getDomain(), c.getName(), c.getValue(), c.getPath(), c.getMaxAge(), c.isSecure())
    })
    writer.close()
  }
  
  private def loadCookies() = if (Files.exists(tin)) {
    val reader = new ObjectInputStream(new FileInputStream(tin.toFile()))
    val jar = reader.readObject().asInstanceOf[mutable.Set[SerializableCookie]].map{ c =>
      new Cookie(c.domain, c.name, c.value, c.path, c.maxAge, c.secure)
    }
    reader.close()
    Some(jar)
  } else {
    None
  }
  */
}