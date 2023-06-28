import {useUtilsStore} from "@/store/utils";
import {useWebsocketStore} from "@/store/websocket";
import Router from '@/router'

export default class MyWebSocket {


    constructor(url) {

        this.websocketStore = useWebsocketStore()

        this.websocket = new WebSocket(url);
        this.websocketStore.websocket = this.websocket
        this.messageType = this.websocketStore.messageType
        this.controllerType = this.websocketStore.controllerType

        // this.websocket.onopen = event => {
        //
        //     useUtilsStore().message("已连接服务器", 'success')
        //     let message = {from: this.user.nickname, type: this.messageType.enter}
        //     super.send(JSON.stringify(message))
        //
        // };

        this.websocket.onmessage = msg => {

            console.log("收到数据====" + msg.data)
            let data = JSON.parse(msg.data)  // 对收到的json数据进行解析， 类似这样的： {"users": [{"username": "zhang"},{ "username": "admin"}]}

            // 开始分类别处理数据
            switch (data.type){

                case this.messageType.sendMessage:
                    this.getMessageHandler(data)
                    break

                case this.messageType.controllerMessage:
                    this.controllerMessageHandler(data);
                    break

                // case this.messageType.

                // case messageType.offer:

            }
        };

        this.websocket.onerror = err => {
            console.log("websocketERR: " + err)
        };

        this.websocket.onclose = event => {
            useUtilsStore().message('连接会话已关闭', 'warning')

            Router.push({name:'home'})
        };
    }

    // 要重写这个方法
    getMessageHandler(data){

    }

    // 分类处理控制事件
    controllerMessageHandler(data){

        const controllerType = this.websocketStore.controllerType

        switch (data.controllerType) {
            case (controllerType.disband):

                Router.push({name:'home'})
                location.reload();
                useUtilsStore().message(data.message, "warning");
                break

            case (controllerType.success):
                useUtilsStore().message(data.message, "success")
                break

            case (controllerType.conflict):
                useUtilsStore().message(data.message, "warning")

        }

    }


    send(data) {
        if (this.websocket.readyState === WebSocket.OPEN && data) {
            this.websocket.send(data);
            return true;
        } else {
            return false;
        }
    }
    //
    // close() {
    //     this.websocket.close();
    // }
}
