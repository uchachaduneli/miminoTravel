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
    $scope.srchCase = {};

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

      ajaxCall($http, "emails/get-emails?start=" + $scope.start + "&limit=" + $scope.limit, angular.toJson($scope.srchCase), getMainData);
    }

    $scope.loadMainData();

    function getUsers(res) {
      $scope.users = res.data;
    }

    ajaxCall($http, "users/get-users", null, getUsers);

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
              <th class="text-right">User</th>
              <td>{{slcted.user.userDesc}}</td>
            </tr>
            <tr>
              <th class="text-right">From</th>
              <td>{{slcted.from}}</td>
            </tr>
            <tr>
              <th class="text-right">To</th>
              <td>{{slcted.to}}</td>
            </tr>
            <tr>
              <th class="text-right">Subject</th>
              <td>{{slcted.subject}}</td>
            </tr>
            <tr>
              <th class="text-right">SendDate</th>
              <td>{{slcted.sendDate}}</td>
            </tr>
            <tr>
              <th class="text-right">ReceiveDate</th>
              <td>{{slcted.receiveDate}}</td>
            </tr>
            <tr>
              <th class="text-right">Content</th>
              <td>{{slcted.content}}</td>
            </tr>
            <tr>
              <th class="text-right">Attachments</th>
              <td>{{slcted.attachments}}</td>
            </tr>
            <tr>
              <th class="text-right">CreateDate</th>
              <td>{{slcted.insertDate}}</td>
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

<div class="row not-printable">
  <div class="col-xs-12">
    <div class="box">
      <div class="box-header">
        <div class="col-md-2">
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
              <th>From</th>
              <th>To</th>
              <th>Subject</th>
              <th>Attachments</th>
              <th>Crt. Date</th>
              <th class="col-md-2 text-center">Action</th>
            </tr>
            </thead>
            <tbody title="Double Click For Detailed Information">
            <tr ng-repeat="r in list" ng-dblclick="handleDoubleClick(r.id)">

              <td>{{r.id}}</td>
              <td>{{r.from}}</td>
              <td>{{r.to}}</td>
              <td>{{r.subject}}</td>
              <td>{{r.attachments.length > 0 ? 'YES('+r.attachments.length+')':'NO'}}</td>
              <td>{{r.insertDate}}</td>
              <td class="text-center">
                <a ng-click="showDetails(r.id)" data-toggle="modal" title="Details"
                   data-target="#detailModal" class="btn btn-xs">
                  <i class="fa fa-sticky-note-o"></i>&nbsp; Details
                </a>&nbsp;&nbsp;
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