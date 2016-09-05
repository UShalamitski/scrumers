//+function($) {
//    $(document).on('click.bs.modal.data-api', '[data-toggle="modal"]',
//            function(e) {
//                var $this = $(this)
//                $("#param4").attr("value", $this.attr("value"));
//                $("#param1").attr("value", $this.attr("value"));
//                $("#param2").attr("value", $this.attr("value"));
//                $("#param3").attr("value", $this.attr("value"));
//                $("#param11").attr("value", $this.attr("value2"));
//                $("#param12").attr("value", $this.attr("value3"));
//            })
//}(jQuery);

function teamModal(e) {
    e.preventDefault();
    var el = $(this);
    if (el.hasClass('link1')) {
        $("#t_id").attr("value", el.attr("t_id"));
        $("#t_name").val(el.attr("t_name"));
    } else {
        $("#t_name").val("");
        $("#t_id").val("");
    }
}

function orgModal(e) {
    e.preventDefault();
    var el = $(this);
    if (el.hasClass('link')) {
        $("#hidden_param").attr("value", el.attr("id"));
        $("#o_name").val(el.attr("o_name"));
    } else {
        $("#o_name").val("");
        $("#hidden_param").val("");
    }
}

function iterModal(e) {
    e.preventDefault();
    var el = $(this);
    if (el.hasClass("link") || el.hasClass("link1")) {
        $("#hidden_param").attr("value", el.attr("id"));
        $("#i_name").val(el.attr("i_name"));
        $("#i_descr").val(el.attr("i_descr"));
        $("#datepicker1").val(el.attr("i_sdate"));
        $("#datepicker2").val(el.attr("i_edate"));
    } else {
        $("#i_name").val("");
        $("#i_descr").val("");
        $("#datepicker1").val("");
        $("#datepicker2").val("");
        $("#hidden_param").val("");
    }
}

function storyModal(e) {
    e.preventDefault();
    var el = $(this);

    if (el.hasClass("link")) {
        $("#s_id").attr("value", el.attr("s_id"));
        $("#s_storyId").attr("value", el.attr("storyId"));
        $("#s_status").attr("value", el.attr("statusId"));
        $("#s_name").val(el.attr("s_name"));
        $("#s_descr").val(el.attr("s_descr"));
        $("#s_points").val(el.attr("s_points"));
        $("#s_estimate").val(el.attr("s_estimate"));
        $("#s_track").val(el.attr("s_track"));
        $("#s_demo").val(el.attr("s_demo"));
        $("#select").val(el.attr("s_assignee"));
    } else {
        $("#s_status").attr("value", el.attr("status"));
        $("#s_name").val("");
        $("#s_descr").val("");
        $("#s_points").val("");
        $("#s_estimate").val("0");
        $("#s_track").val("");
        $("#s_demo").val("");
        $("#select").val("0");
        $("#s_id").val("");
    }
}

function taskModal(e) {
    e.preventDefault();
    var el = $(this);
    if (el.hasClass("link")) {
        $("#hidden_param").attr("value", el.attr("id"));
        $("#hidden_param2").attr("value", el.attr("taskId"));
        $("#hidden_param3").attr("value", el.attr("statusId"));
        $("#hidden_param4").attr("value", el.attr("story_id"));
        $("#hidden_param5").attr("value", el.attr("storyId"));
        $("#t_summary").val(el.attr("t_summary"));
        $("#t_estimatePre").val(el.attr("t_estimatePre"));
        $("#t_estimateReal").val(el.attr("t_estimateReal"));
        $("#t_assignee").val(el.attr("t_assignee"));
    } else {
        $("#hidden_param3").attr("value", el.attr("status"));
        $("#hidden_param4").attr("value", el.attr("story_id"));
        $("#hidden_param5").attr("value", el.attr("storyId"));
        $("#t_summary").val("");
        $("#t_estimatePre").val("");
        $("#t_estimateReal").val("");
        $("#t_assignee").val("");
    }
}

function productModal(e) {
    e.preventDefault();
    var el = $(this);
    if (el.hasClass('link')) {
        $("#hidden_param").attr("value", el.attr("id"));
        $("#p_name").val(el.attr("p_name"));
        $("#p_descr").val(el.attr("p_descr"));
    } else {
        $("#p_name").val("");
        $("#p_descr").val("");
        $("#hidden_param").val("");
    }
}

