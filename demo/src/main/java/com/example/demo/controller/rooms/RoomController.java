package com.example.demo.controller.rooms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.controller.webSocket.pojo.Rooms;
import com.example.demo.controller.webSocket.pojo.UserList;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserSample;
import com.example.demo.pojo.result.Result;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/Room")
public class RoomController {

    @Autowired
    UserServiceImpl userService;
//
//
    @RequestMapping("/create/{roomId}")
    public Result<String> createRoom(@PathVariable("roomId") String roomId, HttpServletRequest request){

        Long userIdByRequest = userService.getUserIdByRequest(request);

        if (Rooms.containsCreator(String.valueOf(userIdByRequest))){
            return new Result<>(HttpStatus.BAD_REQUEST, "只能创建一个房间!");
        }

        if (Rooms.createRoom(roomId, Long.toString(userService.getUserIdByRequest(request)))){

            return new Result<>(HttpStatus.CREATED, "创建房间成功");

        }else {
            return new Result<>(HttpStatus.CONFLICT, "创建房间失败, 房间已存在");
        }

    }

    @RequestMapping("/delete/{roomId}")
    public Result<String> deleteRoom(@PathVariable("roomId") String roomId, HttpServletRequest request){


        Long userIdByRequest = userService.getUserIdByRequest(request);

        if (Rooms.removeRoom(roomId, String.valueOf(userIdByRequest))){

            return new Result<>(HttpStatus.OK, "关闭房间成功!");

        }else {
            return new Result<>(HttpStatus.FORBIDDEN, "关闭房间失败, 不是房主不能关闭");
        }

    }

    @RequestMapping("/join/{roomId}")
    public Result<String> joinRoom(@PathVariable("roomId") String roomId, HttpServletRequest request){

        // 这里可以编写一些用户检测
        if (Rooms.contains(roomId)){
            return new Result<>(true, "加入房间成功");
        } else {
            return new Result<>(HttpStatus.NOT_FOUND, "房间不存在!");
        }

    }

    @RequestMapping("/list/{roomId}")
    public Result<UserList> roomUserList(@PathVariable("roomId") String roomId){

        // 这里可以编写一些用户检测
        if (Rooms.contains(roomId)){

            Set<String> userIds = Rooms.getRoom(roomId).getUserIds();
            String creatorId = Rooms.getRoom(roomId).getCreatorId();

            LambdaQueryWrapper<User> userSelect = new LambdaQueryWrapper<>();
            userSelect.in(User::getId, userIds);
            List<User> userList = userService.getSampleUserList(userSelect);

            System.out.println(userList);

            LambdaQueryWrapper<User> creatorSelect = new LambdaQueryWrapper<>();
            creatorSelect.eq(User::getId, creatorId);
            User creator = userService.getSampleUser(creatorSelect);

            System.out.println(creator);

            UserList userList1 = new UserList(creator, userList);

            return new Result<>(true, userList1);
        }
        return new Result<>(false);

    }


//
//    @RequestMapping("/getUserList/{roomId}")
//    @ResponseBody
////    public ResponseEntity<List<User>> getRoomUserList(@PathVariable("roomId") String roomId){
//    public ResponseEntity<String> getRoomUserList(@PathVariable("roomId") String roomId){
//
////        if (!Rooms.contains(roomId)){
////            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
////        }
////
////        Set<String> userIds = Rooms.getRoom(roomId).getUserIds();
////        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
////        lambdaQueryWrapper.in(User::getId, userIds);
////
////        List<User> users = userService.list(lambdaQueryWrapper);
////
////        ArrayList<User> res = new ArrayList<>();
////        for (User user : users) {
////            User user1 = new User();
////            user1.setId(user.getId());
////            user1.setNickname(user.getNickname());
////            res.add(user1);
////        }
//
////            ResponseEntity<JSON> listResponseEntity = new ResponseEntity<>((res), HttpStatus.ACCEPTED);
//        return new ResponseEntity<>("res", HttpStatus.OK);
//
//
//    }

}
