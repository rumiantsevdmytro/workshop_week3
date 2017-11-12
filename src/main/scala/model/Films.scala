package model
import scala.concurrent.duration.Duration

final case class Films (rating: Double,
                        directorId: Long,
                        duration: Duration,
                        title: String,
                        id: Long=0L)
