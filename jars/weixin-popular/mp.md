微信公众平台各接口文档对应的调用方法（实现）。


- 开始前必读
  - 接口调用频次限制说明 - weixin.popular.api.ClearQuotaAPI.clear_quota(String access_token, String appid)
- 开始开发
  - 接入指南 - weixin.popular.util.SignatureUtil.generateEventMessageSignature(String token, String timestamp, String nonce)
  - 获取 access_token - weixin.popular.api.TokenAPI.token(String appid, String secret), weixin.popular.support.TokenManager
  - 获取微信服务器 IP 地址 - weixin.popular.api.CallbackipAPI.getcallbackip(String access_token)
- 自定义菜单
  - 自定义菜单创建接口 - weixin.popular.api.MenuAPI.menuCreate(String access_token, String menuJson), weixin.popular.api.MenuAPI.menuCreate(String access_token, MenuButtons menuButtons)
  - 自定义菜单查询接口 - weixin.popular.api.MenuAPI.menuGet(String access_token)
  - 自定义菜单删除接口 - weixin.popular.api.MenuAPI.menuDelete(String access_token)
  - 自定义菜单事件推送 -
  - 个性化菜单接口
    - 创建个性化菜单 - weixin.popular.api.MenuAPI.menuAddconditional(String access_token, String menuJson)
    - 删除个性化菜单 - weixin.popular.api.MenuAPI.menuAddconditional(String access_token, MenuButtons menuButtons)
    - 测试个性化菜单匹配结果 - weixin.popular.api.MenuAPI.menuDelconditional(String access_token, String menuid)
    - 查询个性化菜单 - weixin.popular.api.MenuAPI.menuTrymatch(String access_token, String user_id)
  - 获取自定义菜单配置接口 - weixin.popular.api.MenuAPI.get_current_selfmenu_info(String access_token)
- 消息管理
  - 接收消息-接收普通消息 - weixin.popular.util.XMLConverUtil.convertToObject(Class clazz, InputStream inputStream), weixin.popular.util.SignatureUtil.generateEventMessageSignature(String token, String timestamp, String nonce), 参见 https://github.com/liyiorg/weixin-popular/wiki/%E6%B6%88%E6%81%AF%E4%BA%8B%E4%BB%B6%E6%8E%A5%E6%94%B6, https://github.com/liyiorg/weixin-popular/wiki/%E6%B6%88%E6%81%AF%E4%BA%8B%E4%BB%B6%E6%8E%A5%E6%94%B6(%E5%8A%A0%E5%AF%86)
  - 接收消息-接收事件推送 - 与接收普通消息一样处理， EventMessage 中包含了 Event/EventKey
  - 发送消息-被动回复消息 - weixin.popular.bean.xmlmessage.XMLMessage.outputStreamWrite(OutputStream outputStream), weixin.popular.bean.xmlmessage.XMLMessage.outputStreamWrite(OutputStream outputStream, WXBizMsgCrypt bizMsgCrypt)
  - 发送消息-客服消息
    - 添加客服帐号 - weixin.popular.api.CustomserviceAPI.kfaccountAdd(String access_token, String kf_account, String nickname, String password)
    - 修改客服帐号 - weixin.popular.api.CustomserviceAPI.kfaccountUpdate(String access_token, String kf_account, String nickname, String password)
    - 删除客服帐号 - weixin.popular.api.CustomserviceAPI.kfaccountDel(String access_token, String kf_account)
    - 设置客服帐号的头像 - weixin.popular.api.CustomserviceAPI.kfaccountUploadHeadimg(String access_token, String kf_account, File media)
    - 获取所有客服账号 - weixin.popular.api.CustomserviceAPI.getkflist(String access_token)
    - 客服接口-发消息 - weixin.popular.api.MessageAPI.messageCustomSend(String access_token, String messageJson)
  - 发送消息-群发接口和原创校验
    - 上传图文消息内的图片获取 URL - weixin.popular.api.MediaAPI.mediaUploadimg(String access_token, File media)
    - 上传图文消息素材 - weixin.popular.api.MessageAPI.mediaUploadnews(String access_token, String messageJson)
    - 根据标签进行群发 - weixin.popular.api.MessageAPI.messageMassSendall(String access_token, String messageJson)
    - 根据 OpenID 列表群发 - weixin.popular.api.MessageAPI.messageMassSend(String access_token, String messageJson)
    - 删除群发 - weixin.popular.api.MessageAPI.messageMassDelete(String access_token, String msg_id, Integer article_idx)
    - 预览接口 - weixin.popular.api.MessageAPI.messageMassPreview(String access_token, Preview preview)
    - 查询群发消息发送状态 - weixin.popular.api.MessageAPI.messageMassGet(String access_token, String msg_id)
  - 发送消息-模板消息接口
    - 设置所属行业 - weixin.popular.api.MessageAPI.templateApi_set_industry(String access_token, String... industrys)
    - 获取设置的行业信息 - weixin.popular.api.MessageAPI.templateGet_industry(String access_token)
    - 获得模板 ID - weixin.popular.api.MessageAPI.templateApi_add_template(String access_token, String template_id_short)
    - 获取模板列表 - weixin.popular.api.MessageAPI.templateGet_all_private_template(String access_token)
    - 删除模板 - weixin.popular.api.MessageAPI.templateDel_private_template(String access_token, String template_id)
    - 发送模板消息 - weixin.popular.api.MessageAPI.messageTemplateSend(String access_token, TemplateMessage templateMessage)
    - 事件推送 - weixin.popular.bean.message.EventMessage
  - 发送消息-一次性订阅消息 - FIXME
  - 获取公众号的自动回复规则 - weixin.popular.api.MessageAPI.get_current_autoreply_info(String access_token)
