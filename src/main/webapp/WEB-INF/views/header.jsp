<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ge.economy.law.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean isAdmin = ((Integer) session.getAttribute("typeId") != null && (Integer) session.getAttribute("typeId") == UserDTO.USER_ADMIN);
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${pageContext.request.contextPath}/"/>
    <title>LAW</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/font-awesome.css">
    <link rel="stylesheet" href="resources/css/AdminLTE.css">
    <link rel="stylesheet" href="resources/css/dataTables.bootstrap.css">
    <link rel="stylesheet" href="resources/css/skin-blue-light.css">
    <link rel="stylesheet" href="resources/css/global.css">
    <link rel="stylesheet" href="resources/css/bootstrap-select.css">
    <link rel="stylesheet" href="resources/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="resources/css/ionicons.min.css">
    <link rel="shortcut icon" type="image/png" href="resources/imgs/favicon.png"/>

    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/jquery-ui.js"></script>
    <script src="resources/js/bootstrap-select.js"></script>
    <script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
    <script src="resources/js/bootstrap.js"></script>
    <script src="resources/js/bootstrap-datepicker.js"></script>
    <script src="resources/js/bootstrap-datepicker.ka.js"></script>
    <script src="resources/js/jquery.bootstrap-growl.min.js"></script>
    <script src="resources/js/adminlte.js"></script>
    <script src="resources/js/angular.js"></script>
    <script src="resources/js/global_util.js"></script>
    <script src="resources/js/growlMessages.js"></script>
    <script src="resources/js/requireds.js"></script>
    <script>
        $(document).ready(function () {
            $(".datepicker").datepicker({language: 'ka'});
            var url = window.location;
            $('.menuItem').filter(function () {
                return this.href.indexOf(url.pathname) > -1;
            }).addClass('active');
            if (url.pathname.indexOf("cases") > -1) {
                $('#selected_item').text("საქმე");
            } else if (url.pathname.indexOf("courts") > -1) {
                $('#selected_item').text("სასამართლოები");
            } else if (url.pathname.indexOf("instances") > -1) {
                $('#selected_item').text("სასამართლო ინსტანციები");
            } else if (url.pathname.indexOf("caseresults") > -1) {
                $('#selected_item').text("საქმის დამთავრების შედეგები");
            } else if (url.pathname.indexOf("litigsubjects") > -1) {
                $('#selected_item').text("დავის საგნები");
            } else if (url.pathname.indexOf("judges") > -1) {
                $('#selected_item').text("მოსამართლეები");
            } else if (url.pathname.indexOf("boards") > -1) {
                $('#selected_item').text("კოლეგია");
            } else if (url.pathname.indexOf("users") > -1) {
                $('#selected_item').text("მომხმარებლები");
            } else if (url.pathname.indexOf("statistics") > -1) {
                $('#selected_item').text("სტატისტიკა");
            }

        });

        var app = angular.module("app", []);
        app.controller("profileCtrl", function ($scope, $http, $location) {
            var absUrl = $location.absUrl();
            $scope.uri = "";
            if (absUrl.split("?")[1]) {
                $scope.uri = absUrl.split("?")[1].split("=")[1];
            }

            $scope.changePassword = function () {
                function resFunc(res) {
                    if (res.errorCode == 0) {
                        successMsg('ოპერაცია დასრულდა წარმატებით');
                        closeModal('dropdown');
                    } else {
                        errorMsg('ოპერაცია არ სრულდება გადაამოწმეთ ველების სისწორე');
                    }
                    $scope.newpass = {};
                }

                ajaxCall($http, "users/change-password?pass=" + $scope.newpass.password + "&newpass=" + $scope.newpass.newpassword, null, resFunc);
            };
        });
    </script>
</head>
<body ng-app="app" class="hold-transition skin-blue-light sidebar-mini">
<div class="wrapper">
    <header class="main-header">
        <a href="" class="logo">
            <span class="logo-lg"><b>LAW</b></span>
        </a>
        <nav class="navbar navbar-static-top">
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">მენიუს შეკეცვა</span>
            </a>

            <div class="navbar-custom-menu" ng-controller="profileCtrl">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-user"></i>
                            <span class="hidden-xs"><%= session.getAttribute("firstname") %> <%= session.getAttribute("lastname")%></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="user-header">
                                <p>
                                    <%= session.getAttribute("firstname") %> <%= session.getAttribute("lastname")%>
                                    <small><%= session.getAttribute("typeName") %>
                                    </small>
                                </p>
                            </li>
                            <li class="user-body text-center">
                                <div class=" form-group has-feedback">
                                    <input type="password" name="password" placeholder="ძველი პაროლი"
                                           ng-model="newpass.password"
                                           class="form-control">
                                    <span class="fa fa-key form-control-feedback"></span>
                                </div>
                                <div class="form-group has-feedback">
                                    <input type="password" name="password" placeholder="ახალი პაროლი"
                                           ng-model="newpass.newpassword"
                                           class="form-control">
                                    <span class="fa fa-key form-control-feedback"></span>
                                </div>
                            </li>
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="" ng-click="changePassword()" class="btn btn-default btn-flat">შეცვლა</a>
                                </div>
                                <div class="pull-right">
                                    <a href="logout" class="btn btn-default btn-flat">გამოსვლა</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li title="გამოსვლა">
                        <a href="logout"><i class="fa fa-sign-out"></i></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <div class="row" class="main-sidebar">
        <aside class="main-sidebar">
            <section class="sidebar">
                <ul class="sidebar-menu" data-widget="tree">
                    <li>
                        <a class="menuItem" href="cases">
                            <i class="fa fa-briefcase"></i>
                            <span>საქმე</span>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a class="menuItem" href="courts">
                            <i class="fa fa-bank"></i>
                            <span>სასამართლოები</span>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="instances">
                            <i class="fa fa-sitemap"></i>
                            <span>სასამართლო ინსტანცია</span>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="caseresults">
                            <i class="fa fa-folder-open"></i>
                            <span>საქმის დამთ. შედეგები</span>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="litigsubjects">
                            <i class="fa fa-balance-scale"></i>
                            <span>დავის საგანი</span>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="boards">
                            <i class="fa fa-share-alt"></i>
                            <span>კოლეგია</span>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="judges">
                            <i class="fa fa-graduation-cap"></i>
                            <span>მოსამართლეები</span>
                            </span>
                        </a>
                    </li>
                    <c:if test="<%= isAdmin %>">
                        <li>
                            <a href="users">
                                <i class="fa fa-users"></i>
                                <span>მომხმარებლები</span>
                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="statistics">
                                <i class="fa fa-bar-chart"></i>
                                <span>სტატისტიკა</span>
                                </span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </section>
        </aside>
    </div>

    <div class="content-wrapper" ng-controller="angController">
        <section class="content-header text-center">
            <h4 id="selected_item"></h4>
        </section>
        <section class="content">


