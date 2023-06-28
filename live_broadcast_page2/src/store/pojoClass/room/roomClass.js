import {useWebsocketStore} from "@/store/websocket";
import request from "@/utils/request";

export default class Room{

    static getRoomId(){
        return useWebsocketStore().roomId
    }

    static async getUserList(roomId){

        const roomId1 = useWebsocketStore().roomId

        if (roomId1){
            return Promise.reject('RoomId not find');
        }else {
            let userList
            // userList = await request.get('/Room/getUserList/' + 0)
            userList = await request.get('/Room/create/' + 0)
            console.log(userList)
            return userList
        }

    }

}