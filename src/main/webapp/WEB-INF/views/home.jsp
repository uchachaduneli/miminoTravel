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
        $scope.request = {docs: []};
        $scope.srchCase = {};
//        $scope.request.docs = [];

//        $scope.loadMainData = function () {
//            function getMainData(res) {
//                $scope.list = res.data.list;
//            }
//
//            ajaxCall($http, "cases/get-cases?start=" + $scope.start + "&limit=" + $scope.limit, angular.toJson($scope.srchCase), getMainData);
//        }
//
//        $scope.loadMainData();
//
//        function getlitigationsubjects(res) {
//            $scope.litigationsubjects = res.data;
//        }
//
//        ajaxCall($http, "litigationsubjects/get-litigationsubjects", null, getlitigationsubjects);
//
        function getCities(res) {
            $scope.cities = res.data;
        }

        ajaxCall($http, "misc/get-cities", null, getCities);
//
//        function getcourtInstances(res) {
//            $scope.courtInstances = res.data;
//        }
//
//        ajaxCall($http, "courtInstances/get-courtinstances", null, getcourtInstances);
//
//        function getendresults(res) {
//            $scope.endresults = res.data;
//        }
//
//        ajaxCall($http, "endresults/get-endresults", null, getendresults);
//
//        function getcourts(res) {
//            $scope.courts = res.data;
//        }
//
//        ajaxCall($http, "courts/get-courts", null, getcourts);
//
//        function getstatuses(res) {
//            $scope.statuses = res.data;
//        }
//
//        ajaxCall($http, "cases/get-status", null, getstatuses);
//
//        function getUsers(res) {
//            $scope.users = res.data;
//        }
//
//        ajaxCall($http, "users/get-users", null, getUsers);
//
//        function getBoards(res) {
//            $scope.boards = res.data;
//        }
//
//        ajaxCall($http, "boards/get-boards", null, getBoards);
//
//        $scope.remove = function (id) {
//            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
//                if (id != undefined) {
//                    function resFnc(res) {
//                        if (res.errorCode == 0) {
//                            successMsg('ოპერაცია დასრულდა წარმატებით');
//                            $scope.loadMainData();
//                        }
//                    }
//
//                    ajaxCall($http, "cases/delete-case?id=" + id, null, resFnc);
//                }
//            }
//        };
//
//        $scope.showDetails = function (id) {
//            if (id != undefined) {
//                var selected = $filter('filter')($scope.list, {caseId: id}, true);
//                $scope.slcted = selected[0];
//
//                function rsFnc(res) {
//                    $scope.caseInstances = res.data;
//                }
//
//                ajaxCall($http, "cases/get-instance-history?id=" + $scope.slcted.caseId + "&number=" + $scope.slcted.groupId, null, rsFnc);
//
//                $scope.loadCaseDocNames($scope.slcted.caseId);
//            }
//        };
//
//        $scope.loadCaseDocNames = function (caseId) {
//            function getDocNames(res) {
//                $scope.request.docs = res.data;
//            }
//
//            ajaxCall($http, "cases/get-doc-names?caseId=" + caseId, null, getDocNames);
//        }
//
//        $scope.handleDoubleClick = function (id) {
//            $scope.showDetails(id);
//            $('#detailModal').modal('show');
//        };
//
//        $scope.removeDoc = function (docname) {
////            var selected = $filter('filter')($scope.request.docs, {name: docname}, true);
//            var index = $scope.request.docs.indexOf(docname);
//            $scope.request.docs.splice(index, 1);
//        }
//        $scope.edit = function (id) {
//            $('#uploadDocNameInput').val();
//            if (id != undefined) {
//                var selected = $filter('filter')($scope.list, {caseId: id}, true);
//                $scope.request = selected[0];
//                $scope.loadCaseDocNames($scope.request.caseId);
////                $scope.request.judgeId = parseInt($scope.request.judgeId);
//            }
//        };
//
//        $scope.init = function () {
//            $scope.request = {docs: []};
//            $('#uploadDocNameInput').val();
//        };
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
//        $scope.chooseInstance = function (v) {
//            $scope.slcted = v;
//        }
//
//        $scope.handlePage = function (h) {
//            if (parseInt(h) >= 0) {
//                $scope.page += 1;
//                $scope.start = $scope.page * $scope.limit;
//            } else {
//                $scope.page -= 1;
//                $scope.start = ($scope.page * $scope.limit) < 0 ? 0 : ($scope.page * $scope.limit);
//            }
//            $scope.loadMainData();
//        }

        $scope.addDocument = function () {
            $('#uploadDocNameInput').val($('#documentId')[0].files[0].name);
            var oMyForm = new FormData();
            oMyForm.append("file", $('#documentId')[0].files[0]);
            $.ajax({
                url: 'cases/add-doc',
                data: oMyForm,
                dataType: 'text',
                processData: false,
                contentType: false,
                type: 'POST',
                success: function (data) {
                    if ($scope.request.docs === undefined) {
                        $scope.request.docs = [];
                    }
                    $scope.request.docs.push({name: JSON.parse(data).data});
                    successMsg('ატვირტვა დასრულდა წარმატებით, შეინახეთ ინფორმაცია');
                    $scope.$apply();
                    $('#uploadDocNameInput').val('');
                    $('#documentId').val('');
                },
                error: function (data, status, headers, config) {
                    console.log(data);
                    console.log(status);
                    console.log(headers);
                    console.log(config);
                }
            });
        };

    });