function taskBoardCalculatingsForFooter1() {
    var stories1 = $("#sortable1").children().length;
    $("#stat_footer1_item1").text(stories1 + " tasks");
    
    var estimate1 = 0;
    $("#sortable1 li").each(function(index, element) {
        var temp = $(element).attr("estimate");
        estimate1 += temp * 1;
    });
    $("#stat_footer1_item2").text(estimate1 + " hours");
}

function taskBoardCalculatingsForFooter2() {
    var stories2 = $("#sortable2").children().length;
    $("#stat_footer2_item1").text(stories2 + " tasks");
    
    var estimate2 = 0;
    $("#sortable2 li").each(function(index, element) {
        var temp = $(element).attr("estimate");
        estimate2 += temp * 1;
    });
    $("#stat_footer2_item2").text(estimate2 + " hours");
}

function taskBoardCalculatingsForFooter3() {
    var stories3 = $("#sortable3").children().length;
    $("#stat_footer3_item1").text(stories3 + " tasks");
    
    var estimate3 = 0;
    $("#sortable3 li").each(function(index, element) {
        var temp = $(element).attr("estimate");
        estimate3 += temp * 1;
    });
    $("#stat_footer3_item2").text(estimate3 + " hours");
}

function scrumBoardCalculatingsForFooter1() {
    var stories1 = $("#sortable1").children().length;
    $("#stat_footer1_item1").text(stories1 + " stories");

    var estimate1 = 0;
    $("#sortable1 li").each(function(index, element) {
        var temp = $(element).attr("estimate");
        estimate1 += temp * 1;
    });
    $("#stat_footer1_item2").text(estimate1 + " hours");

//    var points1 = 0;
//    $("#sortable1 li").each(function(index, element) {
//        var temp = $(element).attr("points");
//        temp = temp.trim();
//        if (temp == 'XS' || temp == 'xs')
//            points1 += 1;
//        else if (temp == 'S' || temp == 's')
//            points1 += 5;
//        else if (temp == 'M' || temp == 'm')
//            points1 += 10;
//        else if (temp == 'L' || temp == 'l')
//            points1 += 15;
//        else if (temp == 'XL' || temp == 'xl')
//            points1 += 25;
//    });
//    $("#stat_footer1_item2").text(points1 + " points");
}

function scrumBoardCalculatingsForFooter2() {
    var stories2 = $("#sortable2").children().length;
    $("#stat_footer2_item1").text(stories2 + " stories");

    var estimate2 = 0;
    $("#sortable2 li").each(function(index, element) {
        var temp = $(element).attr("estimate");
        estimate2 += temp * 1;
    });
    $("#stat_footer2_item2").text(estimate2 + " hours");
}

function scrumBoardCalculatingsForFooter3() {
    var stories3 = $("#sortable3").children().length;
    $("#stat_footer3_item1").text(stories3 + " stories");

    var estimate3 = 0;
    $("#sortable3 li").each(function(index, element) {
        var temp = $(element).attr("estimate");
        estimate3 += temp * 1;
    });
    $("#stat_footer3_item2").text(estimate3 + " hours");
}

function scrumBoardCalculatingsForFooter4() {
    var stories4 = $("#sortable4").children().length;
    $("#stat_footer4_item1").text(stories4 + " stories");

    var estimate4 = 0;
    $("#sortable4 li").each(function(index, element) {
        var temp = $(element).attr("estimate");
        estimate4 += temp * 1;
    });
    $("#stat_footer4_item2").text(estimate4 + " hours"); 

}


