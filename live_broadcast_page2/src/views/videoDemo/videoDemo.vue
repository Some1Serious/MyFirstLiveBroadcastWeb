<script setup>
import TopBorder from "@/components/topBorder.vue";
</script>

<template>
  <div class="container mt-5">

    <div class="container mt-3">
      <top-border :is_student_stuff_show=true></top-border>
    </div>

    <div class="row justify-content-center">
      <h1 class="text-white">课程直播Demo</h1>
    </div>

    <div class="row " style="">

      <div class="col-md-7 border align-items-center justify-content-center p-0" style="height: 30rem; margin-bottom: 5rem">
        <video id="localVideo" class="justify-content-center text-center" controls autoplay playsinline style="height: 100%; width: 100%"></video>
        <div class="container">
          <div class="row mb-3">
            <button class="btn btn-primary col" @click="openCamera">打开摄像头</button>
            <button class="btn btn-primary ml-md-1 col" @click="openScreen">开启屏幕共享</button>
            <button class="btn btn-primary ml-md-1 col" @click="liveBroadcast">直播</button>
            <button class="btn btn-primary ml-md-1 col" @click="startPlay">观看</button>
            <button class="btn btn-primary ml-md-1 col" @click="drawBoard">画板</button>
            <button class="btn btn-primary ml-md-1 col" @click="closeCamera">关闭</button>

          </div>
        </div>

      </div>


      <div class="col  border ml-md-3 p-0" style="height: 40rem;">
        <ChatRoom :user="user" v-if="initChatRoomSuccess" ref="chatRoom" ></ChatRoom>
      </div>
    </div>




  </div>

</template>

<script>
import {useWebsocketStore} from "@/store/websocket";
import {useUtilsStore} from "@/store/utils";

import ChatRoom from "@/views/videoDemo/chat/chatRoom.vue";
import {useUserStore} from "@/store/userStore";

// 引用srs相关
// @TODO
import {srs_init_rtc} from '@/srs/js/srs.page'
import {SrsRtcPublisherAsync, SrsError, SrsRtcPlayerAsync} from '@/srs/js/srs.sdk'
import {parse_query_string} from '@/srs/js/winlin.utility'
import MyWebSocket from "@/views/videoDemo/js/websocketClass";
// import '@/srs/js/adapter-7.4.0'

