package br.com.marcelodio.telaEnavegacao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.marcelodio.telaEnavegacao.R.*

/*
Classe gerenciadora de lista
 */
class ContactAdapter(var listener: ClickItemContactListener) : RecyclerView.Adapter<ContactAdapter.ContactAdapterViewHolder>() {

    //Este adapter vai recebr uma lista e ele precisa armazenar
    // esta lista na classe para manuser posteriormente

    private var list: MutableList<Contact> = mutableListOf()

    /*
        Este método cria a view, o layout e inflando ele para depois popular com o onBind
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            layout.contact_item,
            parent,
            false
        )//inflo o arquivo xml responsavel por cada card na tela
        return ContactAdapterViewHolder(view,list,listener)
    }

    /*
    Responsavel por rodar em cada item do seu array e preencher ele na tela
    ele lê o item e popula a lista no recycler view
     */
    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    /*
    aqui eu informo quantos itens tem na lista
     */
    override fun getItemCount(): Int {
        return list.size
    }
    /*
    metodo que pega uma lista externa a esta classe e atualiza a lista que está aqui
     */
    fun updateList(list: List<Contact>){
        this.list.clear() //limpa a lista aqui
        this.list.addAll(list) //add a lista que chegou nela
        notifyDataSetChanged() // notifica o adapter que a lista que ele utiliza para renderizar mudou
    }

    /*
        Esta classe gerencia cada contato individualmente
     */
    class ContactAdapterViewHolder(itemView: View, var list: List<Contact>,var listener: ClickItemContactListener) :
        RecyclerView.ViewHolder(itemView) {

        private val tvName = itemView.findViewById<TextView>(id.tv_name)
        private val tvPhone = itemView.findViewById<TextView>(id.tvPhoneDetail)
        private val tvPhotograph = itemView.findViewById<ImageView>(id.iv_photograph)

        init {
            itemView.setOnClickListener {
                listener.clickItemContact(list[adapterPosition])
            }
        }

        fun bind(contact: Contact) {
            tvName.text = contact.name
            tvPhone.text = contact.phone
        }
    }
}