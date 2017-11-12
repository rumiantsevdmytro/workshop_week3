package model
import scala.concurrent.duration.Duration
import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

final case class Films (rating: Double,
                        directorId: Long,
                        duration: Duration,
                        title: String,
                        id: Long=0L)

class FilmsTable(tag: Tag) extends Table[Films] (tag,"films")
{  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def title = column[String]("title")
  def duration = column[Duration]("duration")
  def directorId = column[Long]("director_id")
  def rating = column[Double]("rating")
  def * = (rating, directorId, duration, title, id) <> (Films.apply _ tupled, Films.unapply)
}
