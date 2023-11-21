package com.example.mynewsocketapplication

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {
    lateinit var mSocket:Socket

    @Synchronized
    fun setSocket(){
        try{


            //http://10.0.2.2:3000 remember this url connects the computer's local host not with the imulatter local host...
            mSocket = IO.socket("http://10.0.2.2:3000")
        }catch (e:URISyntaxException){

        }

    }

    @Synchronized
    fun getSocket():Socket{
        return mSocket
    }
}