package model
import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

final case class Staff (age: Int,
                        rate: Double,
                        name: String,
                        id: Long=0L)


class StaffTable(tag: Tag) extends Table[Staff] (tag,"staff")
{  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def rate = column[Double]("rate")
  def age = column[Int]("age")
  def * = (age, rate, name, id) <> (Staff.apply _ tupled, Staff.unapply)
}
