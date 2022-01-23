package br.com.marcelodio.telaEnavegacao

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
Classe que representa cada contato
 */
@Parcelize
data class Contact(
    var name: String,
    var phone: String,
    var photograph: String
) : Parcelable
