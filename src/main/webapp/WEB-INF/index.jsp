<!DOCTYPE html>
<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored ="false" %>

<fmt:requestEncoding value="utf-8"/>

<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <script   src="http://code.jquery.com/jquery-2.2.4.min.js"   integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="   crossorigin="anonymous"></script>
    <script type="text/javascript">
        $(document).ready(function (){
            $('#showAll').click(function () {
                var jsonString = JSON.stringify({

                });
                $.ajax({
                    type: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    url: '/index/ajaxAll',
                    contentType: "application/json; charset=utf-8",
                    data: jsonString,
                    async: false,
                    cache: false,
                    processData:false,
                    success: function (result) {

                        var htmlTable = $('#result').val();
                        htmlTable = htmlTable + '<br><fieldset><legend align="center">All ' + result.length + ' ads</legend><table>';
                        for(var i = 0; i < result.length; i++) {
                            htmlTable = htmlTable + '<tr><td>' + result[i].id + '</td><td>' + result[i].adText + '</td><td>' + result[i].price + '</td><td>' + result[i].contacts + '</td></tr>';
                        }
                        htmlTable = htmlTable + '</table></fieldset>';
                            $('#result').html(htmlTable);

                    }
                });
            });
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#findAd').click(function () {
                var adText = $('#adText').val();
                var jsonString = JSON.stringify({
                    adText : adText
                });
                $.ajax({
                    type: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    url: '/index/ajaxAd',
                    contentType: "application/json; charset=utf-8",
                    data: jsonString,
                    async: false,
                    cache: false,
                    processData: false,
                    success: function (result) {
                        var htmlTable = $('#result').val();
                        htmlTable = htmlTable + '<br><fieldset><legend align="center">Here ' + result.length +' ads with your search critery</legend><table>';
                        for(var i = 0; i < result.length; i++) {
                            htmlTable = htmlTable + '<tr><td>' + (i+1) + '</td><td>' + result[i].adText + '</td><td>' + result[i].price + '</td><td>' + result[i].contacts + '</td></tr>';
                        }
                        htmlTable = htmlTable + '</table></fieldset>';
                        $('#result').html(htmlTable);

                    }
                });
            });
        });
    </script>
</head>
<body>
    <fieldset>
        <legend align="center">Ad search</legend>
        <form action="/index/ajaxAd" method="post" accept-charset="utf-8">
        <input type="text" id="adText" value="альбом"/>
        <br>
        <button type="button" id="findAd" name="findAd">Search in DB </button> <br>
        </form>
    </fieldset>
        <button type="button" name="showAll" id="showAll"> show All Ads</button> <br>

        <span id="result">

        </span>


</body>
</html>
