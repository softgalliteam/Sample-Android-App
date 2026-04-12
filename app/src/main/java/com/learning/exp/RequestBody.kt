package com.learning.exp

data class RequestBody(
    val data: ComputerDetails,
    val name: String
)

data class ComputerDetails(
    val cpuModel: String,
    val hardDiskSize: String,
    val price: Double,
    val year: Int
)