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

    app.controller("angController", ['$scope', '$http', '$filter', '$window', 'Upload', '$timeout',
        function ($scope, $http, $filter, $window, Upload, $timeout) {
            $scope.start = 0;
            $scope.page = 1;
            $scope.limit = "10";
            $scope.request = {restaurantPackages: []};
            $scope.srchCase = {};
            $scope.packageRow = [1];
            $scope.mealCatRow = [1];
            $scope.mealCats = [];
            $scope.FbMealCatsTempl = ['Breakfast', 'Lunch', 'Picnick Lunch', 'Lunch with degustation', 'Dinner', 'Dinner Folk Show', 'Gala Dinner', 'Dinner At hotel'];


            $scope.loadMainData = function () {
                $('#loadingModal').modal('show');

                function getMainData(res) {
                    $scope.list = res.data;
                    $('#loadingModal').modal('hide');
                }

                ajaxCall($http, "restaurants/get-restaurants?start=" + $scope.start + "&limit=" + $scope.limit, angular.toJson($scope.srchCase), getMainData);
            }

            $scope.loadMainData();


            function getPlaces(res) {
                $scope.places = res.data;
            }

            ajaxCall($http, "places/get-places?start=0&limit=999999", {}, getPlaces);

            function getMealCatGroups(res) {
                $scope.mealCatGroups = res.data;
            }

            ajaxCall($http, "misc/get-mealcategories", {}, getMealCatGroups);

            $scope.remove = function (id) {
                if (confirm("Pleace confirm operation?")) {
                    if (id != undefined) {
                        function resFnc(res) {
                            if (res.errorCode == 0) {
                                successMsg('Operation Successfull');
                                $scope.loadMainData();
                            }
                        }

                        ajaxCall($http, "restaurants/delete?id=" + id, null, resFnc);
                    }
                }
            };

            $scope.edit = function (id) {
                if (id != undefined) {
                    var selected = $filter('filter')($scope.list, {id: id}, true);
                    $scope.slcted = selected[0];
                    $scope.request = selected[0];
                    $scope.loadCaseDetailsList($scope.slcted.id);
                }
            }

            $scope.mealCatsAction = function (id) {
                if (id != undefined) {
                    $scope.mealCats = [];
                    $scope.mealCatRow = [1];
                    var selected = $filter('filter')($scope.list, {id: id}, true);
                    $scope.slcted = selected[0];
                    $scope.request = selected[0];
                    $scope.loadRestMealCats();
                }
            }

            $scope.showDetails = function (id) {
                if (id != undefined) {
                    var selected = $filter('filter')($scope.list, {id: id}, true);
                    $scope.slcted = selected[0];
                    $scope.loadCaseDetailsList($scope.slcted.id);
                }
            };

            $scope.loadCaseDetailsList = function (id) {

                function getRestaurantPackages(res) {
                    $scope.packageRow = [];
                    $scope.request.restaurantPackages = [];
                    $scope.slcted.restPacks = [];

                    angular.forEach(res.data, function (v, k) {
                        $scope.packageRow.push(k + 1);
                        $scope.request.restaurantPackages.push(v);
                        $scope.slcted.restPacks.push(v);
                    });

                    if ($scope.request.restaurantPackages.length === 0) {
                        $scope.packageRow = [1];
                    }
                }

                ajaxCall($http, "restaurants/get-restaurant-packages?id=" + id, null, getRestaurantPackages);
            }

            $scope.loadRestMealCats = function () {
                function getData(res) {
                    $scope.mealCatRow = [];
                    angular.forEach(res.data, function (v, k) {
                        $scope.mealCatRow.push(k + 1);
                    });

                    $scope.mealCats = res.data;

                    if (res.data.length === 0) {
                        $scope.mealCatRow = [1];
                    }
                }

                ajaxCall($http, "restaurants/get-restaurant-meal-cats?id=" + $scope.slcted.id + "&group=", null, getData);
            };

            $scope.addDynamicRow = function () {
                var size = $scope.packageRow.length;
                $scope.packageRow.push(size + 1);
            };

            $scope.removeRestaurantPackage = function (index) {
                $scope.packageRow.splice(index, 1);
                if ($scope.request.restaurantPackages) {
                    $scope.request.restaurantPackages.splice(index, 1);
                }
            };

            $scope.addMealCatRow = function () {
                var size = $scope.mealCatRow.length;
                $scope.mealCatRow.push(size + 1);
                // makeAutoCompl(size+1);
            };

            $scope.removeMealCatRow = function (index) {
                var newDataList = [];
                var newRow = [];
                if ($scope.mealCats) {
                    var i = 1;
                    angular.forEach($scope.mealCats, function (v, k) {
                        if (k != index) {
                            newDataList.push(v);
                            newRow.push(i);
                            i++;
                        }
                    });
                    $scope.mealCats = newDataList;
                    $scope.mealCatRow = newRow;
                }
            };

            $scope.handleDoubleClick = function (id) {
                $scope.showDetails(id);
                $('#detailModal').modal('show');
            };

            $scope.init = function () {
                $scope.request = {restaurantPackages: []};
            };

            $scope.saveMealCat = function () {
                function resFunc(res) {
                    if (res.errorCode == 0) {
                        $scope.loadRestMealCats();
                        successMsg('Operation Successfull');
                        $scope.loadMainData();
                        closeModal('mealModal');
                    } else {
                        errorMsg('Operation Failed');
                    }
                }

                console.log(angular.toJson($scope.mealCats));
                ajaxCall($http, "restaurants/save-meal-categories?restaurantId=" + $scope.request.id, angular.toJson($scope.mealCats), resFunc);
            }

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

                $scope.req = {restaurantPackages: []};

                $scope.req.id = $scope.request.id;
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
                $scope.req.placeId = $scope.request.placeId;

                angular.forEach($scope.request.restaurantPackages, function (v) {
                    $scope.req.restaurantPackages.push(v);
                });

                console.log(angular.toJson($scope.req));
                ajaxCall($http, "restaurants/save", angular.toJson($scope.req), resFunc);
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

            $scope.setTemplate = function (temlate, inputId) {
                $("#" + inputId).val(temlate);
                $("#" + inputId).trigger('input');
                $("#" + inputId).trigger('change');
            };

        }]);
