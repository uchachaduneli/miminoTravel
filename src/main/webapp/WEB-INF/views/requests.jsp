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

    $('input[name="tourEndsDateInput"]').datepicker({
      format: "dd/mm/yyyy",
      autoclose: true,
    }).on('changeDate', function (ev) {
//            $("#caseStartDateInput").val($("#caseStartDateInput").val());
    });

    $('input[name="tourStartDateInput"]').datepicker({
      format: "dd/mm/yyyy",
      autoclose: true,
    }).on('changeDate', function (ev) {
//            $("#monthSeterInputId").val($("#srch_datepicker").val());
    });

    $('input[name="date"]').datepicker({
      format: "dd/mm/yyyy",
      autoclose: true,
    })

    $('input[name="datetime"]').datetimepicker({
      weekStart: 1,
      todayBtn: 1,
      autoclose: 1,
      todayHighlight: 1,
      startView: 2,
      forceParse: 0,
      showMeridian: 1,
      use24hours: 1
    }).on('changeDate', function (ev) {

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
    $scope.request = {countries: [], details: [], combined: 2};
    $scope.srchCase = {};
    $scope.countryRow = [1];
//        $scope.request.docs = [];

    $scope.loadMainData = function () {
      $('#loadingModal').modal('show');

      function getMainData(res) {
        $scope.list = res.data;
        $('#loadingModal').modal('hide');
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

    function getLanguagess(res) {
      $scope.languages = res.data;
    }

    ajaxCall($http, "misc/get-languages", null, getLanguagess);

    function getDetails(res) {
      $scope.details = res.data;
    }

    ajaxCall($http, "requests/get-details", null, getDetails);

    function getCities(res) {
      $scope.cities = res.data;
    }

    ajaxCall($http, "misc/get-cities", null, getCities);

    function getCurrencies(res) {
      $scope.currencies = res.data;
    }

    ajaxCall($http, "misc/get-currencies", null, getCurrencies);

    function getPackagecategories(res) {
      $scope.packageCategories = res.data;
    }

    ajaxCall($http, "misc/get-packagecategories", null, getPackagecategories);

    function getMealcategories(res) {
      $scope.mealCategories = res.data;
    }

    ajaxCall($http, "misc/get-mealcategories", null, getMealcategories);

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
        $scope.countryRow = [];
        $scope.request.combinedCountries = [];
        angular.forEach($scope.slcted.countries, function (v, k) {
          $scope.countryRow.push(k + 1);
          $scope.request.combinedCountries.push({
            'countryId': parseInt(v.country.id),
            'daysCount': v.daysCount,
            'note': v.note
          });
        });
        if ($scope.slcted.countries.length === 0) {
          $scope.countryRow = [1];
        }
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
      $scope.request = {countries: [], details: [], combined: 2};
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

      $scope.req = {combinedCountries: []};
      $scope.req.id = $scope.request.id;
      $scope.req.contactEmail = $scope.request.contactEmail;
      $scope.req.combined = $scope.request.combined;
      $scope.req.tourStart = $scope.request.tourStart;
      $scope.req.tourEnd = $scope.request.tourEnd;
      $scope.req.daysCount = $scope.request.daysCount;
      $scope.req.nightsCount = $scope.request.nightsCount;
      $scope.req.touristsCount = $scope.request.touristsCount;
      $scope.req.touristsCountNote = $scope.request.touristsCountNote;
      $scope.req.arrivalCityId = $scope.request.arrivalCityId;
      $scope.req.arrivalTime = $scope.request.arrivalTime + ':00';
      $scope.req.leaveCityId = $scope.request.leaveCityId;
      $scope.req.leaveTime = $scope.request.leaveTime + ':00';
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

      angular.forEach($scope.request.combinedCountries, function (v) {
        $scope.req.combinedCountries.push({
          'countryId': (parseInt(v.countryId)),
          'daysCount': v.daysCount,
          'note': v.note
        });
      });

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
        $scope.start = $scope.start + $scope.limit;
      } else {
        $scope.page -= 1;
        $scope.start = ($scope.start - $scope.limit) < 0 ? 0 : ($scope.start - $scope.limit);
      }
      $scope.loadMainData();
    }

    $scope.addCountryRow = function () {
      var size = $scope.countryRow.length;
      $scope.countryRow.push(size + 1);
//            $scope.combinedCountries[size + 1] = $scope.combinedCountries[1];
    };

    $scope.removePotentialLocation = function (index) {
      $scope.countryRow.splice(index, 1);
      if ($scope.request.combinedCountries) {
        $scope.request.combinedCountries.splice(index, 1);
      }
    };

    $scope.foundInList = function (id, list) {
      var found = false;
      angular.forEach(list, function (v) {
        if (id === v.countryId) {
          found = true;
        }
      });
      return found;
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
              <th class="text-right">Tour Starts</th>
              <td>{{slcted.tourStart}}</td>
            </tr>
            <tr>
              <th class="text-right">Tour Ends</th>
              <td>{{slcted.tourEnd}}</td>
            </tr>
            <tr>
              <th class="text-right">Combined</th>
              <td>{{slcted.combined == 1 ? 'Yes':'No'}}</td>
            </tr>
            <tr ng-if="slcted.combined == 1">
              <th class="text-right" style="vertical-align: middle;">Coutnries</th>
              <td>
                <table class="table table-hover col-sm-8">
                  <thead>
                  <tr>
                    <th>Country</th>
                    <th>Days Count</th>
                    <th>Note</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr ng-repeat="r in slcted.countries">
                    <td>{{r.country.name}}</td>
                    <td>{{r.daysCount}}</td>
                    <td>{{r.note}}</td>
                  </tbody>
                </table>
              </td>
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
              <th class="text-right">Arrival Time</th>
              <td>{{slcted.arrivalTime}}</td>
            </tr>
            <tr>
              <th class="text-right">Leave City</th>
              <td>{{slcted.leaveCity.name}}</td>
            </tr>
            <tr>
              <th class="text-right">Leave Time</th>
              <td>{{slcted.leaveTime}}</td>
            </tr>
            <tr>
              <th class="text-right">Tour Type</th>
              <td>{{slcted.tourType}}</td>
            </tr>
            <tr>
              <th class="text-right">Guide-Driver/Just Guide</th>
              <td>{{slcted.guideDriver == 2 ? 'Guide-Driver':(slcted.guideDriver == 1 ? 'Separated Guide /
                Driver':'')}}
              </td>
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
            <tr>
              <th class="text-right">Create Date</th>
              <td>{{slcted.createDate}}</td>
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
              <label class="control-label col-sm-3">Cont. Email</label>
              <div class="col-sm-9">
                <input type="text" ng-model="request.contactEmail" name="contactEmail" required
                       class="form-control input-sm">
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Tour Starts</label>
              <div class="col-sm-9">
                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="text" name="tourStartDateInput" ng-model="request.tourStart"
                         id="tourStartDateInput" required
                         class="form-control pull-right">
                </div>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Tour Ends</label>
              <div class="col-sm-9">
                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="text" name="tourEndsDateInput" ng-model="request.tourEnd"
                         id="tourEndsDateInput" required
                         class="form-control pull-right">
                </div>
              </div>
            </div>
            <div class="form-group col-sm-10">
              <label class="control-label col-sm-3">Combined</label>
              <div class="col-xs-9 btn-group">
                <div class="radio col-xs-6">
                  <label><input type="radio" ng-model="request.combined" value="1"
                                class="input-sm">Yes</label>&nbsp;
                  <label><input type="radio" ng-model="request.combined" value="2"
                                class="input-sm">No</label>
                </div>
              </div>
            </div>
            <div class="form-group col-sm-10 " ng-if="request.combined == 1">
              <label class="control-label col-sm-3">Choose Countries </label>
              <div class="col-sm-9">
                <div class="form-group" ng-repeat="r in countryRow">
                  <div class="col-sm-11" id="divId_{{r}}">
                    <div class="col-sm-6" id="dv_{{r}}">
                      <select id="combinedCountrySelect{{r}}" class="form-control input-sm"
                              ng-model="request.combinedCountries[r - 1].countryId">
                        <option ng-repeat="c in countries" value="{{c.id}}" ng-value="c.id"
                                ng-selected="c.id === request.combinedCountries[r - 1].countryId">
                          {{c.id}}. {{c.name}}
                        </option>
                      </select>
                    </div>
                    <div class="col-sm-6">
                      <input ng-model="request.combinedCountries[r - 1].daysCount" type="number"
                             placeholder="Days Count" name="activity" required
                             class="form-control input-sm"/>
                    </div>
                    <div class="col-sm-12">
                                        <textarea rows="3" cols="10" placeholder="Note"
                                                  ng-model="request.combinedCountries[r - 1].note"
                                                  class="form-control input-sm"></textarea>
                    </div>
                  </div>
                  <div class="col-sm-1">
                    <div class="col-md-1" ng-show="$index == 0">
                      <a class="btn btn-sm row" style="vertical-align: bottom;">
                        <span class="fa fa-plus" ng-click="addCountryRow()"></span>
                      </a>
                    </div>
                    <div class="col-md-1" ng-show="$index > 0">
                      <a class="btn btn-sm row">
                                                <span class="fa fa-trash"
                                                      ng-click="removePotentialLocation($index)"></span>
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Days Count</label>
              <div class="col-sm-9">
                <input ng-model="request.daysCount" type="number" name="activity" required
                       class="form-control input-sm"/>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Nights Count</label>
              <div class="col-sm-9">
                <input ng-model="request.nightsCount" type="number" name="phone" required
                       class="form-control input-sm"/>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Tourists Count</label>
              <div class="col-sm-9">
                <input ng-model="request.touristsCount" type="number" required
                       class="form-control input-sm"/>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Touris Count Note</label>
              <div class="col-sm-9">
                                <textarea rows="5" cols="10" ng-model="request.touristsCountNote"
                                          name="source" required
                                          class="form-control input-sm"></textarea>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Arrival City</label>
              <div class="col-sm-9">
                <select class="form-control" ng-model="request.arrivalCityId" required>
                  <option ng-repeat="v in cities"
                          ng-selected="v.id === request.arrivalCityId"
                          value="{{v.id}}">{{v.name}}
                  </option>
                </select>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Arrival Time</label>
              <div class="col-sm-9">
                <div class="input-group date col-md-5"
                     data-date-format="yyyy-mm-dd HH:ii p" data-link-field="dtp_input1">
                  <input class="form-control" size="16" type="text" ng-model="request.arrivalTime"
                         name="datetime">
                </div>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Leave City</label>
              <div class="col-sm-9">
                <select class="form-control" ng-model="request.leaveCityId" required>
                  <option ng-repeat="v in cities"
                          ng-selected="v.id === request.leaveCityId"
                          value="{{v.id}}">{{v.name}}
                  </option>
                </select>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Leave Time</label>
              <div class="col-sm-9">
                <div class="input-group date col-md-5"
                     data-date-format="yyyy-mm-dd HH:ii p" data-link-field="dtp_input2">
                  <input class="form-control" size="16" type="text" ng-model="request.leaveTime"
                         name="datetime">
                </div>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Tour Type</label>
              <div class="col-sm-9">
                                <textarea rows="5" cols="10" ng-model="request.tourType"
                                          name="source" required
                                          class="form-control input-sm"></textarea>
              </div>
            </div>
            <div class="form-group col-sm-10">
              <label class="control-label col-sm-3">Separately Guide and Driver or Guide-Driver</label>
              <div class="col-xs-9 btn-group">
                <div class="radio col-xs-6">
                  <label><input type="radio" ng-model="request.guideDriver" value="1"
                                class="input-sm">Guide</label>&nbsp;
                  <label><input type="radio" ng-model="request.guideDriver" value="2"
                                class="input-sm">Guide-Driver</label>
                </div>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Guide Language</label>
              <div class="col-sm-9">
                <select class="form-control" ng-model="request.guideLanguageId" required>
                  <option ng-repeat="v in languages"
                          ng-selected="v.id === request.guideLanguageId"
                          value="{{v.id}}">{{v.name}}
                  </option>
                </select>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Hotel category</label>
              <div class="col-sm-9">
                                <textarea rows="5" cols="10" ng-model="request.hotelCategory"
                                          name="source" required
                                          class="form-control input-sm"></textarea>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Meal Category</label>
              <div class="col-sm-9">
                <select class="form-control" ng-model="request.mealCategoryId" required>
                  <option ng-repeat="v in mealCategories"
                          ng-selected="v.id === request.mealCategoryId"
                          value="{{v.id}}">{{v.name}}
                  </option>
                </select>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Entrance Fees</label>
              <div class="col-sm-9">
                                <textarea rows="5" cols="10" ng-model="request.entranceFees"
                                          name="source" required
                                          class="form-control input-sm"></textarea>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Currency</label>
              <div class="col-sm-9">
                <select class="form-control" ng-model="request.currencyId" required>
                  <option ng-repeat="v in currencies"
                          ng-selected="v.id === request.currencyId"
                          value="{{v.id}}">{{v.name}}
                  </option>
                </select>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Comment</label>
              <div class="col-sm-9">
                                <textarea rows="5" cols="10" ng-model="request.comment"
                                          name="source" required
                                          class="form-control input-sm"></textarea>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Package Category</label>
              <div class="col-sm-9">
                <select class="form-control" ng-model="request.packageCategoryId" required>
                  <option ng-repeat="v in packageCategories"
                          ng-selected="v.id === request.packageCategoryId"
                          value="{{v.id}}">{{v.name}}
                  </option>
                </select>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Budget</label>
              <div class="col-sm-9">
                <input ng-model="request.budget" type="number" required
                       class="form-control input-sm"/>
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
                  <input type="text" class="form-control srch" ng-model="srchCase.contactEmail"
                         placeholder="Email">
                </div>
                <div class="form-group col-md-2">
                  <select class="form-control" ng-model="srchCase.combined"
                          ng-change="loadMainData()">
                    <option value="" selected="selected">Combined</option>
                    <option value="1">Yes</option>
                    <option value="2">No</option>
                  </select>
                </div>
                <div class="form-group col-md-2">
                  <select class="form-control" ng-model="srchCase.currencyId"
                          ng-change="loadMainData()">
                    <option value="" selected="selected">Currency</option>
                    <option ng-repeat="v in currencies" ng-selected="v.id === srchCase.currencyId"
                            value="{{v.id}}">{{v.name}}
                    </option>
                  </select>
                </div>
                <div class="form-group col-md-3">
                  <select class="form-control" ng-model="srchCase.guideLanguageId"
                          ng-change="loadMainData()">
                    <option value="" selected="selected">Language</option>
                    <option ng-repeat="v in languages"
                            ng-selected="v.id === srchCase.guideLanguageId"
                            value="{{v.id}}">{{v.name}}
                    </option>
                  </select>
                </div>
                <div class="form-group col-md-2">
                  <select class="form-control" ng-model="srchCase.packageCategoryId"
                          ng-change="loadMainData()">
                    <option value="" selected="selected">Pakage Category</option>
                    <option ng-repeat="v in packageCategories"
                            ng-selected="v.id === srchCase.packageCategoryId"
                            value="{{v.id}}">{{v.name}}
                    </option>
                  </select>
                </div>
                <div class="form-group col-md-4">
                  <div class="input-group">
                    <div class="input-append">
                      <input type="text" name="date" class="form-control srch"
                             placeholder="From" ng-model="srchCase.tourStart">
                    </div>
                    <span class="input-group-addon">Tour Starts</span>
                    <div class="input-append">
                      <input type="text" name="date" class="form-control srch"
                             placeholder="To" ng-model="srchCase.tourStartTo">
                    </div>
                  </div>
                </div>
                <div class="form-group col-md-4">
                  <div class="input-group">
                    <div class="input-append">
                      <input type="text" name="date" class="form-control srch"
                             placeholder="From" ng-model="srchCase.tourEnd">
                    </div>
                    <span class="input-group-addon">Tour Ends</span>
                    <div class="input-append">
                      <input type="text" name="date" class="form-control srch"
                             placeholder="To" ng-model="srchCase.tourEndTo">
                    </div>
                  </div>
                </div>
                <div class="form-group col-md-2">
                  <button class="btn btn-default col-md-12" ng-click="loadMainData()" id="srchBtnId">
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
              <th>Email</th>
              <th>Days/Nights</th>
              <th>Arrival</th>
              <th>Budget</th>
              <th class="col-md-2 text-center">Action</th>
            </tr>
            </thead>
            <tbody title="Double Click For Detailed Information">
            <tr ng-repeat="r in list" ng-dblclick="handleDoubleClick(r.id)">
              <td>{{r.id}}</td>
              <td>{{r.contactEmail}}</td>
              <td>{{r.daysCount}} / {{r.nightsCount}}</td>
              <td>{{r.arrivalTime}}</td>
              <td>{{r.budget}}</td>
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