package com.example.horoscopoapli.data

import com.example.horoscopoapli.R

class HoroscopeProvider {
    companion object {
        val horoscopeList: List<Horoscope> = listOf(
            Horoscope(
                "Aries",
                R.string.horoscope_name_Aries,
                R.string.horoscope_date_aries,
                R.drawable.aries
            ),
            Horoscope(
                "Taurus",
                R.string.horoscope_name_taurus,
                R.string.horoscope_date_aries,
                R.drawable.tauro
            ),
            Horoscope(
                "Gemini",
                R.string.horoscope_name_gemini,
                R.string.horoscope_date_gemini,
                R.drawable.geminis
            ),
            Horoscope(
                "Cancer",
                R.string.horoscope_name_cancer,
                R.string.horoscope_date_cancer,
                R.drawable.cancer
            ),
            Horoscope(
                "Leo",
                R.string.horoscope_name_leo,
                R.string.horoscope_date_leo,
                R.drawable.leo
            ),
            Horoscope(
                "Virgo",
                R.string.horoscope_name_virgo,
                R.string.horoscope_date_virgo,
                R.drawable.virgo
            ),
            Horoscope(
                "Libra",
                R.string.horoscope_name_libra,
                R.string.horoscope_date_libra,
                R.drawable.libra
            ),
            Horoscope(
                "Scorpio",
                R.string.horoscope_name_scorpio,
                R.string.horoscope_date_scorpio,
                R.drawable.scorpio
            ),
            Horoscope(
                "Sagittarius",
                R.string.horoscope_name_sagittarius,
                R.string.horoscope_date_sagittarius,
                R.drawable.sagitario
            ),
            Horoscope(
                "Capricorn",
                R.string.horoscope_name_capricorn,
                R.string.horoscope_date_capricorn,
                R.drawable.capricornio
            ),
            Horoscope(
                "Aquarius",
                R.string.horoscope_name_aquarius,
                R.string.horoscope_date_aquarius,
                R.drawable.aquarius
            ),
            Horoscope(
                "Piscis",
                R.string.horoscope_name_pisces,
                R.string.horoscope_date_pisces,
                R.drawable.piscis
            )
        )


        fun findAll(): List<Horoscope> {
            return horoscopeList

        }

        fun findById(id: String): Horoscope? {
            return horoscopeList.find { it.id == id }
        }


    }
}