</script>

<div class="modal fade bs-example-modal-lg not-printable" id="mealModal" role="dialog" aria-labelledby="mealModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="mealModalLabel">Fill The Information</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form class="form-horizontal">
                        <div class="box">
                            <div class="box-header">
                                <a class="btn btn-default right" ng-click="addMealCatRow()">
                                    <i class="fa fa-plus"></i> Add Row
                                </a>
                            </div>
                            <div class="box-body">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>Group</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>#</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr ng-repeat="r in mealCatRow">
                                        <td class="col-md-3">
                                            <select class="form-control" ng-model="mealCats[r-1].group" required>
                                                <option ng-repeat="v in mealCatGroups"
                                                        ng-selected="v.name === mealCat.group"
                                                        value="{{v.name}}">{{v.name}}
                                                </option>
                                            </select>
                                        </td>
                                        <td class="col-md-5">

                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <button type="button" class="btn btn-default dropdown-toggle"
                                                            data-toggle="dropdown" aria-haspopup="true"
                                                            aria-expanded="false">Templates
                                                        <span class="caret"></span></button>
                                                    <ul class="dropdown-menu">
                                                        <li ng-repeat="t in FbMealCatsTempl">
                                                            <a href=""
                                                               ng-click="setTemplate(t, ('mealCatAutoCompl'+ r))">{{t}}
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <input type="text" id="mealCatAutoCompl{{r}}"
                                                       ng-model="mealCats[r-1].categoryName"
                                                       class="form-control ">
                                            </div>

                                        </td>
                                        <td class="col-md-3">
                                            <input type="text" ng-model="mealCats[r-1].price" required
                                                   class="form-control">
                                        </td>
                                        <td class="col-md-1">
                                            <a ng-click="removeMealCatRow($index)" ng-show="$index > 0"
                                               class="btn btn-xs">
                                                <i class="fa fa-trash-o"></i>
                                            </a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="form-group col-sm-10"></div>
                        <div class="form-group col-sm-10"></div>
                        <div class="form-group col-sm-12 text-center">
                            <a class="btn btn-app" ng-click="saveMealCat()">
                                <i class="fa fa-save"></i> Save
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

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
                            <th class="col-md-4 text-right">Place</th>
                            <td>{{slcted.place.nameEn}}</td>
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
                        <tr>
                            <th class="text-right">Packages</th>
                            <td>
                                <ul>
                                    <li ng-repeat="item in slcted.restPacks track by $index">
                                        {{item.name}} - {{item.price}}₾
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
                            <label class="control-label col-sm-3">Place</label>
                            <div class="col-sm-9">
                                <select class="form-control" ng-model="request.placeId" required>
                                    <option ng-repeat="v in places"
                                            ng-selected="v.id === request.placeId"
                                            value="{{v.id}}">{{v.nameEn}}
                                    </option>
                                </select>
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
                        <div class="form-group col-sm-10">
                            <label class="control-label col-sm-3">Packages</label>
                            <div class="col-sm-9">
                                <div class="form-group" ng-repeat="r in packageRow">
                                    <div class="col-sm-7">
                                        <input ng-model="request.restaurantPackages[r-1].name" type="text"
                                               placeholder="Package Desc" class="form-control input-sm"/>
                                    </div>
                                    <div class="col-sm-4">
                                        <input ng-model="request.restaurantPackages[r-1].price" type="text"
                                               placeholder="Price" class="form-control input-sm"/>
                                    </div>
                                    <div class="col-sm-1">
                                        <div class="col-md-1" ng-show="$index == 0">
                                            <a class="btn btn-sm row" style="vertical-align: bottom;">
                                                <span class="fa fa-plus" ng-click="addDynamicRow()"></span>
                                            </a>
                                        </div>
                                        <div class="col-md-1" ng-show="$index > 0">
                                            <a class="btn btn-sm row">
                                                <span class="fa fa-trash"
                                                      ng-click="removeRestaurantPackage($index)"></span>
                                            </a>
                                        </div>
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
                                <div class="form-group col-md-3">
                                    <input type="text" class="form-control srch" ng-model="srchCase.id"
                                           placeholder="ID">
                                </div>
                                <div class="form-group col-md-3">
                                    <input type="text" class="form-control srch" ng-model="srchCase.nameEn"
                                           placeholder="Name">
                                </div>
                                <div class="form-group col-md-3">
                                    <select class="form-control" ng-model="srchCase.placeId"
                                            ng-change="loadMainData()">
                                        <option value="" selected="selected">Place</option>
                                        <option ng-repeat="v in places" ng-selected="v === srchCase.placeId"
                                                value="{{v.id}}">{{v.nameEn}}
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
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Place</th>
                        <th class="col-md-3 text-center">Action</th>
                    </tr>
                    </thead>
                    <tbody title="Double Click For Detailed Information">
                    <tr ng-repeat="r in list" ng-dblclick="handleDoubleClick(r.id)">
                        <td>{{r.id}}</td>
                        <td>{{r.nameEn}}</td>
                        <td>{{r.place.nameEn}}</td>
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
                            <a ng-click="showDetails(r.id)" data-toggle="modal" data-target="#imageModal"
                               class="btn btn-xs">
                                <i class="fa fa-file-image-o"></i>&nbsp;Images
                            </a>&nbsp;&nbsp;
                            <a ng-click="remove(r.id)" class="btn btn-xs">
                                <i class="fa fa-trash-o"></i>&nbsp;Remove
                            </a>
                            <a ng-click="mealCatsAction(r.id)" data-toggle="modal" data-target="#mealModal"
                               class="btn btn-xs">
                                <i class="fa fa-cutlery"></i>&nbsp;Meal Categories
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