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

    app.controller("angController", ['$scope', '$http', '$filter', '$window', 'Upload', '$timeout', function ($scope, $http, $filter, $window, Upload, $timeout) {
        $scope.start = 0;
        $scope.page = 1;
        $scope.limit = "10";
        $scope.request = {type: 1};
        $scope.srchCase = {};
        $scope.priceRow = [1];

        $scope.loadMainData = function () {
            $('#loadingModal').modal('show');

            function getMainData(res) {
                $scope.list = res.data;
                $('#loadingModal').modal('hide');
            }

            ajaxCall($http, "guides/get-guides?start=" + $scope.start + "&limit=" + $scope.limit, angular.toJson($scope.srchCase), getMainData);
        }

        $scope.loadMainData();

        function getLanguagess(res) {
            $scope.languages = res.data;
        }

        ajaxCall($http, "misc/get-languages", null, getLanguagess);

        $scope.remove = function (id) {
            if (confirm("Pleace confirm operation?")) {
                if (id != undefined) {
                    function resFnc(res) {
                        if (res.errorCode == 0) {
                            successMsg('Operation Successfull');
                            $scope.loadMainData();
                        }
                    }

                    ajaxCall($http, "guides/delete?id=" + id, null, resFnc);
                }
            }
        };

        function getRegions(res) {
            $scope.regions = res.data;
        }

        ajaxCall($http, "misc/get-regions", null, getRegions);

        $scope.edit = function (id) {
            if (id != undefined) {
                $scope.selected = $filter('filter')($scope.list, {id: id}, true);
                $scope.slcted = $scope.selected[0];
                $scope.request = $scope.selected[0];

                $scope.priceRow = [];
                // $scope.request.prices = [];

                angular.forEach($scope.slcted.prices, function (v, k) {
                    $scope.priceRow.push(k + 1);
                });
                if ($scope.slcted.prices.length === 0) {
                    $scope.priceRow = [1];
                }
            }
        }

        $scope.showDetails = function (id) {
            if (id != undefined) {
                var selected = $filter('filter')($scope.list, {id: id}, true);
                $scope.slcted = selected[0];
            }
        };

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
            $scope.req.name = $scope.request.name;
            $scope.req.trackingPrice = $scope.request.trackingPrice;
            $scope.req.type = $scope.request.type;
            $scope.req.languageId = $scope.request.languageId;
            $scope.req.prices = [];

            angular.forEach($scope.request.prices, function (v) {
                $scope.req.prices.push({
                    'id': v.id == undefined ? 0 : v.id,
                    'guideId': $scope.req.id,
                    'from': v.from,
                    'to': v.to,
                    'amount': v.amount
                });
            });

            console.log(angular.toJson($scope.req));
            ajaxCall($http, "guides/save", angular.toJson($scope.req), resFunc);
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

        $scope.addPriceRow = function () {
            var size = $scope.priceRow.length;
            $scope.priceRow.push(size + 1);
        };

        $scope.removePriceRow = function (index) {
            $scope.priceRow.splice(index, 1);
            if ($scope.request.prices) {
                $scope.request.prices.splice(index, 1);
            }
        };

    }]);
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
                            <th class="text-right">Name</th>
                            <td>{{slcted.name}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Type</th>
                            <td>{{slcted.type == 1 ? 'Guide':'Guide-Driver'}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Traking Price</th>
                            <td>{{slcted.trackingPrice}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Price By Persons Count</th>
                            <td>
                                <ul>
                                    <li ng-repeat="item in slcted.prices">
                                        Persons: From {{item.from}} To {{item.to}} -> {{item.amount}}
                                    </li>
                                </ul>
                            </td>
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
                            <label class="control-label col-sm-3">Name</label>
                            <div class="col-sm-9">
                                <input type="text" ng-model="request.name" required
                                       class="form-control input-sm">
                            </div>
                        </div>
                        <div class="form-group col-sm-10">
                            <label class="control-label col-sm-3">Type</label>
                            <div class="col-xs-9 btn-group">
                                <div class="radio col-xs-6">
                                    <label><input type="radio" ng-model="request.type" value="1"
                                                  class="input-sm">Guide</label>&nbsp;
                                    <label><input type="radio" ng-model="request.type" value="2"
                                                  class="input-sm">Guide-Driver</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-sm-10">
                            <label class="control-label col-sm-3">Language</label>
                            <div class="col-xs-9 btn-group">
                                <select class="form-control" ng-model="request.languageId" required>
                                    <option ng-repeat="v in languages"
                                            ng-selected="v.id === request.languageId"
                                            value="{{v.id}}">{{v.name}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Tracking Price</label>
                            <div class="col-sm-9">
                                <input type="text" ng-model="request.trackingPrice" required
                                       class="form-control input-sm">
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Prices By Pers. Count</label>
                            <div class="col-sm-9">
                                <div class="form-group" ng-repeat="r in priceRow">
                                    <div class="col-sm-10" id="divId_{{r}}">
                                        <div class="col-sm-4">
                                            <input ng-model="request.prices[r - 1].from" type="number"
                                                   placeholder="Count From" class="form-control input-sm"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <input ng-model="request.prices[r - 1].to" type="number"
                                                   placeholder="To" class="form-control input-sm"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <input ng-model="request.prices[r - 1].amount" type="number"
                                                   placeholder="Price" class="form-control input-sm"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <a class="btn btn-sm row" ng-show="$index == 0" style="vertical-align: bottom;">
                                            <span class="fa fa-plus" ng-click="addPriceRow()"></span>
                                        </a>
                                        <a class="btn btn-sm row" ng-show="$index > 0">
                                                <span class="fa fa-trash"
                                                      ng-click="removePriceRow($index)"></span>
                                        </a>
                                    </div>
                                </div>
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
                <div class="col-md-2">
                    <%--<c:if test="<%= isAdmin %>">--%>
                    <button type="button" class="btn btn-block btn-primary btn-md" ng-click="init()"
                            data-toggle="modal" data-target="#editModal">
                        <i class="fa fa-plus" aria-hidden="true"></i> &nbsp;
                        Add
                    </button>
                    <%--</c:if>--%>
                </div>
                <div class="col-md-2 col-xs-offset-8">
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
                                <div class="form-group col-md-2">
                                    <input type="text" class="form-control srch" ng-model="srchCase.id"
                                           placeholder="ID">
                                </div>
                                <div class="form-group col-md-3">
                                    <input type="text" class="form-control srch" ng-model="srchCase.name"
                                           placeholder="Name">
                                </div>
                                <div class="form-group col-md-3">
                                    <select class="form-control" ng-model="srchCase.type"
                                            ng-change="loadMainData()">
                                        <option value="" selected="selected">Type</option>
                                        <option value="1" selected="selected">Guide</option>
                                        <option value="2" selected="selected">Guide-Driver</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-4">
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
                            <th>Language</th>
                            <th>Tracking Price</th>
                            <th class="col-md-3 text-center">Action</th>
                        </tr>
                        </thead>
                        <tbody title="Double Click For Detailed Information">
                        <tr ng-repeat="r in list" ng-dblclick="handleDoubleClick(r.id)">
                            <td>{{r.id}}</td>
                            <td>{{r.name}}</td>
                            <td>{{r.language.name}}</td>
                            <td>{{r.trackingPrice}}</td>
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
                                <a ng-click="remove(r.id)" class="btn btn-xs">
                                    <i class="fa fa-trash-o"></i>&nbsp;Remove
                                </a>
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