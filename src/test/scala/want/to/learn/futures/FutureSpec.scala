package want.to.learn.futures

import org.scalatest.freespec.AnyFreeSpec
import want.to.learn.futures.danielWestheide.WhatsInFuture

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class FutureSpec extends AnyFreeSpec with WhatsInFuture{

  ".onSuccess" - {

    "can be invoked on a future computation" - {

      "providing a partial functions as callback" in {

        val result: Unit = grind("arabica beans").onSuccess { case ground =>
          ground
        }
      }

    }
  }

  ".onComplete" - {

    "can be invoked on a future computation" - {

      "providing a Try as callback" in {

        val result: Unit = grind("arabica beans").onComplete {
          case scala.util.Success(ground) => Some(ground)
          case scala.util.Failure(exception) => None
        }

      }
    }
  }

  "future is composable" in {
    val result: Future[Boolean] = heatWater(Water(50)) map { heatedWater =>
      (80 to 85).contains(heatedWater.temperature)
    }

    //result mustBe Future.successful(false)
  }

}
