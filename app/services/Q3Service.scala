package services

import models.q3.ContractEnum

class Q3Service {

  /**
   * Polymorphic function that println the [[ContractEnum]] value
   *
   * @param c Contract
   * @tparam C Type of Contract
   */
  def processContract[C <: ContractEnum.Contract](c: C): Unit = {
    println(s"Processed ${c.toString}")
  }

}
