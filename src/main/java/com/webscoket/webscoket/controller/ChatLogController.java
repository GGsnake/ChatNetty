package com.webscoket.webscoket.controller;

import com.webscoket.webscoket.bean.dto.ChatLogDto;
import com.webscoket.webscoket.entity.ChatLog;
import com.webscoket.webscoket.service.ChatLogService;
import com.webscoket.webscoket.utils.Constants;
import com.webscoket.webscoket.utils.Response;
import com.webscoket.webscoket.utils.ResponseCode;
import com.webscoket.webscoket.utils.ResponseUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/Chat")
public class ChatLogController {
    @Autowired
    private ChatLogService chatLogService;

    /**
     * [新增]
     *
     * @author Snake
     * @date 2019/06/11
     **/
    @RequestMapping("/insert")
    public Response insert(@Valid @RequestBody ChatLogDto chatLog) {
        ChatLog bean = new ChatLog();
        BeanUtils.copyProperties(chatLog, bean);
        try {
            boolean insert = chatLogService.insert(bean);
            if (!insert) {
                return ResponseUtil.fail(ResponseCode.CHAT_ADD_FAIL);
            }
            return ResponseUtil.success();
        } catch (Exception e) {
            return ResponseUtil.fail(ResponseCode.CHAT_ADD_FAIL);
        }

    }



//    /**
//     * [更新]
//     *
//     * @author Snake
//     * @date 2019/06/11
//     **/
//    @RequestMapping("/update")
//    public Response<String> update(ChatLog chatLog) {
//        return chatLogService.update(chatLog);
//    }

//
//    /**
//     * [查詢] 分頁查詢
//     *
//     * @author Snake
//     * @date 2019/06/11
//     **/
//    @RequestMapping("/pageList")
//    public Response pageList(@RequestParam(required = false, defaultValue = "0") int offset,
//                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
//        return chatLogService.selectPage(offset, pagesize);
//    }
}
