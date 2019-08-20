<%@ page import="ge.mimino.travel.dto.UsersDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean ADMINISTRATOR = false;
    boolean COMUNICATION_MANAGER = false;
    boolean PRODUCT_MANAGER = false;
    boolean FINANCIAL_MANAGER = false;
    boolean RESERVATION_MANAGER = false;
    if ((Integer) session.getAttribute("typeId") != null) {
        switch ((Integer) session.getAttribute("typeId")) {
            case UsersDTO.ADMINISTRATOR:
                ADMINISTRATOR = true;
                break;
            case UsersDTO.COMUNICATION_MANAGER:
                COMUNICATION_MANAGER = true;
                break;
            case UsersDTO.PRODUCT_MANAGER:
                PRODUCT_MANAGER = true;
                break;
            case UsersDTO.FINANCIAL_MANAGER:
                FINANCIAL_MANAGER = true;
                break;
            case UsersDTO.RESERVATION_MANAGER:
                RESERVATION_MANAGER = true;
                break;
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${pageContext.request.contextPath}/"/>
    <title>Mimino Travel</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/font-awesome.css">
    <link rel="stylesheet" href="resources/css/AdminLTE.css">
    <link rel="stylesheet" href="resources/css/dataTables.bootstrap.css">
    <link rel="stylesheet" href="resources/css/skin-blue-light.css">
    <link rel="stylesheet" href="resources/css/global.css">
    <link rel="stylesheet" href="resources/css/bootstrap-select.css">
    <link rel="stylesheet" href="resources/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="resources/css/ionicons.min.css">
    <link rel="shortcut icon" type="image/png" href="resources/imgs/logo.png"/>

    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/jquery-ui.js"></script>
    <script src="resources/js/bootstrap-select.js"></script>
    <script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
    <script src="resources/js/moment.js"></script>
    <script src="resources/js/bootstrap.js"></script>
    <script src="resources/js/bootstrap-datepicker.js"></script>
    <script src="resources/js/bootstrap-datetimepicker.js"></script>
    <script src="resources/js/jquery.bootstrap-growl.min.js"></script>
    <script src="resources/js/adminlte.js"></script>
    <script src="resources/js/angular.js"></script>
    <script src="resources/js/global_util.js"></script>
    <script src="resources/js/growlMessages.js"></script>
    <script src="resources/js/requireds.js"></script>
    <script src="resources/js/checklist-model.js"></script>
    <script src="resources/js/ng-file-upload-shim.js"></script>
    <script src="resources/js/ng-file-upload.js"></script>

    <script>
        $(document).ready(function () {
            $(".datepicker").datepicker({language: 'ka'});
            var url = window.location;
            $('.menuItem').filter(function () {
                return this.href.indexOf(url.pathname) > -1;
            }).addClass('active');
            if (url.pathname.indexOf("requests") > -1) {
                $('#selected_item').text("Requests");
            } else if (url.pathname.indexOf("contacts") > -1) {
                $('#selected_item').text("Contacts Database");
            } else if (url.pathname.indexOf("hotels") > -1) {
                $('#selected_item').text("Hotels");
            } else if (url.pathname.indexOf("objects") > -1) {
                $('#selected_item').text("Sights");
            } else if (url.pathname.indexOf("transports") > -1) {
                $('#selected_item').text("Transpors");
            } else if (url.pathname.indexOf("places") > -1) {
                $('#selected_item').text("Places(Towns, Districts, Resorts, etc.)");
            } else if (url.pathname.indexOf("nonstandarts") > -1) {
                $('#selected_item').text("Non Standart Services(Delica, Horse, ...)");
            } else if (url.pathname.indexOf("restaurants") > -1) {
                $('#selected_item').text("Restaurants");
            } else if (url.pathname.indexOf("distances") > -1) {
                $('#selected_item').text("Distances");
            } else if (url.pathname.indexOf("guides") > -1) {
                $('#selected_item').text("Guides");
            }


            $('.srch').keypress(function (e) {
                var key = e.which;
                if (key == 13) {
                    $('#srchBtnId').click();
                    return false;
                }
            });

            $('.dateInput').datepicker({
                format: "dd/mm/yyyy",
                autoclose: true,
            }).on('changeDate', function (ev) {
            });

            $('input').attr('autocomplete', 'off');

            $(function () {
                $('[data-toggle="popover"]').popover()
            });
        });

        var app = angular.module("app", ["checklist-model", "ngFileUpload"]);
        app.controller("profileCtrl", function ($scope, $http, $location) {
            var absUrl = $location.absUrl();
            $scope.uri = "";
            if (absUrl.split("?")[1]) {
                $scope.uri = absUrl.split("?")[1].split("=")[1];
            }

            $scope.changePassword = function () {
                function resFunc(res) {
                    if (res.errorCode == 0) {
                        successMsg('Operation Successfull');
                        closeModal('dropdown');
                    } else {
                        errorMsg('Operation Failed');
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
            <span class="logo-lg"><b>Mimino Travel</b></span>
        </a>
        <nav class="navbar navbar-static-top">
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Collapse Menu</span>
            </a>

            <div class="navbar-custom-menu" ng-controller="profileCtrl">
                <ul class="nav navbar-nav">
                    <li>
                        <a>
                            <i class="fa fa-comments fa-lg"></i>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class="fa fa-bell fa-lg"></i>
                        </a>
                    </li>
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-user"></i>
                            <span class="hidden-xs"><%= session.getAttribute("userDesc") %></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="user-header">
                                <p>
                                    <%= session.getAttribute("userDesc") %>
                                    <small><%= session.getAttribute("typeName") %>
                                    </small>
                                </p>
                            </li>
                            <li class="user-body text-center">
                                <div class=" form-group has-feedback">
                                    <input type="password" name="password" placeholder="Current Password"
                                           ng-model="newpass.password"
                                           class="form-control">
                                    <span class="fa fa-key form-control-feedback"></span>
                                </div>
                                <div class="form-group has-feedback">
                                    <input type="password" name="password" placeholder="New Password"
                                           ng-model="newpass.newpassword"
                                           class="form-control">
                                    <span class="fa fa-key form-control-feedback"></span>
                                </div>
                            </li>
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="" ng-click="changePassword()" class="btn btn-default btn-flat">Save</a>
                                </div>
                                <div class="pull-right">
                                    <a href="logout" class="btn btn-default btn-flat">Log Out</a>
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
                <img src="resources/imgs/logo.png" style="width: 80%; margin-top: 10%; margin-left: 14%"/>
                <hr/>
                <ul class="sidebar-menu" data-widget="tree">
                    <c:if test="<%= ADMINISTRATOR || COMUNICATION_MANAGER %>">
                        <li>
                            <a class="menuItem" href="emails">
                                <i class="fa fa-envelope"></i>
                                <span>Emails</span>
                            </a>
                        </li>
                    </c:if>
                    <li>
                        <a class="menuItem" href="requests">
                            <i class="fa fa-briefcase"></i>
                            <span>Requests</span>
                        </a>
                    </li>
                    <c:if test="<%= !PRODUCT_MANAGER && !FINANCIAL_MANAGER %>">
                        <li>
                            <a class="menuItem" href="contacts">
                                <i class="fa fa-share-alt"></i>
                                <span>Contact Db</span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="<%= PRODUCT_MANAGER || ADMINISTRATOR %>">
                        <li>
                            <a class="menuItem" href="objects">
                                <i class="fa fa-university"></i>
                                <span>Sights</span>
                            </a>
                        </li>
                        <li>
                            <a class="menuItem" href="places">
                                <i class="fa fa-map-marker"></i>
                                <span>Places</span>
                            </a>
                        </li>
                        <li>
                            <a class="menuItem" href="distances">
                                <i class="fa fa-space-shuttle"></i>
                                <span>Distances</span>
                            </a>
                        </li>
                        <li>
                            <a class="menuItem" href="hotels">
                                <i class="fa fa-bed"></i>
                                <span>Hotels</span>
                            </a>
                        </li>
                        <li>
                            <a class="menuItem" href="restaurants">
                                <i class="fa fa-cutlery"></i>
                                <span>Restaurants</span>
                            </a>
                        </li>
                        <li>
                            <a class="menuItem" href="transports">
                                <i class="fa fa-car"></i>
                                <span>Transport</span>
                            </a>
                        </li>
                        <li>
                            <a class="menuItem" href="nonstandarts">
                                <i class="fa fa-server"></i>
                                <span>Non St. Serv.</span>
                            </a>
                        </li>
                        <li>
                            <a class="menuItem" href="guides">
                                <i class="fa fa-blind"></i>
                                <span>Guides</span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="<%= ADMINISTRATOR %>">
                        <li>
                            <a href="users">
                                <i class="fa fa-users"></i>
                                <span>Users</span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="<%= ADMINISTRATOR || FINANCIAL_MANAGER %>">
                        <li>
                            <a href="statistics">
                                <i class="fa fa-bar-chart"></i>
                                <span>Statistics</span>
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
            <div class="modal fade bs-example-modal-lg not-printable" id="loadingModal"
                 role="dialog" aria-labelledby="loadingModalLabel" aria-hidden="true">
                <div class="modal-dialog" style="height: 80%; width: 120px;">
                    <div class="loader" style="margin-top: 80%"></div>
                </div>
            </div>