function func1() {

    scrumBoardCalculatingsForFooter1();
    scrumBoardCalculatingsForFooter2();
    scrumBoardCalculatingsForFooter3();
    scrumBoardCalculatingsForFooter4();

    $("#sortable1").sortable({
        scroll : false,
        connectWith : ".connectedSortable4",
        receive : function(event, ui) {
            var el = ui.item.context.id;
            $('#' + el).toggleClass('cl1');
            if ($('#' + el).hasClass('cl2'))
                $('#' + el).removeClass('cl2');
            if ($('#' + el).hasClass('cl3'))
                $('#' + el).removeClass('cl3');
            if ($('#' + el).hasClass('cl4'))
                $('#' + el).removeClass('cl4');
            el = el.replace(/item_/g, "");
            $('#' + el).attr("statusId", '1');

            $.ajax({
                method : 'POST',
                url : "sb_it_recieve_1.html?sid=" + el,
                async : false,
                timeout : 500,
                error : function() {
                    // alert("Error!");
                }
            });
        },
        update : function(event, ui) {
            scrumBoardCalculatingsForFooter1();
            var ids = $("#sortable1").sortable("serialize");
            $.ajax({
                method : 'POST',
                url : "board1_upd.html?" + ids
            });
        }
    }).disableSelection();

    $("#sortable2").sortable({
        scroll : false,
        connectWith : ".connectedSortable4",
        receive : function(event, ui) {
            var el = ui.item.context.id;
            $('#' + el).toggleClass('cl2');
            if ($('#' + el).hasClass('cl1'))
                $('#' + el).removeClass('cl1');
            if ($('#' + el).hasClass('cl3'))
                $('#' + el).removeClass('cl3');
            if ($('#' + el).hasClass('cl4'))
                $('#' + el).removeClass('cl4');

            el = el.replace(/item_/g, "");
            $('#' + el).attr("statusId", '2');

            $.ajax({
                method : 'POST',
                url : "sb_it_recieve_2.html?sid=" + el,
                async : false,
                timeout : 500,
                error : function() {
                    // alert("Error!");
                }
            });
        },
        update : function(event, ui) {
            scrumBoardCalculatingsForFooter2();
            var ids = $("#sortable2").sortable("serialize");
            $.ajax({
                method : 'POST',
                url : "board2_upd.html?" + ids
            });
        }
    }).disableSelection();

    $("#sortable3").sortable({
        scroll : false,
        connectWith : ".connectedSortable4",
        receive : function(event, ui) {
            var el = ui.item.context.id;
            $('#' + el).toggleClass('cl3');
            if ($('#' + el).hasClass('cl2'))
                $('#' + el).removeClass('cl2');
            if ($('#' + el).hasClass('cl1'))
                $('#' + el).removeClass('cl1');
            if ($('#' + el).hasClass('cl4'))
                $('#' + el).removeClass('cl4');
            el = el.replace(/item_/g, "");
            $('#' + el).attr("statusId", '3');

            $.ajax({
                method : 'POST',
                url : "sb_it_recieve_3.html?sid=" + el,
                async : false,
                timeout : 500,
                error : function() {
                    // alert("Error!");
                }
            });
        },
        update : function(event, ui) {
            var ids = $("#sortable3").sortable("serialize");
            scrumBoardCalculatingsForFooter3();
            $.ajax({
                method : 'POST',
                url : "board3_upd.html?" + ids
            });
        },
    }).disableSelection();

    $("#sortable4").sortable({
        scroll : false,
        connectWith : ".connectedSortable4",
        receive : function(event, ui) {
            var el = ui.item.context.id;
            $('#' + el).toggleClass('cl4');
            if ($('#' + el).hasClass('cl2'))
                $('#' + el).removeClass('cl2');
            if ($('#' + el).hasClass('cl1'))
                $('#' + el).removeClass('cl1');
            if ($('#' + el).hasClass('cl3'))
                $('#' + el).removeClass('cl3');
            el = el.replace(/item_/g, "");
            $('#' + el).attr("statusId", '4');

            $.ajax({
                method : 'POST',
                url : "sb_it_recieve_4.html?sid=" + el,
                async : false,
                timeout : 500,
                error : function() {
                    // alert("Error!");
                }
            });
        },
        update : function(event, ui) {
            var ids = $("#sortable4").sortable("serialize");
            scrumBoardCalculatingsForFooter4();
            $.ajax({
                method : 'POST',
                url : "board4_upd.html?" + ids
            });
        },
    }).disableSelection();

};

function func2() {

    $("#sortable1").sortable({
        scroll : false,
        update : function(event, ui) {
            var ids = $("#sortable1").sortable("serialize");

            $.ajax({
                method : 'POST',
                url : "backlog_upd.html?" + ids
            });
        },
        containment : $(".move")
    }).disableSelection();
}

function func2a() {

    $("#sortable1").sortable({

        scroll : false,
        update : function(event, ui) {
            var ids = $("#sortable1").sortable("serialize");

            $.ajax({
                method : 'POST',
                url : "storyList_upd.html?" + ids
            });
        },
        containment : $(".move")
    }).disableSelection();
}

