package jobs
import london._
import common._
import david.grind

object standardGrind extends OneManJob {
  def apply(implicit c: Character) = {
    grind.quarter_jade()
    true
  }
  
/*
        # keep 1000 of this around for the presumptuous little opportunity
        if (character.persuasive < 85
            and character.items['Greyfields 1882'] < 1000
            and character.qualities['Scandal'] < 7):
            grind.greyfields_1882(character)

        # save up for lodgings
        elif character.items['Whispered Secret'] < 80000:
            grind.whispered_secrets(character)

        elif character.items['Soul'] < 40000:
            grind.souls(character)

        # get money with risk of scandal
        elif character.qualities['Scandal'] < 7:
            grind.jade_watchful(character)

        # get money with no risk
        else:
            grind.cryptic_clues(character)
 */
}