// Constants
var HTTP = "http://";
var INSTAGRAM_API_URL = "/services/time/api";

// 1myAppモジュールを作成
var app = angular.module('myApp', []);

app.controller('mainCtrl', ['$scope', 'ApiData', function ($scope, ApiData) {
    $scope.Reload = function () {
        ApiData.get().then(function(res){
            //$scope.items = res.data;
            $scope.instagramInfos = res.data.data;
            $scope.show_loading = false;
            
        });
        $scope.show_loading = true;
    };

    $scope.Reload();

  }])

app.factory('ApiData', function ($http) {
    return {
        get: function () {
        	//return $http.get('mock/sample.json')
        	var url = HTTP + location.host + INSTAGRAM_API_URL
        	return $http.get(url)
            //return $http.get('http://0.0.0.0:8080/services/time/api')
                .success(function (data, status, header, config) {
                    return data;
                });
            }
    };
  });