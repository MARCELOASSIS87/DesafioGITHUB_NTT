package com.bootcamp.watch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.marcelodio.shared.Meal
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson

class MealListActivity : AppCompatActivity(),
        MealListAdapter.Callback, GoogleApiClient.ConnectionCallbacks {

  private var adapter: MealListAdapter? = null
  private lateinit var client: GoogleApiClient
  private var connectedNode: List<Node>? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


    val meals = MealStore.fetchMeals(this)
    adapter = MealListAdapter(meals, this)
    list.adapter = adapter
    list.layoutManager = LinearLayoutManager(this)
    /*
    inicio o google client e build ele e conecto ele
     */
    client = GoogleApiClient.Builder(this)
      .addApi(Wearable.API)
      .addConnectionCallbacks(this)
      .build()
    client.connect()
  }

  override fun mealClicked(meal: Meal) {
    /*
    Quando clica na celula ele cria um json e envia para a nuvem
     */
    val gson = Gson()
    connectedNode?.forEach{ node ->
      val bytes = gson.toJson(meal).toByteArray()
      Wearable.MessageApi.sendMessage(client,node.id,"/meal", bytes)
    }
  }

  override fun onConnected(p0: Bundle?) {
    /*
    quando conectar ele pede pra ver se tem algum wear conectado se sim,
    ele se conecta efetivamente
     */
    Wearable.NodeApi.getConnectedNodes(client).setResultCallback {
      connectedNode = it.nodes
      /*
      colocando um listener no mobile
       */
      Wearable.DataApi.addListener(client){data ->
        val meal = Gson().fromJson(String(data[0].dataItem.data), Meal::class.java)
        adapter?.updateMeal(meal)
      }
    }

  }
  override fun onConnectionSuspended(p0: Int) {
    /*Quando nao ha a conexao*/
    connectedNode = null
  }
}