function func3() {

    var stories1 = $("#sortable1").children().length;
    var stories2 = $("#sortable2").children().length;
    $("#stat_footer1_item1").text(stories1 + " stories");
    $("#stat_footer2_item1").text(stories2 + " stories");

    var hours2 = 0;
    var hours1 = 0;
    var points1 = 0;
    var points2 = 0;

    $("#sortable1 li").each(function(index, element) {
        hours1 += parseInt($(element).attr("estimate"));
    });

    $("#sortable2 li").each(function(index, element) {
        hours2 += parseInt($(element).attr("estimate"));
    });

    $("#stat_footer1_item3").text(hours1 + " hours");
    $("#stat_footer2_item3").text(hours2 + " hours");


    $("#sortable1").sortable({
        scroll : false,
        connectWith : ".connectedSortable2",
        appendTo : ".planning-holder",
        opacity : .6,
        helper : "clone",
        delay : 150,
        receive : function(event, ui) {
            var el = ui.item.context.id;
            el = el.replace(/item_/g, "");

            $.ajax({
                method : 'POST',
                url : "storyList_backlog_recieve.html?sid=" + el,
                async : false,
                timeout : 500
            });
        },
        update : function(event, ui) {
            var ids = $("#sortable1").sortable("serialize");

            var stories1 = $("#sortable1").children().length;
            $("#stat_footer1_item1").text(stories1 + " stories");

            var hours1 = 0;
            var points1 = 0;

            $("#sortable1 li").each(function(index, element) {
                hours1 += parseInt($(element).attr("estimate"));
            });

            $("#stat_footer1_item3").text(hours1 + " hours");

            $.ajax({
                method : 'POST',
                url : "storyList_board_upd.html?" + ids
            });
        },
    }).disableSelection();

    $("#sortable2").sortable({
        scroll : false,
        connectWith : ".connectedSortable2",
        appendTo : ".planning-holder",
        helper : "clone",
        delay : 150,
        opacity : .6,
        receive : function(event, ui) {
            var el = ui.item.context.id;
            el = el.replace(/item_/g, "");

            $.ajax({
                method : 'POST',
                url : "storyList_recieve.html?sid=" + el,
                async : false,
                timeout : 500
            });
        },
        update : function(event, ui) {
            var ids = $("#sortable2").sortable("serialize");

            var stories2 = $("#sortable2").children().length;
            $("#stat_footer2_item1").text(stories2 + " stories");

            var hours2 = 0;
            var points2 = 0;

            $("#sortable2 li").each(function(index, element) {
                hours2 += parseInt($(element).attr("estimate"));
            });

            $("#stat_footer2_item3").text(hours2 + " hours");

            $.ajax({
                method : 'POST',
                url : "storyList_upd.html?" + ids
            });
        }
    }).disableSelection();

}

