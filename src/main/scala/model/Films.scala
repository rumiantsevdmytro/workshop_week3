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

  val directorFk = foreignKey("director_id_fk", directorId, TableQuery[StaffTable])(_.id)
  def * = (rating, directorId, duration, title, id) <> (Films.apply _ tupled, Films.unapply)
}
case class FilmsToGenres(id: Option[Long], filmId: Long, genreId: Long)

class FilmsToGenresTable(tag: Tag) extends Table[FilmsToGenres] (tag, "films_to_genres")
{ def id = column[Option[Long]] ("id", O.PrimaryKey, O.AutoInc)
  def filmId = column[Long] ("film_id")
  def genreId = column[Long] ("genre_id")
  val filmFk = foreignKey("film_id_fk", filmId, TableQuery[FilmsTable])(_.id)
  val genreFk = foreignKey("genre_id_fk", genreId, TableQuery[GenresTable])(_.id)
  def * = (id, filmId, genreId) <> (FilmsToGenres.apply _ tupled, FilmsToGenres.unapply)}

case class FilmsToCast(id: Option[Long], filmId: Long, staffId: Long)

class FilmsToCastTable(tag: Tag) extends Table[FilmsToCast] (tag, "films_to_cast")
{ def id = column[Option[Long]] ("id", O.PrimaryKey, O.AutoInc)
  def filmId = column[Long] ("film_id")
  def staffId = column[Long] ("staff_id")
  val filmFk = foreignKey("film_id_fk", filmId, TableQuery[FilmsTable])(_.id)
  val staffFk = foreignKey("genre_id_fk", staffId, TableQuery[StaffTable])(_.id)
  def * = (id, filmId, staffId) <> (FilmsToCast.apply _ tupled, FilmsToCast.unapply)}

case class FilmsToCountries(id: Option[Long], filmId: Long, CountriesId: Long)

class FilmsToCountriesTable(tag: Tag) extends Table[FilmsToCountries] (tag, "films_to_countries")
{ def id = column[Option[Long]] ("id", O.PrimaryKey, O.AutoInc)
  def filmId = column[Long] ("film_id")
  def CountriesId = column[Long] ("countries_id")
  val filmFk = foreignKey("film_id_fk", filmId, TableQuery[FilmsTable])(_.id)
  val countriesFk = foreignKey("genre_id_fk", CountriesId, TableQuery[CountriesTable])(_.id)
  def * = (id, filmId, CountriesId) <> (FilmsToCountries.apply _ tupled, FilmsToCountries.unapply)}