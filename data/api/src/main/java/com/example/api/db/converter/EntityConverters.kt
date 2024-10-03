package com.example.api.db.converter

import androidx.room.TypeConverter
import com.example.api.db.entity.AddressEntity
import com.example.api.db.entity.ExperienceEntity
import com.example.api.db.entity.SalaryEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object EntityConverters {

    private val gson = Gson()

    @TypeConverter
    fun listToString(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun stringToList(data: String?): List<String>? {
        if (data == null) {
            return null
        }

        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun addressToString(address: AddressEntity?): String? {
        return address?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun stringToAddress(data: String?): AddressEntity? {
        return data?.let { gson.fromJson(it, AddressEntity::class.java) }
    }

    @TypeConverter
    fun experienceToString(experience: ExperienceEntity?): String? {
        return experience?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun stringToExperience(data: String?): ExperienceEntity? {
        return data?.let { gson.fromJson(it, ExperienceEntity::class.java) }
    }

    @TypeConverter
    fun salaryToString(salary: SalaryEntity?): String? {
        return salary?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun stringToSalary(data: String?): SalaryEntity? {
        return data?.let { gson.fromJson(it, SalaryEntity::class.java) }
    }
}