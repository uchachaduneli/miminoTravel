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

  app.controller("angController", function ($scope, $http, $filter) {
    $scope.start = 0;
    $scope.page = 1;
    $scope.limit = "10";
    $scope.request = {types: []};
    $scope.srchCase = {};

    $scope.loadMainData = function () {
      $('#loadingModal').modal('show');

      function getMainData(res) {
        $scope.list = res.data;
        $('#loadingModal').modal('hide');
      }

      ajaxCall($http, "transports/get-transports?start=" + $scope.start + "&limit=" + $scope.limit, angular.toJson($scope.srchCase), getMainData);
    }

    $scope.loadMainData();

    $scope.loadFuels = function () {
      function getFuels(res) {
        $scope.fuels = res.data;
        $scope.loadFuelPrices();
      }

      ajaxCall($http, "transports/get-fuels", null, getFuels);
    }

    $scope.loadFuels();

    $scope.remove = function (id) {
      if (confirm("Pleace confirm operation?")) {
        if (id != undefined) {
          function resFnc(res) {
            if (res.errorCode == 0) {
              successMsg('Operation Successfull');
              $scope.loadMainData();
            }
          }

          ajaxCall($http, "transports/delete?id=" + id, null, resFnc);
        }
      }
    };

    $scope.edit = function (id) {
      if (id != undefined) {
        var selected = $filter('filter')($scope.list, {id: id}, true);
        $scope.slcted = selected[0];
        $scope.request = selected[0];
        $scope.loadDetailsList($scope.request.id);
      }
    }

    $scope.showDetails = function (id) {
      if (id != undefined) {
        var selected = $filter('filter')($scope.list, {id: id}, true);
        $scope.slcted = selected[0];

        $scope.loadDetailsList($scope.slcted.id);
      }
    };

    $scope.loadDetailsList = function (id) {

    }

    $scope.handleDoubleClick = function (id) {
      $scope.showDetails(id);
      $('#detailModal').modal('show');
    };

    $scope.init = function () {
      $scope.request = {types: []};
    };

    $scope.save = function () {

      function resFunc(res) {
        if (res.errorCode == 0) {
          successMsg('Operation Successfull');
          $scope.loadMainData();
          closeModal('editModal');
        } else {
          errorMsg('Operation Failed');
        }
      }

      $scope.req = {};

      $scope.req.id = $scope.request.id;
      $scope.req.price = $scope.request.price;
      $scope.req.fuelConsumption = $scope.request.fuelConsumption;
      $scope.req.seatsCount = $scope.request.seatsCount;
      $scope.req.fuelId = $scope.request.fuelId;
      $scope.req.nameEn = $scope.request.nameEn;
      $scope.req.nameGe = $scope.request.nameGe;
      $scope.req.nameFr = $scope.request.nameFr;
      $scope.req.nameIt = $scope.request.nameIt;
      $scope.req.nameSp = $scope.request.nameSp;
      $scope.req.namePo = $scope.request.namePo;
      $scope.req.nameRu = $scope.request.nameRu;
      $scope.req.descriptionEn = $scope.request.descriptionEn;
      $scope.req.descriptionGe = $scope.request.descriptionGe;
      $scope.req.descriptionFr = $scope.request.descriptionFr;
      $scope.req.descriptionIt = $scope.request.descriptionIt;
      $scope.req.descriptionSp = $scope.request.descriptionSp;
      $scope.req.descriptionPo = $scope.request.descriptionPo;
      $scope.req.descriptionRu = $scope.request.descriptionRu;

      console.log(angular.toJson($scope.req));
      ajaxCall($http, "transports/save", angular.toJson($scope.req), resFunc);
    };

    $scope.loadFuelPrices = function () {

      if ($scope.fuel == undefined) {
        $scope.fuel = {};
      }

      var selected = $filter('filter')($scope.fuels, {id: 1}, true);
      $scope.fuel.gasoline = selected[0].price;

      selected = $filter('filter')($scope.fuels, {id: 2}, true);
      $scope.fuel.diesel = selected[0].price;
      $scope.$apply();
    }

    $scope.saveFuel = function () {

      function resFunc1(res) {
        if (res.errorCode == 0) {
          console.log('Gasoline saved Successfully');
        } else {
          errorMsg('Operation Failed');
        }
      }

      function resFunc2(res) {
        if (res.errorCode == 0) {
          successMsg('Operation Successfull');
//          closeModal('fuelModal');
          $scope.loadFuels();

        } else {
          errorMsg('Operation Failed');
        }
      }

      $scope.gasoline = {id: 1, price: $scope.fuel.gasoline};
      $scope.diesel = {id: 2, price: $scope.fuel.diesel};

      console.log(angular.toJson($scope.gasoline));
      console.log(angular.toJson($scope.diesel));

      ajaxCall($http, "transports/save-fuel", angular.toJson($scope.gasoline), resFunc1);
      ajaxCall($http, "transports/save-fuel", angular.toJson($scope.diesel), resFunc2);
    };


    $scope.rowNumbersChange = function () {
      $scope.start = 0;
      $scope.loadMainData();
    }

    $scope.handlePage = function (h) {
      if (parseInt(h) >= 0) {
        $scope.start = $scope.page * parseInt($scope.limit);
        $scope.page += 1;
      } else {
        $scope.page -= 1;
        $scope.start = ($scope.page * parseInt($scope.limit)) - parseInt($scope.limit);
      }
      $scope.loadMainData();
    }
  });
