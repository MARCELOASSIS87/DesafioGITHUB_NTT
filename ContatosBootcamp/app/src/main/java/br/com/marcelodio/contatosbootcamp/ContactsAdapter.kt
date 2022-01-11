package br.com.marcelodio.contatosbootcamp

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter (val contactsList: ArrayList<Contact>): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_view, parent, false)
        //aqui eu inflo o layout dos contacts_view que vai dentro do Recycler
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ContactsAdapter.ViewHolder, position: Int) { //faz o bind da view holder
        holder.bindItem(contactsList[position])//Esse bind item é pra cda item vc pegar a posição dele
    }

    override fun getItemCount(): Int {
        return contactsList.size //Aqui eu passo o tamanho da lista que vier no contact list
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //bind item de cada item desse daí

        fun bindItem(contact: Contact) {
            //itens que vão fazer parte do item view que avi dentro do recyccler view

            val textName = itemView.findViewById<TextView>(R.id.contact_name)
            val textPhone = itemView.findViewById<TextView>(R.id.conatct_phone_number)
            //buscando do layout as variaveis

            textName.text = contact.name
            textPhone.text = contact.phoneNumber
        }

    }

}