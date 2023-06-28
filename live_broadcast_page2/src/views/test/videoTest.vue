<template>


    <div class="container mt-5">


      <div class="row justify-content-center">
        <h1 class="text-white">课程直播Demo</h1>
      </div>

      <div class="row" style="">

        <div class="col-md-7 border align-items-center justify-content-center" style="height: 30rem">
          <video id="localVideo" class="justify-content-center text-center" autoplay playsinline ></video>

        </div>

      </div>

      <div class="row">
        <div class="col-md-7 border align-items-center justify-content-center" style="height: 30rem">
          <video id="remoteVideo" class="justify-content-center text-center" autoplay playsinline ></video>

        </div>
      </div>


      <div class="row justify-content-center">
        <button class="btn btn-primary col-2" @click="openCamera">打开摄像头</button>
        <button class="btn btn-primary offset-1 col-2" @click="openScreen">开启屏幕共享</button>
        <button class="btn btn-primary offset-1 col-2" @click="closeCamera">关闭</button>

      </div>


    </div>



</template>

<script>
import {useWebsocketStore} from "@/store/websocket";
import {useUserStore} from "@/store/userStore";
import {useUtilsStore} from "@/store/utils";
import $ from 'jquery'

export default {
  name: "videoTest",

  data(){
    return{
      videoContent:{

        audio: true,
        video: {
          width:640,
          height:360
        }
      },

      websocket : useWebsocketStore().websocket,
      initChatRoomSuccess: false,
      user:null,

      peerConnect:null,
      localStream: null,
      remoteStream:null,

    }
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
            document.querySelector("#localVideo").srcObject = stream
          });
    },

    closeCamera(){

      document.querySelector("#localVideo").srcObject.getTracks().forEach((track)=>{track.stop()})

    },

    initWebSocket(){

      useUserStore().getUser()
          .then(res=>{
            this.user = res
          }).then(()=>{

        this.initChatRoomSuccess =true

      })
      //  处理websocket协议

      this.websocket.onmessage = (msg)=> {

        console.log("收到数据====" + msg.data)
        let data = JSON.parse(msg.data)  // 对收到的json数据进行解析， 类似这样的： {"users": [{"username": "zhang"},{ "username": "admin"}]}
        const messageType = useWebsocketStore().messageType;

        const privateFunction = {

          sendMessageHandler : ()=>{
            this.$refs.chatRoom.getMessage(data);
          },

          controllerMessageHandler: ()=>{

            const controllerType = useWebsocketStore().controllerType;

            switch (data.controllerType) {
              case (controllerType.disband):
                this.$router.push({name:'createRoom'})
                location.reload();
                useUtilsStore().message(data.message, "warning");
                break

            }

          }

        }


        // 开始分类别处理数据
        switch (data.type){

          case messageType.sendMessage:
            privateFunction.sendMessageHandler()
            break

          case messageType.controllerMessage:
            privateFunction.controllerMessageHandler();
            break

          case messageType.offer:



        }


      }
      this.websocket.onerror = (err)=>{

      }

      this.websocket.onclose = (closeEvent)=>{
        console.log("close!!!!!!!!!!!!!!!!!!!!!!!!!!")
      }

    },

    // 处理PeerConnection

    createPeerConnection(){

      const privateFunction = {


        // 用于传输candidate
        handleIceCandidate : (event)=>{

          if (event.candidate){

            const candidateMsg={

              type: useWebsocketStore().messageType.candidate,
              roomId: useWebsocketStore().roomId,
              id: this.user.id,
              message: JSON.stringify(event.candidate)
            }

            useWebsocketStore().sendMessage(JSON.stringify(candidateMsg))

            console.log('handleIceCandidate message:' + candidateMsg)

          }else {
            console.warn("candidate没有!")
          }

        },
        handleRemoteStreamAdd: (event)=>{

          this.remoteStream = event.streams[0]
          document.querySelector('#remoteVideo').srcObject = this.remoteStream

        },

      }

      this.peerConnect = new RTCPeerConnection(null);
      this.peerConnect.onicecandidate = privateFunction.handleIceCandidate
      this.peerConnect.ontrack = privateFunction.handleRemoteStreamAdd;

      this.localStream.getTracks().forEach((track)=>this.peerConnect.addTrack(track, this.localStream))

    },

    doOffer: ()=>{

      const privateFunction = {

        createOfferAndSendMessage: (session)=>{

          this.peerConnect.setLocalDescription(session)
              .then(()=>{
                const jsonMsg = {
                  type: useWebsocketStore().messageType.offer,
                  roomId: this.roomId,
                  id: this.user.id,
                  message: JSON.stringify(session)
                }

                useWebsocketStore().sendMessage(JSON.stringify(jsonMsg))

              })
              .catch()

        }
      }

      // start

      if (this.peerConnect == null){
        this.createPeerConnection()
      }
      this.peerConnect.createOffer()
          .then(privateFunction.createOfferAndSendMessage)
          .catch( err=>{
            console.log(err)
          })



    }

  },

  mounted() {

    let sdk = null; // Global handler to do cleanup when republishing.
    var startPublish = function() {
      $('#rtc_media_player').show();

      // Close PC when user replay.
      if (sdk) {
        sdk.close();
      }
      sdk = new SrsRtcPublisherAsync();

      // User should set the stream when publish is done, @see https://webrtc.org/getting-started/media-devices
      // However SRS SDK provides a consist API like https://webrtc.org/getting-started/remote-streams
      $('#rtc_media_player').prop('srcObject', sdk.stream);
      // Optional callback, SDK will add track to stream.
      // sdk.ontrack = function (event) { console.log('Got track', event); sdk.stream.addTrack(event.track); };

      // https://developer.mozilla.org/en-US/docs/Web/Media/Formats/WebRTC_codecs#getting_the_supported_codecs
      sdk.pc.onicegatheringstatechange = function (event) {
        if (sdk.pc.iceGatheringState === "complete") {
          $('#acodecs').html(SrsRtcFormatSenders(sdk.pc.getSenders(), "audio"));
          $('#vcodecs').html(SrsRtcFormatSenders(sdk.pc.getSenders(), "video"));
        }
      };

      // For example: webrtc://r.ossrs.net/live/livestream
      var url = $("#txt_url").val();
      sdk.publish(url).then(function(session){
        $('#sessionid').html(session.sessionid);
        $('#simulator-drop').attr('href', session.simulator + '?drop=1&username=' + session.sessionid);
      }).catch(function (reason) {
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

        sdk.close();
        $('#rtc_media_player').hide();
        console.error(reason);
      });
    };

    $('#rtc_media_player').hide();
    var query = parse_query_string();
    srs_init_rtc("#txt_url", query);

    $("#btn_publish").click(startPublish);
    // Never play util windows loaded @see https://github.com/ossrs/srs/issues/2732
    if (query.autostart === 'true') {
      window.addEventListener("load", function(){ startPublish(); });
    }


  }
}
</script>

<style scoped>

</style>