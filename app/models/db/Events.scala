package models.db

import models.Event
import scala.slick.driver.JdbcDriver.simple._
import java.sql.Date

                 
class Events(tag: Tag) extends Table[Event](tag, "events") {
   def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
   def name = column[String]("name")
   def desc = column[String]("description")
   def startDate = column[Date]("start_date")
   def endDate = column[Date]("end_date")
   def category = column[String]("category")
   def submitter = column[String]("submitter")
   def tags = column[String]("tags")
   
   override def * = (id.?, name, desc, startDate, endDate, category, submitter, tags) <> ((Event.apply _).tupled, Event.unapply)
}