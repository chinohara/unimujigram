// Constants
var HTTP = "http://";
var INSTAGRAM_API_URL_BABYS = "/services/api/babys";
var INSTAGRAM_API_URL_KIDS = "/services/api/kids";
var INSTAGRAM_API_URL_SNAP = "/services/api/snap";

// myAppモジュールを作成
var app = angular.module('myApp', []);

app.controller('mainCtrl', ['$scope', 'ApiData', function ($scope, ApiData) {
    // 初期ロード処理
    $scope.Reload = function () {
        ApiData.get(INSTAGRAM_API_URL_BABYS).then(function(res){
            $scope.instagramInfos = res.data.data;
            $scope.show_loading = false;
            
        });
        $scope.show_loading = true;
    };
    
    // BabysPhoto呼び出し処理
    $scope.clickBabys = function() {
    	ApiData.get(INSTAGRAM_API_URL_BABYS).then(function(res){
            $scope.instagramInfos = res.data.data;
            $scope.show_loading = false;
            
        });
        $scope.show_loading = true;
    };
    
    // KidsPhoto呼び出し処理
    $scope.clickKids = function() {
    	ApiData.get(INSTAGRAM_API_URL_KIDS).then(function(res){
            $scope.instagramInfos = res.data.data;
            $scope.show_loading = false;
            
        });
        $scope.show_loading = true;
    };
    
    // Snap呼び出し処理
    $scope.clickBabySnap = function() {
    	ApiData.get(INSTAGRAM_API_URL_SNAP).then(function(res){
            //$scope.items = res.data;
            $scope.instagramInfos = res.data.data;
            $scope.show_loading = false;
            
        });
        $scope.show_loading = true;
    };

	// 初期処理実行
    $scope.Reload();

  }])

app.factory('ApiData', function ($http) {
    return {
        get: function (uri) {
        	var url = HTTP + location.host + uri
        	return $http.get(url)
                .success(function (data, status, header, config) {
                    return data;
                });
            }
    };
  });