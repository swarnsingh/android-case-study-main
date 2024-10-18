package com.target.targetcasestudy.data

import com.target.targetcasestudy.models.Price
import com.target.targetcasestudy.models.deals.Deal

object StaticData {
  val deals = listOf(
    Deal(
      id = "0",
      title = "VIZIO D-Series 40\" Class 1080p Full-Array LED HD Smart TV",
      aisle = "b2",
      description = "fetch full product with details from https://api.target.com/mobile_case_study_deals/v1/deals/0",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/1.jpg",
      regularPrice = Price(22999, "$", "$229.99"),
      fulfillment = "Online",
      availability = "In stock"
    ),
    Deal(
      id = "1",
      title = "TCL 32\" Class 3-Series HD Smart Roku TV",
      aisle = "g33",
      description = "fetch full product with details from https://api.target.com/mobile_case_study_deals/v1/deals/1",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/2.jpg",
      regularPrice = Price(20999, "$", "$209.99"),
      salePrice = Price(15999, "$", "$159.99"),
      fulfillment = "Online",
      availability = "In stock"
    ),
    Deal(
      id = "2",
      title = "Hisense 50\" Class- A6G Series 4K UHD Android Smart TV",
      aisle = "a1",
      description = "fetch full product with details from https://api.target.com/mobile_case_study_deals/v1/deals/2",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/3.jpg",
      regularPrice = Price(38999, "$", "$389.99"),
      salePrice = Price(31999, "$", "$319.99"),
      fulfillment = "Online",
      availability = "In stock"
    ),

    Deal(
      id = "9",
      title = "Element 32\" 720p HD LED Roku TV",
      aisle = "x0",
      description = "fetch full product with details from https://api.target.com/mobile_case_study_deals/v1/deals/9",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/10.jpg",
      regularPrice = Price(2599, "$", "$25.99"),
      salePrice = Price(1298, "$", "$12.98"),
      fulfillment = "Online",
      availability = "In stock"
    )
  )
}