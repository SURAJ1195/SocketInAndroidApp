package com.example.mynewsocketapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SocketHandler.setSocket()
        val mSocket = SocketHandler.getSocket()

        setContent {
            val count = remember {
                mutableStateOf(0)
            }
            mSocket.connect()
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = count.value.toString(), fontSize = 14.sp)
                Button(onClick = { mSocket.emit("counter",) }) {
                    Text("count+")
                }
              CoroutineScope(Dispatchers.IO).launch{
                  mSocket.on("counter"){args ->
                      if(args[0]!=null){
                          val counter = args[0] as Int

                          count.value = counter

                      }
                  }
              }

            }
        }
    }
}
