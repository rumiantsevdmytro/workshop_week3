package model
import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

final case class Countries(title: String,
                           id: Long=0L)

class CountriesTable(tag: Tag) extends Table[Countries] (tag,"countries")
{  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def title = column[String]("title")
  def * = (title, id) <> (Countries.apply _ tupled, Countries.unapply)
}