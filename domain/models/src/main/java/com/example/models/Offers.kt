package com.example.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Offers(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>,
    val showAllVacancy: Boolean = false,
) {
    fun updateFavoriteVacancy(id: String): Offers {
        val updatedVacancies = vacancies.map { vacancy ->
            if (vacancy.id == id) {
                vacancy.copy(isFavorite = !vacancy.isFavorite)
            } else {
                vacancy
            }
        }
        return Offers(offers, updatedVacancies, showAllVacancy)
    }

    fun updateShowAllVacancy(): Offers {
        return Offers(offers, vacancies, showAllVacancy = !showAllVacancy)
    }
}

data class Offer(
    val id: String,
    val title: String,
    val link: String?,
    val button: Button?
)

data class Button(val text: String)

@Parcelize
data class Vacancy(
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String,
    val responsibilities: String,
    val questions: List<String>
) : Parcelable

@Parcelize
data class Address(
    val town: String,
    val street: String,
    val house: String
) : Parcelable

@Parcelize
data class Experience(
    val previewText: String,
    val text: String
) : Parcelable

@Parcelize
data class Salary(
    val full: String,
    val short: String?
) : Parcelable