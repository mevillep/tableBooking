/**
 * Created by Meville on 1/14/16.
 */


(function() {
    'use strict';

    angular
        .module('myApp')
        .service('tabledataService', tabledataService);

    tabledataService.$inject = ['$http', '$q'];

    function tabledataService ($http, $q) {

        var self = this;

        self.getTables = function () {
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/RESTApi/api/tables')
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.getTableById = function (id) {
            var defer = $q.defer();
            $http
                .get('http://localhost:8080/RESTApi/api/tables/' + id)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.deleteTable = function (id) {
            var defer = $q.defer();
            $http
                .delete('http://localhost:8080/RESTApi/api/tables/' + id)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.createTable = function (user1) {
            var defer = $q.defer();
console.log("in service table"+user1);
            $http
                .post('http://localhost:8080/RESTApi/api/tables',user1)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.updateTable= function (id, table1) {
            console.log("this is in service" + table1+ id);
            var defer = $q.defer();
            $http
                .put('http://localhost:8080/RESTApi/api/tables/' + id, table1)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };




    }
})();