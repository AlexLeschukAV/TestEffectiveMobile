package com.example.api.db.entity

import androidx.room.ColumnInfo
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
    val lookingNumber: Int,
    val title: String,
    @ColumnInfo(name = "address_json")
    val address: AddressEntity,
    val company: String,
    @ColumnInfo(name = "experience_json")
    val experience: ExperienceEntity,
    val publishedDate: String,
    val isFavorite: Boolean,
    @ColumnInfo(name = "salary_json")
    val salary: SalaryEntity,
    @ColumnInfo(name = "schedules_json")
    val schedules: List<String>,
    val appliedNumber: Int,
    val description: String,
    val responsibilities: String,
    @ColumnInfo(name = "questions_json")
    val questions: List<String>
)

fun Vacancy.toEntity(): VacancyEntity {
    return VacancyEntity(
        id = id,
        lookingNumber = lookingNumber ?: 0,
        title = title ?: "",
        address = address.toEntity(),
        company = company ?: "",
        experience = experience.toEntity(),
        publishedDate = publishedDate ?: "",
        isFavorite = isFavorite,
        salary = salary.toEntity(),
        schedules = schedules,
        appliedNumber = appliedNumber ?: 0,
        description = description ?: "",
        responsibilities = responsibilities ?: "",
        questions = questions
    )
}

fun VacancyEntity.toModel(): Vacancy {
    return Vacancy(
        id = id,
        lookingNumber = if (lookingNumber != 0) lookingNumber else null,
        title = title,
        address = address.toModel(),
        company = company,
        experience = experience.toModel(),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = salary.toModel(),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions
    )
}

@TypeConverters(EntityConverters::class)
data class ExperienceEntity(
    val previewText: String,
    val text: String
)

@TypeConverters(EntityConverters::class)
data class SalaryEntity(
    val full: String,
    val short: String
)

@TypeConverters(EntityConverters::class)
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

fun ExperienceEntity.toModel(): Experience {
    return Experience(
        previewText = previewText,
        text = text,
    )
}

fun Salary.toEntity(): SalaryEntity {
    return SalaryEntity(
        full = full,
        short = short ?: ""
    )
}

fun SalaryEntity.toModel(): Salary {
    return Salary(
        full = full,
        short = short,
    )
}

fun Address.toEntity(): AddressEntity {
    return AddressEntity(
        town = town,
        street = street,
        house = house,
    )
}

fun AddressEntity.toModel(): Address {
    return Address(
        town = town,
        street = street,
        house = house,
    )
}
