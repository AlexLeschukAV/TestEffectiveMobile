package com.example.api.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.api.db.converter.EntityConverters
import com.example.models.Address
import com.example.models.Experience
import com.example.models.Salary
import com.example.models.Vacancy

@Entity(tableName = "vacancies")
@TypeConverters(EntityConverters::class)
data class VacancyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val address: AddressEntity,
    val company: String,
    val experience: ExperienceEntity,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: SalaryEntity,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String,
    val responsibilities: String,
    val questions: List<String>
)
fun Vacancy.toEntity(): VacancyEntity {
    return VacancyEntity(
        id = id,
        lookingNumber = lookingNumber ?: 0,
        title = title,
        address = address.toEntity(),
        company = company,
        experience = experience.toEntity(),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = salary.toEntity(),
        schedules = schedules,
        appliedNumber = appliedNumber ?: 0,
        description = description,
        responsibilities = responsibilities,
        questions = questions
    )
}

data class ExperienceEntity(
    val previewText: String,
    val text: String
)

data class SalaryEntity(
    val full: String,
    val short: String?
)

data class AddressEntity(
    val town: String,
    val street: String,
    val house: String
)

fun Experience.toEntity(): ExperienceEntity {
    return ExperienceEntity(
        previewText = previewText,
        text = text
    )
}

fun Salary.toEntity(): SalaryEntity {
    return SalaryEntity(
        full = full,
        short = short ?: ""
    )
}

fun Address.toEntity(): AddressEntity {
    return AddressEntity(
        town = town,
        street = street,
        house = house,
    )
}