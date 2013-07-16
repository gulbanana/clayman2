package jobs
import london._
import common._

object getDangerous extends OneManJob {
  def apply(implicit c: Character): Unit = repeat {
    prep || {gear.dangerous(); c.travel(Areas.WolfstackDocks); duel}
  }

  def prep(implicit c: Character) = did (c.items("Rostygold") < 50) {
    gear.watchful()
    grind.ladybones_rostygold()
  } 
  
  def duel(implicit c: Character) = did(c.qualities("Wounds") > 2) {
    c.beginStorylet("Deal with your wounds")
    c.chooseBranch()
    c.onwards()
  } or (c.qualities("Running Battle...") >= 9) {
    mr_inch()
  } or {
    c.beginStorylet("Duelling the Black Ribbon")
    c.chooseBranch("Scout a battlefield")
    c.onwards()
  }
  
  /**
   * Running Battle maths: the final three grind options give +3 cp. Investment is therefore
   * acts cps  qlvl
   * 1    3    2
   * 2    6    3
   * 3    9    3
   * 4    12   4
   * 5    15   5
   * 6    18   5
   * 7    21   6
   * 8    24   6
   * 9    27   6
   * 10   30   7
   * 11   33   7
   * 12   36   8
   * 13   39   8
   * 14   42   8
   * 15   45   9
   * 
   * Duels *reduce* RB on failure, but *reset* it on success. This makes it more efficient to do the higher-level 
   * ones, as the progress is not wasted.
   * Duel difficulties are narrow challenges which increase by 10% per point of RB. This rapidly ceases to be
   * an efficient action-investment, but higher-level duels pay out in multiples.
   * 
   *  70% success =
   *    70% 1 action
   *    21% 3 actions
   *    6.3% 5 actions
   *    1.89% 7 actions
   *  etc. Converges to 1.857142 (see limit.py)
   *  
   *  It seems that higher-level battles are better - albeit requiring a longer cycle - but they may have 
   *  larger wound/RB penalties, which would change this. 
   *  
   *  Are the two actions to go from 70% to 80% (5-6RB, 7-8 RB) worthwhile?
   *  80% success =
   *    80% 1 action
   *    16% 3 actions
   *    3.2% 5 actions
   *    0.64% 7 actions
   *  etc. Converges to 1.5. Saving 0.35 actions is obviously not worth spending 2 actions, so I should always
   *  start attempting duels as soon as I've reached the minimum RB.
   */
  
  //70% at RB5; 432 rostygold; -?cp on failure
  //5+1.85 actions = 0.63E
  def colonel_pommery()(implicit c: Character) {
    c.beginStorylet("Duel Colonel Pommery, the fierce artillerist")
    c.chooseBranch("A friendly duel with Colonel Pommery")
    c.onwards()
  }
  
  //70% at RB7; 836 rostygold; -?cp on failure
  //10+1.85 actions = 0.71E
  def father_norton()(implicit c: Character) {
    c.beginStorylet("Duel Father Norton, the pugilistic priest")
    c.chooseBranch("A friendly duel with Father Norton")
    c.onwards()
  }
  
  //70% at RB9; 1296 rostygold; -?cp on failure
  //15+1.85 actions = 0.77E
  def mr_inch()(implicit c: Character) {
    c.beginStorylet("Duel Mr Inch and his menagerie")
    c.chooseBranch("A friendly duel with Mr Inch")
    c.onwards()
  }
}