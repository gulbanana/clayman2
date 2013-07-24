package david
import london._

//reminders about difficulty:
//diff X = 60% chance at qual X, 100% at qual X*5/3.
//it scales linearly with a factor of the base diff

object casing {
  //3 actions, difficulty 75
  //used twice - Casing 4, so at least 5cp
  def lev1()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Preparing for a Big Score")
    c.chooseBranch("Look for the targets")
    c.onwards()
  }
}
