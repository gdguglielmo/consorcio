package com.gguglielmo.consorcio.controllers

import javax.inject._
import play.api.mvc._
import com.gguglielmo.consorcio.model.Thing
import com.gguglielmo.consorcio.service.ThingsService
import framework.util.{CustomWriteables, RequestHandlingExecutionContext}

@Singleton
class HomeController @Inject()(cc: ControllerComponents,
                               thingsService: ThingsService)(implicit ec: RequestHandlingExecutionContext)

  extends AbstractController(cc) with CustomWriteables {

  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def createThing: Action[Thing] = Action.async(parse.json[Thing]) { implicit request =>
    thingsService.saveThing(request.body).map {
      case () => Ok
    }
  }

  def getThingsByAge(age: Int): Action[AnyContent] = Action.async { _ =>
    thingsService.getThingsByAge(age).map(things => Ok(things))
  }

  def getThings42: Action[AnyContent] = Action.async { _ =>
    thingsService.getThings42.map(things => Ok(things))
  }

}
