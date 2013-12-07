package david.london
import api._

object connected {

  //10cp duchess, 22cp bohemians
  def bohemian()(implicit c: Character) {
    c.travel(Areas.ShutteredPalace)
    c.beginStorylet("Entry to the Duchess' salon")
    c.chooseBranch("Publish poetry in praise of the Duchess")
    c.onwards()
  }

  def society()(implicit c: Character) {
    c.travel(Areas.ShutteredPalace)
    c.beginStorylet("An evening at the Duchess' salon")
    c.chooseBranch("Attend: and be erudite")
    c.onwards()
  }
  
  def the_duchess()(implicit c: Character) = bohemian

/*
from london import *

def hell(character):
    """1 cp and 25 clues"""
    character.travel(areas.LADYBONES_ROAD)
    character.begin_story('Scrutinise infernal contracts')
    character.choose_default_branch()
    character.onwards()

def the_constables(character):
    """1 cp and 54 rostygold"""
    character.travel(areas.LADYBONES_ROAD)
    character.begin_story('Industrial espionage!')
    character.choose_branch('Investigate the Embassy warehouses') #has a rare success
    character.onwards()

def the_orient(character):
    """1 cp and 69 jade
    min watchful 70"""
    character.travel(areas.LADYBONES_ROAD)
    character.begin_story('Consulting detective required')
    character.choose_branch('Well, what is the case?')
    character.onwards()

def the_orient_shadowy(character):
    """4cp and 43 jade
    failure: suspicion"""
    character.travel(areas.SPITE)
    character.begin_story('Avoid an unfair tax on jewels')
    character.choose_branch('Retrieve \'costume\' jewellery')
    character.onwards()
    # later, betterw ith higher shadowy: peach brandy

def revolutionaries(character):
    """1cp rev, 7cp duchess, 30 clues, scandal and suspicion!"""
    character.travel(areas.SHUTTERED_PALACE)
    character.begin_story('Entry to the Duchess\' salon')
    character.choose_branch('The Duchess enjoys controversy')
    character.onwards()
 */
}