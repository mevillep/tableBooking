/**
 * Created by Meville on 1/16/16.
 */
/**
 * Created by Meville on 1/14/16.
 */


(function() {
    'use strict';

    angular
        .module('myApp')
        .service('settingdataService', settingdataService);

    settingdataService.$inject = ['$http', '$q'];

    function settingdataService ($http, $q) {

        var self = this;

        self.getSetting = function () {
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/RESTApi/api/restaurant')
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };


        self.updateSetting= function (id , setting) {
            console.log("this is in service" + setting+ id);
            var defer = $q.defer();
            $http
                .put('http://localhost:8080/RESTApi/api/restaurant/' + id , setting)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };




    }
})();