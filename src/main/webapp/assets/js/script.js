$(document).ready(function () {
    //loadAllData();
});

function cleardata() {
    var companytable = document.getElementById("content_table_body_1");
    var leaguetable = document.getElementById("content_table_body_2");
    var yeartable = document.getElementById("content_table_body_3");
    var matchtable = document.getElementById("content_table_body_4");
    for (var i = $('#content_table_body_1').children().length - 1; i >= 0; i--) {
        companytable.removeChild(companytable.childNodes[i]);
    }
    for (var i = $('#content_table_body_2').children().length - 1; i >= 0; i--) {
        leaguetable.removeChild(leaguetable.childNodes[i]);
    }
    for (var i = $('#content_table_body_3').children().length - 1; i >= 0; i--) {
        yeartable.removeChild(yeartable.childNodes[i]);
    }
    for (var i = $('#content_table_body_4').children().length - 1; i >= 0; i--) {
        matchtable.removeChild(matchtable.childNodes[i]);
    }
}


$("#match").bind('keyup foucus', function (event) {
    cleardata();
    var match = $(this).context.value;
    console.log(match);
    setTimeout(function () {
        if ($('#match').val() === "") {
            cleardata();
        } else {
            $.post('getlikematch', {match_name: match}, function (data) {
                $.each(data, function () {
                    var tr = $("<tr/>");
                    var td = $("<td/>");
                    td.html(this).appendTo(tr);
                    td.hover(function () {
                        $(this).css({background: '#cdcdcd'});
                    });
                    td.mouseleave(function (event) {
                        $(this).css({background: '#ffffff'});
                    });
                    td.click(function (event) {
                        $('#match').val($(this).text());
                        cleardata();
                        $('#league').focus();
                    });
                    $('#content_table_body_1').append(tr);
                });
            }, "json");
        }
    }, 500);
});

$("#league").bind('focus', function (event) {
    cleardata();
    var match_name = $('#match').val();
    if ($('#match').val() === "") {
        cleardata();
    } else {
        $.post('getleaguebymatch', {match: match_name}, function (data) {
            $.each(data, function () {
                var tr = $("<tr/>");
                var td = $("<td/>");
                td.html(this).appendTo(tr);
                td.hover(function () {
                    $(this).css({background: '#cdcdcd'});
                });
                td.mouseleave(function (event) {
                    $(this).css({background: '#ffffff'});
                });
                td.click(function (event) {
                    $('#league').val($(this).text());
                    cleardata();
                    $('#year').focus();
                });
                $('#content_table_body_2').append(tr);
            });
        }, "json");
    }
});

$('#year').bind('focus', function (event) {
    cleardata();
    var match_name = $('#match').val();
    var league_name = $('#league').val();
    if ($('#company').val() === "" && $('#league').val() === "") {
        cleardata();
    } else {
        $.post('getyearbyleague', {match: match_name, league: league_name}, function (data) {
            $.each(data, function () {
                var tr = $("<tr/>");
                var td = $("<td/>");
                td.html(this).appendTo(tr);
                td.hover(function () {
                    $(this).css({background: '#cdcdcd'});
                });
                td.mouseleave(function (event) {
                    $(this).css({background: '#ffffff'});
                });
                td.click(function (event) {
                    $('#year').val($(this).text());
                    cleardata();
                    $('#company').focus();
                });
                $('#content_table_body_3').append(tr);
            });
        }, "json");
    }
});

$('#company').bind('focus', function (event) {
    cleardata();
    var match_name = $('#match').val();
    var league_name = $('#league').val();
    var year = $('#year').val();
    var company_name = $(this).val();
    if ($('#match').val() === "" && $('#league').val() === "" && $('#year').val() === "") {
        cleardata();
    } else {
        $.post('getcompanybyother', {match: match_name, league: league_name, year: year}, function (data) {
            $.each(data, function () {
                var tr = $("<tr/>");
                var td = $("<td/>");
                td.html(this).appendTo(tr);
                td.hover(function () {
                    $(this).css({background: '#cdcdcd'});
                });
                td.mouseleave(function (event) {
                    $(this).css({background: '#ffffff'});
                });
                td.click(function (event) {
                    console.log($(this));
                    loaddata(match_name,league_name,year,company_name);
                    $('#company').val($(this).text());
                    cleardata();
                });
                $('#content_table_body_4').append(tr);
            });
        }, "json");
    }
});

function loaddata(match_name,league_name,year,company){
    var i = 0;
    $.post("getadata", {match:match_name,league:league_name,year:year,company:company}, function (data) {
        $.each(data, function () {
            var tr = $("<tr align='center'/>");
            i += 1;
            $("<td/>").html(i).appendTo(tr);
            $("<td/>").html(this.company).appendTo(tr);
            $("<td/>").html(this.league).appendTo(tr);
            $("<td/>").html(this.year).appendTo(tr);
            $("<td/>").html(this.match).appendTo(tr);
            $("#dataList").append(tr)
        })
    }, "json");
}

/*function loadAllData() {
    var i = 0;
    $.post("getalldata", null, function (data) {
        $.each(data, function () {
            var tr = $("<tr align='center'/>");
            i += 1;
            $("<td/>").html(i).appendTo(tr);
            $("<td/>").html(this.company).appendTo(tr);
            $("<td/>").html(this.league).appendTo(tr);
            $("<td/>").html(this.year).appendTo(tr);
            $("<td/>").html(this.match).appendTo(tr);
            $("#dataList").append(tr)
        })
    }, "json");
}*/