</script>

<div class="modal fade bs-example-modal-lg" id="detailModal" tabindex="-1" role="dialog"
     aria-labelledby="editModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="detailModalLabel">Details</h4>
      </div>
      <div class="modal-body">
        <div class="row" id="printable">
          <table class="table table-striped">
            <tr>
              <th class="col-md-4 text-right">ID</th>
              <td>{{slcted.id}}</td>
            </tr>
            <tr>
              <th class="col-md-4 text-right">Price</th>
              <td>{{slcted.price}}</td>
            </tr>
            <tr>
              <th class="col-md-4 text-right">Seats Count</th>
              <td>{{slcted.seatsCount}}</td>
            </tr>
            <tr>
              <th class="col-md-4 text-right">Fuel</th>
              <td>{{slcted.fuel.name}}</td>
            </tr>
            <tr>
              <th class="col-md-4 text-right">Fuel Consumption</th>
              <td>{{slcted.fuelConsumption}}</td>
            </tr>
            <tr>
              <th class="text-right">Name English</th>
              <td>{{slcted.nameEn}}</td>
            </tr>
            <tr>
              <th class="text-right">Name German</th>
              <td>{{slcted.nameGe}}</td>
            </tr>
            <tr>
              <th class="text-right">Name France</th>
              <td>{{slcted.nameFr}}</td>
            </tr>
            <tr>
              <th class="text-right">Name Italian</th>
              <td>{{slcted.nameIt}}</td>
            </tr>
            <tr>
              <th class="text-right">Name Spain</th>
              <td>{{slcted.nameSp}}</td>
            </tr>
            <tr>
              <th class="text-right">Name Polish</th>
              <td>{{slcted.namePo}}</td>
            </tr>
            <tr>
              <th class="text-right">Name Russian</th>
              <td>{{slcted.nameRu}}</td>
            </tr>
            <tr>
              <th class="text-right">Description English</th>
              <td>{{slcted.descriptionEn}}</td>
            </tr>
            <tr>
              <th class="text-right">Description German</th>
              <td>{{slcted.descriptionGe}}</td>
            </tr>
            <tr>
              <th class="text-right">Description France</th>
              <td>{{slcted.descriptionFr}}</td>
            </tr>
            <tr>
              <th class="text-right">Description Italian</th>
              <td>{{slcted.descriptionIt}}</td>
            </tr>
            <tr>
              <th class="text-right">Description Spain</th>
              <td>{{slcted.descriptionSp}}</td>
            </tr>
            <tr>
              <th class="text-right">Description Polish</th>
              <td>{{slcted.descriptionPo}}</td>
            </tr>
            <tr>
              <th class="text-right">Description Russian</th>
              <td>{{slcted.descriptionRu}}</td>
            </tr>
          </table>
          <div class="form-group"><br/></div>
        </div>
      </div>
    </div>
    <div class="modal-footer">
    </div>
  </div>
