package com.netease.nim.uikit.session.extension;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachmentParser;

/**
 * Created by zhoujianghua on 2015/4/9.
 */
public class CustomAttachParser implements MsgAttachmentParser {

    private static final String KEY_TYPE = "type";
    private static final String KEY_DATA = "data";

    public static String packData(int type, JSONObject data) {
        JSONObject object = new JSONObject();
        object.put(KEY_TYPE, type);
        Log.i("json", "data:" + data);
        if (data != null) {
            object.put(KEY_DATA, data);
        }

        return object.toJSONString();
    }

    @Override
    public MsgAttachment parse(String json) {
        CustomAttachment attachment = null;
        try {
            JSONObject object = JSON.parseObject(json);
            int type = object.getInteger(KEY_TYPE);
            JSONObject data = object.getJSONObject(KEY_DATA);
            switch (type) {
                case CustomAttachmentType.Guess:
                    attachment = new GuessAttachment();
                    break;
                case CustomAttachmentType.SnapChat:
                    return new SnapChatAttachment(data);
                case CustomAttachmentType.Sticker:
                    attachment = new StickerAttachment();
                    break;
                case CustomAttachmentType.RTS:
                    attachment = new RTSAttachment();
                    break;
                case CustomAttachmentType.Like:
                    attachment=new LikeAttachment();
                    break;
                case CustomAttachmentType.Danmu:
                    attachment=new DanmuAttachment();
                    break;
                case CustomAttachmentType.Gift:
                    attachment=new GiftInfoAttachment();
                    break;
                case CustomAttachmentType.ChatRoom:
                    attachment=new ChatAttachment();
                    break;
                default:
                    attachment = new DefaultCustomAttachment();
                    break;
            }

            if (attachment != null) {
                attachment.fromJson(data);
            }
        } catch (Exception e) {

        }

        return attachment;
    }
}
