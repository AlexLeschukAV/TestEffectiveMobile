package com.example.api.db.converter

import androidx.room.TypeConverter
import com.example.api.db.entity.AddressEntity
import com.example.api.db.entity.ExperienceEntity
import com.example.api.db.entity.SalaryEntity

object EntityConverters {
    @JvmStatic
    @TypeConverter
    fun experienceToJSON(experience: ExperienceEntity?): String? {
        return ExperienceConverter.experienceToJSON(experience)
    }

    @JvmStatic
    @TypeConverter
    fun jsonToExperience(json: String?): ExperienceEntity? {
        return ExperienceConverter.jsonToExperience(json)
    }

    @JvmStatic
    @TypeConverter
    fun salaryToJSON(salary: SalaryEntity?): String? {
        return SalaryConverter.salaryToJSON(salary)
    }

    @JvmStatic
    @TypeConverter
    fun jsonToSalary(json: String?): SalaryEntity? {
        return SalaryConverter.jsonToSalary(json)
    }

    @JvmStatic
    @TypeConverter
    fun addressToJSON(address: AddressEntity?): String? {
        return AddressConverter.addressToJSON(address)
    }

    @JvmStatic
    @TypeConverter
    fun jsonToAddress(json: String?): AddressEntity? {
        return AddressConverter.jsonToAddress(json)
    }

    @JvmStatic
    @TypeConverter
    fun listToString(list: List<String>?): String? {
        return ListStringConverter.listToString(list)
    }

    @JvmStatic
    @TypeConverter
    fun stringToList(string: String?): List<String>? {
        return ListStringConverter.stringToList(string)
    }
}