- 微信网页开发
  - 微信网页授权
    - 第一步：用户同意授权，获取 code - weixin.popular.api.SnsAPI.connectOauth2Authorize(String appid, String redirect_uri, boolean snsapi_userinfo, String state)
    - 第二步：通过 code 换取网页授权 access_token - weixin.popular.api.SnsAPI.oauth2AccessToken(String appid, String secret, String code)
    - 第三步：刷新 access_token - weixin.popular.api.SnsAPI.oauth2RefreshToken(String appid, String refresh_token)
    - 第四步：拉取用户信息 - weixin.popular.api.SnsAPI.userinfo(String access_token, String openid, String lang)
    - 附：检验授权凭证(access_token)是否有效 - weixin.popular.api.SnsAPI.auth(String access_token, String openid)
  - 微信 JS-SDK 说明文档
    - 概述
      - JSSDK 使用步骤
        - 步骤三：通过 config 接口注入权限验证配置 - weixin.popular.util.JsUtil.generateConfigJson(String jsapi_ticket, boolean debug, String appId, String url, String... jsApiList)
    - 附录1-JS-SDK 使用权限签名算法 - weixin.popular.api.TicketAPI.ticketGetticket(String access_token), weixin.popular.support.TicketManager, weixin.popular.util.JsUtil.generateConfigSignature(String noncestr, String jsapi_ticket, String timestamp, String url)


接收消息类层次
- weixin.popular.bean.message.EventMessage


发送消息类层次
- weixin.popular.bean.message.message.Message
  - weixin.popular.bean.message.message.ImageMessage
  - weixin.popular.bean.message.message.MusicMessage
  - weixin.popular.bean.message.message.NewsMessage
  - weixin.popular.bean.message.message.TextMessage
  - weixin.popular.bean.message.message.VideoMessage
  - weixin.popular.bean.message.message.VoiceMessage
  - weixin.popular.bean.message.message.WxcardMessage


发送消息辅助类
- weixin.popular.bean.xmlmessage.XMLMessage
  - weixin.popular.bean.xmlmessage.XMLImageMessage
  - weixin.popular.bean.xmlmessage.XMLMusicMessage
  - weixin.popular.bean.xmlmessage.XMLNewsMessage
  - weixin.popular.bean.xmlmessage.XMLTextMessage
  - weixin.popular.bean.xmlmessage.XMLTransferCustomerServiceMessage
  - weixin.popular.bean.xmlmessage.XMLVideoMessage
  - weixin.popular.bean.xmlmessage.XMLVoiceMessage


# Reference
- http://mp.weixin.qq.com/wiki/index.php
