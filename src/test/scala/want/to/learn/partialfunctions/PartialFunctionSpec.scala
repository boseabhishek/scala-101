package want.to.learn.partialfunctions


import org.joda.time.LocalDate
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper

class PartialFunctionSpec extends AnyFreeSpec {

  val evenNumber: PartialFunction[Int, Boolean] = {
    case x if x % 2 == 0 => true
  }

  val positive: PartialFunction[Int, Int] = {
    case x if x >= 0 => x
  }

  ".isDefined method" - {

    "on a Partial Function" - {

      "would give the correct result" in {

        evenNumber.isDefinedAt(8) mustBe true

        positive.isDefinedAt(3) mustBe true

        evenNumber.isDefinedAt(7) mustBe false

        positive.isDefinedAt(-3) mustBe false

      }
    }
  }

  "Partial Function is Function1[_, _]" in {

    positive.isInstanceOf[Function1[_, _]] mustBe true
    evenNumber.isInstanceOf[Function1[_, _]] mustBe true

  }

  "Partial Functions can be chained together using" - {

    "orElse" in {

      val greaterThan10: PartialFunction[Int, Boolean] = {
        case x if x > 10 => true
      }

      val evenOrGreaterThan10: PartialFunction[Int, Boolean] = evenNumber orElse greaterThan10

      evenOrGreaterThan10(4) mustBe true
      evenOrGreaterThan10(13) mustBe true

    }

    "andThen (where output of first has to be the same type as input of second)" in {

      case class Repository(name: String, stars: Int, createdDate: LocalDate)

      val hasMoreThan2Stars: PartialFunction[Repository, Repository] = {
        case repo if repo.stars > 2 => repo
      }

      val createdBeforeEasterThisYear: PartialFunction[Repository, Boolean] = {
        case repo if repo.createdDate.isBefore(new LocalDate(2019, 4, 25)) => true
      }

      val hasMoreThan2StarsAndCreatedBeforeEasterThisYear = hasMoreThan2Stars andThen createdBeforeEasterThisYear

      hasMoreThan2StarsAndCreatedBeforeEasterThisYear(
        Repository("repo-name", 6, new LocalDate(2019, 1, 13))) mustBe true

    }
  }

  "use Partial Functions on list" - {

    "with .collect and .collectFirst" in {

      val greaterThan2LesserThan8: PartialFunction[Int, Int] = {
        case x if x > 2 && x < 8 => x
      }

      val ls = List(1, 2, 3, 4, 5, 6, 7, 8)

      ls.collect(greaterThan2LesserThan8) mustBe List(3, 4, 5, 6, 7)

      ls.collectFirst(greaterThan2LesserThan8) mustBe Some(3)

    }
  }


}
