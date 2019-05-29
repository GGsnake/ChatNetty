sample
===

select #use("cols")# from message where #use("condition")#

cols
===
        `id`,
        `content`,
        `accid`,
        `sid`,
        `sendtime`,
        `status`

updateSample
===
        `id=#id#`,
        `content=#content#`,
        `accid=#accid#`,
        `sid=#sid#`,
        `sendtime=#sendtime#`,
        `status=#status#`

condition
===
    1 = 1
    @if(!isEmpty(id)){
      and `id`=#id#
    @}
    @if(!isEmpty(content)){
      and `content`=#content#
    @}
    @if(!isEmpty(accid)){
      and `accid`=#accid#
    @}
    @if(!isEmpty(sid)){
      and `sid`=#sid#
    @}
    @if(!isEmpty(sendtime)){
      and `sendtime`=#sendtime#
    @}
    @if(!isEmpty(status)){
      and `status`=#status#
    @}
