package model
import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

final case class Genres(description: String,
                        title: String,
                        id:   Long=0L)
final class GenresTable(tag: Tag) extends Table[Genres] (tag,"genres")
{  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def title = column[String]("title")
  def description = column[String]("description")
  def * = (description,title,id) <> (Genres.apply _ tupled, Genres.unapply)
}