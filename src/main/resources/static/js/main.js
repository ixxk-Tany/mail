main();
function main() {
    (function () {
        'use strict';
        $(".age").html(jsGetYears("1990-05-07"));
        $(".project_experience").html(jsGetYears("2016-04-30"));
        // jQuery to collapse the navbar on scroll
        $(window).scroll(function () {
            if ($(".navbar").offset().top > 50) {
                $(".navbar-fixed-top").addClass("top-nav-collapse");
            } else {
                $(".navbar-fixed-top").removeClass("top-nav-collapse");
            }
        });

        // Testimonial Slider
        $('a.page-scroll').click(function () {
            if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                if (target.length) {
                    $('html,body').animate({
                        scrollTop: target.offset().top - 40
                    }, 900);
                    return false;
                }
            }
        });

        /*====================================
        Show Menu on Book
        ======================================*/
        $(window).bind('scroll', function () {
            var navHeight = $(window).height() - 100;
            if ($(window).scrollTop() > navHeight) {
                $('.navbar-default').addClass('on');
            } else {
                $('.navbar-default').removeClass('on');
            }
        });

        $('body').scrollspy({
            target: '.navbar-default',
            offset: 80
        })

        $(document).ready(function () {
            $("#testimonial").owlCarousel({
                navigation: false, // Show next and prev buttons
                slideSpeed: 300,
                paginationSpeed: 400,
                singleItem: true
            });

        });

        /*====================================
      Portfolio Isotope Filter
      ======================================*/
        $(window).load(function () {
            var $container = $('.portfolio-items');
            $container.isotope({
                filter: '*',
                animationOptions: {
                    duration: 750,
                    easing: 'linear',
                    queue: false
                }
            });
            $('.cat a').click(function () {
                $('.cat .active').removeClass('active');
                $(this).addClass('active');
                var selector = $(this).attr('data-filter');
                $container.isotope({
                    filter: selector,
                    animationOptions: {
                        duration: 750,
                        easing: 'linear',
                        queue: false
                    }
                });
                return false;
            });

        });

        // ****************************************************************
// counterUp
// ****************************************************************

        $(document).ready(function ($) {
            if ($("span.count").length > 0) {
                $('span.count').counterUp({
                    delay: 10, // the delay time in ms
                    time: 1000 // the speed time in ms
                });
            }
        });

        /*====================================
      Pretty Photo
      ======================================*/
        $("a[rel^='prettyPhoto']").prettyPhoto({
            social_tools: false
        });

    }());


}

var returnCityIp = "";
var returnCityName = "";
var btnClick = false;

function init() {
    try {
        returnCityIp = returnCitySN["cip"];
        returnCityName = returnCitySN["cname"];
        console.log(returnCityIp);
        console.log(returnCityName)
        var data = {ip: returnCityIp, city: returnCityName};
        $.ajax({
            url: "//api.ixxxk.com/index/hello",
            type: "post",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            success: function (res) {

            }
        })
    } catch (e) {
        console.log(e);
    }

}

function sendEmail() {
    var name = $("#name").val();
    var email = $("#email").val();
    var message = $("#message").val();
    var myreg = /^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (name == "") {
        alert('提示\n\n请填写您的姓名!如: Tany');
        return;
    }
    if (!myreg.test(email)) {
        alert('提示\n\n请输入有效的E_mail！如: admin@ixxxk.com');
        myreg.focus();
        return false;
    }
    if (message == "") {
        alert('提示\n\n请说点儿什么吧!如: 你好啊!');
        return;
    }
    var reqData = {
        name: name,
        email: email,
        text: message,
        ip: returnCityIp,
        city: returnCityName
    }
    alert("发送成功");
    if (btnClick) {
        alert("请勿重复提交！！");
        return;
    }
    $.ajax({
        url: "//api.ixxxk.com/sys/mail/send",
        type: "post",
        data: JSON.stringify(reqData),
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        before: function () {
            btnClick = true;
        },
        success: function (res) {
            $("#name").val("");
            $("#email").val("");
            $("#message").val("");
            btnClick = false;
        }
    })
}

function jsGetYears(strBirthday) {
    var strBirthdayArr = strBirthday.split("-");
    var d = new Date();
    var yearDiff = d.getFullYear() - strBirthdayArr[0];
    var monthDiff = d.getMonth() + 1 - strBirthdayArr[1];
    var dayDiff = d.getDate() - strBirthdayArr[2];
    var yearResult = monthDiff < 0 || (monthDiff === 0 && dayDiff < 0) ? yearDiff - 1 : yearDiff; //判断有没有到生日,没到就减1
    return yearResult < 0 ? 0 : yearResult;
}