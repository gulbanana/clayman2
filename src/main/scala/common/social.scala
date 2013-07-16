package common
import london._

object social {
  def accepted_nursing()(implicit c: Character) = did (c.invitations("Be Nursed Back To Health")) {
    c.acceptInvitation("Be Nursed Back To Health")
  }
}