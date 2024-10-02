package com.example.api.db.converter

import androidx.room.TypeConverter
import com.example.api.db.entity.AddressEntity
import com.example.api.db.entity.ExperienceEntity
import com.example.api.db.entity.SalaryEntity
import com.google.gson.Gson

object ExperienceConverter {
    @TypeConverter
    fun experienceToJSON(experience: ExperienceEntity?): String? {
        return experience?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun jsonToExperience(json: String?): ExperienceEntity? {
        return json?.let { Gson().fromJson(it, ExperienceEntity::class.java) }
    }
}

object SalaryConverter {
    @TypeConverter
    fun salaryToJSON(salary: SalaryEntity?): String? {
        return salary?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun jsonToSalary(json: String?): SalaryEntity? {
        return json?.let { Gson().fromJson(it, SalaryEntity::class.java) }
    }
}

object AddressConverter {
    @TypeConverter
    fun addressToJSON(address: AddressEntity?): String? {
        return address?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun jsonToAddress(json: String?): AddressEntity? {
        return json?.let { Gson().fromJson(it, AddressEntity::class.java) }
    }
}