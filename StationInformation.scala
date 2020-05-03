package Final_Project

import cats.Traverse
import io.circe.Decoder.Result
import io.circe.Decoder
import io.circe.HCursor
import io.circe.Json

case class StationInformation (station_id : String, external_id : Option[String], name : String, short_name : Option[String],
                         lat : Float, lon : Float, rental_methods : Option[Array[String]], capacity : Option[Int],
                          electric_bike_surcharge_waiver : Option[Boolean], eightd_has_key_dispenser : Option[Boolean],
                          has_kiosk : Option[Boolean])

object StationInformation{
  implicit val decode : Decoder[StationInformation] = new Decoder[StationInformation] {
    override def apply(c: HCursor): Result[StationInformation] =
      for {
        station_id <- c.downField("station_id").as[String]
        external_id <- c.downField("external_id").as[Option[String]]
        name <- c.downField("name").as[String]
        short_name <- c.downField("short_name").as[Option[String]]
        lat <- c.downField("lat").as[Float]
        lon <- c.downField("lon").as[Float]
        rental_methods <- c.downField("rental_methods").as[Option[Array[String]]]
        capacity <- c.downField("capacity").as[Option[Int]]
        electric_bike_surcharge_waiver <- c.downField("electric_bike_surcharge_waiver").as[Option[Boolean]]
        eightd_has_key_dispenser <- c.downField("eightd_has_key_dispenser").as[Option[Boolean]]
        has_kiosk <- c.downField("has_kiosk").as[Option[Boolean]]
      }
        yield {
          StationInformation(station_id, external_id, name , short_name , lat, lon, rental_methods , capacity , electric_bike_surcharge_waiver, eightd_has_key_dispenser , has_kiosk)

        }

  }

  def toCsv(input : StationInformation) : String = {
    input.station_id + "," +
      input.external_id.getOrElse("") + "," +
      input.name + "," +
      input.short_name.getOrElse("") + "," +
      input.lat + "," +
      input.lon + "," +
      input.rental_methods.get.mkString("&") + "," +
      input.capacity.getOrElse("".toInt) + "," +
      input.electric_bike_surcharge_waiver.getOrElse("".toBoolean) + "," +
      input.eightd_has_key_dispenser.getOrElse("".toBoolean) + "," +
      input.has_kiosk.getOrElse("".toBoolean) + "\n"
  }
}

