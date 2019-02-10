<%--
  Created by IntelliJ IDEA.
  User: ME
  Date: 10/23/2017
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<style>
    #myBtn {
        position: fixed;
        bottom: 40%;
        right: 0.8%;
        z-index: 99999;
        border: 1px solid #ebccd1;
        outline: none;
        background-color: #f5f5f5;
        cursor: pointer;
        padding: 15px;
        border-radius: 4px;
    }
</style>
<script>
    app.controller("angController", ['$scope', '$http', '$filter', '$location', '$window', 'Upload', '$timeout', function ($scope, $http, $filter, $location, $window, Upload, $timeout) {

        $scope.product = {sights: [], places: [], hotels: [], nonstandarts: []};
        $scope.daysList = ['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX', 'X'];
        $scope.HbMealCats = ['Lunch', 'Picnick Lunch', 'Lunch with degustation', 'Dinner', 'Dinner Folk Show', 'Gala Dinner', 'Dinner At hotel'];
        $scope.FbMealCats = ['Breakfast', 'Lunch', 'Picnick Lunch', 'Lunch with degustation', 'Dinner', 'Dinner Folk Show', 'Gala Dinner', 'Dinner At hotel'];
        $scope.dayIndex = 0;
        $scope.mealCategories = [];
        $scope.prod = {restaurants: [], hotels1: [], hotels2: [], hotels3: []};
        $scope.restPackages = [];
        $scope.tmpSights = [];
        $scope.combSights = [];
        $scope.stars = ['*', '**', '***', '****', '*****'];
        $scope.hotelStars1 = '';
        $scope.hotelStars2 = '';
        $scope.hotelStars3 = '';
        $scope.imageNames = [];
        $scope.transports = [];
        $scope.transportDaysList = [];
        $scope.currencyByService = [];

        function getMealCategories(res) {
            $scope.mealCategories = res.data;
        };

        ajaxCall($http, "misc/get-mealcategories", null, getMealCategories);

        function getCarrencies(res) {
            $scope.currencyByService = res.data;
        };

        ajaxCall($http, "requests/get-currencies", null, getCarrencies);

        $scope.loadRequest = function () {
            var absUrl = $location.absUrl();
            if (absUrl.split("?")[1]) {
                $scope.requestKey = absUrl.split("?")[1].split("=")[1];
            }

            function getRequestData(res) {
                $scope.request = res.data;
                $scope.getProductDetails();
            }

            ajaxCall($http, "requests/get-request-by-key?id=" + $scope.requestKey, null, getRequestData);
        };

        $scope.loadRequest();

        $scope.getProductDetails = function () {
            function getProdDet(res) {
                $scope.product = {
                    regions: [],
                    sights: [],
                    places: [],
                    hotels: [],
                    nonstandarts: [],
                    restaurants: []
                }

                console.log(res.data);
                $scope.transportDaysList = res.data.transportDays;
                $scope.transports = res.data.transports;
                $scope.product.regions = res.data.regionsList;
                $scope.product.places = res.data.placesList;
                $scope.product.sights = res.data.sights;
                $scope.product.hotels1 = $filter('filter')(res.data.hotels, {groupId: 1}, true);
                $scope.product.hotels2 = $filter('filter')(res.data.hotels, {groupId: 2}, true);
                $scope.product.hotels3 = $filter('filter')(res.data.hotels, {groupId: 3}, true);
                $scope.product.restaurants = res.data.restaurants;
                $scope.product.nonstandarts = res.data.nonstandartsList;
            };

            ajaxCall($http, "requests/get-product-details-for-finaince?requestId=" + $scope.request.id + "&day=" + ($scope.dayIndex + 1), null, getProdDet);
        };

        $scope.handleDayChange = function (h) {
            if (parseInt(h) > 0) {
                $scope.dayIndex += 1;

            } else {
                $scope.dayIndex = parseInt($scope.dayIndex - 1) == -1 ? 0 : ($scope.dayIndex - 1);
            }
            $scope.getProductDetails();
        };

        $scope.save = function () {

            function resFunc(res) {
                if (res.errorCode == 0) {
                    successMsg('Operation Successfull');
                } else {
                    errorMsg('Operation Failed');
                }
            }

            $scope.product.requestId = $scope.request.id;
            $scope.product.day = $scope.dayIndex + 1;

            $scope.product.restaurants = [];
            $scope.product.hotels = [];

            angular.forEach($scope.prod.restaurants, function (v, k) {
                var packages = v.packages != undefined ? v.packages.join(',') : '';
                var mealCats = v.meal + '-';
                if (v.mealCats != undefined && v.mealCats.length > 0) {
                    mealCats += (v.mealCats == undefined ? '' : (v.mealCats.join(',')));
                }
                $scope.product.restaurants.push({
                    restaurantId: v.restaurantId,
                    mealCategories: mealCats,
                    packages: packages
                });
            });
            angular.forEach($scope.prod.hotels1, function (v) {
                $scope.product.hotels.push({
                    hotelId: v,
                    groupId: 1
                });
            });
            angular.forEach($scope.prod.hotels2, function (v) {
                $scope.product.hotels.push({
                    hotelId: v,
                    groupId: 2
                });
            });
            angular.forEach($scope.prod.hotels3, function (v) {
                $scope.product.hotels.push({
                    hotelId: v,
                    groupId: 3
                });
            });

            $scope.product.sights = $filter('filter')($scope.combSights, '!null', true);

            console.log(angular.toJson($scope.product));

            ajaxCall($http, "requests/save-product", angular.toJson($scope.product), resFunc);
        };

        $scope.sendToReservation = function () {
            // $scope.save();
            if (confirm("Pleace confirm Sending to Reservation")) {
                alert("Will be Active Soon ;)");
                // $window.location.href = "/miminoTravel/financial?key=" + $scope.request.requestKey;
            }
        };

    }]);
