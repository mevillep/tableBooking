/**
 * Created by Meville on 1/14/16.
 */


(function() {
    'use strict';

    angular
        .module('myApp')
        .service('customerdataService', customerdataService);

    customerdataService.$inject = ['$http', '$q'];

    function customerdataService ($http, $q) {

        var self = this;

        self.getCustomers = function () {
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/RESTApi/api/customers')
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.getCustomerById = function (id) {
            var defer = $q.defer();
            $http
                .get('http://localhost:8080/RESTApi/api/customers/' + id)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.deleteCustomer = function (id) {
            var defer = $q.defer();
            $http
                .delete('http://localhost:8080/RESTApi/api/customers/' + id)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.createCustomer = function (user1) {
            var defer = $q.defer();
console.log("this is in service" + user1);
            $http
                .post('http://localhost:8080/RESTApi/api/customers',user1)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.autoCreateCustomer = function (user1) {
            var defer = $q.defer();
            console.log("this is in auto service" + user1);
            $http
                .post('http://localhost:8080/RESTApi/api/reservations',user1)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };


        self.updateCustomer = function (id, user1) {
            console.log("this is in service" + user1+ id);
            var defer = $q.defer();
            $http
                .put('http://localhost:8080/RESTApi/api/customers/' + id, user1)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };


    }
})();