function func4() {

    taskBoardCalculatingsForFooter1();
    taskBoardCalculatingsForFooter2();
    taskBoardCalculatingsForFooter3();

    $("#sortable1").sortable({
        scroll : false,
        connectWith : ".connectedSortable3",
        receive : function(event, ui) {
            var el = ui.item.context.id;
            $('#' + el).toggleClass('cl1');
            if ($('#' + el).hasClass('cl2'))
                $('#' + el).removeClass('cl2');
            if ($('#' + el).hasClass('cl4'))
                $('#' + el).removeClass('cl4');
            el = el.replace(/item_/g, "");
            $('#' + el).attr("statusId", '1');

            $.ajax({
                method : 'POST',
                url : "task_board_recieve_1.html?tid=" + el,
                async : false,
                timeout : 500,
                error : function() {
                    // alert("Error!");
                }
            });
        },
        update : function(event, ui) {
            taskBoardCalculatingsForFooter1();
            var ids = $("#sortable1").sortable("serialize");
            var sid = $("#" + ui.item.context.id).attr("sid");
            $.ajax({
                method : 'POST',
                url : "task_board_upd1.html?sid=" + sid + "&" + ids
            });
        }
    }).disableSelection();

    $("#sortable2").sortable({
        scroll : false,
        connectWith : ".connectedSortable3",
        receive : function(event, ui) {
            var el = ui.item.context.id;
            $('#' + el).toggleClass('cl2');
            if ($('#' + el).hasClass('cl1'))
                $('#' + el).removeClass('cl1');
            if ($('#' + el).hasClass('cl4'))
                $('#' + el).removeClass('cl4');

            el = el.replace(/item_/g, "");
            $('#' + el).attr("statusId", '2');

            $.ajax({
                method : 'POST',
                url : "task_board_recieve_2.html?tid=" + el,
                async : false,
                timeout : 500,
                error : function() {
                    // alert("Error!");
                }
            });
        },
        update : function(event, ui) {
            taskBoardCalculatingsForFooter2();
            var ids = $("#sortable2").sortable("serialize");
            var sid = $("#" + ui.item.context.id).attr("sid");
            $.ajax({
                method : 'POST',
                url : "task_board_upd2.html?sid=" + sid + "&" + ids
            });
        }
    }).disableSelection();

    $("#sortable3").sortable({
        scroll : false,
        connectWith : ".connectedSortable3",
        receive : function(event, ui) {
            var el = ui.item.context.id;
            $('#' + el).toggleClass('cl4');
            if ($('#' + el).hasClass('cl2'))
                $('#' + el).removeClass('cl2');
            if ($('#' + el).hasClass('cl1'))
                $('#' + el).removeClass('cl1');
            el = el.replace(/item_/g, "");
            $('#' + el).attr("statusId", '3');

            $.ajax({
                method : 'POST',
                url : "task_board_recieve_3.html?tid=" + el,
                async : false,
                timeout : 500,
                error : function() {
                    // alert("Error!");
                }
            });
        },
        update : function(event, ui) {
            taskBoardCalculatingsForFooter3();
            var ids = $("#sortable3").sortable("serialize");
            var sid = $("#" + ui.item.context.id).attr("sid");
            $.ajax({
                method : 'POST',
                url : "task_board_upd3.html?sid=" + sid + "&" + ids
            });
        },
    }).disableSelection();
};

// -------------------------------------------------------------
// --------------------------- plot ----------------------------
// -------------------------------------------------------------

function burndown() {
    // e.preventDefault();

    var ret = null;
    $.ajax({
        async : false,
        url : "plot.html",
        dataType : "json",
        success : function(data) {
            ret = data;
        }
    });

    var count = ret.length;
    var sampleData = [];
    var idealData = [];
    $.each(ret, function(key, value) {
        if (key < (count - 2)) {
            sampleData.push([ value.date, value.hours ]);
        } else {
            idealData.push([ value.date, value.hours ]);
        }
    });

    var plot1 = $.jqplot('chart1', [ idealData, sampleData ], {
        seriesColors : [ "#e74c3c", "#1ABC9C" ],
        animate : true,
        animateReplot : true,
        yaxis : 'hours',
        xaxis : 'days',

        title : {
//            text : 'Burndown diagram',
            show : true,
            fontFamily : 'Georgia, Serif',
            fontSize : '15pt'
        },

        cursor : {
            show : true,
            zoom : true,
            looseZoom : true,
            showTooltip : false
        },

        highlighter : {
            show : true,
            showLabel : true,
            tooltipAxes : 'y',
            sizeAdjust : 7.5,
            tooltipLocation : 'ne'
        },

        // dataRenderer: sineRenderer,
        grid : {
            background : '#ddd',
            drawBorder : true,
            shadow : false,
            gridLineColor : '#999',
            gridLineWidth : 1
        },
        rendererOptions : {
            animation : {
                speed : 100
            }
        },
        /*
         * fillBetween: { series1: 0, series2: 1, color: "#ccc", baseSeries: 0,
         * fill: true },
         */
        axes : {
            xaxis : {
                // label: 'Days',
                labelRenderer : $.jqplot.CanvasAxisLabelRenderer,
                labelOptions : {
                    fontFamily : 'Georgia, Serif',
                    fontSize : '12pt'
                },
                renderer : $.jqplot.DateAxisRenderer,
                tickRenderer : $.jqplot.CanvasAxisTickRenderer,
                tickOptions : {
                    formatString : "%b %e",
                    angle : -45,
                    textColor : '#000'
                },
                // min: "2011-08-01",
                // max: "2011-09-30",
                tickInterval : "1 day",
                rendererOptions : {
                    tickInset : 0.25
                },
                // tickInterval: 1,
                drawMajorGridlines : true,
                drawMinorGridlines : false,
            // drawMajorTickMarks: true,
            // drawMinorTickMarks: false,
            },
            yaxis : {
                label : 'Hours',
                labelRenderer : $.jqplot.CanvasAxisLabelRenderer,
                labelOptions : {
                    fontFamily : 'Georgia, Serif',
                    fontSize : '12pt'
                },
                drawMajorGridlines : true,
                drawMinorGridlines : false,
                min : 0,
                tickOptions : {
                    formatString : "%d"
                },
                rendererOptions : {
                // tickInset : 0.25,
                // forceTickAt0 : true
                }
            },
        },
        seriesDefaults : {
            rendererOptions : {
                // smooth: true,
                animation : {
                    show : true
                }
            },
            showMarker : true
        },
        series : [ {
            // fill: true,
            label : 'Idial burndown'
        }, {
            label : 'Remaining effort'
        } ],
        legend : {
            show : true,
            placement : 'inside'
        },
    });
}



