package controllers

import models.Event
import play.api._
import play.api.mvc._
import play.api.data.{ Form }
import play.api.data.Forms._

object Events extends Controller {
  
 val eventForm = Form(
  mapping(
    "id" ->  optional(longNumber),
    "name" -> nonEmptyText,
    "desc" -> text,
    "startDate" -> sqlDate,
    "endDate" -> sqlDate,
    "category" -> text,
    "submitter" -> text,
    "tags" -> text
  )(Event.apply)(Event.unapply))
  
  
 def newEventForm = Action { implicit session => 
    Logger.info("*** Inside newEvent ****")
    Ok(views.html.eventform(eventForm))
 }
 
 def createEvent = Action { implicit session =>
   Ok(views.html.eventform(eventForm))
 }
}