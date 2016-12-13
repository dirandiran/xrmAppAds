

<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                    url: '/json/ajaxAll',
                    contentType: "application/json; charset=utf-8",
                    data: jsonString,
                    async: false,
                    cache: false,
                    processData:false,
                    success: function (result) {

                        var htmlTable = $('#result').val();
                        htmlTable = htmlTable + '<br><fieldset><legend align="center">All ads</legend><table>';
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
                var text = $('#text').val();
                var jsonString = JSON.stringify({
                    text : text
                });
                $.ajax({
                    type: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    url: '/json/ajaxAd',
                    contentType: "application/json; charset=utf-8",
                    data: jsonString,
                    async: false,
                    cache: false,
                    processData: false,
                    success: function (result) {
                        var res = $('#result').val();
                        res = result;
                        $('#result').html(res);
                    }
                });
            });
        });
    </script>
</head>
<body>
    <fieldset>
        <legend align="center">Ad search</legend>
        <form method="post" action="\RestContr">
            <br>
            <input type="text" id="text" /> <br>

        <button type="button" id="findAd" name="findAd">Search in DB </button> <br>
        </form>
    </fieldset>

        <button type="button" name="showAll" id="showAll"> show All Ads</button> <br>

        <span id="result">

        </span>


</body>
</html>
