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
  app.controller("angController", function ($scope, $http, $filter, $location) {

    $scope.product = {sights: [], places: [], hotels: [], transports: [], nonstandarts: []};
    $scope.daysList = ['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX', 'X'];
    $scope.HbMealCats = ['Lunch', 'Picnick Lunch', 'Lunch with degustation', 'Dinner', 'Dinner Folk Show', 'Gala Dinner', 'Dinner At hotel'];
    $scope.FbMealCats = ['Breakfast', 'Lunch', 'Picnick Lunch', 'Lunch with degustation', 'Dinner', 'Dinner Folk Show', 'Gala Dinner', 'Dinner At hotel'];
    $scope.dayIndex = 0;
    $scope.mealCategories = [];
    $scope.restaurantRow = [];
    $scope.prod = {restaurants: []};
    $scope.restPackages = [];

    function getMealCategories(res) {
      $scope.mealCategories = res.data;
    }

    ajaxCall($http, "misc/get-mealcategories", null, getMealCategories);

    $scope.loadRequest = function () {
      var absUrl = $location.absUrl();
      if (absUrl.split("?")[1]) {
        $scope.requestKey = absUrl.split("?")[1].split("=")[1];
        console.log($scope.requestKey);
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
          transports: [],
          nonstandarts: [],
          restaurants: []
        };
        $scope.product.sights = res.data.sights;
        $scope.product.regions = res.data.regions;
        $scope.product.places = res.data.places;
        $scope.product.hotels = res.data.hotels;
        $scope.product.transports = res.data.transports;
        $scope.product.nonstandarts = res.data.nonstandarts;

        if (res.data.restaurants == undefined || res.data.restaurants.length == 0) {
          $scope.restaurantRow = [1];
        }
        angular.forEach(res.data.restaurants, function (v, k) {
          var meal = (v.mealCategories.split('-')[0] != undefined ? v.mealCategories.split('-')[0] : '');
          var mealCats = (v.mealCategories.split('-')[1] != undefined ? v.mealCategories.split('-')[1].split(',') : '');
          var packs = '';
          if (v.packages != undefined) {
            $scope.loadRestaurantPackages(v.restaurantId, k);
            packs = v.packages.split(',')
          }
          ;
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
          function getHotels(res) {
            $scope.hotels = res.data;
          }

          ajaxCall($http, "hotels/get-hotels-by-place", angular.toJson($scope.product.places), getHotels);

          function getSights(res) {
            $scope.sights = res.data;
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

      function getTransports(res) {
        $scope.transports = res.data;
      }

      ajaxCall($http, "transports/get-transports?start=0&limit=99999", {}, getTransports);

      function getNonstandarts(res) {
        $scope.nonstandarts = res.data;
        $('#loadingModal').modal('hide');
      }

      ajaxCall($http, "nonstandarts/get-nonstandart-services?start=0&limit=99999", {}, getNonstandarts);
    };

    $scope.loadLists();

    $scope.handleDayChange = function (h) {
      console.log($scope.dayIndex);
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

//      console.log(angular.toJson($scope.prod));
      $scope.product.restaurants = [];
      angular.forEach($scope.prod.restaurants, function (v, k) {
        var packages = v.packages != undefined ? v.packages.join(',') : '';
        console.log(angular.toJson(packages));
        $scope.product.restaurants.push({
          restaurantId: v.restaurantId,
          mealCategories: v.meal + '-' + v.mealCats.join(','),
          packages: packages
        });
      });
      console.log(angular.toJson($scope.product));
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

    $scope.searchByPlace = function () {
      var slctedPlaces = $filter('filter')($scope.places, {selected: true}, true);
      var ids = [];
      angular.forEach(slctedPlaces, function (v) {
        ids.push(v.id);
      })
      console.log(angular.toJson(ids));
      if (ids.length > 0) {
        function getHotels(res) {
          $scope.hotels = res.data;
        }

        ajaxCall($http, "hotels/get-hotels-by-place", angular.toJson(ids), getHotels);

        function getSights(res) {
          $scope.sights = res.data;
        }

        ajaxCall($http, "objects/get-objects-by-place", angular.toJson(ids), getSights);
      } else {
        $scope.hotels = [];
        $scope.sights = [];
      }
    };

    $scope.addRestaurantRow = function () {
      var size = $scope.restaurantRow.length;
      $scope.restaurantRow.push(size + 1);
    };

    $scope.removeRestaurantRows = function (index) {
      $scope.restaurantRow.splice(index, 1);
      if ($scope.product.restaurants) {
        $scope.product.restaurants.splice(index, 1);
      }
    };

  });
</script>


<div class="row">
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
                <label ng-repeat="t in regions" class="col-sm-3">
                  <input type="checkbox" id="regionchecks{{t.id}}" ng-model="t.selected" ng-click="searchByRegion()"
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
          </div>
          <div class="panel-body">
            <div class="form-group col-sm-12 ">
              <div class="col-sm-12">
                <label ng-repeat="t in places" class="col-sm-3">
                  <input type="checkbox" id="placechecks{{t.id}}" ng-model="t.selected" ng-click="searchByPlace()"
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
                <label ng-repeat="t in sights" class="col-sm-3">
                  <input type="checkbox" id="sightschecks{{t.id}}"
                         checklist-model="product.sights" checklist-value="t.id">&nbsp; {{t.nameEn}}
                </label>
              </div>
            </div>
          </div>
        </div>

        <div class="panel panel-success ">
          <div class="panel-heading">
            <a class="btn btn-app">
              <i class="fa fa-bed"></i> Hotels
            </a>
          </div>
          <div class="panel-body">
            <div class="form-group col-sm-12 ">
              <div class="col-sm-12">
                <label ng-repeat="t in hotels" class="col-sm-3">
                  <input type="checkbox" id="hotelschecks{{t.id}}"
                         checklist-model="product.hotels" checklist-value="t.id">&nbsp; {{t.nameEn}}
                </label>
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
                      <label><input type="radio" ng-model="prod.restaurants[r - 1].meal" value="BB" class="input-sm">BB</label>&nbsp;
                      <label><input type="radio" ng-model="prod.restaurants[r - 1].meal" value="HB" class="input-sm">HB</label>&nbsp;
                      <label><input type="radio" ng-model="prod.restaurants[r - 1].meal" value="FB" class="input-sm">FB</label>
                    </div>
                    <div class="col-sm-12" ng-if="prod.restaurants[r - 1].meal == 'HB'">
                      <label ng-repeat="t in HbMealCats" class="col-sm-3">
                        <input type="checkbox" id="restMealsChecks{{t.id}}"
                               checklist-model="prod.restaurants[r - 1].mealCats" checklist-value="t">&nbsp; {{t}}
                      </label>
                    </div>
                    <div class="col-sm-12" ng-if="prod.restaurants[r - 1].meal === 'FB'">
                      <label ng-repeat="t in FbMealCats" class="col-sm-3">
                        <input type="checkbox" id="restFbMealsChecks{{t.id}}"
                               checklist-model="prod.restaurants[r - 1].mealCats" checklist-value="t">&nbsp; {{t}}
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
                        <span class="fa fa-trash" ng-click="removeRestaurantRows($index)"></span>
                      </a>
                    </div>
                  </div>
                  <div class="col-sm-12" ng-if="prod.restaurants[r - 1].restaurantId > 0">
                    <div class="panel panel-default">
                      <div class="panel-heading">Restaurant Packages</div>
                      <div class="panel-body">
                        <label ng-repeat="t in restPackages[r - 1]" class="col-sm-3">
                          <input type="checkbox" id="restPackageschecks{{t.id}}"
                                 checklist-model="prod.restaurants[r - 1].packages" checklist-value="t.name">&nbsp;
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

        <div class="panel panel-warning ">
          <div class="panel-heading">
            <a class="btn btn-app">
              <i class="fa fa-car"></i> Transports
            </a>
          </div>
          <div class="panel-body">
            <div class="form-group col-sm-12 ">
              <div class="col-sm-12">
                <label ng-repeat="t in transports" class="col-sm-3">
                  <input type="checkbox" id="transportschecks{{t.id}}"
                         checklist-model="product.transports" checklist-value="t.id">&nbsp; {{t.nameEn}}
                </label>
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
                <label ng-repeat="t in nonstandarts" class="col-sm-3">
                  <input type="checkbox" id="nonstandartschecks{{t.id}}"
                         checklist-model="product.nonstandarts" checklist-value="t.id">&nbsp; {{t.nameEn}}
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
          <div class="form-group col-sm-12 text-center">
            <a class="btn btn-app" ng-click="save()">
              <i class="fa fa-save"></i> Save
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="footer.jsp" %>