package evthub

import scala.slick.driver.JdbcDriver.simple._

import org.specs2.mutable.Specification

import play.api.test.WithApplication

import java.sql.Timestamp
import play.api.db.slick._
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

import models._

class EventsDatabaseSpec extends Specification {
  
  "Events DB" should {

    "save and query for comments and events" in new WithApplication {
      DB.withSession {implicit session =>
        println("count: " + Event.count);
        Event.insert(Event(Option.empty, "ScalaDays 2014-1", "wonderful event", new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis()), "Conference", "hluu", "Java, Scala" ))
        val l = Event.all
        println("events: " + l)
      }
    }
  }
}