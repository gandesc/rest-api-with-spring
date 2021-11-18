angular.module('hello', [ 'ngRoute' ]).config(function($routeProvider) {

    $routeProvider.when('/', {
        templateUrl : 'home.html',
        controller : 'home'
    }).otherwise('/');

}).controller('home', function($scope, $http, $window) {
    var headers = {
        "Accept" : "application/json", 
    };
    
    $http.get("http://localhost:8082/um-webapp/api/roles/1", {
        headers : headers
    }).success(function(data) {
        $scope.role = data;
    })
});
