<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ attribute name="active" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="action" required="false" rtexprvalue="true" type="java.lang.String" %>


<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container">
        <div class="container-fluid">
            <div class="audio-player">
                <h1 id="p_title" class="p_title">Some radio</h1>
                <div class="caption1">
                    <c:url value="/images/img/cover.png" var="href"/><link rel="stylesheet" href="${href}">
                    <img id="p_img" src="${href}" alt="">
                </div>
                <audio id="audio-player" src="http://webcast.apexradio.ru:8000/gzsz" type="audio/mp3" controls="controls"></audio>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            $('#audio-player').mediaelementplayer({
                alwaysShowControls: true,
                features: ['playpause','volume'],
                audioVolume: 'horizontal',
                audioWidth: 400,
                audioHeight: 70
            });
        });
    </script>

</nav>