</script>

<div class="modal fade bs-example-modal-lg" id="detailModal" tabindex="-1" role="dialog"
     aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="detailModalLabel">დეტალური ინფორმაცია</h4>
            </div>
            <div class="modal-body">
                <ul class="nav nav-tabs col-md-12">
                    <li ng-repeat="v in caseInstances" class="nav-item {{v.caseId === slcted.caseId ? 'active':''}}"
                        style="cursor:pointer;">
                        <a class="nav-link"
                           ng-click="chooseInstance(v)">{{$index + 1}}. &nbsp;
                            {{v.courtInstanceName}}</a>
                    </li>
                </ul>
                <div class="row" id="printable">
                    <table class="table table-striped">
                        <tr colspan="2">
                            <button onclick="window.print()" id="prntBtnId" class="pull-right btn btn-default"
                                    style="margin-right: 15px;"><i class="fa fa-print"
                                                                   aria-hidden="true">
                                &nbsp; ბეჭდვა</i></button>
                        </tr>
                        <tr>
                            <th class="col-md-4 text-right">ID</th>
                            <td>{{slcted.caseId}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">დასახელება</th>
                            <td>{{slcted.name}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">საიდენტიფიკაციო N</th>
                            <td>{{slcted.number}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">სასამართლო</th>
                            <td>{{slcted.courtName}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">სამინისტროს სტატუსი</th>
                            <td>{{slcted.ministryStatus === 1 ? 'მოპასუხე': slcted.ministryStatus === 2 ? 'მესამე პირი'
                                :''}}
                            </td>
                        </tr>
                        <tr>
                            <th class="text-right">კოლეგია</th>
                            <td>{{slcted.boardName}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">მოსამართლე</th>
                            <td>{{slcted.judgeName}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">თანაშემწე</th>
                            <td>{{slcted.judgeAssistant}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">თანაშემწის ტელეფონი</th>
                            <td>{{slcted.judgeAssistantPhone}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">საქმის დაწყების დრო</th>
                            <td>{{slcted.caseStartDate}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">საქმის დამთავრების დრო</th>
                            <td>{{slcted.caseEndDate}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">დავის საგანი</th>
                            <td>{{slcted.litigationSubjectName}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">დავის საგნის ღირებულება</th>
                            <td>{{slcted.litigationPrice}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">დავის შინაარსი</th>
                            <td>{{slcted.litigationDescription}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">დამთავრების შედეგი</th>
                            <td>{{slcted.endResultName}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">თანამშრომელი</th>
                            <td>{{slcted.addUserName}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">საქმის სტატუსი</th>
                            <td>{{slcted.statusName}}</td>
                        </tr>
                        <tr>
                            <th class="text-right">დოკუმენტები</th>
                            <td>
                                <ul>
                                    <li ng-repeat="item in request.docs"><a
                                            href="cases/get-doc?name={{item.name.split('.')[0]}}" target="_blank">{{item.name}}</a>
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
                <h4 class="modal-title" id="editModalLabel">შეიტანეთ ინფორმაცია</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form class="form-horizontal" name="ediFormName">
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">დასახელება (*)</label>
                            <div class="col-sm-9">
                                <input type="text" ng-model="request.name" name="name" required
                                       class="form-control input-sm">
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">საქმის # (*)</label>
                            <div class="col-sm-9">
                                <input type="text" ng-model="request.number" name="number" required
                                       class="form-control input-sm">
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">კოლეგია (*)</label>
                            <div class="col-sm-9">
                                <select class="form-control" ng-model="request.boardId" name="boardId" required>
                                    <option ng-repeat="v in boards" ng-selected="v.boardId === request.boardId"
                                            value="{{v.boardId}}">{{v.name}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">მოსამართლე (*)</label>
                            <div class="col-sm-9">
                                <select class="form-control" ng-model="request.judgeId" name="judgeId" required>
                                    <option ng-repeat="v in judges" ng-selected="v.judgeId === request.judgeId"
                                            value="{{v.judgeId}}">{{v.name}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">საქმის დაწყების თარიღი (*)</label>
                            <div class="col-sm-9">
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input type="text" name="startdate" ng-model="request.caseStartDate"
                                           id="caseStartDateInput" required
                                           class="form-control pull-right">
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">საქმის დასრულების თარიღი</label>
                            <div class="col-sm-9">
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input type="text" name="enddate" ng-model="request.caseEndDate"
                                           id="caseEndDateInput"
                                           class="form-control pull-right">
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">დავის საგანი (*)</label>
                            <div class="col-sm-9">
                                <select class="form-control" ng-model="request.litigationSubjectId"
                                        name="litigationSubjectId" required>
                                    <option ng-repeat="v in litigationsubjects"
                                            ng-selected="v.litigationSubjectId === request.litigationSubjectId"
                                            value="{{v.litigationSubjectId}}">
                                        {{v.name}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">დავის საგნის ღირებულება (*)</label>
                            <div class="col-sm-9">
                                <input ng-model="request.litigationPrice" type="number" name="litigationPrice" required
                                       class="form-control input-sm"/>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">დავის შინაარსი (*)</label>
                            <div class="col-sm-9">
                                <textarea rows="5" cols="10" ng-model="request.litigationDescription"
                                          name="litigationDescription" required
                                          class="form-control input-sm"></textarea>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">სასამართლო ინსტანცია (*)</label>
                            <div class="col-sm-9">
                                <select class="form-control" ng-model="request.courtInstanceId" name="courtInstanceId"
                                        required>
                                    <option ng-repeat="v in courtInstances"
                                            ng-selected="v.instanceId === request.courtInstanceId"
                                            value="{{v.instanceId}}">{{v.name}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">საქმის დამთავრების შედეგი (*)</label>
                            <div class="col-sm-9">
                                <select class="form-control" ng-model="request.endResultId" name="endResultId" required>
                                    <option ng-repeat="v in endresults"
                                            ng-selected="request.endResultId === v.endResultId"
                                            value="{{v.endResultId}}">{{v.name}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">სასამართლო (*)</label>
                            <div class="col-sm-9">
                                <select class="form-control" ng-model="request.courtId" name="courtId" required>
                                    <option ng-repeat="v in courts" ng-selected="v.courtId === request.courtId"
                                            value="{{v.courtId}}">{{v.name}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">სამინისტროს სტატუსი (*)</label>
                            <div class="col-sm-9">
                                <select class="form-control" ng-model="request.ministryStatus" name="ministryStatus"
                                        required>
                                    <option ng-selected="1 === request.ministryStatus" value="{{1}}">მოპასუხე
                                    </option>
                                    <option ng-selected="2 === request.ministryStatus" value="{{2}}">მესამე პირი
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">მესამე პირები</label>
                            <div class="col-sm-9">
                                <textarea rows="5" cols="10" ng-model="request.thirdPersons"
                                          name="thirdPersons" required
                                          class="form-control input-sm"></textarea>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">საქმის სტატუსი (*)</label>
                            <div class="col-sm-9">
                                <select class="form-control" ng-model="request.statusId" name="statusId" required>
                                    <option ng-repeat="v in statuses" ng-selected="v.statusId === request.statusId"
                                            value="{{v.statusId}}">{{v.name}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">შენიშვნა</label>
                            <div class="col-sm-9">
                                <textarea rows="5" cols="10" ng-model="request.note"
                                          class="form-control input-sm"></textarea>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">საქმის დოკუმენტები</label>
                            <div class="col-sm-9">
                                <div class="input-group input-file" name="Fichier1">
                                    <input name="upload" type="file" id="documentId"
                                           onchange="angular.element(this).scope().addDocument()"
                                           style="display: none;"/>
                                    <input type="text" id="uploadDocNameInput" class="form-control"
                                           onclick="$('#documentId').trigger('click');"
                                           placeholder='აირჩიეთ ფაილი...'/>
                                    <span class="input-group-btn">
        		                        <button class="btn btn-default btn-choose" type="button"
                                                onclick="$('#documentId').trigger('click');">არჩევა</button>
    		                        </span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-sm-10 ">
                            <label class="control-label col-sm-3">დოკუმენტები ({{request.docs.length}})</label>
                            <div class="col-sm-9">
                                <ul>
                                    <li class="col-md-12" ng-repeat="k in request.docs">{{k.name}}
                                        &nbsp;&nbsp;&nbsp;<span class="fa fa-trash-o" ng-click="removeDoc()"></span>
                                    </li>
                                </ul>
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
                            დამატება
                        </button>
                    </c:if>
                </div>
                <div class="col-md-2 col-xs-offset-8">
                    <select ng-change="loadMainData()" class="pull-right form-control" ng-model="limit"
                            id="rowCountSelectId">
                        <option value="10" selected>მაჩვენე 10</option>
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
                                <%--<div class="form-group col-md-1">--%>
                                <%--<input type="text" class="form-control srch" ng-model="srchCase.caseId"--%>
                                <%--placeholder="ID">--%>
                                <%--</div>--%>
                                <%--<div class="form-group col-md-2">--%>
                                <%--<input type="text" class="form-control srch" ng-model="srchCase.name"--%>
                                <%--placeholder="დასახელება">--%>
                                <%--</div>--%>
                                <%--<div class="form-group col-md-2">--%>
                                <%--<input type="text" class="form-control srch" ng-model="srchCase.number"--%>
                                <%--placeholder="საქმის #">--%>
                                <%--</div>--%>
                                <%--<div class="form-group col-md-2">--%>
                                <%--<input type="text" class="form-control srch"--%>
                                <%--ng-model="srchCase.judgeAssistant" placeholder="თანაშემწე">--%>
                                <%--</div>--%>
                                <%--<div class="form-group col-md-2">--%>
                                <%--<input type="text" class="form-control srch"--%>
                                <%--ng-model="srchCase.judgeAssistantPhone" placeholder="თანაშემწის ტელ.">--%>
                                <%--</div>--%>
                                <div class="form-group col-md-3">
                                    <div class="input-group">
                                        <div class="input-append">
                                            <input type="text" name="startdate" class="form-control srch"
                                                   placeholder="დან"
                                                   ng-model="srchCase.caseStartDateFrom" placeholder="">
                                        </div>
                                        <span class="input-group-addon">ჩატარების თარიღი</span>
                                        <div class="input-append">
                                            <input type="text" name="startdate" class="form-control srch"
                                                   placeholder="მდე"
                                                   ng-model="srchCase.caseStartDateTo" placeholder="">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group col-md-3">
                                    <div class="input-group">
                                        <div class="input-append">
                                            <input type="text" name="enddate" class="form-control srch"
                                                   placeholder="დან"
                                                   ng-model="srchCase.caseEndDateFrom" placeholder="">
                                        </div>
                                        <span class="input-group-addon">ჩამოფრენის დრო</span>
                                        <div class="input-append">
                                            <input type="text" name="enddate" class="form-control srch"
                                                   placeholder="მდე"
                                                   ng-model="srchCase.caseEndDateTo" placeholder="">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group col-md-3">
                                    <div class="input-group">
                                        <div class="input-append">
                                            <input type="text" name="enddate" class="form-control srch"
                                                   placeholder="დან"
                                                   ng-model="srchCase.caseEndDateFrom" placeholder="">
                                        </div>
                                        <span class="input-group-addon">გაფრენის დრო</span>
                                        <div class="input-append">
                                            <input type="text" name="enddate" class="form-control srch"
                                                   placeholder="მდე"
                                                   ng-model="srchCase.caseEndDateTo" placeholder="">
                                        </div>
                                    </div>
                                </div>
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
                                        <span class="fa fa-search"></span> &nbsp; &nbsp;ძებნა &nbsp; &nbsp;
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
                            <th>Contact Email</th>
                            <th>field 4</th>
                            <th>field 5</th>
                            <th>field 6</th>
                            <th class="col-md-2 text-center">Action</th>
                        </tr>
                        </thead>
                        <tbody title="დეტალური ინფორმაციისთვის დაკლიკეთ ორჯერ">
                        <tr ng-repeat="r in list" ng-dblclick="handleDoubleClick(r.caseId)">
                            <td>{{r.caseId}}</td>
                            <td>{{r.name}}</td>
                            <td>{{r.number}}</td>
                            <td>{{r.courtName}}</td>
                            <td>{{r.judgeName}}</td>
                            <td>{{r.caseStartDate}}</td>
                            <td class="text-center">
                                <a ng-click="showDetails(r.caseId)" data-toggle="modal" title="დეტალურად"
                                   data-target="#detailModal" class="btn btn-xs">
                                    <i class="fa fa-sticky-note-o"></i>&nbsp; დეტალურად
                                </a>&nbsp;&nbsp;
                                <c:if test="<%= isAdmin %>">
                                    <a ng-click="edit(r.caseId)" data-toggle="modal" data-target="#editModal"
                                       class="btn btn-xs">
                                        <i class="fa fa-pencil"></i>&nbsp;შეცვლა
                                    </a>&nbsp;&nbsp;
                                    <a ng-click="remove(r.caseId)" class="btn btn-xs">
                                        <i class="fa fa-trash-o"></i>&nbsp;წაშლა
                                    </a>
                                </c:if>
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