export default {
  name: "videoDemo",

  components:{ChatRoom},

  data(){
    return{
      videoContent:{

        audio: true,
        video: {
          width:640,
          height:360
        }
      },

      roomId : this.$route.query.roomId,
      websocket : useWebsocketStore().websocket,
      initChatRoomSuccess: false,
      user:null,

      sdk : '',
      pushUrl:`webrtc://${window.location.host}/live/livestream/${this.$route.query.roomId}`

    }
  },

  computed:{


  },

  methods:{

    openCamera(){
      navigator.mediaDevices.getUserMedia(this.videoContent)
          .then(stream=>{
            this.localStream = stream
            document.querySelector("#localVideo").srcObject = stream
          })
          .catch(stream=>{
            console.log(stream)
          })
    },

    openScreen(){
      navigator.mediaDevices.getDisplayMedia(this.videoContent)
          .then(stream=>{
            this.localStream = stream
            document.querySelector("#localVideo").srcObject = stream
          });
    },

    closeCamera(){

      document.querySelector("#localVideo").srcObject.getTracks().forEach((track)=>{track.stop()})
      this.localStream = null

      if (this.sdk){
        this.sdk.close()
        this.sdk = null
      }


    },

    drawBoard(){

      window.open('src/views/videoDemo/SvgDraw/draw.html','_blank');

    },

    liveBroadcast(){

      //TODO

        // Close PC when user replay.
        if (this.sdk) {
          this.sdk.close();
        }
        this.sdk = new SrsRtcPublisherAsync(this.localStream);

        // User should set the stream when publish is done, @see https://webrtc.org/getting-started/media-devices
        // However SRS SDK provides a consist API like https://webrtc.org/getting-started/remote-streams
        // $('#rtc_media_player').prop('srcObject', sdk.stream);
        // Optional callback, SDK will add track to stream.
        // sdk.ontrack = function (event) { console.log('Got track', event); sdk.stream.addTrack(event.track); };

        // https://developer.mozilla.org/en-US/docs/Web/Media/Formats/WebRTC_codecs#getting_the_supported_codecs
        // this.sdk.pc.onicegatheringstatechange = function (event) {
        //   if (this.sdk.pc.iceGatheringState === "complete") {
        //     $('#acodecs').html(SrsRtcFormatSenders(sdk.pc.getSenders(), "audio"));
        //     $('#vcodecs').html(SrsRtcFormatSenders(sdk.pc.getSenders(), "video"));
        //   }
        // };

        // For example: webrtc://r.ossrs.net/live/livestream
        // this.sdk.publish('this.pushUrl').then(function(session){
        this.sdk.publish(this.pushUrl).then(function(session){

          console.log("session.sessionid" + session.sessionid)
          useUtilsStore().message('直播成功!', 'success')
          // $('#simulator-drop').attr('href', session.simulator + '?drop=1&username=' + session.sessionid);
        }).catch((reason) => {
          // Throw by sdk.
          if (reason instanceof SrsError) {
            if (reason.name === 'HttpsRequiredError') {
              alert(`WebRTC推流必须是HTTPS或者localhost：${reason.name} ${reason.message}`);
            } else {
              alert(`${reason.name} ${reason.message}`);
            }
          }
          // See https://developer.mozilla.org/en-US/docs/Web/API/MediaDevices/getUserMedia#exceptions
          if (reason instanceof DOMException) {
            if (reason.name === 'NotFoundError') {
              alert(`找不到麦克风和摄像头设备：getUserMedia ${reason.name} ${reason.message}`);
            } else if (reason.name === 'NotAllowedError') {
              alert(`你禁止了网页访问摄像头和麦克风：getUserMedia ${reason.name} ${reason.message}`);
            } else if (['AbortError', 'NotAllowedError', 'NotFoundError', 'NotReadableError', 'OverconstrainedError', 'SecurityError', 'TypeError'].includes(reason.name)) {
              alert(`getUserMedia ${reason.name} ${reason.message}`);
            }
          }

          this.sdk.close();
          console.error(reason);
          useUtilsStore().alert('直播开启失败!', 'danger')
        });



    },

    startPlay(){

      // Close PC when user replay.
      if (this.sdk) {
        this.sdk.close();
      }
      this.sdk = new SrsRtcPlayerAsync();

      // https://webrtc.org/getting-started/remote-streams
      document.querySelector('#localVideo').srcObject = this.sdk.stream
      // Optional callback, SDK will add track to stream.
      // sdk.ontrack = function (event) { console.log('Got track', event); sdk.stream.addTrack(event.track); };

      // For example: webrtc://r.ossrs.net/live/livestream
      this.sdk.play(this.pushUrl).then(function(session){

      }).catch(function (reason) {
        this.sdk.close();
        console.error(reason);
        useUtilsStore().message('播放失败!', 'danger')
      });

    },

    // initSrs(){
    //
    //   const query = parse_query_string();
    //
    //   this.pushUrl = srs_init_rtc(query);
    //   // Never play util windows loaded @see https://github.com/ossrs/srs/issues/2732
    //   // if (query.autostart === 'true') {
    //   //   window.addEventListener("load", function(){ startPublish(); });
    //   // }
    //
    // },

    initWebSocket(){

      useUserStore().getUser()
          .then(res=>{
            this.user = res
          }).then(()=>{

            this.initChatRoomSuccess =true

      })
      //  处理websocket协议

      this.websocket = new MyWebSocket(`ws://${location.host}/wsUrl/audience/${this.roomId}/${useUserStore().userId}`)



      this.websocket.getMessageHandler = (msg)=>{
        this.$refs.chatRoom.getMessage(msg);
      }

      this.websocket.websocket.onopen = event => {

        useUtilsStore().message("已连接服务器", 'success')
        let message = {from: this.user.nickname, type: useWebsocketStore().messageType.enter}
        this.websocket.send(JSON.stringify(message))

      };

      // this.websocket.onmessage = (msg)=> {
      //
      //   console.log("收到数据====" + msg.data)
      //   let data = JSON.parse(msg.data)  // 对收到的json数据进行解析， 类似这样的： {"users": [{"username": "zhang"},{ "username": "admin"}]}
      //   const messageType = useWebsocketStore().messageType;
      //
      //   const privateFunction = {
      //
      //
      //
      //     controllerMessageHandler: ()=>{
      //
      //       const controllerType = useWebsocketStore().controllerType;
      //
      //       switch (data.controllerType) {
      //         case (controllerType.disband):
      //           this.$router.push({name:'createRoom'})
      //           location.reload();
      //           useUtilsStore().message(data.message, "warning");
      //           break
      //
      //       }
      //
      //     }
      //
      //   }
      //
      //
      //   // 开始分类别处理数据
      //   switch (data.type){
      //
      //     case messageType.sendMessage:
      //       privateFunction.sendMessageHandler()
      //       break
      //
      //     case messageType.controllerMessage:
      //       privateFunction.controllerMessageHandler();
      //       break
      //
      //     case messageType.offer:
      //
      //
      //
      //   }
      //
      //
      // }
      // this.websocket.onerror = (err)=>{
      //
      // }
      //
      // this.websocket.onclose = (closeEvent)=>{
      //   console.log("close!!!!!!!!!!!!!!!!!!!!!!!!!!")
      // }

    },



  },


  mounted() {

    console.log(this.pushUrl)

    this.initWebSocket();
    console.log(this.websocket)

  }
}
</script>

<style scoped>

</style>

