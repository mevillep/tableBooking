/**
 * Created by Meville on 1/14/16.
 */
(function() {
    'use strict';

    angular
        .module('myApp')
        .service('reservationdataService', reservationdataService);

    reservationdataService.$inject = ['$http', '$q'];

    function reservationdataService ($http, $q) {

        var self = this;

        self.getReservations = function () {
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/RESTApi/api/reservations')
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.getReservationById = function (id) {
            var defer = $q.defer();
            $http
                .get('http://localhost:8080/RESTApi/api/reservations/' + id)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.deleteReservation = function (id) {
            var defer = $q.defer();
            $http
                .delete('http://localhost:8080/RESTApi/api/reservations/' + id)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };


        self.createReservation = function (reservation1) {
            var defer = $q.defer();
            console.log("this is in service" + reservation1);
            $http
                .post('http://localhost:8080/RESTApi/api/createReservation',reservation1)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

    }
})();