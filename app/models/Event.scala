package models

import java.sql.Timestamp

import scala.slick.lifted.TableQuery

import models.db.Events
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._

case class Event(id: Option[Long] = None, name: String, desc: String, startDate: Timestamp,
  endDate: Timestamp, category: String, submitter: String, tags: String)

object Event {
  val events = TableQuery[Events]

  def insert(event: Event)(implicit s: Session) {
    events.insert(event)
  }

  def count()(implicit session: Session) = {
    (for {
      event <- events
    } yield (event)).list.size
  }
  
  def all(implicit session: Session) = {
    val q = (for {
      event <- events
    } yield (event))
    
    q.list
  }
}                 