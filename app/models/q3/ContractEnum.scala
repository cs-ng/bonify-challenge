package models.q3

object ContractEnum extends Enumeration {
  type Contract = Value

  val Electricity = Value("electricity")
  val Dsl = Value("dsl")
  val ApartmentRent = Value("apartment_rent")
}
