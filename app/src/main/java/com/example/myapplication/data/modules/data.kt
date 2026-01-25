package com.example.myapplication.data.modules

import kotlinx.serialization.Serializable

enum class Theme { Light, Dark, System }

enum class CustomerType{ Private, Company }

enum class FrequencyType { Anno, Mese, Settimana, Nessuna }

enum class DeadlineType { Tipo, Singola, Periodica }

enum class FilterKey { ASC_DATE, DESC_DATE, ASC_SELLER, DESC_SELLER }

enum class MachineType{ Mono, Dual, Trial, Quadri, Penta }

enum class JobType { ELE, ALA, CDZ }