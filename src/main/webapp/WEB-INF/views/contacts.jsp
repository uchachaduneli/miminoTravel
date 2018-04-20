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

    $(document).ready(function () {

        $('input[name="startdate"]').datepicker({
            format: "dd/mm/yyyy",
            autoclose: true,
            language: 'ka'
        }).on('changeDate', function (ev) {
//            $("#caseStartDateInput").val($("#caseStartDateInput").val());
        });

        $('input[name="enddate"]').datepicker({
            format: "dd/mm/yyyy",
            autoclose: true,
            language: 'ka'
        }).on('changeDate', function (ev) {
//            $("#monthSeterInputId").val($("#srch_datepicker").val());
        });

        $('.srch').keypress(function (e) {
            var key = e.which;
            if (key == 13) {
                $('#srchBtnId').click();
                return false;
            }
        });

    });

    app.controller("angController", function ($scope, $http, $filter) {
        $scope.start = 0;
        $scope.page = 1;
        $scope.limit = "10";
        $scope.request = {types: [], categories: [], statusHistory: []};
        $scope.srchCase = {};
//        $scope.request.docs = [];

        $scope.loadMainData = function () {
            function getMainData(res) {
                $scope.list = res.data;
            }

            ajaxCall($http, "contact/get-contacts?start=" + $scope.start + "&limit=" + $scope.limit, angular.toJson($scope.srchCase), getMainData);
        }

        $scope.loadMainData();

        function getCountries(res) {
            $scope.countries = res.data;
        }

        ajaxCall($http, "misc/get-countries", null, getCountries);

        function getTypes(res) {
            $scope.types = res.data;
        }

        ajaxCall($http, "contact/get-types", null, getTypes);

        function getCategories(res) {
            $scope.categories = res.data;
        }

        ajaxCall($http, "contact/get-categories", null, getCategories);

        function getStatuses(res) {
            $scope.statuses = res.data;
        }

        ajaxCall($http, "contact/get-statuses", null, getStatuses);

        function getRanks(res) {
            $scope.ranks = res.data;
        }

        ajaxCall($http, "contact/get-ranks", null, getRanks);

        $scope.remove = function (id) {
            if (confirm("Pleace confirm operation?")) {
                if (id != undefined) {
                    function resFnc(res) {
                        if (res.errorCode == 0) {
                            successMsg('Operation Successfull');
                            $scope.loadMainData();
                        }
                    }

                    ajaxCall($http, "contact/delete-contact?id=" + id, null, resFnc);
                }
            }
        };

        $scope.edit = function (id) {
            if (id != undefined) {
                var selected = $filter('filter')($scope.list, {id: id}, true);
                $scope.slcted = selected[0];
                $scope.loadContactDetailsList($scope.slcted.id);
            }
        }

        $scope.showDetails = function (id) {
            if (id != undefined) {
                var selected = $filter('filter')($scope.list, {id: id}, true);
                $scope.slcted = selected[0];

                $scope.loadContactDetailsList($scope.slcted.id);
            }
        };

        $scope.loadContactDetailsList = function (id) {
            function getContactTypes(res) {
                $scope.slcted.contactTypes = res.data;
                angular.forEach($scope.slcted.contactTypes, function (v) {
                    $scope.request.types.push(v.type.id);
                });
            }

            ajaxCall($http, "contact/get-contact-types?id=" + id, null, getContactTypes);

            function getContactCategories(res) {
                $scope.slcted.contactCategories = res.data;
                angular.forEach($scope.slcted.contactCategories, function (v) {
                    $scope.request.categories.push(v.category.id);
                });
            }

            ajaxCall($http, "contact/get-contact-categories?id=" + id, null, getContactCategories);

            function getContactStatusHistory(res) {
                $scope.slcted.contactStatusHistory = res.data;
                angular.forEach($scope.slcted.contactStatusHistory, function (v) {
                    $scope.request.statusHistory.push(v.status.id);
                });
            }

            ajaxCall($http, "contact/get-status-history?id=" + id, null, getContactStatusHistory);
        }

        $scope.handleDoubleClick = function (id) {
            $scope.showDetails(id);
            $('#detailModal').modal('show');
        };

        $scope.init = function () {
            $scope.request = {types: [], categories: [], statusHistory: []};
            $('#uploadDocNameInput').val();
        };

        $scope.isChecked = function (id, matches) {
            var isChecked = false;
            angular.forEach(matches, function (match) {
                if (match === id) {
                    isChecked = true;
                }
            });
            return isChecked;
        }
//
//        $scope.save = function () {
//
//            if (!caseRequredFields($scope.ediFormName)) {
//                return;
//            }
//
//            function resFunc(res) {
//                if (res.errorCode == 0) {
//                    successMsg('ოპერაცია დასრულდა წარმატებით');
//                    $scope.loadMainData();
//                    closeModal('editModal');
//                } else {
//                    errorMsg('ოპერაცია არ სრულდება! გადაამოწმეთ ველების მართებულობა');
//                }
//            }
//
//            if ($scope.request.caseStartDate != undefined && $scope.request.caseStartDate.includes('/')) {
//                $scope.request.caseStartDate = $scope.request.caseStartDate.split(/\//).reverse().join('-')
//            }
//            if ($scope.request.caseEndDate != undefined && $scope.request.caseEndDate.includes('/')) {
//                $scope.request.caseEndDate = $scope.request.caseEndDate.split(/\//).reverse().join('-')
//            }
//            ajaxCall($http, "cases/save-case", angular.toJson($scope.request), resFunc);
//        };
//

        $scope.handlePage = function (h) {
            if (parseInt(h) >= 0) {
                $scope.page += 1;
                $scope.start = $scope.page * $scope.limit;
            } else {
                $scope.page -= 1;
                $scope.start = ($scope.page * $scope.limit) < 0 ? 0 : ($scope.page * $scope.limit);
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
                            <th class="text-right">Name</th>
                            <td>{{slcted.name}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Info</th>
                            <td>{{slcted.info}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Last Activity</th>
                            <td>{{slcted.lastActivity}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Activity</th>
                            <td>{{slcted.activity}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Next Activity</th>
                            <td>{{slcted.nextActivity}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Phone</th>
                            <td>{{slcted.phone}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Email</th>
                            <td>{{slcted.email}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Website</th>
                            <td>{{slcted.website}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Country</th>
                            <td>{{slcted.country.name}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">City</th>
                            <td>{{slcted.city}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Rank</th>
                            <td>{{slcted.rank.name}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">User</th>
                            <td>{{slcted.user.userDesc}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Source</th>
                            <td>{{slcted.source}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Record Created</th>
                            <td>{{slcted.createDate}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Types</th>
                            <td>
                                <ul>
                                    <li ng-repeat="k in slcted.contactTypes">
                                        {{k.type.name}}
                                    </li>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <th class="text-right">Categories</th>
                            <td>
                                <ul>
                                    <li ng-repeat="v in slcted.contactCategories">
                                        {{v.category.name}}
                                    </li>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <th class="text-right">Status History</th>
                            <td>
                                <ul>
                                    <li ng-repeat="item in slcted.contactStatusHistory">
                                        {{item.status.name}}
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
                <h4 class="modal-title" id="editModalLabel">Fill The Infromation</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form class="form-horizontal" name="ediFormName">
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Name</label>
                            <div class="col-sm-9">
                                <input type="text" ng-model="request.name" name="name" required
                                       class="form-control input-sm">
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Contact Person</label>
                            <div class="col-sm-9">
                                <input type="text" ng-model="request.contactPerson" name="contactPerson" required
                                       class="form-control input-sm">
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Info</label>
                            <div class="col-sm-9">
                                <input type="text" ng-model="request.info" name="info" required
                                       class="form-control input-sm">
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Next Activity</label>
                            <div class="col-sm-9">
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input type="text" name="nextActivity" ng-model="request.nextActivity"
                                           id="caseStartDateInput" required
                                           class="form-control pull-right">
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Activity</label>
                            <div class="col-sm-9">
                                <input ng-model="request.activity" type="text" name="activity" required
                                       class="form-control input-sm"/>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Phone</label>
                            <div class="col-sm-9">
                                <input ng-model="request.phone" type="text" name="phone" required
                                       class="form-control input-sm"/>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Website</label>
                            <div class="col-sm-9">
                                <input ng-model="request.website" type="text" name="website" required
                                       class="form-control input-sm"/>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Country</label>
                            <div class="col-sm-9">
                                <select class="form-control" ng-model="request.countryId" name="countryId"
                                        required>
                                    <option ng-repeat="v in countries"
                                            ng-selected="v.id === request.country.id"
                                            value="{{v.id}}">{{v.name}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">City</label>
                            <div class="col-sm-9">
                                <input ng-model="request.city" type="text" name="city" required
                                       class="form-control input-sm"/>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Rank</label>
                            <div class="col-sm-9">
                                <select class="form-control" ng-model="request.rankId" name="rankId"
                                        required>
                                    <option ng-repeat="v in ranks"
                                            ng-selected="v.id === request.rank.id"
                                            value="{{v.id}}">{{v.name}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Types</label>
                            <div class="col-sm-9">
                                <label ng-repeat="t in types">
                                    <input type="checkbox" ng-model="request.types" checklist-value="t.id"
                                           ng-checked="isChecked(t.id,request.types)"> {{t.name}}
                                </label>
                                <select class="form-control" ng-model="request.rankId" name="rankId"
                                        required>
                                    <option ng-repeat="v in ranks"
                                            ng-selected="v.id === request.rank.id"
                                            value="{{v.id}}">{{v.name}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Source</label>
                            <div class="col-sm-9">
                                <textarea rows="5" cols="10" ng-model="request.source"
                                          name="source" required
                                          class="form-control input-sm"></textarea>
                            </div>
                        </div>
                        <div class="form-group col-sm-10"></div>
                        <div class="form-group col-sm-10"></div>
                        <div class="form-group col-sm-12 text-center">
                            <a class="btn btn-app" ng-click="save()">
                                <i class="fa fa-save"></i> შენახვა
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
                    <c:if test="<%= isAdmin %>">
                        <button type="button" class="btn btn-block btn-primary btn-md" ng-click="init()"
                                data-toggle="modal" disabled="true"
                                data-target="#editModal">
                            <i class="fa fa-plus" aria-hidden="true"></i> &nbsp;
                            Add
                        </button>
                    </c:if>
                </div>
                <div class="col-md-2 col-xs-offset-8">
                    <select ng-change="loadMainData()" class="pull-right form-control" ng-model="limit"
                            id="rowCountSelectId">
                        <option value="10" selected>Whow 10</option>
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
                                    <select class="form-control" ng-model="srchCase.judgeId" ng-change="loadMainData()">
                                        <option value="" selected="selected">ქალაქი</option>
                                        <option ng-repeat="v in cities" ng-selected="v.id === srchCase.cityId"
                                                value="{{v.id}}">{{v.name}}
                                        </option>
                                    </select>
                                </div>
                                <div class="form-group col-md-2">
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
                            <th>Email</th>
                            <th>Phone</th>
                            <th>NextActivity</th>
                            <th>activity</th>
                            <th class="col-md-2 text-center">Action</th>
                        </tr>
                        </thead>
                        <tbody title="Double Click For Detailed Information">
                        <tr ng-repeat="r in list" ng-dblclick="handleDoubleClick(r.id)">
                            <td>{{r.id}}</td>
                            <td>{{r.name}}</td>
                            <td>{{r.email}}</td>
                            <td>{{r.phone}}</td>
                            <td>{{r.nextActivity}}</td>
                            <td>{{r.activity}}</td>
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
                                        <a ng-click="handlePage(-1)">«</a>
                                    </li>
                                    <li>
                                        <a ng-click="handlePage(1)" ng>»</a>
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