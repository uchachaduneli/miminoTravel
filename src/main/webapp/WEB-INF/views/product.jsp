<%--
  Created by IntelliJ IDEA.
  User: ME
  Date: 10/23/2017
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<script>
    // $(document).ready(function () {
    //     $('input[type=checkbox]').each(function (i, obj) {
    //         console.log($(this));
    //         // if ($(this).hasClass('ng-not-empty')) {
    //         if ($(this).is(':checked')) {
    //             console.log('yes');
    //             $(this).parent().addClass('markAsSelected');
    //         }
    //     });
    // });

    app.controller("angController", ['$scope', '$http', '$filter', '$location', '$window', 'Upload', '$timeout', function ($scope, $http, $filter, $location, $window, Upload, $timeout) {

        $scope.product = {sights: [], places: [], hotels: [], nonstandarts: []};
        $scope.daysList = ['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX', 'X'];
        $scope.HbMealCats = ['Lunch', 'Picnick Lunch', 'Lunch with degustation', 'Dinner', 'Dinner Folk Show', 'Gala Dinner', 'Dinner At hotel'];
        $scope.FbMealCats = ['Breakfast', 'Lunch', 'Picnick Lunch', 'Lunch with degustation', 'Dinner', 'Dinner Folk Show', 'Gala Dinner', 'Dinner At hotel'];
        $scope.dayIndex = 0;
        $scope.mealCategories = [];
        $scope.restaurantRow = [];
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

        function getMealCategories(res) {
            $scope.mealCategories = res.data;
        }

        ajaxCall($http, "misc/get-mealcategories", null, getMealCategories);

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
        }

        $scope.getProductDetails = function () {

            function getProdDet(res) {
                $scope.product = {
                    regions: [],
                    sights: [],
                    places: [],
                    hotels: [],
                    nonstandarts: [],
                    restaurants: []
                };
                $scope.transportDaysList = res.data.transportDays;
                $scope.transports = res.data.transports;

                $scope.product.regions = res.data.regions;
                $scope.product.places = res.data.places;
                angular.forEach($filter('filter')(res.data.hotels, {groupId: 1}, true), function (v) {
                    $scope.prod.hotels1.push(v.hotelId);
                    if($scope.hotelStars1 == '')
                        $scope.hotelStars1 = v.hotel.starsCount;
                });
                $scope.filterHotels1();
                angular.forEach($filter('filter')(res.data.hotels, {groupId: 2}, true), function (v) {
                    $scope.prod.hotels2.push(v.hotelId);
                    if($scope.hotelStars2 == '')
                        $scope.hotelStars2 = v.hotel.starsCount;
                });
                $scope.filterHotels2();
                angular.forEach($filter('filter')(res.data.hotels, {groupId: 3}, true), function (v) {
                    $scope.prod.hotels3.push(v.hotelId);
                    if($scope.hotelStars3 == '')
                        $scope.hotelStars3 = v.hotel.starsCount;
                });
                $scope.filterHotels3();

                $scope.loadDistances($scope.product.places);

                $scope.product.nonstandarts = res.data.nonstandarts;

                angular.forEach(res.data.sights, function (v, k) {
                    $scope.product.sights[v.id] = v.id;
                    $scope.combSights[v.id] = {id: v.id, photoOrVisit: v.photoOrVisit};
                });

                if (res.data.restaurants == undefined || res.data.restaurants.length == 0) {
                    $scope.restaurantRow = [1];
                } else {
                    $scope.restaurantRow = [];
                }
                angular.forEach(res.data.restaurants, function (v, k) {
                    var meal = (v.mealCategories.split('-')[0] != undefined ? v.mealCategories.split('-')[0] : '');
                    var mealCats = (v.mealCategories.split('-')[1] != undefined ? v.mealCategories.split('-')[1].split(',') : '');
                    var packs = '';
                    if (v.packages != undefined) {
                        $scope.loadRestaurantPackages(v.restaurantId, k);
                        packs = v.packages.split(',');
                    }

                    $scope.prod.restaurants.push({
                        "restaurantId": v.restaurantId,
                        "meal": meal,
                        "mealCats": mealCats,
                        "packages": packs
                    });
                    $scope.restaurantRow.push(k + 1);
                });

                var regionIds = [];
                angular.forEach($scope.product.regions, function (v) {
                    var slctedRegions = $filter('filter')($scope.regions, {id: v}, true);
                    if (slctedRegions != undefined && slctedRegions.length > 0) {
                        regionIds.push(v);
                    }
                });

                if (regionIds.length > 0) {
                    function loadPlaces(res) {
                        $scope.places = res.data;
                    }

                    ajaxCall($http, "places/get-places-by-region", angular.toJson(regionIds), loadPlaces);
                }

                if ($scope.product.places.length > 0) {
                    // function getHotels(res) {
                    //     $scope.hotels1 = res.data;
                    //     $scope.hotels2 = res.data;
                    //     $scope.hotels3 = res.data;
                    //
                    //     $scope.filterHotels1();
                    //     $scope.filterHotels2();
                    //     $scope.filterHotels3();
                    // }
                    //
                    // ajaxCall($http, "hotels/get-hotels-by-place?stars=" + $scope.hotelStars, angular.toJson($scope.product.places), getHotels);

                    function getSights(res) {
                        $scope.sights = res.data;
//            $scope.sights = [];
//            angular.forEach(res.data, function (v, k) {
//              $scope.sights[k] = {id: v.id, photoOrVisit: undefined};
//            });
                    }

                    ajaxCall($http, "objects/get-objects-by-place", angular.toJson($scope.product.places), getSights);

                    function getRestaurants(res) {
                        $scope.restaurants = res.data;
                    }

                    ajaxCall($http, "restaurants/get-restaurants-by-place", angular.toJson($scope.product.places), getRestaurants);
                }
            }

            ajaxCall($http, "requests/get-product-details?requestId=" + $scope.request.id + "&day=" + ($scope.dayIndex + 1), null, getProdDet);
        };

        $scope.loadRestaurantPackages = function (id, indx) {
            function getPacks(res) {
                $scope.restPackages[indx] = res.data;
            }

            ajaxCall($http, "restaurants/get-restaurant-packages?id=" + id, null, getPacks);
        };

        $scope.loadDistances = function (array) {
            $scope.distances = [];
            $scope.distanceSum = 0.0;

            function getDists(res) {
                angular.forEach(res.data, function (value, key) {
                    $scope.distances.push(key);
                    $scope.distanceSum = value + $scope.distanceSum;
                });
            }

            ajaxCall($http, "distances/get-distances-by-place", angular.toJson(array), getDists);
        };

        $scope.loadLists = function () {

            $('#loadingModal').modal('show');

            $scope.loadRequest();

            function getRegions(res) {
                $scope.regions = res.data;
            }

            ajaxCall($http, "misc/get-regions", null, getRegions);

            function getMealcategories(res) {
                $scope.mealCategories = res.data;
            }

            ajaxCall($http, "misc/get-mealcategories", null, getMealcategories);

            function getNonstandarts(res) {
                $scope.nonstandarts = res.data;
                $('#loadingModal').modal('hide');
            }

            ajaxCall($http, "nonstandarts/get-nonstandart-services?start=0&limit=99999", {}, getNonstandarts);
        };

        $scope.loadLists();

        $scope.handleDayChange = function (h) {
            $('#loadingModal').modal('show');
            if (parseInt(h) > 0) {
                $scope.save();

                $scope.dayIndex += 1;

            } else {
                $scope.dayIndex = parseInt($scope.dayIndex - 1) == -1 ? 0 : ($scope.dayIndex - 1);
            }
            $scope.getProductDetails();
            $('#loadingModal').modal('hide');
        };

        $scope.saveTransportDaysList = function () {
            function resFunc(res) {
                if (res.errorCode == 0) {
                    successMsg('Operation Successfull');
                } else {
                    errorMsg('Operation Failed');
                }
            }

            console.log($scope.transportDaysList);

            ajaxCall($http, "requests/update-transport-days?reqId=" + $scope.request.id + "&checkedDays=" + $scope.transportDaysList.join(','), null, resFunc);
        }

        $scope.save = function () {

            function resFunc(res) {
                if (res.errorCode == 0) {
                    successMsg('Data Saved Successfully');
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
            // $scope.product.product.requestId = $scope.request.id;
            console.log(angular.toJson($scope.product));
//      console.log(angular.toJson($scope.product.sights));

            ajaxCall($http, "requests/save-product", angular.toJson($scope.product), resFunc);
        };

        $scope.searchByRegion = function () {
            var slctedRegions = $filter('filter')($scope.regions, {selected: true}, true);
            var ids = [];
            angular.forEach(slctedRegions, function (v) {
                ids.push(v.id);
            })
            console.log(angular.toJson(ids));
            if (ids.length > 0) {
                function loadPlaces(res) {
                    $scope.places = res.data;
                }

                ajaxCall($http, "places/get-places-by-region", angular.toJson(ids), loadPlaces);
            } else {
                $scope.places = [];
            }
        };

        $scope.filterHotels1 = function () {
            var slctedPlaces = $filter('filter')($scope.places, {selected: true}, true);
            var ids = [];
            // angular.forEach(slctedPlaces, function (v) {
            if(slctedPlaces != undefined){
                ids.push(slctedPlaces[slctedPlaces.length - 1].id);
            // });
            if (ids.length > 0 || $scope.hotelStars1.length > 0) {
                function getHotels(res) {
                    $scope.hotels1 = res.data;
                }

                ajaxCall($http, "hotels/get-hotels-by-place?stars=" + $scope.hotelStars1, angular.toJson(ids), getHotels);
            }
            }
        }
        $scope.filterHotels2 = function () {
            var slctedPlaces = $filter('filter')($scope.places, {selected: true}, true);
            var ids = [];
            // angular.forEach(slctedPlaces, function (v) {
            if(slctedPlaces != undefined) {
                ids.push(slctedPlaces[slctedPlaces.length - 1].id);
                // });
                if (ids.length > 0 || $scope.hotelStars2.length > 0) {
                    function getHotels(res) {
                        $scope.hotels2 = res.data;
                    }

                    ajaxCall($http, "hotels/get-hotels-by-place?stars=" + $scope.hotelStars2, angular.toJson(ids), getHotels);
                }
            }
        }
        $scope.filterHotels3 = function () {
            var slctedPlaces = $filter('filter')($scope.places, {selected: true}, true);
            var ids = [];
            // angular.forEach(slctedPlaces, function (v) {
            if(slctedPlaces != undefined) {
                ids.push(slctedPlaces[slctedPlaces.length - 1].id);
                // });
                if (ids.length > 0 || $scope.hotelStars3.length > 0) {
                    function getHotels(res) {
                        $scope.hotels3 = res.data;
                    }

                    ajaxCall($http, "hotels/get-hotels-by-place?stars=" + $scope.hotelStars3, angular.toJson(ids), getHotels);
                }
            }
        }

        $scope.searchByPlace = function () {
            var slctedPlaces = $filter('filter')($scope.places, {selected: true}, true);
            var ids = [];
            angular.forEach(slctedPlaces, function (v) {
                ids.push(v.id);
            });
            $scope.loadDistances(ids);
            console.log(angular.toJson(ids));
            if (ids.length > 0 || $scope.hotelStars.length > 0) {
                // function getHotels(res) {
                //     $scope.hotels = res.data;
                // }
                //
                // ajaxCall($http, "hotels/get-hotels-by-place?stars=" + $scope.hotelStars, angular.toJson(ids), getHotels);

                function getSights(res) {
                    $scope.sights = res.data;
                }

                ajaxCall($http, "objects/get-objects-by-place", angular.toJson(ids), getSights);
            } else {
                // $scope.hotels = [];
                $scope.sights = [];
            }
        };

        $scope.addRestaurantRow = function () {
            var size = $scope.restaurantRow.length;
            $scope.restaurantRow.push(size + 1);
        };

        $scope.sightPhotoVisithandler = function (val, sightId) {
            var slcted = $filter('filter')($scope.product.sights, sightId, true);
            if (slcted != undefined && slcted.length > 0) {
                $scope.combSights[sightId].id = sightId;
            } else {
                errorMsg('Please Check Current Sight At First!');
                $scope.combSights[sightId] = undefined;
            }
        };

        $scope.removeRestaurantRows = function (index) {
            $scope.restaurantRow.splice(index, 1);
            if ($scope.product.restaurants) {
                $scope.product.restaurants.splice(index, 1);
            }
        };

        $scope.generateWord = function () {
            $('#loadingModal').modal('show');

            function redirectToFile() {
                $('#loadingModal').modal('hide');
                $window.open("/requestDoc.docx", "_blank");
            }

            ajaxCall($http, "requests/generate-word", angular.toJson($scope.request.id), redirectToFile);
        }

        $scope.checkInList = function (item, list) {
            return $filter('filter')(list, item, true).length;
        }

        $scope.sendToFinance = function () {
            $scope.save();
            if (confirm("Pleace confirm Sending to Finance")) {
                $window.location.href = "/miminoTravel/financial?key=" + $scope.request.requestKey;
            }
        };

    }]);
</script>
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

<div class="row">
    <button id="myBtn">Day ({{daysList[dayIndex]}})</button>
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <div class="col-xs-12 text-center">
                    <b><h4>Day ({{daysList[dayIndex]}})</h4></b>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="btn btn-app">
                            <i class="fa fa-briefcase"></i> Request Details
                        </a>
                        <table class="table table-striped">
                            <tr>
                                <th class="col-md-1">Tour Code</th>
                                <td>{{request.tourCode}}</td>
                            </tr>
                            <tr>
                                <th>Tour Starts</th>
                                <td>{{request.tourStart}} / {{request.strTourStart}}</td>
                            </tr>
                            <tr>
                                <th>Tour Ends</th>
                                <td>{{request.tourEnd}} / {{request.strTourEnd}}</td>
                            </tr>
                            <tr>
                                <th>Days Count</th>
                                <td>{{request.daysCount}}</td>
                            </tr>
                            <tr>
                                <th>Nights Count</th>
                                <td>{{request.nightsCount}}</td>
                            </tr>
                            <tr>
                                <th>Tourists Count</th>
                                <td>{{request.touristsCount}}</td>
                            </tr>
                            <tr>
                                <th>Tourists Count Note</th>
                                <td>{{request.touristsCountNote}}</td>
                            </tr>
                            <tr>
                                <th>Arrival City</th>
                                <td>{{request.arrivalCity.name}}</td>
                            </tr>
                            <tr>
                                <th>Arrival Time</th>
                                <td>{{request.arrivalTime}} / {{request.strArrivalTime}}</td>
                            </tr>
                            <tr>
                                <th>Leave City</th>
                                <td>{{request.leaveCity.name}}</td>
                            </tr>
                            <tr>
                                <th>Leave Time</th>
                                <td>{{request.leaveTime}} / {{request.strLeaveTime}}</td>
                            </tr>
                        </table>

                        <a class="btn btn-app">
                            <i class="fa fa-car"></i> Transports
                        </a>

                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Transport</th>
                                <th>Price</th>
                                <th>Tourists Count</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="r in transports">
                                <td>{{r.id}}</td>
                                <td>{{r.transport.nameEn}}</td>
                                <td>{{r.transport.price}}</td>
                                <td>{{r.touristCount}}</td>
                            </tr>
                            </tbody>
                        </table>
                        <label ng-repeat="t in daysList | limitTo:request.daysCount"
                               style="display: inline !important; margin-left: 10px;" class="{{checkInList(t, transportDaysList) > 0 ? 'markAsSelected':''}}">
                            <input type="checkbox" id="daysListchecks{{t.id}}"
                                   ng-click="saveTransportDaysList()"
                                   checklist-model="transportDaysList" checklist-value="t">&nbsp; Day -
                            {{t}}&nbsp;&nbsp;
                        </label>
                    </div>
                </div>
            </div>
            <div class="box-body">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="btn btn-app">
                            <i class="fa fa-map"></i> Regions
                        </a>
                    </div>
                    <div class="panel-body">
                        <div class="form-group col-sm-12 ">
                            <div class="col-sm-12">
                                <label ng-repeat="t in regions"
                                       class="col-sm-3 {{t.selected == true ? ' markAsSelected':''}}">
                                    <input type="checkbox" id="regionchecks{{t.id}}" ng-model="t.selected"
                                           ng-click="searchByRegion()"
                                           checklist-model="product.regions" checklist-value="t.id">&nbsp; {{t.name}}
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="btn btn-app">
                            <i class="fa fa-map-marker"></i> Places
                        </a>
                        <div class="progress">
                            <div class="progress-bar {{$index %2 == 0 ? 'progress-bar-success':'progress-bar-primary'}}"
                                 role="progressbar" ng-repeat="key in distances"
                                 style="{{'width:'+100/distances.length+'%;'}}">
                                {{key}}
                            </div>
                        </div>
                        Sum Of Day({{daysList[dayIndex]}}) Distance: {{distanceSum}} Kilometers
                    </div>
                    <div class="panel-body">
                        <div class="form-group col-sm-12 ">
                            <div class="col-sm-12">
                                <label ng-repeat="t in places"
                                       class="col-sm-3 {{t.selected == true ? ' markAsSelected':''}}">
                                    <input type="checkbox" id="placechecks{{t.id}}" ng-model="t.selected"
                                           ng-click="searchByPlace()"
                                           checklist-model="product.places" checklist-value="t.id">&nbsp; {{t.nameEn}}
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-info ">
                    <div class="panel-heading">
                        <a class="btn btn-app">
                            <i class="fa fa-university"></i> Sights
                        </a>
                    </div>
                    <div class="panel-body">
                        <div class="form-group col-sm-12 ">
                            <div class="col-sm-12">
                                <label ng-repeat="t in sights" class="col-sm-3 panel {{checkInList(t.id, product.sights) > 0 ? 'markAsSelected':''}}">

                                    <input type="checkbox" id="sightschecks{{t.id}}"
                                           checklist-model="product.sights" checklist-value="t.id">&nbsp;
                                    {{t.nameEn}}

                                    <button class="btn btn-xs" type="button" data-toggle="collapse" data-target="#collapsibleDiv{{t.id}}">
                                        <span class="caret"></span>
                                    </button>

                                    <div id="collapsibleDiv{{t.id}}" class="radio text-right collapse">
                                        <label><input type="radio" ng-model="combSights[t.id].photoOrVisit" value="1"
                                                      ng-change="sightPhotoVisithandler(combSights[t.id].photoOrVisit, t.id)"
                                                      class="input-sm">Visit</label>&nbsp;
                                        <label><input type="radio" ng-model="combSights[t.id].photoOrVisit" value="2"
                                                      ng-change="sightPhotoVisithandler(combSights[t.id].photoOrVisit, t.id)"
                                                      class="input-sm">Photo
                                            Stop</label>&nbsp;
                                    </div>
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
                                    <select class="form-control" ng-model="hotelStars1"
                                            ng-change="filterHotels1()">
                                        <option value="" selected="selected">Stars Count</option>
                                        <option ng-repeat="v in stars" ng-selected="v === hotelStars1"
                                                value="{{v}}">{{v}}
                                        </option>
                                    </select>
                                </div>

                                <label ng-repeat="t in hotels1" class="col-sm-3 {{checkInList(t.id, prod.hotels1) > 0 ? 'markAsSelected':''}}">
                                    <input type="checkbox" id="gr1hotelschecks{{t.id}}"
                                           checklist-model="prod.hotels1" checklist-value="t.id">&nbsp; {{t.nameEn}}
                                </label>
                            </fieldset>
                            <fieldset class="border p-2">
                                <legend class="w-auto">Group N2</legend>

                                <div class="form-group">
                                    <select class="form-control" ng-model="hotelStars2"
                                            ng-change="filterHotels2()">
                                        <option value="" selected="selected">Stars Count</option>
                                        <option ng-repeat="v in stars" ng-selected="v === hotelStars2"
                                                value="{{v}}">{{v}}
                                        </option>
                                    </select>
                                </div>
                                <label ng-repeat="t in hotels2" class="col-sm-3 {{checkInList(t.id, prod.hotels2) > 0 ? 'markAsSelected':''}}">
                                    <input type="checkbox" id="gr2hotelschecks{{t.id}}"
                                           checklist-model="prod.hotels2" checklist-value="t.id">&nbsp; {{t.nameEn}}
                                </label>
                            </fieldset>
                            <fieldset class="border p-2">
                                <legend class="w-auto">Group N3</legend>

                                <div class="form-group">
                                    <select class="form-control" ng-model="hotelStars3"
                                            ng-change="filterHotels3()">
                                        <option value="" selected="selected">Stars Count</option>
                                        <option ng-repeat="v in stars" ng-selected="v === hotelStars3"
                                                value="{{v}}">{{v}}
                                        </option>
                                    </select>
                                </div>
                                <label ng-repeat="t in hotels3" class="col-sm-3 {{checkInList(t.id, prod.hotels3) > 0 ? 'markAsSelected':''}}">
                                    <input type="checkbox" id="gr3hotelschecks{{t.id}}"
                                           checklist-model="prod.hotels3" checklist-value="t.id">&nbsp; {{t.nameEn}}
                                </label>
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
                        <label class="control-label col-sm-3">Add Restaurants </label>
                        <div class="col-sm-9">
                            <div class="form-group" ng-repeat="r in restaurantRow">
                                <div class="col-sm-11" id="divId_{{r}}">
                                    <div class="col-sm-6" id="dv_{{r}}">
                                        <select id="restaurantSelect{{r}}" class="form-control input-sm"
                                                ng-change="loadRestaurantPackages(prod.restaurants[r - 1].restaurantId, r-1)"
                                                ng-model="prod.restaurants[r - 1].restaurantId">
                                            <option ng-repeat="c in restaurants" value="{{c.id}}" ng-value="c.id"
                                                    ng-selected="c.id === prod.restaurants[r - 1].restaurantId">
                                                {{c.id}}. {{c.nameEn}}
                                            </option>
                                        </select>
                                    </div>
                                    <div class="radio col-sm-6">
                                        <label><input type="radio" ng-model="prod.restaurants[r - 1].meal"
                                                      value="BB" class="input-sm">BB</label>&nbsp;
                                        <label><input type="radio" ng-model="prod.restaurants[r - 1].meal"
                                                      value="HB" class="input-sm">HB</label>&nbsp;
                                        <label><input type="radio" ng-model="prod.restaurants[r - 1].meal"
                                                      value="FB" class="input-sm">FB</label>
                                    </div>
                                    <div class="col-sm-12" ng-if="prod.restaurants[r - 1].meal == 'HB'">
                                        <label ng-repeat="t in HbMealCats" class="col-sm-3 {{checkInList(t, prod.restaurants[r - 1].mealCats) > 0 ? 'markAsSelected':''}}">
                                            <input type="checkbox" id="restMealsChecks{{t.id}}"
                                                   checklist-model="prod.restaurants[r - 1].mealCats"
                                                   checklist-value="t">&nbsp; {{t}}
                                        </label>
                                    </div>
                                    <div class="col-sm-12" ng-if="prod.restaurants[r - 1].meal === 'FB'">
                                        <label ng-repeat="t in FbMealCats" class="col-sm-3 {{checkInList(t, prod.restaurants[r - 1].mealCats) > 0 ? 'markAsSelected':''}}">
                                            <input type="checkbox" id="restFbMealsChecks{{t.id}}"
                                                   checklist-model="prod.restaurants[r - 1].mealCats"
                                                   checklist-value="t">&nbsp; {{t}}
                                        </label>
                                    </div>
                                </div>
                                <div class="col-sm-1">
                                    <div class="col-md-1" ng-show="$index == 0">
                                        <a class="btn btn-sm row" style="vertical-align: bottom;">
                                            <span class="fa fa-plus" ng-click="addRestaurantRow()"></span>
                                        </a>
                                    </div>
                                    <div class="col-md-1" ng-show="$index > 0">
                                        <a class="btn btn-sm row">
                                                <span class="fa fa-trash"
                                                      ng-click="removeRestaurantRows($index)"></span>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-sm-12" ng-if="prod.restaurants[r - 1].restaurantId > 0">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">Restaurant Packages</div>
                                        <div class="panel-body">
                                            <label ng-repeat="t in restPackages[r - 1]" class="col-sm-3 {{checkInList(t.name, prod.restaurants[r - 1].packages) > 0 ? 'markAsSelected':''}}">
                                                <input type="checkbox" id="restPackageschecks{{t.id}}"
                                                       checklist-model="prod.restaurants[r - 1].packages"
                                                       checklist-value="t.name">&nbsp;
                                                {{t.name}}
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <hr class="col-sm-12"/>
                            </div>
                        </div>
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
                            <label ng-repeat="t in nonstandarts" class="col-sm-3 {{checkInList(t.id, product.nonstandarts) > 0 ? 'markAsSelected':''}}">
                                <input type="checkbox" id="nonstandartschecks{{t.id}}"
                                       checklist-model="product.nonstandarts" checklist-value="t.id">&nbsp;
                                {{t.nameEn}}
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
                            <i class="fa fa-save"></i> Save
                        </a>
                    </div>
                    <div class="col-sm-6 text-right">
                        <a class="btn btn-app" ng-click="generateWord()" title="Generate File(Word)">
                            <i class="fa fa-file-word-o"></i>
                        </a>
                        <a class="btn btn-app" ng-click="sendToFinance()">
                            <i class="fa fa-send"></i> Send To Finance
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%@include file="footer.jsp" %>