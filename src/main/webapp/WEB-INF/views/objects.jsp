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
    $scope.request = {types: []};
    $scope.srchCase = {};
    $scope.imageNames = [];

    $scope.loadMainData = function () {
      $('#loadingModal').modal('show');

      function getMainData(res) {
        $scope.list = res.data;
        $('#loadingModal').modal('hide');
      }

      ajaxCall($http, "objects/get-objects?start=" + $scope.start + "&limit=" + $scope.limit, angular.toJson($scope.srchCase), getMainData);
    }

    $scope.loadMainData();

    function getTypes(res) {
      $scope.types = res.data;
    }

    ajaxCall($http, "objects/get-types", null, getTypes);

    $scope.remove = function (id) {
      if (confirm("Pleace confirm operation?")) {
        if (id != undefined) {
          function resFnc(res) {
            if (res.errorCode == 0) {
              successMsg('Operation Successfull');
              $scope.loadMainData();
            }
          }

          ajaxCall($http, "objects/delete?id=" + id, null, resFnc);
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

      function getImages(res) {
        $scope.slcted.images = res.data;
      }

      ajaxCall($http, "objects/get-images?id=" + id, null, getImages);
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
      $scope.req.personPrice = $scope.request.personPrice;
      $scope.req.typeId = $scope.request.typeId;
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
      ajaxCall($http, "objects/save", angular.toJson($scope.req), resFunc);
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

    $scope.uploadFiles = function (files) {
      $scope.files = files;
      angular.forEach(files, function (file) {

        $scope.slcted.images.push({name: 'sight' + $scope.slcted.id + '_' + file.name});
        $scope.imageNames.push('sight' + $scope.slcted.id + '_' + file.name);

        if (file && !file.$error) {
          file.upload = Upload.upload({
            url: 'objects/add-images?id=sight' + $scope.slcted.id,
            file: file
          });

          file.upload.then(function (response) {
            $timeout(function () {
              file.result = response.data;
            });
          }, function (response) {
            if (response.status > 0)
              $scope.errorMsg = response.status + ': ' + response.data;
          });
        }
      });
      console.log($scope.files);
    }

    $scope.removeDoc = function (docname) {

      var index = $scope.imageNames.indexOf(docname);
      $scope.imageNames.splice(index, 1);

      var selected = $filter('filter')($scope.slcted.images, {name: docname}, true);
      var index = $scope.slcted.images.indexOf(selected);
      $scope.slcted.images.splice(index, 1);
    }

    $scope.saveImages = function () {
      function onImageSave(res) {
        if (res.errorCode == 0) {
          successMsg('Operation Successfull');
          closeModal('imageModal');
        } else {
          errorMsg('Operation Failed');
        }
      }

      angular.forEach($scope.slcted.images, function (v) {
        var index = $scope.imageNames.indexOf(v.name);
        if (index < 0) {
          $scope.imageNames.push(v.name);
        }
      });

      console.log(angular.toJson($scope.imageNames));

      ajaxCall($http, "objects/save-images?id=" + $scope.slcted.id, angular.toJson($scope.imageNames), onImageSave);
    }

  }]);
</script>

<div class="modal fade bs-example-modal-lg not-printable" id="imageModal" role="dialog"
     aria-labelledby="imageModalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Upload Images</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <form class="form-horizontal" name="ediFormName">
            <table class="table table-striped">
              <tr ng-repeat="item in slcted.images">
                <th class="text-right col-sm-3"></th>
                <td class="col-sm-6">
                  <a href="misc/get-file?name={{'uploads/' + item.name.split('.')[0]}}" target="_blank">
                    <img src="misc/get-file?name={{'uploads/' + item.name.split('.')[0]}}&" + new Date().getTime();
                         class="thumbnail img-responsive"/>
                  </a>
                </td>
                <td class="col-sm-3">
                  <a><span class="fa fa-trash-o fa-lg" style="cursor: pointer;"
                           ng-click="removeDoc(item.name)"></span></a>
                </td>
              </tr>
            </table>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Images</label>
              <div class="col-sm-9">
                <div class="input-group input-file">
                  <input type="text" id="uploadDocNameInput" class="form-control"
                         onclick="$('#documentId').trigger('click');"
                         placeholder='Choose files...'/>
                  <span class="input-group-btn">
                    <button class="btn btn-default btn-choose" id="documentId"
                            type="file" ngf-select="uploadFiles($files)" ng-model="files" multiple
                            accept="*/*" ngf-max-size="30MB">
                      Browse</button>
    		           </span>
                </div>
              </div>
            </div>
            <div class="form-group col-sm-10"></div>
            <div class="form-group col-sm-10"></div>
            <div class="form-group col-sm-12 text-center">
              <a class="btn btn-app" ng-click="saveImages()">
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
              <th class="col-md-4 text-right">Person Price</th>
              <td>{{slcted.personPrice}}</td>
            </tr>
            <tr>
              <th class="col-md-4 text-right">Type</th>
              <td>{{slcted.type.name}}</td>
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
              <th class="text-right">Record Created</th>
              <td>{{slcted.createDate}}</td>
            </tr>
            <tr>
              <th class="text-right">images</th>
              <td>
                <ul>
                  <li ng-repeat="item in slcted.images">
                    <a href="misc/get-file?name=uploads/{{item.name.split('.')[0]}}"
                       target="_blank">{{item.name}}</a>
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
              <label class="control-label col-sm-3">Type</label>
              <div class="col-sm-9">
                <select class="form-control" ng-model="request.typeId"
                        required>
                  <option ng-repeat="v in types"
                          ng-selected="v.id === request.typeId"
                          value="{{v.id}}">{{v.name}}
                  </option>
                </select>
              </div>
            </div>
            <div class="form-group col-sm-10 ">
              <label class="control-label col-sm-3">Person Price</label>
              <div class="col-sm-9">
                <input type="text" ng-model="request.personPrice" required
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
                  <select class="form-control" ng-model="srchCase.typeId"
                          ng-change="loadMainData()">
                    <option value="" selected="selected">Type</option>
                    <option ng-repeat="v in types" ng-selected="v === srchCase.typeId"
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
              <th>Type</th>
              <th>Person Price</th>
              <th class="col-md-3 text-center">Action</th>
            </tr>
            </thead>
            <tbody title="Double Click For Detailed Information">
            <tr ng-repeat="r in list" ng-dblclick="handleDoubleClick(r.id)">
              <td>{{r.id}}</td>
              <td>{{r.nameEn}}</td>
              <td>{{r.type.name}}</td>
              <td>{{r.personPrice}}</td>
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