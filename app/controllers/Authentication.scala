package controllers

import play.api.Logger
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.Messages
//import views._

object Authentication extends Controller {
  case class LoginInfo(email:String, password:String)
  
  val loginForm = Form(
    mapping(
      "email" -> email.verifying("Email is required and can't be empty", email => email.trim() != ""),
      "password" -> text.verifying("Password is required", {!_.isEmpty})
    )(LoginInfo.apply)(LoginInfo.unapply)
  )


  def login = Action { implicit request =>
    val defaultValue = Map("email" -> "bob@gmail.com", "password" -> "secret")
    Logger.info("Displyaing login form with data: " + defaultValue)
    //Ok(views.html.login(loginForm.bind(defaultValue)))
    Ok(views.html.login(loginForm))
  }

  def authenticate = Action { implicit request =>
    val email = (request.body.asFormUrlEncoded.get("email")(0))
    val password = (request.body.asFormUrlEncoded.get("password")(0))
    Logger.info("email: " + email + " password: " + password);
    loginForm.bindFromRequest.fold(
      formWithErrors => {
          Logger.info("formWithErrors: " + formWithErrors.hasErrors + " " + formWithErrors.hasGlobalErrors);
          formWithErrors.errors.foreach { m => println("error: "+ m) }
          BadRequest(views.html.login(formWithErrors))
      },
      user =>  {
        Logger.info("successful binding: " + user.email);
        Redirect(routes.Application.index).withSession(Security.username -> user.email)
      }
    )
  }

  def logout = Action {
    Redirect(routes.Authentication.login).withNewSession.flashing(
      "success" -> "You are now logged out."
    )
  }
}