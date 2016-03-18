/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var LoginApp = angular.module('LoginApp',[]);
LoginApp.controller('loginController', ['$scope', '$http', 
    function($scope, $http){
        
        $scope.loginData = {};
        
        $scope.processLogin = function(){
            $http({
                method: 'POST',
                url: 'login',
                data: $.param($scope.loginData), //this is to serialize the object as params param1=blah&param2=blahblah
                headers: {'Content-Type' : 'application/x-www-form-urlencoded'}
            }).then(function (response){
                console.log("We succeeded!");
            console.log(response);
            console.log("Response data is: " + response.data);
            location.reload();
        
        },
                    function (response){
                console.log("we FAILED"); 
                console.log("Response Data is: " + response.data);
                    });
            
        };
        
}]);


LoginApp.controller('LogoutController', ['$scope', '$http', function($scope, $http){
  
  $scope.processLogout = function(){
      $http({
          method: 'GET',
          url: 'logout'   
      }).then(function(response){
          console.log("Logged Out Successfully");
          console.log(response.data);
      }, function(response){
          console.log("Failed to Logout");
          console.log(response.data);
      });
  };
        
}]);

