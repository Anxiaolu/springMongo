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
    $('.nav-tabs').empty();
    $('.tab-content').empty();
    var match_name = $('#match').val();
    var league_name = $('#league').val();
    var year = $('#year').val();
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
                    if ($('#company').val() === "") {
                        $('#company').val($(this).text());
                        cleardata();
                    } else {
                        $('#company').val($('#company').val() + ',' + $(this).text());
                        cleardata();
                    }
                    loaddata(match_name, league_name, year, $('#company').val());
                });
                $('#content_table_body_4').append(tr);
            });
        }, "json");
    }
});

function loaddata(match_name, league_name, year, company) {
    var i = 0;
    $.post("getdata", {match: match_name, league: league_name, year: year, company: company}, function (data) {
        $.each(data, function () {
            console.log(data);
            //因为css中active属性,分开单独定义
            if (i === 0) {
                var li0 = $("<li role=\"presentation\" class=\"active\"><a href=\"" + "#content" + i
                        + "\" aria-controls=\"" + "content" + i + "\" role=\"tab\" data-toggle=\"tab\">" + this.company + "</a></li>");
                $('.nav-tabs').append(li0);
                var div0 = $("<div role=\"tabpanel\" class=\"tab-pane active\" id=\"" + "content" + i + "\"><table class=\"table table-bordered table-striped\" id=\"dataList" + i + "\"><tr><th>Order</th><th>Win</th><th>Draw</th><th>Lose</th><th>ReturnRate</th><th>UpdateTime</th></tr></table></div>");
                $('.tab-content').append(div0);

                var Odd = this.odds;
                var n = 0;
                for (var s = 0; s < Odd.length; s++) {
                    n += 1;
                    var tr = $("<tr align='center'/>");
                    $("<td/>").html(n).appendTo(tr);
                    $("<td/>").html(Odd[s].win).appendTo(tr);
                    $("<td/>").html(Odd[s].draw).appendTo(tr);
                    $("<td/>").html(Odd[s].lose).appendTo(tr);
                    $("<td/>").html(Odd[s].returnrate).appendTo(tr);
                    $("<td/>").html(changedate(Odd[s].updatetime)).appendTo(tr);
                    $("#dataList0").append(tr);
                }

                i += 1;
            } else {
                var li = $("<li role=\"presentation\"><a href=\"" + "#content" + i + "\" aria-controls=\""
                        + "content" + i + "\" role=\"tab\" data-toggle=\"tab\">" + this.company + "</a></li>");
                $('.nav-tabs').append(li);
                var div = $("<div role=\"tabpanel\" class=\"tab-pane\" id=\"" + "content" + i + "\"><table class=\"table table-bordered table-striped\" id=\"dataList" + i + "\"><tr><th>Order</th><th>Win</th><th>Draw</th><th>Lose</th><th>ReturnRate</th><th>UpdateTime</th></tr></table></div>");
                $('.tab-content').append(div);

                var string = "dataList" + i;
                var dataList = document.getElementById(string);
                var $dataList = $(dataList);
                var Odd = this.odds;
                var n = 0;
                for (var s = 0; s < Odd.length; s++) {
                    n += 1;
                    var tr = $("<tr align='center'/>");
                    $("<td/>").html(n).appendTo(tr);
                    $("<td/>").html(Odd[s].win).appendTo(tr);
                    $("<td/>").html(Odd[s].draw).appendTo(tr);
                    $("<td/>").html(Odd[s].lose).appendTo(tr);
                    $("<td/>").html(Odd[s].returnrate).appendTo(tr);
                    $("<td/>").html(changedate(Odd[s].updatetime)).appendTo(tr);
                    $dataList.append(tr);
                }
                i += 1;
            }
        })
    }, "json");
}

function changedate(longdate) {
    var newtime = new Date(longdate);
    /*return newtime.getFullYear() + "/"+  newtime.getMonth() + "/" + newtime.getDay() + " " + 
     newtime.getHours() + ":"+ newtime.getMinutes() + ":" + newtime.getSeconds();*/
    return convertTime(longdate, "yyyy-MM-dd hh:mm:ss");
}


function convertTime(jsonTime, format) {
    var date = new Date(jsonTime);
    var formatDate = date.format(format);
    return formatDate;
}


Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };

    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }

    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }

    return format;
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