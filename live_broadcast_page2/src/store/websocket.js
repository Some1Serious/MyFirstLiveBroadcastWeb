import { defineStore } from 'pinia'
import request from "@/utils/request";

export const useWebsocketStore = defineStore('websocket', {

    state: () => ({

        websocket: undefined,
        roomId: undefined,

        messageType:{
            enter: 1,
            leave: 2,
            sendMessage: 3,
            systemMessage: 4,
            controllerMessage: 5,
            candidate: 6,
            offer: 7
        },

        controllerType:{
            conflict:1,
            success:2,
            disband:3
        },


    }),

    actions:{

        sendMessage(msg){

            if (this.websocket && msg){
                console.log(msg)
                this.websocket.send(msg);
            }

        }

    }


})