function prodDiagram() {

    var ret1 = null;
    var ret2 = null;
    
    $.ajax({
        async : false,
        url : "plot2.html",
        dataType : "json",
        success : function(data) {
            ret1 = data;
        }
    });

    $.ajax({
        async : false,
        url : "plot3.html",
        dataType : "json",
        success : function(data) {
            ret2 = data;
        }
    });

    var sampleData = [];
    var idealData = [];

    $.each(ret1, function(key, value) {
        idealData.push([ value.date, value.hours ]);
    });

    $.each(ret2, function(key, value) {
        sampleData.push([ value.date, value.hours ]);
    });

    var plot1 = $.jqplot('chart1', [ idealData, sampleData ], {
        seriesColors : [ "#e74c3c", "#1ABC9C" ],
        animate : true,
        animateReplot : true,
        yaxis : 'Stories',
        xaxis : 'days',

        title : {
//            text : 'Burndown diagram',
            show : true,
            fontFamily : 'Georgia, Serif',
            fontSize : '15pt'
        },

        cursor : {
            show : true,
            zoom : true,
            looseZoom : true,
            showTooltip : false
        },

        highlighter : {
            show : true,
            showLabel : true,
            tooltipAxes : 'y',
            sizeAdjust : 7.5,
            tooltipLocation : 'ne'
        },

        // dataRenderer: sineRenderer,
        grid : {
            background : '#ddd',
            drawBorder : true,
            shadow : false,
            gridLineColor : '#999',
            gridLineWidth : 1
        },
        rendererOptions : {
            animation : {
                speed : 100
            }
        },
        /*
         * fillBetween: { series1: 0, series2: 1, color: "#ccc", baseSeries: 0,
         * fill: true },
         */
        axes : {
            xaxis : {
                // label: 'Days',
                labelRenderer : $.jqplot.CanvasAxisLabelRenderer,
                labelOptions : {
                    fontFamily : 'Georgia, Serif',
                    fontSize : '12pt'
                },
                renderer : $.jqplot.DateAxisRenderer,
                tickRenderer : $.jqplot.CanvasAxisTickRenderer,
                tickOptions : {
                    formatString : "%b %e",
                    angle : -45,
                    textColor : '#000'
                },
                // min: "2011-08-01",
                // max: "2011-09-30",
                tickInterval : "1 day",
                rendererOptions : {
                    tickInset : 0.25
                },
                // tickInterval: 1,
                drawMajorGridlines : true,
                drawMinorGridlines : false,
            // drawMajorTickMarks: true,
            // drawMinorTickMarks: false,
            },
            yaxis : {
                label : 'Hours',
                labelRenderer : $.jqplot.CanvasAxisLabelRenderer,
                labelOptions : {
                    fontFamily : 'Georgia, Serif',
                    fontSize : '12pt'
                },
                drawMajorGridlines : true,
                drawMinorGridlines : false,
                min : 0,
                tickOptions : {
                    formatString : "%d"
                },
                rendererOptions : {
                // tickInset : 0.25,
                // forceTickAt0 : true
                }
            },
        },
        seriesDefaults : {
            rendererOptions : {
                // smooth: true,
                animation : {
                    show : true
                }
            },
            showMarker : true
        },
        series : [ {
            // fill: true,
            label : 'All stories'
        }, {
            label : 'Done stories'
        } ],
        legend : {
            show : true,
            placement : 'inside'
        },
    });
}

