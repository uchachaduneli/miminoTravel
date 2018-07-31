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

    $scope.loadRequest = function () {
      var absUrl = $location.absUrl();
      if (absUrl.split("?")[1]) {
        $scope.requestKey = absUrl.split("?")[1].split("=")[1];
        console.log($scope.requestKey);
      }
      $('#loadingModal').modal('show');

      function getRequestData(res) {
        $scope.request = res.data;
        $('#loadingModal').modal('hide');
      }

      ajaxCall($http, "requests/get-request-by-key?id=" + $scope.requestKey, null, getRequestData);
    }

    $scope.loadRequest();

    function getMealcategories(res) {
      $scope.mealCategories = res.data;
    }

    ajaxCall($http, "misc/get-mealcategories", null, getMealcategories);

    $scope.addCountryRow = function () {
      var size = $scope.countryRow.length;
      $scope.countryRow.push(size + 1);
    };

    $scope.removePotentialLocation = function (index) {
      $scope.countryRow.splice(index, 1);
      if ($scope.request.combinedCountries) {
        $scope.request.combinedCountries.splice(index, 1);
      }
    };
  });
</script>


<div class="row">
  <div class="col-xs-12">
    <div ng-repeat="n in [].constructor(request.daysCount) track by $index" class="box">
      <div class="box-header text-center">
        <b><h4>Day ({{$index}})</h4></b>
      </div>
      <div class="box-body">
        {{$index}}
      </div>
    </div>
  </div>
</div>
<%@include file="footer.jsp" %>