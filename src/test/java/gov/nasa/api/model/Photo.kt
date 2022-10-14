package gov.nasa.api.model

import java.util.*

data class Photo(val id : Long, val sol : Int, val camera: Camera, val img_src : String, val earth_date : Date, val rover: Rover )
