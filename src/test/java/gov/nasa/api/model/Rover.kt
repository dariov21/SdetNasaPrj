package gov.nasa.api.model

import java.util.*

data class Rover(val id : Long, val name : String, val landing_date : Date, val launch_date : Date, val status : String)
