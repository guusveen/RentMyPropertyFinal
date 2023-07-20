package com.example.rentmyproperty.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateAdapter {

    companion object {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy")
    }

    @ToJson
    fun toJson(value: LocalDate): String {
        return formatter.format(value)
    }

    @FromJson
    fun fromJson(value: String): LocalDate {
        return LocalDate.parse(value, formatter)
    }
}
