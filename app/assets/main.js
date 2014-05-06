 angular.module('myApp', []).
    controller('Hello', function($scope, $http, $timeout) {

        $scope.msg = "";

        $scope.socket = new WebSocket("ws://localhost:9090/echo-ws");
        $scope.socket.onmessage = function(msg) {
            $timeout(function() { $scope.msg = "hello, " + msg.data; });
        };

        $scope.sendName = function() {
            $scope.socket.send($scope.yourName);
            $http({method: 'GET', url: '/tweets', params: {q: $scope.yourName}}).
                success(function(data) {
                    $scope.tweets = data;
                });
        };

    });