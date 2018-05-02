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

        $('input[name="nextActivity"]').datepicker({
            format: "dd/mm/yyyy",
            autoclose: true,
        }).on('changeDate', function (ev) {
            $("#caseStartDateInput").val($("#caseStartDateInput").val());
        });

        $('input[name="srchActivityStart"]').datepicker({
            format: "dd/mm/yyyy",
            autoclose: true,
        }).on('changeDate', function (ev) {
//            $("#monthSeterInputId").val($("#srch_datepicker").val());
        });

        $('input[name="srchActivityEnd"]').datepicker({
            format: "dd/mm/yyyy",
            autoclose: true,
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
        $scope.request = {countries: [], details: []};
        $scope.srchCase = {};
//        $scope.request.docs = [];

        $scope.loadMainData = function () {
            function getMainData(res) {
                $scope.list = res.data;
            }

            if ($scope.srchCase != undefined) {
                if ($scope.srchCase.nextActivity != undefined && $scope.srchCase.nextActivity.includes('/')) {
                    $scope.srchCase.nextActivity = $scope.srchCase.nextActivity.split(/\//).reverse().join('-')
                }
                if ($scope.srchCase.nextActivityTo != undefined && $scope.srchCase.nextActivityTo.includes('/')) {
                    $scope.srchCase.nextActivityTo = $scope.srchCase.nextActivityTo.split(/\//).reverse().join('-')
                }
            }

            ajaxCall($http, "requests/get-requests?start=" + $scope.start + "&limit=" + $scope.limit, angular.toJson($scope.srchCase), getMainData);
        }

        $scope.loadMainData();

        function getCountries(res) {
            $scope.countries = res.data;
        }

        ajaxCall($http, "misc/get-countries", null, getCountries);

        function getDetails(res) {
            $scope.details = res.data;
        }

        ajaxCall($http, "requests/get-details", null, getDetails);

        $scope.remove = function (id) {
            if (confirm("Pleace confirm operation?")) {
                if (id != undefined) {
                    function resFnc(res) {
                        if (res.errorCode == 0) {
                            successMsg('Operation Successfull');
                            $scope.loadMainData();
                        }
                    }

                    ajaxCall($http, "requests/delete?id=" + id, null, resFnc);
                }
            }
        };

        $scope.edit = function (id) {
            if (id != undefined) {
                var selected = $filter('filter')($scope.list, {id: id}, true);
                $scope.slcted = selected[0];
                $scope.request = selected[0];
                $scope.request.countries = [];
                $scope.request.details = [];
                $scope.loadCaseDetailsList($scope.request.id);
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
            function getCaseCountries(res) {
                $scope.slcted.countries = res.data;

                angular.forEach($scope.slcted.countries, function (v) {
                    $scope.request.countries.push(v);
                });
            }

            ajaxCall($http, "requests/get-requests-countries?id=" + id, null, getCaseCountries);

            function getCaseDetailes(res) {
                $scope.slcted.details = res.data;
                angular.forEach($scope.slcted.details, function (v) {
                    $scope.request.details.push(v);
                });
            }
        }

        $scope.handleDoubleClick = function (id) {
            $scope.showDetails(id);
            $('#detailModal').modal('show');
        };

        $scope.init = function () {
            $scope.request = {countries: [], details: []};
        };

        $scope.save = function () {

//            if (!caseRequredFields($scope.ediFormName)) {
//                return;
//            }

            function resFunc(res) {
                if (res.errorCode == 0) {
                    successMsg('Operation Successfull');
                    $scope.loadMainData();
                    closeModal('editModal');
                } else {
                    errorMsg('Operation Failed');
                }
            }

            if ($scope.request.tourStart != undefined && $scope.request.tourStart.includes('/')) {
                $scope.request.tourStart = $scope.request.tourStart.split(/\//).reverse().join('-')
            }
            if ($scope.request.tourEnd != undefined && $scope.request.tourEnd.includes('/')) {
                $scope.request.tourEnd = $scope.request.tourEnd.split(/\//).reverse().join('-')
            }
            if ($scope.request.arrivalTime != undefined && $scope.request.arrivalTime.includes('/')) {
                $scope.request.arrivalTime = $scope.request.arrivalTime.split(/\//).reverse().join('-')
            }
            if ($scope.request.leaveTime != undefined && $scope.request.leaveTime.includes('/')) {
                $scope.request.leaveTime = $scope.request.leaveTime.split(/\//).reverse().join('-')
            }

            $scope.req = {};
            $scope.req.id = $scope.request.id;
            $scope.req.contactEmail = $scope.request.contactEmail;
            $scope.req.combined = $scope.request.combined;
            $scope.req.daysCount = $scope.request.daysCount;
            $scope.req.nightsCount = $scope.request.nightsCount;
            $scope.req.touristsCount = $scope.request.touristsCount;
            $scope.req.touristsCountNote = $scope.request.touristsCountNote;
            $scope.req.arrivalCityId = $scope.request.arrivalCityId;
            $scope.req.leaveCityId = $scope.request.leaveCityId;
            $scope.req.tourType = $scope.request.tourType;
            $scope.req.guideDriver = $scope.request.guideDriver;
            $scope.req.guideLanguageId = $scope.request.guideLanguageId;
            $scope.req.hotelCategory = $scope.request.hotelCategory;
            $scope.req.mealCategoryId = $scope.request.mealCategoryId;
            $scope.req.entranceFees = $scope.request.entranceFees;
            $scope.req.currencyId = $scope.request.currencyId;
            $scope.req.comment = $scope.request.comment;
            $scope.req.budget = $scope.request.budget;
            $scope.req.packageCategoryId = $scope.request.packageCategoryId;

            console.log(angular.toJson($scope.req));
            ajaxCall($http, "requests/save", angular.toJson($scope.req), resFunc);
        };


        $scope.rowNumbersChange = function () {
            $scope.start = 0;
            $scope.loadMainData();
        }

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
                            <th class="text-right">Contact Email</th>
                            <td>{{slcted.contactEmail}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Combined</th>
                            <td>{{slcted.combined == 1 ? 'No':'Yes'}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Days Count</th>
                            <td>{{slcted.daysCount}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Nights Count</th>
                            <td>{{slcted.nightsCount}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Tourists Count</th>
                            <td>{{slcted.touristsCount}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Tourists Count Note</th>
                            <td>{{slcted.touristsCountNote}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Arrival City</th>
                            <td>{{slcted.arrivalCity.name}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Leave City</th>
                            <td>{{slcted.leaveCity.name}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Tour Type</th>
                            <td>{{slcted.tourType}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Guide-Driver/Just Guide</th>
                            <td>{{slcted.guideDriver ==1 ? 'Guide-Driver':'Just Guide'}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Guide Language</th>
                            <td>{{slcted.guideLanguage.name}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">hotelCategory</th>
                            <td>{{slcted.hotelCategory}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Meal Category</th>
                            <td>{{slcted.mealCategory.name}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Entrance Fees</th>
                            <td>{{slcted.entranceFees}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Currency</th>
                            <td>{{slcted.currency.name}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Comment</th>
                            <td>{{slcted.comment}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Package Category</th>
                            <td>{{slcted.packageCategory.name}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Budget</th>
                            <td>{{slcted.budget}}</td>
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
                            <label class="control-label col-sm-3">Cont. Person</label>
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
                            <label class="control-label col-sm-3">Email</label>
                            <div class="col-sm-9">
                                <input ng-model="request.email" type="text" name="email" required
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
                                <label ng-repeat="t in types" class="col-sm-6">
                                    <input type="checkbox" id="typechecks{{t.id}}"
                                           checklist-model="request.types" checklist-value="t.id">&nbsp; {{t.name}}
                                </label>
                                <hr class="col-sm-11" style="margin: 0 0 !important;">
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">Categories</label>
                            <div class="col-sm-9">
                                <label ng-repeat="t in categories" class="col-sm-6">
                                    <input type="checkbox" id="categorychecks{{t.id}}"
                                           checklist-model="request.categories" checklist-value="t.id">&nbsp; {{t.name}}
                                </label>
                                <hr class="col-sm-11" style="margin: 0 0 !important;">
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
                    <c:if test="<%= isAdmin %>">
                        <button type="button" class="btn btn-block btn-primary btn-md" ng-click="init()"
                                data-toggle="modal" data-target="#editModal">
                            <i class="fa fa-plus" aria-hidden="true"></i> &nbsp;
                            Add
                        </button>
                    </c:if>
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
                                    <input type="text" class="form-control srch" ng-model="srchCase.contactPerson"
                                           placeholder="Contact Person">
                                </div>
                                <div class="form-group col-md-2">
                                    <input type="text" class="form-control srch"
                                           ng-model="srchCase.phone" placeholder="Phone">
                                </div>
                                <div class="form-group col-md-2">
                                    <input type="text" class="form-control srch"
                                           ng-model="srchCase.email" placeholder="Email">
                                </div>
                                <div class="form-group col-md-2">
                                    <select class="form-control" ng-model="srchCase.countryId"
                                            ng-change="loadMainData()">
                                        <option value="" selected="selected">Country</option>
                                        <option ng-repeat="v in countries" ng-selected="v.id === srchCase.countryId"
                                                value="{{v.id}}">{{v.name}}
                                        </option>
                                    </select>
                                </div>
                                <div class="form-group col-md-2">
                                    <select class="form-control" ng-model="srchCase.rankId"
                                            ng-change="loadMainData()">
                                        <option value="" selected="selected">Rank</option>
                                        <option ng-repeat="v in ranks" ng-selected="v.id === srchCase.rankId"
                                                value="{{v.id}}">{{v.name}}
                                        </option>
                                    </select>
                                </div>
                                <div class="form-group col-md-2">
                                    <select class="form-control" ng-model="srchCase.userId"
                                            ng-change="loadMainData()">
                                        <option value="" selected="selected">User</option>
                                        <option ng-repeat="v in users" ng-selected="v.userId === srchCase.userId"
                                                value="{{v.userId}}">{{v.userName}}
                                        </option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <div class="input-group">
                                        <div class="input-append">
                                            <input type="text" name="srchActivityStart" class="form-control srch"
                                                   placeholder="From"
                                                   ng-model="srchCase.nextActivity" placeholder="">
                                        </div>
                                        <span class="input-group-addon">Next Activ</span>
                                        <div class="input-append">
                                            <input type="text" name="srchActivityEnd" class="form-control srch"
                                                   placeholder="To" ng-model="srchCase.nextActivityTo">
                                        </div>
                                    </div>
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