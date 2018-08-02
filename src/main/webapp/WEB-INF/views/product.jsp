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
    $scope.dayIndex = 0;

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
        $scope.product = {sights: [], places: [], hotels: [], transports: [], nonstandarts: []};
        $scope.product.sights = res.data.sights;
        $scope.product.places = res.data.places;
        $scope.product.hotels = res.data.hotels;
        $scope.product.transports = res.data.transports;
        $scope.product.nonstandarts = res.data.nonstandarts;
      }

      ajaxCall($http, "requests/get-product-details?requestId=" + $scope.request.id + "&day=" + ($scope.dayIndex + 1), null, getProdDet);
    };

    $scope.loadLists = function () {

      $('#loadingModal').modal('show');

      $scope.loadRequest();

      function getMealcategories(res) {
        $scope.mealCategories = res.data;
      }

      ajaxCall($http, "misc/get-mealcategories", null, getMealcategories);


      function loadPlaces(res) {
        $scope.places = res.data;
      }

      ajaxCall($http, "places/get-places?start=0&limit=999999", {}, loadPlaces);

      function getHotels(res) {
        $scope.hotels = res.data;
      }

      ajaxCall($http, "hotels/get-hotels?start=0&limit=99999", {}, getHotels);

      function getSights(res) {
        $scope.sights = res.data;
      }

      ajaxCall($http, "objects/get-objects?start=0&limit=99999", {}, getSights);

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
          $location.reload();
        } else {
          errorMsg('Operation Failed');
        }
      }

      $scope.product.requestId = $scope.request.id;
      $scope.product.day = $scope.dayIndex + 1;

      console.log(angular.toJson($scope.product));
      ajaxCall($http, "requests/save-product", angular.toJson($scope.product), resFunc);
    };

  });
</script>


<div class="row">
  <div class="col-xs-12">
    <%--<div ng-repeat="n in [].constructor(request.daysCount) track by $index" class="box">--%>
    <div class="box">
      <div class="box-header text-center">
        <b><h4>Day ({{daysList[dayIndex]}})</h4></b>
      </div>
      <div class="box-body">

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
                  <input type="checkbox" id="placechecks{{t.id}}"
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