</script>


<div class="row">
    <button id="myBtn">Day ({{daysList[dayIndex]}})</button>
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="col-md-2">
                            <a class="btn btn-app ">
                                <i class="fa fa-briefcase"></i> Request Details
                            </a>
                        </div>
                        <div class="col-md-10 text-right"><b>
                            Currency By National Bank - USD: {{currencyByService['USD']}} | EUR:
                            {{currencyByService['EUR']}}</b></div>

                        <table class="table table-striped">
                            <tr>
                                <th class="col-md-2">Tour Code</th>
                                <td>{{request.tourCode}}</td>

                                <th class="col-md-2">Tourists Count</th>
                                <td>{{request.touristsCount}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-2">Tour Starts</th>
                                <td>{{request.tourStart}} / {{request.strTourStart}}</td>

                                <th class="col-md-2">Tourists Count Note</th>
                                <td>{{request.touristsCountNote}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-2">Tour Ends</th>
                                <td>{{request.tourEnd}} / {{request.strTourEnd}}</td>

                                <th class="col-md-2">Arrival City</th>
                                <td>{{request.arrivalCity.name}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-2">Days Count</th>
                                <td>{{request.daysCount}}</td>

                                <th class="col-md-2">Arrival Time</th>
                                <td>{{request.arrivalTime}} / {{request.strArrivalTime}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-2">Nights Count</th>
                                <td>{{request.nightsCount}}</td>

                                <th class="col-md-2">Leave City</th>
                                <td>{{request.leaveCity.name}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-2">Leave Time</th>
                                <td>{{request.leaveTime}} / {{request.strLeaveTime}}</td>
                            </tr>
                        </table>

                        <a class="btn btn-app">
                            <i class="fa fa-car"></i> Transports
                        </a>

                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Transport</th>
                                <th>Price</th>
                                <th>Tourists Count</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="r in transports">
                                <td>{{r.transport.nameEn}}</td>
                                <td>{{r.transport.price}}</td>
                                <td>{{r.touristCount}}</td>
                            </tr>
                            </tbody>
                        </table>
                        Transport Needed For Days: &nbsp;
                        <label ng-repeat="t in transportDaysList" style="display: inline !important;">
                            &nbsp; Day -
                            {{t}}&nbsp;&nbsp;
                        </label>
                    </div>
                </div>
            </div>


            <div class="box-body">

                <div class="panel panel-default col-sm-6"
                     style="padding-left: 0px !important; padding-right: 0px !important;">
                    <div class="panel-heading">
                        <a class="btn btn-app">
                            <i class="fa fa-map"></i> Regions
                        </a>
                    </div>
                    <div class="panel-body">
                        <div class="form-group col-sm-12 ">
                            <div class="col-sm-12">
                                <label ng-repeat="t in product.regions" class="col-sm-3">
                                    {{$index+1}}) &nbsp;{{t.region.name}}
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default col-sm-6"
                     style="padding-left: 0px !important; padding-right: 0px !important;">
                    <div class="panel-heading">
                        <a class="btn btn-app">
                            <i class="fa fa-map-marker"></i> Places
                        </a>
                        <div class="progress">
                            <div class="progress-bar {{$index %2 == 0 ? 'progress-bar-success':'progress-bar-primary'}}"
                                 role="progressbar" ng-repeat="key in distances | orderBy : '[]': true"
                                 style="{{'width:'+100/distances.length+'%;'}}">
                                {{key}}
                            </div>
                        </div>
                        Sum Of Day({{daysList[dayIndex]}}) Distance: {{distanceSum}} Kilometers
                    </div>
                    <div class="panel-body">
                        <div class="form-group col-sm-12 ">
                            <div class="col-sm-12">
                                <label ng-repeat="t in product.places" class="col-sm-3">
                                    {{$index+1}}) &nbsp;{{t.place.nameEn}}
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="box-body">

                <div class="panel panel-info ">
                    <div class="panel-heading">
                        <a class="btn btn-app">
                            <i class="fa fa-university"></i> Sights
                        </a>
                    </div>
                    <div class="panel-body">
                        <div class="form-group col-sm-12 ">
                            <div class="col-sm-12">
                                <label ng-repeat="t in product.sights" class="col-sm-3 panel">
                                    {{$index+1}}) {{t.nameEn}} {{photoOrVisit == 2?'( Photo )':''}}
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <a class="btn btn-app">
                            <i class="fa fa-bed"></i> Hotels
                        </a>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="form-group col-sm-12 ">
                        <div class="col-sm-12">
                            <fieldset class="border p-2">
                                <legend class="w-auto">Group N1</legend>

                                <div class="form-group">
                                    <div ng-repeat="t in product.hotels1" class="col-sm-3 panel">
                                        {{$index+1}}) {{t.hotel.nameEn}}
                                        <%--<input value="{{t.hotel.singlePrice}}" placeholder="Single Price" title="Single Price">--%>
                                    </div>
                                </div>

                            </fieldset>
                            <br><br>

                            <fieldset class="border p-2">
                                <legend class="w-auto">Group N2</legend>

                                <div class="form-group">
                                    <div ng-repeat="t in product.hotels2" class="col-sm-3 panel">
                                        {{$index+1}}) {{t.hotel.nameEn}}
                                        <%--<input value="{{t.hotel.singlePrice}}" placeholder="Single Price" title="Single Price">--%>
                                    </div>
                                </div>
                            </fieldset>
                            <br><br>

                            <fieldset class="border p-2">
                                <legend class="w-auto">Group N3</legend>

                                <div class="form-group">
                                    <div ng-repeat="t in product.hotels3" class="col-sm-3 panel">
                                        {{$index+1}}) {{t.hotel.nameEn}}
                                        <%--<input value="{{t.hotel.singlePrice}}" placeholder="Single Price" title="Single Price">--%>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>


            <div class="panel panel-success ">
                <div class="panel-heading">
                    <a class="btn btn-app">
                        <i class="fa fa-cutlery"></i> Restaurants
                    </a>
                </div>
                <div class="panel-body">
                    <div class="form-group col-sm-12 ">
                        <div class="col-sm-12">
                            <div class="panel panel-default" ng-repeat="r in product.restaurants">
                                <div class="panel-heading">{{r.restaurant.nameEn}}</div>
                                <div class="panel-body">
                                    <div class="col-sm-6">
                                        <label>
                                            Meal Category: {{r.mealCategories}}
                                        </label>
                                    </div>
                                    <div class="col-sm-6">
                                        <label class="control-label col-sm-2">
                                            Packages:
                                        </label>
                                        <ul class="col-sm-9">
                                            <li ng-if="r.packages.length > 0" ng-repeat="t in r.packages.split(',')">
                                                {{t}}
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr class="col-sm-12"/>
                    </div>
                </div>
            </div>

            <div class="panel panel-danger ">
                <div class="panel-heading">
                    <a class="btn btn-app">
                        <i class="fa fa-server"></i> NonSt. Ser.
                    </a>
                </div>
                <div class="panel-body">
                    <div class="form-group col-sm-12 ">
                        <div class="col-sm-12">
                            <label ng-repeat="t in product.nonstandarts" class="col-sm-3">
                                {{$index+1}}) &nbsp; {{t.nonstandartService.nameEn}}
                            </label>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="panel-footer">
            <div class="row">
                <div class="col col-md-12">
                    <ul class="pagination pull-right">
                        <li>
                            <a>(Day {{daysList[dayIndex]}}) </a>
                        </li>
                        <li>
                            <a ng-click="handleDayChange(-1)">« &nbsp; Prev</a>
                        </li>
                        <li ng-show="dayIndex < (request.daysCount-1)">
                            <a ng-click="handleDayChange(1)">» &nbsp; Next</a>
                        </li>
                    </ul>
                </div>
                <div class="form-group col-sm-12">
                    <div class="col-sm-6 text-right">
                        <a class="btn btn-app" ng-click="save()">
                            <i class="fa fa-save"></i> Save Data
                        </a>
                    </div>
                    <div class="col-sm-6 text-right">
                        <a class="btn btn-app" ng-click="generateWord()" title="Generate Word">
                            <i class="fa fa-file-word-o"></i>
                        </a>
                        <a class="btn btn-app" ng-click="sendToReservation()">
                            <i class="fa fa-send"></i> Send To Reservation
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>