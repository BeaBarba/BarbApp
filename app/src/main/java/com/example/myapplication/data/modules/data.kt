package com.example.myapplication.data.modules

import kotlinx.serialization.Serializable

enum class Theme { Light, Dark, System }

enum class CustomerType{ Pivato, Azienda }

enum class FrequencyType { Anno, Mese, Settimana, Nessuna }

enum class DeadlineType { Tipo, Singola, Periodica }

enum class FilterKey { ASC_DATE, DESC_DATE, ASC_SELLER, DESC_SELLER }

enum class SplitNumber{ Mono, Dual, Trial, Quadri, Penta }

enum class JobType { ELE, ALA, CDZ }

enum class MachineType { Esterna, Interna }