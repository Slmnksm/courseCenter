<%-- 
    Document   : message
    Created on : 20.May.2017, 10:13:45
    Author     : SelmanKasim
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="tr.kasim.cc.model.Message"%>
<%@page import="tr.kasim.cc.util.GeneralUtil"%>
<%@page import="tr.kasim.cc.model.User"%>
<%@page import="tr.kasim.cc.app.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User sourceUser = (User) Application.getApp().getMainService().getById(GeneralUtil.toInt(request.getParameter("srcId")), User.class);
    User destUser = (User) Application.getApp().getMainService().getById(GeneralUtil.toInt(request.getParameter("destId")), User.class);
    
    Map<User, List<Message>> srcUserMsgs = Application.getApp().getMainService().getMessages(sourceUser.getId(), destUser.getId());
    Map<User, List<Message>> dstUserMsg = Application.getApp().getMainService().getMessages(destUser.getId(), sourceUser.getId());
    
    Map<Long, Message> oldMessages = new TreeMap();
    for (User msgDestUser : srcUserMsgs.keySet()) {
        if (msgDestUser.getId().equals(destUser.getId())) {
            List<Message> oldMessageList = srcUserMsgs.get(msgDestUser);
            for (Message message : oldMessageList) {
                oldMessages.put(message.getSendTime().getTime(), message);
            }
        }
    }
    for (User msgSrcUser : dstUserMsg.keySet()) {
        if (msgSrcUser.getId().equals(sourceUser.getId())) {
            List<Message> oldMessageList = dstUserMsg.get(msgSrcUser);
            for (Message message : oldMessageList) {
                oldMessages.put(message.getSendTime().getTime(), message);
            }
        }
    }

%>
<!DOCTYPE html>
<script>
    var sender = "<%=(sourceUser.getFirstName() + " " + sourceUser.getSurname())%>";
    var receiver = "<%=(destUser.getFirstName() + " " + destUser.getSurname())%>";
    function sendMessage() {
        
        var message = $("#msgText").val();
        var url = "./ccService";
        var data = {msg: message, action: "sendMessage", srcId:<%=(sourceUser.getId())%>, destId:<%=(destUser.getId())%>};
        try {
            $.post(url, data, function (result) {
                if (result.status) {
                    $("#msgText").val("");
                    $("#messages").append('<div style="width: 100%;height: 30px;background-color: #0085c8;color: white">('+result.time+')<b>' + sender + '</b> : ' + message + '</div><hr>');
                } else {
                    $("#messages").append("");
                }
                
            }, "json");
        } catch (err) {
            alert(err)
        }
    }
</script>
<div>
    <table>
        <tr>
            <td>
                <h2>Önceki Konuşmalarınız:</h2>
        <div id="messages" style="height: 400px;overflow: scroll">
            <%
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                for (Long time : oldMessages.keySet()) {
                    Message message = oldMessages.get(time);
            %>
            <div style="width: 100%;height: 30px;background-color: #0085c8;color: white">(<%=(simpleDateFormat.format(message.getSendTime()))%>)<b>
                    <%=(message.getSourceUser().getFirstName() + " " + message.getSourceUser().getSurname())%>
                </b> : <%=(message.getMessageText())%></div><hr>
                <%
                    }
                %>
        </div>
        </td>
        </tr>
        <tr>
            <td>
                <br><hr>
                Mesajınız : <input type="text" id="msgText" name="msgText" />
        <input type="button" onClick="sendMessage()" value="Gonder"/>
        </td>
        </tr>
    </table>
</div>