</div>

<div class="modal fade bs-example-modal-lg not-printable" id="fuelModal" role="dialog" aria-labelledby="fuelModalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="fuelEdittModalLabel">Fuel Prices</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <form class="form-horizontal" name="ediFormName">
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Gasoline</label>
              <div class="col-sm-9">
                <input type="text" ng-model="fuel.gasoline" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Diesel</label>
              <div class="col-sm-9">
                <input type="text" ng-model="fuel.diesel" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10"></div>
            <div class="form-group col-sm-10"></div>
            <div class="form-group col-sm-12 text-center">
              <a class="btn btn-app" ng-click="saveFuel()">
                <i class="fa fa-save"></i> Save
              </a>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>


<div class="modal fade bs-example-modal-lg not-printable" id="editModal" role="dialog" aria-labelledby="editModalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="editModalLabel">Fill The Information</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <form class="form-horizontal" name="ediFormName">
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Fuel</label>
              <div class="col-sm-9">
                <select class="form-control" ng-model="request.fuelId"
                        required>
                  <option ng-repeat="v in fuels"
                          ng-selected="v.id === request.fuelId"
                          value="{{v.id}}">{{v.name}}
                  </option>
                </select>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Fuel Consumption</label>
              <div class="col-sm-9">
                <input type="text" ng-model="request.fuelConsumption" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Seats Count</label>
              <div class="col-sm-9">
                <input type="text" ng-model="request.seatsCount" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Price</label>
              <div class="col-sm-9">
                <input type="text" ng-model="request.price" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Name English</label>
              <div class="col-sm-9">
                <input type="text" ng-model="request.nameEn" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Name German</label>
              <div class="col-sm-9">
                <input type="text" ng-model="request.nameGe" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Name France</label>
              <div class="col-sm-9">
                <input type="text" ng-model="request.nameFr" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Name Italian</label>
              <div class="col-sm-9">
                <input type="text" ng-model="request.nameIt" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Name Spain</label>
              <div class="col-sm-9">
                <input type="text" ng-model="request.nameSp" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Name Polish</label>
              <div class="col-sm-9">
                <input type="text" ng-model="request.namePo" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Name russian</label>
              <div class="col-sm-9">
                <input type="text" ng-model="request.nameRu" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Description English</label>
              <div class="col-sm-9">
                <textarea rows="5" cols="10" ng-model="request.descriptionEn"
                          name="source" required class="form-control input-sm">
                </textarea>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Description German</label>
              <div class="col-sm-9">
                <textarea rows="5" cols="10" ng-model="request.descriptionGe"
                          name="source" required class="form-control input-sm">
                </textarea>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Description France</label>
              <div class="col-sm-9">
                <textarea rows="5" cols="10" ng-model="request.descriptionFr"
                          name="source" required class="form-control input-sm">
                </textarea>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Description Italian</label>
              <div class="col-sm-9">
                <textarea rows="5" cols="10" ng-model="request.descriptionIt"
                          name="source" required class="form-control input-sm">
                </textarea>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Description Spain</label>
              <div class="col-sm-9">
                <textarea rows="5" cols="10" ng-model="request.descriptionSp"
                          name="source" required class="form-control input-sm">
                </textarea>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Description Polish</label>
              <div class="col-sm-9">
                <textarea rows="5" cols="10" ng-model="request.descriptionPo"
                          name="source" required class="form-control input-sm">
                </textarea>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Description Russian</label>
              <div class="col-sm-9">
                <textarea rows="5" cols="10" ng-model="request.descriptionRu"
                          name="source" required class="form-control input-sm">
                </textarea>
              </div>
            </div>
            <div class="form-group col-sm-10"></div>
            <div class="form-group col-sm-10"></div>
            <div class="form-group col-sm-12 text-center">
              <a class="btn btn-app" ng-click="save()">
                <i class="fa fa-save"></i> Save
              </a>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="row not-printable">
  <div class="col-xs-12">
    <div class="box">
      <div class="box-header">
        <div class="col-md-4">
          <%--<c:if test="<%= isAdmin %>">--%>
          <div class="btn-group">
            <a ng-click="init()" class="btn btn-primary" data-toggle="modal" data-target="#editModal"><i
                    class="fa fa-plus" aria-hidden="true"></i> &nbsp;Add Transport</a>
            <a ng-click="loadFuelPrices()" class="btn btn-default" data-toggle="modal" data-target="#fuelModal"><i
                    class="fa fa-tachometer" aria-hidden="true"></i> &nbsp;Fuel Prices</a>
          </div>
          <%--</c:if>--%>
        </div>
        <div class="col-md-2 col-xs-offset-6">
          <select ng-change="rowNumbersChange()" class="pull-right form-control" ng-model="limit"
                  id="rowCountSelectId">
            <option value="10" selected>Show 10</option>
            <option value="15">15</option>
            <option value="30">30</option>
            <option value="50">50</option>
            <option value="100">100</option>
          </select>
        </div>
        <div class="row">
          <hr class="col-md-12"/>
        </div>
        <div class="col-md-12">
          <div id="filter-panel" class="filter-panel">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="form-group col-md-3">
                  <input type="text" class="form-control srch" ng-model="srchCase.id"
                         placeholder="ID">
                </div>
                <div class="form-group col-md-3">
                  <input type="text" class="form-control srch" ng-model="srchCase.nameEn">
                </div>
                <div class="form-group col-md-3">
                  <select class="form-control" ng-model="srchCase.fuelId"
                          ng-change="loadMainData()">
                    <option value="" selected="selected">Fuel</option>
                    <option ng-repeat="v in fuels" ng-selected="v === srchCase.fuelId"
                            value="{{v.id}}">{{v.name}}
                    </option>
                  </select>
                </div>
                <div class="form-group col-md-3">
                  <button class="btn btn-default col-md-11" ng-click="loadMainData()" id="srchBtnId">
                    <span class="fa fa-search"></span> &nbsp; &nbsp;Search &nbsp; &nbsp;
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <table class="table table-bordered table-hover">
            <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Fuel</th>
              <th>Price</th>
              <th class="col-md-2 text-center">Action</th>
            </tr>
            </thead>
            <tbody title="Double Click For Detailed Information">
            <tr ng-repeat="r in list" ng-dblclick="handleDoubleClick(r.id)">
              <td>{{r.id}}</td>
              <td>{{r.nameEn}}</td>
              <td>{{r.fuel.name}}</td>
              <td>{{r.price}}</td>
              <td class="text-center">
                <a ng-click="showDetails(r.id)" data-toggle="modal" title="Details"
                   data-target="#detailModal" class="btn btn-xs">
                  <i class="fa fa-sticky-note-o"></i>&nbsp; Details
                </a>&nbsp;&nbsp;
                <%--<c:if test="<%= isAdmin %>">--%>
                <a ng-click="edit(r.id)" data-toggle="modal" data-target="#editModal"
                class="btn btn-xs">
                <i class="fa fa-pencil"></i>&nbsp;Edit
                </a>&nbsp;&nbsp;
                <%--<a ng-click="remove(r.id)" class="btn btn-xs">--%>
                <%--<i class="fa fa-trash-o"></i>&nbsp;Remove--%>
                <%--</a>--%>
                <%--</c:if>--%>
              </td>
            </tr>
            </tbody>
          </table>
          <div class="panel-footer">
            <div class="row">
              <div class="col col-md-12">
                <ul class="pagination pull-right">
                  <li>
                    <a ng-click="handlePage(-1)" style="cursor: pointer;">«</a>
                  </li>
                  <li>
                    <a ng-click="handlePage(1)" style="cursor: pointer;">»</a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
<%@include file="footer.jsp" %>