package want.to.learn.ordering

import org.joda.time.LocalDate
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper

class OrderingSpec extends AnyFreeSpec {

  ".sorted method on a list" - {

    "depends on the implicit ordering present in the scope"  - {

      "for sorting a list of Person by their DOB" in {

        val persons = Seq(Person("aahbjj", new LocalDate(2017, 9, 9)),
          Person("ghnhh", new LocalDate(2017, 9, 9)),
          Person("trgg", new LocalDate(2017, 9, 9)))

        implicit  val ord = Person.byAlphabeticalOrder

        persons.sorted mustBe persons

      }

    }
  }

}
